/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
public class ListaDeEntidade extends ItemSimples {

    public ListaDeEntidade(String nomeEnum, String nomeDeclaracao, String nomeEntidade, String nomeObjetoListado, String javaDoc, EstruturaDeEntidade pEstrutura) {
        this.nomeEnum = nomeEnum;
        id = nomeEnum.hashCode();
        this.nomeDeclaracao = nomeDeclaracao;
        this.nomeEntidade = nomeEntidade;
        this.nomeObjetoListado = nomeObjetoListado;
        this.javaDoc = javaDoc;
        estrutura = pEstrutura;
    }
    private int id;
    private String javaDoc;
    private String nomeEntidade;
    private String nomeEnum;
    private String nomeDeclaracao;
    private String nomeObjetoListado;
    private EstruturaDeEntidade estrutura;

    public String getNomeObjetoListado() {
        return nomeObjetoListado;
    }

    public void setNomeObjetoListado(String nomeObjetoListado) {
        this.nomeObjetoListado = nomeObjetoListado;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getNomeEnum() {
        return nomeEnum;
    }

    public void setNomeEnum(String nomeEnum) {
        this.nomeEnum = nomeEnum;
    }

    public String getNomeDeclaracao() {
        return nomeDeclaracao;
    }

    public void setNomeDeclaracao(String nomeDeclaracao) {
        this.nomeDeclaracao = nomeDeclaracao;
    }

    public String getJavaDoc() {
        return javaDoc;
    }

    public void setJavaDoc(String javaDoc) {
        this.javaDoc = javaDoc;
    }

    public EstruturaDeEntidade getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(EstruturaDeEntidade estrutura) {
        this.estrutura = estrutura;
    }

    @Override
    public int getId() {
        return id;
    }

}
