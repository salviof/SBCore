/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.configuracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigModuloTeste;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloDetalhes;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class ServicoConfiguracaoModuloBasicoTest {

    public ServicoConfiguracaoModuloBasicoTest() {
    }

    /**
     * Test of getConfigModulo method, of class ServicoConfiguracaoModuloBasico.
     */
    @Test
    public void testGetConfigModulo() {
        CarameloCode.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        String teste = CarameloCode.getServicoConfigModulo().getValor(FabConfigModuloTeste.NOME);
        List<ConfigModuloDetalhes> detalhes = CarameloCode.getServicoConfigModulo().getDetalhesModulosAtivos();
        for (ConfigModuloDetalhes configDetalhes : detalhes) {
            System.out.println(configDetalhes.getNome());
        }

    }

}
