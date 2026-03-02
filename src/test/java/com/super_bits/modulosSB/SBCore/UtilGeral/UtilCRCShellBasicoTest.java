/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class UtilCRCShellBasicoTest {

    public UtilCRCShellBasicoTest() {
    }

    @Test
    public void testExecuteCommand() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.PRODUCAO);
        System.out.println(UtilCRCShellBasico.executeCommand("/home/superBits/projetos/Casa_Nova/source/servicosnuvem/modelRegras/src/main/resources/provedores/caramelo/renovarChaves.sh teste 2 3 4"));

    }

}
