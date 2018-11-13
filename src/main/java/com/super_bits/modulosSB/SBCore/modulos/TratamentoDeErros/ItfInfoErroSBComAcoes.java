/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

/**
 *
 * @author desenvolvedor
 */
public interface ItfInfoErroSBComAcoes extends ItfInfoErroSB {

    /**
     * ATENÇÃO ESTE MÉTODO SÓ DEVE SER CHAMADO PELO EXECUTAR ERRO
     */
    public abstract void alertarResponsavel();

    /**
     ** ATENÇÃO ESTE MÉTODO SÓ DEVE SER CHAMADO PELO EXECUTAR ERRO
     */
    public abstract void lancarExcecao();

    /**
     ** ATENÇÃO ESTE MÉTODO SÓ DEVE SER CHAMADO PELO EXECUTAR ERRO
     */
    public abstract void lancarPane();

    /**
     ** ATENÇÃO ESTE MÉTODO SÓ DEVE SER CHAMADO PELO EXECUTAR ERRO
     */
    public abstract void registrarErro();

    /**
     *
     *
     *
     * Executa uma ação de acordo com as informações do Erro
     *
     * Cada Ambiente de execução pode ter um tratamento diferente O Ambiente de
     * execução é configurado Chamando SBCore.configurar
     *
     *
     * ###ATENÇÃO### E execução de sistemas SuperBits sem executar esta
     * configuração gera um erro to tipo PARATUDO que executa um system.out!
     *
     */
    public void executarErro();

}
