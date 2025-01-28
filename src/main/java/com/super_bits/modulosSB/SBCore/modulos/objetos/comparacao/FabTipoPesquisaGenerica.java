/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author salvio
 */
public enum FabTipoPesquisaGenerica {

    CODIGO,
    NOME,
    DESCRICAO,
    TELEFONE,
    EMAIL,
    SITE,
    DOCUMENTO,
    PERSONALIZADA;

    public static FabTipoPesquisaGenerica getTipoPesquisaByTermo(final String pTermoPesquisa) {
        if (UtilSBCoreStringValidador.isContemApenasNumero(pTermoPesquisa)) {
            return CODIGO;
        }
        if (pTermoPesquisa.contains("@")) {
            return EMAIL;
        }

        if (pTermoPesquisa.contains(".br") || pTermoPesquisa.contains(".com")) {
            return SITE;
        }

        //CNPJS
        if (pTermoPesquisa.contains("/0001") || pTermoPesquisa.contains("/0002") || pTermoPesquisa.contains("/0003")) {
            return DOCUMENTO;
        }
        String cpfRegex = "^(0?\\d{2}\\.\\d{3}(\\.\\d{3})?(-\\d{2})?)$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(cpfRegex);
        Matcher matcher = pattern.matcher(pTermoPesquisa);

        // Verifica se há correspondência
        if (matcher.find()) {
            return DOCUMENTO;
        }
        String phoneRegex = "\\(?\\d{0,3}\\)?\\s?\\d{4,10}$";
        pattern = Pattern.compile(phoneRegex);
        matcher = pattern.matcher(pTermoPesquisa);
        if (matcher.find()) {
            return TELEFONE;
        }
        if (pTermoPesquisa.length() > 20) {
            return DESCRICAO;
        }
        return NOME;
    }

}
