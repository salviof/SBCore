/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ItfServicoController;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.admin.ItfCentralAdministrativa;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.view.ItfServicoVisualizacao;
import com.super_bits.modulosSB.SBCore.modulos.centralDados.ItfServicoRepositorioEntidades;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoControleDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoAtributosDeObjetos;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.CmoServicoLocalizacao;

/**
 *
 * Classe para criação dinimica de configuradores
 *
 *
 * @author cristopher
 */
public class ConfigCoreCustomizavel implements ItfConfiguracaoCoreCustomizavel {

    private String nomeSocial;
    private Class<? extends ItfCentralMensagens> centralMEnsagens;
    private Class<? extends InfoErroSBComAcoes> classeErro;
    private Class<? extends ComoControleDeSessao> controleDeSessao;
    private Class<? extends ItfCentralEventos> centralDeEventos;
    private Class<? extends ConfigPermissaoSBCoreAbstrato> classeConfigPermissao;
    private Class<? extends ItfServicoVisualizacao> classeVisualizacao;
    private Class<? extends ItfCentralDeArquivos> classeCentralDeArquivos;
    private Class<? extends ComoServicoComunicacao> classeCentralComunicacao;
    private Class<? extends CmoServicoLocalizacao> classeCentralLocalizacao;
    private Class<? extends ComoServicoAtributosDeObjetos> classeCentralAtributos;
    private Class<? extends ItfServicoRepositorioEntidades> classeCentralDados;
    private Class<? extends ItfServicoController> classeServicoController;
    private ItfCentralAdministrativa centralAdministrativa;
    private FabTipoProjeto tipoProjeto;

    private String nomeProjeto;
    private SBCore.ESTADO_APP estadoAPP;
    private String cliente;
    private String grupoProjeto;
    private String diretorioBase = "";
    private Class<? extends ComoFabricaAcoes>[] acoesDoSistema;
    private String urlJira;

    @Override
    public Class<? extends ConfigPermissaoSBCoreAbstrato> getConfigPermissoes() {
        return classeConfigPermissao;
    }

    @Override
    public Class<? extends ComoFabricaAcoes>[] getFabricaDeAcoes() {
        return acoesDoSistema;
    }

    @Override
    public String getUrlJira() {
        return urlJira;
    }

    public ConfigCoreCustomizavel() {
    }

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {
        return centralMEnsagens;
    }

    @Override
    public Class<? extends InfoErroSBComAcoes> getClasseErro() {
        return classeErro;
    }

    @Override
    public Class<? extends ComoControleDeSessao> getControleDeSessao() {
        return controleDeSessao;
    }

    @Override
    public Class<? extends ItfCentralEventos> getCentralDeEventos() {
        return centralDeEventos;
    }

    @Override
    public String getNomeProjeto() {
        return nomeProjeto;

    }

    @Override
    public SBCore.ESTADO_APP getEstadoApp() {
        return estadoAPP;
    }

    @Override
    public String getDiretorioBase() {
        return diretorioBase;

    }

    @Override
    public String getCliente() {
        return cliente;
    }

    @Override
    public String getGrupoProjeto() {
        return grupoProjeto;
    }

    @Override
    public final void setCentralMEnsagens(Class<? extends ItfCentralMensagens> centralMEnsagens) {
        this.centralMEnsagens = centralMEnsagens;

    }

    @Override
    public final void setClasseErro(Class<? extends InfoErroSBComAcoes> classeErro) {
        this.classeErro = classeErro;

    }

    /**
     *
     * @param controleDeSessao
     * @return
     */
    @Override
    public final void setControleDeSessao(Class<? extends ComoControleDeSessao> controleDeSessao) {

        if (this.controleDeSessao == null) {
            this.controleDeSessao = controleDeSessao;
        } else {
            System.out.println("Ouve uma tentatidade de configurar o controle de sessão duas vezes!!!! ");
        }

    }

