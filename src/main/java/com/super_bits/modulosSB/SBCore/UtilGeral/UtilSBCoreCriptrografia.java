/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import org.apache.commons.codec.digest.Crypt;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/**
 *
 * Rferencia:
 * https://www.devmedia.com.br/criptografia-assimetrica-criptografando-e-descriptografando-dados-em-java/31213
 *
 * @author salviofurbino
 * @since 21/05/2019
 * @version 1.0
 */
public class UtilSBCoreCriptrografia {

    public KeyPair gerarParChaveRSA1024() {
        final KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            return key;
        } catch (NoSuchAlgorithmException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando chave Rsa 1024", ex);
            return null;
        }

    }

    public KeyPair gerarParChaveRSA2048() {
        final KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(2048);
            final KeyPair key = keyGen.generateKeyPair();

            return key;
        } catch (NoSuchAlgorithmException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando chave Rsa 248", ex);
            return null;
        }
    }

    /**
     * Criptografa o texto puro usando chave pública.
     */
    public static byte[] criptografaTextoAssimetrico(String texto, PublicKey chave) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    /**
     * Decriptografa o texto puro usando chave privada.
     */
    public static String decriptografaAssimetrica(byte[] texto, PrivateKey chave) {
        byte[] dectyptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

    public static String criptografarTextoSimetricoSaltAleatorio(String pSenha) {
        return Crypt.crypt(pSenha);
    }

    public static boolean checarCriptografiaTextoSimetricoSaltAleatorio(String pSenha, String chave) {

        final String check = Crypt.crypt(pSenha, chave);
        return check.equals(chave);
    }

    public static String getMD5(String pTexto) {

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(pTexto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;

    }

    public static String getHash128HexaMD5AsString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // 128 bits
            byte[] hashBytes = md.digest(input.getBytes("UTF-8"));

            // Converte os bytes para hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
