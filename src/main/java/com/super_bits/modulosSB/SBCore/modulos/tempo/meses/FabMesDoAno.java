/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo.meses;

import com.super_bits.modulosSB.SBCore.modulos.tempo.anos.AnoCalendario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author salvio
 */
public enum FabMesDoAno implements ComoFabrica {

    JANEIRO, FEVEREIRO, MARCO, ABRIL, MAIO, JUNHO, JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO;

    private final static Locale br = new Locale("pt", "BR");

    public static MesDoAnoCalendario getRegistro(Date pData) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pData);

        LocalDateTime localDateTime = pData.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        Long year = (long) localDateTime.getYear();

        int mesID = calendar.get(Calendar.MONTH) + 1;
        switch (mesID) {
            case 1:
                return JANEIRO.getRegistro(year);
            case 2:
                return FEVEREIRO.getRegistro(year);

            case 3:
                return MARCO.getRegistro(year);

            case 4:
                return ABRIL.getRegistro(year);

            case 5:
                return MAIO.getRegistro(year);

            case 6:
                return JUNHO.getRegistro(year);

            case 7:
                return JULHO.getRegistro(year);

            case 8:
                return AGOSTO.getRegistro(year);

            case 9:
                return SETEMBRO.getRegistro(year);

            case 10:
                return OUTUBRO.getRegistro(year);

            case 11:
                return NOVEMBRO.getRegistro(year);

            case 12:
                return DEZEMBRO.getRegistro(year);

            default:
                throw new AssertionError();
        }
    }

    public static MesDoAnoCalendario getRegistroMesAtual() {

        return getRegistro(new Date());

    }

    @Override
    public MesDoAnoCalendario getRegistro() {

        MesDoAnoCalendario mes = new MesDoAnoCalendario();
        switch (this) {
            case JANEIRO:
                mes.setId((long) Month.JANUARY.getValue());
                break;
            case FEVEREIRO:
                mes.setId((long) Month.FEBRUARY.getValue());
                break;
            case MARCO:
                mes.setId((long) Month.MARCH.getValue());
                break;
            case ABRIL:
                mes.setId((long) Month.APRIL.getValue());
                break;
            case MAIO:
                mes.setId((long) Month.MAY.getValue());
                break;
            case JUNHO:
                mes.setId((long) Month.JUNE.getValue());
                break;
            case JULHO:
                mes.setId((long) Month.JULY.getValue());
                break;
            case AGOSTO:
                mes.setId((long) Month.AUGUST.getValue());
                break;
            case SETEMBRO:
                mes.setId((long) Month.SEPTEMBER.getValue());
                break;
            case OUTUBRO:
                mes.setId((long) Month.OCTOBER.getValue());
                break;
            case NOVEMBRO:
                mes.setId((long) Month.NOVEMBER.getValue());
                break;
            case DEZEMBRO:
                mes.setId((long) Month.DECEMBER.getValue());
                break;
            default:
                throw new AssertionError(this.name());

        }
        Long anoAtual = Long.valueOf(Year.now().getValue());
        Long mesID = mes.getId();
        LocalDate date = LocalDate.ofYearDay(anoAtual.intValue(), mesID.intValue() * 28);
        mes.setNome(date.getMonth().getDisplayName(TextStyle.FULL, br));

        AnoCalendario anoCalendario = new AnoCalendario(anoAtual);
        mes.setAno(anoCalendario);
        return mes;
    }

    public MesDoAnoCalendario getRegistro(Long pAno) {
        MesDoAnoCalendario mes = getRegistro();

        mes.setAno(new AnoCalendario(pAno));

        return mes;
    }

}
