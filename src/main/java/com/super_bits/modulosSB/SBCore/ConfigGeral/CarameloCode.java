/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfServicoPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ItfServicoController;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.centralDados.ItfServicoRepositorioEntidades;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.CmoServicoLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoControleDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoAtributosDeObjetos;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 *
 *
 *
 *
 * @author salvio
 */
public class CarameloCode {

    public static boolean isAmbienteCoreConfigurado() {
        return SBCore.isAmbienteCoreConfigurado();
    }

    public static boolean isEmModoDesenvolvimento() {
        return SBCore.isEmModoDesenvolvimento();
    }

    public static boolean isEmModoProducao() {

        return SBCore.isEmModoProducao();
    }

    public static ItfConfiguracaoCoreSomenteLeitura getInfoAplicacao() {
        return SBCore.getInfoAplicacao();
    }

    public static ArquivoConfiguracaoDistribuicao getArquivoDistribuicao() {
        return SBCore.getArquivoDistribuicao();
    }

    public static boolean isControleDeAcessoDefinido() {
        return SBCore.isControleDeAcessoDefinido();

    }

    public static ItfServicoRepositorioEntidades getServicoRepositorioEntidade() {
        return SBCore.getServicoRepositorio();
    }

    /**
     *
     * @see ItfCentralDeArquivos
     *
     *
     *
     *
     * @return Manipulação de Arquivos de Entidade e do Sistema
     */
    public static ItfCentralDeArquivos getServicoArquivosDeEntidade() {
        return SBCore.getServicoArquivosDeEntidade();
    }

    /**
     *
     *
     *
     * @param configurador
     * @param pEstado
     *
     */
    public static void configurar(ItfConfiguradorCore configurador, SBCore.ESTADO_APP pEstado) {
        SBCore.configurar(configurador, pEstado);
    }

    public static SBCore.ESTADO_APP getEstadoAPP() {
        return SBCore.getEstadoAPP();
    }

    public static String getNomeProjeto() {

        return SBCore.getNomeProjeto();
    }

    public static String getDiretorioBase() {
        return SBCore.getDiretorioBase();
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErro(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {
        SBCore.RelatarErro(pTipoErro, pMensagem, pErroJava);
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErroAoUsuario(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {
        SBCore.RelatarErroAoUsuario(pTipoErro, pMensagem, pErroJava);
    }

    public static boolean isPermitido(ComoAcaoDoSistema pAcao) {
        return ControllerAppAbstratoSBCore.isAcessoPermitido(pAcao);
    }

    public static String getCaminhoGrupoProjetoSource() {
        return SBCore.getCaminhoGrupoProjetoSource();
    }

    public static String getCaminhoDesenvolvimento() {

        return SBCore.getCaminhoDesenvolvimento();

    }

    public static String getGrupoProjeto() {
        return SBCore.getGrupoProjeto();
    }

    /**
     *
     * Atalho para SBCore.getControleDeSessao.getsessaoAtual.getusuarioLogado;
     *
     * @return
     */
    public static ComoUsuario getUsuarioLogado() {
        return SBCore.getUsuarioLogado();
    }

    public static Class<? extends ComoFabricaAcoes>[] getFabricasDeAcaoDoSistema() {
        return SBCore.getFabricasDeAcaoDoSistema();
    }

    /**
     *
     * Retona um Objeto estatico apartir do nome da fabrica
     *
     *
     * -> Atenção, o prefixo Fab não é obrigatório
     *
     *
     * @param pNome Nome da fabrica ex: FabCompVisualBotaoAcao.ICONE_E_NOME
     * @return O objeto instanciado
     */
    public static Object getObjetoEstatico(String pNome) {
        return SBCore.getObjetoEstatico(pNome);

    }

    /**
     *
     * @return O classe loader onde a classe de configuração foi criada
     */
    public static ClassLoader getClasseLoaderAplicacao() {
        return SBCore.getClasseLoaderAplicacao();
    }

    public static synchronized ConfigModulo getConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        return SBCore.getConfigModulo(pFabricaConfig);

    }

    /**
     * @see CmoServicoLocalizacao
     * @return Helper framework CEP
     */
    public static CmoServicoLocalizacao getServicoLocalizacao() {
        return SBCore.getServicoLocalizacao();
    }

    /**
     *
     *
     *
     * @return
     */
    public static ItfCentralEventos getServicoLogEventos() {
        return SBCore.getServicoLogEventos();
    }

    /**
     *
     * @see ComoControleDeSessao
     * @return Controle de Sessão do contexto Atual de execução
     *
     */
    public static ComoControleDeSessao getServicoSessao() {
        return SBCore.getServicoSessao();
    }

    /**
     *
     * @see ComoServicoComunicacao
     *
     * @return Controle de comunicação, entre Sistema, Usuário e Desenvolvedor
     */
    public static ComoServicoComunicacao getServicoComunicacao() {
        return SBCore.getServicoComunicacao();
    }

    /**
     *
     * @see ComoServicoAtributosDeObjetos
     *
     * @return Helper para exibição de opções possiveis para determinado
     * atributo de objeto
     */
    public static ComoServicoAtributosDeObjetos getServicoFonteDeDadosParaAtributos() {
        return SBCore.getServicoFonteDeDadosParaAtributos();
    }

    /**
     *
     * @see ItfCentralPermissoes
     *
     * @return Asistente para Controle de Acesso as ações do sistema
     */
    public static ItfServicoPermissao getServicoPermissao() {
        return SBCore.getServicoPermissao();
    }

    public static FabTipoProjeto getTipoProjeto() {
        return SBCore.getTipoProjeto();
    }

    public static ItfServicoController getServicoController() {
        return SBCore.getServicoController();
    }

    public static <T> T getServicoERP(ItfApiErpSuperBits pTipoERP) {
        return (T) pTipoERP.getImplementacaoDoContexto();
    }

}
