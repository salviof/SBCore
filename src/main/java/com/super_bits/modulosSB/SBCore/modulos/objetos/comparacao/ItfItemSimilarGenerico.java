/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;

/**
 *
 * @author sfurbino
 */
public interface ItfItemSimilarGenerico<T> extends Comparable<T> {

    public ComoEntidadeSimples getObjetoAnalizado();

    public String getTextoReferencia();

    public double getNota();
}
