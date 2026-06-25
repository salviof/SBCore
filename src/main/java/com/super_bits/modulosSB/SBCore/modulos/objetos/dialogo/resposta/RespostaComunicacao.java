/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = "Resposta do dialogo", plural = "Respostas de dialogos")
public class RespostaComunicacao extends EntidadeSimples implements ItfRespostaComunicacao {

    private ComoDialogo comunicacaoVinculada;
    private ComoTipoRespostaComunicacao tipoResposta;
    private BotaoResposta botaoResposta;

    public RespostaComunicacao() {

    }

    public RespostaComunicacao(ComoDialogo comunicacaoVinculada,
            ComoTipoRespostaComunicacao tipoResposta) {

        this.comunicacaoVinculada = comunicacaoVinculada;
        this.tipoResposta = tipoResposta;
        botaoResposta = new BotaoResposta(tipoResposta, tipoResposta.getBotaoResposta());

    }

    @Override
    public ComoDialogo getComunicacao() {
        return comunicacaoVinculada;
    }

    @Override
    public ComoTipoRespostaComunicacao getTipoResposta() {
        return tipoResposta;
    }

    @Override
    public ComoAcaoDoSistema getBotaoAcao() {
        return botaoResposta;
    }

    @Override
    public void setNome(String pNome) {

    }

    @Override
    public String getNome() {
        return botaoResposta.getNomeAcao();
    }

    @Override
    public Long getId() {
        return Long.valueOf(comunicacaoVinculada.toString() + toString().hashCode());
    }

    @Override
    public void setId(Long pID) {

    }

}
