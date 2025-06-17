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
public class UtilSBCoreCriptrografiaTest {

    public UtilSBCoreCriptrografiaTest() {
    }

    /**
     * Test of gerarParChaveRSA1024 method, of class UtilSBCoreCriptrografia.
     */
    @Test
    public void testesCriptografiaSimples() {
        String hash = UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste");

        Assert.assertTrue("Senha não confere", UtilSBCoreCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("SenhaTEste", hash));
        Assert.assertFalse("Senha  confere", UtilSBCoreCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("senhaTeste@testrrre", hash));

        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste").length());
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));

    }

    @Test
    public void hash128HexaMD5AsString() {
        String valor = UtilSBCoreCriptrografia.getHash128HexaMD5AsString("Olá!");
        System.out.println(valor);
        Assert.assertEquals(valor, "ce0a1f646efc94a60b1bc2296ecd0df8");
    }

}
