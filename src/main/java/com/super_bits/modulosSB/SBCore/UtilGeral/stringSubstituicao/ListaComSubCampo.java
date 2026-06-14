/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author salvio
 */
public class ListaComSubCampo {

    private final List<ItemListaComSubcampos> itensOrdenadas = new ArrayList<>();
    private final String nomeLista;

    public ListaComSubCampo(String pNomeCompletoSubCampooriginal, String pValor) {

        nomeLista = gerarNomeLista(pNomeCompletoSubCampooriginal);
        adicionarNovoSubCampo(pNomeCompletoSubCampooriginal, pValor);
    }

    private String gerarNomeLista(String pNomeComplentoComSubCampo) {

        return UtilVariavelDeTemplate.gerarNomeLista(pNomeComplentoComSubCampo);
    }

    private String gerarNomeSubCampo(String pNomeComplentoComSubCampo) {
        return UtilVariavelDeTemplate.gerarNomeSubCampo(pNomeComplentoComSubCampo);
    }

    public void adicionarNovoSubCampo(String pSubCampo, String pValor) {
        ItemListaComSubcampos item = new ItemListaComSubcampos(pSubCampo, this, pValor);
        Optional<ItemListaComSubcampos> itemJaRegistrado = itensOrdenadas.stream().filter(it -> it.getIndice().equals(item.getIndice())).findFirst();
        if (itemJaRegistrado.isPresent()) {
            itemJaRegistrado.get().getSubCampos().put(gerarNomeSubCampo(pSubCampo), pValor);
        } else {
            itensOrdenadas.add(item);
        }
    }

    public List<ItemListaComSubcampos> getItensOrdenadas() {
        itensOrdenadas.sort((a, b) -> a.getIndice().compareTo(b.getIndice()));
        return itensOrdenadas;
    }

    public String getNomeLista() {
        return nomeLista;
    }

}
