/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.erros;

/**
 *
 * @author salvio
 */
public class ErroEnviandoEmail extends Throwable {

    public ErroEnviandoEmail(String pMotivo) {
        super(pMotivo);
    }

}
