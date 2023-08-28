/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreNumerosTest {

    /**
     * Test of getLpadZero method, of class UtilSBCoreNumeros.
     */
    public void testGetLpadZero() {
        System.out.println("getLpadZero");
        Integer pValor = null;
        int pCasas = 0;
        Integer expResult = null;
        Integer result = UtilSBCoreNumeros.getLpadZero(pValor, pCasas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void converterStringParaDouble() {

        double valorConvertido = UtilSBCoreNumeros.getDoublePorString("325.24");
        assertEquals("Falhou converdento", 325.24, valorConvertido, 0);
        double valorConvertido1 = UtilSBCoreNumeros.getDoublePorString("1.123,20");
        assertEquals("", 1123.20, valorConvertido1, 0);
        double valorConvertido2 = UtilSBCoreNumeros.getDoublePorString("1,333.10");
        assertEquals("", 1333.10, valorConvertido2, 0);
        double valorConvertido3 = UtilSBCoreNumeros.getDoublePorString("12,23");
        assertEquals("", 12.23, valorConvertido3, 0);
        double valorConvertido4 = UtilSBCoreNumeros.getDoublePorString("12.23");
        assertEquals("", 12.23, valorConvertido4, 0);

        double valorConvertido5 = UtilSBCoreNumeros.getDoublePorString("12");
        assertEquals("", 0.12, valorConvertido5, 0);

        double valorConvertido6 = UtilSBCoreNumeros.getDoublePorString("12.2");
        assertEquals("", 12.2, valorConvertido6, 0);

        double valorConvertido7 = UtilSBCoreNumeros.getDoublePorString("5000.2");
        assertEquals("", 5000.20, valorConvertido7, 0);

        double valorConvertido8 = UtilSBCoreNumeros.getDoublePorString("R$1,333.10");
        assertEquals("", 1333.10, valorConvertido8, 0);

        double valorConvertido9 = UtilSBCoreNumeros.getDoublePorString("$1,333.10");
        assertEquals("", 1333.10, valorConvertido9, 0);

        System.out.println("teste");

    }

    /**
     * Test of getConcatenados method, of class UtilSBCoreNumeros.
     */
    public void testGetConcatenados() {
        System.out.println("getConcatenados");
        int[] pNumeros = null;
        Integer expResult = null;
        Integer result = UtilSBCoreNumeros.getConcatenados(pNumeros);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroRandomico method, of class UtilSBCoreNumeros.
     */
    @Test
    public void testGetNumeroRandomico() {
        System.out.println("getNumeroRandomico");
        int pMinimo = 666;
        int pMaximo = 1024;

        Integer result = UtilSBCoreNumeros.getNumeroRandomico(pMinimo, pMaximo);
        assertTrue("gerou numero menor que o minimo", result > pMinimo);
        assertTrue("gerou numero maior  que o maximo", result < pMaximo);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of converterMoeda method, of class UtilSBCoreNumeros.
     */
    @Test
    public void testConverterMoeda() {

        System.out.println(UtilSBCoreNumeros.converterMoeda(10.30));

    }

    @Test
    public void converterInteiro() {
        System.out.println("inteiro" + UtilSBCoreNumeros.formatarNumeroInteiro(12334));
    }

}
