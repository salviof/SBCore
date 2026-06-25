/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_entidade_to_json;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author salvio
 */
public class MetadadosParserEntidadeTransitoria implements ComoMetaDadosParserDeEntidade {

    @Override
    public boolean isEmLazyMode(ComoEntidadeSimplesSomenteLeitura pEntidade, String nomeAtributo) {
        return false;
    }

    @Override
    public String getLinkLoadEntidade(ComoEntidadeSimplesSomenteLeitura pEntidade, String nomeAtributo) {
        return "Lasymode de objetos Transient ainda não foram implementados para " + pEntidade.getClass().getSimpleName();
    }

}
