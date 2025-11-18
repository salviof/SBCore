/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;

/**
 *
 * @author desenvolvedor
 */
public class ItemBairro extends EntidadeSimples implements ComoBairro {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    private List<Long> coordenadas = new ArrayList<Long>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_CIDADE)
    private ItemCidade cidade;

    public ItemBairro() {
        cidade = new ItemCidade();
    }

    @Override
    public void setId(Long pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        configIDPeloNome();
        return this.nome;

    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

    @Override
    public List<Long> getCordenadas() {

        return this.coordenadas;

    }

    @Override
    public ComoCidade getCidade() {
        return cidade;
    }

    @Override
    public void setCidade(ComoCidade pCidade) {
        cidade = (ItemCidade) pCidade;
    }

}
