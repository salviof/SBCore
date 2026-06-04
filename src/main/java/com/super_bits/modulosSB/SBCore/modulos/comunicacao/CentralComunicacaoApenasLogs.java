/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CentralComunicacaoApenasLogs extends CentralComunicaoAbstrato {

    private final ArmazenamentoComunicacaoTransient aramazenamento;

    public CentralComunicacaoApenasLogs() {
        aramazenamento = new ArmazenamentoComunicacaoTransient();
    }

    @Override
    public ItfDialogo gerarComunicacaoUsuario_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuarioRemetente, ComoUsuario pUsuarioDestinatario, String mensagem, String pAssunto) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(pUsuarioRemetente, pUsuarioDestinatario, tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
            comunicacao.setAssunto(pAssunto);
            comunicacao.setNome(mensagem);
            if (getArmazenamento().registrarDialogoAtivo(comunicacao)) {
                return comunicacao;
            } else {
                return null;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando comunicação entre usuários", t);
            return null;
        }
    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuario, String mensagem, String pAssunto) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario, tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            comunicacao.setAssunto(pAssunto);
            if (getArmazenamento().registrarDialogoAtivo(comunicacao)) {
                return comunicacao;
            } else {
                return null;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando comunicação entre usuários", t);
            return null;
        }

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pTransporte,
            ItfDialogo pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();

        return FabTipoRespostaComunicacao.NAO;

    }

    @Override
    public ComoArmazenamentoComunicacao getArmazenamento() {
        return aramazenamento;
    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return null;
    }

    @Override
    public ItffabricaCanalComunicacao getCanalPadrao() {
        return ERPTipoCanalComunicacao.INTRANET_MENU;
    }

    @Override
    public boolean notificarViaMenu(ItfDialogo pDialogo) {
        CarameloCode.getServicoLogEventos().registrarLogDeEvento(FabMensagens.AVISO, pDialogo.getMensagem());
        return true;
    }

    @Override
    public boolean notificarViaBloqueioTEla(ItfDialogo pDialogo) {
        CarameloCode.getServicoLogEventos().registrarLogDeEvento(FabMensagens.ALERTA, pDialogo.getMensagem());
        return true;
    }

}
