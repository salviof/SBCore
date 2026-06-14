/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItemExemploTestes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringVariaveisEntreCaracteres;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 * @author salvio
 */
public class MapaSubstituicaoTest {

    public MapaSubstituicaoTest() {
    }

    /**
     * Test of getValorImagem method, of class MapaSubstituicao.
     */
    @Test
    public void testesMapaSubstituicao() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        MapaSubstituicao mapa = new MapaSubstituicao();

        mapa.adicionarPalavrasChaveDoObjeto(new UsuarioAnonimo());

        String textoComPalavrasChave = "[ItemExemploTestes.subitensPublicos[2]] , [nome] ,  [ItemExemploTestes.nome]  , [ItemExemploTestes.subitensPublicos[]] , [ItemExemploTestes.subitensPublicos[]], [ItemExemploTestes.subitensPublicos[2]],  [subitensPublicos],"
                + "[ItemExemploTestes.subitensPublicos[0].nome],  [subitensPublicos[1].nome] , [subItensExclusivos[2].itemPaisubItemPai.subitensPublicos[1].nome] ";
        List<String> valoresEncontradas = UtilCRCStringVariaveisEntreCaracteres.extrairVariaveisEntreColchete(textoComPalavrasChave);
        ItemExemploTestes itemExemplo = ItemExemploTestes.gerarExemploPadrao();
        mapa.adicionarPalavrasChavePorTextoModelo(itemExemplo, "[ItemExemploTestes.subitensPublicos[]]  [ItemExemploTestes.subitensPublicos[2]]");
        String teste = mapa.substituirEmString("[ItemExemploTestes.subitensPublicos[]]  [ItemExemploTestes.subitensPublicos[2]]");
        System.out.println(teste);
        mapa.adicionarPalavrasChavePorTextoModelo(itemExemplo, textoComPalavrasChave);
        mapa.adicionarPalavrasChavePorTextoModelo(itemExemplo, textoComPalavrasChave);

        mapa.adicionarPalavraChave("[nome]", "coléeeeeeeeeeeeeeeeeeee");
        mapa.adicionarImagem("[logoCliente]", "/home/superBits/projetos/coletivoJava/source/fw/util/EditorDeArquivos/Gnu.png");
        mapa.adicionarImagem("[logoProspecto]", "/home/superBits/projetos/coletivoJava/source/fw/util/EditorDeArquivos/Gnu.png");
        mapa.adicionarPalavraChave("[Cliente.contratos[0].nome]", "contrato1");
        mapa.adicionarPalavraChave("[Cliente.contratos[0].valor]", "R$ 100,00");
        mapa.adicionarPalavraChave("[SITE]", "www.teste.com.br  [nome] ");
        mapa.adicionarPalavraChave("[Cliente.contratos[1].nome]", "contrato2");
        mapa.adicionarPalavraChave("[Cliente.contratos[1].valor]", "R$ 500,00");

        mapa.adicionarPalavraChave("dados.FORMA_PAGAMENTO", "Pagarei com amor");
        mapa.adicionarPalavraChave("dados.CONTRATO_PARCELAMENTO", "A parcelas a deusdará");
        int contador = 0;
        for (int i = 0; i < 3; i++) {
            mapa.adicionarPalavraChave("[solucao[" + i + "].descricao]", "Descricao solucao teste  " + i);
            mapa.adicionarPalavraChave("[solucao[" + i + "].valorSetup]", "R$5.000,0" + i);
            mapa.adicionarPalavraChave("[solucao[" + i + "].valorMensal]", "R$500,00");
            mapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.nome]", "Tipo solução Teste" + i);
            mapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.descricao]", "DEscricao tipo solução teste");
            mapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.url]", "www.teste.com.br");
            mapa.adicionarPalavraChave("[solucao[" + i + "].tipoSolucao.descricaoApresentacao]", "Descricao apresentação");
            contador++;
        }
        System.out.println(contador);
        List<ItemListaComSubcampos> lista = mapa.getListaOrdenada("[solucao[]");

        Assert.assertEquals("Esperado 3 itens listados em soluções", lista.size(), 3);

        Assert.assertEquals("Valor setup não confere", lista.get(0).getSubCampos().get("valorSetup"), "R$5.000,00");
        Assert.assertEquals("Valor setup não confere", lista.get(2).getSubCampos().get("valorSetup"), "R$5.000,02");

        Assert.assertEquals("Valor setup não confere", lista.get(2).getSubCampos().get("tipoSolucao.nome"), "Tipo solução Teste2");

        String resultado = mapa.substituirEmString(textoComPalavrasChave);
        System.out.println(resultado);
    }

}
