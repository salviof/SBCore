/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.grafico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author salvio
 */
public class ItemGraficoTotalPorTipo extends ItemDadoGraficoTotal implements ItfDadoGraficoTotalTipo {

    private final ComoEntidadeSimples tipoEntidade;
    private final String urlDetalhes;

    public ItemGraficoTotalPorTipo(ComoEntidadeSimples pEntidade, double pValor, String pUrl) {
        super(pEntidade.getId(), pEntidade.getNome(), pValor);
        tipoEntidade = pEntidade;
        urlDetalhes = pUrl;
    }

    public ItemGraficoTotalPorTipo(ComoEntidadeSimples pEntidade, double pValor) {
        this(pEntidade, pValor, "#");
    }

    @Override
    public ComoEntidadeSimples getTipo() {
        return tipoEntidade;
    }

    @Override
    public Object getItemRelacionado() {
        return tipoEntidade;
    }

    public String getUrlDetalhes() {
        return urlDetalhes;
    }

}
