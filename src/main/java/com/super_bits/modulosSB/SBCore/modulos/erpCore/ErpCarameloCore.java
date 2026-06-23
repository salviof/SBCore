/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.erpCore;

import com.super_bits.modulosSB.SBCore.modulos.erp.ErroJsonInterpredador;
import com.super_bits.modulosSB.SBCore.modulos.erp.InfoApiERPCarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.erp.ItfApiErpSuperBits;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import jakarta.json.JsonObject;

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
        switch (this) {

            case CORE_PADRAO:
                return ItfApiErpSuperBits.super.getDTO(pJson, pItefaceObjeto);
            case COREJUIT:
            case COREWEB:
            case CORE_ANDROID:
            case CORE_MODEL_SERVICE:
            case CORE_DESKTOP:
                return CORE_PADRAO.getDTO(pJson, pItefaceObjeto);

            default:
                throw new AssertionError();
        }

    }

}
