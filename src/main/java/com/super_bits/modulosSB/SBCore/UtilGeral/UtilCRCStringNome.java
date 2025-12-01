/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.List;

/**
 *
 * @author salvio
 */
public class UtilCRCStringNome {

    public static String gerarPrimeiraLetraMaiusculaPalavras(String pFrase) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pFrase)) {
            return pFrase;
        }
        String[] arr = pFrase.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String arr1 : arr) {
            if (arr1.isEmpty()) {
                continue;
            }
            if (arr1.length() > 1) {
                sb.append(Character.toUpperCase(arr1.charAt(0))).append(arr1.substring(1).toLowerCase()).append(" ");
            } else {
                sb.append(Character.toUpperCase(arr1.charAt(0))).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String gerarNomeAbreviado(String pNome) {
        if (UtilCRCStringValidador.isNuloOuEmbranco(pNome)) {
            return pNome;
        }
        pNome = gerarPrimeiraLetraMaiusculaPalavras(pNome);
        String[] nomes = pNome.split(" ");
        if (nomes.length < 3) {
            return pNome;
        }
        String meio = " ";
        for (int i = 1; i <= nomes.length - 2; i++) {

            meio += nomes[i] + ". ";
        }

        return nomes[0] + meio + nomes[nomes.length - 1];

    }

}
