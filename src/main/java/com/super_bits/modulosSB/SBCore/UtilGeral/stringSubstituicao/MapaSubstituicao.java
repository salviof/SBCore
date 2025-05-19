/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import org.coletivojava.fw.api.analiseDados.ItfMapaSubstituicao;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringBuscaTrecho;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringVariaveisEntreCaracteres;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.formulario.ItfFormularioAcao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class MapaSubstituicao implements ItfMapaSubstituicao {

    protected final Map<String, String> mapaSubstituicao = new HashMap<>();
    protected final Map<String, String> mapaSubstituicaoImagem = new HashMap<>();
    protected final Map<String, Map<String, String>> mapaSubstituicaoListas = new HashMap<>();
    protected final Map<String, Map<Integer, List<String>>> ordemMapaSubstituicaoListas = new HashMap<>();
    protected final List<ItfBeanSimples> entidadesVinculada = new ArrayList<>();

    public String getValorImagem(String pValorChave) {
        return mapaSubstituicaoImagem.get(pValorChave);
    }

    @Override
    public String substituirEmString(String pString) {

        if (pString == null) {
            return "";
        }
        String novaString = pString;
        for (String palavraChave : mapaSubstituicao.keySet()) {
            try {

                List<String> valoresEncontradas = UtilSBCoreStringVariaveisEntreCaracteres.extrairVariaveisEntreColchete(pString);
                for (String chave : valoresEncontradas) {
                    if (chave.startsWith("[link:")) {
                        String nomeAcao = chave.replace("[link:", "").replace("]", "");

                        ItfBeanSimples entidade = null;

                        ItfAcaoDoSistema pAcao = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(nomeAcao);
                        //ItfFormularioAcao formulario = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao, paramentros);
                        //formulario.getParametrosURL().stream().fo
                        String link = null;
                        if (entidade == null) {
                            link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema());
                        } else {
                            link = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(pAcao.getEnumAcaoDoSistema(), entidade);
                        }
                        novaString = novaString.replace(chave, link);
                    } else {
                        String valorConformidade = chave.replaceAll("<[^>]*>", "");
                        if (mapaSubstituicao.containsKey(valorConformidade)) {

                            novaString = novaString.replace(valorConformidade.replace("]", "").replace("[", ""), mapaSubstituicao.get(valorConformidade));
                        }
                    }
                }
                //if (!SBCore.isEmModoProducao()) {
                //     System.out.println("Verificando substituição em [" + pString + "] por" + mapaSubstituicao.get(palavraChave));
                // }
                // if (pString.contains(palavraChave)) {

                //if (mapaSubstituicao.get(palavraChave) != null) {
                //   novaString = novaString.replace(palavraChave, mapaSubstituicao.get(palavraChave));
                //}
                //}
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha subistituindo" + palavraChave, t);
            }
        }
        System.out.println("Resultado=" + novaString);
        return novaString.replace("[", "").replace("]", "");

    }

    protected Map<String, String> getMapaSubstituicao() {
        return mapaSubstituicao;
    }

    public final String getChaveiLstasByTextoencontrado(String texto) {
        String chave = UtilSBCoreStringFiltros.getStringSemNumeros(texto);
        chave = UtilSBCoreStringBuscaTrecho.getStringAteEncontrarIsto(chave, "[]");
        return chave;
    }

    public final Map<String, String> getValoresListas(String pChaveLista) {
        return mapaSubstituicaoListas.get(pChaveLista);
    }

    public Map<Integer, List<String>> getOrdemItensLista(String pNomeLista) {
        return ordemMapaSubstituicaoListas.get(pNomeLista);
    }

    @Override
    public final void adicionarPalavraChave(String palavra, String valor) {
        if (!palavra.replaceAll("\\[[0-9]", "-").equals(palavra)) {
            String chavelista = getChaveiLstasByTextoencontrado(palavra);
            Integer palavraIndice = Integer.valueOf(UtilSBCoreStringFiltros.getNumericosDaString(palavra));

            if (mapaSubstituicaoListas.get(chavelista) == null) {
                mapaSubstituicaoListas.put(chavelista, new HashMap<>());
                ordemMapaSubstituicaoListas.put(chavelista, new HashMap<>());
            }
            if (ordemMapaSubstituicaoListas.get(chavelista).get(palavraIndice) == null) {
                ordemMapaSubstituicaoListas.get(chavelista).put(palavraIndice, new ArrayList<>());
            }

            if (mapaSubstituicaoListas.get(chavelista) != null) {
                mapaSubstituicaoListas.get(chavelista).put(palavra, valor);
                ordemMapaSubstituicaoListas.get(chavelista).get(palavraIndice).add(palavra);
            }
        } else {
            mapaSubstituicao.put(palavra, valor);
        }

    }

    @Override
    public final void adicionarPalavrasChaveDoObjeto(ItfBeanSimples pObjeto) {
        adicionarPalavrasChaveDoObjeto(null, pObjeto);
        adicionarPalavrasChaveDoObjeto(pObjeto.getClass().getSimpleName(), pObjeto);
    }

    @Override
    public void adicionarPalavrasChaveDoObjeto(String prefixo, ItfBeanSimples pObjeto) {
        if (pObjeto == null) {
            return;
        }
        if (prefixo != null) {
            prefixo = prefixo + ".";
        } else {
            prefixo = "";
        }
        for (ItfCampoInstanciado campo : pObjeto.getCamposInstanciados()) {
            String nomeVariavelCampo = "não determinado";

            if (campo != null && !campo.isCampoNaoInstanciado()) {
                try {
                    nomeVariavelCampo = campo.getNomeCamponaClasse();
                } catch (Throwable t) {

                }
                if (nomeVariavelCampo.contains("json")) {
                    continue;
                }
                try {
                    if (campo.isUmCampoArquivoEntidade()) {
                        if (campo.getValor() != null) {
                            switch (FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(campo.getValor().toString())) {
                                case IMAGEM_WEB:
                                    adicionarImagem("[" + prefixo + campo.getNomeCamponaClasse() + "]", SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campo));

                                    break;
                            }
                        }

                    } else {
                        if (!campo.isCampoNaoInstanciado()) {
                            String valorCampo = "Valor não informado";
                            switch (campo.getFabricaTipoAtributo()) {

                                case LC_BAIRRO:
                                case LC_CIDADE:
                                case LC_LOCALIDADE:
                                case LC_UNIDADE_FEDERATIVA:
                                case OBJETO_DE_UMA_LISTA:
                                case ENUM_FABRICA:
                                case LISTA_OBJETOS_PUBLICOS:
                                case LISTA_OBJETOS_PARTICULARES:
                                case LC_LOCALIZACAO:
                                case CAMPO_SEPARADOR:
                                case GRUPO_CAMPO:
                                case GRUPOS_DE_CAMPO:
                                case FORMULARIO_DE_ACAO:
                                case TIPO_PADRAO_POR_DECLARACAO:
                                    continue;
                                default:
                                    valorCampo = campo.getValorTextoFormatado();

                            }
                            switch (campo.getFabricaTipoAtributo()) {
                                //TODO outras personalizações
                            }
                            mapaSubstituicao.put("[" + prefixo + nomeVariavelCampo + "]", valorCampo);

                        }
                    }

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "o Campo:" + nomeVariavelCampo + "não foi adicionado ao mapeamento" + t.getMessage(), t);
                    System.out.println("Observacao, o Campo:" + nomeVariavelCampo + "não foi adicionado ao mapeamento");
                }
            }
        }
    }

    @Override
    public final void adicionarImagem(String palavraChave, String valor) {
        if (!palavraChave.startsWith("[") || !palavraChave.endsWith("]")) {
            if (!palavraChave.startsWith("[")) {
                palavraChave = "[".concat(palavraChave);
            }
            if (!palavraChave.endsWith("]")) {
                palavraChave = (palavraChave).concat("]");
            }
        }
        mapaSubstituicaoImagem.put(palavraChave, valor);
    }

    /**
     *
     * @return Lista de Palavra chave dispon
     */
    @Override
    public List<String> getpalavrasChave() {
        List<String> palavras = new ArrayList<>();
        palavras.addAll(Lists.newArrayList(mapaSubstituicao.keySet().iterator()));
        palavras.addAll(Lists.newArrayList(mapaSubstituicaoImagem.keySet().iterator()));

        List<String> listagensEncontradas = new ArrayList<>();
        for (String chave : mapaSubstituicaoListas.keySet()) {
            for (String valor : mapaSubstituicaoListas.get(chave).keySet()) {
                String valorFormatado = UtilSBCoreStringFiltros.getStringSemNumeros(valor);
                if (!listagensEncontradas.contains(valorFormatado)) {
                    listagensEncontradas.add(valorFormatado);
                }
            }
        }
        palavras.addAll(listagensEncontradas);
        return palavras;
    }

}
