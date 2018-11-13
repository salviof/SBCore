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
public class LigacaoMuitosParaMuitos extends EstruturaDeEntidade {

    private String joinTableName, joinColumName, inverseJoinColumName, nomeDeclarado;

    public String getJoinTableName() {
        return joinTableName;
    }

    public void setJoinTableName(String joinTableName) {
        this.joinTableName = joinTableName;
    }

    public String getJoinColumName() {
        return joinColumName;
    }

    public void setJoinColumName(String joinColumName) {
        this.joinColumName = joinColumName;
    }

    public String getInverseJoinColumName() {
        return inverseJoinColumName;
    }

    public void setInverseJoinColumName(String inverseJoinColumName) {
        this.inverseJoinColumName = inverseJoinColumName;
    }

    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

}
