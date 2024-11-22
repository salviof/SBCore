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
public class UtilSBCoreStringFiltrosTest {

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
     * Test of getStringSemNumeros method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetStringSemNumeros() {

    }

    /**
     * Test of getNomeReduzido method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetNomeReduzido() {

    }

    /**
     * Test of getNuloEmEmpty method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetNuloEmEmpty() {

    }

    /**
     * Test of getLTRIMRemoveEspacosAesquerda method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetLTRIMRemoveEspacosAesquerda() {

    }

    /**
     * Test of getRTRIMRemoveEspacosAesquerda method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetRTRIMRemoveEspacosAesquerda() {

    }

    /**
     * Test of getTRIMRemoveEspacos method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetTRIMRemoveEspacos() {

    }

    /**
     * Test of substituirEspacosPorCaracter method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testSubstituirEspacosPorCaracter() {

    }

    /**
     * Test of getLpad method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetLpad() {
        String lpad = UtilSBCoreStringFiltros.getLpad("OPA", 10, " ");
        assertEquals("Rpad falhou", "       OPA", lpad);
    }

    /**
     * Test of getRpad method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetRpad() {
        String rpad = UtilSBCoreStringFiltros.getRpad("OPA", 10, " ");
        assertEquals("Rpad falhou", "OPA       ", rpad);
    }

    /**
     * Test of quebrarStringEmLinhas method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testQuebrarStringEmLinhas() {

    }

    /**
     * Test of getPrimeirasXLetrasDaString method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetPrimeirasXLetrasDaString() {

    }

    /**
     * Test of getNumericosDaString method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetNumericosDaString() {

    }

    /**
     * Test of filtrarApenasNumeros method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testFiltrarApenasNumeros() {
        final String LETRASENUMEROS = "123ADASDA SDAasda/sd .asd  -asd asd 123 asd123";
        final String APENASNUMEROS = UtilSBCoreStringFiltros.filtrarApenasNumeros(LETRASENUMEROS);
        assertFalse("lETRAS eforam encontrados", APENASNUMEROS.contains("a"));
        assertEquals("caracteres inválidos tentando obter número", "123123123", APENASNUMEROS);
        String documento = "32371685000179?22,7";
        documento = documento.substring(0, documento.indexOf("?"));
        System.out.println(documento);
    }

    /**
     * Test of filtrarApenasLetra method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testFiltrarApenasLetra() {
        final String LETRASENUMEROS = "123ADASDA SDAasdasd asd asd asd 123 asd123";
        final String APENAS_LETRAS = UtilSBCoreStringFiltros.filtrarApenasLetra(LETRASENUMEROS);
        assertFalse("lETRAS eforam encontrados", APENAS_LETRAS.contains("123"));
    }

    /**
     * Test of getMascaraJavaMaskParaJQueryMask method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testGetMascaraJavaMaskParaJQueryMask() {
        System.out.println("getMascaraJavaMaskParaJQueryMask");
        String pMascara = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.getMascaraJavaMaskParaJQueryMask(pMascara);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inverteStringData method, of class UtilSBCoreStringFiltros.
     */
    /**
     * Test of removeEspacamentoDuplo method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testRemoveEspacamentoDuplo() {
        System.out.println("removeEspacamentoDuplo");
        String param = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.removeEspacamentoDuplo(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEspacamentoDuploEUltimoEspacaomento method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testRemoveEspacamentoDuploEUltimoEspacaomento() {
        System.out.println("removeEspacamentoDuploEUltimoEspacaomento");
        String param = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.removeEspacamentoDuploEUltimoEspacaomento(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCaracteresEspeciaisEspacosETracos method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testRemoveCaracteresEspeciaisEspacosETracos() {
        System.out.println("removeCaracteresEspeciaisEspacosETracos");
        String param = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.removeCaracteresEspeciaisEspacosETracos(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCaracteresEspeciaisEspacosTracosEPontos method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testRemoveCaracteresEspeciaisEspacosTracosEPontos() {
        System.out.println("removeCaracteresEspeciaisEspacosTracosEPontos");
        String param = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.removeCaracteresEspeciaisEspacosTracosEPontos(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of limparCacteresEstranhosDaStringComNumeros method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testLimparCacteresEstranhosDaStringComNumeros() {
        System.out.println("limparCacteresEstranhosDaStringComNumeros");
        String s = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.limparCacteresEstranhosDaStringComNumeros(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inverteString method, of class UtilSBCoreStringFiltros.
     */
    @Test
    public void testInverteString() {
        System.out.println("inverteString");
        String pString = "";
        String expResult = "";
        String result = UtilSBCoreStringFiltros.inverteString(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of substituirUsandoInicioFimTrechoPesquisado method, of class
     * UtilSBCoreStringFiltros.
     */
    @Test
    public void testSubstituirUsandoInicioFimTrechoPesquisado() {
        System.out.println("substituirUsandoInicioFimTrechoPesquisado");
        String pTextoOriginal = "123 blablabla 456 dfaslkdfjalkjds conteudo 123 vousumirdaqui 456 asdfasdfasdf";
        String inicioTrechoPesquisado = "123";
        String pFinalTrechoPesquisado = "456";
        String pconteudoSubstituto = "";
        String expResult = " dfaslkdfjalkjds conteudo  asdfasdfasdf";
        String result = UtilSBCoreStringFiltros
                .substituirUsandoInicioFimTrechoPesquisado(pTextoOriginal,
                        inicioTrechoPesquisado,
                        pFinalTrechoPesquisado,
                        pconteudoSubstituto);
        assertEquals(expResult, result);

    }

}
