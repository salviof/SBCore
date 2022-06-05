/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreCriptoRSA {

    public static Map<String, String> chavePublicaPrivada() {
        Map<String, String> parDeChave = new HashMap<>();
        KeyPairGenerator gerador;
        try {
            gerador = KeyPairGenerator.getInstance("RSA");
            gerador.initialize(4072);
            gerador.generateKeyPair();
            Base64.Encoder encoder = Base64.getEncoder();
            KeyPair parRSA = gerador.genKeyPair();
            String publicRSA = encoder.encodeToString(parRSA.getPublic().getEncoded());
            String privadoRSA = encoder.encodeToString(parRSA.getPrivate().getEncoded());
            parDeChave.put(publicRSA, privadoRSA);
            return parDeChave;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static PrivateKey getChavePrivadaByTexto(String pChave) {
        PrivateKey privateKey;
        try {

            KeyFactory kf = KeyFactory.getInstance("RSA");
            byte[] encodedPv = Base64.getDecoder().decode(pChave);
            PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
            privateKey = kf.generatePrivate(keySpecPv);
        } catch (Throwable t) {
            return null;
        }
        return privateKey;
    }

    public static PublicKey getChavePublicaByTexto(String pChave) {
        PublicKey chavePublica;
        try {

            KeyFactory kf = KeyFactory.getInstance("RSA");

            byte[] keyDecoded = Base64.getDecoder().decode(pChave.getBytes());
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(keyDecoded);

            PublicKey pubKey = kf.generatePublic(publicSpec);
            return pubKey;
        } catch (Throwable t) {
            return null;
        }

    }

    public static String getTextoCriptografado(String pTexto, String pChavePrivada) {

        try {
            Cipher encryptionCipher = Cipher.getInstance("RSA");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, getChavePrivadaByTexto(pChavePrivada));

            byte[] encryptedMessage
                    = encryptionCipher.doFinal(pTexto.getBytes());
            String encryption
                    = Base64.getEncoder().encodeToString(encryptedMessage);
            return encryption;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(UtilSBCoreCriptoRSA.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static String getTextoDescriptografado(String pTextoCripto, String pChavePublica) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(pTextoCripto);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getChavePublicaByTexto(pChavePublica));
            byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, "UTF8");

        } catch (Throwable t) {
            return null;
        }

    }

    public static String getTextoDescriptografadoUsandoChavePrivada(String pTextoCripto, String pChavePrivada) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(pTextoCripto.getBytes(Charset.forName("UTF8")));
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, getChavePrivadaByTexto(pChavePrivada));
            //  rsaCipher.update(encryptedBytes);
            byte[] decryptedMessage = rsaCipher.doFinal(encryptedBytes);
            return new String(decryptedMessage, "UTF8");

        } catch (Throwable t) {
            return null;
        }

    }

    public static String getTextoCriptografadoUsandoChavePublica(String pTexto, String pChavePublica) {

        try {
            Cipher encryptionCipher = Cipher.getInstance("RSA");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, getChavePublicaByTexto(pChavePublica));

            byte[] encryptedMessage
                    = encryptionCipher.doFinal(pTexto.getBytes());
            String encryption
                    = Base64.getEncoder().encodeToString(encryptedMessage);
            return encryption;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(UtilSBCoreCriptoRSA.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
