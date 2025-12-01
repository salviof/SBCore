/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.tempo.FabTipoQuantidadeTempo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCDataHoraTest {

    public final static Long HORASDIA = 24L;
    public final static Long MESESANO = 12L;
    public final static Long MINUTOSHORA = 60L;
    public final static Long SEGUNDOSMINUTO = 60L;
    public final static Long QTDMILISEGUNDOSSEGUNDO = 1000L;
    public final static Long DIASMES = 30L;

    public UtilCRCDataHoraTest() {
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

    @Test
    public void testesDataBuilder() {
        int diaVencimento = 15;
        int mesVencimento = UtilCRCDataHora.getMes(new Date());
        int ano = UtilCRCDataHora.getMes(new Date());
        Date dataVencimento = UtilCRCDataHora.converteStringEmData(diaVencimento + "/" + mesVencimento + "/" + ano);
        System.out.println(dataVencimento);

        diaVencimento = 15;
        mesVencimento = 1;
        ano = UtilCRCDataHora.getMes(new Date());
        dataVencimento = UtilCRCDataHora.converteStringEmData(diaVencimento + "/" + mesVencimento + "/" + ano);
        System.out.println(dataVencimento);

        diaVencimento = 15;
        mesVencimento = 1;
        ano = 22;
        dataVencimento = UtilCRCDataHora.converteStringEmData(diaVencimento + "/" + mesVencimento + "/" + ano);
        System.out.println(dataVencimento);
    }

    @Test
    public void getMesTextoAbreviado() {
        try {
            SimpleDateFormat formatoDataPadrao = new SimpleDateFormat("dd/MM/yyyy");

            assertEquals("janeiro falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/01/23")), "jan");
            assertEquals("feverierio falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/02/23")), "fev");
            assertEquals("março falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/03/23")), "mar");
            assertEquals("abril falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/04/23")), "abr");
            assertEquals("maio falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/05/23")), "mai");
            assertEquals("junho falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/06/23")), "jun");
            assertEquals("julho falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/07/23")), "jul");
            assertEquals("agosto falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/08/23")), "ago");
            assertEquals("setembro falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/09/23")), "set");
            assertEquals("outubro falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/10/23")), "out");
            assertEquals("novembro falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/11/23")), "nov");
            assertEquals("dezembro falhou", UtilCRCDataHora.getMesTextoAbreviado(formatoDataPadrao.parse("01/12/23")), "dez");
        } catch (ParseException ex) {
            fail("Data testes invalida" + ex.getMessage());
        }
    }

    @Test
    public void getMesAnoTextoAbreviado() {
        try {
            SimpleDateFormat formatoDataPadrao = new SimpleDateFormat("dd/MM/yyyy");

            assertEquals("janeiro falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/01/23")), "jan de 23");
            assertEquals("feverierio falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/02/23")), "fev de 23");
            assertEquals("março falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/03/23")), "mar de 23");
            assertEquals("abril falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/04/23")), "abr de 23");
            assertEquals("maio falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/05/23")), "mai de 23");
            assertEquals("junho falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/06/23")), "jun de 23");
            assertEquals("julho falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/07/23")), "jul de 23");
            assertEquals("agosto falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/08/23")), "ago de 23");
            assertEquals("setembro falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/09/23")), "set de 23");
            assertEquals("outubro falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/10/23")), "out de 23");
            assertEquals("novembro falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/11/23")), "nov de 23");
            assertEquals("dezembro falhou", UtilCRCDataHora.getMesAnoTextoAbreviado(formatoDataPadrao.parse("01/12/23")), "dez de 23");
        } catch (ParseException ex) {
            fail("Data testes invalida" + ex.getMessage());
        }
    }

    //@Test
    public void testGetDataSTRFormatoUsuario() {
    }

    //@Test
    public void testSegundosEntre() {
    }

    //@Test
    public void testMileSegundosEntre() {
    }

    //@Test
    public void testGetAgoraString_UtilCRCDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGetAgoraString_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_UtilCRCDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGethoje() {
    }

    //@Test
    public void testIntervaloTempoMileSegundos() { // não fazer nenhuma multiplicação por mil 60 60 e 24 simplesmente o .getTime()
    }

    @Test
    public void testIntervaloTempoSegundos() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora) * 60 segundos (1 minuto)

            long diferencaSegundos;

            diferencaSegundos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO);
            assertEquals(432000, diferencaSegundos);

            diferencaSegundos = UtilCRCDataHora.intervaloTempoSegundos(dataInicial, dataFinal);
            assertEquals(432000, diferencaSegundos);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoMinutos() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora)

            long diferencaMinutos;

            diferencaMinutos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * MINUTOSHORA);
            assertEquals(7200, diferencaMinutos);

            diferencaMinutos = UtilCRCDataHora.intervaloTempoMinutos(dataInicial, dataFinal);
            assertEquals(7200, diferencaMinutos);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoHoras() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas

            long diferencaHoras;

            diferencaHoras = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA);
            assertEquals(120, diferencaHoras);

            diferencaHoras = UtilCRCDataHora.intervaloTempoHoras(dataInicial, dataFinal);
            assertEquals(120, diferencaHoras);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoDias() {

        try {
            Date dataInicial;
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 5);

            long diferencaDias;

            diferencaDias = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA);
            assertEquals(5, diferencaDias);

            diferencaDias = UtilCRCDataHora.intervaloTempoDias(dataInicial, dataFinal);
            assertEquals(5, diferencaDias);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoMeses() {

        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("07/09/2016 12:00:00");

            dataInicial = new Date(dataInicial.getTime());
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 30);

            long diferencaMeses = (dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES;
            assertEquals(1, diferencaMeses);

            diferencaMeses = UtilCRCDataHora.intervaloTempoMeses(dataInicial, dataFinal);
            assertEquals(1, diferencaMeses);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    @Test
    public void testIntervaloTempoAnos() {

        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("07/09/2016 12:00:00");
            Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 365);

            Long diferencaEsperada = 1L;

            Long diferencaAnos = ((dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES) / MESESANO;
            assertEquals(diferencaEsperada, diferencaAnos);

            diferencaAnos = (long) UtilCRCDataHora.intervaloTempoAnos(dataInicial, dataFinal);
            assertEquals(diferencaEsperada, diferencaAnos);

        } catch (ParseException | NumberFormatException t) {
            t.getCause();
        }

    }

    //@Test
    public void testDecrementaMinutos() {
    }

    //@Test
    public void testDecrementarDias() {
    }

    //@Test
    public void testIncrementaDias() {
    }

    //@Test
    public void testIncrementaSegundos() {
    }

    //@Test
    public void testIncrementaHoras() {
    }

    //@Test
    public void testIncrementaMinutos() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundo() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundosDiasUteis() {
    }

    //@Test
    public void testIntervaloTempoDiasHorasMinutosSegundos() {
    }

    //@Test
    public void testIntervaloTempoSegundos_Date_Date() {
    }

    ///@Test
    public void testIntervaloTempoSegundos_Long() {
    }

    //@Test
    public void testIntervarlTempoMinutos() {
    }

    //@Test
    public void testIntervaloTempoHoras_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoHoras_Long() {
    }

    //@Test
    public void testIntervaloTempoDias_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoDias_Long() {
    }

    public void testIntervaloTempoSemanas_Date_Date() throws ParseException {

        SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataInicial = ds.parse("07/09/2016 12:00:00");
        Date dataFinal = UtilCRCDataHora.incrementaDias(dataInicial, 365);

        Long diferencaEsperada = 52L;
        Long diferencaSemanas = ((dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA * 24));
        assertEquals(diferencaEsperada, diferencaSemanas);

        diferencaSemanas = UtilCRCDataHora.intervaloTempoSemanas(dataInicial, dataFinal);
        assertEquals(diferencaEsperada, diferencaSemanas);

    }

    @Test
    public void testIsDiaIgualOuSuperior() {
        try {
            SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInicial = ds.parse("22/01/2020 12:00:00");

            Date dataIgual = ds.parse("22/01/2020 12:00:00");
            Date dataIgual2 = ds.parse("22/01/2020 10:00:00");
            Date dataSuperior = ds.parse("23/01/2020 12:00:00");
            Date dataSuperior3 = ds.parse("22/02/2020 12:00:00");
            Date dataSuperior2 = new Date();
            Date dataInferior = ds.parse("21/01/2020 12:00:00");
            assertTrue("Falha verificando dia igual1", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual));
            assertTrue("Falha verificando dia igual2", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual2));
            assertTrue("Falha verificando dia superior 1", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior));
            assertTrue("Falha verificando dia superior 2", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior2));
            assertTrue("Falha verificando dia superior 3", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior3));
            assertFalse("Falha verificando dia inferior 2", UtilCRCDataHora.isDiaIgualOuSuperior(dataInicial, dataInferior));
        } catch (ParseException ex) {
            Logger.getLogger(UtilCRCDataHoraTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Falhou testando data igual ou superior");
        }
    }

    //@Test
    public void testIntervaloTempoSemanas_Long() {
    }

    //@Test
    public void testIntervaloTempoMeses_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoMeses_Long() {
    }

    //@Test
    public void testIntervaloTempoAnos_Date_Date() {
    }

    //@Test
    public void testIntervaloTempoAnos_Long() {
    }

    //@Test
    public void testIntervaloTempoBaseAnos() {
    }

    //@Test
    public void testIntervaloTempoDiasHorasMinitosSegundos() {
    }

    //@Test
    public void testInterTempContRegSegundos() {
    }

    /**
     * Test of horasEntre method, of class UtilCRCDataHora.
     */
    @Test
    public void testHorasEntre() {
        System.out.println("horasEntre");
        Date pDatainicial = null;
        Date pDatafinal = null;
        double expResult = 0.0;
        double result = UtilCRCDataHora.horasEntre(pDatainicial, pDatafinal);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmSegundos method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmSegundos() {
        System.out.println("quantidadeTempoEmSegundos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmSegundos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmMinutos method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmMinutos() {
        System.out.println("quantidadeTempoEmMinutos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmMinutos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmHoras method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmHoras() {
        System.out.println("quantidadeTempoEmHoras");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmHoras(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmDias method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmDias() {
        System.out.println("quantidadeTempoEmDias");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        boolean contabilizarSemanas = false;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmDias(valor, divisorMaximo, contabilizarSemanas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmMeses method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmMeses() {
        System.out.println("quantidadeTempoEmMeses");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmMeses(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmSemanas method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmSemanas() {
        System.out.println("quantidadeTempoEmSemanas");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmSemanas(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeTempoEmAnos method, of class UtilCRCDataHora.
     */
    @Test
    public void testQuantidadeTempoEmAnos() {
        System.out.println("quantidadeTempoEmAnos");
        long valor = 0L;
        FabTipoQuantidadeTempo divisorMaximo = null;
        Long expResult = null;
        Long result = UtilCRCDataHora.quantidadeTempoEmAnos(valor, divisorMaximo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementaMes method, of class UtilCRCDataHora.
     */
    @Test
    public void testIncrementaMes() {
        System.out.println("incrementaMes");
        Date pData = null;
        int pNumeroMeses = 0;
        Date expResult = null;
        Date result = UtilCRCDataHora.incrementaMes(pData, pNumeroMeses);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarStringDiaMesAnoAtual method, of class UtilCRCDataHora.
     */
    @Test
    public void testGerarStringDiaMesAnoAtual() {
        System.out.println("gerarStringDiaMesAnoAtual");
        String expResult = "";
        String result = UtilCRCDataHora.gerarStringDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarStringDiaMesAnoAtualFormatada method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testGerarStringDiaMesAnoAtualFormatada() {
        System.out.println("gerarStringDiaMesAnoAtualFormatada");
        String expResult = "";
        String result = UtilCRCDataHora.gerarStringDiaMesAnoAtualFormatada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarInteiroDiaMesAnoAtual method, of class UtilCRCDataHora.
     */
    @Test
    public void testGerarInteiroDiaMesAnoAtual() {
        System.out.println("gerarInteiroDiaMesAnoAtual");
        int expResult = 0;
        int result = UtilCRCDataHora.gerarInteiroDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarInteiroDiaMesAnoDataInformada method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testGerarInteiroDiaMesAnoDataInformada() {
        System.out.println("gerarInteiroDiaMesAnoDataInformada");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.gerarInteiroDiaMesAnoDataInformada(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gerarDataDiaMesAnoAtual method, of class UtilCRCDataHora.
     */
    @Test
    public void testGerarDataDiaMesAnoAtual() {
        System.out.println("gerarDataDiaMesAnoAtual");
        Date expResult = null;
        Date result = UtilCRCDataHora.gerarDataDiaMesAnoAtual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDataEmStringFormatada method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteDataEmStringFormatada() {
        System.out.println("converteDataEmStringFormatada");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.converteDataEmStringFormatada(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDataEmStringCorrida method, of class UtilCRCDataHora.
     */
    @Test
    public void testConverteDataEmStringCorrida() {
        System.out.println("converteDataEmStringCorrida");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.converteDataEmStringCorrida(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringDD_MM_YYYYEmData method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteStringDD_MM_YYYYEmData() {
        System.out.println("converteStringDD_MM_YYYYEmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteStringDD_MM_YYYYEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteString_dd_MM_yyEmData method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteString_dd_MM_yyEmData() {
        System.out.println("converteString_dd_MM_yyEmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteString_dd_MM_yyEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteString_dd_MM_yyEmData method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteString_hh_doisPontos_MinutosEmDate() {
        System.out.println("converteString_hh_doisPontos_MinutosEmDate");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteString_dd_MM_yyEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDateEmSTringDD_MM_YY method, of class UtilCRCDataHora.
     */
    @Test
    public void testConverteDateEmSTringDD_MM_YY() {
        System.out.println("converteDateEmSTringDD_MM_YY");
        Date pdata = null;
        String expResult = "";
        String result = UtilCRCDataHora.converteDateEmSTringDD_MM_YY(pdata);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteDateEmSTringDD_MM_YYYY method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteDateEmSTringDD_MM_YYYY() {
        System.out.println("converteDateEmSTringDD_MM_YYYY");
        Date pdata = null;
        String expResult = "";
        String result = UtilCRCDataHora.converteDateEmSTringDD_MM_YYYY(pdata);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringEmDataEHora method, of class UtilCRCDataHora.
     */
    @Test
    public void testConverteStringEmDataEHora() {
        System.out.println("converteStringEmDataEHora");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteStringEmDataEHora(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornaHoraAtual method, of class UtilCRCDataHora.
     */
    @Test
    public void testRetornaHoraAtual() {
        System.out.println("retornaHoraAtual");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.retornaHoraAtual(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDiaIgual method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsDiaIgual() {
        System.out.println("isDiaIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isDiaIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMesFazParteDoIntevalor method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsMesFazParteDoIntevalor() {
        System.out.println("isMesFazParteDoIntevalor");
        Date pMesReferencia = null;
        Date pMesinicial = null;
        Date pMesFinal = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isMesFazParteDoIntevalo(pMesReferencia, pMesinicial, pMesFinal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAnoIgual method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsAnoIgual() {
        System.out.println("isAnoIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isAnoIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMesIgual method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsMesIgual() {
        System.out.println("isMesIgual");
        Date pData1 = null;
        Date pData2 = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isMesIgual(pData1, pData2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMes method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMes() {
        System.out.println("getMes");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getMes(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDia method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetDia() {
        System.out.println("getDia");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getDia(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiaDaSemana method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetDiaDaSemana() {
        System.out.println("getDiaDaSemana");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.getDiaDaSemana(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void getUltimoDiaDoMes() {

        String dataReferencia = "27/02/23";
        SimpleDateFormat dataParser = new SimpleDateFormat("dd/MM/yy");
        try {
            Date data = dataParser.parse(dataReferencia);
            System.out.println(dataParser.format(data));
            data = gerarDataPagamentoTerceirizado(data, 5);
            System.out.println(dataParser.format(data));
            assertEquals("", "28/02/23", dataParser.format(data));
        } catch (ParseException ex) {
            Logger.getLogger(UtilCRCDataHoraTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Date gerarDataPagamentoTerceirizado(Date dataCliente, int diasFuturo) {
        SimpleDateFormat dataParser = new SimpleDateFormat("dd/MM/yy");
        Date dataPrevistaIdeal = UtilCRCDataHora.incrementaDias(dataCliente, diasFuturo);
        System.out.println(dataParser.format(dataPrevistaIdeal));
        if (UtilCRCDataHora.isDataMesmoMes(dataCliente, dataPrevistaIdeal)) {
            return dataPrevistaIdeal;
        } else {
            Date dataFimDoMes = UtilCRCDataHora.getUltimoDiaDoMes(dataCliente);
            return dataFimDoMes;
        }

    }

    /**
     * Test of getHoraString method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetHoraString() {
        System.out.println("getHoraString");
        Date pDAta = null;
        String expResult = "";
        String result = UtilCRCDataHora.getHoraString(pDAta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteString_HH_doisPontos_mm_EmData method, of class
     * UtilCRCDataHora.
     */
    @Test
    public void testConverteString_HH_doisPontos_mm_EmData() {
        System.out.println("converteString_HH_doisPontos_mm_EmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteString_HH_doisPontos_mm_EmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringEmData method, of class UtilCRCDataHora.
     */
    @Test
    public void testConverteStringEmData_String() {
        System.out.println("converteStringEmData");
        String pString = "";
        Date expResult = null;
        Date result = UtilCRCDataHora.converteStringEmData(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of converteStringEmData method, of class UtilCRCDataHora.
     */
    @Test
    public void testConverteStringEmData_String_UtilCRCDataHoraFORMATO_TEMPO() {
        System.out.println("converteStringEmData");
        String pString = "";
        UtilCRCDataHora.FORMATO_TEMPO pFormato = null;
        Date expResult = null;
        Date result = UtilCRCDataHora.converteStringEmData(pString, pFormato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHorarioFazParteDoIntevalo method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsHorarioFazParteDoIntevalo() {
        System.out.println("isHorarioFazParteDoIntevalo");

        assertTrue(UtilCRCDataHora.isHorarioFazParteDoIntevalo("22:00", "22:00", "5:00"));
        assertFalse(UtilCRCDataHora.isHorarioFazParteDoIntevalo("6:00", "22:00", "5:00"));

        assertTrue(UtilCRCDataHora.isHorarioFazParteDoIntevalo("06:00", "05:00", "22:00"));
        assertFalse(UtilCRCDataHora.isHorarioFazParteDoIntevalo("06:00", "22:00", "5:00"));

        assertTrue(UtilCRCDataHora.isHorarioFazParteDoIntevalo("06:00", "06:00", "22:00"));

        assertTrue(UtilCRCDataHora.isHorarioFazParteDoIntevalo("07:00", "06:00", "22:00"));

    }

    /**
     * Test of isMesFazParteDoIntevalo method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsMesFazParteDoIntevalo() {
        System.out.println("isMesFazParteDoIntevalo");
        Date pMesReferencia = null;
        Date pMesinicial = null;
        Date pMesFinal = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isMesFazParteDoIntevalo(pMesReferencia, pMesinicial, pMesFinal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesFormatoHumano method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMesFormatoHumano() {
        System.out.println("getMesFormatoHumano");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getMesFormatoHumano(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAno method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetAno() {
        System.out.println("getAno");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getAno(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesTexto method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMesTexto() {
        System.out.println("getMesTexto");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.getMesTexto(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesTextoAbreviado method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMesTextoAbreviado() {
        System.out.println("getMesTextoAbreviado");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.getMesTextoAbreviado(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesAnoTextoAbreviado method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMesAnoTextoAbreviado() {
        System.out.println("getMesAnoTextoAbreviado");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.getMesAnoTextoAbreviado(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnoAbreviado method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetAnoAbreviado() {
        System.out.println("getAnoAbreviado");
        Date pData = null;
        String expResult = "";
        String result = UtilCRCDataHora.getAnoAbreviado(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHora method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetHora() {
        System.out.println("getHora");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getHora(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinuto method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetMinuto() {
        System.out.println("getMinuto");
        Date pData = null;
        int expResult = 0;
        int result = UtilCRCDataHora.getMinuto(pData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDiaIgualOuInferior method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsDiaIgualOuInferior() {
        System.out.println("isDiaIgualOuInferior");
        Date pDiaReferenciaLimite = null;
        Date pPossivelDiaInferior = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isDiaIgualOuInferior(pDiaReferenciaLimite, pPossivelDiaInferior);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataHora method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetDataHora() {
        System.out.println("getDataHora");
        Date pDia = null;
        Date pHorario = null;
        Date expResult = null;
        Date result = UtilCRCDataHora.getDataHora(pDia, pHorario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalDoDIa method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetFinalDoDIa() {
        System.out.println("getFinalDoDIa");
        Date pDate = null;
        Date expResult = null;
        Date result = UtilCRCDataHora.getFinalDoDIa(pDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUltimoDiaDoMes method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetUltimoDiaDoMes() {
        System.out.println("getUltimoDiaDoMes");
        Date pDdata = null;
        Date expResult = null;
        Date result = UtilCRCDataHora.getUltimoDiaDoMes(pDdata);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimeiroDiaDoMes method, of class UtilCRCDataHora.
     */
    @Test
    public void testGetPrimeiroDiaDoMes() {
        System.out.println("getPrimeiroDiaDoMes");
        Date pDia = null;
        Date expResult = null;
        Date result = UtilCRCDataHora.getPrimeiroDiaDoMes(pDia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDataMesmoMes method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsDataMesmoMes() {
        System.out.println("isDataMesmoMes");
        Date pData = null;
        Date pMesReferencia = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isDataMesmoMes(pData, pMesReferencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDataMesmoMesOuSuperior method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsDataMesmoMesOuSuperior() {
        System.out.println("isDataMesmoMesOuSuperior");
        Date pData = null;
        Date pMesReferencia = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isDataMesmoMesOuSuperior(pData, pMesReferencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDataMesmoMesOuInferior method, of class UtilCRCDataHora.
     */
    @Test
    public void testIsDataMesmoMesOuInferior() {
        System.out.println("isDataMesmoMesOuInferior");
        Date pData = null;
        Date pMesReferencia = null;
        boolean expResult = false;
        boolean result = UtilCRCDataHora.isDataMesmoMesOuInferior(pData, pMesReferencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
