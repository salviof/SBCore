/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.comparadorObjeto.ComparadorGenerico;
import java.util.List;

/**
 *
 * @author salviofurbino
 * @since 12/10/2019
 * @version 1.0
 */
public class UtilSBCoreListasObjeto {

    public static void ordernarPorTipoCampo(List pLista, FabTipoAtributoObjeto pAtributo) {
        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, false);
        pLista.sort(cpGenerico);
    }

    public static void ordernarPorTipoCampoReverso(List pLista, FabTipoAtributoObjeto pAtributo) {
        ComparadorGenerico cpGenerico = new ComparadorGenerico(pAtributo, true);
        pLista.sort(cpGenerico);
    }

}
