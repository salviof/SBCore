/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreInputOutputConversoes {

    public static ByteArrayInputStream byteArrayStreamPorBufferedImage(BufferedImage pImagem, String pExtencao) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(pImagem, pExtencao, os);
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro transformando BufferedImage em ByteArray", t);
            return null;
        }
    }

    public static byte[] byteArrayPorBufferedImage(BufferedImage pImagem, String pExtencao) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(pImagem, pExtencao, os);

            return os.toByteArray();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro transformando BufferedImage em ByteArray", t);
            return null;
        }
    }

    public static InputStream BufferedImageToInputStream(BufferedImage pImagem, String pExtencao) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(pImagem, pExtencao, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro transformando BufferedImage em ByteArray", t);
            return null;
        }
    }

    public static String getStringUTF8(InputStream pInputStream) {
        StringBuilder textBuilder = new StringBuilder();
        try ( Reader reader = new BufferedReader(new InputStreamReader(pInputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
            return textBuilder.toString();
        } catch (Throwable t) {
            return null;
        }
    }

}
