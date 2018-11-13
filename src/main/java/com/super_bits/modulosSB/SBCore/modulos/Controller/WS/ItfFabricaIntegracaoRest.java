/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS;

import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.ConexaoClienteWebService;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

/**
 *
 * @author SalvioF
 */
public interface ItfFabricaIntegracaoRest<T> extends ItfFabricaIntegracaoRestBasico {

    public String getCaminhoServico(String... parametros);

    public default FabTipoArquivoImportacao getTipoInterpretacaoDados() {
        return FabTipoArquivoImportacao.JSON;
    }

    public String getUrlServidor();

    public default ConexaoClienteWebService getConexao(String... pParametros) {
        return new ConexaoClienteWebService(this, pParametros);
    }

    public default boolean isMetodoEnviaDados() {
        return ((getTipoRequisicao().equals(FabTipoConexaoRest.PUT)) || (getTipoRequisicao().equals(FabTipoConexaoRest.POST)));
    }

    public String getCorpoRequisicao(String... parametros);

    public default T getObjeto(String... parametros) {

        throw new UnsupportedOperationException("Ainda não implementado...");

    }

    public default String getMensagemAmigavel(String pErro) {

        return pErro;

    }

}
