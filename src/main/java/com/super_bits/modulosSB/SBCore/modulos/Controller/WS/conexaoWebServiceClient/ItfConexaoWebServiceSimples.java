/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.RespostaWebServiceRestIntegracao;

/**
 *
 * @author desenvolvedor
 */
public interface ItfConexaoWebServiceSimples {

    public RespostaWebServiceRestIntegracao getRespostaComoObjetoJson();

    public String getRespostaTexto();
}
