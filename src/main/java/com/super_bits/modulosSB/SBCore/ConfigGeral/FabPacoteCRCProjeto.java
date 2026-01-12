/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.erp.ComoFabricaPacoteDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaDeEntidade;

/**
 *
 * @author salvio
 */
public enum FabPacoteCRCProjeto implements ComoFabricaPacoteDeEntidade {

    API_ESTRUTURA_ENTIDADE,
    IMPLEMENTACAO_ESTRUTURA_ENTIDADE,
    MODULO_ERP_API_ESTRUTURA_ENTIDADE,
    MODULO_ERP_IMPLEMENTACAO_ESTRUTURA_ENTIDADE;

    private boolean usarModuloERP() {

        switch (this) {

            case API_ESTRUTURA_ENTIDADE:

            case IMPLEMENTACAO_ESTRUTURA_ENTIDADE:
                return false;
            case MODULO_ERP_API_ESTRUTURA_ENTIDADE:

            case MODULO_ERP_IMPLEMENTACAO_ESTRUTURA_ENTIDADE:
                return true;
            default:
                throw new AssertionError();
        }

    }

    @Override
    public String getPacoteCanonicoDeEntidade(ItfEstruturaDeEntidade pEstrutura
    ) {

        if (usarModuloERP()) {
            switch (this) {
                case MODULO_ERP_API_ESTRUTURA_ENTIDADE:
                    //"org.coletivoJava.erp." + p + ".api.model." + pSubPacote.toLowerCase()
                    return "br.org.carameloCode.erp.modulo." + pEstrutura.getModuloERP().toLowerCase() + ".api.model." + pEstrutura.getNomeEntidade().toLowerCase();
                case MODULO_ERP_IMPLEMENTACAO_ESTRUTURA_ENTIDADE:
                    return "br.org.carameloCode.erp.modulo." + pEstrutura.getModuloERP().toLowerCase() + ".implemetation.model." + pEstrutura.getNomeEntidade().toLowerCase();

                case IMPLEMENTACAO_ESTRUTURA_ENTIDADE:

                case API_ESTRUTURA_ENTIDADE:
                default:
                    throw new AssertionError();
            }

        } else {
            switch (this) {
                case API_ESTRUTURA_ENTIDADE:
                    return "br.org.coletivoJava.fw.projetos." + SBCore.getGrupoProjeto() + ".api.model." + pEstrutura.getNomeEntidade().toLowerCase();
                case IMPLEMENTACAO_ESTRUTURA_ENTIDADE:
                    return "br.org.coletivoJava.fw.projetos." + SBCore.getGrupoProjeto() + ".implemetation.model." + pEstrutura.getNomeEntidade().toLowerCase();
                case MODULO_ERP_API_ESTRUTURA_ENTIDADE:
                case MODULO_ERP_IMPLEMENTACAO_ESTRUTURA_ENTIDADE:
                default:
                    throw new AssertionError();
            }
        }

    }

}
