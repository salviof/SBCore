/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.HashMap;
import java.util.Map;

public class UtilCRCDesktopFontWasome {

    private static final Map<String, String> ICON_MAP = new HashMap<>();

    static {
        // User & People
        ICON_MAP.put("fa-user", "\uf007");
        ICON_MAP.put("fa-user-plus", "\uf234");
        ICON_MAP.put("fa-user-minus", "\uf503");
        ICON_MAP.put("fa-user-check", "\uf4fc");
        ICON_MAP.put("fa-user-times", "\uf235");
        ICON_MAP.put("fa-user-circle", "\uf2bd");
        ICON_MAP.put("fa-user-secret", "\uf21b");
        ICON_MAP.put("fa-users", "\uf0c0");
        ICON_MAP.put("fa-user-tie", "\uf508");

        // Interface / Actions
        ICON_MAP.put("fa-check", "\uf00c");
        ICON_MAP.put("fa-times", "\uf00d");
        ICON_MAP.put("fa-plus", "\uf067");
        ICON_MAP.put("fa-minus", "\uf068");
        ICON_MAP.put("fa-trash", "\uf1f8");
        ICON_MAP.put("fa-trash-alt", "\uf2ed");
        ICON_MAP.put("fa-edit", "\uf044");
        ICON_MAP.put("fa-pencil-alt", "\uf303");
        ICON_MAP.put("fa-save", "\uf0c7");
        ICON_MAP.put("fa-search", "\uf002");
        ICON_MAP.put("fa-filter", "\uf0b0");
        ICON_MAP.put("fa-sort", "\uf0dc");
        ICON_MAP.put("fa-copy", "\uf0c5");
        ICON_MAP.put("fa-cut", "\uf0c4");
        ICON_MAP.put("fa-paste", "\uf0ea");
        ICON_MAP.put("fa-undo", "\uf0e2");
        ICON_MAP.put("fa-redo", "\uf01e");
        ICON_MAP.put("fa-sync", "\uf021");
        ICON_MAP.put("fa-sync-alt", "\uf2f1");
        ICON_MAP.put("fa-lock", "\uf023");
        ICON_MAP.put("fa-unlock", "\uf09c");
        ICON_MAP.put("fa-eye", "\uf06e");
        ICON_MAP.put("fa-eye-slash", "\uf070");
        ICON_MAP.put("fa-ban", "\uf05e");
        ICON_MAP.put("fa-cog", "\uf013");
        ICON_MAP.put("fa-cogs", "\uf085");
        ICON_MAP.put("fa-wrench", "\uf0ad");
        ICON_MAP.put("fa-power-off", "\uf011");

        // Navigation / Arrows
        ICON_MAP.put("fa-arrow-left", "\uf060");
        ICON_MAP.put("fa-arrow-right", "\uf061");
        ICON_MAP.put("fa-arrow-up", "\uf062");
        ICON_MAP.put("fa-arrow-down", "\uf063");
        ICON_MAP.put("fa-chevron-left", "\uf053");
        ICON_MAP.put("fa-chevron-right", "\uf054");
        ICON_MAP.put("fa-chevron-up", "\uf077");
        ICON_MAP.put("fa-chevron-down", "\uf078");
        ICON_MAP.put("fa-angle-left", "\uf104");
        ICON_MAP.put("fa-angle-right", "\uf105");
        ICON_MAP.put("fa-home", "\uf015");
        ICON_MAP.put("fa-external-link-alt", "\uf35d");
        ICON_MAP.put("fa-map-marker-alt", "\uf3c5");
        ICON_MAP.put("fa-compass", "\uf14e");

        // Status / Feedback
        ICON_MAP.put("fa-check-circle", "\uf058");
        ICON_MAP.put("fa-times-circle", "\uf057");
        ICON_MAP.put("fa-exclamation-circle", "\uf06a");
        ICON_MAP.put("fa-exclamation-triangle", "\uf071");
        ICON_MAP.put("fa-info-circle", "\uf05a");
        ICON_MAP.put("fa-question-circle", "\uf059");
        ICON_MAP.put("fa-check-square", "\uf14a");
        ICON_MAP.put("fa-star", "\uf005");
        ICON_MAP.put("fa-star-half-alt", "\uf5c0");
        ICON_MAP.put("fa-heart", "\uf004");
        ICON_MAP.put("fa-thumbs-up", "\uf164");
        ICON_MAP.put("fa-thumbs-down", "\uf165");

        // Communication
        ICON_MAP.put("fa-envelope", "\uf0e0");
        ICON_MAP.put("fa-envelope-open", "\uf2b6");
        ICON_MAP.put("fa-bell", "\uf0f3");
        ICON_MAP.put("fa-bell-slash", "\uf1f6");
        ICON_MAP.put("fa-comment", "\uf075");
        ICON_MAP.put("fa-comment-alt", "\uf27a");
        ICON_MAP.put("fa-comments", "\uf086");
        ICON_MAP.put("fa-reply", "\uf3e5");
        ICON_MAP.put("fa-paper-plane", "\uf1d8");
        ICON_MAP.put("fa-phone", "\uf095");
        ICON_MAP.put("fa-phone-alt", "\uf879");
        ICON_MAP.put("fa-mobile-alt", "\uf3cd");

        // Files & Documents
        ICON_MAP.put("fa-file", "\uf15b");
        ICON_MAP.put("fa-file-alt", "\uf15c");
        ICON_MAP.put("fa-file-pdf", "\uf1c1");
        ICON_MAP.put("fa-file-word", "\uf1c2");
        ICON_MAP.put("fa-file-excel", "\uf1c3");
        ICON_MAP.put("fa-file-image", "\uf1c5");
        ICON_MAP.put("fa-file-code", "\uf1c9");
        ICON_MAP.put("fa-folder", "\uf07b");
        ICON_MAP.put("fa-folder-open", "\uf07c");
        ICON_MAP.put("fa-download", "\uf019");
        ICON_MAP.put("fa-upload", "\uf093");
        ICON_MAP.put("fa-cloud-download-alt", "\uf381");
        ICON_MAP.put("fa-cloud-upload-alt", "\uf382");
        ICON_MAP.put("fa-paperclip", "\uf0c6");
        ICON_MAP.put("fa-print", "\uf02f");

        // Business / ERP
        ICON_MAP.put("fa-briefcase", "\uf0b1");
        ICON_MAP.put("fa-building", "\uf1ad");
        ICON_MAP.put("fa-industry", "\uf275");
        ICON_MAP.put("fa-store", "\uf54e");
        ICON_MAP.put("fa-cash-register", "\uf788");
        ICON_MAP.put("fa-shopping-cart", "\uf07a");
        ICON_MAP.put("fa-shopping-bag", "\uf290");
        ICON_MAP.put("fa-tags", "\uf02c");
        ICON_MAP.put("fa-tag", "\uf02b");
        ICON_MAP.put("fa-barcode", "\uf02a");
        ICON_MAP.put("fa-qrcode", "\uf029");
        ICON_MAP.put("fa-box", "\uf466");
        ICON_MAP.put("fa-boxes", "\uf468");
        ICON_MAP.put("fa-truck", "\uf0d1");
        ICON_MAP.put("fa-warehouse", "\uf494");
        ICON_MAP.put("fa-dolly", "\uf472");
        ICON_MAP.put("fa-inventory", "\uf468");
        ICON_MAP.put("fa-chart-bar", "\uf080");
        ICON_MAP.put("fa-chart-line", "\uf201");
        ICON_MAP.put("fa-chart-pie", "\uf200");
        ICON_MAP.put("fa-table", "\uf0ce");
        ICON_MAP.put("fa-list", "\uf03a");
        ICON_MAP.put("fa-list-alt", "\uf022");
        ICON_MAP.put("fa-tasks", "\uf0ae");
        ICON_MAP.put("fa-clipboard", "\uf328");
        ICON_MAP.put("fa-clipboard-list", "\uf46d");
        ICON_MAP.put("fa-clipboard-check", "\uf46c");
        ICON_MAP.put("fa-calendar", "\uf133");
        ICON_MAP.put("fa-calendar-alt", "\uf073");
        ICON_MAP.put("fa-calendar-check", "\uf274");
        ICON_MAP.put("fa-clock", "\uf017");
        ICON_MAP.put("fa-history", "\uf1da");

        // Finance
        ICON_MAP.put("fa-dollar-sign", "\uf155");
        ICON_MAP.put("fa-euro-sign", "\uf153");
        ICON_MAP.put("fa-money-bill", "\uf0d6");
        ICON_MAP.put("fa-money-bill-alt", "\uf3d1");
        ICON_MAP.put("fa-credit-card", "\uf09d");
        ICON_MAP.put("fa-receipt", "\uf543");
        ICON_MAP.put("fa-file-invoice", "\uf570");
        ICON_MAP.put("fa-file-invoice-dollar", "\uf571");
        ICON_MAP.put("fa-percentage", "\uf541");
        ICON_MAP.put("fa-balance-scale", "\uf24e");
        ICON_MAP.put("fa-piggy-bank", "\uf4d3");
        ICON_MAP.put("fa-hand-holding-usd", "\uf4c0");

        // Tech / System
        ICON_MAP.put("fa-database", "\uf1c0");
        ICON_MAP.put("fa-server", "\uf233");
        ICON_MAP.put("fa-network-wired", "\uf6ff");
        ICON_MAP.put("fa-wifi", "\uf1eb");
        ICON_MAP.put("fa-cloud", "\uf0c2");
        ICON_MAP.put("fa-hdd", "\uf0a0");
        ICON_MAP.put("fa-desktop", "\uf108");
        ICON_MAP.put("fa-laptop", "\uf109");
        ICON_MAP.put("fa-tablet-alt", "\uf3fa");
        ICON_MAP.put("fa-code", "\uf121");
        ICON_MAP.put("fa-terminal", "\uf120");
        ICON_MAP.put("fa-bug", "\uf188");
        ICON_MAP.put("fa-key", "\uf084");
        ICON_MAP.put("fa-shield-alt", "\uf3ed");
        ICON_MAP.put("fa-robot", "\uf544");
        ICON_MAP.put("fa-microchip", "\uf2db");
        ICON_MAP.put("fa-plug", "\uf1e6");

        // Media
        ICON_MAP.put("fa-play", "\uf04b");
        ICON_MAP.put("fa-pause", "\uf04c");
        ICON_MAP.put("fa-stop", "\uf04d");
        ICON_MAP.put("fa-image", "\uf03e");
        ICON_MAP.put("fa-images", "\uf302");
        ICON_MAP.put("fa-video", "\uf03d");
        ICON_MAP.put("fa-music", "\uf001");
        ICON_MAP.put("fa-volume-up", "\uf028");
        ICON_MAP.put("fa-volume-mute", "\uf6a9");
        ICON_MAP.put("fa-camera", "\uf030");
        ICON_MAP.put("fa-microphone", "\uf130");
        ICON_MAP.put("fa-microphone-slash", "\uf131");
    }

