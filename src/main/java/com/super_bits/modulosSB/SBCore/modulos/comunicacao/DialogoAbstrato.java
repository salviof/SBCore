/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SalvioF
 */
public abstract class DialogoAbstrato extends ItemSimples implements ItfDialogo, Serializable {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    private boolean foiSelado;
    private String codigoSelo;
    private FabStatusComunicacao status;
    private final Date dataHoraDisparo;
    private Date dataHoraResposta;

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
        foiSelado = codigoSelo != null && codigoSelo.isEmpty();
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

}
