/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class UtilCRCGravatar {

    public static String getGravatarUrl(String email, int size) {

        if (null == email) {
            return null;
        }

        final String hash = UtilCRCCriptrografia.getMD5(email.toLowerCase());
        final String gravtar = "http://www.gravatar.com/avatar/%s?s=%s&r=g&d=mp";
        final String gravatarUrl = String.format(gravtar, hash, size);

        try {
            String urlEncode = URLEncoder.encode(gravatarUrl, "UTF-8");
            return gravatarUrl;
        } catch (UnsupportedEncodingException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha gerando url  gravatar", e);
        }
        return null;

    }

}
