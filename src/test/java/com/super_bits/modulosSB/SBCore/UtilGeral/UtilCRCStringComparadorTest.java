/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.List;
import mtfn.MetaphonePtBr;
import mtfn.MetaphonePtBrFrouxo;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author SalvioF
 */
public class UtilCRCStringComparadorTest {

    @Before
    public void UtilCRCStringComparadorTest() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void teste() {
        double score = UtilCRCStringComparador.JaroWinkler("eritro", "erito");
        System.out.println(score);
    }

    /**
     * Test of isParecido method, of class UtilCRCStringComparador.
     */
    @Test
    public void testIsParecido_String_String() {

        String testeMaluco = "ROSCA CHOCOLATE";
        String testeMalucoPrametro = "coca cola";
        for (String ss : testeMalucoPrametro.toLowerCase().split("\\s")) {
            for (String s : testeMaluco.toLowerCase().split("\\s")) {
                UtilCRCStringComparador.JaroWinkler(s, ss);
            }
        }

        double score = UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");
        UtilCRCStringComparador.JaroWinkler("CHOCOLATE", "CHOCO");
        UtilCRCStringComparador.JaroWinkler("CHOCO", "CHOCOLATE");
        UtilCRCStringComparador.JaroWinkler("CHOCOLATE", "CHOCOLATE");
        UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilCRCStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        System.out.println(score);

        UtilCRCStringComparador.isParecido("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");

        System.out.println("isParecido");
        MetaphonePtBr testeFonetic;
        MetaphonePtBrFrouxo testeFonetic2;
        UtilCRCStringComparador.isParecido("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G", "manteiga");
        System.out.println(new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString());
        System.out.println(new MetaphonePtBr("queratina").toString());
        assertTrue("Metaphone", new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString().contains(new MetaphonePtBr("nieli").toString()));

        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JaroWinkler method, of class UtilCRCStringComparador.
     */
    @Test
    public void testJaroWinkler() {
        System.out.println("JaroWinkler");
        String pUm = "";
        String pDois = "";
        double expResult = 0.0;
        double result = UtilCRCStringComparador.JaroWinkler(pUm, pDois);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBastanteParecido method, of class UtilCRCStringComparador.
     */
    @Test
    public void testIsBastanteParecido() {
        System.out.println("isBastanteParecido");
        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isBastanteParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecido method, of class UtilCRCStringComparador.
     */
    @Test
    public void testIsParecido_3args() {
        System.out.println("isParecido");
        String pReferencia = "";
        String pParametro = "";
        int indiceIgualdade = 0;
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isParecido(pReferencia, pParametro, indiceIgualdade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBastanteParecidoFoneticamente method, of class
     * UtilCRCStringComparador.
     */
    @Test
    public void testIsBastanteParecidoFoneticamente() {
        System.out.println("isBastanteParecidoFoneticamente");
        String pReferencia = "";
        String pParamentro = "";
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isBastanteParecidoFoneticamente(pReferencia, pParamentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecidoFoneticamente method, of class
     * UtilCRCStringComparador.
     */
    @Test
    public void testIsParecidoFoneticamente() {
        System.out.println("isParecidoFoneticamente");
        String pReferencia = "";
        String pParamentro = "";
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isParecidoFoneticamente(pReferencia, pParamentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalizarTexto method, of class UtilCRCStringComparador.
     */
    @Test
    public void testNormalizarTexto() {
        System.out.println("normalizarTexto");
        String texto = "";
        String expResult = "";
        String result = UtilCRCStringComparador.normalizarTexto(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecido method, of class UtilCRCStringComparador.
     */
    @Test
    public void testIsParecido_4args() {
        System.out.println("isParecido");
        ComoEntidadeSimples pReferencia = null;
        List<? extends ItfCaminhoCampo> pCampos = null;
        String pParametro = "";
        boolean pParametroNumerico = false;
        boolean expResult = false;
        boolean result = UtilCRCStringComparador.isParecido(pReferencia, pCampos, pParametro, pParametroNumerico);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
