/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreDataHoraCalendarioLocal {

    public static LocalTime gerarLocalTimeByDate(Date pData) {
        if (pData == null) {
            return null;
        }

        LocalTime localDate = Instant.ofEpochMilli(pData.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
        return localDate;
    }

    public static Date gerarDateByLocalTime(LocalTime pLocalTime) {

        return Timestamp.valueOf(pLocalTime.atDate(LocalDate.now()));

    }

    public static Date gerarDateByLocalDate(LocalDate pLocalDate) {
        if (pLocalDate == null) {
            return null;
        }
        Date date = Date.from(pLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;

    }

    public static LocalDate gerarLocalDateByDate(Date pData) {

        LocalDate localDate = Instant.ofEpochMilli(pData.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate;
    }

    public static LocalDateTime gerarLocalDateTimeByDate(Date pData) {
        LocalDateTime localDate = Instant.ofEpochMilli(pData.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return localDate;
    }

}
