/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidadorGoverno;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioCPF extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioCPF(ItfAtributoObjetoSB pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.MAXIMO);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        switch (campoInstanciado.getFabricaTipoAtributo()) {
            case CPF:
                if (!UtilSBCoreValidadorGoverno.validaCPF(pValor.toString())) {
                    return "CPF Inválido";
                } else {
                    return null;
                }
            default:
                return null;
        }

    }

}
