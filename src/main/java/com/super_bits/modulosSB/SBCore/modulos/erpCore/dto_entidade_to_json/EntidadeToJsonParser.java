package com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_entidade_to_json;

import com.super_bits.modulosSB.SBCore.modulos.objetos.dto.ComoEntidadeToJsonParser;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringBuscaTrecho;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoDominioEntidadeGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeReflexivel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 * Converte entidades (transitórias ou persistentes) em JSON.
 *
 * Proteções embutidas: - Limite de profundidade: evita caminhos longos como
 * Cliente → Contrato → TipoContrato → VersaoContrato → ... O padrão é
 * PROFUNDIDADE_PADRAO (2 níveis de entidades aninhadas). Use o construtor com
 * pProfundidadeMaxima para ajustar.
 *
 * - Detecção de ciclos: o Set visitados (identidade de objeto) é compartilhado
 * por toda a árvore de chamadas recursivas. Se a mesma instância aparecer duas
 * vezes no caminho, a recursão é interrompida e emitido um marcador { "_ciclo":
 * true }.
 *
 * @author salvio
 */
public class EntidadeToJsonParser implements ComoEntidadeToJsonParser {

    // ---------------------------------------------------------------
    // Profundidade padrão: nível 0 (raiz) + 2 níveis de aninhamento.
    // Aumente conforme necessário, mas prefira ser explícito.
    // ---------------------------------------------------------------
    public static final int PROFUNDIDADE_PADRAO = 3;

    // ---------------------------------------------------------------
    // Resolução de metadados (sem alteração)
    // ---------------------------------------------------------------
    private static Class metadadosTransitoria = MetadadosParserEntidadeTransitoria.class;
    private static Class metadadosPersistente;

