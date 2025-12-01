/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilCRCReflexaoCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.seletorMultiplo.ItfselecaoListaComOrigem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSeletorDeItens extends CampoInstanciadoComSelecao implements ItfCampoInstSeletorItens {

    private final CampoInstanciadoGenerico campoInstanciado;
    private final ItfselecaoListaComOrigem listaComListaOrigemDeObjetos;

    private ComoEntidadeSimples itemSelecionado;

    public CampoInstanciadoSeletorDeItens(CampoInstanciadoGenerico pCampoInstanciado) {
        super(UtilCRCReflexaoCaminhoCampo.getSelecaoItens(pCampoInstanciado), pCampoInstanciado);
        this.campoInstanciado = pCampoInstanciado;
        listaComListaOrigemDeObjetos = (ItfselecaoListaComOrigem) getSeletor();

    }

    @Override
    public ItfselecaoListaComOrigem getCampoSeletorItens() {

        return listaComListaOrigemDeObjetos;

    }

    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInstanciado;
    }

}
