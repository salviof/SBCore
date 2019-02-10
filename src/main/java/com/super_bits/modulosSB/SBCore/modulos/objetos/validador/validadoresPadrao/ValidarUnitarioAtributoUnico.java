/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public class ValidarUnitarioAtributoUnico extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidarUnitarioAtributoUnico(ItfAtributoObjetoSB pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.MAXIMO);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {

        if (!(campoInstanciado instanceof ItfCampoInstanciado)) {
            return null;
        }
        if (!campoInstanciado.isValorCampoUnico()) {
            return null;
        }
        ItfCampoInstanciado campoInst = (ItfCampoInstanciado) campoInstanciado;
        if (campoInst.getObjetoDoAtributo().getId() != 0) {
            return null;
        }
        if ((pValor instanceof Integer)
                || (pValor instanceof Long)) {
            long valorrr = (long) pValor;
            if (valorrr == 0) {
                return null;
            }
        }
        if (!UtilSBCoreValidacao.isValorUnico(campoInstanciado, pValor)) {
            return "Já existe um " + campoInstanciado.getLabel() + " com o valor " + pValor;
        }
        return null;

    }

}
