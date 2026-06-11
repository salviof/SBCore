/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ComoEntidadeGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import org.coletivojava.fw.utilCoreBase.UtilCRCReflexaoObjetoSimples;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.proxy.LazyInitializer;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCReflexaoObjeto extends UtilCRCReflexaoObjetoSimples {

    public static boolean isDetached(Object pEntidade) {
        // proxy não inicializado + sem sessão = detached

        if (pEntidade instanceof HibernateProxy) {
            LazyInitializer li = ((HibernateProxy) pEntidade).getHibernateLazyInitializer();
            return li.isUninitialized() && li.getSession() == null;
        }
        return false;
    }

    public static boolean getClasseTemProxy(String pNomeClasse) {
        //compativel com chamadas em sistemas sem Dependencia com hibernate

        return pNomeClasse.indexOf("$") > 0;
    }

    public static Class getClassExtraindoProxy(String pNomeClasse) {
        if (pNomeClasse.indexOf("$") <= 0) {
            return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNomeClasse);
        }
        String nomeClasse = UtilCRCStringBuscaTrecho.getStringAteEncontrarIsto(pNomeClasse, "$");
        //TODO avaliar substituição por:  Hibernate.getClass(pNomeClasse) ou HibernateProxyHelper., porém precisa validar se o objeto é um objeto registrado do projeto
        // como esse método precisa funcionar em projetos sem hibernate, talvez Hibernate.getClass não faça sentido
        return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomeClasse);
    }

    public static String getClasseDiscriminatoriaPolimorfismoDeEntidade(ComoEntidadeSimples pEntidade) {
        String nomeColuna;
        Optional<Class> classeBaseEncontrada = UtilCRCReflexao.getClasseESubclassesAteClasseBaseDeEntidade(pEntidade.getClass()).stream().filter(
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

        return UtilCRCReflexao.isInterfaceImplementadaNaClasse(pClasse, pInterface);

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
