/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivoTexto;
import java.util.Date;

/**
 *
 * @author salvioF
 */
public class ErroSBArquivoTextoEmHomologacao extends InfoErroSBComAcoes {

    @Override
    public void alertarResponsavel() {
        if (!SBCore.isEmModoProducao()) {
            UtilCRCArquivoTexto.printLnNoArquivo(getMensagemGenericaFormatada(UtilCRCDataHora.getDataHoraString(new Date(), UtilCRCDataHora.FORMATO_TEMPO.HORA_USUARIO)), SBCore.getCaminhoGrupoProjetoSource() + "/log_" + tipoErro + ".txt");
        }
    }

    @Override
    public void lancarExcecao() {
        throw new UnsupportedOperationException(getErroGerado()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancarPane() {
        if (!SBCore.isEmModoProducao()) {
            UtilCRCArquivoTexto.printLnNoArquivo(getMensagemGenericaFormatada(UtilCRCDataHora.getDataHoraString(new Date(), UtilCRCDataHora.FORMATO_TEMPO.HORA_USUARIO)), SBCore.getCaminhoGrupoProjetoSource() + "/log_" + tipoErro + ".txt");
        }
    }

    @Override
    public void registrarErro() {
        if (!SBCore.isEmModoProducao()) {
            UtilCRCArquivoTexto.printLnNoArquivo(getMensagemGenericaFormatada(UtilCRCDataHora.getDataHoraString(new Date(), UtilCRCDataHora.FORMATO_TEMPO.HORA_USUARIO)), SBCore.getCaminhoGrupoProjetoSource() + "/log_" + tipoErro + ".txt");
        }
    }

}
