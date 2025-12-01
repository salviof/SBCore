/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.apache.commons.codec.digest.Crypt;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author desenvolvedorninja01
 */
public class UtilCRCCriptrografiaTest {

    public UtilCRCCriptrografiaTest() {
    }

    /**
     * Test of gerarParChaveRSA1024 method, of class UtilCRCCriptrografia.
     */
    @Test
    public void testesCriptografiaSimples() {
        String hash = UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste");

        Assert.assertTrue("Senha não confere", UtilCRCCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("SenhaTEste", hash));
        Assert.assertFalse("Senha  confere", UtilCRCCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("senhaTeste@testrrre", hash));

        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste").length());
        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilCRCCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));

    }

    @Test
    public void hash128HexaMD5AsString() {
        String valor = UtilCRCCriptrografia.getHash128HexaMD5AsString("Olá!");
        System.out.println(valor);
        Assert.assertEquals(valor, "ce0a1f646efc94a60b1bc2296ecd0df8");
    }

}
