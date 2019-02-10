/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringSlugsTest {

    @Test
    public void testGerarSlugCaixaAlta() {

        String testeeee = "https://muffatosupermercados.vteximg.com.br/arquivos/ids/196246-500-500/21735-feijao-biju-cor-vermelho-500-g.jpg?v=635711724964800000";
        testeeee = testeeee.replace("-400-400", "-500-500");
        System.out.println(testeeee);
        String retorno = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/home/fullBanner.frase1");
        System.out.println(retorno);
        assertEquals(retorno, "casanovadigital.com.br/home/");

        String retorno2 = UtilSBCoreStringSlugs.getSlugPagina("/home/fullBanner.frase1");
        System.out.println(retorno2);
        assertEquals(retorno2, "/home/");

        String retorno3 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/home");
        System.out.println(retorno3);
        assertEquals(retorno3, "casanovadigital.com.br/");
        String retorno4 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos");
        System.out.println(retorno4);
        assertEquals(retorno4, "casanovadigital.com.br/");
        String retorno5 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos/");
        System.out.println(retorno5);
        assertEquals(retorno5, "casanovadigital.com.br/servicos/");

        String retorno6 = UtilSBCoreStringSlugs.getSlugPagina("servicosDestaque.produto1");
        System.out.println(retorno6);
        assertEquals(retorno6, "/");

        String retorno7 = UtilSBCoreStringSlugs.getSlugPagina("casanovadigital.com.br/servicos/destaque.banner");
        System.out.println(retorno7);
        assertEquals(retorno7, "casanovadigital.com.br/servicos/");

    }

}
