/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;
import org.junit.Before;
import org.junit.Test;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComunicacaoTest extends TestesCore {

    public UtilSBCoreComunicacaoTest() {
    }

    public void setUp() {
    }

    @Test
    public void testGetSaudacao() {
        System.out.println(UtilSBCoreComunicacao.getSaudacao());
        ItfDialogo cm = SBCore.getServicoComunicacao().
                gerarComunicacaoSistema_Usuario(FabTipoComunicacao.CONFIRMAR_CANCELAR, new UsuarioAnonimo(), "Teste", "asfasdf");
        cm.getRepostasPossiveis().forEach(rp -> System.out.println(rp.getTipoResposta().getNome()));
    }

}
