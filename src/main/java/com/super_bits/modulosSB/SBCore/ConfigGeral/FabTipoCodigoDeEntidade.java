/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
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
                return MODULO_ERP;
            } else {
                return EXTENCAO_MODULO_ERP;
            }
        }
        return PROJETO_AUTONOMO;
    }

}
