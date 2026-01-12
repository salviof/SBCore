/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author salvio
 */
public class ItemSimilarComPrioridade extends ItemSimilar<ItemSimilarComPrioridade> {

    private final boolean prioritario;

    public ItemSimilarComPrioridade(ComoEntidadeSimples pObjetoAnalizado, String pTermpoPesquisa, boolean pPrioritario) {
        super(pObjetoAnalizado, pTermpoPesquisa);
        prioritario = pPrioritario;
    }

    @Override
    public int compareTo(ItemSimilarComPrioridade o) {

        if (prioritario && o.isPrioritario()) {
            return super.compareTo(o);
        }

        if (prioritario) {
            return 1;
        }

        return super.compareTo(o); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public boolean isPrioritario() {
        return prioritario;
    }

}
