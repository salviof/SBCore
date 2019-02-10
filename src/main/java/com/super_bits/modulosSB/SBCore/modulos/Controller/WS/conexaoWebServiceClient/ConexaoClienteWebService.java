/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreClienteRest;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRest;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import org.json.simple.JSONObject;

/**
 *
 * @author SalvioF
 */
public class ConexaoClienteWebService extends ConexaoClienteWebServiceBasico implements ItfConexaoClientWebService {

    private final ConsumoWSExecucao consumoWS;

    private int tempoEsperaMaximo = 5000;
    private boolean permitirModificacoesPreEnviar;
    private boolean postarInformacoes;
    private InfoConsumoRestService infoConsumo;
    private final Map<String, String> CABECALHO = new HashMap<>();
    private String corpoRequisicao;
    private String[] parametros;

    ;

    public ConexaoClienteWebService(ItfFabricaIntegracaoRest pConexao, String... parametros) {
        this(pConexao, false, parametros);

    }

    @Override
    public void adicionarHeaders(Map<String, String> pHeader) {

    }

    public ItfFabricaIntegracaoRest<?> getFabIntegracao() {
        return (ItfFabricaIntegracaoRest<?>) fabricaIntegracao;
    }

    @Override
    public void adicionarHeaders(Map<String, String> pHeader, boolean pLimparHeader) {

    }

    private void configurarPropriedadesPadrao() {
        switch (fabricaIntegracao.getTipoRequisicao()) {
            case POST:
            case PUT:
            case DELET:
                postarInformacoes = true;

                break;
            case GET:
                break;

            case PATCH:
                break;
            case INDETERMINADO:

                break;

            default:
                throw new AssertionError(infoConsumo.tipoConexao().name());

        }

        if (infoConsumo.tipoInformacaoEnviada().getMediaType() != null) {
            CABECALHO.put(HttpHeaders.CONTENT_TYPE, infoConsumo.tipoInformacaoEnviada().getMediaType().toString());
        }
        corpoRequisicao = getFabIntegracao().getCorpoRequisicao(parametros);

        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(corpoRequisicao)) {
            CABECALHO.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(corpoRequisicao.length()));
        }

    }

    /**
     *
     * @param pConexao
     * @param pModificarPropriedades Permitie modificar prorpiedades antes da
     * conexao (não executa conexção imediata..)
     * @param pParametros
     */
    public ConexaoClienteWebService(ItfFabricaIntegracaoRest pConexao, boolean pModificarPropriedades, String... pParametros) {
        super(pConexao);
        System.out.println("Criando conexao de" + pConexao.toString() + " modiProp=" + pModificarPropriedades + " parametros=" + UtilSBCoreListas.getValoresSeparadosPorVirgula(Lists.newArrayList(pParametros)));
        permitirModificacoesPreEnviar = pModificarPropriedades;

        infoConsumo = pConexao.getInformacoesConsumo();
        parametros = pParametros;

        System.out.println("Informações de consumo obtidas, preparando para conectar");
        if (infoConsumo == null) {
            throw new UnsupportedOperationException("a anotação " + InfoConsumoRestService.class.getSimpleName() + " não foi encontrada em " + fabricaIntegracao.toString());
        }
        configurarPropriedadesPadrao();

        consumoWS = new ConsumoWSExecucao() {
            @Override
            public synchronized void run() {

                try {

                    String urlCompleta = pConexao.getUrlServidor() + pConexao.getCaminhoServico(pParametros);
                    RespostaWebServiceSimples respostWS = UtilSBCoreClienteRest.getRespostaRest(
                            urlCompleta, fabricaIntegracao.getTipoRequisicao(), postarInformacoes, CABECALHO, corpoRequisicao);
                    if (respostWS != null) {
                        resposta = respostWS.getResposta();
                        codigoResposta = respostWS.getCodigoResposta();
                        erroMsg = getFabIntegracao().getMensagemAmigavel(respostWS.getRespostaErro());
                    } else {

                    }

                } finally {
                    finalizou = true;
                }
            }

        };

        if (!permitirModificacoesPreEnviar) {
            conectar();
        }

    }

    @Override
    public final synchronized void conectar() {
        if (!consumoWS.finalizou) {
            consumoWS.start();
        }
    }

    @Override
    public String getRespostaTexto() {

        return (String) consumoWS.getResposta().getRetorno();
    }

    @Override
    public JSONObject getRespostaComoObjetoJson() {
        return consumoWS.getResposta().getJsonObj();
    }

}
