/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static String getHyperlink(String pTexto, String pUrl) {
        StringBuilder conteudoBuilder = new StringBuilder();
        conteudoBuilder.append("<a href='");
        try {
            conteudoBuilder.append(new URL(pUrl).toString());
            conteudoBuilder.append("' >");
            conteudoBuilder.append(pTexto);
            conteudoBuilder.append("</a>");
            return conteudoBuilder.toString();
        } catch (MalformedURLException ex) {
            return pTexto + " (Url inválida)";
        }
    }
}
