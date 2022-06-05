/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.chavesPublicasePrivadas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCriptoRSA;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class RepositorioChavePublicaPrivadaTest {

    public RepositorioChavePublicaPrivadaTest() {
    }

    /**
     * Test of persistirChavePublica method, of class
     * RepositorioChavePublicaPrivada.
     */
    @Test
    public void testPersistirChavePublica() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        Map<String, String> parDeChaves = UtilSBCoreCriptoRSA.chavePublicaPrivada();
        String texto = "texto teste \nexemploini\n1 eai carinha  2\nexemplofim\n colpé";
        int indiceInicio = texto.indexOf("\nexemploini\n") + "\nexemploini\n".length();
        int indiceFInal = texto.indexOf("\nexemplofim\n");
        String resposta = texto.substring(indiceInicio, indiceFInal);
        System.out.println(resposta);
        String indiceParDeChaves = RepositorioChavePublicaPrivada.getIndentificadorParDeChaves(parDeChaves);
        String chavePublica = parDeChaves.keySet().iterator().next();
        String chavePrivada = parDeChaves.values().iterator().next();
        String identificacao = RepositorioChavePublicaPrivada.getIndentificadorParDeChaves(parDeChaves);
        RepositorioChavePublicaPrivada.persistirChavePublica(parDeChaves);

        Map<String, String> parDeChavesPersistida = RepositorioChavePublicaPrivada.geParDeChavesPubPrivada(indiceParDeChaves);
        assertEquals("", parDeChavesPersistida.keySet().iterator().next(), chavePublica);
        assertEquals("", parDeChavesPersistida.values().iterator().next(), chavePrivada);

    }

}
