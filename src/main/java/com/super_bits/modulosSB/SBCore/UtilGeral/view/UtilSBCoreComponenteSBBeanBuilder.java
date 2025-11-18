/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.view;

import com.super_bits.modulosSB.SBCore.modulos.view.componenteAtributo.ComponenteVisualSBBean;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualBase;
import org.coletivojava.fw.utilCoreBase.UtilSBFabricaComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteBuilder;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComoFabTipoComponenteVisual;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComponenteSBBeanBuilder implements ComoComponenteBuilder {

    @Override
    public ComoComponenteVisualSB gerarComponenteVisual(ComoFabTipoComponenteVisual pFabrica) {
        ComponenteVisualBase componenteBase = UtilSBFabricaComponenteVisual.getComponenteVisual(pFabrica);
        ComponenteVisualSBBean componente = new ComponenteVisualSBBean(componenteBase);
        return componente;
    }

}
