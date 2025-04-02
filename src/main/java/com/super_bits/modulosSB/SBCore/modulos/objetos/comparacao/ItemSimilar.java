/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author desenvolvedorninja01
 * @since 05/09/2019
 * @version 1.0
 */
public class ItemSimilar<T> extends ItemSimilarGenerico<T> {

    public ItemSimilar(ItfBeanSimples pObjetoAnalizado, String parametro) {
        super(pObjetoAnalizado, parametro, FabTipoPesquisaGenerica.NOME);

    }

    public ItemSimilar(ItfBeanSimples pObjetoAnalizado, String parametro, FabTipoPesquisaGenerica pTipo) {
        super(pObjetoAnalizado, parametro, pTipo);

    }

    boolean textoDefinido = false;
    private String texto;

    private String defineValorTextoReferenciaNormalizando(String pValor) {
        textoDefinido = true;
        texto = UtilSBCoreStringComparador.normalizarTexto(pValor);
        return texto;
    }

    private String defineValorTextoReferencia(String pValor) {
        textoDefinido = true;
        texto = pValor;
        return texto;
    }

    @Override
    public String getTextoReferencia() {
        if (textoDefinido) {
            return texto;
        }
        if (!(objetoAnalizado instanceof ItfBeanSimples)) {

            return defineValorTextoReferenciaNormalizando(objetoAnalizado.toString());
        }

        switch (tipoPesquisaGenerica) {

            case CODIGO:

                return defineValorTextoReferencia(String.valueOf(objetoAnalizado.getId()));

            case NOME:
                return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.NOME).getValor()));

            case DESCRICAO:
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.DESCRITIVO)) {
                    return String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.DESCRITIVO).getValor());
                }
                break;
            case TELEFONE:
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.TELEFONE_GENERICO)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.TELEFONE_GENERICO).getValor()));
                }
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.TELEFONE_CELULAR)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.TELEFONE_CELULAR).getValor()));
                }
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL).getValor()));
                }
                break;
            case EMAIL:
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.EMAIL)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.EMAIL).getValor()));
                }
                break;
            case SITE:
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.SITE)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.SITE).getValor()));
                }
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.URL)) {
                    return defineValorTextoReferenciaNormalizando(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.URL).getValor()));
                }
                break;
            case DOCUMENTO:
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.CNPJ)) {
                    return defineValorTextoReferencia(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.CPF).getValor()));
                }
                if (objetoAnalizado.isTemCampoAnotado(FabTipoAtributoObjeto.CPF)) {
                    return defineValorTextoReferencia(String.valueOf(objetoAnalizado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.CPF).getValor()));
                }

                break;
            default:
                throw new AssertionError();
        }
        return objetoAnalizado.getNome();
    }

}
