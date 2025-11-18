/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfTelaUsuario;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenusDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author Salvio
 */
public class SessaoOffline implements ComoSessao {

    protected String idSessao;
    private ComoUsuario usuarioLogado;
    private final Date dataInicial;
    private Date dataFinal;
    private final List<ItfPermissao> acoesRealizadas;
    private final Map<String, ComoAcaoDoSistema> acoesPermitidas;
    private final Map<String, ComoAcaoDoSistema> acoesNegadas;

    protected String pastaTempDeSessao;

    private ComoMenusDeSessao menusDaSessao;

    @Override
    public ComoMenusDeSessao getMenusDaSessao() {
        return menusDaSessao;
    }

    public void setMenusDaSessao(ComoMenusDeSessao menusDaSessao) {
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
    public ComoUsuario getUsuario() {

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
    public void setUsuario(ComoUsuario pUsuario) {

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
    public boolean isAcessoPermitido(ComoAcaoDoSistema pAcao) {

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
