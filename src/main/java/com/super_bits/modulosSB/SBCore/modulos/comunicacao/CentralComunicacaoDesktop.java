/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioOSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

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
    public ItfComunicacao gerarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao,
            ItfUsuario pRemetente, ItfDestinatario pDestinatario,
            String mensagem,
            ItffabricaTrasporteCominicacao... tiposTransporte
    ) {

        try {
            ItfComunicacao comunicacao
                    = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

            comunicacao.setMensagem(mensagem);
            comunicacao.setNome(mensagem);
            if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
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
    public ItfComunicacao iniciarComunicacaoEntre_Usuairos(FabTipoComunicacao tipocomunicacao, ItfUsuario pRemetente, ItfDestinatario pDestinatario, ItfModeloMensagem mensagem, ItffabricaTrasporteCominicacao... tiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(pRemetente, pDestinatario, tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));

        getAramazenamento().registrarInicioComunicacao(comunicacao);

        for (ItffabricaTrasporteCominicacao tipoTranposporte : tiposTransporte) {
            try {

                ItfDisparoComunicacao disparo = (ItfDisparoComunicacao) tipoTranposporte.getImplementacaoDoContexto();
                disparo.dispararInicioComunicacao(comunicacao);
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando mensagem de comunicação", t);
            }
        }
        throw new UnsupportedOperationException("Processador de mensagem ainda não implementado;");

    }

    @Override
    public FabTipoRespostaComunicacao aguardarRespostaComunicacao(ItfTipoTransporteComunicacao pTransporte,
            ItfComunicacao pComunicacao, int tempoAguardar, FabTipoRespostaComunicacao pTipoRespostaTempoFinal) {
        FabTipoComunicacao tipocomunicacao = pComunicacao.getTipoComunicacao().getFabTipoComunicacao();
        /**
         * switch (pTransporte) { case EMAIL: break; case SMS: break; case
         * INTRANET_MENU: break; case INTRANET_MODAL: switch (tipocomunicacao) {
         * case NOTIFICAR: break; case PERGUNTAR_SIM_OU_NAO: int dialogResult =
         * JOptionPane.showConfirmDialog(null, pComunicacao.getMensagem(),
         * "Deseja continuar?", JOptionPane.YES_OPTION); if (dialogResult ==
         * JOptionPane.YES_OPTION) { return FabTipoRespostaComunicacao.SIM; }
         * else { System.out.println("não"); return
         * FabTipoRespostaComunicacao.NAO; }
         *
         * case PERGUNTAR_SIM_NAO_IGNORAR: break; case
         * PERGUNTAR_SIM_NAO_MAIS_TARDE: break; case SOLICITAR_AUTORIZACAO:
         * break; default: throw new AssertionError(tipocomunicacao.name());
         *
         * }
         * return FabTipoRespostaComunicacao.NAO_ENTENDIDO;
         *
         * case API_PERSONALIZADA: break; case WHATZAUP: break; default: return
         * null;
         *
         * }
         * return null;
         */
        return null;
    }

    @Override
    public ItfComunicacao iniciarComunicacaoSistema_Usuairo(FabTipoComunicacao tipocomunicacao,
            ItfUsuario pUsuario, String mensagem, ItffabricaTrasporteCominicacao... pTiposTransporte) {
        ItfComunicacao comunicacao = new ComunicacaoTransient(pUsuario, pUsuario, tipocomunicacao.getRegistro(), gerarListaTransportes(pTiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setNome(mensagem);
        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {
            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfComunicacao gerarComunicacaoSistema_UsuairoLogado(FabTipoComunicacao tipocomunicacao, String mensagem, ItffabricaTrasporteCominicacao... tiposTransporte) {
        ItfComunicacao comunicacao
                = new ComunicacaoTransient(new UsuarioOSistema(), SBCore.getUsuarioLogado(),
                        tipocomunicacao.getRegistro(), gerarListaTransportes(tiposTransporte));
        comunicacao.setMensagem(mensagem);
        comunicacao.setNome(mensagem);
        if (getAramazenamento().registrarInicioComunicacao(comunicacao)) {

            return comunicacao;
        } else {
            return null;
        }
    }

    @Override
    public ItfArmazenamentoComunicacao getAramazenamento() {
        return aramazenamento;
    }
}
