/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public interface ItfInfoErroSB {

    /**
     *
     * ISTO:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto() No formato String
     *
     * @return
     */
    public String getCaminhoStackTraceCompletoStr();

    public String getCaminhoStackTraceResumidoStr();

    /**
     *
     * Obtem a tragetoria completa hierarquica de chamadas de método até
     * acontecer este erro
     *
     * @return
     */
    public List<String> getCaminhoStackTraceCompleto();

    /**
     *
     * ISTO:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto() Onde o Classhpath do
     * metodo contenha super_bits
     *
     *
     * @return
     */
    public List<String> getCaminhoStackTraceResumido();

    /**
     *
     * Obtem:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto()
     *
     * Onde o método contenha uma palavra específica em seu classpach
     *
     * @param pPalavras String com a palavra que deve estar no pacote do metodo
     * @return Caminho de metodos contendo isto no Classpath
     */
    public List<String> getCaminhoStackTraceContendoPalavrasNoPacote(String... pPalavras);

    /**
     * Objeto que extende Throwable que originou o relato de erro
     *
     * @return objeto Throwable origem
     */
    public Throwable getErroGerado();

    /**
     *
     * Mensagem enviada pelo desenvolvedor ao lançar este erro, seja camarada e
     * maroto para pensar em dicas eficases para seu coleguinha, ele e nós
     * merecemos, e queremos trabalhar menos e ganhar mais.
     *
     * @return Mensagem criada pelo desenvolvedor ao lançar o erro
     */
    public String getMsgDesenvolvedorLancou();

    public List<String> causas();

    public String getCausaInicial();

    public String getCausaFinal();

    public void configurar(ItfMensagem pMensagemDoDesenvolvedor, FabErro pTipoErrp, Throwable pErroGerado);

}
