/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;

/**
 *
 * @author salvio
 */
public class VariavelAmbienteModulo {

    public VariavelAmbienteModulo(ItfFabConfigModulo pVariavel) {
        nomeEmum = pVariavel.toString();
        valorPadrao = pVariavel.getValorPadrao();

        demonstracaoValor = UtilCRCStringFiltros.getPrimeirasXLetrasDaString(pVariavel.getValorParametroSistema(), 10) + "...";
        nomeVariavelAmbiente = ConfigModulo.getNomeCompleto(pVariavel);
        nomeLegado = pVariavel.toString();

    }

    private String nomeEmum;
    private String valorPadrao;
    private String nomeVariavelAmbiente;
    private String nomeLegado;

    private String demonstracaoValor;
    boolean variavelEmArquivoEncontrada;
    boolean variavelEmVariavemDeAmbienteEncontrada;
    private String tipoDeclaracao;

    public String getNomeEmum() {
        return nomeEmum;
    }

    public String getNomeVariavelAmbiente() {
        return nomeVariavelAmbiente;
    }

    public String getNomeLegado() {
        return nomeLegado;
    }

    public String getDemonstracaoValor() {
        return demonstracaoValor;
    }

    public String getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(String valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public boolean isVariavelEmArquivoEncontrada() {
        return variavelEmArquivoEncontrada;
    }

    public void setVariavelEmArquivoEncontrada(boolean variavelEmArquivoEncontrada) {
        this.variavelEmArquivoEncontrada = variavelEmArquivoEncontrada;
    }

    public boolean isVariavelEmVariavemDeAmbienteEncontrada() {
        return variavelEmVariavemDeAmbienteEncontrada;
    }

    public void setVariavelEmVariavemDeAmbienteEncontrada(boolean variavelEmVariavemDeAmbienteEncontrada) {
        this.variavelEmVariavemDeAmbienteEncontrada = variavelEmVariavemDeAmbienteEncontrada;
    }

    public String getTipoDeclaracao() {
        return tipoDeclaracao;
    }

    public void setTipoDeclaracao(String tipoDeclaracao) {
        this.tipoDeclaracao = tipoDeclaracao;
    }

}
