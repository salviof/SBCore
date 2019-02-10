/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.calculos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

/**
 *
 * @author desenvolvedor
 */
public class ValorLogicoCalculoGenerico implements ItfCalculos {

    private final ItfCampoInstanciado campoInst;

    public ValorLogicoCalculoGenerico(ItfCampoInstanciado pCampo) {
        campoInst = pCampo;
    }

    @Override
    public Object getValor(Object... pEntidade) {
        SBCore.getCentralDeMensagens().enviarMsgAvisoAoDesenvolvedor("O valor logico para " + getCampoInst().getLabel() + "Não foi definido");
        return null;
    }

    public ItfCampoInstanciado getCampoInst() {
        return campoInst;
    }

}
