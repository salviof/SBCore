/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.Random;

/**
 *
 * @author salvio
 */
public class UtilSBCoreNumerosAleatorios {

    private static final Random random = new Random();

    public static int getParOuImparAleatorio() {
        return random.nextBoolean() ? 1 : 0;
    }

    public static boolean getParOuImparAleatorioBoolean() {
        return (random.nextBoolean() ? 1 : 0) == 1;
    }

}
