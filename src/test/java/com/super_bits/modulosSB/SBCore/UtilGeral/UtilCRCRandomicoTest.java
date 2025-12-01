/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCRandomicoTest extends TestesCore {

    /**
     * Test of getValorStringRandomico method, of class UtilCRCRandomico.
     */
    @Test
    public void testGetValorStringRandomico() {
        try {
            String pvalor = UtilCRCRandomico.getValorStringRandomico(UtilCRCRandomico.TIPO_VALOR_RANDON.NUMERO, 5);
            System.out.println(UtilCRCRandomico.getValorRandomicoDaMaskara('#'));
            System.out.println(pvalor);
            System.out.println(UtilCRCRandomico.getValorStringRandomico(UtilCRCRandomico.TIPO_VALOR_RANDON.LETRAS, 5));
        } catch (Throwable t) {
            //lancarErroJUnit(t);
        }
    }

}
