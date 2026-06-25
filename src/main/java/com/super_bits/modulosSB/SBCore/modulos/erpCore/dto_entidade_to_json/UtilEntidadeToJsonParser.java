/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_entidade_to_json;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import jakarta.json.JsonObjectBuilder;

/**
 *
 * @author salvio
 */
public class UtilEntidadeToJsonParser {

    public static JsonObjectBuilder getJsonBuilderFrom(ComoEntidadeSimples pEntidade, int pNiveis) {
        EntidadeToJsonParser jsonParser = new EntidadeToJsonParser(pEntidade, pNiveis);
        return jsonParser.getJsonBuilder();
    }

    public static JsonObjectBuilder getJsonBuilderFrom(ComoEntidadeSimples pEntidade) {
        return getJsonBuilderFrom(pEntidade);
    }

}
