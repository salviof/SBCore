/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCStringNomeArquivosEDiretoriosTest {

    public UtilCRCStringNomeArquivosEDiretoriosTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetDiretorioMenosXCasas() {
    }

    @Test
    public void testGetNomeArquivo() {
    }

    @Test
    public void testGetDiretorioArquivo() {

    }

    @Test
    public void testGetNomeDoArquivoSemExtencao() {

        System.out.println(UtilCRCStringNomeArquivosEDiretorios.getNomeDoArquivoSemExtencao("arquivohuiahweuiahe.exeDoRuindows"));
        System.out.println("---" + UtilCRCStringNomeArquivosEDiretorios.getNomeDoArquivoSemExtencao("arquivohuiahweuiahe"));
    }

    @Test
    public void testGetExtencaoNomeArquivo() {
        System.out.println(UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo("arquivohuiahweuiahe.exeDoRuindows"));
        System.out.println("---" + UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo("arquivohuiahweuiahe"));
    }

}
