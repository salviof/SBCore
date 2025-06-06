/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfMenusDeSessao;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;

/**
 *
 * @author Salvio
 */
public class SessaoOffline implements ItfSessao {

    protected String idSessao;
    private ItfUsuario usuarioLogado;
    private final Date dataInicial;
    private Date dataFinal;
    private final List<ItfPermissao> acoesRealizadas;
    private final Map<String, ItfAcaoDoSistema> acoesPermitidas;
    private final Map<String, ItfAcaoDoSistema> acoesNegadas;

    protected String pastaTempDeSessao;

    private ItfMenusDeSessao menusDaSessao;

    @Override
    public ItfMenusDeSessao getMenusDaSessao() {
        return menusDaSessao;
    }

    public void setMenusDaSessao(ItfMenusDeSessao menusDaSessao) {
        this.menusDaSessao = menusDaSessao;
    }

    @Override
    public String getPastaTempDeSessao() {
        String caminho = SBCore.getCaminhoGrupoProjetoSource() + "/modelRegras/target/" + String.valueOf(SBCore.getControleDeSessao().getSessaoAtual().getUsuario().getEmail().hashCode());

        File pastaTemo = new File(caminho + "/");
        pastaTemo.mkdirs();

        return caminho;
    }

    public SessaoOffline() {
        this.acoesRealizadas = new ArrayList();
        this.usuarioLogado = new UsuarioAnonimo();
        this.dataInicial = new Date();
        acoesPermitidas = new HashMap<>();
        acoesNegadas = new HashMap<>();

    }

    @Override
    public ItfUsuario getUsuario() {

        return usuarioLogado;
    }

    @Override
    public Date getInicio() {
        return dataInicial;
    }

    @Override
    public Date getFim() {
        return dataFinal;
    }

    @Override
    public List<ItfPermissao> getAcoesRealizadas() {
        return acoesRealizadas;
    }

    @Override
    public void setUsuario(ItfUsuario pUsuario) {

        usuarioLogado = pUsuario;
        if (SBCore.getCentralPermissao() != null) {
            menusDaSessao = (MenusDaSessao) SBCore.getCentralPermissao().definirMenu(pUsuario.getGrupo());
        }

    }

    @Override
    public boolean isIdentificado() {
        if (usuarioLogado == null || usuarioLogado.equals(new UsuarioAnonimo())) {

            return false;
        }
        if (usuarioLogado.getId() == null || usuarioLogado.getId() == null) {

            return false;
        }
        return true;
    }

    @Override
    public ItfTelaUsuario getTelaUsuario() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoViewDefinido() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encerrarSessao() {
        pastaTempDeSessao = null;
        setUsuario(new UsuarioAnonimo());
        dataFinal = new Date();
    }

    @Override
    public boolean isAcessoPermitido(ItfAcaoDoSistema pAcao) {

        if (acoesPermitidas.containsKey(pAcao.getNomeUnico())) {
            return true;
        }

        if (acoesNegadas.containsKey(pAcao.getNomeUnico())) {
            return false;
        }

        if (ControllerAppAbstratoSBCore.isAcessoPermitido(pAcao)) {
            acoesPermitidas.put(pAcao.getNomeUnico(), pAcao);
            return true;
        } else {

            acoesNegadas.put(pAcao.getNomeUnico(), pAcao);
            return false;
        }

    }

    public List<ItfDialogo> getComunicacaoAguardandoRespostaUsuarioSesso() {
        List<ItfDialogo> comunicacoesEncontradas = SBCore.getServicoComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(getUsuario());
        return comunicacoesEncontradas;
    }

    public List<ItfDialogo> getUltimasComuniccoesAguardandoRespostaUsuarioSesso() {

        List<ItfDialogo> lista = new ArrayList();
        SBCore.getServicoComunicacao().getComunicacoesAguardandoRespostaDoDestinatario(getUsuario())
                .stream().limit(6).forEach(lista::add);
        return lista;
    }

    @Override
    public String getIdSessao() {
        if (idSessao == null) {
            idSessao = String.valueOf(Thread.currentThread().toString().hashCode());
        }
        return idSessao;
    }

}
