/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringsMaiuculoMinusculo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;

/**
 *
 * @author salvio
 */
public enum FabNomeClassePadraoAtributoEntidade implements ComoDefinicaoNomeClasseCRC {

    CLASSE_CAMPO_ENTIDADE_VALIDACAO,
    CLASSE_CAMPO_ENTIDADE_VALOR_LOGICO,
    CLASSE_CAMPO_ENTIDADE_VALOR_LOGICO_IMPLMENTACAO_PADRAO_ERP,
    CLASSE_CAMPO_ENTIDADE_VALIDACAO_IMPLEMENTACAO_PADRAO_ERP;

    public String getNomeClassseAtributoEntidade(ItfEstruturaCampoEntidade pEstruturaCampo) {
        FabTipoCodigoDeEntidade tipo = FabTipoCodigoDeEntidade.getTipoProjeto(pEstruturaCampo.getEstruturaPai());
        String slugExtencaoModulo = "";
        switch (tipo) {
            case EXTENCAO_MODULO_ERP:
                slugExtencaoModulo = "ExtErp" + UtilCRCStringFiltros.getPrimeirasXLetrasDaString(SBCore.getGrupoProjeto(), 5);
                break;
            case PROJETO_AUTONOMO:
                break;
            case MODULO_ERP:
                break;
            default:
                throw new AssertionError();
        }
        switch (this) {

            case CLASSE_CAMPO_ENTIDADE_VALIDACAO:
                return "Validacao" + slugExtencaoModulo + pEstruturaCampo.getEstruturaPai().getNome()
                        + UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pEstruturaCampo.getNomeDeclarado());

            case CLASSE_CAMPO_ENTIDADE_VALOR_LOGICO:
                return "ValorLogico" + slugExtencaoModulo + pEstruturaCampo.getEstruturaPai().getNome()
                        + UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pEstruturaCampo.getNomeDeclarado());
            case CLASSE_CAMPO_ENTIDADE_VALOR_LOGICO_IMPLMENTACAO_PADRAO_ERP:
                return "ValorLogico" + pEstruturaCampo.getEstruturaPai().getNome()
                        + UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pEstruturaCampo.getNomeDeclarado());

            case CLASSE_CAMPO_ENTIDADE_VALIDACAO_IMPLEMENTACAO_PADRAO_ERP:
                return "Validacao" + pEstruturaCampo.getEstruturaPai().getNome()
                        + UtilCRCStringsMaiuculoMinusculo.getPrimeiraLetraMaiusculo(pEstruturaCampo.getNomeDeclarado());

            default:
                throw new AssertionError();
        }
    }

}
