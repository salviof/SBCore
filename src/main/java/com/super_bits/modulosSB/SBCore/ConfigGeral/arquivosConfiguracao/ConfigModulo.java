/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoEnuns;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 *
 *
 * @author SalvioF
 */
public class ConfigModulo extends ArquivoConfiguracaoModulo implements ItfConfigModulo {

    @Deprecated
    public ConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig, ClassLoader resourceLoader) throws IOException {
        this(pFabricaConfig);

    }

    public ConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) throws IOException {
        super(pFabricaConfig);
    }

    @Override
    public String getPropriedade(ItfFabConfigModulo pPropriedades) {
        return proppriedadesBasicas.getProperty(pPropriedades.getNomePropriedade());
    }

    public String getPropriedadePorAnotacao(ItfFabrica pPropriedades) {

        ItfFabConfigModulo atributo = UtilSBCoreReflexaoEnuns.getEnumConfigComEstaFabricaNaAnotacao(fabricaConfig, pPropriedades);
        if (atributo == null) {
            throw new UnsupportedOperationException("Atributo " + pPropriedades + "não parece ser um parametro esperado ");
        }
        return getPropriedade(atributo);

    }

    @Override
    public String toString() {
        String descricao = fabricaConfig.getSimpleName() + "/n";

        for (Iterator iterator = proppriedadesBasicas.entrySet().iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            descricao += next + "/n";
        }
        return descricao;
    }

}
