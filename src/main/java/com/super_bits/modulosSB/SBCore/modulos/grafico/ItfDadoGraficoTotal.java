/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.grafico;

/**
 *
 * @author salvio
 */
public interface ItfDadoGraficoTotal extends ItfDadoGrafico {

    //public String getCor();
//    public String getLink();
    public boolean isTotalPorTipo();

    public boolean isTotalPorData();

    public ItfDadoGraficoTotalTipo getComoDadoGraficoTotalTipo();

    public ItfDadoGraficoTotalData getComoDadoGraficoTotalData();
}
