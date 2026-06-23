/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Resposta"}, plural = "Resposta")
public class BotaoResposta extends EntidadeSimples implements ComoAcaoDoSistema {

    public final ComoTipoRespostaComunicacao tipoResposta;
    public final ComoAcaoDoSistema acaoVinculada;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private final String nomeDescricao;

    public BotaoResposta(ComoTipoRespostaComunicacao pTipoResposta, ComoAcaoDoSistema pAcaoVinculada) {
        tipoResposta = pTipoResposta;
        acaoVinculada = pAcaoVinculada;
        id = 0;
        nomeDescricao = tipoResposta.getNome();

    }

    public BotaoResposta() {
        tipoResposta = null;
        acaoVinculada = null;
        id = 0;
        nomeDescricao = null;
    }

    @Override
    public void setNomeAcao(String pNome) {

    }

    @Override
    public String getNomeAcao() {
        return tipoResposta.getNome();
    }

    @Override
    public String getIconeAcao() {
        return tipoResposta.getIcone();
    }

    @Override
    public String getCor() {
        return tipoResposta.getCor();
    }

    @Override
    public String getDescricao() {
        return tipoResposta.getNome();
    }

    @Override
    public void setDescricao(String pDescricao) {

    }

    @Override
    public boolean isPrecisaPermissao() {
        return false;
    }

    @Override
    public void setId(Long pId) {

    }

    @Override
    public void setIconeAcao(String pIcone) {

    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {

    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return acaoVinculada.getModulo();
    }

    @Override
    public boolean isConfigurado() {
        return acaoVinculada.isConfigurado();
    }

    @Override
    public String getNomeUnico() {
        return acaoVinculada.getNomeUnico();
    }

    @Override
    public String getNomeEnumOriginal() {
        return acaoVinculada.getNomeEnumOriginal();
    }

    @Override
    public FabTipoAcaoSistema getTipoAcaoSistema() {
        return acaoVinculada.getTipoAcaoSistema();
    }

    @Override
    public ComoFabricaAcoes getEnumAcaoDoSistema() {
        return acaoVinculada.getEnumAcaoDoSistema();
    }

    @Override
    public void configurarPropriedadesBasicas(ComoAcaoDoSistema pAcaoDoSistema) {

    }

    @Override
    public String getIdDescritivoJira() {
        return acaoVinculada.getIdDescritivoJira();
    }

    @Override
    public void setIdDescritivoJira(String pIdJira) {

    }

    @Override
    public void setPrecisaPermissao(boolean pPermissao) {

    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica() {
        return acaoVinculada.getTipoAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoFormulario() {
        return acaoVinculada.isUmaAcaoFormulario();
    }

    @Override
    public boolean isTemAcaoPrincipal() {
        return acaoVinculada.isTemAcaoPrincipal();
    }

    @Override
    public boolean isUmaAcaoGenerica() {
        return acaoVinculada.isUmaAcaoGenerica();
    }

    @Override
    public boolean isUmaAcaoGestaoDominio() {
        return acaoVinculada.isUmaAcaoGestaoDominio();
    }

    @Override
    public boolean isUmaAcaoSessaoMenu() {
        return acaoVinculada.isUmaAcaoSessaoMenu();
    }

    @Override
    public boolean isUmaAcaoDeEntidade() {
        return acaoVinculada.isUmaAcaoDeEntidade();
    }

    @Override
    public boolean isUmaAcaoController() {
        return acaoVinculada.isUmaAcaoController();
    }

    @Override
    public String getNomeDominio() {
        return acaoVinculada.getNomeDominio();
    }

    @Override
    public ItfAcaoFormulario getComoFormulario() {
        return acaoVinculada.getComoFormulario();
    }

    @Override
    public ItfAcaoFormularioEntidade getComoFormularioEntidade() {
        return acaoVinculada.getComoFormularioEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getComoGestaoEntidade() {
        return acaoVinculada.getComoGestaoEntidade();
    }

    @Override
    public ComoAcaoController getComoController() {
        return acaoVinculada.getComoController();
    }

    @Override
    public ComoAcaoSecundaria getComoSecundaria() {
        return acaoVinculada.getComoSecundaria();
    }

    @Override
    public ComoAcaoControllerEntidade getComoControllerEntidade() {
        return acaoVinculada.getComoControllerEntidade();
    }

    @Override
    public ItfAcaoGerenciarEntidade getAcaoDeGestaoEntidade() {
        return acaoVinculada.getAcaoDeGestaoEntidade();
    }

    @Override
    public boolean isAcaoTemModal() {
        return acaoVinculada.isAcaoTemModal();
    }

    @Override
    public String getNomeUnicoSlug() {
        return acaoVinculada.getNomeUnicoSlug();
    }

    @Override
    public boolean validar() {
        return acaoVinculada.validar();
    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return acaoVinculada.validarComMensagens();
    }

    @Override
    public String getSlugIdentificador() {
        return acaoVinculada.getSlugIdentificador();
    }

    @Override
    public String getNomeCurto() {
        return tipoResposta.getNomeCurto();
    }

    @Override
    public String getNome() {
        return acaoVinculada.getNome();
    }

    @Override
    public String getXhtmlVisao() {
        return acaoVinculada.getXhtmlVisao();
    }

    @Override
    public Long getId() {
        return acaoVinculada.getId();
    }

    @Override
    public String getImgPequena() {
        return tipoResposta.getImgPequena();
    }

    @Override
    public String getNomeDoObjeto() {
        return tipoResposta.getNomeDoObjeto();
    }

    @Override
    public void setNome(String pNome) {
        tipoResposta.setNome(pNome);
    }

    @Override
    public String getIcone() {
        return tipoResposta.getIcone();
    }

}
