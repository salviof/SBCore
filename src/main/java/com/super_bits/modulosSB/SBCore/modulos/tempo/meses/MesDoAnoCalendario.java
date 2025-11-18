/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo.meses;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.tempo.anos.AnoCalendario;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import javax.persistence.Id;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Mês", plural = "Meses")
public class MesDoAnoCalendario extends EntidadeSimples {

    @Id
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private AnoCalendario ano;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraInicio;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DATAHORA)
    private Date dataHoraFinal;

    private int ordenador;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public AnoCalendario getAno() {
        return ano;
    }

    public void setAno(AnoCalendario ano) {
        this.ano = ano;
        int anoAtual = Year.now().getValue();
        LocalDateTime navgadorCalendario = LocalDateTime.of(anoAtual, getId().intValue(), 1, 0, 0);
        navgadorCalendario = navgadorCalendario.with(TemporalAdjusters.firstDayOfMonth());
        dataHoraInicio = Date.from(navgadorCalendario.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime localTimeUltimoDiaMes = navgadorCalendario.with(TemporalAdjusters.lastDayOfMonth());
        localTimeUltimoDiaMes.with(LocalTime.MAX);
        dataHoraFinal = Date.from(localTimeUltimoDiaMes.atZone(ZoneId.systemDefault()).toInstant());
        dataHoraFinal = UtilSBCoreDataHora.getFinalDoDIa(dataHoraFinal);
        ordenador = (ano.getId().intValue() * 356) + getId().intValue();
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFinal() {

        return dataHoraFinal;
    }

    public void setDataHoraFinal(Date dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    @Override
    public String toString() {
        if (getAno() != null && getDataHoraInicio() != null) {
            return getAno().getNome() + getNome();
        }
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public int getOrdenador() {
        return ordenador;
    }

}
