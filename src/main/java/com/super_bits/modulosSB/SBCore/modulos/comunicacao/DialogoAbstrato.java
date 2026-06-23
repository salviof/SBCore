/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public abstract class DialogoAbstrato extends EntidadeSimples implements ComoDialogo, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    private boolean foiSelado;
    private String codigoSelo;
    private FabStatusComunicacao status;
    private final Date dataHoraDisparo;
    private Date dataHoraResposta;
    private List<ERPTipoCanalComunicacao> canais = Lists.newArrayList(ERPTipoCanalComunicacao.AUTOMATICO);

    public DialogoAbstrato() {
        dataHoraDisparo = new Date();
    }

    public void setCodigoSelo(String codigoSelo) {
        if (codigoSelo != null) {
            this.codigoSelo = codigoSelo;

        }

    }

    @Override
    public FabStatusComunicacao getStatusComunicacao() {
        if (status == null && isFoiSelado()) {
            status = FabStatusComunicacao.SELADO;
        }
        return status;
    }

    @Override
    public void setStatusComunicacao(FabStatusComunicacao pStatus) {
        status = pStatus;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isFoiSelado() {
        foiSelado = (codigoSelo != null) && (!codigoSelo.isEmpty());
        return foiSelado;
    }

    @Override
    public String getCodigoSelo() {
        return codigoSelo;
    }

    @Override
    public Date getDataHoraDisparo() {
        return dataHoraDisparo;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getDataHoraResposta() {
        return dataHoraResposta;
    }

    @Override
    public List<ERPTipoCanalComunicacao> getCanais() {
        return canais;
    }

    @Override
    public void setCanais(List<ERPTipoCanalComunicacao> pCanais) {
        canais = pCanais;
    }

}
