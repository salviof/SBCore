/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringUrl;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;

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
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuario, String mensagem, String pAssunto) {
        try {
            ItfDialogo comunicacao
                    = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario, tipocomunicacao.getRegistro());

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            comunicacao.setAssunto(pAssunto);
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

        return FabTipoRespostaComunicacao.NAO;

    }

    @Override
    public ComoArmazenamentoComunicacao getArmazenamento() {
        return aramazenamento;
    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItffabricaCanalComunicacao getFabricaCanalPadrao() {
        return ERPTipoCanalComunicacao.INTRANET_MENU;
    }

}
