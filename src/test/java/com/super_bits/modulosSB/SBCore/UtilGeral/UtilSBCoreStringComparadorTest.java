/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
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
public class UtilSBCoreStringComparadorTest {

    @Before
    public void UtilSBCoreStringComparadorTest() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void teste() {
        double score = UtilSBCoreStringComparador.JaroWinkler("eritro", "erito");
        System.out.println(score);
    }

    /**
     * Test of isParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecido_String_String() {

        String testeMaluco = "ROSCA CHOCOLATE";
        String testeMalucoPrametro = "coca cola";
        for (String ss : testeMalucoPrametro.toLowerCase().split("\\s")) {
            for (String s : testeMaluco.toLowerCase().split("\\s")) {
                UtilSBCoreStringComparador.JaroWinkler(s, ss);
            }
        }

        double score = UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");
        UtilSBCoreStringComparador.JaroWinkler("CHOCOLATE", "CHOCO");
        UtilSBCoreStringComparador.JaroWinkler("CHOCO", "CHOCOLATE");
        UtilSBCoreStringComparador.JaroWinkler("CHOCOLATE", "CHOCOLATE");
        UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        UtilSBCoreStringComparador.JaroWinkler("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "SHAMPOO");
        System.out.println(score);

        UtilSBCoreStringComparador.isParecido("SHAMPOO DOVE CACHOS CONTROLADOS DAMAGE THERAPY 200ML", "CHOCOLATE");

        System.out.println("isParecido");
        MetaphonePtBr testeFonetic;
        MetaphonePtBrFrouxo testeFonetic2;
        UtilSBCoreStringComparador.isParecido("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G", "manteiga");
        System.out.println(new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString());
        System.out.println(new MetaphonePtBr("queratina").toString());
        assertTrue("Metaphone", new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString().contains(new MetaphonePtBr("nieli").toString()));

        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JaroWinkler method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testJaroWinkler() {
        System.out.println("JaroWinkler");
        String pUm = "";
        String pDois = "";
        double expResult = 0.0;
        double result = UtilSBCoreStringComparador.JaroWinkler(pUm, pDois);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBastanteParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsBastanteParecido() {
        System.out.println("isBastanteParecido");
        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isBastanteParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecido_3args() {
        System.out.println("isParecido");
        String pReferencia = "";
        String pParametro = "";
        int indiceIgualdade = 0;
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecido(pReferencia, pParametro, indiceIgualdade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBastanteParecidoFoneticamente method, of class
     * UtilSBCoreStringComparador.
     */
    @Test
    public void testIsBastanteParecidoFoneticamente() {
        System.out.println("isBastanteParecidoFoneticamente");
        String pReferencia = "";
        String pParamentro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isBastanteParecidoFoneticamente(pReferencia, pParamentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecidoFoneticamente method, of class
     * UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecidoFoneticamente() {
        System.out.println("isParecidoFoneticamente");
        String pReferencia = "";
        String pParamentro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecidoFoneticamente(pReferencia, pParamentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalizarTexto method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testNormalizarTexto() {
        System.out.println("normalizarTexto");
        String texto = "";
        String expResult = "";
        String result = UtilSBCoreStringComparador.normalizarTexto(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecido_4args() {
        System.out.println("isParecido");
        ComoEntidadeSimples pReferencia = null;
        List<? extends ItfCaminhoCampo> pCampos = null;
        String pParametro = "";
        boolean pParametroNumerico = false;
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecido(pReferencia, pCampos, pParametro, pParametroNumerico);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
