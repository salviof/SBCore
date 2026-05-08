/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;

/**
 *
 * @author salvio
 */
public enum FabTipoCodigoDeEntidade {

    EXTENCAO_MODULO_ERP,
    PROJETO_AUTONOMO,
    MODULO_ERP;

    public static FabTipoCodigoDeEntidade getTipoProjeto(ItfEstruturaDeEntidade pEstrutura) {
        if (pEstrutura.isUmaEntidadeModuloERP()) {
            if (CarameloCode.isProjetoModuloERP()) {
                if (!CarameloCode.getGroupIDMaven().toLowerCase().contains(pEstrutura.getModuloERP().toLowerCase())) {
                    // Extenção usando outra extenção.
                    return EXTENCAO_MODULO_ERP;
                }

                return MODULO_ERP;
            } else {
                return EXTENCAO_MODULO_ERP;
            }
        }
        return PROJETO_AUTONOMO;
    }

    public boolean isTipoCodigoERP(ItfEstruturaDeEntidade pEstrutura) {
        FabTipoCodigoDeEntidade tipo = getTipoProjeto(pEstrutura);
        switch (tipo) {
            case EXTENCAO_MODULO_ERP:
                return false;

            case PROJETO_AUTONOMO:
                return false;

            case MODULO_ERP:
                return true;

            default:
                throw new AssertionError();
        }
    }

}
