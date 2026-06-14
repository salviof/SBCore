/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salvio
 */
public class ItemListaComSubcampos {

    private final ListaComSubCampo lista;
    private final Map<String, String> subCampos = new HashMap();
    private final int indice;

    public ItemListaComSubcampos(String pSubCampo, ListaComSubCampo pLista, String pValor) {
        lista = pLista;
        indice = obterIndice(pSubCampo);
        subCampos.put(obterNomeCampo(pSubCampo), pValor);

    }

    private int obterIndice(String pSubcampo) {
        String semColchetesExternos = UtilVariavelDeTemplate.removerColchetesExternos(pSubcampo);
        int abre = semColchetesExternos.indexOf("[");
        int fecha = semColchetesExternos.indexOf("]");
        if (abre < 0 || fecha < 0 || fecha <= abre + 1) {
            return 0;
        }
        return Integer.parseInt(semColchetesExternos.substring(abre + 1, fecha).trim());
    }

    private String obterNomeCampo(String pNomeCampo) {
        return UtilVariavelDeTemplate.gerarNomeSubCampo(pNomeCampo);
    }

    public ListaComSubCampo getLista() {
        return lista;
    }

    public Map<String, String> getSubCampos() {
        return subCampos;
    }

    public Integer getIndice() {
        return indice;
    }

}
