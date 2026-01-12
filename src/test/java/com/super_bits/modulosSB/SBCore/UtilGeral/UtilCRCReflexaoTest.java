/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TipoAtributoObjetoSB;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;

/**
 *
 * @author sfurbino
 */
public class UtilCRCReflexaoTest {

    public UtilCRCReflexaoTest() {
    }

    @Before
    public void setUp() {
        configAmbienteDesevolvimento();
    }

    /**
     * Test of getClassesComEstaAnotacao method, of class UtilCRCReflexao.
     */
    //@Test
    public void testGetClassesComEstaAnotacao() {

        try {

            List<Class> classes = UtilCRCReflexao.getClassesComEstaAnotacao(InfoObjetoSB.class, "com.super_bits");
            assertTrue("nenhuma classe foi encontrada", classes.size() > 0);

        } catch (Throwable t) {
            //      lancarErroJUnit(t);
        }

    }

    @Test
    public void testClasseImplementaEstaInterface() {
        System.out.println(UtilCRCReflexao.isInterfaceImplementadaNaClasse(TipoAtributoObjetoSB.class, ComoEntidadeSimplesSomenteLeitura.class));
    }

    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
