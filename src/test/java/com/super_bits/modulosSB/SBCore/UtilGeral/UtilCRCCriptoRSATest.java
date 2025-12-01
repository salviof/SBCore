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
public class UtilCRCCriptoRSATest {

    public UtilCRCCriptoRSATest() {
    }

    @Test
    public void testChavePublicaPrivada() {

        String encript = UtilCRCCriptoAES.encrypt("semSenha@123", "hIDo6HA#GRXDnZk4");
        System.out.println(encript);
        Map<String, String> parDeChaves = UtilCRCCriptoRSA.chavePublicaPrivada();
        String frase = "Olá Mundo";
        String fraseCriptografada = UtilCRCCriptoRSA.getTextoCriptografado(frase, parDeChaves.values().stream().findFirst().get());
        String fraseDescriptografada = UtilCRCCriptoRSA.getTextoDescriptografado(fraseCriptografada, parDeChaves.keySet().stream().findFirst().get());
        assertEquals("Erro descriptografando", frase, fraseDescriptografada);

        String frase2 = "Olá Mundo";
        String fraseCriptografada2 = UtilCRCCriptoRSA.getTextoCriptografadoUsandoChavePublica(frase2, parDeChaves.keySet().stream().findFirst().get());
        String fraseDescriptografada2 = UtilCRCCriptoRSA.getTextoDescriptografadoUsandoChavePrivada(fraseCriptografada2, parDeChaves.values().stream().findFirst().get());
        assertEquals("Erro descriptografando", frase2, fraseDescriptografada2);
    }

}