    /**
     * Converte um nome de ícone FontAwesome (ex: "fa fa-user" ou "fa-user")
     * para o caractere Unicode correspondente, compatível com renderização via
     * fonte FontAwesome no Swing.
     *
     * @param pIconeFA nome do ícone FA (ex: "fa fa-check", "fa-check", "fas
     * fa-check")
     * @return caractere Unicode do ícone, ou "?" se não encontrado
     */
    public static String toUnicode(String pIconeFA) {
        if (pIconeFA == null || pIconeFA.isEmpty()) {
            return "?";
        }

        // Extrai apenas a parte "fa-nome" de strings como "fa fa-user" ou "fas fa-user"
        String chave = extrairChave(pIconeFA.trim().toLowerCase());

        String unicode = ICON_MAP.get(chave);
        if (unicode != null) {
            return unicode;
        }

        // Tenta sem prefixo "fa-" por tolerância
        unicode = ICON_MAP.get("fa-" + chave);
        if (unicode != null) {
            return unicode;
        }

        return "?";
    }

    /**
     * Mesma conversão, mas retorna um fallback customizado em vez de "?" quando
     * o ícone não é encontrado.
     */
    public static String toUnicode(String pIconeFA, String pFallback) {
        String resultado = toUnicode(pIconeFA);
        return "?".equals(resultado) ? pFallback : resultado;
    }

    /**
     * Retorna true se o ícone está mapeado no utilitário.
     */
    public static boolean isIconeMapeado(String pIconeFA) {
        if (pIconeFA == null || pIconeFA.isEmpty()) {
            return false;
        }
        return ICON_MAP.containsKey(extrairChave(pIconeFA.trim().toLowerCase()));
    }

    // -------------------------------------------------------------------------
    // Privado
    // -------------------------------------------------------------------------
    private static String extrairChave(String pInput) {
        // Separa tokens: "fa fa-user" → ["fa", "fa-user"] → pega o que começa com "fa-"
        for (String token : pInput.split("\\s+")) {
            if (token.startsWith("fa-")) {
                return token;
            }
        }
        // Sem prefixo de classe, assume que o próprio input é a chave
        return pInput;
    }
}
