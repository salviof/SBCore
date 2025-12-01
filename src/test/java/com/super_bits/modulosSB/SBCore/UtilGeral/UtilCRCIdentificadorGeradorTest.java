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
 * @author salviofurbino
 */
public class UtilCRCIdentificadorGeradorTest {

    public UtilCRCIdentificadorGeradorTest() {
    }

    /**
     * Test of getIdentificadorUnico method, of class
     * UtilCRCIdentificadorGerador.
     */
    @Test
    public void testGetIdentificadorUnico() {
        System.out.println(UtilCRCGeradorDeID.getIdentificadorUnico());

    }

    @Test
    public void testItentificadorUnicoNumeroLetras() {
        System.out.println(UtilCRCGeradorDeID.getIdentificadorUnicoNumerosLetras());
    }

}
