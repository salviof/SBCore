/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public abstract class CentralComunicaoAbstrato implements ItfCentralComunicacao {

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoDestinatario(ItfUsuario pDestinatario) {
        return getAramazenamento().getComunicacoesAguardandoRespostaDoDestinatario(pDestinatario);
    }

    @Override
    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoRemetente(ItfUsuario pRemetente) {
        return getAramazenamento().getComunicacoesAguardandoRespostaDoRemetente(pRemetente);
    }

    @Override
    public boolean responderComunicacao(ItfComunicacao pComunicacao, ItfRespostaComunicacao pResposta) {
        return getAramazenamento().regsitrarRespostaComunicacao(pComunicacao.getCodigoSelo(), pResposta);
    }

    @Override
    public ItfComunicacao getComnunicacaoRegistrada(String codigoComunicacao) {
        return getAramazenamento().getComunicacaoByCodigoSelo(codigoComunicacao);
    }

    public List<ItfTipoTransporteComunicacao> gerarListaTransportes(ItffabricaTrasporteCominicacao... fabricas) {
        List<ItfTipoTransporteComunicacao> transportes = new ArrayList<>();
        for (ItffabricaTrasporteCominicacao fab : fabricas) {
            transportes.add((ItfTipoTransporteComunicacao) fab.getRegistro());
        }
        return transportes;
    }

    @Override
    public ItffabricaTrasporteCominicacao getFabricaTransportePadrao() {
        return null;
    }

}
