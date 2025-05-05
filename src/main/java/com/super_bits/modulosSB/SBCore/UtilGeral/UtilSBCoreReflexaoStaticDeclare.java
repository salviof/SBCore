/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvio
 */
public class UtilSBCoreReflexaoStaticDeclare {

    public static List<Field> getObjetosEstaticosDestaClasse(Class<?> classe, Class classePesquisa) {
        List<Field> camposEncontrados = new ArrayList<>();

        for (Field field : classe.getFields()) { // somente campos públicos
            int modifiers = field.getModifiers();

            if (Modifier.isStatic(modifiers) && classePesquisa.isAssignableFrom(field.getType())) {
                camposEncontrados.add(field); // Deve ser ignorado porque estamos acessando somente campos públicos
            }
        }

        return camposEncontrados;
    }

    public static List<ItfBeanSimples> getEntidadesEstaticasDeclaradas(Class pClasse) {
        List<Field> campos = getObjetosEstaticosDestaClasse(pClasse, ItfBeanSimples.class);
        List<ItfBeanSimples> entidadesEncontradas = new ArrayList<>();
        for (Field cp : campos) {
            Object value;
            try {
                value = cp.get(null); // campos estáticos: passa null
                if (value != null) {
                    entidadesEncontradas.add((ItfBeanSimples) value);
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UtilSBCoreReflexaoStaticDeclare.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UtilSBCoreReflexaoStaticDeclare.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return entidadesEncontradas;
    }
}
