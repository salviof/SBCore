/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;

/**
 *
 * @author SalvioF
 */
public interface ItfServidorEmail extends ComoEntidadeSimples {

    @Override
    public default Long getId() {
        return (long) getEmail().hashCode();
    }

    @Override
    public default String getNome() {
        return getEmail();
    }

    public String getEnderecoServidor();

    public String getEmail();

    public String getSenha();

}
