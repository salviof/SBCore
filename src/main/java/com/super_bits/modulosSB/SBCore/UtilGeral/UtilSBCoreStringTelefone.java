/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author salvio
 */
public class UtilSBCoreStringTelefone {

    public static String gerarCeluarInternacional(final String pTelefone) {
        if (pTelefone.length() < 10) {
            return null;
        }
        String telefoneFormatoInternacional = UtilSBCoreStringFiltros.removeCaracteresEspeciais(pTelefone);
        telefoneFormatoInternacional = telefoneFormatoInternacional.replace("_", "");

        if (telefoneFormatoInternacional.startsWith("55") && telefoneFormatoInternacional.length() > 11) {
            telefoneFormatoInternacional = "+55" + telefoneFormatoInternacional.substring(2, telefoneFormatoInternacional.length());
        }

        if (!telefoneFormatoInternacional.startsWith("+55")) {
            telefoneFormatoInternacional = "+55" + telefoneFormatoInternacional;
        }

        if (telefoneFormatoInternacional.length() == 13) {
            telefoneFormatoInternacional = telefoneFormatoInternacional.substring(0, 5) + "9"
                    + telefoneFormatoInternacional.substring(5, telefoneFormatoInternacional.length());
        }

        if (telefoneFormatoInternacional.length() >= 13) {
            return telefoneFormatoInternacional;

        }
        return null;
    }

    public static String gerarCeluarWhatasapp(String pTelefone) {
        String telefone = gerarCeluarInternacional(pTelefone);
        //+5531988888888"
        if (telefone == null) {
            return null;
        }
        if (telefone.length() == 13) {
            return telefone.replace("+", "");
        }
        if (telefone.length() == 14) {
            if (telefone.charAt(5) == '9') {
                telefone = UtilSBCoreStringFiltros.removerCaracteres(telefone, 5);
                return telefone.replace("+", "");
            }
        }
        return pTelefone.replace("+", "");

    }

}
