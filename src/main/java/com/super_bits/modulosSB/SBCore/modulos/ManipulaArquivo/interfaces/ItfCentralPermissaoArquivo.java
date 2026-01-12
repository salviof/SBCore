/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstArquivoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author desenvolvedor
 */
public interface ItfCentralPermissaoArquivo {

    public boolean isUsuarioPodeAlterar(ComoUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioPodeBaixar(ComoUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioPodeCriar(ComoUsuario pUsuario, ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeAlterar(ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeBaixar(ItfCampoInstArquivoEntidade pArquivo);

    public boolean isUsuarioLogadoPodeCriar(ItfCampoInstArquivoEntidade pArquivo);

}
