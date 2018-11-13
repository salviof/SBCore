/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;

/**
 *
 * @author Salvio
 */
public interface ItfControleDeSessao {

    public abstract ItfSessao getSessaoAtual();

    public abstract void efetuarLogIn();

    public abstract void efetuarLogOut();

    public abstract void registrarAcao(ItfPermissao pAcesso);

    public abstract void logarEmailESenha(String usuario, String senha);

    public abstract void logarComoRoot();

    public abstract void logarComoAnonimo();

}
