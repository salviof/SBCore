/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsCammelCase;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.RecursosExternosPorIndice;

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

    public default RecursosExternosPorIndice getRepositorioDeArquivosExternos() {
        return SBCore.getConfigModulo(this.getClass()).getRepositorioDeArquivosExternos();
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
        return UtilCRCStringsCammelCase.getCamelByTextoPrimeiraLetraMinuscula(this.toString());
    }

    public default String getNomeModulo() {
        return this.getClass().getSimpleName();
    }

    public default String getCaminhoArquivoVariaveisAmbiente() {
        return UtilSBcoreModulos.getCaminhoAbsolutoDoContextoAtual(this.getClass());
    }
}
