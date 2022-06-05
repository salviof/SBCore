/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.erros;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;

/**
 *
 * @author salvio
 */
public class ErroDeteccaoSeparadorDecimal extends ErroSBCoreFW {

    public enum TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL {
        NENHUM_SEPARADOR_DETECTADO,
        VALOR_NULO_OU_INVALIDO,
    }
    private TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL tipoErro;

    public ErroDeteccaoSeparadorDecimal(TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL pTipo) {
        super(pTipo.name());
        tipoErro = pTipo;

    }

    public ErroDeteccaoSeparadorDecimal(TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL pTipo, String pMensagem) {
        super(pMensagem);
        tipoErro = pTipo;

    }

    public TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL getTipoErro() {
        return tipoErro;
    }

}
