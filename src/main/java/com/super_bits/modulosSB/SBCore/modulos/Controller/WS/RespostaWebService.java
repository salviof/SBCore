/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao.RespostaAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class RespostaWebService extends RespostaAcaoDoSistema {

    @Expose()
    private String retornoJson;
    @Expose
    private Resultado resultadoWS = Resultado.SUCESSO;

    public RespostaWebService() {
        super(null, null);
    }

    public RespostaWebService(Class pTipoRetorno, ItfAcaoDoSistema pAcaoDoSistema) {
        super(pTipoRetorno, pAcaoDoSistema);
    }

    @Override
    public ItfRespostaAcaoDoSistema setRetorno(Object pObjetoResultante) {
        super.setRetorno(pObjetoResultante); //To change body of generated methods, choose Tools | Templates.
        retornoJson = new Gson().toJson(pObjetoResultante);
        return this;
    }

    public String getRetornoJson() {
        return retornoJson;
    }

    public void setRetornoJson(String retornoJson) {
        this.retornoJson = retornoJson;
    }

    @Override
    public void setResultadoWS(Resultado resultadoWS) {
        this.resultadoWS = resultadoWS;

    }

}
