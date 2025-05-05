/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.testes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreTestes {

    public static EntityManager emContextoTEste;

    public static EntityManager renovarConexao() {
        if (UtilSBCoreTestes.emContextoTEste != null) {
            UtilSBCoreTestes.emContextoTEste.close();
        }
        UtilSBCoreTestes.emContextoTEste = null;
        UtilSBCoreTestes.emContextoTEste = SBCore.getServicoRepositorio().gerarNovoEntityManagerPadrao();
        return UtilSBCoreTestes.emContextoTEste;
    }

}
