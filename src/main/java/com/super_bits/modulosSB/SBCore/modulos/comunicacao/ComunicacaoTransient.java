/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(plural = "Comunicações", tags = "Comunicação")
public class ComunicacaoTransient extends DialogoAbstrato implements ItfDialogo {

    private final ItfDestinatario destinatario;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;
    private final ComoUsuario usuarioRemetente;
    private final List<ItfRespostaComunicacao> respostasPossiveis;

    private final ItfTipoComunicacao tipoComunicacao;
    private String mensgem;
    private String assunto;
    private ItfRespostaComunicacao respostaEscolhida;

    private long tempoAceitavelResposta = -1;

    public ComunicacaoTransient(ComoUsuario usuarioRemetente,
            ComoUsuario destinatario, ItfTipoComunicacao tipoComunicacao
    ) {
        this.destinatario = new DestinatarioTransiente(destinatario);
        this.usuarioRemetente = usuarioRemetente;

        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilSBCoreComunicacao.getRespostaCOmunicacao(this);
        defineMensagem();

    }

    public final void defineMensagem() {
        mensgem = UtilSBCoreComunicacao.gerarMensagem(this);
        assunto = UtilSBCoreComunicacao.gerarAssunto(this);

    }

    public ComunicacaoTransient(ComoUsuario usuarioRemetente, ItfDestinatario destinatario, ItfTipoComunicacao tipoComunicacao
    ) {
        this.destinatario = destinatario;
        this.usuarioRemetente = usuarioRemetente;
        this.tipoComunicacao = tipoComunicacao;
        this.respostasPossiveis = UtilSBCoreComunicacao.getRespostaCOmunicacao(this);

        defineMensagem();

    }

    @Override
    public ItfDestinatario getDestinatario() {
        return destinatario;
    }

    @Override
    public ComoUsuario getUsuarioRemetente() {
        return usuarioRemetente;
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    @Override
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        return respostasPossiveis;
    }

    @Override
    public String getMensagem() {
        if (mensgem == null) {
            UtilSBCoreComunicacao.gerarMensagem(this);
        }
        return mensgem;
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
            descricao = "cm" + getUsuarioRemetente().getEmail() + "às ";
        }
        return descricao;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {
        tempoAceitavelResposta = pTempo;
    }

    public String getMensgem() {
        return mensgem;
    }

    @Override
    public String getAssunto() {
        return assunto;
    }

    @Override
    public void setMensagem(String pMensagem) {
        mensgem = pMensagem;
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

}
