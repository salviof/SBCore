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
public class UtilCRCTestes {

    public static EntityManager emContextoTEste;

    public static EntityManager renovarConexao() {
        if (UtilCRCTestes.emContextoTEste != null) {
            UtilCRCTestes.emContextoTEste.close();
        }
        UtilCRCTestes.emContextoTEste = null;
        UtilCRCTestes.emContextoTEste = SBCore.getServicoRepositorio().gerarNovoEntityManagerPadrao();
        return UtilCRCTestes.emContextoTEste;
    }

}
