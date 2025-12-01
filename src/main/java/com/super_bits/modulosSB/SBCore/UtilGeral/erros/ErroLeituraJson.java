/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.erros;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import jakarta.json.JsonObject;

/**
 *
 * @author salvio
 */
public class ErroLeituraJson extends Throwable {

    private final String caminho;
    private final String json;

    private static final String buildMensagem(String pJson, String pCaminho) {

        String amostrajson = UtilCRCStringFiltros.getPrimeirasXLetrasDaString(pJson, 15) + " ....";
        StringBuilder mensagem = new StringBuilder();

        JsonObject json = UtilCRCJson.getJsonObjectByTexto(pJson);
        if (json == null) {

            mensagem.append("Json inválido");

        } else {

            mensagem.append("Erro buscando informação em documento Json");
            mensagem.append("\n<br> O caminho solicitado:  [").append(pCaminho).append("]");
            mensagem.append("\n<br> Json, parte:");
            mensagem.append(amostrajson);
        }
        return mensagem.toString();
    }

    public ErroLeituraJson(String pJson, String pCaminho) {
        super(buildMensagem(pJson, pCaminho));
        json = pJson;
        caminho = pCaminho;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getJson() {
        return json;
    }

}
