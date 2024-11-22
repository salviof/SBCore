/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.grafico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author salvio
 */
public class ItemGraficoTotalPorTipo extends ItemDadoGraficoTotal implements ItfDadoGraficoTotalTipo {

    private final ItfBeanSimples tipoEntidade;

    public ItemGraficoTotalPorTipo(ItfBeanSimples pEntidade, double pValor) {
        super(pEntidade.getId(), pEntidade.getNome(), pValor);
        tipoEntidade = pEntidade;
    }

    @Override
    public ItfBeanSimples getTipo() {
        return tipoEntidade;
    }

}
