/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import java.io.BufferedInputStream;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class UtilCRCArquivosTest {

    public UtilCRCArquivosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of criarDiretorioParaArquivo method, of class UtilCRCArquivos.
     */
    @Test
    public void testCriarDiretorioParaArquivo() {
        System.out.println("criarDiretorioParaArquivo");
        String pCaminhoArquivoOuDiretorio = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.criarDiretorioParaArquivo(pCaminhoArquivoOuDiretorio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tornarExecutavel method, of class UtilCRCArquivos.
     */
    @Test
    public void testTornarExecutavel() {
        System.out.println("tornarExecutavel");
        String pCaminhoArquivo = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.tornarExecutavel(pCaminhoArquivo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compactarParaZip method, of class UtilCRCArquivos.
     */
    @Test
    public void testCompactarParaZip() {
        System.out.println("compactarParaZip");
        String arqSaida = "";
        String arqEntrada = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.compactarParaZip(arqSaida, arqEntrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerArquivoLocal method, of class UtilCRCArquivos.
     */
    @Test
    public void testRemoverArquivoLocal() {
        System.out.println("removerArquivoLocal");
        String pCaminhoLocal = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.removerArquivoLocal(pCaminhoLocal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descompactar method, of class UtilCRCArquivos.
     */
    @Test
    public void testDescompactar() {
        System.out.println("descompactar");
        String arquivoZip = "";
        String pastaDestino = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.descompactar(arquivoZip, pastaDestino);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copiarArquivos method, of class UtilCRCArquivos.
     */
    @Test
    public void testCopiarArquivos() {
        System.out.println("copiarArquivos");
        String origem = "";
        String destino = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.copiarArquivos(origem, destino);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isArquivoExiste method, of class UtilCRCArquivos.
     */
    @Test
    public void testIsArquivoExiste() {
        System.out.println("isArquivoExiste");
        String pString = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.isArquivoExiste(pString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArquivosDoDiretorio method, of class UtilCRCArquivos.
     */
    @Test
    public void testGetArquivosDoDiretorio() {
        System.out.println("getArquivosDoDiretorio");
        String pDiretorio = "";
        List<String> expResult = null;
        List<String> result = UtilCRCArquivos.getArquivosDoDiretorio(pDiretorio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copiarArquivoResourceJar method, of class UtilCRCArquivos.
     */
    @Test
    public void testCopiarArquivoResourceJar() {
        System.out.println("copiarArquivoResourceJar");
        Class classeDoResource = null;
        String caminhoArquivoResource = "";
        String destinoArquivo = "";
        boolean expResult = false;
        boolean result = UtilCRCArquivos.copiarArquivoResourceJar(classeDoResource, caminhoArquivoResource, destinoArquivo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHashDoByteArray method, of class UtilCRCArquivos.
     */
    @Test
    public void testGetHashDoByteArray() {

        BufferedInputStream arquivo = UTilSBCoreInputs.getStreamBuffredByURL("https://www.4devs.com.br/4devs_gerador_imagem.php?acao=gerar_imagem&txt_largura=320&txt_altura=240&extensao=png&fundo_r=0.049800067292380845&fundo_g=0.1256103515625&fundo_b=0.029025927186012268&texto_r=0.9302978515625&texto_g=0.8945259004831314&texto_b=0.8945259004831314&texto=Apenas%20um%20teste%20simples&tamanho_fonte=10");
        byte[] imagem1 = UtilCRCBytes.gerarBytePorInputstream(arquivo);
        String hash1 = UtilCRCArquivos.getHashDoByteArray(imagem1);
        System.out.println(hash1);
        BufferedInputStream arquivo2 = UTilSBCoreInputs.getStreamBuffredByURL("https://www.4devs.com.br/4devs_gerador_imagem.php?acao=gerar_imagem&txt_largura=320&txt_altura=240&extensao=png&fundo_r=0.049800067292380845&fundo_g=0.1256103515625&fundo_b=0.029025927186012268&texto_r=0.9302978515625&texto_g=0.8945259004831314&texto_b=0.8945259004831314&texto=Apenas%20um%20teste%20simples&tamanho_fonte=10");
        byte[] imagemIgualImagem1 = UtilCRCBytes.gerarBytePorInputstream(arquivo2);
        String hash2 = UtilCRCArquivos.getHashDoByteArray(imagemIgualImagem1);
        System.out.println(hash2);

        BufferedInputStream arquivo3 = UTilSBCoreInputs.getStreamBuffredByURL("https://www.4devs.com.br/4devs_gerador_imagem.php?acao=gerar_imagem&txt_largura=320&txt_altura=240&extensao=png&fundo_r=0.049800067292380845&fundo_g=0.1256103515625&fundo_b=0.029025927186012268&texto_r=0.9302978515625&texto_g=0.8945259004831314&texto_b=0.8945259004831314&texto=Apenasss%20um%20teste%20simples&tamanho_fonte=10");
        byte[] imagemDif = UtilCRCBytes.gerarBytePorInputstream(arquivo3);
        String hash3 = UtilCRCArquivos.getHashDoByteArray(imagemDif);
        System.out.println(hash3);
    }

}
