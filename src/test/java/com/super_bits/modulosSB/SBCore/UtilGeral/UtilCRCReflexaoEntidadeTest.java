/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItemExemploTestes;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroEntidade;
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
    private static final String _11_caminhoTesteFinal = "subItensExclusivos[2].itemPaisubItemPai.subitensPublicos[1].nome";

    @Test
    public void testGetCampoInstanciadoByCaminho() throws Exception {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        MapaObjetosProjetoAtual.adcionarObjeto(ItemExemploTestes.class);
        ItemExemploTestes item = ItemExemploTestes.gerarExemploPadrao();

        ItfCampoInstanciado caminhoSimples = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _1_caminhoCampoSimples);
        Assert.assertFalse("Falha buscando " + _1_caminhoCampoSimples, caminhoSimples.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoSimplesCompleto = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _2_caminhoCampoSimplesCompleto);
        Assert.assertFalse("Falha buscando " + _2_caminhoCampoSimplesCompleto, caminhoCampoSimplesCompleto.isCampoNaoInstanciado());
        try {
            ItfCampoInstanciado caminhoCampoSimplesCompletoNomeErrado = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _3_caminhoCampoSimplesCompletoNomeErrado);
            Assert.fail("Esparado erro com nome de entidade errada");
        } catch (ErroEntidade e) {
            System.out.println("Erro " + _3_caminhoCampoSimplesCompletoNomeErrado + " lançado com sucesso ");
        }

        ItfCampoInstanciado caminhoCampoLista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _4_caminhoCampoLista);
        Assert.assertFalse("Falha buscando " + _4_caminhoCampoLista, caminhoCampoLista.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCampoListaComIndiceDaLista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _5_caminhoCampoListaComIndiceDaLista);
        Assert.assertEquals("Esperado indice 2 no campo instanciado", 2, caminhoCampoListaComIndiceDaLista.getIndiceValorLista());

        ItfCampoInstanciado caminhoCampoListaSemTags = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _6_caminhoCampoListaSemTags);
        Assert.assertFalse("Falha buscando " + _6_caminhoCampoListaSemTags, caminhoCampoListaSemTags.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCompletoSublista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _7_caminhoCompletoSublista);
        Assert.assertFalse("Falha buscando " + _7_caminhoCompletoSublista, caminhoCompletoSublista.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoSublista = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _8_caminhoSublista);
        Assert.assertFalse("Falha buscando " + _8_caminhoSublista, caminhoSublista.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCompletoMaisNiveis = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _9_caminhoCompletoMaisNiveis);
        Assert.assertFalse("Falha buscando " + _9_caminhoCompletoMaisNiveis, caminhoCompletoMaisNiveis.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoCompletoMaisNiveis2 = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _10_caminhoCompletoMaisNiveis2);
        Assert.assertFalse("Falha buscando " + _10_caminhoCompletoMaisNiveis2, caminhoCompletoMaisNiveis2.isCampoNaoInstanciado());

        ItfCampoInstanciado caminhoTesteFinal = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, _11_caminhoTesteFinal);
        Assert.assertFalse("Falha buscando " + _11_caminhoTesteFinal, caminhoTesteFinal.isCampoNaoInstanciado());

        try {
            ItfCampoInstanciado caminhoCampoSimplesCompletoNomeErrado = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, "ItemExemploTestes.subitensPublicos[6].nome");
            Assert.fail("Esparado erro com indice inválido");
        } catch (ErroEntidade e) {
            System.out.println("Erro " + _3_caminhoCampoSimplesCompletoNomeErrado + " lançado com sucesso ");
        }
        try {
            ItfCampoInstanciado caminhoCampoSimplesCompletoNomeErrado = UtilCRCReflexaoEntidade.getCampoInstanciadoByCaminho(item, "subitensPublicos[1].nomesss");
            Assert.fail("Esperado erro com nome atributo errado");
        } catch (ErroEntidade e) {
            System.out.println("Erro " + _3_caminhoCampoSimplesCompletoNomeErrado + " lançado com sucesso ");
        }

    }

}
