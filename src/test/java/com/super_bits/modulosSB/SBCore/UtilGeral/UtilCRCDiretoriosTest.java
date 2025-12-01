/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilCRCDiretoriosSimples;
import java.io.File;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilCRCDiretoriosTest {

    public UtilCRCDiretoriosTest() {
    }

    //@Test
    public void testGetDiretorioMenosXCasas() {
        String teste
                = "resources/SBComp/tagLib/tags/com/sb/botaoAcao";

        System.out.println("Resultado=" + UtilCRCDiretoriosSimples.getDiretorioMenosXCasas(teste, 1));
        System.out.println("Resultado=" + UtilCRCDiretoriosSimples.getNomeArquivo(teste));

    }

    @Test
    public void testeSubdiretorios() {
        List<String> lista = UtilCRCDiretorio.getDiretoriosRecursivoOrdemMaoirArvore(new File("/home/superBits/projetos"));
        for (String arq : lista) {
            System.out.println("Arquivo===" + arq);
        }
    }

}
