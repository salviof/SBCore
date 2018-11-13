/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.InfoConsumoRestService;

/**
 *
 * Fabrica pronta para ser utilizada com o consumo de webservice forncido pelo
 * sistema de integração
 *
 *
 * @author desenvolvedor
 */
public interface ItfFabricaIntegracaoRestBasico {

    public default InfoConsumoRestService getInformacoesConsumo() {
        return UtilSBCoreClienteRest.getInformacoesConsumoRest(this);
    }

    public default FabTipoConexaoRest getTipoRequisicao() {
        if (getInformacoesConsumo().tipoConexao().equals(FabTipoConexaoRest.INDETERMINADO)) {
            if (this.toString().contains("CTR")) {
                return FabTipoConexaoRest.PUT;
            }
            if (this.toString().contains("SELECAO")) {
                return FabTipoConexaoRest.PATCH;
            }

            return FabTipoConexaoRest.GET;

        } else {
            return getInformacoesConsumo().tipoConexao();
        }
    }

}
