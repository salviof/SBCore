/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author salvio
 */
public class UtilCRCChaveValor {

    public static void saveAsEnv(Properties props, String destino) throws IOException {

        // Ordena para gerar arquivo determinístico
        Set<String> keys = new TreeSet<>(props.stringPropertyNames());

        try (BufferedWriter writer = Files.newBufferedWriter(new File(destino).toPath(), StandardCharsets.UTF_8)) {

            for (String key : keys) {

                if (key == null || key.trim().isEmpty()) {
                    continue;
                }

                String normalizedKey = normalizeKey(key);
                if (normalizedKey.isEmpty()) {
                    continue;
                }

                String value = props.getProperty(key);
                if (value == null) {
                    value = "";
                }

                String formattedValue = formatValue(value);

                writer.write(normalizedKey + "=" + formattedValue);
                writer.newLine();
            }
        }
    }

    private static String normalizeKey(String key) {
        // Remove espaços e converte para padrão ENV
        String k = key.trim().toUpperCase();

        // Substitui caracteres inválidos por _
        k = k.replaceAll("[^A-Z0-9_]", "_");

        // Não pode começar com número
        if (!k.isEmpty() && Character.isDigit(k.charAt(0))) {
            k = "_" + k;
        }

        return k;
    }

    private static String formatValue(String value) {

        if (value == null) {
            return "";
        }

        String v = value;

        // 1) Remove qualquer escape de aspas
        while (v.contains("\\\"")) {
            v = v.replace("\\\"", "\"");
        }

        // 2) Remove todas as camadas externas de aspas repetidas
        while (v.length() >= 2
                && v.startsWith("\"")
                && v.endsWith("\"")) {

            v = v.substring(1, v.length() - 1);
        }

        // 3) Verifica necessidade de aspas
        boolean needsQuotes
                = v.contains(" ")
                || v.contains("#")
                || v.contains("\n")
                || v.contains("\r")
                || v.startsWith(" ")
                || v.endsWith(" ");

        return needsQuotes ? "\"" + v + "\"" : v;
    }
}
