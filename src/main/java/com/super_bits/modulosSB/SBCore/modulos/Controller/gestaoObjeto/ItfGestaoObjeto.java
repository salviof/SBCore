/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.gestaoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.ItfNavegacaoDataSet;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public interface ItfGestaoObjeto<T> extends ItfNavegacaoDataSet<T> {

    public List<T> pesquisar(String pParametro);

    public List<ComoAcaoDoSistema> getAcoesDisponiveis();

    public List<ComoAcaoDoSistema> getAcoesDeFormularioObjetoSelecionado();

    public List<ComoAcaoDoSistema> getAcoesDeFormularioNovoObjeto();

    public ItfResposta executarAcaoController(ComoAcaoController pAcaoExecucao);

    public void executarAcaoFormulario(ComoAcaoController pAcaoExecucao);

}
