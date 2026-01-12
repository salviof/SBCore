/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeNormal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.ComoTemIcone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalPostagem;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Usuários", tags = {"O Sistema"})
public class UsuarioAplicacaoEmExecucao extends EntidadeNormal implements ComoUsuario, ComoTemIcone, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @Override
    public String getEmail() {
        return "sistema@coletivojava.com.br";
    }

    @Override
    public String getSenha() {
        return null;
    }

    @Override
    public String getTipoUsuario() {
        return "APLICACAO";
    }

    @Override
    public ComoGrupoUsuario getGrupo() {
        return new GrupoUsuariosDoSistema();
    }

    @Override
    public void setGrupo(ComoGrupoUsuario grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComoGrupoUsuario> getGruposAdicionais() {
        return new ArrayList<>();
    }

    @Override
    public Date getDataCadastro() {
        return new Date();
    }

    @Override
    public String getApelido() {
        return SBCore.getGrupoProjeto() + "-" + SBCore.getNomeProjeto();
    }

    @Override
    public ComoLocalPostagem getLocalizacao() {
        return null;
    }

    @Override
    public String getComplemento() {
        return null;
    }

    @Override
    public void setComplemento(String pcomplemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void instanciarNovoEndereco() {

    }

    @Override
    public String getTelefone() {
        return null;
    }

    @Override
    public void setLocalizacao(ComoLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIcone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return super.getXhtmlVisaoMobile(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return super.getXhtmlVisao(numeroColunas); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getClassePontoIdentificador() {
        return super.getClassePontoIdentificador(); //To change body of generated methods, choose Tools | Templates.
    }

}
