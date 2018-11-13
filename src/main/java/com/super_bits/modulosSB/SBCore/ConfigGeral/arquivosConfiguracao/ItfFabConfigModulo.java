/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;

/**
 *
 * @author SalvioF
 */
public interface ItfFabConfigModulo {

    public String getValorPadrao();

    public default String getNomePropriedade() {

        return UtilSBCoreStringsCammelCase.getCammelByTexto(this.toString());
    }

    public default String getNomeModulo() {
        return this.getClass().getSimpleName();
    }

}
