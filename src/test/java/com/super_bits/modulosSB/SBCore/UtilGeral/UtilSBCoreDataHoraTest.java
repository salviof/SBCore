/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDataHoraTest {

    public final static Long HORASDIA = 24L;
    public final static Long MESESANO = 12L;
    public final static Long MINUTOSHORA = 60L;
    public final static Long SEGUNDOSMINUTO = 60L;
    public final static Long QTDMILISEGUNDOSSEGUNDO = 1000L;
    public final static Long DIASMES = 30L;

    public UtilSBCoreDataHoraTest() {
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
    public void testGetAgoraString_UtilSBCoreDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGetAgoraString_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_UtilSBCoreDataHoraFORMATO_TEMPO() {
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora) * 60 segundos (1 minuto)

            long diferencaSegundos;

            diferencaSegundos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO);
            assertEquals(432000, diferencaSegundos);

            diferencaSegundos = UtilSBCoreDataHora.intervaloTempoSegundos(dataInicial, dataFinal);
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas * 60 minutos(1 hora)

            long diferencaMinutos;

            diferencaMinutos = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * MINUTOSHORA);
            assertEquals(7200, diferencaMinutos);

            diferencaMinutos = UtilSBCoreDataHora.intervaloTempoMinutos(dataInicial, dataFinal);
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5); // 5 dias * 24 horas

            long diferencaHoras;

            diferencaHoras = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA);
            assertEquals(120, diferencaHoras);

            diferencaHoras = UtilSBCoreDataHora.intervaloTempoHoras(dataInicial, dataFinal);
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5);

            long diferencaDias;

            diferencaDias = (dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA);
            assertEquals(5, diferencaDias);

            diferencaDias = UtilSBCoreDataHora.intervaloTempoDias(dataInicial, dataFinal);
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 30);

            long diferencaMeses = (dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES;
            assertEquals(1, diferencaMeses);

            diferencaMeses = UtilSBCoreDataHora.intervaloTempoMeses(dataInicial, dataFinal);
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
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 365);

            Long diferencaEsperada = 1L;

            Long diferencaAnos = ((dataFinal.getTime() - dataInicial.getTime()) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES) / MESESANO;
            assertEquals(diferencaEsperada, diferencaAnos);

            diferencaAnos = (long) UtilSBCoreDataHora.intervaloTempoAnos(dataInicial, dataFinal);
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
        Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 365);

        Long diferencaEsperada = 52L;
        Long diferencaSemanas = ((dataFinal.getTime() - dataInicial.getTime()) / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA * 24));
        assertEquals(diferencaEsperada, diferencaSemanas);

        diferencaSemanas = UtilSBCoreDataHora.intervaloTempoSemanas(dataInicial, dataFinal);
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
            assertTrue("Falha verificando dia igual1", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual));
            assertTrue("Falha verificando dia igual2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataIgual2));
            assertTrue("Falha verificando dia superior 1", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior));
            assertTrue("Falha verificando dia superior 2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior2));
            assertTrue("Falha verificando dia superior 3", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataSuperior3));
            assertFalse("Falha verificando dia inferior 2", UtilSBCoreDataHora.isDiaIgualOuSuperior(dataInicial, dataInferior));
        } catch (ParseException ex) {
            Logger.getLogger(UtilSBCoreDataHoraTest.class.getName()).log(Level.SEVERE, null, ex);
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

}
