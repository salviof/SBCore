/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import java.util.Date;

/**
 *
 * @author desenvolvedor
 */
public class InfoSatusObjetoDoSistemaAtual {

    private final EstruturaDeEntidade estrutura;
    private final Class classeObjeto;
    private Date dataUltimaAtualizacao;
    private int quantidadeRegistro;

    private void atualizaUltimaAtualizacao() {
        dataUltimaAtualizacao = new Date();
    }

    public InfoSatusObjetoDoSistemaAtual(EstruturaDeEntidade pEstrutura, Class pClasseObjeto) {
        estrutura = pEstrutura;
        classeObjeto = pClasseObjeto;
    }

    public void atualizarInfomacoes() {
        SBCore.getCentralFonteDeDados().atualizarInformacoesDeObjeto(this);
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public int getQuantidadeRegistro() {
        return quantidadeRegistro;
    }

    public void setQuantidadeRegistro(int quantidadeRegistro) {
        this.quantidadeRegistro = quantidadeRegistro;
    }

    public EstruturaDeEntidade getEstrutura() {
        return estrutura;
    }

    public Class getClasseObjeto() {
        return classeObjeto;
    }

}
