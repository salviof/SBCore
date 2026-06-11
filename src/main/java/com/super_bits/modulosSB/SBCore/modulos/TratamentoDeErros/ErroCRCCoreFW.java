/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class ErroCRCCoreFW extends Exception {

    private final FabErro tipoTratamentoErro;

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroCRCCoreFW(String pMensagem) {
        super(pMensagem);
        tipoTratamentoErro = FabErro.SOLICITAR_REPARO;
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroCRCCoreFW(FabErro pTipoErro, String pMensagem) {
        super(pMensagem);
        tipoTratamentoErro = pTipoErro;
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroCRCCoreFW(FabErro pTipoErro, Throwable pMensagem) {
        super(pMensagem);
        tipoTratamentoErro = pTipoErro;
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public ErroCRCCoreFW(Throwable pCausa) {
        super(pCausa);
        tipoTratamentoErro = FabErro.SOLICITAR_REPARO;
    }

    public ErroCRCCoreFW(String pMensagem, Throwable pCausa) {
        super(pMensagem, pCausa);
        tipoTratamentoErro = FabErro.SOLICITAR_REPARO;
    }

    public FabErro getTipoTratamentoErro() {
        return tipoTratamentoErro;
    }

    @Override
    public final String getMessage() {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }

}
