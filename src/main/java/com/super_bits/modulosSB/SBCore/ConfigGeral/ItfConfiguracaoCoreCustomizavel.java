/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ItfServicoController;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
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
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ComoServicoMensagemFireAndForget;

/**
 *
 * @author salvioF
 */
public interface ItfConfiguracaoCoreCustomizavel extends ItfConfiguracaoCoreSomenteLeitura {

    public void setCentralMEnsagens(Class<? extends ComoServicoMensagemFireAndForget> centralMEnsagens);

    public void setClasseErro(Class<? extends InfoErroSBComAcoes> classeErro);

    public void setControleDeSessao(Class<? extends ComoControleDeSessao> controleDeSessao);

    public void setCentralAdmin(ItfCentralAdministrativa centralAdmin);

    public void setCentralDeEventos(Class<? extends ItfCentralEventos> centralDeEventos);

    public void setCentralDeArquivos(Class<? extends ItfCentralDeArquivos> centralDeArquivos);

    public void setServicoController(Class<? extends ItfServicoController> pServicoController);

    /**
     *
     * O nome do projeto identifica a pasta onde o projeto se encontra
     *
     * @param nomeProjeto
     * @return
     */
    public void setNomeProjeto(String nomeProjeto);

    public void setEstadoAPP(SBCore.ESTADO_APP estadoAPP);

    public void setCliente(String cliente);

    public void setGrupoProjeto(String grupoProjeto);

    /**
     *
     * O diretorio base é o diretorio que pode existir logo depois da pasta
     * source, agrupando diversos projetos
     *
     * @param diretorioBase
     * @return
     */
    public void setDiretorioBase(String diretorioBase);

    public void setClasseConfigPermissao(Class<? extends ConfigPermissaoSBCoreAbstrato> pConfigPermissao);

    public void setFabricaDeAcoes(Class<? extends ComoFabricaAcoes>[] pAcoes);

    public void setUrlJira(String urlJira);

    public void setNomeSocial(String pNomeSocial);

    public void setServicoVisualizacao(Class<? extends ItfServicoVisualizacao> classeVisualizacao);

    public void setCentralComunicacao(Class<? extends ComoServicoComunicacao> pCentral);

    public void setCentralDeLocalizacao(Class<? extends CmoServicoLocalizacao> pCentralLocalizacao);

    public void setCentralAtributoDados(Class<? extends ComoServicoAtributosDeObjetos> pCentralAtributos);

    public void setCentralDados(Class<? extends ItfServicoRepositorioEntidades> pCentral);

    public void setTipoProjeto(FabTipoProjeto pTipoProjeto);

    public void setGrupoIdMaven(String pGrupo);

}
