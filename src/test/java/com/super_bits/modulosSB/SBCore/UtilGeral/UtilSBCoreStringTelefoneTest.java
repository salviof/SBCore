/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class UtilSBCoreStringTelefoneTest {

    String teste1 = "3187777777";
    String teste2 = "31987777777";
    String teste3 = "5531987777777";
    String teste4 = "+55(31)987777777";
    String teste5 = "55(31)987777777";
    String teste6 = "(31)87777777";

    String teste7 = "88888888";
    String teste8 = "988888888";

    public UtilSBCoreStringTelefoneTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of gerarCeluarInternacional method, of class
     * UtilSBCoreStringTelefone.
     */
    @Test
    public void testGerarCeluarInternacional() {
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste1));
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste2));
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste3));
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste4));
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste5));
        assertEquals("Resultado inesperado", "+5531987777777", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste6));
        assertNull("Resultado inesperado", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste7));
        assertNull("Resultado inesperado", UtilSBCoreStringTelefone.gerarCeluarInternacional(teste8));

    }

    /**
     * Test of gerarCeluarWhatasapp method, of class UtilSBCoreStringTelefone.
     */
    @Test
    public void testGerarCeluarWhatasapp() {

    }

}
