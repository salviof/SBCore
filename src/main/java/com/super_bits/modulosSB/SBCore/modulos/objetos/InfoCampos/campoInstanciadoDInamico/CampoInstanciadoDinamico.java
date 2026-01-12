/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoInstanciadoGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoEditavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.lang.reflect.Field;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;

/**
 *
 *
 * @author desenvolvedor
 */
public abstract class CampoInstanciadoDinamico extends CampoInstanciadoGenerico {

    private final ItfAtributoObjetoSB campoDinamico;

    public CampoInstanciadoDinamico(Field pCampoValor,
            ItfAtributoObjetoEditavel pCampoDinamico
    ) {

        super(pCampoValor, pCampoDinamico);

        campoDinamico = pCampoDinamico;
    }

    @Override
    public List<ComoEntidadeSimples> getListaDeOpcoes() {
        return campoDinamico.getListaDeOpcoes();
    }

    public abstract ItfAtributoObjetoSB getAtributosCampoDinamico();

    @Override
    public ComoComponenteVisualSB getComponenteVisualPadrao() {
        return super.getComponenteVisualPadrao(); //chamada super do metodo (implementação classe pai)
    }

    private ItfCalculoValorLogicoAtributoObjeto logicaValorAutomatico;

    @Override
    public ItfCalculoValorLogicoAtributoObjeto getValorLogicaEstrategia() {
        if (logicaValorAutomatico != null) {
            return logicaValorAutomatico;
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(getValorPadrao())) {
            return null;
        }

        logicaValorAutomatico = UtilCRCReflexaoCampoLogicoDinamico.getLogicaValorCampo(getValorPadrao(), this);

        return logicaValorAutomatico;
    }

}