    @Override
    public final void setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos) {
        this.centralDeEventos = centralDeEventos;

    }

    /**
     *
     * O nome do projeto identifica a pasta onde o projeto se encontra
     *
     * @param pnomeProjeto
     * @return
     */
    @Override
    public final void setNomeProjeto(String pnomeProjeto) {
        if (UtilCRCStringValidador.isNAO_NuloNemBranco(nomeProjeto)) {
            System.out.println("O nomr do Projeto já foi setado como " + nomeProjeto + " a mudança para [" + pnomeProjeto + "] não foi realizada");
        } else {
            this.nomeProjeto = pnomeProjeto;
        }
    }

    @Override
    public final void setEstadoAPP(SBCore.ESTADO_APP estadoAPP) {
        this.estadoAPP = estadoAPP;

    }

    @Override
    public final void setCliente(String pCliente) {

        if (UtilCRCStringValidador.isNAO_NuloNemBranco(cliente)) {
            System.out.println("O cliente já foi setado como " + cliente + " a mudança para [" + pCliente + "] não foi realizada");
        } else {
            this.cliente = pCliente;
        }

    }

    @Override
    public final void setGrupoProjeto(String pGrupoProjeto) {

        if (UtilCRCStringValidador.isNAO_NuloNemBranco(grupoProjeto)) {
            System.out.println("O grupo projeto já foi setado como " + grupoProjeto + " a mudança para [" + pGrupoProjeto + "] não foi realizada");
        } else {
            this.grupoProjeto = pGrupoProjeto;

        }
    }

    /**
     *
     * O diretorio base é o diretorio que pode existir logo depois da pasta
     * source, agrupando diversos projetos
     *
     * @param pDiretorioBase
     * @return
     */
    @Override
    public final void setDiretorioBase(String pDiretorioBase) {

        if (UtilCRCStringValidador.isNAO_NuloNemBranco(this.diretorioBase)) {
            System.out.println("O Diretorio base já foi setado como" + diretorioBase + " o diretório NAO foi alterado para" + pDiretorioBase);
        } else {
            this.diretorioBase = pDiretorioBase;
        }

    }

    @Override
    public final void setClasseConfigPermissao(Class<? extends ConfigPermissaoSBCoreAbstrato> pConfigPermissao
    ) {

        classeConfigPermissao = pConfigPermissao;

    }

    @Override
    public final void setFabricaDeAcoes(Class<? extends ComoFabricaAcoes>[] pAcoes) {
        acoesDoSistema = pAcoes;

    }

    @Override
    public final void setUrlJira(String urlJira) {

        this.urlJira = urlJira;

    }

    @Override
    public String getNomeSocial() {
        return nomeSocial;
    }

    @Override
    public void setNomeSocial(String nomeSocial
    ) {
        this.nomeSocial = nomeSocial;
    }

    @Override
    public Class<? extends ItfServicoVisualizacao> getServicoVisualizacao() {
        return classeVisualizacao;
    }

    @Override
    public void setServicoVisualizacao(Class<? extends ItfServicoVisualizacao> classeVisualizacao) {
        this.classeVisualizacao = classeVisualizacao;
    }

    @Override
    public void setCentralDeArquivos(Class<? extends ItfCentralDeArquivos> centralDeArquivos) {
        classeCentralDeArquivos = centralDeArquivos;
    }

    @Override
    public Class<? extends ItfCentralDeArquivos> getCentralDeArquivo() {
        return classeCentralDeArquivos;
    }

    @Override
    public void setCentralComunicacao(Class<? extends ComoServicoComunicacao> pCentral) {
        classeCentralComunicacao = pCentral;
    }

    @Override
    public Class<? extends ComoServicoComunicacao> getCentralComunicacao() {
        return classeCentralComunicacao;
    }

    @Override
    public void setCentralDeLocalizacao(Class<? extends CmoServicoLocalizacao> pCentralLocalizacao) {
        classeCentralLocalizacao = pCentralLocalizacao;
    }

    @Override
    public Class<? extends CmoServicoLocalizacao> getCentralDeLocalizacao() {
        return classeCentralLocalizacao;
    }

    @Override
    public Class<? extends ComoServicoAtributosDeObjetos> getCentralAtributoDados() {
        return classeCentralAtributos;
    }

    @Override
    public void setCentralAtributoDados(Class<? extends ComoServicoAtributosDeObjetos> pCentralAtributos) {
        classeCentralAtributos = pCentralAtributos;
    }

    @Override
    public void setCentralAdmin(ItfCentralAdministrativa centralAdmin) {
        centralAdministrativa = centralAdmin;
    }

    @Override
    public ItfCentralAdministrativa getCentralAdministrativa() {
        return centralAdministrativa;
    }

    @Override
    public void setCentralDados(Class<? extends ItfServicoRepositorioEntidades> pCentral) {
        classeCentralDados = pCentral;
    }

    @Override
    public Class<? extends ItfServicoRepositorioEntidades> getCentralDados() {
        return classeCentralDados;
    }

    @Override
    public FabTipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    @Override
    public void setTipoProjeto(FabTipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    @Override
    public void setServicoController(Class<? extends ItfServicoController> pClasseServicoController) {
        classeServicoController = pClasseServicoController;
    }

    @Override
    public Class<? extends ItfServicoController> getServicoController() {
        return classeServicoController;
    }

}
