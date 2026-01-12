/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.contato.ComoContatoHumano;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemEmail;

/**
 *
 * @author sfurbino
 */
public class ItemSimilarContato extends ItemSimilarGenerico {

    public ItemSimilarContato(ComoEntidadeSimples pObjetoAnalizado, String parametro) {

        super(pObjetoAnalizado, parametro, FabTipoPesquisaGenerica.PERSONALIZADA);

    }

    @Override
    public String getTextoReferencia() {
        try {
            if (termoPesquisa.contains("@")) {
                return ((ComoTemEmail) objetoAnalizado).getEmail();
            } else {
                return objetoAnalizado.getNome().toLowerCase();
            }
        } catch (Throwable t) {
            return null;
        }

    }

}
