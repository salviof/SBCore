/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDialogo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Comunicações", tags = "Comunicação")
public class ComunicacaoTransient extends DialogoAbstrato implements ComoDialogo {

    private final ItfDestinatario destinatario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;
    private final ComoUsuario usuarioRemetente;
    private final List<ItfRespostaComunicacao> respostasPossiveis;

    private final ComoTipoComunicacao tipoComunicacao;
    private String mensagem;
    private String assunto;
    private ItfRespostaComunicacao respostaEscolhida;

    private String paginaInstanciaID;

    private long tempoAceitavelResposta = -1;

    public ComunicacaoTransient() {
        this.destinatario = null;
        this.usuarioRemetente = null;
        this.respostasPossiveis = null;
        this.tipoComunicacao = null;
        // para fins de reflexão
    }

    public ComunicacaoTransient(ComoUsuario usuarioRemetente,
            ComoUsuario destinatario, ComoTipoComunicacao tipoComunicacao
    ) {
        this.destinatario = new DestinatarioTransiente(destinatario);
        this.usuarioRemetente = usuarioRemetente;

        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilCRCDialogo.getRespostasPossiveisComunicacao(this);
        defineMensagem();

    }

    public final void defineMensagem() {
        mensagem = UtilCRCComunicacao.gerarMensagem(this);
        assunto = UtilCRCComunicacao.gerarAssunto(this);

    }

    public ComunicacaoTransient(ComoUsuario usuarioRemetente, ItfDestinatario destinatario, ComoTipoComunicacao tipoComunicacao
    ) {
        this.destinatario = destinatario;
        this.usuarioRemetente = usuarioRemetente;
        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilCRCDialogo.getRespostasPossiveisComunicacao(this);

        defineMensagem();

    }

    @Override
    public ItfDestinatario getDestinatario() {
        return destinatario;
    }

    @Override
    public ComoTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        return respostasPossiveis;
    }

    @Override
    public String getMensagem() {
        if (mensagem == null) {
            UtilCRCComunicacao.gerarMensagem(this);
        }
        return mensagem;
    }

    @Override
    public long getTempoAceitavelResposta() {
        return tempoAceitavelResposta;
    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {
        return respostaEscolhida;
    }

    @Override
    public void setRespostaEscolhida(ItfRespostaComunicacao pResposta) {
        respostaEscolhida = pResposta;
    }

    public String getDescricao() {
        if (descricao == null) {
            descricao = "cm" + getAssunto().trim() + "às " + UtilCRCDataHora.getAgoraString(UtilCRCDataHora.FORMATO_TEMPO.DATA_HORA_SISTEMA).trim();
        }
        return descricao;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {
        tempoAceitavelResposta = pTempo;
    }

    public String getMensgem() {
        return mensagem;
    }

    @Override
    public String getAssunto() {
        return assunto;
    }

    @Override
    public void setMensagem(String pMensagem) {
        mensagem = pMensagem;
    }

    @Override
    public void setNome(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public void setAssunto(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public boolean isAssuntoIgualMensagem() {
        if (getMensagem() == null) {
            return false;
        }
        return getMensagem().equals(getNome());
    }

    @Override
    public String getPaginaInstanciaID() {
        return paginaInstanciaID;
    }

    public void setPaginaInstanciaID(String paginaInstanciaID) {
        this.paginaInstanciaID = paginaInstanciaID;
    }

}
