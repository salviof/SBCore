/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.text.WordUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 *
 * @author salvio
 */
public class UtilSBCoreHtmlFormat {

    public static String adicionarBReakLineACadaXCaracteres(final String pConteudo, int pNumeroLinhas) {
        if (pNumeroLinhas < 1) {
            return pConteudo;
        }
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pConteudo)) {
            return pConteudo;
        }
        return WordUtils.wrap(pConteudo, pNumeroLinhas, "</br>", false);
    }

    public static String getHyperlink(String pTexto, String pUrl) {
        StringBuilder conteudoBuilder = new StringBuilder();
        conteudoBuilder.append("<a href='");
        try {
            conteudoBuilder.append(new URL(pUrl).toString());
            conteudoBuilder.append("' >");
            conteudoBuilder.append(pTexto);
            conteudoBuilder.append("</a>");
            return conteudoBuilder.toString();
        } catch (MalformedURLException ex) {
            return pTexto + " (Url inválida)";
        }
    }

    public static String gerarMarkdownDeWhatsapp(String pHtml) {
        Document doc = Jsoup.parse(pHtml);

        // Substitui cabeçalhos por negrito (como alternativa para WhatsApp)
        for (Element el : doc.select("h1, h2")) {
            el.replaceWith(new TextNode("*" + el.text() + "*"));
        }

        // Negrito: <b> ou <strong>
        for (Element el : doc.select("b, strong")) {
            el.replaceWith(new TextNode("*" + el.text() + "*"));
        }

        // Itálico: <i> ou <em>
        for (Element el : doc.select("i, em")) {
            el.replaceWith(new TextNode("_" + el.text() + "_"));
        }

        // Riscado: <s>, <del>
        for (Element el : doc.select("s, del")) {
            el.replaceWith(new TextNode("~" + el.text() + "~"));
        }

        // Código: <code>
        for (Element el : doc.select("code")) {
            el.replaceWith(new TextNode("`" + el.text() + "`"));
        }

        // Links: <a href="...">texto</a> => texto (url)
        for (Element link : doc.select("a[href]")) {
            String texto = link.text();
            String url = link.attr("href");
            link.replaceWith(new TextNode(texto + ": " + url));
        }

        // Resultado final como texto plano
        return doc.text();
    }

    public static String retirarTagsDeHtml(String pHtml) {
        Document doc = Jsoup.parse(pHtml);

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String textoVisivel = link.text();
            String url = link.attr("href");
            // Substitui o conteúdo visível do <a> pelo href
            link.text(textoVisivel + ": " + url);
        }

        String resultado = doc.text();
        return resultado;

    }

}
