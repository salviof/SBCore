/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author desenvolvedorninja01
 * @since 11/02/2020
 * @version 1.0
 */
public class UtilCRCNumerosOperacoes {

    /**
     *
     * @param f1
     * @param f2
     * @return Retorna verdadeiro se o valor for menor que 3 centavos
     */
    public static boolean compararDoubleAproximado(double f1, double f2) {

        return compararDoubleAproximado(.03, f1, f2);

    }

    /**
     *
     * @param plimiteTolerancia Limite de toleráncia exemplo: 0.03 centavos
     * @param f1 Valor 1
     * @param f2 Valor 2
     * @return Verdadeiro se a diferença for menor que o limite de tolerância
     */
    public static boolean compararDoubleAproximado(final double plimiteTolerancia, double f1, double f2) {
        if (plimiteTolerancia > 1) {
            throw new UnsupportedOperationException("O Limite de tolerância precisa ser menor que 1");
        }

        boolean resultado = Math.abs(f1 - f2) < plimiteTolerancia;
        return resultado;

    }

}
