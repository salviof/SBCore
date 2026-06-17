/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(plural = "Mensagem entre usuários", tags = "Comunicação")
public class ComunicacaoTransientUsrToUsr extends ComunicacaoTransient implements ItfDialogoEntrePessoas {

    private ComoUsuario usuarioRementente;
    private String observacaoResposta;

    public ComunicacaoTransientUsrToUsr(ComoUsuario pUsuarioRemetente,
            ComoUsuario destinatario, ItfTipoComunicacao tipoComunicacao) {
        super(pUsuarioRemetente, destinatario, tipoComunicacao);
        usuarioRementente = pUsuarioRemetente;

    }

    @Override
    public ComoUsuario getUsuarioRemetente() {
        return usuarioRementente;
    }

    @Override
    public boolean isRemetenteAguardandoReposta() {
        return true;
    }

    @Override
    public String getTextoRespostaPositivaAoRemetente() {
        return getDestinatario().getUsuario().getNome() + " respondeu positivo sobre " + getAssunto();
    }

    @Override
    public String getTextoRespostaNegativaAoRemetente() {
        return getDestinatario().getUsuario().getNome() + " respondeu negativo sobre " + getAssunto();
    }

    public ComoUsuario getUsuarioRementente() {
        return usuarioRementente;
    }

    public void setUsuarioRementente(ComoUsuario usuarioRementente) {
        this.usuarioRementente = usuarioRementente;
    }

    public String getObservacaoResposta() {
        return observacaoResposta;
    }

    public void setObservacaoResposta(String observacaoResposta) {
        this.observacaoResposta = observacaoResposta;
    }

}
