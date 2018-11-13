/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.RespostaWebServiceRestIntegracao;
import static java.lang.Thread.sleep;

/**
 *
 * @author SalvioF
 */
public abstract class ConsumoWSExecucao extends Thread {

    protected boolean finalizou;
    protected String resposta;
    protected int codigoResposta;
    protected String erroMsg;

    private RespostaWebServiceRestIntegracao respostaWS;

    public ConsumoWSExecucao() {
        finalizou = false;
    }

    public RespostaWebServiceRestIntegracao getResposta() {
        int i = 0;
        while (!finalizou) {
            try {

                sleep(100);
                i++;
                if (i > 1000) {
                    System.out.println("Tempo limite para obter resposta foi ultrapassado");
                    return null;
                }

            } catch (InterruptedException t) {
                return null;
            }
        }
        if (resposta == null) {
            System.out.println("Resposta encontrada igual a nulo, impossível determinar a RespostaWS");
            return null;
        } else {
            if (respostaWS == null) {
                respostaWS = new RespostaWebServiceRestIntegracao(resposta, codigoResposta, erroMsg);
            }
            return respostaWS;
        }

    }

}
