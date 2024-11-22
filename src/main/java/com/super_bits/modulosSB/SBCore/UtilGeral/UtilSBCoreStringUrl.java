package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author salvio
 */
public class UtilSBCoreStringUrl {

    public static String gerarUrlCodificada(String pParteUrl) {
        String parteCodigicada;
        try {
            parteCodigicada = URLEncoder.encode(pParteUrl, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            return pParteUrl;
        }
        return parteCodigicada;
    }

    public static String gerarUrlDesCodificada(String pParteUrl) {
        String partedeCodigicada;
        try {
            partedeCodigicada = URLDecoder.decode(pParteUrl, StandardCharsets.UTF_8.name());
            return partedeCodigicada;
        } catch (UnsupportedEncodingException ex) {
            return pParteUrl;
        }

    }

}
