/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRestBasico;

/**
 *
 * @author desenvolvedor
 */
public abstract class ConexaoClienteWebServiceBasico implements ItfConexaoWebServiceSimples {

    protected ItfFabricaIntegracaoRestBasico fabricaIntegracao;

    public ConexaoClienteWebServiceBasico(ItfFabricaIntegracaoRestBasico conexao) {
        this.fabricaIntegracao = conexao;
    }

}
