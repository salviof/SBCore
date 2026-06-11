/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(plural = "Itens exemplos", tags = "Item baixinho")
public class ItemExemploTestes extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<ItemExemploTestes> subitensPublicos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PARTICULARES)
    private List<ItemExemploTestes> subItensExclusivos;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ItemExemploTestes itemPaisubItemPai;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ItemExemploTestes subItemFilho;

    public ItemExemploTestes() {
    }

    public ItemExemploTestes(ItemExemploTestes itemPaisubItemPai) {

        this.itemPaisubItemPai = itemPaisubItemPai;

    }

    public ItemExemploTestes(Long pID, String pNome) {
        id = pID;
        nome = pNome;
    }

    public ItemExemploTestes(Long pID, String pNome, ItemExemploTestes itemPaisubItemPai) {
        id = pID;
        nome = pNome;
        this.itemPaisubItemPai = itemPaisubItemPai;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemExemploTestes> getSubitensPublicos() {
        return subitensPublicos;
    }

    public void setSubitensPublicos(List<ItemExemploTestes> subitensPublicos) {
        this.subitensPublicos = subitensPublicos;
    }

    public List<ItemExemploTestes> getSubItensExclusivos() {
        return subItensExclusivos;
    }

    public void setSubItensExclusivos(List<ItemExemploTestes> subItensExclusivos) {
        this.subItensExclusivos = subItensExclusivos;
    }

    public ItemExemploTestes getItemPaisubItemPai() {
        return itemPaisubItemPai;
    }

    public void setItemPaisubItemPai(ItemExemploTestes itemPaisubItemPai) {
        this.itemPaisubItemPai = itemPaisubItemPai;
    }

    public ItemExemploTestes getSubItemFilho() {
        return subItemFilho;
    }

    public void setSubItemFilho(ItemExemploTestes subItemFilho) {
        this.subItemFilho = subItemFilho;
    }

    public static ItemExemploTestes gerarExemploPadrao() {

        ItemExemploTestes itemExemplo = new ItemExemploTestes();
        itemExemplo.setId(1l);
        itemExemplo.setNome("Exemplo Raiz");

        itemExemplo.setSubitensPublicos(new ArrayList<>());

        itemExemplo.getSubitensPublicos().add(new ItemExemploTestes(2l, "Subitem 1"));
        itemExemplo.getSubitensPublicos().add(new ItemExemploTestes(3l, "Subitem 2"));
        itemExemplo.getSubitensPublicos().add(new ItemExemploTestes(4l, "Subitem 3"));

        itemExemplo.setSubItensExclusivos(new ArrayList<>());
        itemExemplo.getSubItensExclusivos().add(new ItemExemploTestes(5l, "Subitem Exclusivo 1", itemExemplo));
        itemExemplo.getSubItensExclusivos().add(new ItemExemploTestes(6l, "Subitem Exclusivo 2", itemExemplo));
        itemExemplo.getSubItensExclusivos().add(new ItemExemploTestes(7l, "Subitem Exclusivo 3", itemExemplo));
        itemExemplo.getSubItensExclusivos().add(new ItemExemploTestes(8l, "Subitem Exclusivo 4", itemExemplo));

        itemExemplo.setSubItemFilho(new ItemExemploTestes(9l, "Subitem filho"));
        return itemExemplo;
    }

}
