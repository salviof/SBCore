/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemNormal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocalPostagem;

/**
 *
 * Usuário anonimo
 *
 *
 *
 * @author Salvio
 */
@InfoObjetoSB(tags = {"Usuário Anonimo"}, plural = "Usuários Anônimos")
public class UsuarioAnonimo extends ItemNormal implements ComoUsuario, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome = "Anônimo ";

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAALTERACAO)
    private Date dataHoraAlteracao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO)
    private Date dataHoraInsersao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO)
    private ComoUsuario usuarioInsercao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_ALTERACAO)
    private ComoUsuario usuarioAlteracao;

    public UsuarioAnonimo() {
        super();
    }

    @Override
    public String getEmail() {
        return "suporte@casanovadigital.com.br";
    }

    @Override
    public String getSenha() {
        return "";
    }

    public String getComplemento() {
        return "";
    }

    @Override
    public String getTelefone() {
        return "";
    }

    @Override
    public String getNomeLongo() {
        return "Usuário não Identificado";
    }

    @Override
    public String getDescritivo() {
        return "usuário anoniomo, não identificado no sistema";
    }

    @Override
    public List<String> getGaleria() {
        return new ArrayList<>();
    }

    @Override
    public String getNomeCurto() {
        return "Anônimo";
    }

    @Override
    public Long getId() {
        return (long) getNome().hashCode();
    }

    @Override
    public String getNome() {
        return "Anônimo";
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }

    @Override
    public ComoGrupoUsuario getGrupo() {
        return new GrupoUsuariosDoSistema();

    }

    @Override
    public void setGrupo(ComoGrupoUsuario grupo) {
        System.out.println("Este usuário é estático e não pode ser manipulado");
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
        return "Anonymous";
    }

    @Override
    public boolean isAtivo() {
        return true;
    }

    @Override
    public String getTipoUsuario() {
        return "UsuarioDoSistema";
    }

    @Override
    public void setNome(String pNome) {
        System.out.println("ESTE NOME É SOMENTE LEITURA (USUARIO ANONIMO)");
    }

    @Override
    public ComoLocalPostagem getLocalizacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setComplemento(String pcomplemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocalizacao(ComoLocal localizacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void instanciarNovoEndereco() {

    }

}
