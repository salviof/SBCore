/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigModuloTeste;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.chavesPublicasePrivadas.RepositorioChavePublicaPrivada;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class RecursosExternosPorIndiceTest {

    public RecursosExternosPorIndiceTest() {

    }

    /**
     * Test of getCaminhoArquivosRepositorio method, of class
     * RecursosExternosPorIndice.
     */
    @Test
    public void testeRepositorioSimples() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        String caminho = SBCore.getConfigModulo(FabConfigModuloTeste.class).getRepositorioDeArquivosExternos().getCaminhoArquivosRepositorio();
        System.out.println(caminho);

    }

}
