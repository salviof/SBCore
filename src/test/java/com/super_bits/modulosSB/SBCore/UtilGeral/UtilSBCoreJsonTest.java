/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.UtilGeral.erros.ErroDeteccaoSeparadorDecimal;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class UtilSBCoreJsonTest {

    private static final String corpoRestFull = "{\n"
            + "    \"@id\": 0,\n"
            + "    \"teste1\": \"1123,20\",\n"
            + "    \"teste2\": 1123.20,\n"
            + "    \"teste3\": 1123.2,\n"
            + "    \"teste4\": \"1.123,20\",\n"
            + "    \"teste5\": \"1.123,20\",\n"
            + "    \"teste6\": \"R$1.123,20\",\n"
            + "    \"teste7\": \"1,123.20\",\n"
            + "    \"itens\": [\n"
            + "        {\n"
            + "            \"tipoServico\": {\n"
            + "                \"@id\": \"2\"\n"
            + "            },\n"
            + "            \"valorNegociado\": 2000.30\n"
            + "        }\n"
            + "    ],\n"
            + "    \"itensRecorrentes\": [\n"
            + "    ]\n"
            + "}";

    @Test
    public void testeGetValorApartirDoCaminho() throws Throwable {
        try {
            String oi = "oi";
            double valor = UtilSBCoreJson.getValorApartirDoCaminho("teste3", UtilSBCoreJson.getJsonObjectByTexto(corpoRestFull));
            double valor2 = UtilSBCoreJson.getValorApartirDoCaminho("teste3", UtilSBCoreJson.getJsonObjectByTexto(corpoRestFull));
            String valorSulista = UtilSBCoreJson.getValorApartirDoCaminho("itens[0].tipoServico.@id", UtilSBCoreJson.getJsonObjectByTexto(corpoRestFull));
            String valorinexiste = UtilSBCoreJson.getValorApartirDoCaminho("propriedadenaoexistente", UtilSBCoreJson.getJsonObjectByTexto(corpoRestFull));

            System.out.println("ok");
        } catch (Throwable t) {
            throw new Throwable(t);
        }
    }

    /**
     * Test of getComoDecimal method, of class UtilSBCoreJson.
     */
    @Test
    public void testGetComoDecimal() {
        JsonObject jsonOrcamento = UtilSBCoreJson.getJsonObjectByTexto(corpoRestFull);

        try {
            double valor1 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste1"));
            double valor2 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste2"));
            double valor3 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste3"));
            double valor4 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste4"));
            double valor5 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste5"));
            double valor6 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste6"));
            double valor7 = UtilSBCoreJson.getComoDecimal(jsonOrcamento.get("teste7"));

            Assert.assertEquals("Falha ", 1123.2, valor1, 0);
            Assert.assertEquals("Falha ", 1123.2, valor2, 0);
            Assert.assertEquals("Falha ", 1123.2, valor3, 0);
            Assert.assertEquals("Falha ", 1123.2, valor4, 0);
            Assert.assertEquals("Falha ", 1123.2, valor5, 0);
            Assert.assertEquals("Falha ", 1123.2, valor6, 0);
            Assert.assertEquals("Falha ", 1123.2, valor7, 0);
        } catch (ErroDeteccaoSeparadorDecimal ex) {
            fail("Falha convertendo double");
        }

        System.out.println("");

    }

}
