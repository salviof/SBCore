/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.jayway.restassured.internal.assertion.AssertionSupport.properties;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.erros.ErroDeteccaoSeparadorDecimal;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salviofurbino
 * @since 07/06/2019
 * @version 1.0
 */
public class UtilSBCoreJson {

    private static final String PADRAO_REGEX_NUMERO_ENTRE_CONXETE = "(\\[(\\d*+)\\])";
    private static final Pattern REGEX_NUMERO_ENTRE_COLCHETE = Pattern.compile(PADRAO_REGEX_NUMERO_ENTRE_CONXETE);

    public static JsonObject getJsonObjectByTexto(String pStringJson) {
        if (pStringJson == null) {
            return null;
        }
        JsonReader jsonReader = Json.createReader(new StringReader(pStringJson));
        JsonObject json = jsonReader.readObject();
        return json;
    }

    public static String getTextoByJsonArray(JsonArray pArray) {
        StringWriter sw = new StringWriter();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeArray(pArray);
        jsonWriter.close();
        return (sw.toString());
    }

    public static String getTextoByJsonObjeect(JsonObject pJson) {
        StringWriter sw = new StringWriter();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(pJson);
        jsonWriter.close();
        return (sw.toString());
    }

    private static Map<String, Object> getMapBySequencia(String... pSequencia) throws ErroProcessandoJson {
        Map<String, Object> chaveValor = new HashMap();
        int i = 0;
        if (pSequencia.length % 2 != 0) {
            throw new ErroProcessandoJson("O total de parâmetros deve ser par, a sequencia atributo,será processada da seguinte forma: (atributo1,valor1,atributo2,valor2), foram enviados " + pSequencia.length + " parametros");
        }
        for (String texto : pSequencia) {
            if (i == 0 || (i % 2 == 0)) {
                String chave = texto;
                int indicevalor = i + 1;
                String valor = pSequencia[indicevalor];

                if (chave == null) {
                    throw new ErroProcessandoJson("Eviado nulo em nome do atributo, no parâmetro com indice " + i + ". "
                            + "apenas o valor pode conter nulo, o valor do parâmetro nulo enviado foi:" + pSequencia[indicevalor]);
                }
                chaveValor.put(chave, valor);
            }
            i++;
        }
        return chaveValor;
    }

    public static JsonObject getJsonObjectBySequenciaChaveValor(String... pSequencia) throws ErroProcessandoJson {
        if (pSequencia.length == 0) {
            throw new ErroProcessandoJson("Nenhum parâmetro enviado");
        }
        Map<String, Object> chaveValor = getMapBySequencia(pSequencia);
        JsonObjectBuilder construtor = Json.createObjectBuilder(chaveValor);
        return construtor.build();
    }

    public static JsonObjectBuilder getJsonBuilderBySequenciaChaveValor(String... pSequencia) throws ErroProcessandoJson {
        if (pSequencia.length == 0) {
            throw new ErroProcessandoJson("Nenhum parâmetro enviado");
        }
        Map<String, Object> chaveValor = getMapBySequencia(pSequencia);
        JsonObjectBuilder construtor = Json.createObjectBuilder(chaveValor);
        return construtor;
    }

    public static String getValorApartirDoCaminho(String pCaminho, JSONObject pJsonSimple) {
        try {
            if (UtilSBCoreStringValidador.isNuloOuEmbranco(pCaminho) || pJsonSimple == null) {
                return null;
            }

            String[] partes = pCaminho.split("\\.");
            if (partes.length == 0) {
                Object valorAtributo = pJsonSimple.get(partes[0]);
                if (valorAtributo == null) {
                    return null;
                } else {
                    return pJsonSimple.get(partes[0]).toString();
                }

            } else {
                JSONObject objetoJsonAnalize = pJsonSimple;
                for (String parte : partes) {
                    if (parte.contains("[")) {

                        // Now create matcher object.
                        Matcher m = REGEX_NUMERO_ENTRE_COLCHETE.matcher(parte);
                        m.find();
                        String indiceStr = m.group(2);
                        String propriedade = parte.substring(0, parte.indexOf("["));
                        Object objetoProp = objetoJsonAnalize.get(propriedade);
                        Optional<Object> itemEncontrado = null;

                        AtomicInteger indexDinamico = new AtomicInteger(0);
                        Integer indice = Integer.valueOf(indiceStr);
                        if (objetoProp instanceof JSONObject) {
                            objetoJsonAnalize = (JSONObject) objetoJsonAnalize.get(propriedade);
                            itemEncontrado = objetoJsonAnalize.values().stream().
                                    filter(ob -> indice == indexDinamico.getAndIncrement()).findFirst();
                        }
                        if (objetoProp instanceof JSONArray) {
                            JSONArray jarray = (JSONArray) objetoProp;
                            itemEncontrado = jarray.stream().filter(ob -> indice == indexDinamico.getAndIncrement()).findFirst();
                        }
                        if (itemEncontrado == null) {
                            return null;
                        }
                        if (parte.equals(partes[partes.length - 1])) {

                            if (itemEncontrado.isPresent()) {
                                return itemEncontrado.get().toString();
                            } else {
                                return null;
                            }

                        } else {
                            if (itemEncontrado.isPresent()) {
                                Object item = itemEncontrado.get();
                                objetoJsonAnalize = (JSONObject) item;
                            } else {
                                return null;
                            }
                        }
                    } else {

                        if (parte.equals(partes[partes.length - 1])) {
                            Object valor = objetoJsonAnalize.get(parte);
                            if (valor == null) {
                                return null;
                            } else {
                                return objetoJsonAnalize.get(parte).toString();
                            }
                        } else {
                            objetoJsonAnalize = (JSONObject) objetoJsonAnalize.get(parte);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro interpretando caminho em objeto json" + pCaminho, t);
        }
        return null;
    }

    public static Integer getComoInteiro(JsonValue pValor) {

        switch (pValor.getValueType()) {
            case ARRAY:
                break;
            case OBJECT:
                break;
            case STRING:
                return Integer.valueOf(UtilSBCoreStringFiltros.filtrarApenasNumeros(pValor.toString()));

            case NUMBER:
                return Integer.valueOf(pValor.toString());

            case TRUE:
                return 1;
            case FALSE:
                return 0;
            case NULL:
                return null;

            default:
                throw new AssertionError();
        }
        return null;
    }

    public static Double getComoDecimal(JsonValue pValor) throws ErroDeteccaoSeparadorDecimal {

        switch (pValor.getValueType()) {
            case ARRAY:
                break;
            case OBJECT:
                break;
            case STRING:
                String pValorDouble = pValor.toString();
                pValorDouble = pValorDouble.replace("\"", "");
                pValorDouble = pValorDouble.replace(" ", "");
                double valor = UtilSBCoreNumeros.getDoublePorString(pValorDouble);
                return valor;

            case NUMBER:
                String pValorJsonNumvberDouble = pValor.toString();
                double valordouble = UtilSBCoreNumeros.getDoublePorString(pValorJsonNumvberDouble);
                return valordouble;
            case TRUE:
                break;
            case FALSE:
                break;
            case NULL:
                break;
            default:
                throw new AssertionError();
        }
        return null;
    }
}
