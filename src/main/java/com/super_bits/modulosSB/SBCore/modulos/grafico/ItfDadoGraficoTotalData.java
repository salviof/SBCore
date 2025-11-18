/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.grafico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.util.Date;

/**
 *
 * @author salvio
 */
public interface ItfDadoGraficoTotalData extends ItfDadoGraficoTotal {

    public Date getDataInicial();

    public Date getDataFinal();

}
