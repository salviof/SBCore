/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilSBCoreGeradorDeIDSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreGeradorDeID extends UtilSBCoreGeradorDeIDSimples {

    public static Long gerarIdUnicoLetrasDaString(String pValor) {

        String nomeparaHash = UtilSBCoreStringFiltros.removeCaracteresEspeciaisEspacosETracos(pValor);
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(nomeparaHash)) {
            return -1l;
        }
        nomeparaHash = nomeparaHash.toUpperCase();
        return (long) nomeparaHash.hashCode();
    }

}