    private static ComoMetaDadosParserDeEntidade getMetadados(ComoEntidadeReflexivel pEntidade) {
        if (!UtilCRCReflexaoEntidade.isEntidadeJPA(pEntidade.getClass())) {
            try {
                return (ComoMetaDadosParserDeEntidade) metadadosTransitoria.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO,
                        "Falha obtendo processador de metadados de parser de entidade para json", ex);
            }
        } else {
            if (metadadosPersistente == null) {
                try {
                    metadadosPersistente = Class.forName(
                            "br.org.coletivoJava.fw.erp.implementacao.caramelocore"
                            + ".json_bind_core_padrao.util.MetadadosParserEntidadePersistente");
                } catch (ClassNotFoundException ex) {
                    CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO,
                            "Falha obtendo processador de metadados de parser de entidade para json", ex);
                }
            }
            try {
                return (ComoMetaDadosParserDeEntidade) metadadosPersistente.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO,
                        "Falha obtendo processador de metadados de parser de entidade para json", ex);
            }
        }
        return null;
    }

    // ---------------------------------------------------------------
    // Estado da instância
    // ---------------------------------------------------------------
    private final ComoEntidadeReflexivel entidade;
    private final JsonObjectBuilder jsonBuilder;
    private final ComoMetaDadosParserDeEntidade metadadosparser;

    /**
     * Nível atual na árvore (0 = raiz).
     */
    private final int nivelAtual;

    /**
     * Profundidade máxima permitida para entidades aninhadas.
     */
    private final int profundidadeMaxima;

    /**
     * Conjunto de identidades (System.identityHashCode) das entidades já
     * abertas no caminho atual. Usado para detectar ciclos estruturais SEM
     * chamar toString() / hashCode() da entidade — evita StackOverflow em
     * objetos cujo getId() ou toString() referenciam de volta o pai (ex.:
     * RespostaComunicacao).
     *
     * Usamos identityHashCode aqui (e não toString) porque toString() pode ser
     * recursivo; a detecção por identidade é segura e suficiente para quebrar
     * ciclos.
     */
    private final Set<Integer> visitadosIdentidade;

    // ---------------------------------------------------------------
    // Construtores públicos
    // ---------------------------------------------------------------
    /**
     * Profundidade padrão (2), sem entidade-pai.
     */
    public EntidadeToJsonParser(ComoEntidadeReflexivel pEntidade) {
        this(pEntidade, PROFUNDIDADE_PADRAO);
    }

    /**
     * Profundidade explícita, sem entidade-pai.
     */
    public EntidadeToJsonParser(ComoEntidadeReflexivel pEntidade, int pProfundidadeMaxima) {
        this(pEntidade, pProfundidadeMaxima, 0, new HashSet<Integer>());
    }

    // ---------------------------------------------------------------
    // Construtor interno (recursão)
    // ---------------------------------------------------------------
    /**
     * @param pEntidade entidade a serializar
     * @param pProfundidadeMaxima profundidade máxima de aninhamento
     * @param pNivelAtual nível corrente (0 = raiz)
     * @param pVisitados set de identidades já abertas no caminho
     */
    private EntidadeToJsonParser(
            ComoEntidadeReflexivel pEntidade,
            int pProfundidadeMaxima,
            int pNivelAtual,
            Set<Integer> pVisitados) {

        this.entidade = pEntidade;
        this.nivelAtual = pNivelAtual;
        this.profundidadeMaxima = pProfundidadeMaxima;
        this.visitadosIdentidade = pVisitados;
        this.metadadosparser = getMetadados(pEntidade);
        this.jsonBuilder = Json.createObjectBuilder();

        // Registra por identidade ANTES de descer — evita chamar toString()
        // em objetos que podem ser recursivos (ex.: RespostaComunicacao)
        int idEntidade = System.identityHashCode(pEntidade);
        visitadosIdentidade.add(idEntidade);
        String nomeClasse = UtilCRCStringBuscaTrecho.getStringAteEncontrarIsto(pEntidade.getClass().getSimpleName(), "$");
        jsonBuilder.add("entidade", nomeClasse);
        for (ItfCampoInstanciado campo : ((ComoDominioEntidadeGenerico) pEntidade).getCamposInstanciados()) {
            adicionarCampo(campo);
        }

        // Remove ao terminar: permite a mesma instância em ramos independentes
        visitadosIdentidade.remove(idEntidade);
    }

    // ---------------------------------------------------------------
    // Serialização de campo
    // ---------------------------------------------------------------
    public void adicionarCampo(ItfCampoInstanciado pCampo) {
        if (pCampo.getValor() == null) {
            return;
        }

        if (pCampo.isUmaListagemParticular() || pCampo.isUmaListaDinamica() || pCampo.getFabricaTipoAtributo().equals(FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)) {

            System.out.println(pCampo.getFabricaTipoAtributo().getTipoPrimitivo());
            System.out.println("");

            if (metadadosparser.isEmLazyMode((ComoEntidadeSimples) entidade, pCampo.getNome())) {

                jsonBuilder.add(pCampo.getNome(),
                        Json.createObjectBuilder()
                                .add("nomeEntidade", BigDecimal.ONE)
                                .add("modoLasy", true)
                                .add("lista", true)
                                .add("linkLoadEntidade",
                                        metadadosparser.getLinkLoadEntidade(
                                                (ComoEntidadeSimples) entidade, pCampo.getNome())));
                return;
            }

            JsonArrayBuilder listaEntidades = Json.createArrayBuilder();
            List lista = (List) pCampo.getValor();
            for (Object item : lista) {
                if (item instanceof ComoEntidadeReflexivel) {
                    ComoEntidadeReflexivel itemEntidade = (ComoEntidadeReflexivel) item;
                    int idItem = System.identityHashCode(itemEntidade);

                    // ciclo: mesma instância já está no caminho atual
                    if (visitadosIdentidade.contains(idItem)) {
                        listaEntidades.add(Json.createObjectBuilder()
                                .add("_ciclo", true)
                                .add("_tipo", itemEntidade.getClass().getSimpleName()));
                        continue;
                    }

                    // profundidade máxima atingida
                    if (nivelAtual >= profundidadeMaxima) {
                        listaEntidades.add(Json.createObjectBuilder()
                                .add("_profundidadeMaxima", true)
                                .add("_tipo", itemEntidade.getClass().getSimpleName()));
                        continue;
                    }

                    // recursão segura
                    listaEntidades.add(new EntidadeToJsonParser(
                            itemEntidade,
                            profundidadeMaxima,
                            nivelAtual + 1,
                            visitadosIdentidade
                    ).getJsonBuilder());

                } else if (item != null) {
                    // item escalar (String, Number, Boolean) dentro de uma lista mista
                    listaEntidades.add(item.toString());
                }
            }
            jsonBuilder.add(pCampo.getNome(), listaEntidades);
            return;
        }

        switch (pCampo.getFabricaTipoAtributo().getTipoPrimitivo()) {
            case INTEIRO:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(),
                        Integer.valueOf(pCampo.getValor().toString()));
                break;

            case NUMERO_LONGO:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(), pCampo.getValorComoLong());
                break;

            case LETRAS:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(), pCampo.getValor().toString());
                break;

            case DATAS:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(),
                        pCampo.getValorComoDate().getTime());
                break;

            case BOOLEAN:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(), pCampo.getValorComoBoolean());
                break;

            case DECIMAL:
                jsonBuilder.add(pCampo.getNomeCamponaClasse(), pCampo.getValorComoDouble());
                break;

            case ENTIDADE:
                adicionarCampoEntidade(pCampo);
                break;

            case OUTROS_OBJETOS:
                switch (pCampo.getFabricaTipoAtributo()) {
                    case ENUM_FABRICA:
                        jsonBuilder.add(pCampo.getNomeCamponaClasse(), pCampo.getValor().toString());
                        break;
                    default:
                        System.out.println("Tipo não reconhecido como json: " + pCampo.getNomeCamponaClasse());
                }

                break;

            default:
                throw new AssertionError(pCampo.getFabricaTipoAtributo().getTipoPrimitivo().name());
        }
    }

    // ---------------------------------------------------------------
    // Lógica de aninhamento com controle de profundidade e ciclos
    // ---------------------------------------------------------------
    private void adicionarCampoEntidade(ItfCampoInstanciado pCampo) {

        // --- modo lazy: delega ao servidor, independente de profundidade ---
        if (metadadosparser.isEmLazyMode((ComoEntidadeSimples) entidade, pCampo.getNome())) {
            jsonBuilder.add(pCampo.getNome(),
                    Json.createObjectBuilder()
                            .add("nomeEntidade", BigDecimal.ONE)
                            .add("modoLasy", true)
                            .add("lista", false)
                            .add("linkLoadEntidade",
                                    metadadosparser.getLinkLoadEntidade(
                                            (ComoEntidadeSimples) entidade, pCampo.getNome())));
            return;
        }

        if (!(pCampo.getValor() instanceof EntidadeSimples)) {
            return; // tipo inesperado — ignora sem quebrar
        }

        ComoEntidadeReflexivel entidadeFilha = (ComoEntidadeReflexivel) pCampo.getValor();
        int idFilha = System.identityHashCode(entidadeFilha);

        // --- detecção de ciclo: mesma instância já está no caminho atual ---
        if (visitadosIdentidade.contains(idFilha)) {
            jsonBuilder.add(pCampo.getNomeCamponaClasse(),
                    Json.createObjectBuilder()
                            .add("_ciclo", true)
                            .add("_tipo", entidadeFilha.getClass().getSimpleName()));
            return;
        }

        // --- limite de profundidade atingido ---
        if (nivelAtual >= profundidadeMaxima) {
            jsonBuilder.add(pCampo.getNomeCamponaClasse(),
                    Json.createObjectBuilder()
                            .add("_profundidadeMaxima", true)
                            .add("_tipo", entidadeFilha.getClass().getSimpleName()));
            return;
        }

        // --- recursão segura ---
        jsonBuilder.add(pCampo.getNomeCamponaClasse(),
                new EntidadeToJsonParser(
                        entidadeFilha,
                        profundidadeMaxima,
                        nivelAtual + 1,
                        visitadosIdentidade
                ).getJsonBuilder());
    }

    // ---------------------------------------------------------------
    // Acesso ao resultado
    // ---------------------------------------------------------------
    @Override
    public JsonObjectBuilder getJsonBuilder() {
        return jsonBuilder;
    }
}
