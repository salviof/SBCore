/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
public enum FabSMSIntegracao implements ItfFabricaIntegracaoRest<ItfUsuario> {

    ENVIARMENSAGEM;

    @Override
    public String getCaminhoServico(String... parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrlServidor() {
        return "sns.us-east-1.amazonaws.com";
    }

    @Override
    public String getCorpoRequisicao(String... parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
