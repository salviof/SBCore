/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90
 */
package com.super_bits.modulosSB.SBCore.modulos.logeventos;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Geração de log em JSON de linha única (NDJSON), com colorização ANSI opcional
 * para saída em terminal.
 *
 * @author sfurbino
 */
public class UtilCRCLogJson {

    private static final String CAMINHO_TEMPLATE = "/modeloLog/modeloLogSystemOut.txt";
    private static final String RESET = "\033[0m";

    /**
     * Template puro, como está no arquivo. Carregado sob demanda.
     */
    private static String TEMPLATE_JSON_TEXTO = null;
    /**
     * Mesmo template envolvido em ANSI. Só populado se a cor estiver ativa.
     */
    private static String TEMPLATE_JSON_SYSTEM_OUT = null;

    /**
     * Resolvido uma vez no carregamento da classe.
     */
    private static final boolean COR_HABILITADA = resolverCor();

    private UtilCRCLogJson() {
    }

    // ------------------------------------------------------------------
    // API pública
    // ------------------------------------------------------------------
    /**
     * JSON puro, sem cor. Para arquivo, coletor, tela do sistema e testes.
     *
     * @param pTipo severidade da mensagem
     * @param pMensagem texto livre, escapado automaticamente
     * @return uma linha de JSON válido
     */
    public static String gerarJson(FabMensagens pTipo, String pMensagem) {
        return preencher(TEMPLATE_JSON_TEXTO(), pTipo, pMensagem);
    }

    /**
     * Linha de log para console, colorida conforme o ambiente permitir.
     *
     * @param pTipo severidade da mensagem
     * @param pMensagem texto livre, escapado automaticamente
     * @return JSON, envolvido em ANSI quando a cor estiver ativa
     */
    public static String gerarTextoSystemOut(FabMensagens pTipo, String pMensagem) {
        return preencher(gerarMensagem(pTipo), pTipo, pMensagem);
    }

    /**
     * Idem, forçando ou suprimindo a cor independentemente da detecção.
     *
     * @param pTipo severidade da mensagem
     * @param pMensagem texto livre, escapado automaticamente
     * @param pComCor true para aplicar ANSI
     * @return linha de log pronta para impressão
     */
    public static String gerarTextoSystemOut(FabMensagens pTipo, String pMensagem, boolean pComCor) {
        if (!pComCor) {
            return gerarJson(pTipo, pMensagem);
        }
        String colorido = pTipo.getCorTerminal() + TEMPLATE_JSON_TEXTO() + RESET;
        return preencher(colorido, pTipo, pMensagem);
    }

    // ------------------------------------------------------------------
    // Montagem
    // ------------------------------------------------------------------
    /**
     * Devolve o template apropriado ao tipo, já com a cor aplicada se for o
     * caso. Restam os marcadores [tipo], [dataHora] e [descricao].
     *
     * @param tipo severidade da mensagem
     * @return template pronto para as substituições finais
     */
    private static String gerarMensagem(FabMensagens tipo) {
        if (TEMPLATE_JSON_SYSTEM_OUT == null) {
            if (!COR_HABILITADA) {
                return TEMPLATE_JSON_TEXTO();
            }
            TEMPLATE_JSON_SYSTEM_OUT = "[cor]" + TEMPLATE_JSON_TEXTO() + RESET;
        }
        return TEMPLATE_JSON_SYSTEM_OUT.replace("[cor]", tipo.getCorTerminal());
    }

    /**
     * Substituição dos marcadores. A descrição é sempre a última, para que uma
     * mensagem contendo o texto de outro marcador não seja reprocessada.
     */
    private static String preencher(String pTemplate, FabMensagens pTipo, String pMensagem) {

        return pTemplate
                .replace("[tipo]", pTipo.name())
                .replace("[dataHora]", UtilCRCDataHora.getDataHoraString(
                        new Date(), UtilCRCDataHora.FORMATO_TEMPO.DATA_USUARIO))
                .replace("[descricao]", escaparJson(pMensagem));
    }

    // ------------------------------------------------------------------
    // Template
    // ------------------------------------------------------------------
    private static String TEMPLATE_JSON_TEXTO() {
        if (TEMPLATE_JSON_TEXTO == null) {
            TEMPLATE_JSON_TEXTO = carregarTemplate();
        }
        return TEMPLATE_JSON_TEXTO;
    }

    private static String carregarTemplate() {
        try (InputStream in = UtilCRCLogJson.class.getResourceAsStream(CAMINHO_TEMPLATE)) {
            if (in == null) {
                throw new IllegalStateException("Template ausente: " + CAMINHO_TEMPLATE);
            }
            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                sb.append(linha);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException("Falha lendo " + CAMINHO_TEMPLATE, e);
        }
    }

    // ------------------------------------------------------------------
    // Escape
    // ------------------------------------------------------------------
    /**
     * Escapa os caracteres que quebrariam a string JSON. Sem isso, uma exceção
     * contendo aspas ou quebra de linha invalida o log inteiro.
     */
    private static String escaparJson(String pValor) {
        if (pValor == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(pValor.length() + 16);
        for (int i = 0; i < pValor.length(); i++) {
            char c = pValor.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    // ------------------------------------------------------------------
    // Detecção de terminal
    // ------------------------------------------------------------------
    private static boolean resolverCor() {
        String prop = System.getProperty("crc.log.cor");
        if (prop != null) {
            return Boolean.parseBoolean(prop);
        }
        if (System.getenv("NO_COLOR") != null) {
            return false;
        }
        return System.console() != null;
    }
}
