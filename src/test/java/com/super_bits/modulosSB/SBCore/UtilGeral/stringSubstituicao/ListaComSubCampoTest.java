/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class ListaComSubCampoTest {

    public ListaComSubCampoTest() {
    }

    /**
     * Test of adicionarNovoSubCampo method, of class ListaComSubCampo.
     */
    @Test
    public void testAdicionarNovoSubCampo() {

        ListaComSubCampo lista = new ListaComSubCampo("atributoTeste1.subCampos[0].coluna1", "Valor linha 0 coluna1");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[0].coluna1", "Cordenada01");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[0].coluna2", "Cordenada02");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[1].coluna1", "Cordenada11");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[1].coluna2", "Cordenada12");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[2].coluna1", "Cordenada21");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[2].coluna2", "Cordenada22");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[3].coluna1", "Cordenada31");
        lista.adicionarNovoSubCampo("atributoTeste1.subCampos[3].coluna2", "Cordenada32");
        System.out.println(lista.getNomeLista());
        assertEquals("Nome da lista diferente do esperado", lista.getNomeLista(), "[atributoTeste1.subCampos[]]");

        Integer indice = 0;
        for (ItemListaComSubcampos item : lista.getItensOrdenadas()) {
            assertEquals("Indice enviado em ordem errada", indice, item.getIndice());
            indice++;
        }
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(0).getSubCampos().get("coluna1"), "Cordenada01");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(0).getSubCampos().get("coluna2"), "Cordenada02");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(1).getSubCampos().get("coluna1"), "Cordenada11");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(1).getSubCampos().get("coluna2"), "Cordenada12");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(2).getSubCampos().get("coluna1"), "Cordenada21");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(2).getSubCampos().get("coluna2"), "Cordenada22");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(3).getSubCampos().get("coluna1"), "Cordenada31");
        assertEquals("Indice enviado em ordem errada", lista.getItensOrdenadas().get(3).getSubCampos().get("coluna2"), "Cordenada32");

    }

    /**
     * Test of getItensOrdenadas method, of class ListaComSubCampo.
     */
}
