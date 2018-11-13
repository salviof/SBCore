/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import java.io.Serializable;

/**
 *
 * @author SalvioF
 */
public class CampoInstanciadoVerdadeiroOuFalso implements Serializable {

    private final ItfCampoInstanciado campoInstanciado;

    public CampoInstanciadoVerdadeiroOuFalso(ItfCampoInstanciado campoInstanciado) {
        this.campoInstanciado = campoInstanciado;
    }

    public String getTextoPositivo() {
        return campoInstanciado.getPropriedadesRefexao().getAtributoGerado().getTextoPositivo();
    }

    public String getTextoNegativo() {
        return campoInstanciado.getPropriedadesRefexao().getAtributoGerado().getTextoPositivo();
    }

    public String getIconePositivo() {
        return campoInstanciado.getPropriedadesRefexao().getAtributoGerado().getIconePositivo();
    }

    public String getIconeNegativo() {
        return campoInstanciado.getPropriedadesRefexao().getAtributoGerado().getIconeNegativo();
    }

}
