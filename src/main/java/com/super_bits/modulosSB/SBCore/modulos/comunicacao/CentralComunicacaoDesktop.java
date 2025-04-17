/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import javax.swing.JOptionPane;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ItfArmazenamentoComunicacao;

/**
 *
 * @author salvioF
 */
public class CentralComunicacaoDesktop extends CentralComunicaoAbstrato {

    private final ArmazenamentoComunicacaoTransient aramazenamento;

    public CentralComunicacaoDesktop() {
        aramazenamento = new ArmazenamentoComunicacaoTransient();
    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ItfUsuario pUsuario, String mensagem, String pAssunto) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario,
                            tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            if (getArmazenamento().registrarDialogo(comunicacao)) {
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
    public ItfDialogo gerarComunicacaoUsuario_Usuario(FabTipoComunicacao tipocomunicacao, ItfUsuario pUsuarioRemetente, ItfUsuario pUsuarioDestinatario, String pAssunto, String mensagem) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(pUsuarioRemetente, pUsuarioDestinatario,
                            tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            if (getArmazenamento().registrarDialogo(comunicacao)) {
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
    public ItfArmazenamentoComunicacao getArmazenamento() {
        return aramazenamento;
    }

    @Override
    public String getTokenDispositivoNotificacao(ItfUsuario pUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
