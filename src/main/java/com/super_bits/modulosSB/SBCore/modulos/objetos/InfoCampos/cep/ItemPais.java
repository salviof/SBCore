/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoPais;

/**
 *
 * @author desenvolvedor
 */
public class ItemPais extends ItemSimples implements ComoPais {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    private final List<ComoUnidadeFederativa> unidadesFederativas = new ArrayList<>();

    @Override
    public void setId(Long pID) {

        this.id = pID;

    }

    @Override
    public String getNome() {

        return this.nome;

    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

    @Override
    public List<ComoUnidadeFederativa> getUnidadesFederativas() {

        return this.unidadesFederativas;

    }

}
