/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author salvio
 */
public class UtilCRCStringEnderecos {

    // Formato brasileiro: 00000-000 ou 00000000
    // Apenas CEP com traço: 00000-000
    private static final Pattern PADRAO_CEP
            = Pattern.compile("\\b(\\d{5}-\\d{3})\\b");

    public static String extrairCep(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        Matcher matcher = PADRAO_CEP.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

}
