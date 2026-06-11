/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import javax.swing.JOptionPane;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;

/**
 *
 * @author salvioF
 */
public class CentralComunicacaoDesktopTransient extends CentralComunicaoAbstrato {

    protected ComoArmazenamentoComunicacao armazenamento;

    public CentralComunicacaoDesktopTransient() {

    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuario, String mensagem, String pAssunto) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario,
                            tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
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
    public ItfDialogo gerarComunicacaoUsuario_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuarioRemetente, ComoUsuario pUsuarioDestinatario, String pAssunto, String mensagem) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(pUsuarioRemetente, pUsuarioDestinatario,
                            tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
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
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoCanalComunicacao pTransporte,
            ItfDialogo pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();

        int dialogResult
                = JOptionPane.showConfirmDialog(null, pComunicacao.getMensagem(),
                        "Deseja continuar?", JOptionPane.YES_OPTION);
        if (dialogResult
                == JOptionPane.YES_OPTION) {
            return FabTipoRespostaComunicacao.SIM;
        } else {
            System.out.println("não");
            return FabTipoRespostaComunicacao.NAO;
        }

    }

    @Override
    public ComoArmazenamentoComunicacao getArmazenamento() {
        if (armazenamento == null) {
            armazenamento = new ArmazenamentoComunicacaoTransient();
        }
        return armazenamento;
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
    public boolean responderComunicacao(String codigoSeloComunicacao, ItfRespostaComunicacao pResposta, ERPTipoCanalComunicacao pErpCanal) {
        return getArmazenamento().removerDialogoAtivo(codigoSeloComunicacao);
    }

    @Override
    public boolean notificarViaMenu(ItfDialogo pDialogo) {
        System.out.println("Notificado via menu, para :" + pDialogo.getDestinatario().getUsuario().getNome());
        System.out.println(pDialogo.getAssunto());
        System.out.println(pDialogo.getMensagem());
        return true;
    }

    @Override
    public boolean notificarViaBloqueioTEla(ItfDialogo pDialogo) {
        int dialogResult
                = JOptionPane.showConfirmDialog(null, pDialogo.getMensagem(),
                        "Olá, " + pDialogo.getDestinatario().getUsuario().getNome() + ". Deseja continuar?", JOptionPane.YES_OPTION);
        if (dialogResult
                == JOptionPane.YES_OPTION) {
            return true;
        } else {
            System.out.println("não");
            return false;
        }
    }

}
