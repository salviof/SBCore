/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;

/**
 *
 * @author desenvolvedor
 */
public class ValidadorUnitarioNulo extends ValidadorUnitarioCampoInstGenerico {

    public ValidadorUnitarioNulo(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.NULO);
    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        if (!campoInstanciado.isObrigatorio()) {
            return null;
        }
        switch (campoInstanciado.getFabricaTipoAtributo()) {
            case ARQUIVO_DE_ENTIDADE:
                if (pValor != null) {
                    return null;
                }
            default:
                if (campoInstanciado.isUmValorLivre()) {
                    if (campoInstanciado.isObrigatorio()) {
                        if (pValor instanceof String) {
                            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor.toString())) {
                                return campoInstanciado.getLabel() + " não pode ser nulo";
                            }
                        }
                    }
                }
        }

        return null;
    }

}
