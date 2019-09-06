/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores.ItfSeletorGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao.ItemSimilar;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoComSelecao implements ItfCampoInstSeletor {

    private String filtro = "";
    private List<FabTipoAtributoObjeto> tiposcamposFiltro;
    private final List<String> camposFiltro;
    private int minimoParaPesquisa = 4;
    private final ItfSeletorGenerico seletor;
    private final List<ItfBeanSimples> listaCompleta;
    private final ItfCampoInstanciado campoInstanciado;

    public CampoInstanciadoComSelecao(ItfSeletorGenerico pSeletor, ItfCampoInstanciado pCampoInstanciado) {
        this.camposFiltro = new ArrayList<>();
        if (pCampoInstanciado == null) {
            throw new UnsupportedOperationException("Erro instanciando campo com selecao, o campo instanciado não foi especificado");
        }
        this.seletor = pSeletor;
        listaCompleta = new ArrayList();
        listaCompleta.addAll((List) pCampoInstanciado.getListaDeOpcoes());
        campoInstanciado = pCampoInstanciado;

    }

    private synchronized List getOrigemSincronizado() {
        return seletor.getOrigem();
    }

    private List filtroSimilaridadeEmListaCompleta(final String pParamentro) {
        List resp = new ArrayList();
        Map<Integer, ItemSimilar> itens = listaCompleta.stream().parallel()
                .collect(Collectors.toMap(n -> ((ItfBeanSimples) n).getId(),
                        n -> new ItemSimilar((ItfBeanSimples) n, pParamentro)));
        System.out.println("teste");
//(prodsml->produtosOrdenados.add(new ItemSimilar(prodsml, "coca ")));
        List<ItemSimilar> produtosOrdenados = itens.values().stream().parallel().collect(Collectors.toList());
        Collections.sort(produtosOrdenados);
        Collections.reverse(produtosOrdenados);
        long fim = new Date().getTime();

        produtosOrdenados.stream().limit(20).forEach(item -> resp.add(item.getpObjetoAnalizado()));
        return resp;
    }

    private void filtrar() {

        seletor.getOrigem().clear();
        boolean apenasNumero = UtilSBCoreStringValidador.isContemApenasNumero(filtro);

        if (UtilSBCoreStringValidador.isNuloOuEmbranco(filtro)) {
            listaCompleta.forEach(seletor.getOrigem()::add);
        } else {
            try {

                if (!apenasNumero) {
                    // String chave = MapaPesquisaFonetica.getChaveFonetica(filtro);
                    filtroSimilaridadeEmListaCompleta(filtro).forEach(getOrigemSincronizado()::add);

                } else {
                    listaCompleta.parallelStream().filter(item
                            -> UtilSBCoreStringComparador.isParecido((ItfBeanSimples) item, getGrupoCampoExibicao().getCampos(), filtro, apenasNumero))
                            .forEach(getOrigemSincronizado()::add);
                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando Pesquisa completa ", t);
                listaCompleta.stream().
                        filter(item -> UtilSBCoreStringComparador.
                        isBastanteParecido(((ItfBeanSimplesSomenteLeitura) item).getNome(), filtro))
                        .forEach(getOrigemSincronizado()::add);
            }
        }

    }

    @Override
    public void atualizarListaCompleta() {
        try {

            if (filtro.length() > getMinimoParaPesquisa()) {

                listaCompleta.clear();
                if (false) {
                    List<ItfBeanSimples> resultado = (List) SBCore.getCentralFonteDeDados().getListaOpcoesCampoInstanciado(campoInstanciado, filtro, camposFiltro.toArray(new String[camposFiltro.size()]));
                    resultado.stream().forEach(listaCompleta::add);

                }
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando ListaCompleta de " + campoInstanciado.getNomeCamponaClasse() + " em " + campoInstanciado.getNomeDoObjeto(), t);
        }
    }

    @Override
    public List filtrarPorAutoComplet(String pParametro) {
        setFiltro(pParametro);

        return seletor.getOrigem();
    }

    @Override
    public String getFiltro() {
        return filtro;
    }

    private void atualizarListagens(boolean pAtualizarListaCompleta, String pFiltro) {
        filtro = pFiltro;
        if (pAtualizarListaCompleta) {
            atualizarListaCompleta();
        }
        filtrar();
    }

    @Override
    public void setFiltro(String pFiltro) {

        int qtdCaracteresAnteriores = filtro.length();
        int qtdCaracteresNovoFiltro = pFiltro.length();
        int qtdDiferencaCaracteresFiltro = qtdCaracteresNovoFiltro - qtdCaracteresAnteriores;
        if (filtro.equals(pFiltro)) {
            return;
        }
        if (pFiltro.contains(filtro) && filtro.length() > getMinimoParaPesquisa()) {
            atualizarListagens(false, pFiltro);
            return;
        }

        if (pFiltro.contains(filtro)) {

            if (qtdDiferencaCaracteresFiltro < 0) {

                atualizarListagens(false, pFiltro);
                return;
            }

            if (qtdDiferencaCaracteresFiltro < getMinimoParaPesquisa()) {
                if (getSeletor().getOrigem().isEmpty()) {
                    //atualizarListagens(true, pFiltro);
                    atualizarListagens(false, pFiltro);
                } else {
                    atualizarListagens(false, pFiltro);
                }
            } else {
                //  atualizarListagens(true, pFiltro);
                atualizarListagens(false, pFiltro);
            }
        } else {
            if (qtdCaracteresNovoFiltro >= getMinimoParaPesquisa()) {
                //atualizarListagens(true, pFiltro);
                atualizarListagens(false, pFiltro);

            }

        }

    }

    @Override
    public void listarPrimeiros50() {
        //  atualizarListaCompleta();
        filtrar();
    }

    @Override
    public int getMinimoParaPesquisa() {
        return minimoParaPesquisa;
    }

    public void setMinimoParaPesquisa(int minimoParaPesquisa) {
        this.minimoParaPesquisa = minimoParaPesquisa;
    }

    @Override
    public final ItfSeletorGenerico getSeletor() {
        return seletor;
    }

    @Override
    public GrupoCampos getGrupoCampoExibicao() {
        return (GrupoCampos) campoInstanciado.getGrupoCampoExibicao();
    }

    @Override
    public void limparFiltro() {
        filtro = "";
        filtrar();
    }

}
