/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo.meses;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.tempo.anos.AnoCalendario;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author salvio
 */
public enum FabMesDoAno implements ItfFabrica {

    JANEIRO, FEVEREIRO, MARCO, ABRIL, MAIO, JUNHO, JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO;

    private final static Locale br = new Locale("pt", "BR");

    public static FabMesDoAno getMesAtual() {
        Date date = new Date(); // sua instancia Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int mesID = calendar.get(Calendar.MONTH) + 1;
        switch (mesID) {
            case 1:
                return JANEIRO;
            case 2:
                return FEVEREIRO;
            case 3:
                return MARCO;
            case 4:
                return ABRIL;
            case 5:
                return MAIO;
            case 6:
                return JUNHO;
            case 7:
                return JULHO;
            case 8:
                return AGOSTO;
            case 9:
                return SETEMBRO;
            case 10:
                return OUTUBRO;
            case 11:
                return NOVEMBRO;
            case 12:
                return DEZEMBRO;

            default:
                throw new AssertionError();
        }

    }

    @Override
    public MesDoAnoCalendario getRegistro() {

        MesDoAnoCalendario mes = new MesDoAnoCalendario();
        switch (this) {
            case JANEIRO:
                mes.setId(Month.JANUARY.getValue());
                break;
            case FEVEREIRO:
                mes.setId(Month.FEBRUARY.getValue());
                break;
            case MARCO:
                mes.setId(Month.MARCH.getValue());
                break;
            case ABRIL:
                mes.setId(Month.APRIL.getValue());
                break;
            case MAIO:
                mes.setId(Month.MAY.getValue());
                break;
            case JUNHO:
                mes.setId(Month.JUNE.getValue());
                break;
            case JULHO:
                mes.setId(Month.JULY.getValue());
                break;
            case AGOSTO:
                mes.setId(Month.AUGUST.getValue());
                break;
            case SETEMBRO:
                mes.setId(Month.SEPTEMBER.getValue());
                break;
            case OUTUBRO:
                mes.setId(Month.OCTOBER.getValue());
                break;
            case NOVEMBRO:
                mes.setId(Month.NOVEMBER.getValue());
                break;
            case DEZEMBRO:
                mes.setId(Month.DECEMBER.getValue());
                break;
            default:
                throw new AssertionError(this.name());

        }
        int anoAtual = Year.now().getValue();
        int mesID = mes.getId();
        LocalDate date = LocalDate.ofYearDay(anoAtual, mesID * 28);
        mes.setNome(date.getMonth().getDisplayName(TextStyle.FULL, br));

        AnoCalendario anoCalendario = new AnoCalendario(anoAtual);
        mes.setAno(anoCalendario);
        return mes;
    }

    public MesDoAnoCalendario getMesSetAno(int pAno) {
        MesDoAnoCalendario mes = getRegistro();
        mes.getAno().setId(pAno);
        return mes;
    }

}
