/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroAcessandoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroRegistrandoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroSelandoDialogo;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;

/**
 *
 * @author SalvioF
 */
public abstract class CentralComunicaoAbstrato implements ComoServicoComunicacao {

    @Override
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuario, String mensagem) {
        return gerarComunicacaoSistema_Usuario(tipocomunicacao, pUsuario, mensagem, UtilCRCStringFiltros.getPrimeirasXLetrasDaString(mensagem, 140));
    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_UsuarioLogado(FabTipoComunicacao tipocomunicacao, String mensagem) {
        return gerarComunicacaoSistema_Usuario(tipocomunicacao, SBCore.getUsuarioLogado(), mensagem, UtilCRCStringFiltros.getPrimeirasXLetrasDaString(mensagem, 140));
    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_UsuarioLogado(FabTipoComunicacao tipocomunicacao, String mensagem, String pAssunto) {
        return gerarComunicacaoSistema_Usuario(tipocomunicacao, SBCore.getUsuarioLogado(), mensagem, pAssunto);
    }

    @Override
    public ItfDialogo gerarComunicacaoSistema_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuario, String pAssunto, String mensagem) {
        ItfDialogo comunicacao = new ComunicacaoTransient(new UsuarioAplicacaoEmExecucao(), pUsuario, tipocomunicacao.getRegistro());
        comunicacao.setMensagem(mensagem);
        comunicacao.setAssunto(pAssunto);
        return comunicacao;
    }

    @Override
    public ItfDialogo gerarComunicacaoUsuario_Usuario(FabTipoComunicacao tipocomunicacao, ComoUsuario pUsuarioRemetente, ComoUsuario pUsuarioDestinatario, String pAssunto, String mensagem) {
        ItfDialogo comunicacao = new ComunicacaoTransient(pUsuarioRemetente, pUsuarioDestinatario, tipocomunicacao.getRegistro());
        comunicacao.setMensagem(mensagem);
        comunicacao.setAssunto(pAssunto);

        return comunicacao;
    }

    @Override
    public String dispararComunicacao(ItfDialogo pComunicacao, ItffabricaCanalComunicacao pTipoCanalComunicacao) throws ErroAcessandoCanalComunicacao {
        if (!pComunicacao.isFoiSelado()) {
            try {
                selarComunicacao(pComunicacao);
            } catch (ErroSelandoDialogo ex) {
                throw new ErroAcessandoCanalComunicacao("Falha gerando selo de comunicação " + pComunicacao.getAssunto());
            }
        }

        if (getArmazenamento().getDialogoAtivoByCodigoSelo(pComunicacao.getCodigoSelo()) == null) {
            try {
                getArmazenamento().registrarDialogoAtivo(pComunicacao);
            } catch (ErroRegistrandoDialogo ex) {
                throw new ErroAcessandoCanalComunicacao("Falha registrando dialogo para encaminhamento no canal " + pTipoCanalComunicacao);
            }
        }

        try {
            Object implementacao = pTipoCanalComunicacao.getImplementacaoDoContexto();
            ItfDisparoComunicacao execucaoTransporteCM = (ItfDisparoComunicacao) implementacao;
            if (!execucaoTransporteCM.validarDadosDisparo(pComunicacao)) {
                throw new ErroAcessandoCanalComunicacao("Mensagem não tem os dados nescessários para enviar via " + pTipoCanalComunicacao);
            }
            String codigoReciboDisparo = execucaoTransporteCM.dispararInicioComunicacao(pComunicacao);

            if (codigoReciboDisparo == null) {
                throw new ErroAcessandoCanalComunicacao(" Falha disparando via " + pTipoCanalComunicacao.toString() + "o codigo do recibo de disparo recebido é nulo ");
            }
            return codigoReciboDisparo;

            //getArmazenamento().getDialogoAtivoByCodigoSelo(codigoReciboDisparo)
        } catch (Throwable t) {
            if (t instanceof ErroAcessandoCanalComunicacao) {
                throw t;
            }
            throw new ErroAcessandoCanalComunicacao("Falha enviando dialogo, usando canal " + pTipoCanalComunicacao);
        }

    }

    public List<ItfTipoCanalComunicacao> gerarListaTransportes(ItffabricaCanalComunicacao... fabricas) {
        List<ItfTipoCanalComunicacao> transportes = new ArrayList<>();
        for (ItffabricaCanalComunicacao fab : fabricas) {
            transportes.add((ItfTipoCanalComunicacao) fab.getRegistro());
        }
        return transportes;
    }

    @Override
    public synchronized boolean selarComunicacao(ItfDialogo pcomunicacao) throws ErroSelandoDialogo {
        try {
            Thread.sleep(2);
            if (!pcomunicacao.isFoiSelado()) {
                String usuario = pcomunicacao.getUsuarioRemetente().getEmail();
                String destinatario = pcomunicacao.getDestinatario().getEmailsConcatenados();
                Long idDataHora = pcomunicacao.getDataHoraDisparo().getTime();
                int id = (usuario + destinatario + String.valueOf(idDataHora)).hashCode();
                String codigoSelo = String.valueOf(id);

                pcomunicacao.setCodigoSelo(codigoSelo);
                if (pcomunicacao.getStatusComunicacao() == null) {
                    pcomunicacao.setStatusComunicacao(FabStatusComunicacao.SELADO);
                }
                return true;
            }
        } catch (Throwable t) {
            throw new ErroSelandoDialogo("Falha selando comunicação" + t.getMessage());
        }
        return pcomunicacao.isFoiSelado();
    }

    @Override
    public boolean responderComunicacao(String codigoSeloComunicacao, ItfRespostaComunicacao pResposta, ERPTipoCanalComunicacao pErpCanal) {

        return getArmazenamento().removerDialogoAtivo(codigoSeloComunicacao);

    }

    @Override
    public List<ItfDialogo> getNotificacoesAtivasMenu() {
        return getArmazenamento().getDialogos(CarameloCode.getUsuarioLogado(), ERPTipoCanalComunicacao.INTRANET_MENU);
    }

    @Override
    public List<ItfDialogo> getNotificacoesAtivasBloqueioTela() {
        return getArmazenamento().getDialogos(CarameloCode.getUsuarioLogado(), ERPTipoCanalComunicacao.INTRANET_BLOQUEIO_TELA);
    }

    @Override
    public List<ItfDialogo> dispararNotificacaoAcaoSucesso(ComoAcaoDoSistema pAcao, ComoEntidadeSimples pEntidadeRetorno) {
        return new ArrayList<>();
    }

    @Override
    public void atualizarGatilhosDeNotificacaoPorAcao() {
        System.out.println("Sem gatilhos de notificação por ação");
    }
}
