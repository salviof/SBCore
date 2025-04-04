/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.tempo.FabTipoQuantidadeTempo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados) Funções
 * relativas a data e hora dos aplicativos
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public class UtilSBCoreDataHora {

    public final static Long QTD_HORAS_EM1DIA = 24L;
    public final static Long QTD_MESESEM1ANO = 12L;
    public final static Long QTD_MINUTOS_EM1HORA = 60L;
    public final static Long QTD_SEGUNDOS_EM1MINUTO = 60L;
    public final static Long QTD_MILISEGUNDOS_EM1SEGUNDO = 1000L;
    public final static Long QTD_DIASEM1MES = 30L;

    public static enum FORMATO_TEMPO {

        DATA_SISTEMA,
        DATA_USUARIO,
        HORA_SISTEMA,
        HORA_USUARIO,
        DATA_HORA_USUARIO,
        DATA_HORA_EXTENSO,
        DATA_HORA_SISTEMA,
        DATA_HORA_AMERICANO,
        DATA_SEM_SEPARADOR,
        HORA_SEM_SEPARADOR,
        MES,
        PERSONALIZADO,
        ANO_MES_CARTAO,
        /**
         * Exemplo 2023-12-25
         */
        ANO_MES_DIA_POR_TRACO;

        public String getSimpleDateFormatStr() {
            switch (this) {
                case DATA_HORA_SISTEMA:
                    return "dd-MM-yy HH-mm-ss";
                case PERSONALIZADO:
                case DATA_HORA_USUARIO:
                    return "dd/MM/yy HH:mm";
                case DATA_SISTEMA:
                    return "dd-MM-yy";
                case DATA_USUARIO:
                    return "dd/MM/yy";
                case HORA_SISTEMA:
                    return "[HH-mm-ss]";
                case HORA_USUARIO:
                    return "[HH-mm-ss]";
                case DATA_HORA_AMERICANO:
                    return "yyyy-MM-dd HH:mm:ss";
                case DATA_SEM_SEPARADOR:
                    return "ddMMyyyy";
                case HORA_SEM_SEPARADOR:
                    return "HHmmss";

                case MES:

                    return "MMMM";
                case ANO_MES_CARTAO:
                    return "yyy-MM";

                case ANO_MES_DIA_POR_TRACO:
                    return "yyy-MM-dd";
                case DATA_HORA_EXTENSO:
                    return "EE, dd, 'de' MMMM 'às' HH:mm";

                default:
                    throw new AssertionError(this.name());

            }
        }

    }

    public final static SimpleDateFormat datahoraSistemaFr = new SimpleDateFormat("dd-MM-yy [HH-mm-ssss]");
    public final static SimpleDateFormat horaUsuarioFr = new SimpleDateFormat("HH:mm:ss");

    public static String getDataSTRFormatoUsuario(Date pData) {
        return getDatePaternByFormatoTempo(FORMATO_TEMPO.DATA_USUARIO).format(pData);
    }

    public static int segundosEntre(Date pDatainicial, Date pDatafinal) {
        return (int) ((pDatafinal.getTime() - pDatainicial.getTime()) / 1000);
    }

    public static double horasEntre(Date pDatainicial, Date pDatafinal) {
        return (double) ((pDatafinal.getTime() - pDatainicial.getTime()) / 1000 / 60 / 60d);
    }

    public static int mileSegundosEntre(Date pDatainicial, Date pDatafinal) {
        return (int) ((pDatafinal.getTime() - pDatainicial.getTime()));
    }

    private static SimpleDateFormat getDatePaternByFormatoTempo(FORMATO_TEMPO pFormato) {
        return getDatePaternByFormatoTempo(pFormato, null);

    }

    private static SimpleDateFormat getDatePaternByFormatoTempo(FORMATO_TEMPO pFormato, String pPersonalizado) {
        //Locale localPadrao = new Locale("pt", "BR");

        Locale local = new Locale("pt", "BR");
        if (pFormato.equals(FORMATO_TEMPO.PERSONALIZADO)) {
            if (pPersonalizado == null) {
                throw new UnsupportedOperationException("Criou formato de data personalizado sem espeficar o formato");
            }
            try {
                return new SimpleDateFormat(pPersonalizado);

            } catch (Throwable e) {

                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro criando um SimpleDateFormat no UtilCoreDAtaHora", e);
            }
        }
        return new SimpleDateFormat(pFormato.getSimpleDateFormatStr());

    }

    /**
     *
     * Retorna uma String com o horário do momento, de acordo com o formato
     * escolhido
     *
     * @param pFormato Formato Padronizado para Retorno de datas
     * @return o registro temporal do momento em string de acordo com o formato
     * solicitado
     */
    public static String getAgoraString(FORMATO_TEMPO pFormato) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(pFormato);
        return sdf.format(new Date());

    }

    /**
     *
     * Retorna o horario do momento em um formato personalizado
     *
     * @param pformatoPersonalizado
     * @return
     */
    public static String getAgoraString(String pformatoPersonalizado) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(FORMATO_TEMPO.PERSONALIZADO, pformatoPersonalizado);

        return sdf.format(new Date());

    }

    /**
     *
     *
     * @see
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">Veja
     * os Parametros Possíveis</a>
     * @serialData
     * @param pDAta
     * @param pformatoPersonalizado
     * @return
     */
    public static String getDataHoraString(Date pDAta, String pformatoPersonalizado) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(FORMATO_TEMPO.PERSONALIZADO, pformatoPersonalizado);

        return sdf.format(pDAta);

    }

    public static String getDataHoraString(Date pDAta, FORMATO_TEMPO pFormato) {

        SimpleDateFormat sdf = getDatePaternByFormatoTempo(pFormato);

        return sdf.format(pDAta);

    }

    public static String getHoraString(Date pDAta) {

        SimpleDateFormat sdf = getDatePaternByFormatoTempo(FORMATO_TEMPO.HORA_USUARIO);

        return sdf.format(pDAta);

    }

    /**
     *
     * @return Um new Date();
     */
    public static Date gethoje() {

        return new Date();

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de mileSegundos entre um tempo e outro
     */
    public static Long intervaloTempoMileSegundos(Date pDatahoraInicio, Date pDatahoraFim) {
        if (pDatahoraInicio == null || pDatahoraFim == null) {
            return null;
        }
        return pDatahoraFim.getTime() - pDatahoraInicio.getTime();
    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de segundos entre um tempo e outro
     */
    public static long intervaloTempoSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervaloTempoSegundos(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Ingeter de um intervalo de tempo em segundos
     */
    public static long intervaloTempoSegundos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / QTD_MILISEGUNDOS_EM1SEGUNDO;
            return intervalo;
        }
        return 0;
    }

    /**
     *
     * @param pDatahoraInicio
     * @param pDatahoraFim
     * @return intervalo entre duas datas em minutos
     */
    public static Long intervaloTempoMinutos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervarlTempoMinutos(diferenca);
        } else {
            return 0l;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Inteiro de quantidade de tempo em minutos da um intervalo de
     * tempo entre duas datas
     */
    public static long intervarlTempoMinutos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_SEGUNDOS_EM1MINUTO);
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de horas entre duas datas
     */
    public static long intervaloTempoHoras(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervaloTempoHoras(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor inteiro de horas que um intervalor de tempo Long
     * representa
     *
     */
    public static long intervaloTempoHoras(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_SEGUNDOS_EM1MINUTO * QTD_MINUTOS_EM1HORA);
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return Retorna o intervalo de tempo em dias de uma data inicial até uma
     * data final
     */
    public static long intervaloTempoDias(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoDias(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor inteiro de dias que correspondente a um intervalo de
     * tempo Long informado no parâmetro
     */
    public static long intervaloTempoDias(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_SEGUNDOS_EM1MINUTO * QTD_MINUTOS_EM1HORA * QTD_HORAS_EM1DIA);
            return intervalo;
        }
        return 0;
    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return quantidade em semanas entre duas datas
     */
    public static long intervaloTempoSemanas(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoSemanas(diferenca);
        } else {
            return 0L;
        }
    }

    /**
     *
     * @param pIntervalo
     * @return diferença de tempo em semanas de um dado intervalo de tempo Long
     * passado como parâmetro
     */
    public static long intervaloTempoSemanas(Long pIntervalo) {

        Long intervalo;
        if (pIntervalo != null) {
            intervalo = pIntervalo / (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_HORAS_EM1DIA * 7);
            return intervalo;
        }
        return 0L;
    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return quantidade de meses entre duas datas
     */
    public static long intervaloTempoMeses(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoMeses(diferenca);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param pIntervaloTempo
     * @return Valor inteiro que corresponde a Meses de um intervalo de tempo
     * Long passado como parâmetro
     */
    public static long intervaloTempoMeses(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTD_HORAS_EM1DIA * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO) / QTD_DIASEM1MES;
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return quantidade de meses entre duas datas
     */
    public static long intervaloTempoAnos(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoAnos(diferenca);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor Inteiro correspondente a anos de um intervalo de tempo
     * Long passado como parâmetro
     */
    public static long intervaloTempoAnos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = ((pIntervaloTempo) / (QTD_HORAS_EM1DIA * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO) / QTD_DIASEM1MES) / QTD_MESESEM1ANO;
            return intervalo;
        }
        return 0;
    }

    public static List<Long> intervaloTempoBaseAnos(Long pIntervaloTempo) {

        List<Long> intevalos = new ArrayList<>();

        Long meses = (intervaloTempoAnos(pIntervaloTempo) % QTD_MESESEM1ANO) + intervaloTempoMeses(pIntervaloTempo);
        intevalos.add(meses);
        return intevalos;
    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna um Array com de integer sendo anos->[0] meses->=[1] e
     * assim sucessivamente
     */
    public static Integer[] intervaloTempoDiasHorasMinitosSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {

            Integer[] resp = new Integer[6];

            resp[5] = new Long((diferenca / QTD_MILISEGUNDOS_EM1SEGUNDO) % QTD_SEGUNDOS_EM1MINUTO).intValue(); // segundos
            resp[4] = new Long(diferenca / (QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO) % QTD_MINUTOS_EM1HORA).intValue(); // Minutos
            resp[3] = new Long(diferenca / (QTD_SEGUNDOS_EM1MINUTO * QTD_MINUTOS_EM1HORA * QTD_MILISEGUNDOS_EM1SEGUNDO) % QTD_HORAS_EM1DIA).intValue(); // Horas
            resp[2] = new Long(diferenca / (QTD_HORAS_EM1DIA * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO)).intValue();// Dias
            resp[1] = new Long(diferenca / (QTD_HORAS_EM1DIA * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO) / QTD_DIASEM1MES).intValue(); // Meses
            resp[0] = new Long(((diferenca) / (QTD_HORAS_EM1DIA * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO) / QTD_DIASEM1MES) / QTD_MESESEM1ANO).intValue(); // Anos
            return resp;
        } else {
            return null;
        }
    }

    public static Long quantidadeTempoEmSegundos(long valor, FabTipoQuantidadeTempo divisorMaximo) {
        switch (FabTipoQuantidadeTempo.SEGUNDOS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                return (valor / 1000) % 60;
            case 0:
                return (valor / QTD_MILISEGUNDOS_EM1SEGUNDO);
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmMinutos(long valor, FabTipoQuantidadeTempo divisorMaximo) {
        switch (FabTipoQuantidadeTempo.MINUTOS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                return valor / (60 * 1000) % 60;
            case 0:
                return (valor / QTD_MILISEGUNDOS_EM1SEGUNDO);
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmHoras(long valor, FabTipoQuantidadeTempo divisorMaximo) {
        switch (FabTipoQuantidadeTempo.HORAS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                //um dia em milesegundos
                return Math.abs(((valor / (60 * 60 * 1000))) % 24);
            case 0:
                return (valor / QTD_MILISEGUNDOS_EM1SEGUNDO);
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmDias(long valor, FabTipoQuantidadeTempo divisorMaximo, boolean contabilizarSemanas) {
        switch (FabTipoQuantidadeTempo.DIAS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                long qtdAnos = (365 * 24L * 60L * 60L * 1000L);
                long qtdMeses = (30 * 24L * 60L * 60L * 1000L);
                if (valor > qtdAnos) {
                    long resultado = Math.abs(valor / (365 * 24L * 60L * 60L * 1000L));
                    if (!contabilizarSemanas) {
                        return resultado;
                    } else {
                        return resultado % 7;
                    }

                }

                if (valor > qtdMeses) {
                    long resultado = Math.abs(valor / (30 * 24L * 60L * 60L * 1000L));
                    if (!contabilizarSemanas) {
                        return resultado;
                    } else {
                        return resultado % 7;
                    }
                }

                long resultado = Math.abs(valor / (24L * 60L * 60L * 1000L));
                if (!contabilizarSemanas) {
                    return resultado;
                } else {
                    return resultado % 7;
                }

            // se quantidade maior que quantidade de anos
            // se quantidade menor que quantidade de meses
            case 0:
                return (valor / QTD_MILISEGUNDOS_EM1SEGUNDO);
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmMeses(long valor, FabTipoQuantidadeTempo divisorMaximo) {

        switch (FabTipoQuantidadeTempo.MESES.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                return quantidadeTempoEmSegundos(valor, FabTipoQuantidadeTempo.SEGUNDOS) % 60; // retornando tempo em segundos correto. porém.. não é o esperado para este caso
            case 0:
                return (valor / QTD_MILISEGUNDOS_EM1SEGUNDO); // implementar este código
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmSemanas(long valor, FabTipoQuantidadeTempo divisorMaximo) {

        switch (FabTipoQuantidadeTempo.SEMANAS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                //Long semanas = (valor / ((24L * 60L * 60L * 1000L))) % 7L;
                Long semanas = intervaloTempoDias(valor) % 7;
                return semanas;
            case 0:
                return Math.abs(valor / (24L * 60L * 60L * 1000L * 7L));
            case -1:
                return 0L;
        }
        return null;
    }

    public static Long quantidadeTempoEmAnos(long valor, FabTipoQuantidadeTempo divisorMaximo) {

        switch (FabTipoQuantidadeTempo.ANOS.maiorQueMedidaDeTempo(divisorMaximo)) {
            case 1:
                return Math.abs(valor / (365L * 24L * 60L * 60L * 1000L));
            case 0:
                return Math.abs(valor / (365L * 24L * 60L * 60L * 1000L));
            case -1:
                return 0L;
        }
        return null;

    }

    /**
     *
     * Decrementa a quantidade de minutos especificada nos parametros apartir de
     * um objeto Date
     *
     * @param pData Data referencia
     * @param pMinutos Quantidade de minutos para decrementar
     * @return
     */
    public static Date decrementaMinutos(Date pData, int pMinutos) {
        if (pMinutos == 0) {
            return pData;
        }
        long novadata = pData.getTime() - (pMinutos * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO);

        return new Date(novadata);
    }

    /**
     *
     * Decrementa a partir de uma Data obtida, o número de dias enviado,
     * preservando os minutos, e segundos
     *
     * @param pData Data de Referencia
     * @param pDias Número de dias que será decrementado
     * @return A data decrementada
     */
    public static Date decrementarDias(Date pData, int pDias) {
        if (pDias == 0) {
            return pData;
        }

        long novadata;

        novadata = novadata = pData.getTime() - pDias * (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_SEGUNDOS_EM1MINUTO * QTD_MINUTOS_EM1HORA * QTD_HORAS_EM1DIA);
        return new Date(novadata);

    }

    /**
     *
     * @param pData Data de referência
     * @param pNumeroDias numero de dias para incrementar
     * @return
     */
    public static Date incrementaDias(Date pData, int pNumeroDias) {
        if (pNumeroDias == 0) {
            return pData;
        }
        long novadata;

        long dataAntiga = pData.getTime();
        long incremento = (pNumeroDias * (QTD_MILISEGUNDOS_EM1SEGUNDO * QTD_SEGUNDOS_EM1MINUTO * QTD_MINUTOS_EM1HORA * QTD_HORAS_EM1DIA));
        novadata = (dataAntiga + incremento);
        Date dataRetorno = new Date(novadata);
        return dataRetorno;
    }

    /**
     *
     * @param pData Data de referência
     * @param pNumeroMeses numero de dias para incrementar
     * @return
     */
    public static Date incrementaMes(Date pData, int pNumeroMeses) {
        if (pNumeroMeses == 0) {
            return pData;
        }

        Date novadata = DateUtils.addMonths(pData, pNumeroMeses);

        return novadata;
    }

    /**
     *
     * @param pData Data de referência
     * @param pNumeroMeses numero de dias para incrementar
     * @return
     */
    public static Date decrementaMes(Date pData, int pNumeroMeses) {
        if (pNumeroMeses == 0) {
            return pData;
        }

        Date novadata = DateUtils.addMonths(pData, pNumeroMeses * -1);

        return novadata;
    }

    public static long interTempContRegSegundos(Date pDataInicial, Date pDataFinal) {

        long diferencaSegundos = intervaloTempoSegundos(pDataInicial, pDataFinal);
        while (diferencaSegundos > 59 || diferencaSegundos % 60 == 0) {
            diferencaSegundos--;
        }
        return diferencaSegundos;
    }

    /**
     *
     * @param pData Data referência
     * @param pSegundos Segundos para incrementar
     * @return
     */
    public static Date incrementaSegundos(Date pData, int pSegundos) {
        if (pSegundos == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pSegundos * QTD_MILISEGUNDOS_EM1SEGUNDO;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data referência
     * @param pHoras Quantidade de horas para incrementar
     * @return
     */
    public static Date incrementaHoras(Date pData, int pHoras) {
        if (pHoras == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pHoras * QTD_MINUTOS_EM1HORA * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data referencia
     * @param pMinutos Quantidade de minutos para incrementar
     * @return
     */
    public static Date incrementaMinutos(Date pData, int pMinutos) {
        if (pMinutos == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pMinutos * QTD_SEGUNDOS_EM1MINUTO * QTD_MILISEGUNDOS_EM1SEGUNDO;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data de referencia
     * @param pDiaHoraMinutoSegundo Matriz de inteiros, contendo nesta ordem 0->
     * Dias 1 Horas e assim sucessivamente
     * @return Nova data com incremento
     */
    public static Date incrementaDiaHorasMinutosSegundo(Date pData, Integer[] pDiaHoraMinutoSegundo) {
        Date novadata = pData;
        System.out.println("incrementando" + novadata + " dia" + pDiaHoraMinutoSegundo[0] + " horas" + pDiaHoraMinutoSegundo[1] + "minutos" + pDiaHoraMinutoSegundo[2] + "segundos" + pDiaHoraMinutoSegundo[3]);
        novadata = incrementaDias(novadata, pDiaHoraMinutoSegundo[0]);
        novadata = incrementaHoras(novadata, pDiaHoraMinutoSegundo[1]);
        novadata = incrementaMinutos(novadata, pDiaHoraMinutoSegundo[2]);
        novadata = incrementaSegundos(novadata, pDiaHoraMinutoSegundo[3]);
        return novadata;
    }

    public static Date incrementaDiaHorasMinutosSegundosDiasUteis(Date pData, Integer[] pDiaHoraMinutoSegundo) {

        Date novadata = pData;

        novadata = incrementaDiaHorasMinutosSegundo(pData, pDiaHoraMinutoSegundo);
        int feriados = 0;
        for (Date dataRef = pData; dataRef.getTime() < novadata.getTime(); dataRef = incrementaDias(pData, 1)) {
            if (dataRef.getDay() == 0 || dataRef.getDay() == 6) {
                feriados++;
            }
        }

        novadata = incrementaDias(pData, feriados);
        return novadata;
    }

    /**
     *
     * DESCOBRE A DATA ATUAL SEM FORMATAÇÃO
     *
     * Ex: 29112016
     *
     *
     * @return DIA, MES E ANO ATUAL
     */
    public static String gerarStringDiaMesAnoAtual() {

        GregorianCalendar calendar = new GregorianCalendar();

        int dia = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);

        String data = "" + dia + mes + ano;

        return data;

    }

    /**
     *
     * DESCOBRE A DATA ATUAL COM FORMATAÇÃO
     *
     * Ex: 29/11/2016
     *
     *
     * @return DIA, MES E ANO ATUAL
     */
    public static String gerarStringDiaMesAnoAtualFormatada() {

        GregorianCalendar calendar = new GregorianCalendar();

        int dia = calendar.get(Calendar.DATE);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);

        String data = "" + dia + "/" + mes + "/" + ano;

        return data;

    }

    /**
     *
     * DESCOBRE A DATA ATUAL SEM FORMATAÇÃO
     *
     * Ex: 29112016
     *
     * @return DIA, MES E ANO ATUAL
     */
    public static int gerarInteiroDiaMesAnoAtual() {

        String textoData = gerarStringDiaMesAnoAtual();

        int numData = Integer.parseInt(textoData);

        return numData;

    }

    /**
     *
     * DESCOBRE A DATA ATUAL SEM FORMATAÇÃO
     *
     * Ex: 29112016
     *
     * @return DIA, MES E ANO ATUAL EM UM NUMERO INTEIRO
     */
    public static int gerarInteiroDiaMesAnoDataInformada(Date pData) {

        String textoData = converteDataEmStringCorrida(pData);

        int numData = Integer.parseInt(textoData);

        return numData;

    }

    /**
     *
     * DESCOBRE A DATA ATUAL COM HORA ZERADA
     *
     * Ex: Tue Nov 29 00:00:00 BRST 2016
     *
     *
     * @return DIA, MES E ANO
     */
    public static Date gerarDataDiaMesAnoAtual() {

        Date dataFormatada = null;
        String formato, textoData;

        try {

            textoData = gerarStringDiaMesAnoAtualFormatada();
            formato = "dd/MM/yyyy";

            SimpleDateFormat formatador = new SimpleDateFormat(formato);

            dataFormatada = formatador.parse(textoData);

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel gerar a Data formatada!", t);

        }

        return dataFormatada;
    }

    /**
     *
     * DEVOLVE DATA FORMATADA
     *
     * Ex: ENTRADA Tue Nov 29 05:09:37 BRST 2016 - SAIDA 29/11/2016
     *
     *
     * @param pData DATA A CONVERTER
     * @return DATA ATUAL
     */
    public static String converteDataEmStringFormatada(Date pData) {

        String dataFormatada;

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        dataFormatada = formatador.format(pData);

        return dataFormatada;

    }

    /**
     *
     * DEVOLVE DATA EM TEXTO CORRIDO SEM / OU - OU ESPAÇOS
     *
     * Ex: ENTRADA Tue Nov 29 05:09:37 BRST 2016 - SAIDA 29/11/2016
     *
     *
     * @param pData DATA A CONVERTER
     * @return DATA ATUAL
     */
    public static String converteDataEmStringCorrida(Date pData) {

        String dataFormatada;
        char[] auxiliar;
        ArrayList lista = new ArrayList();

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        dataFormatada = formatador.format(pData);

        auxiliar = dataFormatada.toCharArray();

        dataFormatada = "";

        for (int i = 0; i < auxiliar.length; i++) {
            lista.add(auxiliar[i]);
            if ((!lista.get(i).equals('/')) && (!lista.get(i).equals('-')) && (!lista.get(i).equals(' '))) {
                dataFormatada += lista.get(i);
            }
        }

        return dataFormatada;

    }

    /**
     *
     * DEVOLVE DATE COM HORA ZERADA
     *
     * Ex: ENTRADA 29/11/2016 - SAIDA Tue Nov 29 00:00:00 BRST 2016
     *
     * @param pString DATA NO FORMATO A CONVERTER
     * @return DATA ATUAL
     */
    public static Date converteStringDD_MM_YYYYEmData(String pString) {
        Date dataConvertida;
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
            return null;
        }
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            dataConvertida = formatador.parse(pString);
            return dataConvertida;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!" + pString, t);
            return null;
        }
    }

    /**
     * Converte 16/05/84 em um Date contendo apenas o registro deste dia
     * SimpleDateFormat: dd/MM/yy
     *
     *
     *
     * @param pString Exemplo: ENTRADA 29/11/16
     * @return DATA ATUAL
     */
    public static Date converteString_dd_MM_yyEmData(String pString) {
        Date dataConvertida;
        try {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
                return null;
            }
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
            dataConvertida = formatador.parse(pString);
            return dataConvertida;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    /**
     * Converte hora e minito, em um Date contendo apenas hora e minuto
     * SimpleDateFormat: HH:hh
     *
     * @see SimpleDateFormat
     * @param pString Exemplo: 16:20
     * @return Date representando Hora e minuto
     */
    public static Date converteString_HH_doisPontos_mm_EmData(String pString) {
        Date dataConvertida;
        try {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pString)) {
                return null;
            }
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            dataConvertida = formatador.parse(pString);
            return dataConvertida;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    public static String converteDateEmSTringDD_MM_YY(Date pdata) {

        try {
            if (pdata == null) {
                return null;
            }
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
            return formatador.format(pdata);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    public static String converteDateEmSTringDD_MM_YYYY(Date pdata) {

        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            return formatador.format(pdata);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    /**
     * @param pString '29/11/2016 12:30:15'
     * @return Registro Date() -> Tue Nov 29 12:30:15 BRST 2016
     */
    public static Date converteStringEmDataEHora(String pString) {

        Date dataConvertida;

        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataConvertida = formatador.parse(pString);
            return dataConvertida;

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;

        }

    }

    /**
     * @param pString '29/11/2016'
     * @return Registro Date() -> Tue Nov 29 12:30:15 BRST 2016
     */
    public static Date converteStringEmData(String pString) {
        Date dataConvertida;
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            dataConvertida = formatador.parse(pString);
            return dataConvertida;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    public static Date converteStringEmData(String pString, FORMATO_TEMPO pFormato) {
        Date dataConvertida;
        try {
            SimpleDateFormat formatador = new SimpleDateFormat(pFormato.getSimpleDateFormatStr());
            dataConvertida = formatador.parse(pString);
            return dataConvertida;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel converter a String em Data!", t);
            return null;
        }
    }

    /**
     *
     * RECEBE UMA DATE E DEVOLVE UM TEXTO COM HORA ATUAL
     *
     * Ex: SAIDA 13:17:43 HORAS:MINUTOS:SEGUNDOS
     *
     * @param pData DATA QUE DESEJA SABER HORAS
     * @return HORA ATUAL
     */
    public static String retornaHoraAtual(Date pData) {

        String horaObtida;

        try {

            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
            horaObtida = formatador.format(pData);

            return horaObtida;

        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possivel obter hora atual!", t);
            return null;
        }

    }

    public static boolean isDiaIgual(Date pData1, Date pData2) {
        if (pData1 == null || pData2 == null) {
            //False por questão filosófica de não existir dia nulo
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(pData1);
        cal2.setTime(pData2);

        return cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

    }

    /**
     *
     * @param pHorarioReferencia Ex: 22:30
     * @param pHorarioInicial ex: 22:00
     * @param pHorarioFinal ex: 23:00
     * @return positivo se o horario deferencia estiver entre o horario inicial
     * e final, caso o horario inicial seja maior que o horário final, deve
     * retornar true considerando que o horário ultrapassa um intervalo durante
     * a madrugada, exemplo: inicial 22:00 final as 05:00, retonra true para
     * 22:30 e 3:00 e falso para 21:00
     */
    public static boolean isHorarioFazParteDoIntevalo(String pHorarioReferencia, String pHorarioInicial, String pHorarioFinal) {
        SimpleDateFormat FORMATADOR_HORA_MINUTOS = new SimpleDateFormat("HH:mm");

        Calendar horarioInicial = Calendar.getInstance();
        try {
            horarioInicial.setTime(FORMATADOR_HORA_MINUTOS.parse(pHorarioInicial));

            Calendar horarioFinal = Calendar.getInstance();
            horarioFinal.setTime(FORMATADOR_HORA_MINUTOS.parse(pHorarioFinal));
            Calendar horarioReferencia = Calendar.getInstance();
            horarioReferencia.setTime(FORMATADOR_HORA_MINUTOS.parse(pHorarioReferencia));

            boolean horarioMadruga = horarioFinal.before(horarioInicial);
            if (horarioInicial.equals(horarioReferencia)) {
                return true;
            }
            if (horarioFinal.equals(horarioReferencia)) {
                return false;
            }
            if (!horarioMadruga) {
                return (horarioReferencia.after(horarioInicial) && horarioReferencia.before(horarioFinal));
            } else {
                if (horarioReferencia.before(horarioInicial)) {
                    return horarioReferencia.before(horarioFinal);
                }
                if (horarioReferencia.after(horarioInicial)) {
                    return horarioReferencia.before(horarioFinal);
                }
            }
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean isMesFazParteDoIntevalo(Date pMesReferencia, Date pMesinicial, Date pMesFinal) {
        if (pMesinicial == null || pMesFinal == null) {
            //False por questão filosófica de não existir mês nulo
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar calReferencia = Calendar.getInstance();
        cal1.setTime(pMesinicial);
        cal2.setTime(pMesFinal);
        calReferencia.setTime(pMesReferencia);

        int anoReferencia = calReferencia.get(Calendar.YEAR);
        int mesREferencia = calReferencia.get(Calendar.MONTH);

        int anoinicial = cal1.get(Calendar.YEAR);
        int anoFinal = cal2.get(Calendar.YEAR);

        int mesInicial = cal1.get(Calendar.MONTH);
        int mesFinal = cal2.get(Calendar.MONTH);

        if (anoReferencia >= anoinicial && anoReferencia <= anoFinal) {
            if (anoReferencia == anoFinal) {
                if (mesREferencia <= mesFinal) {
                    return true;
                }
            }
            return anoReferencia < anoFinal;

        }
        return false;

    }

    public static boolean isAnoIgual(Date pData1, Date pData2) {
        if (pData1 == null || pData2 == null) {
            //False por questão filosófica de não existir mês nulo
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(pData1);
        cal2.setTime(pData2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

    }

    public static boolean isMesIgual(Date pData1, Date pData2) {
        if (pData1 == null || pData2 == null) {
            //False por questão filosófica de não existir mês nulo
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(pData1);
        cal2.setTime(pData2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }

    /**
     *
     * @param pData 10/01/22
     * @return (0) ATENÇÃO, Mes modelo Api calendario, onde 0 é janeiro, 11 é
     * dezembro.
     */
    public static int getMes(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.MONTH);
    }

    /**
     *
     * @param 10/01/22
     * @return 1
     */
    public static int getMesFormatoHumano(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.MONTH) + 1;
    }

    public static int getAno(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.YEAR);
    }

    public static String getMesTexto(Date pData) {

        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("MMMM", local);
        return dateFormat.format(pData);
    }

    public static String getMesTextoAbreviado(Date pData) {

        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("MMM", local);
        return dateFormat.format(pData);
    }

    public static String getMesAnoTextoAbreviado(Date pData) {

        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("MMM 'de' yy", local);
        return dateFormat.format(pData);
    }

    public static String getAnoAbreviado(Date pData) {

        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("yy", local);
        return dateFormat.format(pData);
    }

    public static int getDia(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHora(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinuto(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        return cal1.get(Calendar.MINUTE);
    }

    public static String getDiaDaSemana(Date pData) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pData);
        int diaDaSemana = cal1.get(Calendar.DAY_OF_WEEK);

        switch (diaDaSemana) {
            case Calendar.SUNDAY:
                return "Domingo";
            case Calendar.MONDAY:
                return "Segunda";
            case Calendar.TUESDAY:
                return "Terça";
            case Calendar.WEDNESDAY:
                return "Quarta";
            case Calendar.THURSDAY:
                return "Quinta";
            case Calendar.FRIDAY:
                return "Sexta";
            case Calendar.SATURDAY:
                return "Sábado";
        }

        return null;
    }

    public static boolean isDiaIgualOuInferior(Date pDiaReferenciaLimite, Date pPossivelDiaInferior) {
        if (pDiaReferenciaLimite == null || pPossivelDiaInferior == null) {
            //False por questão filosófica de não existir dia nulo
            return false;
        }

        Calendar dataHoraReferenciaLimite = Calendar.getInstance();
        Calendar dataHoraPossivelDiaIterior = Calendar.getInstance();
        dataHoraReferenciaLimite.setTime(pDiaReferenciaLimite);
        dataHoraPossivelDiaIterior.setTime(pPossivelDiaInferior);

        if (dataHoraReferenciaLimite.get(Calendar.DAY_OF_MONTH) == dataHoraPossivelDiaIterior.get(Calendar.DAY_OF_MONTH)
                && dataHoraReferenciaLimite.get(Calendar.YEAR) == dataHoraPossivelDiaIterior.get(Calendar.YEAR)
                && dataHoraReferenciaLimite.get(Calendar.MONTH) == dataHoraPossivelDiaIterior.get(Calendar.MONTH)) {
            return true;
        }
        if (dataHoraPossivelDiaIterior.get(Calendar.YEAR) > dataHoraReferenciaLimite.get(Calendar.YEAR)) {
            return false;
        }
        if (dataHoraPossivelDiaIterior.get(Calendar.YEAR) < dataHoraReferenciaLimite.get(Calendar.YEAR)) {

            return true;
        }
        int diaDoAnoReferenciaLimite = dataHoraReferenciaLimite.get(Calendar.DAY_OF_YEAR);
        int diaDoAnoPossivelDiaAnterior = dataHoraPossivelDiaIterior.get(Calendar.DAY_OF_YEAR);
        return diaDoAnoPossivelDiaAnterior < diaDoAnoReferenciaLimite;

    }

    public static boolean isDiaIgualOuSuperior(Date pDiaReferenciaLimite, Date pPossivelDiaposterior) {
        if (pDiaReferenciaLimite == null || pPossivelDiaposterior == null) {
            //False por questão filosófica de não existir dia nulo
            return false;
        }

        Calendar diaReferenciaLimite = Calendar.getInstance();
        Calendar possivelDiaSuperior = Calendar.getInstance();
        diaReferenciaLimite.setTime(pDiaReferenciaLimite);
        possivelDiaSuperior.setTime(pPossivelDiaposterior);

        if (diaReferenciaLimite.get(Calendar.DAY_OF_MONTH) == possivelDiaSuperior.get(Calendar.DAY_OF_MONTH)
                && diaReferenciaLimite.get(Calendar.YEAR) == possivelDiaSuperior.get(Calendar.YEAR)
                && diaReferenciaLimite.get(Calendar.MONTH) == possivelDiaSuperior.get(Calendar.MONTH)) {
            return true;
        }

        if (possivelDiaSuperior.get(Calendar.YEAR) < diaReferenciaLimite.get(Calendar.YEAR)) {
            return false;
        }
        if (possivelDiaSuperior.get(Calendar.YEAR) > diaReferenciaLimite.get(Calendar.YEAR)) {
            return true;
        }
        int diaDoAnoReferenciaLimite = diaReferenciaLimite.get(Calendar.DAY_OF_YEAR);
        int diaDoAnoPossivelDiaSuperor = possivelDiaSuperior.get(Calendar.DAY_OF_YEAR);
        return diaDoAnoPossivelDiaSuperor > diaDoAnoReferenciaLimite;

    }

    public static Date getDataHora(Date pDia, Date pHorario) {

        Calendar diaCalendar = Calendar.getInstance();
        diaCalendar.setTime(pDia);

        Calendar horarioCalendar = Calendar.getInstance();
        horarioCalendar.setTime(pHorario);

        Calendar dataHoraCalendario = Calendar.getInstance();

        int year = diaCalendar.get(Calendar.YEAR);
        int month = diaCalendar.get(Calendar.MONTH);
        int day = diaCalendar.get(Calendar.DATE);

        int hora = horarioCalendar.get(Calendar.HOUR_OF_DAY);
        int minuto = horarioCalendar.get(Calendar.MINUTE);

        dataHoraCalendario.set(year, month, day, hora, minuto);

        return dataHoraCalendario.getTime();
    }

    public static Date getFinalDoDIa(Date pDate) {

        LocalDateTime localDateTime = Instant.ofEpochMilli(pDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getUltimoDiaDoMes(Date pDdata) {

        LocalDate calendario = UtilSBCoreDataHoraCalendarioLocal.gerarLocalDateByDate(pDdata);
        calendario = calendario.with(TemporalAdjusters.lastDayOfMonth());
        String dataVencimento = calendario.format(DateTimeFormatter.ISO_DATE);
        SimpleDateFormat novadata = new SimpleDateFormat("yyyy-MM-dd");
        Date dataUltimoDia;
        try {
            dataUltimoDia = novadata.parse(dataVencimento);
            return dataUltimoDia;
        } catch (ParseException ex) {
            Logger.getLogger(UtilSBCoreDataHora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date getPrimeiroDiaDoMes(Date pDia) {

        int mesVencimento = UtilSBCoreDataHora.getMes(pDia) + 1;
        int ano = UtilSBCoreDataHora.getAno(pDia);
        Date dataVencimento = converteStringEmData(1 + "/" + mesVencimento + "/" + ano);
        return dataVencimento;
    }

    public static boolean isDataMesmoMes(Date pData, Date pMesReferencia) {
        int mesData = getMes(pData);
        int mesReferencia = getMes(pMesReferencia);
        int ano = getAno(pData);
        int anoReferencia = getAno(pMesReferencia);
        return (mesData == mesReferencia && ano == anoReferencia);
    }

    /**
     *
     * @param pData DAta questionavel
     * @param pMesReferencia data referencia
     * @return
     */
    public static boolean isDataMesmoMesOuSuperior(Date pData, Date pMesReferencia) {
        if (pData == null || pMesReferencia == null) {
            return false;
        }

        int mesReferencia = getMes(pMesReferencia);
        int anoReferencia = getAno(pMesReferencia);
        int anoData = getAno(pData);
        int mesData = getMes(pData);

        if (anoData > anoReferencia) {
            return true;
        }
        if (anoData < anoReferencia) {
            return false;
        }

        return (mesData >= mesReferencia);
    }

    /**
     *
     * @param pData DAta questionavel
     * @param pMesReferencia data referencia
     * @return
     */
    public static boolean isDataMesmoMesOuInferior(Date pData, Date pMesReferencia) {
        if (pData == null || pMesReferencia == null) {
            return false;
        }

        int mesReferencia = getMes(pMesReferencia);
        int anoReferencia = getAno(pMesReferencia);
        int anoData = getAno(pData);
        int mesData = getMes(pData);
        if (anoData < anoReferencia) {
            return true;
        }
        if (anoData > anoReferencia) {
            return false;
        }
        return (mesData <= mesReferencia);
    }
}
