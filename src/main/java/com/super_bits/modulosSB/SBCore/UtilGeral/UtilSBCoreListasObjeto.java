/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilarGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparadorObjeto.ComparadorGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author salviofurbino
 * @since 12/10/2019
 * @version 1.0
 */
public class UtilSBCoreListasObjeto {

    public static void ordernarPorTipoCampo(List pLista, FabTipoAtributoObjeto pAtributo) {

        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, false);
        pLista.sort(cpGenerico);

    }

    public static void ordernarPorCampo(List pLista, String pAtributo) {

        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, false);
        pLista.sort(cpGenerico);

    }

    public static void ordernarPorCampoReverso(List pLista, String pAtributo) {

        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, true);
        pLista.sort(cpGenerico);

    }

    public static void ordernarPorTipoCampoReverso(List pLista, FabTipoAtributoObjeto pAtributo) {
        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, true);
        pLista.sort(cpGenerico);
    }

    public static <T> List<T> filtrarOrdenandoMaisParecidos(List<T> pLista, String pParametro, int pLimite) {
        List resp = new ArrayList();
        Map<Integer, ItemSimilar> itens = pLista.stream().parallel()
                .collect(Collectors.toMap(n -> ((ItfBeanSimples) n).getId(),
                        n -> new ItemSimilar((ItfBeanSimples) n, pParametro)));

//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilar> itensOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(itensOrdenados);
        Collections.reverse(itensOrdenados);
        itensOrdenados.stream().limit(pLimite).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;
    }

}
