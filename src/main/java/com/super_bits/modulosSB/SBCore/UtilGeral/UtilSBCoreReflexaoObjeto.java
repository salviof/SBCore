/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ComoEntidadeGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreReflexaoObjetoSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreReflexaoObjeto extends UtilSBCoreReflexaoObjetoSimples {

    public static Class getClassExtraindoProxy(String pNomeClasse) {
        if (pNomeClasse.indexOf("$") <= 0) {
            return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeClasse);
        }
        String nomeClasse = UtilSBCoreStringBuscaTrecho.getStringAteEncontrarIsto(pNomeClasse, "$");
        return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasse);
    }

    public static String getClasseDiscriminatoriaPolimorfismoDeEntidade(ComoEntidadeSimples pEntidade) {
        String nomeColuna;
        Optional<Class> classeBaseEncontrada = UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(pEntidade.getClass()).stream().filter(
                classe -> classe.isAnnotationPresent(DiscriminatorColumn.class)).findFirst();
        if (classeBaseEncontrada.isPresent()) {

            Class classe = classeBaseEncontrada.get();
            DiscriminatorColumn campoDiscriminidor = (DiscriminatorColumn) classe.getAnnotation(DiscriminatorColumn.class);
            nomeColuna = campoDiscriminidor.name();
            return (String) pEntidade.getCampoInstanciadoByNomeOuAnotacao(nomeColuna).getValor();

        } else {
            return null;
        }

    }

    public static boolean classeImplementaInterface(Class pClasse, Class pInterface) {

        return UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClasse, pInterface);

    }

    public static List<Class> getClassesEntidadeComHeranca(Class pClasse) {

        List<Class> classes = new ArrayList<>();

        Class classeAtual = pClasse;
        boolean temEntidade = pClasse.getAnnotation(Entity.class) != null;
        while (temEntidade) {

            if (classeAtual == ComoEntidadeGenerica.class
                    || classeAtual == Object.class) {
                return classes;
            }

            classes.add(classeAtual);
            classeAtual = classeAtual.getSuperclass();
            temEntidade = classeAtual.getAnnotation(Entity.class) != null;
        }
        return classes;
    }
}
