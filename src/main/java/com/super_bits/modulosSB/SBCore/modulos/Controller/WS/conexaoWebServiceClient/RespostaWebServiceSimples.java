/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

/**
 *
 * @author desenvolvedor
 */
public class RespostaWebServiceSimples {

    private final int codigoResposta;
    private final String resposta;
    private final String respostaErro;

    public RespostaWebServiceSimples(int pCodigo, String pResposta, String pRespostaErro) {
        codigoResposta = pCodigo;
        resposta = pResposta;
        respostaErro = pRespostaErro;

    }

    public int getCodigoResposta() {
        return codigoResposta;
    }

    public String getResposta() {
        return resposta;
    }

    public String getRespostaErro() {
        return respostaErro;
    }

}
