/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import br.org.coletivojava.erp.comunicacao.transporte.ERPTipoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroAcessandoCanalComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroRegistrandoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ErroSelandoDialogo;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
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
    public ItffabricaCanalComunicacao getFabricaCanalPadrao() {
        return ERPTipoCanalComunicacao.INTRANET_MENU;
    }

    @Override
    public List<ItfDialogo> getComunicacoesAguardandoRespostaDoDestinatario(ComoUsuario pDestinatario) {
        return getArmazenamento().getComunicacoesAguardandoRespostaDoDestinatario(pDestinatario);
    }

    @Override
    public List<ItfDialogo> getComunicacoesAguardandoRespostaDoRemetente(ComoUsuario pRemetente) {
        return getArmazenamento().getComunicacoesAguardandoRespostaDoRemetente(pRemetente);
    }

    @Override
    public boolean responderComunicacao(String codigoSeloComunicacao, ItfRespostaComunicacao pResposta) {
        return getArmazenamento().regsitrarRespostaDialogo(codigoSeloComunicacao, pResposta);
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
    public ItfDialogo registrarDialogo(String pCodigoRegistroDialogo, ItfDialogo pDialogo) throws ErroRegistrandoDialogo {
        if (pCodigoRegistroDialogo == null || pCodigoRegistroDialogo.isEmpty()) {
            throw new ErroRegistrandoDialogo("O Código enviado não pode ser nulo");
        }
        ItfDialogo dialogoJaResgistrado = getArmazenamento().getDialogoByCodigoSelo(pCodigoRegistroDialogo);
        if (dialogoJaResgistrado != null) {
            return dialogoJaResgistrado;
        }
        pDialogo.setCodigoSelo(pCodigoRegistroDialogo);
        getArmazenamento().registrarDialogo(pDialogo);
        return getArmazenamento().getDialogoByCodigoSelo(pCodigoRegistroDialogo);
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

        if (getArmazenamento().getDialogoByCodigoSelo(pComunicacao.getCodigoSelo()) == null) {
            try {
                getArmazenamento().registrarDialogo(pComunicacao);
            } catch (ErroRegistrandoDialogo ex) {
                throw new ErroAcessandoCanalComunicacao("Falha registrando dialogo para encaminhamento no canal " + pTipoCanalComunicacao);
            }
        }

        try {
            Object implementacao = pTipoCanalComunicacao.getImplementacaoDoContexto();
            ItfDisparoComunicacao execucaoTransporteCM = (ItfDisparoComunicacao) implementacao;
            String codigoReciboDisparo = execucaoTransporteCM.dispararInicioComunicacao(pComunicacao);
            if (codigoReciboDisparo == null) {
                throw new ErroAcessandoCanalComunicacao("Codigo do recibo de disparo recebido é nulo " + pTipoCanalComunicacao);
            }
            return codigoReciboDisparo;
            //getArmazenamento().getDialogoByCodigoSelo(codigoReciboDisparo)
        } catch (Throwable t) {
            if (t instanceof ErroAcessandoCanalComunicacao) {
                throw t;
            }
            throw new ErroAcessandoCanalComunicacao("Falha enviando dialogo, usando canal " + pTipoCanalComunicacao);
        }

    }

    @Override
    public ItfDialogo getComnunicacaoRegistrada(String codigoComunicacao) {
        return getArmazenamento().getDialogoByCodigoSelo(codigoComunicacao);
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

}
