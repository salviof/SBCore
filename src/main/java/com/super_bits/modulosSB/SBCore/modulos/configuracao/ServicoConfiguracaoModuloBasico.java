/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.configuracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import static com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore.getClasseLoaderAplicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloDetalhes;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCSystemOut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class ServicoConfiguracaoModuloBasico implements ComoSrvConfiguracaoModulos {

    private static final Map<Class<? extends ItfFabConfigModulo>, ConfigModulo> MAPA_CONFIGURACOES_MODULO = new HashMap<>();

    @Override
    public ConfigModulo getConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) {

        ConfigModulo config = null;

        try {
            if (!MAPA_CONFIGURACOES_MODULO.containsKey(pFabricaConfig)) {
                MAPA_CONFIGURACOES_MODULO.put(pFabricaConfig, new ConfigModulo(pFabricaConfig, getClasseLoaderAplicacao()));
                if (!SBCore.isEmModoProducao()) {
                    if (config != null) {
                        UtilCRCSystemOut.exibirMensagemEmDestaque("Um módulo foi configurado: " + config.toString());
                    }
                }
            }
            config = MAPA_CONFIGURACOES_MODULO.get(pFabricaConfig);
            return config;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo configuração do modulo", t);
            return null;
        } finally {

        }

    }

    @Override
    public String getValor(ItfFabConfigModulo pTipoVariavelAmbiente) {
        return CarameloCode.getConfigModulo(pTipoVariavelAmbiente.getClass()).getPropriedade(pTipoVariavelAmbiente);
    }

    @Override
    public List<ConfigModuloDetalhes> getDetalhesModulosAtivos() {
        List<ConfigModuloDetalhes> configuracoes = new ArrayList<>();

        for (ConfigModulo configMod : MAPA_CONFIGURACOES_MODULO.values()) {
            configuracoes.add(new ConfigModuloDetalhes(configMod.getFabricaConfig()));
        }

        return configuracoes;
    }

}
