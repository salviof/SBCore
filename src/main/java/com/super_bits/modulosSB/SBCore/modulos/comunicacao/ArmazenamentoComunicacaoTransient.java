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

    @Override
    public boolean registrarDialogo(ItfDialogo pComunicacao) {
        try {

            comunicacoesAtivas.put(pComunicacao.getCodigoSelo(), pComunicacao);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Armazenando comunicação " + t.getMessage(), t);
            return false;
        }
    }

    @Override
    public boolean regsitrarRespostaDialogo(String codigoComunicacao, ItfRespostaComunicacao pResposta) {
        try {
            ItfDialogo comunicacao = comunicacoesAtivas.get(codigoComunicacao);
            if (codigoComunicacao == null) {
                throw new UnsupportedOperationException("Comunicação não encontrada no sistema");
            }
            comunicacao.setRespostaEscolhida(pResposta);
            comunicacoesAtivas.remove(codigoComunicacao);
            return true;
        } catch (Throwable t) {
            return false;
        }

    }

    @Override
    public boolean limparComunicacaoExpirada() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<ItfDialogo> getComunicacoesAguardandoRespostaDoDestinatario(ComoUsuario pDestinatario) {
        List<ItfDialogo> comunicacoes = new ArrayList<>();

        comunicacoesAtivas.values().stream().
                filter(cm -> isComunicacaoEdousuario(pDestinatario, cm)).
                sorted(new OrdemComunicacaoMaisNovoPrimeiro()).forEach(comunicacoes::add);

        return comunicacoes;
    }

    @Override
    public List<ItfDialogo> getComunicacoesAguardandoRespostaDoRemetente(ComoUsuario pRemetente) {
        List<ItfDialogo> comunicacoesEncontradas = new ArrayList<>();
        comunicacoesAtivas.values().stream().filter((cm) -> (cm.getUsuarioRemetente().equals(pRemetente))).limit(5).forEachOrdered((cm) -> {
            comunicacoesEncontradas.add(cm);
        });
        return comunicacoesEncontradas;

    }

    @Override
    public ItfDialogo getDialogoByCodigoSelo(String pCodigoSelo) {
        return comunicacoesAtivas.get(pCodigoSelo);
    }

    @Override
    public boolean removerDialogoByCodigoSelo(String pCodigoSelo) {
        if (!comunicacoesAtivas.containsKey(pCodigoSelo)) {
            return false;
        }
        comunicacoesAtivas.remove(pCodigoSelo);
        return true;
    }

}
