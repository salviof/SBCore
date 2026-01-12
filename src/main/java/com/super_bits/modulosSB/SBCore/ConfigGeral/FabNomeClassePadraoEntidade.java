/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;

/**
 *
 * @author salvio
 */
public enum FabNomeClassePadraoEntidade {

    LISTENER_ENTIDADE,
    ANOTACAO_VALORES_LOGICOS;

    public String getPrefixo() {
        switch (this) {

            case LISTENER_ENTIDADE:
                return "Listener";

            case ANOTACAO_VALORES_LOGICOS:
                return "ValorLogico";

            default:
                throw new AssertionError();
        }
    }

    public String getNomeClassseAtributoEntidade(Class<? extends ComoEntidadeSimples> pValor) {

        switch (this) {
            case LISTENER_ENTIDADE:
            case ANOTACAO_VALORES_LOGICOS:
                return getPrefixo() + pValor.getSimpleName();

            default:
                throw new AssertionError();
        }

    }

    public String getNomeClassseAtributoEntidade(EstruturaDeEntidade pEstrutura) {

        switch (this) {
            case LISTENER_ENTIDADE:
            case ANOTACAO_VALORES_LOGICOS:
                return getPrefixo() + pEstrutura.getNomeEntidade();
            default:
                throw new AssertionError();
        }

    }

}
