/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.erpCore;

import com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_json_to_entidade.UtilJsonToEntidadeParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.super_bits.modulosSB.SBCore.modulos.erp.ErroJsonInterpredador;
import com.super_bits.modulosSB.SBCore.modulos.erp.InfoApiERPCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_entidade_to_json.EntidadeToJsonParser;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dto.ComoDTOCaramelo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dto.ComoEntidadeToJsonParser;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeReflexivel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import jakarta.json.JsonObjectBuilder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvio
 */
@InfoApiERPCarameloCode(descricaoApi = "Caramelo Core", nomeApi = "CarameloCore", slugInicial = "caramelocore")
public enum ErpCarameloCore implements ItfApiErpSuperBits<ComoCarameloCore> {

    CORE_PADRAO,
    COREWEB,
    COREJUIT,
    CORE_MODEL_SERVICE,
    CORE_ANDROID,
    CORE_DESKTOP;

    @Override
    public Class<? extends ComoCarameloCore> getInterface() {
        return ComoCarameloCore.class;
    }

    /**
     *
     * TODOS OS DTOS DO ErpCarameloCore, compartilham a mesma biblioteca de DTO
     *
     *
     * @param <I>
     * @param pJson
     * @param pItefaceObjeto
     * @return
     * @throws ErroJsonInterpredador
     */
    @Override
    public <I extends ComoEntidadeSimplesSomenteLeitura> I getDTO(String pJson, Class<? extends I> pItefaceObjeto) throws ErroJsonInterpredador {

        boolean teminterpretadorPersonalizado = false;

        if (teminterpretadorPersonalizado) {
            ComoDTOCaramelo dto = (ComoDTOCaramelo) ItfApiErpSuperBits.super.getDTO(pJson, pItefaceObjeto);
            if (dto.getObjetoDeserializado().getClass().getSimpleName().equals("DtoEntidadeCaramelo")) {
                return (I) ((ComoDTOCaramelo) dto.getObjetoDeserializado()).getObjetoDeserializado();
            }
        } else {

            ObjectMapper mapper = new ObjectMapper();

            try {
                return (I) UtilJsonToEntidadeParser.instanciarClasseEntidadeCaramelo(mapper.readTree(pJson));
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ErpCarameloCore.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        }
        return null;

    }

    public JsonObjectBuilder getJsonEntidade(ComoEntidadeSimples pEntidade) {
        return getJsonEntidade(pEntidade, 4);

    }

    public JsonObjectBuilder getJsonEntidade(ComoEntidadeSimples pEntidade, int niveis) {

        ComoEntidadeToJsonParser parser = new EntidadeToJsonParser(pEntidade, niveis);
        return parser.getJsonBuilder();

    }

}
