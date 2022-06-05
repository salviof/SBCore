/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public interface ItfServicoController {

    public ItfRespostaAcaoDoSistema getResposta(ItfFabricaAcoes pAcao, Object... pParametros) throws ErroChamadaController;

    public ItfRespostaAcaoDoSistema getResposta(ItfFabricaAcoes pAcao, ItfBeanSimples pEntidade) throws ErroChamadaController;

    public ItfRespostaAcaoDoSistema getResposta(String pNomeUncicoAcao, Object... pParametros) throws ErroChamadaController;

    public ItfRespostaAcaoDoSistema getResposta(String pNomeUncicoAcao, ItfBeanSimples pEntidade) throws ErroChamadaController;

}
