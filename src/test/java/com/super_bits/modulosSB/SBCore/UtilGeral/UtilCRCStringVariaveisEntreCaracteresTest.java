/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.List;
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
public class UtilCRCStringVariaveisEntreCaracteresTest {

    public UtilCRCStringVariaveisEntreCaracteresTest() {
    }

    /**
     * Test of extrairVariaveisEntreColchete method, of class
     * UtilCRCStringVariaveisEntreCaracteres.
     */
    @Test
    public void testExtrairVariaveisEntreColchete() {
        List<String> placehoders = UtilCRCStringVariaveisEntreCaracteres.extrairVariaveisEntreColchete("[Cliente.nome] é um exeplo de uma [Entidade.nome] que pode ter também um [link:FabAcaoDOMINIO_DE_ENTIDADE_FRM_MEUFORM]");
        assertEquals(placehoders.size(), 3);
    }

    /**
     * Test of extrairVariaveisEntreAspas method, of class
     * UtilCRCStringVariaveisEntreCaracteres.
     */
    @Test
    public void testExtrairVariaveisEntreAspas() {
        System.out.println("extrairVariaveisEntreAspas");
        String pValor = "";
        List<String> expResult = null;
        List<String> result = UtilCRCStringVariaveisEntreCaracteres.extrairVariaveisEntreAspas(pValor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extrairVariaveisEntreNenorMaior method, of class
     * UtilCRCStringVariaveisEntreCaracteres.
     */
    @Test
    public void testExtrairVariaveisEntreNenorMaior() {
        System.out.println("extrairVariaveisEntreNenorMaior");
        String pValor = "";
        List<String> expResult = null;
        List<String> result = UtilCRCStringVariaveisEntreCaracteres.extrairVariaveisEntreNenorMaior(pValor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarTextoSubstituindoAtributosSimples method, of class
     * UtilCRCStringVariaveisEntreCaracteres.
     */
    @Test
    public void testGerarTextoSubstituindoAtributosSimples() {
        System.out.println("gerarTextoSubstituindoAtributosSimples");
        String pTexto = "";
        ComoEntidadeSimples pObjeto = null;
        String expResult = "";
        String result = UtilCRCStringVariaveisEntreCaracteres.gerarTextoSubstituindoAtributosSimples(pTexto, pObjeto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
