/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioLiberal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioNulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioAtributoUnico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioCNPJ;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioCPF;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioMaximo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioMinimo;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoValidacaoUnitaria {

    NULO,
    MINIMO,
    MAXIMO,
    CPF,
    CNPJ,
    CAMPO_UNICO;

    public ItfValidacaoUnitaria getValidador(ItfAtributoObjetoSB pCampo) {
        switch (this) {
            case NULO:
                return new ValidadorUnitarioNulo(pCampo);
            case MINIMO:
                return new ValidarUnitarioMinimo(pCampo);
            case MAXIMO:
                return new ValidarUnitarioMaximo(pCampo);
            case CPF:
                return new ValidarUnitarioCPF(pCampo);
            case CNPJ:
                return new ValidarUnitarioCNPJ(pCampo);
            case CAMPO_UNICO:
                return new ValidarUnitarioAtributoUnico(pCampo);

            default:
                return new ValidadorUnitarioLiberal();

        }

    }

}
