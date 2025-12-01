/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;
import jakarta.json.JsonObject;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author SalvioF
 */
public class RecursosExternosPorIndice {

    private final String pastaRepositorio;

    public RecursosExternosPorIndice(Class<? extends ItfFabConfigModulo> pClasse) {
        if (SBCore.isEmModoProducao()) {
            pastaRepositorio = SBCore.getCentralDeArquivos().getEndrLocalResources() + "/" + pClasse.getSimpleName();
        } else {
            pastaRepositorio = UtilSBcoreModulos.DIRETORIO_ARQUIVOS_CONFIG_TESTE + "/" + SBCore.getGrupoProjeto()
                    + "/modulos/" + pClasse.getSimpleName();
        }

        if (!new File(pastaRepositorio).exists()) {
            new File(pastaRepositorio).mkdirs();
        }
    }

    public String getCaminhoArquivosRepositorio() {
        return pastaRepositorio;
    }

    private String getCaminhoArquivo(String pIndice) {
        return pastaRepositorio + "/" + pIndice;
    }

    public boolean putConteudoRecursoExterno(String pIndice, String pConteudo) {
        try {
            return UtilCRCArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(getCaminhoArquivo(pIndice), pConteudo);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro escrevendo em indice", t);
            return false;
        }
    }

    public String getTexto(String indice) {
        String arquivo = pastaRepositorio + "/" + indice;
        if (!new File(pastaRepositorio + "/" + indice).exists()) {
            return null;

        } else {
            return UTilSBCoreInputs.getStringByArquivoLocal(arquivo);
        }
    }

    public JsonObject getJsonObjeto(String pIndice) {
        try {
            String texto = getTexto(pIndice);
            if (UtilCRCStringValidador.isNuloOuEmbranco(texto)) {
                return JsonObject.EMPTY_JSON_OBJECT;
            }

            return UtilCRCJson.getJsonObjectByTexto(texto);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo objeto Json" + t.getMessage(), t);
            return null;
        }
    }

    public JSONArray getJsonListaObjeto(String pIndice) {
        try {
            String texto = getTexto(pIndice);
            if (texto == null) {
                return null;
            }
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(texto);
        } catch (ParseException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo objeto Json" + t.getMessage(), t);
            return null;
        }
    }

}
