/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfEstruturaDeFormuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class ServicoVisualizacaoSemVisualizacao extends ServicoVisualizacaoAbstrato {

    public ServicoVisualizacaoSemVisualizacao() {
        super(TIPOS_INTERFACES_COMUM_VISUALIZACAO.DESKTOP_SWING);
    }

    @Override
    public String getCaminhoXhtmlAcaoDoSistema(ItfAcaoFormulario pAcao) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicoVisualizacaoAbstrato.TIPOS_INTERFACES_COMUM_VISUALIZACAO getTipoVisualizacao() {
        return TIPOS_INTERFACES_COMUM_VISUALIZACAO.DESKTOP_SWING;
    }

    @Override
    public String getCaminhoXhtmlItemCard(Class pEntidade) {

        return "/resources/visualizacao/" + pEntidade + "/" + pEntidade.getSimpleName() + ".xhtml";
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return "/naoimplementado";
    }

    @Override
    public String getRemotoPastaResource() {
        return "http://sistemanaoImplementado.superbits.org";
    }

    @Override
    public String getCaminhoXhtmlItemCardLab(Class pEntidade) {
        return "/naoimplementado";
    }

    @Override
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String nomeAlternativo) {
        return "/naoimplementado";
    }

    @Override
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String nomeAlternativo) {
        return "/naoimplementado";
    }

    @Override
    public List<String> getTodasVisualizacoes(Class pEntidade) {
        return new ArrayList<>();
    }

    @Override
    public String getCaminhoPastaContainerLaboratorio(Class pEntidade) {
        return "/naoimplementado";
    }

    @Override
    public String getCaminhoPastaContainerPublicado(Class pEntidade) {
        return "/naoimplementado";
    }

    @Override
    public String buildCaminhoRelativoItemSimples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildCaminhoRelativoItemSimplesNulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildCaminhoRelativoItemNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildCaminhoRelativoItemNulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrLocalArquivoReferenciaNovoComponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndrRemotoFormulario(ItfFabricaAcoes pAcao, Object... paramentros) {
        return pAcao.getNomeUnico();
    }

    @Override
    public String getEndrRemotoFormulario(String pAcaoNomeUnico, Object... paramentros) {
        return getEndrRemotoFormulario(MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(pAcaoNomeUnico).getEnumAcaoDoSistema(), paramentros);
    }

    @Override
    public String getEndrRemotoFormularioTokenAcesso(ItfTokenAcessoDinamico token) {
        return getEndrRemotoFormulario("FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB", token);
    }

    @Override
    public String getCaminhoWebAppDeveloper() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItfEstruturaDeFormuario getEstruturaFormulario(ItfAcaoDoSistema pAcaop) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
