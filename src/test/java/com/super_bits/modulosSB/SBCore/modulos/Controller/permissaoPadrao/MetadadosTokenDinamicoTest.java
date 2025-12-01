/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import jakarta.json.JsonObject;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class MetadadosTokenDinamicoTest {

    /**
     * Test of toString method, of class MetadadosTokenDinamico.
     */
    @Test
    public void testToString() {

        MetadadosTokenDinamico metadados = new MetadadosTokenDinamico();
        metadados.setAssinaturaSistema("");
        metadados.setSistemaRequisicao("mktFatura");
        metadados.setLeadNome("Apenas teste");
        metadados.setLeadEmail("teste");
        System.out.println(UtilCRCJson.getTextoByJsonObjeect(metadados.getJson()));
        System.out.println(metadados.toString());
    }

    /**
     * Test of getJson method, of class MetadadosTokenDinamico.
     */
    @Test
    public void testGetJson() {
        System.out.println("getJson");
        MetadadosTokenDinamico instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.getJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public static MetadadosTokenDinamico getMetadadosFromJson64Encoded(String pBase64Encoded) {
        return new MetadadosTokenDinamico(pBase64Encoded);

    }
}
