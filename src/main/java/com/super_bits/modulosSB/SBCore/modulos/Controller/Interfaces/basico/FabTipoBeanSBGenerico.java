/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.basico;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoBeanSBGenerico implements ItfFabrica {

    BEAN_CONTATO_CORPORATIVO,
    BEAN_ENDERECO,
    BEAN_NORMAL,
    BEAN_SIMPLES;

    public String getNomeClasseEntidade() {

        switch (this) {

            case BEAN_CONTATO_CORPORATIVO:

                return "EntidadeContatoCorporativo";

            case BEAN_ENDERECO:

                return "EntidadeEndereco";

            case BEAN_NORMAL:

                return "EntidadeNormal";
            case BEAN_SIMPLES:

                return "EntidadeSimples";

            default:
                throw new AssertionError(this.name());

        }
    }

    @Override
    public ItemTipoEntidadeGenerico getRegistro() {
        ItemTipoEntidadeGenerico tipoGenericoEntidade = new ItemTipoEntidadeGenerico();
        tipoGenericoEntidade.setId(this.ordinal());
        switch (this) {
            case BEAN_CONTATO_CORPORATIVO:
                tipoGenericoEntidade.setNome("Contato corporativo");
                tipoGenericoEntidade.setDescricao("Um contato corporativo possui todos os campos de endereço, mais site, fanpage, e twiter");
            case BEAN_ENDERECO:
                tipoGenericoEntidade.setNome("Contato com endereço");
                tipoGenericoEntidade.setDescricao("Um contato com endereço possui todos os dados nescessários para entrega de cartas, mais seu telefone e posicionamento gepgráfico");
                break;
            case BEAN_NORMAL:
                tipoGenericoEntidade.setNome("Normal");
                tipoGenericoEntidade.setDescricao("Um Bean normal possui id, nome, descrição, imagem pequena media e granda ");

                break;
            case BEAN_SIMPLES:
                tipoGenericoEntidade.setNome("Simples");
                tipoGenericoEntidade.setDescricao("Um ben simples possui id, nome, e imagem pequena");

                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipoGenericoEntidade;
    }

}
