/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioLiberal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidadorUnitarioNulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioAtributoUnico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioCNPJ;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioCPF;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioCodigoDeBarass;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioMaximo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioMinimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao.ValidarUnitarioREGEX;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

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
    CODIGO_DE_BARRAS,
    CAMPO_UNICO,
    REGEX,
    EMAIL,
    LOCALIZACAO;

    public ItfValidacaoUnitaria getValidador(ItfCampoInstanciado pCampo) {
        try {
            switch (pCampo.getFabricaTipoAtributo()) {
                case CODIGO_DE_BARRAS:
                    switch (this) {
                        case CODIGO_DE_BARRAS:
                            return new ValidarUnitarioCodigoDeBarass(pCampo);
                    }
                    break;
                case CPF:
                    switch (this) {
                        case CPF:
                            return new ValidarUnitarioCPF(pCampo);
                    }
                    break;
                case CNPJ:
                    switch (this) {
                        case CNPJ:
                            return new ValidarUnitarioCNPJ(pCampo);
                    }
                    break;
                case LC_LOCALIZACAO: {
                    switch (this) {
                        case LOCALIZACAO:
                            return new ValidadorUnitarioLocalizacao(pCampo);
                    }

                }
                break;
                case EMAIL:
                    switch (this) {
                        case EMAIL:
                            return new ValidadorUnitarioEmail(pCampo);
                    }
                    break;
                default:
            }

            switch (this) {
                case NULO:
                    return new ValidadorUnitarioNulo(pCampo);
                case MINIMO:
                    return new ValidarUnitarioMinimo(pCampo);
                case MAXIMO:
                    return new ValidarUnitarioMaximo(pCampo);

                case CAMPO_UNICO:
                    return new ValidarUnitarioAtributoUnico(pCampo);
                case REGEX:

                    return new ValidarUnitarioREGEX(pCampo);

                default:
                    return new ValidadorUnitarioLiberal();

            }
        } catch (Throwable t) {
            if (pCampo != null) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha detectando classse de implantação para " + this.name() + " em " + pCampo.getNomeCompostoIdentificador(), t);
            } else {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha tentando validar campoinstanciado nulo", t);
            }

            return new ValidadorUnitarioLiberal();
        }
    }

}
