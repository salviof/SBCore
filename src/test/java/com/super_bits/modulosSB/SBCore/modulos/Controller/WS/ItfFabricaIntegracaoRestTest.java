/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS;

import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class ItfFabricaIntegracaoRestTest extends TestesCore {

    public ItfFabricaIntegracaoRestTest() {
    }

    /**
     * Test of criarObjeto method, of class ItfFabricaIntegracaoRest.
     */
    @Test
    public void testCriarObjeto() {
        try {
            FabRestExemplo.USUARIO_OBTER.getObjeto();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

}
