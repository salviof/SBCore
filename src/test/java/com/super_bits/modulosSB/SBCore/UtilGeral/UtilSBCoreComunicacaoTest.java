/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComunicacaoTest {

    public UtilSBCoreComunicacaoTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetSaudacao() {
        System.out.println(UtilSBCoreComunicacao.getSaudacao());
    }

}
