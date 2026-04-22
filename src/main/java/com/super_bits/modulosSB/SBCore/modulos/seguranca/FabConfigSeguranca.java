/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.seguranca;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador;

/**
 *
 * @author salvio
 */
public enum FabConfigSeguranca implements ItfFabConfigModulo {

    USUARIO_ADMIN,
    SENHA,
    CHAVE_CRIPTO;

    @Override
    public String getValorPadrao() {
        switch (this) {

            case USUARIO_ADMIN:
                return "root@caramelocode.com.br";

            case SENHA:
                return UtilCRCStringGerador.getStringRandomicaUUID();

            case CHAVE_CRIPTO:
                return UtilCRCStringGerador.getStringRandomicaTokenAleatorio(256);

            default:
                throw new AssertionError();
        }

    }

}
