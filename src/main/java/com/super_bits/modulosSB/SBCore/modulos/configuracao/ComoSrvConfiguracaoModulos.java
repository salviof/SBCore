/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.configuracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModuloDetalhes;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvio
 */
public interface ComoSrvConfiguracaoModulos {

    public ConfigModulo getConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig);

    public String getValor(ItfFabConfigModulo pTipoVariavelAmbiente);

    public List<ConfigModuloDetalhes> getDetalhesModulosAtivos();

}
