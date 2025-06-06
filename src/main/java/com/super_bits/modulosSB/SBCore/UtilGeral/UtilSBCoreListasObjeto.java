/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.FabTipoPesquisaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilarComPrioridade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparadorObjeto.ComparadorGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparadorObjeto.ComparadorGenericoListaEspecialPrimeiro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparadorObjeto.ComparadorPorId;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author salviofurbino
 * @since 12/10/2019
 * @version 1.0
 */
public class UtilSBCoreListasObjeto {

    private static void ordernarPorTipoCampo(List pLista, FabTipoAtributoObjeto pAtributo, boolean pReverso) {
        ComparadorGenerico comparador = null;
        switch (pAtributo) {
            case ID:
                comparador = new ComparadorPorId(pReverso);
                break;
            default:
                ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, false);

        }

        pLista.sort(comparador);

    }

    public static void ordernarPorTipoCampo(List pLista, FabTipoAtributoObjeto pAtributo) {
        ordernarPorTipoCampo(pLista, pAtributo, false);
    }

    public static void ordernarPorTipoCampoReverso(List pLista, FabTipoAtributoObjeto pAtributo) {
        ordernarPorTipoCampo(pLista, pAtributo, true);

    }

    public static void ordernarPorCampo(List pLista, String pAtributo) {

        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, false);
        pLista.sort(cpGenerico);

    }

    public static void ordernarPorCampoComSelecionadosPrimeiro(List pLista, FabTipoAtributoObjeto pAtributo, List pSelecao) {
        if (pSelecao != null && !pSelecao.isEmpty()) {
            ComparadorGenericoListaEspecialPrimeiro cpGenerico = new ComparadorGenericoListaEspecialPrimeiro(pAtributo, pSelecao, false);
            pLista.sort(cpGenerico);
        } else {
            ordernarPorTipoCampo(pLista, pAtributo);
        }

    }

    public static <T> List<T> filtrarPorCampoComSelecionadosPrimeiro(List<T> pLista, String pParametro, int pLimite, List pSelecao) {
        if (pSelecao == null || pSelecao.isEmpty()) {
            return filtrarOrdenandoMaisParecidos(pLista, pParametro, pLimite);
        }
        List resp = new ArrayList();
        Map<Long, ItemSimilarComPrioridade> itens = pLista.stream().parallel()
                .collect(Collectors.toMap(n -> ((ItfBeanSimples) n).getId(),
                        n -> new ItemSimilarComPrioridade((ItfBeanSimples) n, pParametro, pSelecao.contains(n))));

        List<ItemSimilarComPrioridade> itensOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(itensOrdenados);
        //Collections.reverse(itensOrdenados);

        ComparadorGenericoListaEspecialPrimeiro cpGenerico = new ComparadorGenericoListaEspecialPrimeiro(FabTipoAtributoObjeto.NOME, pSelecao, false);

        itensOrdenados.stream().limit(pLimite).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;

    }

    public static void ordernarPorCampoReverso(List pLista, String pAtributo) {
        if (pLista == null) {
            return;
        }
        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, true);
        pLista.sort(cpGenerico);

    }

    public static <T> List<T> filtrarOrdenandoMaisParecidos(List<T> pLista, String pParametro, int pLimite) {
        final String parametro = UtilSBCoreStringComparador.normalizarTexto(pParametro);
        FabTipoPesquisaGenerica tipoPesquisa = FabTipoPesquisaGenerica.getTipoPesquisaByTermo(pParametro);
        List resp = new ArrayList();
        Map<Long, ItemSimilar> itens = pLista.stream().parallel()
                .collect(Collectors.toMap(n -> ((ItfBeanSimples) n).getId(),
                        n -> new ItemSimilar((ItfBeanSimples) n, parametro, tipoPesquisa)));

//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilar> itensOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(itensOrdenados);
        //Collections.reverse(itensOrdenados);
        itensOrdenados.stream().limit(pLimite).forEach(item -> resp.add(item.getObjetoAnalizado()));
        return resp;
    }

}
