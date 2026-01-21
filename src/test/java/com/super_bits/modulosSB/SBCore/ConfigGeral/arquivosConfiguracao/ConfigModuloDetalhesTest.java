/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigModuloTeste;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ConfigModuloDetalhesTest {

    public ConfigModuloDetalhesTest() {
    }

    /**
     * Test of getId method, of class ConfigModuloDetalhes.
     */
    @Test
    public void testGetId() {
        CarameloCode.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ConfigModulo configuracao = SBCore.getConfigModulo(FabConfigModuloTeste.class);
        configuracao.getPropriedade(FabConfigModuloTeste.NOME);
        ConfigModuloDetalhes configuraca = new ConfigModuloDetalhes(FabConfigModuloTeste.class);

        System.out.println(configuraca.getCaminhoArquivoVariaveis());
        System.out.println(configuraca.getNomeFabrica());
        for (VariavelAmbienteModulo variavel : configuraca.getVariaveisAmbiente()) {
            System.out.println(variavel.getNomeEmum());
            System.out.println(variavel.getValorPadrao());
            System.out.println(variavel.getNomeVariavelAmbiente());
        }

    }

}
