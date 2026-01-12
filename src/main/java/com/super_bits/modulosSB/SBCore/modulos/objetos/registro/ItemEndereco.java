package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;

public class ItemEndereco extends EntidadeNormal implements ComoEntidadeLocalizavel {

    /**
     *
     */
    private Double latitude;
    private Double longitude;
    //private LatLng localizacao;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ItemEndereco() {

        super();

        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.LATITUDE, "-19.8225864"));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.LONGITUDE, "-43.926274"));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.LCCEP, null), true);

        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.LC_LOGRADOURO, null));
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL, null));

    }

    private void setLongitude(Double pLongitude) {
        this.longitude = pLongitude;

    }

    private void setLatitude(Double pLatitude) {
        this.latitude = pLatitude;

    }

    private void setLocalizacao() {

        System.out.println("SETANDO LOCALIZACAO");

        if (latitude.intValue() != 0 & longitude.intValue() != 0) {
            //		localizacao = new LatLng(latitude, longitude);
        }

    }

    /**
     * public LatLng getLocalizacao() { setLatitude((Double)
     * GetValorByTipoCampoEsperado(TC.LAT) ); setLongitude((Double)
     * GetValorByTipoCampoEsperado(TC.LONG)); setLocalizacao(); return
     * localizacao; }
     *
     * @return
     */
    public String getComplemento() {

        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.LC_COMPLEMENTO_E_NUMERO);
    }

    @Override
    public String getTelefone() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.TELEFONE_FIXO_NACIONAL);
    }

    @Override
    public ComoLocal getLocalizacao() {
        return (ComoLocal) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.LC_LOCALIZACAO);
    }

    @Override
    public void setLocalizacao(ComoLocal pLocal) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.LC_LOCALIZACAO, pLocal);
    }

}
