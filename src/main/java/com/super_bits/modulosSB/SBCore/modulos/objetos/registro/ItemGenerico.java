package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoMetodoEmContextoDeExecucao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.CentralAtributosDeObjetosSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfCentralAtributosDeObjetos;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoAtributoDeObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.util.ErrorMessages;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAssistenteDeLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroDeMapaDeCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroObtendoValorDoCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.excecao.ErroSetandoValorDeCampoPorReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanReflexoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

/**
 *
 *
 * @author sfurbino
 */
public abstract class ItemGenerico extends Object implements ItfBeanGenerico, ItfBeanReflexoes, Serializable, Comparable<ItfBeanSimples> {

    protected CampoMapValores camposEsperados;
    private Map<String, ItfCampoInstanciado> mapaCamposInstanciados;
    private boolean novoBeanPreparado;
    // TODO, Justificativas para alteração de Campos
    private Map<String, String> mapaJustificativasExecucaoAcao;
    private Map<String, ItfAssistenteDeLocalizacao> mapaAssistenteLocalizacao;

    private Map<String, Field> mapaCampoPorAnotacao;
    private boolean mapeouTodosOsCampos = false;

    private Map<ItfCalculos, Boolean> controleCalculo;

    public static enum DEFINICAO_VIEW {
        NAO_DEFINIDO, PADRAO, PERSONALIZADO
    }

    protected void zerarControleCalculos() {

        getControleCalculo().keySet().stream().forEach((calc) -> {
            getControleCalculo().put(calc, false);
        });

    }

    protected Map<ItfCalculos, Boolean> getControleCalculo() {

        if (controleCalculo == null) {
            controleCalculo = new HashMap();
        }
        return controleCalculo;
    }

    private final Class<?> classeModelo;

    public String getTesteParametro(String pteste) {
        if (pteste == null) {
            pteste = "";
        }
        return "o parametro é" + pteste;

    }

    protected Object getInstancia() {
        return this;
    }

    /**
     *
     *
     * A classe TipoAtributoObjetoSB Item Generico instanciado oferece todas as
     * propriedades extendidas que um campo deve ter, e possui um getValor e
     * SetValor, que acessa o campo diretamente via reflexão
     *
     *
     */
    protected class CampoIntemGenericoInstanciado extends CampoInstanciadoGenerico implements ItfCampoInstanciado {

        public CampoIntemGenericoInstanciado(Field pCampoReflection) {
            super(pCampoReflection);

        }

        @Override
        public void setValor(Object pValor) {

            aplicarValor(getInstancia(), pValor);

        }

        @Override
        public Object getValor() {

            return obterValor(getInstancia());

        }

        @Override
        public Object getParent() {
            return getInstancia();
        }

    }

    /**
     *
     * Obtem o um Objeto do Tipo TipoAtributoObjetoSB, analizando as anotações
     * do Field ( do java reflection)
     *
     * @param pCampo
     * @return
     */
    protected ItfCentralAtributosDeObjetos getCentraldeAtributosDoObjeto(Field pCampo) {
        return new CentralAtributosDeObjetosSemPersistencia();
    }

    /**
     *
     * Retorna uma mapa de campos que não sejam estaticos de uma classe
     * contendo;
     *
     * @param object Classe Analizada
     * @return Mapa, contendo: nomeDoCampo,Field (do reflection Java)
     */
    protected static Map<String, Field> analyze(Object object) {
        try {

            if (object == null) {
                throw new NullPointerException("Erro obtendo análalise de propriedades da classe");
            }
            String teste = "";
            StringBuilder teaste = new StringBuilder();

            Map<String, Field> map = new TreeMap<>();

            Class<?> current = object.getClass();

            for (Field field : current.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!map.containsKey(field.getName())) {
                        map.put(field.getName(), field);
                    }
                }

            }

