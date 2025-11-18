package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.contato.ComoContatoCorporativo;

public class ItemContatoCorporativo extends ItemEndereco implements
        ComoContatoCorporativo {

    public ItemContatoCorporativo() {
        super();

    }

    @Override
    public String getSite() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.SITE);
    }

    @Override
    public String telefone() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.TELEFONE_GENERICO);
    }

    @Override
    public String responsavel() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.RESPONSAVEL);
    }

    @Override
    public String getTelefonePrincipal() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.TELEFONE_GENERICO);
    }

    @Override
    public String getEmail() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.EMAIL);
    }

}
