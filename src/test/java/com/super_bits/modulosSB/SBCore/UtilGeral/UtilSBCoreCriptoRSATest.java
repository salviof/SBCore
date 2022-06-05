/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreCriptoRSATest {

    public UtilSBCoreCriptoRSATest() {
    }

    @Test
    public void testChavePublicaPrivada() {
        Map<String, String> parDeChaves = UtilSBCoreCriptoRSA.chavePublicaPrivada();
        String frase = "Olá Mundo";
        String fraseCriptografada = UtilSBCoreCriptoRSA.getTextoCriptografado(frase, parDeChaves.values().stream().findFirst().get());
        String fraseDescriptografada = UtilSBCoreCriptoRSA.getTextoDescriptografado(fraseCriptografada, parDeChaves.keySet().stream().findFirst().get());
        assertEquals("Erro descriptografando", frase, fraseDescriptografada);

        String frase2 = "Olá Mundo";
        String fraseCriptografada2 = UtilSBCoreCriptoRSA.getTextoCriptografadoUsandoChavePublica(frase2, parDeChaves.keySet().stream().findFirst().get());
        String fraseDescriptografada2 = UtilSBCoreCriptoRSA.getTextoDescriptografadoUsandoChavePrivada(fraseCriptografada2, parDeChaves.values().stream().findFirst().get());
        assertEquals("Erro descriptografando", frase2, fraseDescriptografada2);
    }

}
