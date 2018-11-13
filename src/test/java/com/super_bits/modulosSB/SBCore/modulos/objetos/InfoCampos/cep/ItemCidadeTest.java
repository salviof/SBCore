/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class ItemCidadeTest extends TestesCore {

    public ItemCidadeTest() {
    }

    @Test
    public void testeGetEstado() {

        ItemCidade cidade = new ItemCidade();
        cidade.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA);

        ItemBairro bairro = new ItemBairro();
        bairro.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_CIDADE);
    }

}
