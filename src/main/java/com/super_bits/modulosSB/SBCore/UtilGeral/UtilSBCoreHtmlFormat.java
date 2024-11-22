/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author salvio
 */
public class UtilSBCoreHtmlFormat {

    public static String adicionarBReakLineACadaXCaracteres(final String pConteudo, int pNumeroLinhas) {
        if (pNumeroLinhas < 1) {
            return pConteudo;
        }
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pConteudo)) {
            return pConteudo;
        }
        return WordUtils.wrap(pConteudo, pNumeroLinhas, "</br>", false);
    }

}