            return map;
        } catch (NullPointerException | SecurityException e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro analizando propriedades da class", e);
            return new TreeMap<>();
        }
    }

    /**
     *
     * Copia todos os campos de uma classe para outra do mesmo tipo, utilizando
     * reflection
     *
     * @param dados
     */
    public void copiaDados(Object dados) {
        if (dados == null) {
            return;
        }
        Map<String, Field> fromFields = analyze(dados);
        Map<String, Field> toFields = analyze(this);
        fromFields.keySet().retainAll(toFields.keySet());

        for (Entry<String, Field> fromFieldEntry : fromFields.entrySet()) {
            final String name = fromFieldEntry.getKey();
            final Field sourceField = fromFieldEntry.getValue();
            final Field targetField = toFields.get(name);
            if (targetField.getType().isAssignableFrom(sourceField.getType())) {
                sourceField.setAccessible(true);
                if (Modifier.isFinal(targetField.getModifiers())) {
                    continue;
                }
                targetField.setAccessible(true);
                try {
                    targetField.set(this, sourceField.get(dados));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Can't access field!");
                }
            }
        }
    }

    protected ItemGenerico() {
        super();
        this.camposEsperados = new CampoMapValores();
        classeModelo = this.getClass();
        UtilSBCoreReflexao.instanciarListas(this);

    }

    /**
     *
     * Valida o envio dos parametros de preparo do novo Objeto de acordo com a
     * anotação InfoPreparacaoObjeto
     *
     *
     * @param parametros
     * @throws
     * com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ErroPreparandoObjeto
     */
    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        novoBeanPreparado = true;
        try {
            InfoPreparacaoObjeto infoPreparacaoObjeto = UtilSBCoreReflexaoObjeto.getInfoPreparacaoObjeto(this.getClass());

            if (infoPreparacaoObjeto != null) {
                UtilSBCoreReflexaoObjeto.validarMetodoPrepararObjeto(infoPreparacaoObjeto, parametros);
            }

        } catch (Throwable t) {
            throw new ErroPreparandoObjeto((ItfBeanSimplesSomenteLeitura) this, t);

        }

    }

    protected boolean isClasseFinalPesquisa(Class pClasse) {
        return (pClasse == ItemGenerico.class || pClasse == Object.class);

    }

    /**
     *
     * Retorna o TipoAtributoObjetoSB de acordo com a Anotação
     *
     * @param pNomedaAnotacao
     * @return
     */
    protected Field getCampoByAnotacao(FabTipoAtributoObjeto pNomedaAnotacao) {
        Class classeEntidade = null;
        try {
            if (this instanceof HibernateProxy) {
                classeEntidade = HibernateProxyHelper.getClassWithoutInitializingProxy(this.getClass());
            } else {

                classeEntidade = this.getClass();
            }
        } catch (Throwable t) {
            classeEntidade = this.getClass();
        }
        Field campo = UtilSBCoreReflexaoCaminhoCampo.getSBCampobyTipoCampo(classeEntidade, pNomedaAnotacao);
        return campo;

    }

    /**
     *
     * Cria um campo Esperado que não é obrigatório
     *
     *
     * @param pCampo
     * @deprecated
     */
    @Deprecated
    protected void adcionaCampoEsperado(CampoEsperado pCampo) {
        adcionaCampoEsperado(pCampo, false);
    }

    /**
     *
     * Quando um Objeto generico é criado, é nescessário especificar quais os
     * campos devem ser anotados ao extender a classe exemplo: Uma classe
     * abstrata que implementa ItfBeanGenerico deve ter em seu constructor
     *
     * adcionaCampoEsperado(new
     * CampoEsperado(FabTipoAtributoObjeto.NOME_CURTO),true);
     * adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID),true);
     *
     * Desta forma sempre que a Classe for instanciada e estes campos NãO
     * tiverem sido anotados o sistema irá disparar um erro do tipo PARATUDO
     *
     * @param pCampo TipoAtributoObjetoSB que deve ser instanciado com o
     * FabCampo
     * @param pObrigatorio (Especifica que a anotação de um campo deste tipo é
     * obrigatória na classe).
     */
    protected final void adcionaCampoEsperado(CampoEsperado pCampo, boolean pObrigatorio) {

        try {
            Field campo;
            if (this instanceof HibernateProxy) {
                Class classe = UtilSBCoreReflexaoObjeto.getClassExtraindoProxy(this.getClass().getSimpleName());
                campo = UtilSBCoreReflexaoCaminhoCampo.getSBCampobyTipoCampo(classe, pCampo.getTipoCampo());
            } else {
                campo = getCampoByAnotacao(pCampo.getTipoCampo());
            }
            pCampo.setAnotacaoObrigatoria(pObrigatorio);
            if (campo != null) {
                pCampo.setCampoReflex(campo);
                pCampo.setFoiAnotado(true);

            } else {
                pCampo.setFoiAnotado(false);
                if (pObrigatorio) {
                    throw new UnsupportedOperationException("Campo " + pCampo.getTipoCampo() + " obrigatório não foi implementado na classe " + this.getClass().getSimpleName());
                }
            }

            camposEsperados.AdcionaCampo(pCampo);
        } catch (Throwable e) {
            if (SBCore.isEmModoDesenvolvimento()) {
                //Campo não encontrado utilizndo o seguinte método:
                Field campo = getCampoByAnotacao(pCampo.getTipoCampo());
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Campo obrigatório para o tipo de objeto não foi anotado com infoCampo, O tipo não encontrado foi: " + pCampo.getTipoCampo().name() + " Objeto: " + this.getClass().getSimpleName() + " (Atenção, nas proximas versões este erro irá gerar um PARATUDO", e);

        }

    }

    /**
     *
     * Procura o primeiro campo anotado com certa anotação e seta um valor a ele
     *
     * @param tipoCampo
     * @param valor
     */
    protected void setValorByTipoCampoEsperado(FabTipoAtributoObjeto tipoCampo, Object valor) {
        try {

            setValorByFieldReflexao(getCampoReflexaoByAnotacao(tipoCampo), valor);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro stando valor para Tipo de campo esperado (" + tipoCampo + ")->" + valor, t);
        }
    }

    /**
     *
     * Procura o primeiro campo anotado com certa anotação e seta um valor do
     * tipo primitivo int a ele
     *
     * @param tipoCampo Anotação pesquisada
     * @param valor Valor a ser configurado
     */
    protected void setValorByTipoCampoEsperado(FabTipoAtributoObjeto tipoCampo, int valor) {
        try {

            setValorByFieldReflexao(getCampoReflexaoByAnotacao(tipoCampo), valor);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro stando valor para Tipo de campo esperado", t);
        }
    }

    private void setValorByFieldReflexao(Field pCampoReflexao, Object valor) throws ErroSetandoValorDeCampoPorReflexao {

        try {
            if (pCampoReflexao == null) {
                throw new UnsupportedOperationException("Chamada de SetValorByFieldReflexao, com Fileld nulo em " + this);
            }
            pCampoReflexao.setAccessible(true);

            pCampoReflexao.set(getInstancia(), valor);
            return;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando setar novo valor para o campo " + pCampoReflexao.getName() + " na classe " + this.getClass().getSimpleName() + " via reflection", ex);
        }

        throw new ErroSetandoValorDeCampoPorReflexao();

    }

    private void setValorByFieldReflexao(Field pCampoReflexao, int valor) throws ErroSetandoValorDeCampoPorReflexao {
        pCampoReflexao.setAccessible(true);
        try {
            pCampoReflexao.set(this, valor);
            return;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando setar novo valor via reflection", ex);
        }

        throw new ErroSetandoValorDeCampoPorReflexao();

    }

    private Object getValorByFieldReflexao(Field pCampoReflexao) throws ErroObtendoValorDoCampoPorReflexao {
        try {

            pCampoReflexao.setAccessible(true);

            Object valor;
            try {
                String tipoDeValor = pCampoReflexao.getType().getName();
                if (tipoDeValor.equals(String.class.toString())) {
                    valor = (String) pCampoReflexao.get(this);
                } else // System.out.println("TTTTIIIPOOOO diferente de String:"+campoReflecao.getType().getName());
                if (pCampoReflexao.getType().getName().equals("int")) {
                    // System.out.println("TTTTIIIPOOOO int");
                    valor = (Integer) pCampoReflexao.get(this);
                } else if (pCampoReflexao.getType().getName()
                        .equals("java.lang.Double")
                        || pCampoReflexao.getType().getName()
                                .equals("double")) {
                    valor = (Double) pCampoReflexao.get(this);
                } else if (pCampoReflexao.getType().getSimpleName()
                        .equals("Date")) {
                    valor = ((Date) pCampoReflexao.get(this)).toString();
                } else if (pCampoReflexao.get(this) != null) {
                    valor = pCampoReflexao.get(this);
                    return valor;
                } else {
                    return null;
                }
                return valor;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Valor do Campo tipo:" + pCampoReflexao, e);
            }

        } catch (SecurityException e) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Valor do Campo", e);
        }

        throw new ErroObtendoValorDoCampoPorReflexao();

    }

    /**
     *
     * Retorna o Valor da propriedade de acordo com a anotação
     *
     * @param tipoCampo Tipo do campo procurado
     * @return Valor da propriedade pojo anotada com o campo procurado
     */
    protected Object getValorByTipoCampoEsperado(FabTipoAtributoObjeto tipoCampo) {
        CampoEsperado campoEsperadoEncontrado = camposEsperados.getCampo(tipoCampo);

        if (campoEsperadoEncontrado.getFoiAnotado()) {

            Field campoReflecao = campoEsperadoEncontrado.getCampoReflex();

            Object valor;
            try {
                valor = getValorByFieldReflexao(campoReflecao);

                if (valor == null) {
                    //todo. se for string verificar isempty, e se for numerico verificar = a 0
                    return campoEsperadoEncontrado.getValorPadrao();
                } else {
                    return valor;
                }

            } catch (ErroObtendoValorDoCampoPorReflexao ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O infoCampo  " + tipoCampo + " pesquisado não foi encontrado na classe" + this.getClass().getName(), ex);
            }

            if (campoEsperadoEncontrado.getAnotacaoObrigatoria()) {

                try {
                    throw new ErroDeMapaDeCampos(String.format(
                            ErrorMessages.CAMPO_ANOTACAO_OBRIGATORIO,
                            campoEsperadoEncontrado.getTipoCampo()));
                } catch (ErroDeMapaDeCampos e) {

                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Um campo  do tipo " + campoEsperadoEncontrado.getTipoCampo().getRegistro() + "era esperado, e não foi localizado na classe" + this.getClass(), e);
                }
            }

            return campoEsperadoEncontrado.getValorPadrao();

        }
        return null;
    }

    /**
     *
     * Metodo responsável para carregar o TipoAtributoObjetoSB instanciado da
     * classe
     *
     * @param pCampoReflection
     */
    private void carregaCampoInstanciado(Field pCampoReflection) {
        try {
            CampoIntemGenericoInstanciado campoformatado = (CampoIntemGenericoInstanciado) instanciarnovoCampo(pCampoReflection);
            if (mapaCamposInstanciados.get(pCampoReflection.getName()) != null) {
                throw new UnsupportedOperationException("O Campo " + pCampoReflection.getName() + " já foi instanciado");
            }
            //se encontrar adiciona duas vezes, para ser encontrado também pelo nome da anotacao
            mapaCamposInstanciados.put(pCampoReflection.getName(), campoformatado);
            InfoCampo anotacao = pCampoReflection.getAnnotation(InfoCampo.class);
            if (anotacao != null) {
                mapaCamposInstanciados.put(anotacao.tipo().toString().toUpperCase(), campoformatado);
            }
        } catch (Throwable t) {
            String nomeCampo = "Enviado campo null";
            if (pCampoReflection != null) {
                nomeCampo = pCampoReflection.getName();
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando novo campo [" + nomeCampo + "] em "
                    + this.getClass().getSimpleName(), t);
        }

    }

    protected <Y> Y getParametroInicialEnviado(Class<Y> pTipoParametro, Object... parametros) {
        return UtilSBCoreReflexaoObjeto.getParametroPrepararObjeto(pTipoParametro,
                UtilSBCoreReflexaoMetodoEmContextoDeExecucao.getAnotacaoNesteMetodo(InfoPreparacaoObjeto.class),
                parametros);
    }

    /**
     *
     * Metodo utilizado para criação do campo instanciado, deve Ser substituído
     * caso quera mudar a regra de negocio do campo instanciado.
     *
     * Destaque para o metodo getListaDeOpções e getListaDeOpcoesAutoComplet que
     * permite obter a lista de opções disponíveis
     *
     * @param pCampoReflexao
     * @return
     */
    protected ItfCampoInstanciado instanciarnovoCampo(Field pCampoReflexao) {
        return new CampoIntemGenericoInstanciado(pCampoReflexao);
    }

    private void buildCamposPorAnotacao() {
        if (mapaCampoPorAnotacao.isEmpty()) {

            mapaCamposInstanciados = new HashMap<>();
            mapaCampoPorAnotacao = new HashMap<>();
            Class classeAnalizada = this.getClass();
            while (!UtilSBCoreReflexaoCaminhoCampo.isClasseBasicaSB(classeAnalizada)) {

                for (Field campoEncontrado : classeAnalizada.getDeclaredFields()) {
                    InfoCampo anotacao = campoEncontrado.getAnnotation(InfoCampo.class);
                    if (anotacao != null) {
                        mapaCampoPorAnotacao.put(anotacao.tipo().toString().toUpperCase(), campoEncontrado);
                    }
                }
                classeAnalizada = classeAnalizada.getSuperclass();
            }

        }
    }

    public Map<String, ItfCampoInstanciado> getmapaCamposInstanciados(String pCampo) {

        if (mapaCampoPorAnotacao == null) {
            mapaCamposInstanciados = new HashMap<>();
            mapaCampoPorAnotacao = new HashMap<>();
        }

        buildCamposPorAnotacao();
        boolean pesquisouanotacao = false;
        pCampo = pCampo.replaceAll("\\[]", "");
        if (mapaCamposInstanciados.containsKey(pCampo)) {
            return mapaCamposInstanciados;
        } else {

            Class classeAnalizada = this.getClass();

            while (!UtilSBCoreReflexaoCaminhoCampo.isClasseBasicaSB(classeAnalizada)) {

                try {
                    Field campoEncontrado = classeAnalizada.getDeclaredField(pCampo);
                    campoEncontrado.setAccessible(true);

                    carregaCampoInstanciado(campoEncontrado);
                    return mapaCamposInstanciados;
                    // Caso não encontre pelo nome, procura pela anotação
                } catch (NoSuchFieldException | SecurityException ex) {
                    if (!pesquisouanotacao) {
                        pesquisouanotacao = true;

                        if (mapaCampoPorAnotacao.containsKey(pCampo)) {
                            carregaCampoInstanciado(mapaCampoPorAnotacao.get(pCampo));
                            return mapaCamposInstanciados;
                        } else {

                        }

                    }

                }
                classeAnalizada = classeAnalizada.getSuperclass();
            }

        }

        return mapaCamposInstanciados;

    }

    @Override
    @Deprecated()
    public ItfCampoInstanciado getCampoByCaminhoCampo(ItfCaminhoCampo pCaminhoCampo) {
        return getCampoByNomeOuAnotacao(pCaminhoCampo.getCaminhoSemNomeClasse());
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByNomeOuAnotacao(String pNome) {
        try {
            ItfCampoInstanciado campoInstanciado = getCampoByNomeOuAnotacao(pNome);
            if (campoInstanciado == null) {
                throw new UnsupportedOperationException("Erro tendando acessar atributo " + pNome + " em " + this.getClass().getSimpleName());
            } else {
                return campoInstanciado;
            }
        } catch (Throwable t) {
            if (!SBCore.isEmModoProducao()) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha acessando atributo de Objeto em " + this.getClass().getSimpleName(), t);
            }
            return new CampoNaoImplementado();
        }

    }

    @Override
    public Object getValorCampoByCaminhoCampo(ItfCaminhoCampo pCaminhoCampo) {
        try {
            ItfCampoInstanciado campoInstanciado = getCampoByNomeOuAnotacao(pCaminhoCampo.getCaminhoSemNomeClasse());
            if (campoInstanciado == UtilSBCoreReflexaoCaminhoCampo.CAMPO_NAO_IMPLEMENTADO) {
                return null;
            }
            Object resposta = campoInstanciado.getValor();
            return resposta;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo valor de campo:" + pCaminhoCampo + " na classe " + this.getClass().getSimpleName(), t);
            throw new UnsupportedOperationException("impossivel obter valor pelo caminho do campo");
        }

    }

    /**
     *
     *
     * O objeto campo instanciado, permite acessar e alterar o atributo via
     * propriedade .valor e utilizar outras opções como:
     *
     * .listaDepcoes .mascara .label .regex -> e muito mais
     *
     * @param pNomeOuANotacao Nome do TipoAtributoObjetoSB, ou String com O nome
     * do enum referente ao tipoCampo da anotação @InfoCampo
     * @return Um campo instanciado completo
     */
    public ItfCampoInstanciado ggetCampoetCampoInstanciadoByNomeOuAnotacao(String pNomeOuANotacao) {
        return getCampoByNomeOuAnotacao(pNomeOuANotacao);
    }

    /**
     * Depreciado, (nomeclatura incorreta) Utilize:
     *
     * @param pNomeOuANotacao
     * @see #getCampoInstanciadoByNomeOuAnotacao(java.lang.String)
     */
    @Override
    @Deprecated
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNomeOuANotacao) {

        if (UtilSBCoreReflexaoCaminhoCampo.isUmCampoSeparador(pNomeOuANotacao)) {
            return UtilSBCoreReflexaoCaminhoCampo.getCampoSeparador(pNomeOuANotacao);
        }
        int quantidade = UtilSBCoreReflexaoCaminhoCampo.getQuantidadeSubCampos(pNomeOuANotacao);

        if (quantidade == 1) {
            TIPO_ORIGEM_VALOR_CAMPO tipoOrigem = UtilSBCoreReflexaoCaminhoCampo.getTipoCampoLista(pNomeOuANotacao);

            switch (tipoOrigem) {
                case VALOR_COM_LISTA:
                case VALOR_LIVRE:
                    return getmapaCamposInstanciados(pNomeOuANotacao).get(pNomeOuANotacao);
                case VALORES_COM_LISTA:
                case VALORES_LIVRE:
                    String nomeCampoSemIndice = UtilSBCoreReflexaoCaminhoCampo.getListaSemColchete(pNomeOuANotacao);
                    return getmapaCamposInstanciados(nomeCampoSemIndice).get(nomeCampoSemIndice);

                case REGISTRO_ESTATICO_DA_LISTA:
                    int idIndiceCampo = UtilSBCoreReflexaoCaminhoCampo.getIdCampoDaLista(pNomeOuANotacao);
                    String nomeCampoSemIndice2 = UtilSBCoreReflexaoCaminhoCampo.getListaSemColchete(pNomeOuANotacao);
                    ItfCampoInstanciado cp = getmapaCamposInstanciados(nomeCampoSemIndice2).get(nomeCampoSemIndice2);

                    cp.setIndiceValorLista(idIndiceCampo);
                    return cp;
                default:
                    throw new AssertionError(tipoOrigem.name());

            }

        } else {

            String nomeProximoObjeto = UtilSBCoreReflexaoCaminhoCampo.getStrPrimeiroCampoDoCaminhoCampo(pNomeOuANotacao);

            TIPO_ORIGEM_VALOR_CAMPO tipo = UtilSBCoreReflexaoCaminhoCampo.getTipoCampoLista(nomeProximoObjeto);

            if (tipo.equals(TIPO_ORIGEM_VALOR_CAMPO.REGISTRO_ESTATICO_DA_LISTA)) {

                int idReflexao = UtilSBCoreReflexaoCaminhoCampo.getIdCampoDaLista(nomeProximoObjeto);
                String nomeCampoSemIndice = UtilSBCoreReflexaoCaminhoCampo.getListaSemColchete(nomeProximoObjeto);
                ItfCampoInstanciado lista = getmapaCamposInstanciados(nomeCampoSemIndice).get(nomeCampoSemIndice);
                ItfBeanSimples itemDaLista = (ItfBeanSimples) ((List) lista.getValor()).get(idReflexao);
                String nomeProximoCAmpo = UtilSBCoreReflexaoCaminhoCampo.getStrCaminhoCampoSemPrimeiroCampo(pNomeOuANotacao);
                return itemDaLista.getCampoByNomeOuAnotacao(nomeProximoCAmpo);

            } else {

                ItfCampoInstanciado itemAtual = getmapaCamposInstanciados(nomeProximoObjeto).get(nomeProximoObjeto);

                if (itemAtual == null) {
                    return UtilSBCoreReflexaoCaminhoCampo.CAMPO_NAO_IMPLEMENTADO;
                }

                ItemGenerico itemSuperior = (ItemGenerico) itemAtual.getValor();

                if (itemSuperior == null) {
                    return UtilSBCoreReflexaoCaminhoCampo.CAMPO_NAO_IMPLEMENTADO;
                }
                String nomeRestante = UtilSBCoreReflexaoCaminhoCampo.getStrCaminhoCampoSemPrimeiroCampo(pNomeOuANotacao);

                ItfCampoInstanciado proximoCampo = itemSuperior.getCampoByNomeOuAnotacao(nomeRestante);
                if (proximoCampo == null) {
                    throw new UnsupportedOperationException(nomeRestante + "o Caminhio nao foi encontrado em " + itemSuperior);
                }
                proximoCampo.setCampoInstanciadoRaiz(itemAtual);

                return proximoCampo;

            }
        }
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto pTipocampo) {
        return getCampoByNomeOuAnotacao(pTipocampo.toString());
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabTipoAtributoObjeto pInfoCampo) {
        return UtilSBCoreReflexaoCaminhoCampo.getSBCampobyTipoCampo(this.getClass(), pInfoCampo);
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        return UtilSBCoreReflexaoCaminhoCampo.getSBCampobyTipoCampo(this.getClass(), pCampo) != null;
    }

    @Override
    public String getNomeCampo(FabTipoAtributoObjeto pInfocampo) {
        try {
            Field campo = UtilSBCoreReflexaoCaminhoCampo.getSBCampobyTipoCampo(this.getClass(), pInfocampo);
            if (campo == null) {
                throw new UnsupportedOperationException("a anotação " + pInfocampo + " não foi encontrada em " + this.getClass().getSimpleName());
            }
            return campo.getName();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo nome do campo em" + this.getClass().getSimpleName() + "<b>Utilize isTem campo anotado para verificar se o item possui ou não este campo declarado </b>", t);
            return null;
        }
    }

    @Override
    @Deprecated
    public List<ItfCaminhoCampoInvalido> getCamposInvalidos() {

        throw new UnsupportedOperationException("A lista de campos instanciaados está sob análise, sem tempo previsto para retorno do desenvolivmento");

    }

    @Override
    @Deprecated
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {

        throw new UnsupportedOperationException("A lista de campos instanciaados invalidos está sob análise, sem tempo previsto para retorno do desenvolivmento");

    }

    /**
     *
     * Localiza o nomeCurto, retira acentos, espaços e caracteres especiais,
     * coloca tudo em maiusculo, e calcula o hash desta palavra
     *
     * Exemplo de utilização: Para integrar ids em sistemas diferentes
     *
     *
     * @return
     */
    @Override
    public int configIDPeloNome() {
        try {
            String nomeparaHash = (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.AAA_NOME);
            if (nomeparaHash == null) {
                throw new UnsupportedOperationException("Erro configurando id pelo nome, o campo que identifica" + FabTipoAtributoObjeto.AAA_NOME + " retornou nulo");
            }
            nomeparaHash = UtilSBCoreStringFiltros.removeCaracteresEspeciaisEspacosETracos(nomeparaHash);
            nomeparaHash = nomeparaHash.toUpperCase();
            int id = nomeparaHash.hashCode();
            setValorByTipoCampoEsperado(FabTipoAtributoObjeto.ID, id);
            return id;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando definir o ID para o item" + this.getClass().getName(), t);
            return 0;
        }
    }

    /**
     *
     *
     *
     * @return Todos os campos Instanciados do Objeto
     */
    @Deprecated
    public List<ItfCampoInstanciado> getTodosCamposInstanciados() {

        if (mapeouTodosOsCampos) {
            return Lists.newLinkedList(mapaCamposInstanciados.values());
        }

        for (Class classse : UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(classeModelo)) {
            for (Field campo : classse.getDeclaredFields()) {
                getCampoByNomeOuAnotacao(campo.getName());
            }
        }
        mapeouTodosOsCampos = true;
        return Lists.newLinkedList(mapaCamposInstanciados.values());
    }

    @Override
    public List<ItfCaminhoCampo> getEntidadesVinculadas() {

        return Lists.newArrayList(UtilSBCoreReflexaoCaminhoCampo.getCamposComSubCamposDaClasse(this.getClass()).values());

    }

    /**
     *
     * @param pCaminho
     * @return
     */
    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(ItfCaminhoCampo pCaminho
    ) {
        try {

            // to do substituir por acesso direto sem precisar instanciar o CampoInstanciado
            return (ItfBeanSimples) getCampoByNomeOuAnotacao(pCaminho.getCaminhoSemNomeClasse()).getValor();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter item por caminho do campo" + pCaminho, t);
        }
        return null;
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(ItfCaminhoCampo pCaminho
    ) {
        try {

            return (List) getCampoByNomeOuAnotacao(pCaminho.getCaminhoSemNomeClasse()).getValor();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter item por caminho do campo " + pCaminho, t);
        }
        return null;
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo
    ) {

        try {
            ItfBeanSimples ret = null;
            if (pNomeCampo == null) {
                throw new UnsupportedOperationException("String nula enviada para obter um bean simples por nome campo");
            }
            try {

                Field cp = getClass().getDeclaredField(pNomeCampo);
                cp.setAccessible(true);
                ret = (ItfBeanSimples) cp.get(this);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Campo: [" + pNomeCampo + "] não encontrado na classe : [" + getClass().getSimpleName() + "]", ex);
            }
            return ret;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter campo BenSimples pelo nome" + pNomeCampo, t
            );
        }
        return null;
    }

    @Override
    public String getNomeDoObjeto() {
        return UtilSBCoreReflexaoObjeto.getNomeDoObjetoPorAnotacaoInfoClasse(this.getClass());
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return UtilSBCoreReflexaoObjeto.getNomeObjetoPlural(this.getClass());
    }

    /**
     *
     * Adiciona um novo item na em uma lista da entidade ex:
     *
     * uma classe chamada EntidadeParanormal contendo private List<Pedidos>
     * meusPedidos;
     *
     * ao chamar adicionarItemNaLista("meusPedidos") um novo pedido é adicionado
     * na lista
     *
     * @param nomeDaLista
     */
    @Override
    public void adicionarItemNaLista(String nomeDaLista
    ) {
        nomeDaLista = nomeDaLista.replace("[]", "");
        try {
            Field campo = this.getClass().getDeclaredField(nomeDaLista);
            campo.setAccessible(true);
            List lista = (List) campo.get(this);
            Class classeTipo = UtilSBCoreReflexaoAtributoDeObjeto.getClasseGenerica(campo);

            lista.add(classeTipo.newInstance());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando registro em  lista:" + nomeDaLista, ex);
        }
    }

    public EstruturaDeEntidade getEstruturaDaEntidade() {
        return MapaObjetosProjetoAtual.getEstruturaObjeto(this.getClass());
    }

    public String getXhtmlVisualizacao() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

    public ItfResposta executarAcao(ItfAcaoController pAcao) {
        //Method metodo UtilSBCoreReflexao.getMetodoByAcao(pAcao);
        throw new UnsupportedOperationException("Ainda não implementado");
        //metodo.invoke(metodo.get, args)
    }

    @Override
    public void adicionarJustificativaExecucaoAcao(ItfAcaoDoSistema pAcao, String pJustificativa) {
        if (mapaJustificativasExecucaoAcao == null) {
            mapaJustificativasExecucaoAcao = new HashMap<>();
        }
        mapaJustificativasExecucaoAcao.put(pAcao.getNomeUnico(), pJustificativa);
    }

    @Override
    public String getJustificativa(ItfAcaoDoSistema pAcao) {
        if (mapaJustificativasExecucaoAcao == null) {
            return null;
        }
        return mapaJustificativasExecucaoAcao.get(pAcao.getNomeUnico());
    }

    protected String gerarSlug() {
        ItfBeanSimples bean = (ItfBeanSimples) this;
        return bean.getNome() + "-" + bean.getId();
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstanciados() {
        List<ItfCampoInstanciado> camposInstanciados = new ArrayList<>();
        for (EstruturaCampo estrutura : getEstruturaDaEntidade().getCampos()) {
            camposInstanciados.add(getCampoInstanciadoByNomeOuAnotacao(estrutura.getNomeDeclarado()));
        }
        return camposInstanciados;
    }

    public boolean isNovoBeanPreparado() {
        return novoBeanPreparado;
    }

    @Override
    public void adicionarSubItem(String pNomeCampo) {
        try {
            Class classe = this.getClass();
            Field campo = classe.getDeclaredField(pNomeCampo);
            campo.setAccessible(true);
            Class tipoItem = UtilSBCoreReflexao.getClasseGenericaDaClasseDoCampo(campo);
            ((List) campo.get(this)).add(tipoItem.newInstance());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando item em campo" + pNomeCampo, t);
        }

    }

    @Override
    public ItfAssistenteDeLocalizacao getAssistenteLocalizacao(ItfCampoInstanciado pCampoInstanciado, TipoOrganizacaoDadosEndereco pTipo) {
        if (mapaAssistenteLocalizacao == null) {
            return null;
        } else {

            return mapaAssistenteLocalizacao.get(pTipo.getIdentificacaoMapaAssisteBean(pCampoInstanciado));
        }
    }

    @Override
    public void adicionarAssitenteLocalizacao(ItfAssistenteDeLocalizacao pLocalizacao) {

        if (mapaAssistenteLocalizacao == null) {
            mapaAssistenteLocalizacao = new HashMap<>();
        }

        mapaAssistenteLocalizacao.put(pLocalizacao.getIdentificacaoMapa(), pLocalizacao);

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ItfBeanGenerico novoObjeto = this.getClass().newInstance();
            novoObjeto.copiaDados(this);
            return novoObjeto; //To change body of generated methods, choose Tools | Templates.
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro TEntando criar clone do Objeto", t);
            return null;
        }
    }

    @Override
    public int compareTo(ItfBeanSimples o) {

        try {
            return ((ItfBeanSimples) this).getNome().compareTo(o.getNome());
        } catch (Throwable t) {
            return -1;
        }

    }

    @Override
    public String toString() {
        try {
            ItfBeanSimplesSomenteLeitura itemSimples = (ItfBeanSimplesSomenteLeitura) this;
            if (this.getClass().getSimpleName().contains("$")) {
                return UtilSBCoreReflexaoObjeto.getClassExtraindoProxy(this.getClass().getSimpleName()).getSimpleName() + "_" + itemSimples.getId();
            } else {
                return this.getClass().getSimpleName() + "_" + itemSimples.getId();
            }

        } catch (Throwable t) {
            return super.toString(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    @Override
    public int hashCode() {
        try {
            return toString().hashCode();
        } catch (Throwable t) {
            return super.hashCode();
        }

    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        try {
            return obj.hashCode() == this.hashCode();
        } catch (Throwable t) {
            return super.equals(obj);
        }
    }

}
