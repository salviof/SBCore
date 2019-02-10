/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SalvioF
 */
public class ArmazenamentoComunicacaoTransient implements ItfArmazenamentoComunicacao {

    private final Map<String, ItfComunicacao> comunicacoesAtivas = new HashMap<>();

    @Override
    public boolean registrarInicioComunicacao(ItfComunicacao pComunicacao) {
        try {
            pComunicacao.selarComunicacao();
            comunicacoesAtivas.put(pComunicacao.getCodigoSelo(), pComunicacao);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public boolean regsitrarRespostaComunicacao(String codigoComunicacao, ItfRespostaComunicacao pResposta) {
        try {
            ItfComunicacao comunicacao = comunicacoesAtivas.get(codigoComunicacao);
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

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoDestinatario(ItfUsuario pDestinatario) {
        List<ItfComunicacao> comunicacoes = new ArrayList<>();
        for (ItfComunicacao comunicacao : comunicacoesAtivas.values()) {
            switch (comunicacao.getDestinatario().getTipoDestinatario()) {
                case USUARIO:
                    if (pDestinatario.equals(comunicacao.getDestinatario().getUsuario())) {
                        comunicacoes.add(comunicacao);
                    }
                    break;
                case USUARIOS:
                    if (comunicacao.getDestinatario().getUsuarios().contains(pDestinatario)) {
                        comunicacoes.add(comunicacao);
                    }
                    break;
                case GRUPO:
                    if (comunicacao.getDestinatario().getGrupoUsuario().equals(pDestinatario.getGrupo())) {
                        comunicacoes.add(comunicacao);
                    }
                    break;
                case GRUPOS:
                    if (comunicacao.getDestinatario().getGruposUsuario().contains(pDestinatario.getGrupo())) {
                        comunicacoes.add(comunicacao);
                    }
                default:
                    throw new AssertionError(comunicacao.getDestinatario().getTipoDestinatario().name());

            }
        }
        return comunicacoes;
    }

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoRemetente(ItfUsuario pRemetente) {
        List<ItfComunicacao> comunicacoesEncontradas = new ArrayList<>();
        comunicacoesAtivas.values().stream().filter((cm) -> (cm.getUsuarioRemetente().equals(pRemetente))).forEachOrdered((cm) -> {
            comunicacoesEncontradas.add(cm);
        });
        return comunicacoesEncontradas;

    }

    @Override
    public ItfComunicacao getComunicacaoByCodigoSelo(String pCodigoSelo) {
        return comunicacoesAtivas.get(pCodigoSelo);
    }

}
