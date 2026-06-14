/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import java.util.regex.Pattern;

/**
 *
 * @author salvio
 */
public class UtilVariavelDeTemplate {

    public static String gerarNomeLista(String pNomeComplentoComSubCampo) {
        String semColchetesExternos = UtilVariavelDeTemplate.removerColchetesExternos(pNomeComplentoComSubCampo);
        int posColchete = semColchetesExternos.indexOf("[");
        String nomeBase = posColchete < 0 ? semColchetesExternos : semColchetesExternos.substring(0, posColchete);
        return UtilVariavelDeTemplate.adicionarColchetes(nomeBase + "[]");
    }

    /**
     * Garante que a chave esteja cercada por colchetes. Quando a chave vier sem
     * colchete, eles são acrescentados; chaves já cercadas permanecem iguais.
     */
    public static String adicionarColchetes(String palavra) {
        if (palavra == null) {
            return null;
        }
        String chave = palavra;
        if (!chave.startsWith("[")) {
            chave = "[".concat(chave);
        }
        if (!chave.endsWith("]")) {
            chave = chave.concat("]");
        }
        if (chave.endsWith("[]")) {
            chave = chave.concat("]");
        }
        return chave;
    }

    /**
     * Extrai o nome do subcampo de uma chave de lista, ou seja, tudo o que vem
     * após o índice "[n]." Exemplos: "solucao[0].descricao" -> "descricao";
     * "solucao[2].tipoSolucao.nome" -> "tipoSolucao.nome". Mantém subcampos
     * compostos (com ponto) intactos.
     */
    public static String gerarNomeSubCampo(String pNomeComplentoComSubCampo) {
        String semColchetesExternos = removerColchetesExternos(pNomeComplentoComSubCampo);
        int posAposIndice = semColchetesExternos.indexOf("].");
        if (posAposIndice >= 0) {
            return semColchetesExternos.substring(posAposIndice + 2);
        }
        int posPonto = semColchetesExternos.lastIndexOf(".");
        if (posPonto < 0) {
            return semColchetesExternos;
        }
        return semColchetesExternos.substring(posPonto + 1);
    }

    public static String removerColchetesExternos(String pChave) {
        if (pChave == null) {
            return null;
        }
        String chave = pChave.trim();
        if (chave.length() >= 2 && chave.startsWith("[") && chave.endsWith("]")) {
            return chave.substring(1, chave.length() - 1);
        }
        return chave;
    }

    /**
     *
     * Explicação
     *
     * .* → qualquer coisa antes (o "Qualquercoisa") \\[\\] → literalmente []
     * \\. → o ponto literal (\\w+) → captura o nome do campo (letras, números,
     * _) $ → fim da string (garante que termina exatamente assim)
     *
     * @param caminho
     * @return
     */
    public static boolean terminaComArrayENomeCampo(String caminho) {
        // [\\w.]+ aceita subcampos compostos (ex: tipoSolucao.nome); \\]? torna tolerante à chave cercada por colchete
        return Pattern.matches(".*\\[\\]\\.[\\w.]+\\]?", caminho);
    }

    /**
     *
     * .* → qualquer coisa antes \\[\\d+\\] → colchetes com um ou mais dígitos
     * dentro (índice numérico, ex: [0], [12]) \\.\\w+$ → ponto seguido do nome
     * do campo, até o fim da string
     *
     * @param caminho
     * @return
     */
    public static boolean terminaComIndiceECampo(String caminho) {
        // [\\w.]+ aceita subcampos compostos (ex: tipoSolucao.nome); \\]? torna tolerante à chave cercada por colchete
        return Pattern.matches(".*\\[\\d+\\]\\.[\\w.]+\\]?", caminho);
    }

}
