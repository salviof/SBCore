/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.json;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class ErroProcessandoJson extends ErroSBCoreFW {

    public ErroProcessandoJson(String pMensagem) {
        super(pMensagem);
    }

    public ErroProcessandoJson(FabErro pTipoErro, String pMensagem) {
        super(pTipoErro, pMensagem);
    }

    public ErroProcessandoJson(Throwable pCausa) {
        super(pCausa);
    }

    public ErroProcessandoJson(String pMensagem, Throwable pCausa) {
        super(pMensagem, pCausa);
    }

}
