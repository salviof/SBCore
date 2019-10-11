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

        final String check = Crypt.crypt("SenhaTEste", hash);
        final String check2 = Crypt.crypt("SenhaTEstess", hash);

        String hashteste = UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("senhaTeste@teste");
        Assert.assertTrue("Senha não confere", UtilSBCoreCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("senhaTeste@teste", hashteste));
        Assert.assertFalse("Senha  confere", UtilSBCoreCriptrografia.checarCriptografiaTextoSimetricoSaltAleatorio("senhaTeste@testrrre", hashteste));

        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste").length());
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));
        System.out.println(UtilSBCoreCriptrografia.criptografarTextoSimetricoSaltAleatorio("SenhaTEste"));

    }

}
