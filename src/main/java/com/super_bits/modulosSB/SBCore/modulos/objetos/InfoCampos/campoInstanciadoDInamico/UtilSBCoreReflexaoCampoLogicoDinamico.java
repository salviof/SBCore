/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciadoDInamico;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.calculos.ItfCalculoValorLogicoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author salvio
 */
public class UtilSBCoreReflexaoCampoLogicoDinamico {

    public static ItfCalculoValorLogicoAtributoObjeto getLogicaValorCampo(final String pNomeLogica, ItfCampoInstanciado pCampo) {

        if (pNomeLogica.endsWith(".class") || pNomeLogica.startsWith("ValorLogicoDD")) {
            try {

                String nomeClasse = pNomeLogica;
                nomeClasse = pNomeLogica.replace(".class", "");

                String classecanonica;
                if (nomeClasse.contains(".")) {
                    classecanonica = nomeClasse;
                } else {
                    if (!nomeClasse.startsWith("ValorLogicoDD")) {
                        nomeClasse = "ValorLogicoDD" + nomeClasse;
                    }
                    classecanonica = "org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico." + nomeClasse;
                }

                Class<? extends ItfCalculoValorLogicoAtributoObjeto> implementacaoCalculo
                        = (Class<? extends ItfCalculoValorLogicoAtributoObjeto>) SBCore.getClasseLoaderAplicacao().loadClass(classecanonica);

                ItfCalculoValorLogicoAtributoObjeto logicaValorAutomatico;
                logicaValorAutomatico = implementacaoCalculo.getConstructor(ItfCampoInstanciado.class).newInstance(pCampo);
                return logicaValorAutomatico;
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                return null;
            }

        } else {
            return null;
        }

    }
}
