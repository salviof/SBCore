/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.commons.mail.util.MimeMessageParser;

/**
 *
 * @author salviofurbino
 * @since 19/04/2019
 * @version 1.0
 */
public class UtilSBCoreEmailObjetos {

    public static String getAddresEmFormatoTextoPadrao(Address[] pDEstinatarios) {
        StringBuilder str = new StringBuilder();
        if (pDEstinatarios == null) {
            return "";
        }
        for (Address endreco : pDEstinatarios) {
            if (endreco == pDEstinatarios[pDEstinatarios.length - 1]) {
                endreco.toString();
            }
        }
        return str.toString();
    }

    public static String getAddresEmFormatoSeparadoVirgula(Address[] pDEstinatarios) {
        if (pDEstinatarios == null) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (Address endreco : pDEstinatarios) {
            str.append(endreco.toString());
            str.append(",");

        }
        return str.toString();
    }

    public static class ArquivoAnexoEmail {

        private int id;
        private String nome;
        private String tipo;
        private InputStream arquivo;

        public ArquivoAnexoEmail(ByteArrayDataSource pAnexo, int id) throws IOException {
            nome = pAnexo.getName();
            tipo = pAnexo.getContentType();
            arquivo = IOUtils.toBufferedInputStream(pAnexo.getInputStream());
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getTipo() {
            return tipo;
        }

        public InputStream getArquivo() {
            return arquivo;
        }

    }

    public static String substituirCidPorUrl(String texto, Map<String, String> mapaUrls) {
        Pattern r = Pattern.compile("((cid:)(\\w*.\\w*)@\\w*.\\w*)");

        // Now create matcher object.
        Matcher m = r.matcher(texto);

        while (m.find()) {
            String cidCompleta = m.group(0);
            String imagem = m.group(3);
            if (mapaUrls.get(imagem) != null) {
                texto = texto.replace(cidCompleta, mapaUrls.get(imagem));
            }

        }
        return texto;
    }

    public static List<ArquivoAnexoEmail> lerAnexos(Message m)
            throws IOException, MessagingException {
        MimeMessageParser conteudo;
        List<ArquivoAnexoEmail> listaanexos = new ArrayList<>();
        try {

            if (m instanceof MimeMessage) {
                MimeMessage mimeMensagem = (MimeMessage) m;

            }
            conteudo = new MimeMessageParser((MimeMessage) m);
            conteudo.parse();
            final DataSourceResolver dataSourceResolver = new DataSourceUrlResolver(new URL("http://www.apache.org"), false);

            for (Iterator iterator = conteudo.getAttachmentList().iterator(); iterator.hasNext();) {
                Object next = iterator.next();

                int id = 0;
                if (next instanceof ByteArrayDataSource) {

                    ByteArrayDataSource anexo = (ByteArrayDataSource) next;

                    System.out.println(anexo.getName());
                    listaanexos.add(new ArquivoAnexoEmail(anexo, id));
                    id++;
                    //UtilSBCoreOutputs.salvarArquivoBfInput(new BufferedInputStream(IOUtils.toBufferedInputStream(anexo.getInputStream())), "/home/superBits/temp/" + anexo.getName());
                }
                System.out.println(next);
                System.out.println(next.getClass().getSimpleName());
            }

            System.out.println(conteudo.getHtmlContent());
            System.out.println(conteudo.getPlainContent());

        } catch (Exception ex) {
            Logger.getLogger(UtilSBCoreEmailObjetos.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listaanexos;
    }

    public static String lerConteudo(Message m)
            throws IOException, MessagingException {
        MimeMessageParser conteudo;
        try {
            conteudo = new MimeMessageParser((MimeMessage) m);

            conteudo.parse();
            System.out.println(conteudo.getHtmlContent());
            System.out.println(conteudo.getPlainContent());
            return conteudo.getHtmlContent();

        } catch (Exception ex) {
            Logger.getLogger(UtilSBCoreEmailObjetos.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
