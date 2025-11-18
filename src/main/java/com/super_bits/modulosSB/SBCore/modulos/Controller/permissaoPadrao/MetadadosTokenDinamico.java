/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.permissaoPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author salvio
 */
public class MetadadosTokenDinamico {

    private String leadNome;
    private String leadTelefone;
    private String leadEmail;
    private String leadWhatsappId;
    private String urlRetorno;
    private String assinaturaSistema;
    private String sistemaRequisicao;

    public MetadadosTokenDinamico(String pDadosCodificadosBase64) {
        byte[] decodedBytes = Base64.getDecoder().decode(pDadosCodificadosBase64);
        String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);
        JsonObject dados = UtilSBCoreJson.getJsonObjectByTexto(decodedJson);
        if (!dados.isEmpty()) {
            if (dados.containsKey("leadNome")) {
                leadNome = dados.getString("leadNome");
            }
            if (dados.containsKey("leadTelefone")) {
                leadTelefone = dados.getString("leadTelefone");
            }
            if (dados.containsKey("leadEmail")) {
                leadEmail = dados.getString("leadEmail");
            }
            if (dados.containsKey("urlRetorno")) {
                urlRetorno = dados.getString("urlRetorno");
            }
            if (dados.containsKey("assinaturaSistema")) {
                assinaturaSistema = dados.getString("assinaturaSistema");
            }
            if (dados.containsKey("sistemaRequisicao")) {
                sistemaRequisicao = dados.getString("sistemaRequisicao");
            }
            if (dados.containsKey("leadWhatsappId")) {
                leadWhatsappId = dados.getString("leadWhatsappId");
            }

        }
    }

    public MetadadosTokenDinamico() {

    }

    @Override
    public String toString() {
        String json = UtilSBCoreJson.getTextoByJsonObjeect(getJson());
        String base64Encoded = Base64.getEncoder()
                .encodeToString(json.getBytes(StandardCharsets.UTF_8));
        return base64Encoded;
    }

    public JsonObject getJson() {
        JsonObjectBuilder jbMetadadosToken = Json.createObjectBuilder();
        if (leadNome != null && !leadNome.isEmpty()) {
            jbMetadadosToken.add("leadNome", leadNome);
        }
        if (leadTelefone != null && !leadTelefone.isEmpty()) {
            jbMetadadosToken.add("leadTelefone", leadTelefone);
        }
        if (leadEmail != null && !leadEmail.isEmpty()) {
            jbMetadadosToken.add("leadEmail", leadEmail);
        }
        if (urlRetorno != null && !urlRetorno.isEmpty()) {
            jbMetadadosToken.add("urlRetorno", urlRetorno);
        }
        if (assinaturaSistema != null && !assinaturaSistema.isEmpty()) {
            jbMetadadosToken.add("assinaturaSistema", assinaturaSistema);
        }
        if (sistemaRequisicao != null && !sistemaRequisicao.isEmpty()) {
            jbMetadadosToken.add("sistemaRequisicao", sistemaRequisicao);
        }

        if (leadWhatsappId != null && !leadWhatsappId.isEmpty()) {
            jbMetadadosToken.add("leadWhatsappId", leadWhatsappId);
        }

        return jbMetadadosToken.build();
    }

    public String getLeadNome() {
        return leadNome;
    }

    public void setLeadNome(String leadNome) {
        this.leadNome = leadNome;
    }

    public String getLeadTelefone() {
        return leadTelefone;
    }

    public void setLeadTelefone(String leadTelefone) {
        this.leadTelefone = leadTelefone;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getUrlRetorno() {
        return urlRetorno;
    }

    public void setUrlRetorno(String urlRetorno) {
        this.urlRetorno = urlRetorno;
    }

    public String getAssinaturaSistema() {
        return assinaturaSistema;
    }

    public void setAssinaturaSistema(String assinaturaSistema) {
        this.assinaturaSistema = assinaturaSistema;
    }

    public String getSistemaRequisicao() {
        return sistemaRequisicao;
    }

    public void setSistemaRequisicao(String sistemaRequisicao) {
        this.sistemaRequisicao = sistemaRequisicao;
    }

}
