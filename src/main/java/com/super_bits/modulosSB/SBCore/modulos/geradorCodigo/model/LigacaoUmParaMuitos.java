/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

/**
 *
 * @author desenvolvedor
 */
public class LigacaoUmParaMuitos extends EstruturaDeEntidade {

    private String orphanRemove = "true";

    private String label, descricao, nomeDeclarado;

    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrphanRemove() {
        return orphanRemove;
    }

    public void setOrphanRemove(String orphanRemove) {
        this.orphanRemove = orphanRemove;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
