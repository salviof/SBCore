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

    public ArmazenamentoComunicacaoTransient() {
        System.out.println("Armazenamento Comunicacao Transient");
    }

    protected Map<String, ItfDialogo> getComunicacoesAtivas() {

        return comunicacoesAtivas;
    }

    @Override
    public boolean registrarDialogoAtivo(ItfDialogo pComunicacao) {
        try {

            getComunicacoesAtivas().put(pComunicacao.getCodigoSelo(), pComunicacao);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Armazenando comunicação " + t.getMessage(), t);
            return false;
        }
    }

    @Override
    public List<ItfDialogo> getDialogos(ComoUsuario pUsuario, ERPTipoCanalComunicacao pCanal) {
        List<ItfDialogo> dialogos = new ArrayList<>();
        comunicacoesAtivas.values().stream().forEach(dialogos::add);
        return dialogos;
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

    private boolean isComunicacaoEdousuario(ComoUsuario pUsuario, ItfDialogo pComunicacao) {
        switch (pComunicacao.getDestinatario().getTipoDestinatario()) {
            case USUARIO:
                if (pUsuario.equals(pComunicacao.getDestinatario().getUsuario())) {
                    return true;
                }
                break;
            case USUARIOS:
                if (pComunicacao.getDestinatario().getUsuarios().contains(pUsuario)) {
                    return true;
                }
                break;
            case GRUPO:
                if (pComunicacao.getDestinatario().getGrupoUsuario().equals(pUsuario.getGrupo())) {
                    return true;
                }
                break;
            case GRUPOS:
                if (pComunicacao.getDestinatario().getGruposUsuario().contains(pUsuario.getGrupo())) {
                    return true;
                }
            default:
                throw new AssertionError(pComunicacao.getDestinatario().getTipoDestinatario().name());

        }
        return false;
    }

    @Override
    public ItfDialogo getDialogoAtivoByCodigoSelo(String pCodigoSelo) {
        if (getComunicacoesAtivas().containsKey(pCodigoSelo)) {
            return getComunicacoesAtivas().get(pCodigoSelo);
        }
        //TODO BUSCAR EM NOTIFICAÇÔES
        return null;
    }

    @Override
    public boolean removerDialogoAtivo(String pCodigoSelo) {
        if (!getComunicacoesAtivas().containsKey(pCodigoSelo)) {
            return false;
        }
        getComunicacoesAtivas().remove(pCodigoSelo);
        return true;
    }

}
