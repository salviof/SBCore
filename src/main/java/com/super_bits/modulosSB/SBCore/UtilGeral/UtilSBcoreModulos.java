/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author SalvioF
 */
public abstract class UtilSBcoreModulos {

    public static String getCaminhoRelativoConfigModulo(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        String nomeAmigavel = pFabricaConfig.getSimpleName().replace("Fab", "");
        nomeAmigavel = nomeAmigavel.replaceAll("config", "");
        nomeAmigavel = nomeAmigavel.replaceAll("Config", "");
        return "modulos/" + nomeAmigavel + "/" + nomeAmigavel + ".prop";
    }

    public static String getCaminhoBaseContextoAtual() {

        switch (SBCore.getEstadoAPP()) {
            case DESENVOLVIMENTO:
                return SBCore.getCaminhoDesenvolvimento() + "/src/test/resources/";

            case PRODUCAO:
            case HOMOLOGACAO:
                return SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto();

            default:
                throw new AssertionError(SBCore.getEstadoAPP().name());

        }
    }

    public static String getCaminhoAbsolutoPastaDesenvolvimento(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        String caminhoRelativo = getCaminhoRelativoConfigModulo(pFabricaConfig);

        return "/home/superBits/desenvolvedor/configModuloTestes/" + caminhoRelativo;
    }

    public static String getCaminhoAbsolutoDoContextoAtual(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        if (SBCore.isEmModoDesenvolvimento()) {
            return getCaminhoAbsolutoPastaDesenvolvimento(pFabricaConfig);
        } else {
            return getCaminhoAbsolutoPastaProducaoEHomologacao(pFabricaConfig);
        }
    }

    public static String getCaminhoAbsolutoPastaProducaoEHomologacao(Class<? extends ItfFabConfigModulo> pFabricaConfig) {
        String caminhoRelativo = getCaminhoRelativoConfigModulo(pFabricaConfig);
        return getCaminhoBaseContextoAtual() + "/" + caminhoRelativo;
    }

}
