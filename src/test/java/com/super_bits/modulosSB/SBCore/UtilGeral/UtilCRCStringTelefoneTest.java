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
public class UtilCRCStringTelefoneTest {

    String teste1 = "3187777777";
    String teste2 = "31987777777";
    String teste3 = "5531987777777";
    String teste4 = "+55(31)987777777";
    String teste5 = "55(31)987777777";
    String teste6 = "(31)87777777";

    String teste7 = "88888888";
    String teste8 = "988888888";

    public UtilCRCStringTelefoneTest() {
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
     * Test of gerarNumeroTelefoneInternacional method, of class
 UtilCRCStringTelefone.
     */
    public void testGerarCeluarInternacional() {
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste1));
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste2));
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste3));
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste4));
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste5));
        assertEquals("Resultado inesperado", "+5531987777777", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste6));
        assertNull("Resultado inesperado", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste7));
        assertNull("Resultado inesperado", UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(teste8));

    }

    /**
     * Test of gerarCeluarWhatasapp method, of class UtilCRCStringTelefone.
     */
    @Test
    public void testGerarCeluarWhatasapp() {
        System.out.println(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("84178550"));
        System.out.println(UtilCRCStringTelefone.gerarNumeroTelefoneInternacional("553132240677"));

    }

}
