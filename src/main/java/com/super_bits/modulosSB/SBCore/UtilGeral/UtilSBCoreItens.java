/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreItens {

    /**
     *
     * Ordena os itens em ordem de id
     *
     * @param pLista Lista desordenada
     * @return Lista ordenada por id
     */
    public static List<ItfBeanSimples> ordernarPorId(List<ItfBeanSimples> pLista) {

        Map<Long, ItfBeanSimples> mapaItens = new HashMap();

        List<ItfBeanSimples> ordenado = new ArrayList<>();
        List<Long> ordem = new ArrayList<>();
        for (ItfBeanSimples item : pLista) {
            if (item.getId() != null) {
                mapaItens.put((Long) item.getId(), item);
                ordem.add(item.getId());
            } else {
                System.out.println("Enviou registro com id nulo para ser ordenado" + item);
            }
        }
        Collections.sort(ordem);

        for (Long id : ordem) {
            ordenado.add(mapaItens.get(id));
        }
        return ordenado;

    }

    public static String getValoresSeparadosPorVirgula(List<ItfBeanSimples> pLista) {
        if (UtilSBCoreListas.isNuloOuVazio(pLista)) {
            return "";
        } else {
            String resp = "";
            int i = 0;
            for (ItfBeanSimples itfBeanSimples : pLista) {
                if (i < pLista.size() - 1) {
                    resp += String.valueOf((itfBeanSimples.getId())) + ",";
                } else {
                    resp += String.valueOf((itfBeanSimples.getId()));
                }
                i++;
            }
            return resp;
        }

    }

}
