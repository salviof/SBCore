/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.jayway.restassured.path.json.JsonPath;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.erros.ErroDeteccaoSeparadorDecimal;
import com.super_bits.modulosSB.SBCore.UtilGeral.erros.ErroLeituraJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonLocation;
import jakarta.json.stream.JsonParsingException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class UtilCRCJson {

    private static final String PADRAO_REGEX_NUMERO_ENTRE_CONXETE = "(\\[(\\d*+)\\])";
    private static final Pattern REGEX_NUMERO_ENTRE_COLCHETE = Pattern.compile(PADRAO_REGEX_NUMERO_ENTRE_CONXETE);

    public static JsonObject getJsonObjectByTexto(String pStringJson) {
        try {
            if (pStringJson == null) {
                return null;
            }
            JsonReader jsonReader = Json.createReader(new StringReader(pStringJson));

            JsonObject json = jsonReader.readObject();
            return json;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha processando " + pStringJson, t);
            return null;
        }

    }

    public static JsonObject getJsonObjectByTextoComTratamento(String pStringJson) throws ErroProcessandoJson {

        try (JsonReader reader = Json.createReader(new StringReader(pStringJson))) {
            return reader.readObject(); // ou readArray()
        } catch (JsonParsingException e) {
            StringBuilder mensagemErro = new StringBuilder();
            JsonLocation loc = e.getLocation();

            long offset = loc.getStreamOffset();
            Long linha = loc.getLineNumber();
            long coluna = loc.getColumnNumber();
            mensagemErro.append("Erro de parsing:\n");
            mensagemErro.append("Linha: ").append(linha)
                    .append(", Coluna: ").append(coluna).append("\n");

            // Obter a linha do erro
            String[] linhas = pStringJson.split("\n");
            if (linha - 1 < linhas.length) {
                String linhaErro = linhas[linha.intValue() - 1];
                mensagemErro.append("Linha com erro:\n");
                mensagemErro.append(linhaErro).append("\n");

                // Marcar a posição da coluna com uma seta
                for (int i = 1; i < coluna; i++) {
                    mensagemErro.append(' ');
                }
                mensagemErro.append("^\n");
            } else {
                mensagemErro.append("Não foi possível localizar a linha do erro.\n");
            }

            throw new ErroProcessandoJson(mensagemErro.toString());
        }

    }

    public static JsonArray getJsonArrayByTexto(String pStringJson) {
        try {
            if (pStringJson == null) {
                return null;
            }

            JsonReader jsonReader = Json.createReader(new StringReader(pStringJson));
            JsonArray json = jsonReader.readArray();
            return json;
        } catch (Throwable t) {
            return null;
        }

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

    public static JsonObject getJsonObjectIncrementandoCampo(JsonObject pOrigem, String pChave, Object pValor) {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        pOrigem.entrySet().
                forEach(e -> builder.add(e.getKey(), e.getValue()));
        if (pValor instanceof BigDecimal) {
            builder.add(pChave, (BigDecimal) pValor);
            return builder.build();
        }
        if (pValor instanceof BigInteger) {
            builder.add(pChave, (BigDecimal) pValor);
            return builder.build();
        }
        if (pValor instanceof JsonArrayBuilder) {
            builder.add(pChave, (JsonArrayBuilder) pValor);
            return builder.build();
        }
        if (pValor instanceof JsonObjectBuilder) {
            builder.add(pChave, (JsonObjectBuilder) pValor);
            return builder.build();
        }
        if (pValor instanceof JsonValue) {
            builder.add(pChave, (JsonValue) pValor);
            return builder.build();
        }
        if (pValor instanceof String) {
            builder.add(pChave, (String) pValor);
            return builder.build();
        }
        if (pValor instanceof Boolean) {
            builder.add(pChave, (Boolean) pValor);
            return builder.build();
        }
        if (pValor instanceof Double) {
            builder.add(pChave, (Double) pValor);
            return builder.build();
        }
        if (pValor instanceof Integer) {
            builder.add(pChave, (Integer) pValor);
            return builder.build();
        }
        if (pValor instanceof Long) {
            builder.add(pChave, (Long) pValor);
            return builder.build();
        }
        return builder.build();
    }

    public static String getTextoByJsonObjeect(JsonObject pJson) {
        if (pJson == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(pJson);
        jsonWriter.close();
        return (sw.toString());
    }

    private static Map<String, Object> getMapBySequencia(Object... pSequencia) throws ErroProcessandoJson {
        Map<String, Object> chaveValor = new HashMap();
        int i = 0;
        if (pSequencia.length % 2 != 0) {
            throw new ErroProcessandoJson("O total de parâmetros deve ser par, a sequencia atributo,será processada da seguinte forma: (atributo1,valor1,atributo2,valor2), foram enviados " + pSequencia.length + " parametros");
        }
        for (Object texto : pSequencia) {
            if (i == 0 || (i % 2 == 0)) {
                String chave = (String) texto;
                int indicevalor = i + 1;
                Object valor = pSequencia[indicevalor];

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

    public static JsonObjectBuilder getJsonBuilderBySequenciaChaveValor(Object... pSequencia) throws ErroProcessandoJson {
        if (pSequencia.length == 0) {
            throw new ErroProcessandoJson("Nenhum parâmetro enviado");
        }
        Map<String, Object> chaveValor = getMapBySequencia(pSequencia);
        JsonObjectBuilder construtor = Json.createObjectBuilder(chaveValor);
        return construtor;
    }

    public static String getJsonStringBySequenciaChaveValor(Object... pSequencia) throws ErroProcessandoJson {
        if (pSequencia.length == 0) {
            throw new ErroProcessandoJson("Nenhum parâmetro enviado");
        }
        Map<String, Object> chaveValor = getMapBySequencia(pSequencia);
        JsonObjectBuilder construtor = Json.createObjectBuilder(chaveValor);
        JsonObject jsonObj = construtor.build();
        String jsonString = UtilCRCJson.getTextoByJsonObjeect(jsonObj);
        return jsonString;
    }

    public static <T> T getValorApartirDoCaminho(String pCaminho, JsonObject pJsonSimple) {

        try {
            //to do split do caminho, e extração manipulando o JsonObjet para ganho de performance
            return getValorApartirDoCaminho(pCaminho, pJsonSimple, false);
        } catch (ErroLeituraJson ex) {
            return null;
        }
    }

    public static <T> T getChavaValorApartirDoCaminho(String pCaminho, JsonObject pJsonSimple) {

        try {
            //to do split do caminho, e extração manipulando o JsonObjet para ganho de performance
            return getValorApartirDoCaminho(pCaminho, pJsonSimple, false);
        } catch (ErroLeituraJson ex) {
            return null;
        }
    }

    public static <T> T getValorApartirDoCaminho(String pCaminho, JsonObject pJson, boolean pCampoObrigatorio) throws ErroLeituraJson {
        String jsonStr = UtilCRCJson.getTextoByJsonObjeect(pJson);
        Object resposta = JsonPath.from(jsonStr).get(pCaminho);
        if (resposta == null) {
            if (pCampoObrigatorio) {
                throw new ErroLeituraJson(jsonStr, pCaminho);
            }
        }
        if (resposta instanceof Float) {
            Float repostaFloat = (Float) resposta;
            Double valorDouble = UtilCRCNumeros.doubleArredondamentoMetadeParaBaixo(repostaFloat.doubleValue(), 2);

            return (T) valorDouble;
        }
        //to do split do caminho, e extração manipulando o JsonObjet para ganho de performance
        return (T) resposta;
    }

    public static String getValorApartirDoCaminho(String pCaminho, JSONObject pJsonSimple) {
        try {
            if (UtilCRCStringValidador.isNuloOuEmbranco(pCaminho) || pJsonSimple == null) {
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
                return Integer.valueOf(UtilCRCStringFiltros.filtrarApenasNumeros(pValor.toString()));

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
                double valor = UtilCRCNumeros.getDoublePorString(pValorDouble);
                return valor;

            case NUMBER:
                String pValorJsonNumvberDouble = pValor.toString();
                double valordouble = UtilCRCNumeros.getDoublePorString(pValorJsonNumvberDouble);
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

    public static JsonObject gerarJsonByEntidadeSimplesLasyMode(ComoEntidadeSimples pEntididade) {
        JsonObjectBuilder jb = Json.createObjectBuilder();
        JsonObjectBuilder jbMetadados = Json.createObjectBuilder();
        jbMetadados.add("tipoEntidade", UtilCRCReflexaoObjeto.getClassExtraindoProxy(pEntididade.getClass().getSimpleName()).getSimpleName());
        jbMetadados.add("nomeEntidade", pEntididade.getNomeDoObjeto());
        jbMetadados.add("pluralEntidade", pEntididade.getNomeDoObjetoPlural());
        jbMetadados.add("nomeUnicoSlug", pEntididade.getNomeUnicoSlug());
        jb.add(pEntididade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ID).getNomeCamponaClasse(), (Long) pEntididade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ID).getValor());
        jb.add(pEntididade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.NOME).getNomeCamponaClasse(), (String) pEntididade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.ID).getValor());
        jb.add("metadados", jbMetadados.build());
        return jb.build();
    }
}
