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
public class UtilSBCoreNumerosOperacoes {

    public static boolean compararDouble(double f1, double f2) {
        final double LIMITE = .0001;

<<<<<<< HEAD
        boolean resultado = Math.abs(f1 - f2) < LIMITE;
        return resultado;
=======
        return Math.abs(f1 - f2) < THRESHOLD;
>>>>>>> 8bed58aec68dcf776cae3a1efd2599efc88f4322
    }

}
