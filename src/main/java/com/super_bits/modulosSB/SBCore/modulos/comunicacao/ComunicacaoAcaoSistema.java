/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringVariaveisEntreCaracteres;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.comunicacao.RespostaComunicacao;
import org.coletivojava.fw.api.objetoNativo.comunicacao.TipoComunicacao;
import org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 *
 *
 * @author desenvolvedor
 */
public class ComunicacaoAcaoSistema extends DialogoAbstrato implements ItfComunicacaoAcaoVinculada {

    private final ComoAcaoController acaoVinculada;
    private final ItfTipoComunicacao tipoComunicacao;
    private final List<ItfRespostaComunicacao> repostas;
    private final ComoEntidadeSimples beanVinculado;
    private final ItfDestinatario destinatario;
    private final ComoUsuario usuarioRemetente;

    private long tempoAceitavelResposta;

    private FabStatusComunicacao status;

    private ItfRespostaComunicacao respostaEscolhida;
    private String mensagem;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String assunto;

    public String gerarMensagemPAdrao() {
        assunto = acaoVinculada.getNomeAcao();
        String artigoNome = "o";
        if (UtilCRCReflexaoObjeto.getInfoClasseObjeto(beanVinculado.getClass()).generoFeminino()) {
            artigoNome = "a";
        }
        switch (acaoVinculada.getTipoAcaoGenerica()) {

            case CONTROLLER_DESATIVAR:
                return "Deseja DESATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
            case CONTROLLER_ATIVAR:
                return "Deseja ATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
            case CONTROLLER_ATIVAR_DESATIVAR:
                if (beanVinculado instanceof ComoEntidadeNormal) {

                    ComoEntidadeNormal beanComStatus = (ComoEntidadeNormal) beanVinculado;
                    if (beanComStatus.isAtivo()) {
                        return "Deseja DESATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
                    } else {
                        return "Deseja ATIVAR " + artigoNome + " " + beanVinculado.getNomeDoObjeto() + " " + beanVinculado.getNome() + " ?";
                    }
                }

        }
        if (UtilCRCReflexaoObjeto.getInfoClasseObjeto(beanVinculado.getClass()).generoFeminino()) {
            mensagem = "Deseja " + acaoVinculada.getNomeAcao() + "-" + beanVinculado.getNome() + " ";
        } else {
            mensagem = "Deseja " + acaoVinculada.getNomeAcao() + "-" + beanVinculado.getNome() + " ";
        }
        return mensagem;
    }

    public ComunicacaoAcaoSistema(ComoAcaoController pAcaoVinculada, ComoEntidadeSimples pBeanVinculado) {
        this.acaoVinculada = pAcaoVinculada;
        this.tipoComunicacao = pAcaoVinculada.getTipoComunicacao();

        this.beanVinculado = pBeanVinculado;
        this.destinatario = new DestinatarioTransiente(SBCore.getUsuarioLogado());
        this.usuarioRemetente = new UsuarioSistemaRoot();

        this.repostas = UtilCRCComunicacao.getRespostaCOmunicacao(this);

        if (this.repostas.isEmpty()) {

            repostas.addAll(UtilCRCComunicacao.getRespostaCOmunicacao(FabTipoComunicacao.CONFIRMAR_CANCELAR, this));

        }
        String mensagemModeloPredefinida = pAcaoVinculada.getTipoComunicacao().getMensagemModeloPredefinida();

    }

    @Override
    public String getMensagem() {
        InfoTipoAcaoController anotacao = UtilCRCReflexao.getAnotacaoEmEnum(InfoTipoAcaoController.class, (Enum) acaoVinculada.getEnumAcaoDoSistema());

        if (anotacao != null && UtilCRCStringValidador.isNAO_NuloNemBranco(anotacao.fraseComunicação())) {
            return UtilCRCStringVariaveisEntreCaracteres.gerarTextoSubstituindoAtributosSimples(anotacao.fraseComunicação(), beanVinculado);
        }
        if (tipoComunicacao.getMensagemModeloPredefinida() == null
                || tipoComunicacao.getMensagemModeloPredefinida().isEmpty()) {

            return gerarMensagemPAdrao();
        }
        if (tipoComunicacao.getMensagemModeloPredefinida().equals(TipoComunicacao.MENSAGEM_PADRAO_COMUNICACAO)) {
            return gerarMensagemPAdrao();
        }

        return tipoComunicacao.getMensagemModeloPredefinida();
    }

    /**
     *
     * @return
     */
    @Override
    public ComoAcaoController getAcaoVinculada() {
        return acaoVinculada;
    }

    @Override
    public ItfTipoComunicacao getTipoComunicacao() {
        return tipoComunicacao;
    }

    public ComoEntidadeSimples getBeanVinculado() {
        return beanVinculado;
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
    public List<ItfRespostaComunicacao> getRepostasPossiveis() {
        return repostas;
    }

    @Override
    public ItfRespostaComunicacao getRespostaEscolhida() {
        return respostaEscolhida;
    }

    @Override
    public void setRespostaEscolhida(ItfRespostaComunicacao pResposta) {
        respostaEscolhida = pResposta;
    }

    @Override
    public long getTempoAceitavelResposta() {
        return tempoAceitavelResposta;
    }

    @Override
    public void setTempoAceitavelResposta(long pTempo) {
        tempoAceitavelResposta = pTempo;
    }

    @Override
    public String getAssunto() {
        return getMensagem();
    }

    @Override
    public void setNome(String pAssunto) {
        assunto = pAssunto;
    }

    @Override
    public void setMensagem(String pMensagem) {
        mensagem = pMensagem;
    }

    @Override
    public FabStatusComunicacao getStatusComunicacao() {
        if (status == null && isFoiSelado()) {
            status = FabStatusComunicacao.SELADO;
        }

        return status;
    }

    @Override
    public void setStatusComunicacao(FabStatusComunicacao pStatus) {
        status = pStatus;
    }

    @Override
    public void setAssunto(String pAssunto) {
        assunto = pAssunto;
    }

}
