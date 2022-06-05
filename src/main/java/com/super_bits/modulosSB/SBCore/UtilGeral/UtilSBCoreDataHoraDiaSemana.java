/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreDataHoraDiaSemana {

    public static String getDiaDaSemanaTexto(Date pDiaDaSemana) {
        try {
            if (pDiaDaSemana == null) {
                return null;
            }
            Locale local = new Locale("pt", "BR");
            SimpleDateFormat formatador = new SimpleDateFormat("EEEE", local);
            return formatador.format(pDiaDaSemana);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro formantando data", t);
            return null;
        }

    }

    public static DayOfWeek getDiaDaSemana(Date pDiaDaSemana) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(pDiaDaSemana);
        int diaDaSemana = cal1.get(Calendar.DAY_OF_WEEK);
        switch (diaDaSemana) {
            case Calendar.SUNDAY:
                return DayOfWeek.SUNDAY;
            case Calendar.MONDAY:
                //segunda
                return DayOfWeek.MONDAY;
            case Calendar.TUESDAY:
                return DayOfWeek.TUESDAY;
            case Calendar.WEDNESDAY:
                return DayOfWeek.WEDNESDAY;
            case Calendar.THURSDAY:
                return DayOfWeek.THURSDAY;
            case Calendar.FRIDAY:
                return DayOfWeek.FRIDAY;
            case Calendar.SATURDAY:
                return DayOfWeek.SATURDAY;
        }
        return null;
    }

    public static boolean isDiaDaSemana(Date pData, DayOfWeek pDiadaSemana) {
        DayOfWeek diadaSemana = getDiaDaSemana(pData);
        return pDiadaSemana.equals(diadaSemana);
    }

}
