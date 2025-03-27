/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.grafico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;

/**
 *
 * @author salvio
 */
public interface ItfDadoGrafico extends ItfBeanSimplesSomenteLeitura {

    public double getValor();
    
    public Object getItemRelacionado();
    
    public default String getLabel() {
        return getNome();
    }

}
