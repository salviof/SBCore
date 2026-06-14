/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItemExemploTestes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorProjetoSBCore extends ConfiguradorCoreDeProjetoJarAbstrato {

    public ConfiguradorProjetoSBCore() {
        super();
        setIgnorarConfiguracaoAcoesDoSistema(true);
        setIgnorarConfiguracaoPermissoes(true);
    }

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        super.defineClassesBasicas(pConfiguracao);
        MapaObjetosProjetoAtual.adcionarObjeto(UsuarioAnonimo.class);
        MapaObjetosProjetoAtual.adcionarObjeto(ItemExemploTestes.class);
    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {
        //pConfig.setFabricaDeAcoes(new Class[]{FabAcaoTemporariaTestes.class});
    }

}
