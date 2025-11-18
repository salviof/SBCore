/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author SalvioF
 */
public abstract class UtilSBCoreReflexaoEnuns {

    public static ComoFabrica getFAbricaAnotadaEmAnotacao(ComoFabrica pFabrica) {
        try {

            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            for (Annotation anotacao : campo.getAnnotations()) {
                for (Method metodo : anotacao.getClass().getMethods()) {

                    if (metodo.getReturnType().isAssignableFrom(ComoFabrica.class)) {
                        return (ComoFabrica) metodo.invoke(anotacao);
                    }
                }
            }
            return null;
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException | InvocationTargetException t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo propriedade do tipo Fabrica", t);
            return null;
        }

    }

    public static ComoFabrica getFAbricaAnotadaEmPropriedadeCOnfiguracao(ItfFabConfigModulo pFabrica) {
        try {

            Field campo = pFabrica.getClass().getField(pFabrica.toString());

            for (Annotation anotacao : campo.getAnnotations()) {
                Class classeANotacao = anotacao.annotationType();
                for (Method metodo : classeANotacao.getMethods()) {
                    String nomeMetodo = metodo.getName();
                    Class classeRetorno = metodo.getReturnType();
                    if (!nomeMetodo.equals("annotationType")) {
                        try {
                            ComoFabrica fab = (ComoFabrica) metodo.invoke(anotacao);
                            return fab;
                        } catch (Throwable t) {

                        }

                    }
                }
            }
            return null;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo propriedade do tipo Fabrica", t);
            return null;
        }

    }

    public static ComoFabrica getFabricaCOmEstaFabricaNaAnotacao(Class<? extends ComoFabrica> pFabricaEnum, ComoFabrica pFabrica) {

        for (ComoFabrica fab : pFabricaEnum.getEnumConstants()) {
            ComoFabrica fabricaAnotacao = getFAbricaAnotadaEmAnotacao(fab);
            if (fabricaAnotacao != null) {
                if (fabricaAnotacao.equals(pFabrica)) {
                    return fab;
                }
            }
        }
        return null;

    }

    public static ItfFabConfigModulo getEnumConfigComEstaFabricaNaAnotacao(Class<? extends ItfFabConfigModulo> pFabricaEnum, ComoFabrica pFabrica) {

        for (ItfFabConfigModulo prop : pFabricaEnum.getEnumConstants()) {
            ComoFabrica fabricaAnotacao = getFAbricaAnotadaEmPropriedadeCOnfiguracao(prop);
            if (fabricaAnotacao != null) {
                if (fabricaAnotacao.equals(pFabrica)) {
                    return prop;
                }
            }
        }
        return null;

    }

}
