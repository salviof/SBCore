/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.GrupoUsuariosDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author SalvioF
 */
public class PermissaoSBTransient extends ItemSimples implements ItfPermissao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomePermissao;
    private final ComoAcaoDoSistema acao;

    public PermissaoSBTransient(ComoAcaoDoSistema pAcao) {
        acao = pAcao;
        nomePermissao = pAcao.getNomeUnico();
        id = (long) pAcao.getNomeUnico().hashCode();
    }

    @Override
    public ComoAcaoDoSistema getAcao() {
        return acao;
    }

    @Override
    public String getDescricaoAcesso() {
        return "Permissão para: " + acao.getNomeAcao();
    }

    @Override
    public List<ComoGrupoUsuario> getGruposPermitidos() {
        return Lists.newArrayList(new GrupoUsuariosDoSistema(new UsuarioSistemaRoot()));

    }

    @Override
    public List<ComoGrupoUsuario> getGruposNegados() {
        return new ArrayList<>();
    }

    @Override
    public List<ComoGrupoUsuario> getGruposDisponiveis() {
        return new ArrayList<>();
    }

    @Override
    public List<ComoUsuario> getUsuariosPermitidos() {
        return new ArrayList<>();
    }

    @Override
    public List<ComoUsuario> getUsuariosNegados() {
        return new ArrayList<>();
    }

    @Override
    public List<ComoUsuario> getUsuariosDisponiveis() {
        return new ArrayList<>();
    }

    @Override
    public TIPO_AUTENTICACAO getTipoAutenticacao() {
        return TIPO_AUTENTICACAO.SOLICITAR_EMAIL;
    }

    @Override
    public String getNomeCurto() {
        return "Controle de acesso simples, para sistemas simples";
    }

    @Override
    public String getNome() {
        return "Controle de acesso simples, para sistemas simples";
    }

    @Override
    public String getIconeDaClasse() {
        return "Controle de acesso simples, para sistemas simples";
    }

}
