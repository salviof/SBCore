/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Mensagens;

/**
 *
 * @author salvio
 */
public class CentralMensagemSystemout extends CentralDeMensagemAbstrata {

    public CentralMensagemSystemout() {
        System.out.println("");
    }

    @Override
    public void enviaMensagem(ItfMensagem pMensagem) {
        System.out.println(pMensagem.getMenssagem());
        // UTILSBCoreDesktopApp.showMessageNovaThread(pMensagem);
    }

}
