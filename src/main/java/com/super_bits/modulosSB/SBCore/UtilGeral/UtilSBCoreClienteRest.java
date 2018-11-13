/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRestBasico;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreClienteRest {

    public static InfoConsumoRestService getInformacoesConsumoRest(ItfFabricaIntegracaoRestBasico pConexao) {

        try {
            Field camo = pConexao.getClass().getField(pConexao.toString());
            return camo.getAnnotation(InfoConsumoRestService.class);
        } catch (Throwable t) {
            return null;
        }

    }

    public static JSONObject getObjetoJsonPorUrl(String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Falha erro HTTP codigo : " + conn.getResponseCode() + "->" + conn.getResponseMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            String respostaStr = "";
            while ((output = br.readLine()) != null) {
                respostaStr += output;
            }
            //System.out.println(respostaStr);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(respostaStr);
            return json;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro   " + pUrl, t);
            return null;
        }
    }

    public static void getRelatorioTesteDeAcessoGet(String pUrl) {
        try {
            JSONObject json = getObjetoJsonPorUrl(pUrl);

            Collection valores = json.values();

            for (Object teste : valores) {

                try {
                    System.out.println(teste.getClass().getSimpleName());

                    if (teste instanceof JSONArray) {
                        System.out.println("Array de dadados ------------>");
                        JSONArray array = (JSONArray) teste;

                        for (Iterator iterator = array.iterator(); iterator.hasNext();) {
                            Object next = iterator.next();
                            if (next instanceof JSONObject) {
                                Map<String, Object> informacao = (Map<String, Object>) next;
                                for (String key : informacao.keySet()) {
                                    System.out.println("-------------" + key + "(" + informacao.get(key).getClass().getSimpleName() + ")--------");
                                    System.out.println(informacao.get(key));
                                }
                            } else {
                                System.out.println("------------");
                                System.out.println(next.getClass().getSimpleName());
                                System.out.println(next);
                            }

                        }

                    }

                    if (teste instanceof JSONObject) {
                        Map<String, Object> informacao = (Map<String, Object>) teste;
                        for (String key : informacao.keySet()) {
                            System.out.println("-------------" + key + "(" + informacao.get(key).getClass().getSimpleName() + ")--------");
                            System.out.println(informacao.get(key));
                        }
                    } else {
                        System.out.println("------------");
                        System.out.println(teste.getClass().getSimpleName());
                        System.out.println(teste);
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Lendo valores" + teste, t);
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo relatório de Acesso REste", t);
        }

    }

    public static void getRelatorioTesteDeAcessoPut(String pUrl) {

    }

    public static RespostaWebServiceSimples getRespostaRest(String pURL, FabTipoConexaoRest pTipoConexao,
            boolean pPostarInformcoesCorpoRequisicao,
            Map<String, String> pCabecalho, String pCorpoRequisicao) {

        try {
            System.out.println("conectando com" + pURL);
            HttpURLConnection conn = (HttpURLConnection) new URL(pURL).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod(pTipoConexao.getMetodoRequest());
            pCabecalho.keySet().forEach((cabecalho) -> {
                conn.setRequestProperty(cabecalho, pCabecalho.get(cabecalho));
            });

            if (pPostarInformcoesCorpoRequisicao) {

                if (pCorpoRequisicao != null) {
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(pCorpoRequisicao);
                    wr.flush();
                }
            }
            BufferedReader br = null;
            String respostaStr = "";
            try {
                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            } catch (Throwable t) {
                // SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getLocalizedMessage() + t.getMessage(), t);
                //      return null;
                respostaStr += t.getMessage();
            }

            String inputResposta;

            if (br != null) {
                while ((inputResposta = br.readLine()) != null) {
                    respostaStr += inputResposta;
                }
            }

            int codigoResposta = conn.getResponseCode();
            String mensagemErro = "";
            if (codigoResposta < 200 || codigoResposta > 220) {
                mensagemErro = conn.getResponseCode() + conn.getResponseMessage();
                try {
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    ArrayList<String> respostaErro = new ArrayList<>();
                    String linha = br.readLine();
                    while (linha != null) {
                        respostaErro.add(linha);
                        linha = br.readLine();
                    }
                    mensagemErro += UtilSBCoreStringValidador.getStringDaListaComBarraN(respostaErro);

                } catch (IOException t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro processando informações de erro", t);
                }

                //throw new RuntimeException("Falha Comunicação com serviço rest : HTTP error codidigo : " + conn.getResponseCode() + " Mensagem:" + mensagemErro);
            }

            conn.disconnect();

            return new RespostaWebServiceSimples(codigoResposta, respostaStr, mensagemErro);
        } catch (IOException | RuntimeException t) {
            return null;
        }
    }
}
