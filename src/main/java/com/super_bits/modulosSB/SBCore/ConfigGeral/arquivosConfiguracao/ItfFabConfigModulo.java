/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;

/**
 *
 * @author SalvioF
 */
public interface ItfFabConfigModulo {

    public String getValorPadrao();

    public default String getValorParametroSistema() {
        String valor = SBCore.getConfigModulo(this.getClass()).getPropriedade(this);
        if (valor == null || valor.isEmpty()) {
            return getValorPadrao();
        }
        return valor;
    }

    /**
     *
     * @return @deprecated toString da propriedade é mais adequado, pois seguem
     * o padrão maiusculos de variavens de ambiente este método é considerado
     * legado.
     */
    @Deprecated
    public default String getNomePropriedade() {
        System.out.println("Obtendo parametro de módulo com sintaxe legada, o novo padrão deve ser um simples toString como:" + this.toString());
        return UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMinuscula(this.toString());
    }

    public default String getNomeModulo() {
        return this.getClass().getSimpleName();
    }

    public default String getCaminhoArquivoVariaveisAmbiente() {
        return UtilSBcoreModulos.getCaminhoAbsolutoDoContextoAtual(this.getClass());
    }
}
