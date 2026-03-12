/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import static com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringGerador.getStringRandomicaTokenAleatorio;
import com.super_bits.modulosSB.SBCore.UtilGeral.criptografia.GeradorTokenRandomico;
import org.coletivojava.fw.utilCoreBase.UtilCRCGeradorDeIDSimples;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilCRCGeradorDeID extends UtilCRCGeradorDeIDSimples {

    public static Long gerarIdUnicoLetrasDaString(String pValor) {

        String nomeparaHash = UtilCRCStringFiltros.removeCaracteresEspeciaisEspacosETracos(pValor);
        if (UtilCRCStringValidador.isNuloOuEmbranco(nomeparaHash)) {
            return -1l;
        }
        nomeparaHash = nomeparaHash.toUpperCase();
        return (long) nomeparaHash.hashCode();
    }

    public static String getStringRandomicaTokenAleatorio(int pNumero) {
        return new GeradorTokenRandomico(pNumero).proximaString();
    }

    public static String getStringRandomicaTokenAleatorio() {
        return getStringRandomicaTokenAleatorio(8);
    }
}
