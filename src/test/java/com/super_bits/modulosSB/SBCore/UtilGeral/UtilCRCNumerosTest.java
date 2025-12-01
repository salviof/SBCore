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
public class UtilCRCNumerosTest {

    /**
     * Test of getLpadZero method, of class UtilCRCNumeros.
     */
    public void testGetLpadZero() {
        System.out.println("getLpadZero");
        Integer pValor = null;
        int pCasas = 0;
        Integer expResult = null;
        Integer result = UtilCRCNumeros.getLpadZero(pValor, pCasas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void converterStringParaDouble() {

        double valorConvertido = UtilCRCNumeros.getDoublePorString("325.24");
        assertEquals("Falhou converdento", 325.24, valorConvertido, 0);
        double valorConvertido1 = UtilCRCNumeros.getDoublePorString("1.123,20");
        assertEquals("", 1123.20, valorConvertido1, 0);
        double valorConvertido2 = UtilCRCNumeros.getDoublePorString("1,333.10");
        assertEquals("", 1333.10, valorConvertido2, 0);
        double valorConvertido3 = UtilCRCNumeros.getDoublePorString("12,23");
        assertEquals("", 12.23, valorConvertido3, 0);
        double valorConvertido4 = UtilCRCNumeros.getDoublePorString("12.23");
        assertEquals("", 12.23, valorConvertido4, 0);

        double valorConvertido5 = UtilCRCNumeros.getDoublePorString("12");
        assertEquals("", 0.12, valorConvertido5, 0);

        double valorConvertido6 = UtilCRCNumeros.getDoublePorString("12.2");
        assertEquals("", 12.2, valorConvertido6, 0);

        double valorConvertido7 = UtilCRCNumeros.getDoublePorString("5000.2");
        assertEquals("", 5000.20, valorConvertido7, 0);

        double valorConvertido8 = UtilCRCNumeros.getDoublePorString("R$1,333.10");
        assertEquals("", 1333.10, valorConvertido8, 0);

        double valorConvertido9 = UtilCRCNumeros.getDoublePorString("$1,333.10");
        assertEquals("", 1333.10, valorConvertido9, 0);

        System.out.println("teste");

    }

    /**
     * Test of getConcatenados method, of class UtilCRCNumeros.
     */
    public void testGetConcatenados() {
        System.out.println("getConcatenados");
        int[] pNumeros = null;
        Integer expResult = null;
        Integer result = UtilCRCNumeros.getConcatenados(pNumeros);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumeroRandomico method, of class UtilCRCNumeros.
     */
    @Test
    public void testGetNumeroRandomico() {
        System.out.println("getNumeroRandomico");
        int pMinimo = 666;
        int pMaximo = 1024;

        Integer result = UtilCRCNumeros.getNumeroRandomico(pMinimo, pMaximo);
        assertTrue("gerou numero menor que o minimo", result > pMinimo);
        assertTrue("gerou numero maior  que o maximo", result < pMaximo);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of converterMoeda method, of class UtilCRCNumeros.
     */
    @Test
    public void testConverterMoeda() {

        System.out.println(UtilCRCNumeros.converterMoeda(10.30));

    }

    @Test
    public void converterInteiro() {
        System.out.println("inteiro" + UtilCRCNumeros.formatarNumeroInteiro(12334));
    }

}
