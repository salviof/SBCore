/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRestBasico;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public interface ItfConexaoClientWebService extends ItfConexaoWebServiceSimples {

    public void adicionarHeaders(Map<String, String> pHeader);

    public void adicionarHeaders(Map<String, String> pHeader, boolean pLimparHeader);

    public void conectar();

}
