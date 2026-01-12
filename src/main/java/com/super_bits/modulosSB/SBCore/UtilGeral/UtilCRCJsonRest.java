/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao.RespostaSimples;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoAgenteDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public class UtilCRCJsonRest {

    public static ItfResposta getResposta(JsonObject pJsonResposta) {

        RespostaSimples resp = new RespostaSimples(Object.class);
        if (pJsonResposta.containsKey("retorno")) {
            resp.setRetorno(pJsonResposta.get("retorno"));
        }
        if (pJsonResposta.containsKey("mensagens")) {

            JsonArray mensagem = pJsonResposta.getJsonArray("mensagens");
            List<ItfMensagem> mensagens = new ArrayList<>();

            for (JsonValue msg : mensagem) {

                switch (msg.getValueType()) {

                    case OBJECT:

                        String tipoMsg = msg.asJsonObject().getString("tipo");
                        String tipoAgente = msg.asJsonObject().getString("agenteDestino");
                        String mensagemTexto = msg.asJsonObject().getString("texto");
                        FabMensagens tipoMSG = FabMensagens.getTipoMensagemByTexto(tipoMsg);

                        ItfMensagem mensagemItem;
                        if (tipoAgente.equals(FabTipoAgenteDoSistema.USUARIO.name())) {
                            mensagemItem = tipoMSG.getMsgUsuario(mensagemTexto);
                        } else {
                            mensagemItem = tipoMSG.getMsgDesenvolvedor(mensagemTexto);
                        }
                        resp.addMensagem(mensagemItem);
                        break;

                }
            }
        }
        return resp;
    }

    public static JsonObjectBuilder getRespostaJsonBuilder(ItfRespostaAcaoDoSistema pResposta) {
        JsonObjectBuilder respostaJson = getRespostaJsonBuilderBase(pResposta.isSucesso(), pResposta.getResultado(), pResposta.getMensagens());
        if (pResposta.getRetorno() != null) {

            if (pResposta.getRetorno() instanceof JsonObject) {
                getRespostaJsonAdiocionarRetorno(respostaJson, (JsonObject) pResposta.getRetorno());
            }
            if (pResposta.getRetorno() instanceof JsonArray) {
                getRespostaJsonAdiocionarRetorno(respostaJson, (JsonArray) pResposta.getRetorno());
            }
            if (pResposta.getRetorno() instanceof ComoEntidadeSimples) {
                JsonObject retorno = UtilCRCJson.gerarJsonByEntidadeSimplesLasyMode((ComoEntidadeSimples) pResposta.getRetorno());
                getRespostaJsonAdiocionarRetorno(respostaJson, retorno);
            }

        }
        return respostaJson;
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBase(List<ItfMensagem> pMensagens) {
        return getRespostaJsonBuilderBase(FabMensagens.isSucesso(pMensagens), FabMensagens.getResultado(pMensagens), pMensagens);
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBaseFalha(String pMensagemFalha) {
        ItfMensagem mensagem = FabMensagens.ERRO.getMsgUsuario(pMensagemFalha);
        return getRespostaJsonBuilderBase(false, ItfResposta.Resultado.FALHOU, Lists.newArrayList(mensagem));
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBaseSucesso(String pMensagemSucesso, JsonObject pJsonRetorno) {
        ItfMensagem mensagem = FabMensagens.AVISO.getMsgUsuario(pMensagemSucesso);
        JsonObjectBuilder respJson = getRespostaJsonBuilderBase(true, ItfResposta.Resultado.SUCESSO, Lists.newArrayList(mensagem));
        getRespostaJsonAdiocionarRetorno(respJson, pJsonRetorno);
        return respJson;
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBaseSucesso(JsonObject pJsonRetorno) {
        JsonObjectBuilder respJson = getRespostaJsonBuilderBase(true, ItfResposta.Resultado.SUCESSO, new ArrayList<>());
        getRespostaJsonAdiocionarRetorno(respJson, pJsonRetorno);
        return respJson;
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBaseSucesso(JsonArray pJsonArray) {
        JsonObjectBuilder respJson = getRespostaJsonBuilderBase(true, ItfResposta.Resultado.SUCESSO, new ArrayList<>());
        getRespostaJsonAdiocionarRetorno(respJson, pJsonArray);
        return respJson;
    }

    private static JsonObjectBuilder getRespostaJsonAdiocionarRetorno(JsonObjectBuilder pRespostaBase, JsonObject pRetorno) {
        if (pRetorno != null) {
            pRespostaBase.add("retorno", pRetorno);
        } else {
            pRespostaBase.add("retorno", JsonValue.EMPTY_JSON_OBJECT);
        }
        return pRespostaBase;
    }

    private static JsonObjectBuilder getRespostaJsonAdiocionarRetorno(JsonObjectBuilder pRespostaBase, JsonArray pRetorno) {
        if (pRetorno != null) {
            pRespostaBase.add("retorno", pRetorno);
        } else {
            pRespostaBase.add("retorno", JsonValue.EMPTY_JSON_ARRAY);
        }
        return pRespostaBase;
    }

    public static JsonObjectBuilder getRespostaJsonBuilderBase(boolean pSucesso, ItfResposta.Resultado pResultado, List<ItfMensagem> pMensagens) {
        JsonArrayBuilder mensagens = Json.createArrayBuilder();
        for (ItfMensagem msg : pMensagens) {
            JsonObjectBuilder msgJsonBuilder = Json.createObjectBuilder();
            msgJsonBuilder.add("tipoMensagem", msg.getTipoDeMensagem().name());
            msgJsonBuilder.add("tipoAgente", msg.getTipoDestinatario().name());
            msgJsonBuilder.add("mensagem", msg.getMenssagem());
            mensagens.add(msgJsonBuilder.build());
        }

        JsonObjectBuilder jsonRespconstrutor = Json.createObjectBuilder();
        jsonRespconstrutor.add("resultado", pResultado.name());
        jsonRespconstrutor.add("sucesso", pSucesso);
        jsonRespconstrutor.add("mensagem", mensagens);
        return jsonRespconstrutor;
    }
}
