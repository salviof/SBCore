/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoArmazenamentoComunicacao;

/**
 *
 * @author SalvioF
 */
public class ArmazenamentoComunicacaoTransient implements ComoArmazenamentoComunicacao {

    private final Map<String, ItfDialogo> comunicacoesAtivas = new HashMap<>();
    private final Map<String, ItfDialogoEntrePessoas> comunicacoesAguardantoRespostaUrToUsr = new HashMap<>();

    public ArmazenamentoComunicacaoTransient() {
        System.out.println("Armazenamento Comunicacao Transient");
    }

    protected Map<String, ItfDialogo> getComunicacoesAtivas() {

        return comunicacoesAtivas;
    }

    protected Map<String, ItfDialogoEntrePessoas> getComunicacoesEntreUsrAguardandoResposta() {
        return comunicacoesAguardantoRespostaUrToUsr;
    }

    @Override
    public List<ItfDialogo> getDialogos(ComoUsuario pUsuario, ERPTipoCanalComunicacao pCanal) {
        List<ItfDialogo> dialogos = new ArrayList<>();
        comunicacoesAtivas.values().stream().filter(dlg -> pUsuario.getEmail().equals(dlg.getDestinatario().getUsuario().getEmail())).forEach(dialogos::add);
        return dialogos;
    }

    @Override
    public List<ItfDialogoEntrePessoas> getMensagemAguardandoMinhaResposta(ComoUsuario pUsuario, ERPTipoCanalComunicacao pCanal) {
        List<ItfDialogoEntrePessoas> dialogos = new ArrayList<>();
        comunicacoesAguardantoRespostaUrToUsr.values().stream().filter(dlg -> pUsuario.getEmail().equals(dlg.getDestinatario().getUsuario().getEmail())).forEach(dialogos::add);
        return dialogos;
    }

    @Override
    public List<ItfDialogoEntrePessoas> getMensagemAguardandoRespostaDeOutra(ComoUsuario pUsuario, ERPTipoCanalComunicacao pCanal) {
        List<ItfDialogoEntrePessoas> dialogos = new ArrayList<>();
        comunicacoesAguardantoRespostaUrToUsr.values().stream().filter(dlg -> pUsuario.getEmail().equals(dlg.getComoDialogoEntrePesoas().getUsuarioRemetente().getEmail())).forEach(dialogos::add);
        return dialogos;
    }

    @Override
    public boolean registrarDialogoAtivo(ItfDialogo pComunicacao) {
        try {
            if (pComunicacao.isUmDialogoEntrePessoas()) {
                getComunicacoesEntreUsrAguardandoResposta().put(pComunicacao.getCodigoSelo(), pComunicacao.getComoDialogoEntrePesoas());
            } else {
                getComunicacoesAtivas().put(pComunicacao.getCodigoSelo(), pComunicacao);
            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Armazenando comunicação " + t.getMessage(), t);
            return false;
        }
    }

    @Override
    public boolean atualizarNotificacoesAtivas() {
        //Comunicação transiente não possui repositorio persistido
        return true;
    }

    private class OrdemComunicacaoMaisNovoPrimeiro implements Comparator<ItfDialogo> {

        @Override
        public int compare(ItfDialogo o1, ItfDialogo o2) {

            return (o1.getDataHoraDisparo().getTime() < o2.getDataHoraDisparo().getTime() ? 1 : -1);

        }

    }

    @Override
    public ItfDialogo getDialogoAtivoByCodigoSelo(String pCodigoSelo) {
        if (getComunicacoesAtivas().containsKey(pCodigoSelo)) {
            return getComunicacoesAtivas().get(pCodigoSelo);
        }
        if (getComunicacoesEntreUsrAguardandoResposta().containsKey(pCodigoSelo)) {
            return getComunicacoesEntreUsrAguardandoResposta().get(pCodigoSelo);
        }
        //TODO BUSCAR EM NOTIFICAÇÔES
        return null;
    }

    @Override
    public boolean removerDialogoAtivo(String pCodigoSelo) {

        if (!getComunicacoesAtivas().containsKey(pCodigoSelo) && !getComunicacoesEntreUsrAguardandoResposta().containsKey(pCodigoSelo)) {
            return false;
        }
        if (getComunicacoesEntreUsrAguardandoResposta().containsKey(pCodigoSelo)) {
            getComunicacoesEntreUsrAguardandoResposta().remove(pCodigoSelo);
        }
        if (getComunicacoesAtivas().containsKey(pCodigoSelo)) {
            getComunicacoesAtivas().remove(pCodigoSelo);
        }

        return true;
    }

}
