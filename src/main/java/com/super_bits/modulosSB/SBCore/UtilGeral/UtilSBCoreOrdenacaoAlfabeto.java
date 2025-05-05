/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author salvio
 */
public class UtilSBCoreOrdenacaoAlfabeto {

    /**
     * Retorna a próxima letra do alfabeto, com suporte para maiúsculas e
     * minúsculas. Se a letra for 'z' ou 'Z', retorna 'a' ou 'A'
     * respectivamente. Retorna null se o caractere não for uma letra.
     *
     * @param letra Letra de entrada (char)
     * @return Próxima letra (char) ou null se não for letra.
     */
    public static Character proximaLetra(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            return (letra == 'z') ? 'a' : (char) (letra + 1);
        } else if (letra >= 'A' && letra <= 'Z') {
            return (letra == 'Z') ? 'A' : (char) (letra + 1);
        } else {
            return null; // Não é uma letra
        }
    }

    /**
     * Converte um número para sua representação em letras (estilo colunas do
     * Excel). Exemplo: 1 -> A, 26 -> Z, 27 -> AA, 28 -> AB, etc. Retorna null
     * para valores menores ou iguais a zero.
     *
     * @param numero Número inteiro (1 ou mais)
     * @return Representação em letras, ou null se número for inválido.
     */
    public static String numeroParaLetras(int numero) {
        if (numero <= 0) {
            return null;
        }

        StringBuilder resultado = new StringBuilder();
        while (numero > 0) {
            numero--; // Decrementa 1 porque A = 1 e não 0
            char letra = (char) ('A' + (numero % 26));
            resultado.insert(0, letra);
            numero /= 26;
        }

        return resultado.toString();
    }
}
