/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItemExemploTestes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class UtilCRCReflexaoEntidadeTest {

    public UtilCRCReflexaoEntidadeTest() {
    }

    /**
     * Test of getCampoInstanciadoByCaminho method, of class
     * UtilCRCReflexaoEntidade.
     */
    private static final String _1_caminhoCampoSimples = "nome";
    private static final String _2_caminhoCampoSimplesCompleto = "ItemExemploTestes.nome";
    private static final String _3_caminhoCampoSimplesCompletoNomeErrado = "ItemExemploTes.nome";
    private static final String _4_caminhoCampoLista = "ItemExemploTestes.subitensPublicos[]";

    private static final String _5_caminhoCampoListaComIndiceDaLista = "ItemExemploTestes.subitensPublicos[2]";

    private static final String _6_caminhoCampoListaSemTags = "subitensPublicos";
    private static final String _7_caminhoCompletoSublista = "ItemExemploTestes.subitensPublicos[0].nome";
    private static final String _8_caminhoSublista = "subitensPublicos[1].nome";
    private static final String _9_caminhoCompletoMaisNiveis = "nome";
    private static final String _10_caminhoCompletoMaisNiveis2 = "nome";

    @Test
    public void testGetCampoInstanciadoByCaminho() throws Exception {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        MapaObjetosProjetoAtual.adcionarObjeto(ItemExemploTestes.class);
        ItemExemploTestes item = ItemExemploTestes.gerarExemploPadrao();

        ItfCampoInstanciado caminhoSimples = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _1_caminhoCampoSimples);
        Assert.assertFalse("Falha buscando " + _1_caminhoCampoSimples, caminhoSimples.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoSimplesCompleto = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _2_caminhoCampoSimplesCompleto);
        Assert.assertFalse("Falha buscando " + _2_caminhoCampoSimplesCompleto, caminhoCampoSimplesCompleto.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoSimplesCompletoNomeErrado = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _3_caminhoCampoSimplesCompletoNomeErrado);
        Assert.assertTrue(_3_caminhoCampoSimplesCompletoNomeErrado + " deveria falhar", caminhoCampoSimplesCompletoNomeErrado.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoLista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _4_caminhoCampoLista);
        Assert.assertTrue("Falha buscando " + _4_caminhoCampoLista, caminhoCampoLista.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoListaComIndiceDaLista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _5_caminhoCampoListaComIndiceDaLista);
        Assert.assertFalse("Falha buscando " + _5_caminhoCampoListaComIndiceDaLista, caminhoCampoListaComIndiceDaLista.isCampoNaoInstanciado());

    }

    /**
     * Test of getValorByCaminho method, of class UtilCRCReflexaoEntidade.
     */
    //@Test
    public void testGetValorByCaminho() throws Exception {

    }

    /**
     * Test of getListaByCaminho method, of class UtilCRCReflexaoEntidade.
     */
    //@Test
    public void testGetListaByCaminho() throws Exception {

    }

}
