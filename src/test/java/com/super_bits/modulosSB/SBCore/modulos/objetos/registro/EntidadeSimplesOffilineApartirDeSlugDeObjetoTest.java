/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

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
public class EntidadeSimplesOffilineApartirDeSlugDeObjetoTest {

    /**
     * Test of getId method, of class
     * EntidadeSimplesOffilineApartirDeSlugDeObjeto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        EntidadeSimplesOffilineApartirDeSlugDeObjeto teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("asdfasdf-asdfasdf-asdfasdf-123");
        assertEquals("asdfasdf-asdfasdf-asdfasdf", teste.getDescricao());
        assertEquals(123l, teste.getId().longValue());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("asdfasdf-asdfasdf-asdfasdf--123");
        assertEquals("asdfasdf-asdfasdf-asdfasdf", teste.getDescricao());
        assertEquals(-123l, teste.getId().longValue());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("123");

        assertEquals(123l, teste.getId().longValue());
        assertEquals("123", teste.getDescricao());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("-123");
        assertEquals("123", teste.getDescricao());
        assertEquals(123l, teste.getId().longValue());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("--123");
        assertEquals("-123", teste.getDescricao());
        assertEquals(-123l, teste.getId().longValue());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("asdfasdfasdf");
        assertEquals("asdfasdfasdf", teste.getDescricao());
        assertNull("Esperado nulo", teste.getId());

        teste = new EntidadeSimplesOffilineApartirDeSlugDeObjeto("Oferta_para_Salvio_Furbino_Operacoes_Dev_Servicos_para_cliente_Camara_Mineira_do_Livro_no_valor_de_R_674.65-1032");
        assertEquals("Oferta_para_Salvio_Furbino_Operacoes_Dev_Servicos_para_cliente_Camara_Mineira_do_Livro_no_valor_de_R_674.65", teste.getDescricao());

        assertEquals(1032, teste.getId().longValue());
    }

}
