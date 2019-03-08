/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;
import mtfn.MetaphonePtBr;
import mtfn.MetaphonePtBrFrouxo;
import org.apache.commons.codec.language.Metaphone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.theories.suppliers.TestedOnSupplier;

/**
 *
 * @author SalvioF
 */
public class UtilSBCoreStringComparadorTest {

    @Before
    public void UtilSBCoreStringComparadorTest() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    public void teste() {

    }

    /**
     * Test of isParecido method, of class UtilSBCoreStringComparador.
     */
    @Test
    public void testIsParecido_String_String() {
        System.out.println("isParecido");
        MetaphonePtBr testeFonetic;
        MetaphonePtBrFrouxo testeFonetic2;
        UtilSBCoreStringComparador.isParecido("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G", "manteiga");
        System.out.println(new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString());
        System.out.println(new MetaphonePtBr("queratina").toString());
        assertTrue("Metaphone", new MetaphonePtBr("CREME PARA PENTEAR NIELY GOLD QUERATINA 280G").toString().contains(new MetaphonePtBr("nieli").toString()));

        String pReferencia = "";
        String pParametro = "";
        boolean expResult = false;
        boolean result = UtilSBCoreStringComparador.isParecido(pReferencia, pParametro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
