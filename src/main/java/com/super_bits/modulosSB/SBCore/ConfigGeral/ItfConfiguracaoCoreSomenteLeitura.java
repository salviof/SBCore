/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

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
 * Configurções genericas da Aplicação, como: nome, Status controle de usuários,
 * Controle de mensagem, e outros
 *
 *
 * @author Salvio
 */
public interface ItfConfiguracaoCoreSomenteLeitura {

    public String getNomeSocial();

    public Class<? extends ItfCentralMensagens> getCentralDeMensagens();

    /**
     * Classe responsável pelo tratamento de erros, e logs de sistema
     *
     * @return Classe responsável pelo tratamento de erros, e logs de sistema
     */
    public Class<? extends InfoErroSBComAcoes> getClasseErro();

    /**
     *
     * Classe responsável pelo controle de sessões e autenticação
     *
     * @return Classe responsável pelo controle de sessões e autenticação
     */
    public Class<? extends ComoControleDeSessao> getControleDeSessao();

    /**
     * Classe responsável por configurar as permissões de acesso do sistema
     *
     * @return Classe responsável por configurar as permissões de acesso do
     * sistema
     */
    public Class<? extends ConfigPermissaoSBCoreAbstrato> getConfigPermissoes();

    /**
     * Classe responsável por registrar os logs de eventos do sistema.
     *
     * @return
     */
    public Class<? extends ItfCentralEventos> getCentralDeEventos();

    public Class<? extends ItfServicoController> getServicoController();

    /**
     *
     * Nome do Projeto (utilizado em pastas locais de desenvolvimento e
     * repositórios)
     *
     * @return Nome do Projeto (utilizado em pastas locais de desenvolvimento e
     * repositórios)
     */
    public String getNomeProjeto();

    /**
     * Status do desenvolvimento que pode ser DESENVOLVIMENTO, PRODUCAO ou
     * TESTES
     *
     * @return Status do desenvolvimento que pode ser DESENVOLVIMENTO, PRODUCAO
     * ou TESTES
     */
    public SBCore.ESTADO_APP getEstadoApp();

    /**
     * Diretorio onde o arquivo Jar ou War está localizado
     *
     * @return
     */
    public String getDiretorioBase();

    /**
     * Nome Simples do cliente (utilizado em pastas locais, e repositórios)
     *
     * @return Nome Simples do cliente (utilizado em pastas locais, e
     * repositórios)
     */
    public String getCliente();

    /**
     * Nome do grupo, caso o projeto seja parte de um grupo (utilizado em pastas
     * locais, e repositórios)
     *
     * @return Nome do grupo, caso o projeto seja parte de um grupo (utilizado
     * em pastas locais, e repositórios)
     */
    public String getGrupoProjeto();

    /**
     *
     * Defina as fabricas de ação do sistema
     *
     * @return
     */
    public Class<? extends ComoFabricaAcoes>[] getFabricaDeAcoes();

    /**
     *
     * @return
     */
    public String getUrlJira();

    /**
     *
     * @return
     */
    public Class<? extends ItfServicoVisualizacao> getServicoVisualizacao();

    /**
     *
     * @return
     */
    public Class<? extends ItfCentralDeArquivos> getCentralDeArquivo();

    public Class<? extends ComoServicoComunicacao> getCentralComunicacao();

    public Class<? extends CmoServicoLocalizacao> getCentralDeLocalizacao();

    public Class<? extends ComoServicoAtributosDeObjetos> getCentralAtributoDados();

    public ItfCentralAdministrativa getCentralAdministrativa();

    public Class<? extends ItfServicoRepositorioEntidades> getCentralDados();

    public FabTipoProjeto getTipoProjeto();
}
