/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilCRCStringsCammelCaseSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilCRCStringsCammelCase extends UtilCRCStringsCammelCaseSimples {

    public static String getCammelByTextoSemCaractersEspeciais(String pString) {
        return getCammelByTexto(UtilCRCStringFiltros.removeCaracteresEspeciais(pString));
    }

    public static String getCamelCaseTextoSemAcentuacaoECaracterEspecial(String pTExto) {

        pTExto = UtilCRCStringFiltros.removeCaracteresEspeciaisAcentoMantendoApenasLetrasNumerosEspaco(pTExto);

        return getCammelByTexto(pTExto);

    }

    /**
     * Mantido por compatibilidade
     *
     * @param pString
     * @return
     */
    public static String getCammelByTexto(String pString) {
        String[] parts = pString.split("[\\s?_?-]+");
        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].length() > 1) {
                parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1).toLowerCase();
            } else {
                parts[i] = parts[i].toUpperCase();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; ++i) {
            sb.append(parts[i]);
        }
        return sb.toString();

    }

    public static String getTextoByCammelMinusculo(String pString) {
        if (pString == null) {
            return null;
        }
        return pString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );

    }

    public static String getCamelByTextoPrimeiraLetraMaiuscula(String pString) {
        return UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getCammelByTexto(pString));
    }

    public static String getCamelByTextoPrimeiraLetraMinuscula(String pString) {
        return UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMinuscula(getCammelByTexto(pString));
    }

    public static String getTextoByCammelPrimeiraLetraMaiuscula(String pString) {
        return UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getTextoByCammel(pString));
    }

    public static String getCamelByTextoPrimeiraLetraMaiusculaSemCaracterEspecial(String pString) {
        return UtilCRCStringSlugs.gerarSlugSimples(UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(getCammelByTexto(pString)));
    }

}
