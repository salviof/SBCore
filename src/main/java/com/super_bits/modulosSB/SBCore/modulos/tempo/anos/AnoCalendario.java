/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo.anos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Ano", plural = "Anos")
public class AnoCalendario extends ItemSimples {

    public AnoCalendario(int pAno) {
        id = pAno;
    }

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        System.out.println("INSTANCIE UM NOVO ANO DE CALENDÁRIO, O ANO É DO TIPO FINAL");
    }

    @Override
    public String getNome() {
        nome = String.valueOf(id);
        return nome;
    }

}
