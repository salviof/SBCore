/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fonteDados.FabTipoSelecaoRegistro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAtributoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreValidacao {

    public static boolean validaSintaxeENulo(ItfAtributoObjetoSB pCampo, Object pValor) {
        if (pCampo.isUmValorLivre()) {
            if (pCampo.isObrigatorio()) {
                if (pValor instanceof String) {
                    return !UtilSBCoreStringValidador.isNuloOuEmbranco(pValor.toString());
                }
            }
        }

        switch (pCampo.getFabricaTipoAtributo()) {
            case ARQUIVO_DE_ENTIDADE:
                if (pCampo.isObrigatorio()) {
                    return pValor != null;
                }
            case CNPJ:

                if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor.toString())) {
                    return !pCampo.isObrigatorio();
                }
                return UtilSBCoreValidadorGoverno.validaCNPJ(pValor.toString());
            case CPF:
                if (UtilSBCoreStringValidador.isNuloOuEmbranco(pValor.toString())) {
                    return !pCampo.isObrigatorio();
                }
                return UtilSBCoreValidadorGoverno.validaCPF(pValor.toString());
            default:
                return true;
        }

    }

    public static String getPrimeiraInconsistenciaDeValidacao(ItfBeanSimples pObjeto) {
        Optional<ItfCampoInstanciado> cpEncontrado = pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo())).findFirst();
        if (cpEncontrado.isPresent()) {
            return gerarMensagensValidacao(cpEncontrado.get(), pObjeto, pObjeto.getId() == 0, true).get(0);
        } else {
            return null;
        }

    }

    public static List<String> getValidacoes(ItfBeanSimples pObjeto) {
        List<String> lista = new ArrayList<>();

        pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo()))
                .forEach(cp -> lista.add(gerarMensagensValidacao(cp, pObjeto, pObjeto.getId() == 0, true).get(0)));
        return lista;

    }

    public static boolean isTodosCamposValidadados(ItfBeanSimples pObjeto) {
        try {
            return (pObjeto.getCamposInstanciados().stream().filter(cp -> (!cp.validarCampo())).findFirst().isPresent());
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isValorUnico(ItfAtributoObjetoSB pCampo, Object pValor) {
        try {
            return SBCore.getCentralDados().selecaoRegistros(null, null,
                    "from " + pCampo.getNomeClasseOrigemAtributo() + " where " + pCampo.getNome() + "= ?0", 1, MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pCampo.getNomeClasseOrigemAtributo()), FabTipoSelecaoRegistro.NOMECURTO, pValor).isEmpty();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando valor unico em " + pCampo.getNomeClasseOrigemAtributo() + "." + pCampo.getNome(), t);
            return true;
        }
    }

    public static List<String> gerarMensagensValidacao(ItfAtributoObjetoSB pCampo, Object pValor, boolean pUmaNovaEntidade, boolean imporMensagemPadrao) {
        List<String> resp = new ArrayList<>();
        try {

            switch (pCampo.getFabricaTipoAtributo()) {
                case ARQUIVO_DE_ENTIDADE:
                    if (pValor == null) {
                        resp.add("O Arquivo " + pCampo.getLabel() + " não foi encontrado");
                    }
            }
            if (!validaSintaxeENulo(pCampo, pValor)) {
                resp.add("O Valor para " + pCampo.getLabel() + " não é válido");
                return resp;
            }
            if (pCampo.isNumeral() || pCampo.isMoeda()) {
                if (pCampo.getValorMinimo() > 0) {

                    if (pValor == null || Double.parseDouble(pValor.toString()) < pCampo.getValorMinimo()) {

                        resp.add("O Mínimo permitido para " + pCampo.getLabel() + " é " + pCampo.getValorMinimo());
                    }

                }

            }
            if (pUmaNovaEntidade) {
                if (pValor != null) {
                    if (pCampo.isValorCampoUnico()) {
                        if ((pValor instanceof Integer)
                                || (pValor instanceof Long)) {
                            long valorrr = (long) pValor;
                            if (valorrr == 0) {
                                return resp;
                            }
                        }

                        if (!isValorUnico(pCampo, pValor)) {
                            resp.add("Já existe um " + pCampo.getLabel() + " com o valor " + pValor);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validando" + pCampo, t);
        }
        if (imporMensagemPadrao) {
            if (resp.isEmpty()) {
                resp.add("Valor inválido para " + pCampo.getLabel());
            }
        }
        return resp;

    }

    public static boolean validaCampoPorTipo(Object pValor, FabTipoAtributoObjeto pCampo) {
        switch (pCampo) {
            case TEXTO_SIMPLES:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_PEQUENA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_MEDIA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_GRANDE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_NOME:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_NOME_LONGO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_DESCRITIVO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case ID:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LATITUDE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case Longitude:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LC_LOGRADOURO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCCEP:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LC_BAIRRO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LC_CIDADE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case TELEFONE_FIXO_NACIONAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LC_COMPLEMENTO_E_NUMERO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case HTML:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_VALOR:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_LABEL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_CATEGORIA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CALENDARIO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case MOEDA_REAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case OBJETO_DE_UMA_LISTA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LISTA_OBJETOS_PUBLICOS:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case COR:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case EMAIL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case SITE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case URL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case RESPONSAVEL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CNPJ:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case CPF:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case INSCRICAO_ESTADUAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case INSCRIACAO_MUNICIPAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            default:
                throw new AssertionError(pCampo.name());

        }
        //throw new UnsupportedClassVersionError("Ainda Não implementado");

    }

}
