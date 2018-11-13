/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRestOAuth;
import java.util.Date;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.tipoModulos.integracaoOauth.FabPropriedadeModuloIntegracaoOauth;

/**
 *
 * @author SalvioF
 */
public class Oath2Conexao {

    private final FabTipoClienteOauth tipoCliente;
    private final ConfigModulo confiModulo;
    private final Class<? extends ItfFabricaIntegracaoRestOAuth> fabricaOauth;
    private String chavePublica;

    private String chavePrivada;
    private String siteCliente;
    private String siteServidor;
    private String codigoSolicitacao;
    private InfoTokenOauth2 tokenDeAcesso;

    public FabTipoClienteOauth getTipoCliente() {
        return tipoCliente;
    }

    public final void atualizarDados() {
        confiModulo.reloadConfiguracoes();
        chavePublica = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.CHAVE_PUBLICA);
        chavePrivada = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.CHAVE_PRIVADA);
        siteCliente = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.SITE_CLIENTE);
        siteServidor = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.SITE_SERVIDOR);

    }

    public boolean isCodigoSolicitacaoRegistrado() {
        return codigoSolicitacao != null;
    }

    public Oath2Conexao(Class<? extends ItfFabConfigModulo> arquivoConfiguracao, FabTipoClienteOauth pTipocliente, Class<? extends ItfFabricaIntegracaoRestOAuth> pFabricaOauth) {
        tipoCliente = pTipocliente;
        confiModulo = SBCore.getConfigModulo(arquivoConfiguracao);
        fabricaOauth = pFabricaOauth;
        atualizarDados();
    }

    public final boolean carregarTokenArmazenado(ItfFabricaIntegracaoRestOAuth api) {
        InfoTokenOauth2 tokenencontrado = null;
        switch (tipoCliente) {
            case USUARIO:
                tokenencontrado = api.getTokenArmazenadoUsuario();

                break;
            case SISTEMA:
                tokenencontrado = api.getTokenArmazenadoSistema();
                break;
            default:
                throw new AssertionError(tipoCliente.name());

        }
        if (tokenencontrado != null) {
            tokenDeAcesso = tokenencontrado;
            return true;
        } else {
            return false;
        }
    }

    public FabStatusToken getStatusToken() {

        if (getTokenDeAcesso() != null) {
            if (tokenDeAcesso.getDataHoraExpirarToken() == null) {
                return FabStatusToken.ATIVO;
            } else {
                if (tokenDeAcesso.getDataHoraExpirarToken().getTime() < new Date().getTime()) {
                    return FabStatusToken.EXPIRADO;
                } else {
                    return FabStatusToken.ATIVO;
                }
            }
        } else {
            if (codigoSolicitacao != null) {
                return FabStatusToken.SOLICITACAO_TOKEN_EM_ANDAMENTO;
            }
        }

        return FabStatusToken.SEM_TOKEN;
    }

    public boolean isPossuiTokenValido() {

        switch (getStatusToken()) {
            case ATIVO:
                return true;
            default:
                return false;
        }

    }

    public boolean atualizarTokenExpirado() {
        return false;
    }

    public String getUrlAutenticacao(ItfFabricaIntegracaoRestOAuth api) {
        return api.getUrlSolicitacaoAutenticacao();
    }

    private final ItfFabricaIntegracaoRestOAuth getFabricaAcesosMetodosPadrao() {
        return fabricaOauth.getEnumConstants()[0];
    }

    public final void gerarNovoToken(String pCodigoSolicitacao) {
        codigoSolicitacao = pCodigoSolicitacao;
        ItfFabricaIntegracaoRestOAuth api = fabricaOauth.getEnumConstants()[0];

        atualizarDados();
        if (isPossuiTokenValido()) {
            return;
        }

        if (codigoSolicitacao != null) {
            tokenDeAcesso = api.gerarNovoToken(codigoSolicitacao);
        } else {
            SBCore.getCentralDeMensagens().enviarMsgAvisoAoUsuario("O token não foi encontrado, acesse \n"
                    + api.getUrlSolicitacaoAutenticacao() + "\n para obter um token de acesso.");
        }
    }

    public ConfigModulo getConfiModulo() {
        return confiModulo;
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public String getSiteCliente() {
        return siteCliente;
    }

    public String getSiteServidor() {
        return siteServidor;
    }

    public String getCodigoSolicitacao() {
        return codigoSolicitacao;
    }

    public InfoTokenOauth2 getTokenDeAcesso() {
        if (tokenDeAcesso == null) {
            switch (tipoCliente) {
                case USUARIO:
                    tokenDeAcesso = getFabricaAcesosMetodosPadrao().getTokenArmazenadoUsuario();
                    break;
                case SISTEMA:
                    tokenDeAcesso = getFabricaAcesosMetodosPadrao().getTokenArmazenadoSistema();
                    break;
                default:
                    throw new AssertionError(tipoCliente.name());

            }

        }
        return tokenDeAcesso;
    }

    public void setCodigoSolicitacao(String codigoSolicitacao) {
        this.codigoSolicitacao = codigoSolicitacao;
    }

}
