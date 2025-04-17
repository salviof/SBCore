/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.email;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author salvio
 */
public enum FabConfigModuloEmailService implements ItfFabConfigModulo {

    EMAIL_SERVICE_USUARIO,
    EMAIL_SERVICE_SENHA,
    EMAIL_SERVICE_HOSTNAME,
    EMAIL_SERVICE_NOME_REMETENTE,
    EMAIL_SERVICE_EMAIL_REMETENTE,;

    @Override
    public String getValorPadrao() {
        switch (this) {

            case EMAIL_SERVICE_USUARIO:
                return "joao@coletivojava.com.br";
            case EMAIL_SERVICE_SENHA:
                return "minhaSenha";
            case EMAIL_SERVICE_HOSTNAME:
                return "servicoSMTP.coletivojava.com.br";
            case EMAIL_SERVICE_NOME_REMETENTE:
                return "João da Silva";
            case EMAIL_SERVICE_EMAIL_REMETENTE:
                return "joao@coletivojava.com.br";

            default:
                throw new AssertionError();
        }
    }
}
