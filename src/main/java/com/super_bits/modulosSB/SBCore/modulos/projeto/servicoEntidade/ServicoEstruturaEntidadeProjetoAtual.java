/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.projeto.servicoEntidade;

import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;

/**
 *
 * @author salvio
 */
public class ServicoEstruturaEntidadeProjetoAtual implements ComoServicoEstruturaEntidades {

    @Override
    public Class getEntidadeByName(String pNome) {
        return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNome);
    }

}
