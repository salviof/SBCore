/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import org.junit.Before;
import org.junit.Test;

/**
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
        mapa.substituirEmString("[nome] , link [link:https://com.br]");
    }

}
