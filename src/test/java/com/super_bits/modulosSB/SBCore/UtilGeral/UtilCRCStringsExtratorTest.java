/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class UtilCRCStringsExtratorTest {

    public UtilCRCStringsExtratorTest() {
    }

    /**
     * Test of extrairEmail method, of class UtilCRCStringsExtrator.
     */
    @Test
    public void testExtrairEmail() {

    }

    /**
     * Test of extrairEmails method, of class UtilCRCStringsExtrator.
     */
    @Test
    public void testExtrairEmails() {

    }

    /**
     * Test of extrairPalavrasEntreColchete method, of class
     * UtilCRCStringsExtrator.
     */
    @Test
    public void testExtrairPalavrasEntreColchete() {

        String texto = " Apenas um teste pegando palavras entre colchete como [esta] e [esta] e [estaquiTambém] e uma mais deificioainda [https://www.googlc.eom.br]  ";
        List<String> lista = UtilCRCStringsExtrator.extrairPalavrasEntreColchete(texto);
        assertEquals(4, lista.size());
        for (String item : lista) {
            System.out.println(item);
        }

    }

}
