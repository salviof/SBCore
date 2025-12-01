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
 * @author sfurbino
 */
public class UtilCRCStringGeradorTest {

    /**
     * Test of getStringRandomica method, of class UtilCRCStringGerador.
     */
    @Test
    public void testGetStringRandomica() {
        System.out.println("getStringRandomica");
        int pQuantidade = 15;

        for (int i = 0; i < 10; i++) {
            String result = UtilCRCStringGerador.getStringRandomicaUUID(pQuantidade);
            System.out.println(result);
        }
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of GetLorenIpsilum method, of class UtilCRCStringGerador.
     */
    @Test
    public void testGetLorenIpsilum_int_UtilCRCStringGeradorTIPO_LOREN() {
        System.out.println("GetLorenIpsilum");
        int pQuantidade = 0;
        UtilCRCStringGerador.TIPO_LOREN pTipoLTipo_loren = null;
        String expResult = "";
        String result = UtilCRCStringGerador.GetLorenIpsilum(pQuantidade, pTipoLTipo_loren);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLorenIpsilum method, of class UtilCRCStringGerador.
     */
    @Test
    public void testGetLorenIpsilum_UtilCRCStringGeradorTIPO_LOREN() {
        System.out.println("getLorenIpsilum");
        UtilCRCStringGerador.TIPO_LOREN pTipoLTipo_loren = null;
        String expResult = "";
        String result = UtilCRCStringGerador.getLorenIpsilum(pTipoLTipo_loren);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
