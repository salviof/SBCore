/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth.InfoTokenOauth2;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth.Oath2Conexao;

/**
 *
 *
 *
 * @author SalvioF
 * @param <T>
 */
public interface ItfFabricaIntegracaoRestOAuth<T> extends ItfFabricaIntegracaoRest<T> {

    /**
     *
     * Implentar Syngleton de conexão , utilizando os métodos estáticos de
     * MapaInfoOauthEmAndamento
     *
     * @return
     */
    public Oath2Conexao getOauthConexao();

    public String getUrlRetornoSucesso();

    public String getUrlSolicitacaoAutenticacao();

    public String getUrlRetornoFalha();

    public InfoTokenOauth2 gerarNovoToken(String pSolicitacao);

    public InfoTokenOauth2 getTokenArmazenadoSistema();

    public InfoTokenOauth2 getTokenArmazenadoUsuario();

    @Override
    public String getCaminhoServico(String... parametros);

}
