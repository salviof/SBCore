/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringComparador {

    private static final LongestCommonSubsequence CALCULO_SIMILAR = new LongestCommonSubsequence();

    public static boolean isParecido(String pReferencia, String pParametro) {
        return isParecido(pReferencia, pParametro, 80);
    }

    public static boolean isBastanteParecido(String pReferencia, String pParametro) {
        return isParecido(pReferencia, pParametro, 90);
    }

    public static boolean isParecido(String pReferencia, String pParametro, int indiceIgualdade) {
        int maximo = pParametro.length();
        int score = CALCULO_SIMILAR.apply(pReferencia.toLowerCase(), pParametro.toLowerCase());
        double valor = (score * 100) / maximo;
        return valor >= indiceIgualdade;
    }

    public static boolean isParecido(ItfBeanSimples pReferencia, List<? extends ItfCaminhoCampo> pCampos, String pParametro, boolean pParametroNumerico) {
        if (pReferencia == null) {
            return false;
        }
        ConcurrentLinkedQueue<ItfCaminhoCampo> campos = new ConcurrentLinkedQueue<>();
        pCampos.parallelStream().forEach(
                coluna -> {
                    try {
                        ItfCampoInstanciado campoinstanciado = pReferencia.getCampoInstanciadoByNomeOuAnotacao(coluna.getCaminhoSemNomeClasse());
                        if (pParametroNumerico) {
                            switch (campoinstanciado.getTipoPrimitivoDoValor()) {
                                case LETRAS:
                                    if (campoinstanciado.isTemMascara()) {
                                        if (campoinstanciado.getMascara().contains("#")) {
                                            if (campoinstanciado.contem(pParametro, 100)) {
                                                campos.add(coluna);
                                                return;
                                            }
                                        }
                                    } else {

                                    }
                                case INTEIRO:
                                case NUMERO_LONGO:

                                    break;
                                case DATAS:
                                    break;
                                case BOOLEAN:
                                    break;
                                case DECIMAL:
                                    break;
                                case ENTIDADE:
                                    break;
                                case OUTROS_OBJETOS:
                                    break;
                                default:
                                    throw new AssertionError(campoinstanciado.getTipoPrimitivoDoValor().name());

                            }
                            if (campoinstanciado.contem(pParametro, 100)) {
                                campos.add(coluna);
                            }
                        } else {
                            if (campoinstanciado.contem(pParametro, 100)) {
                                campos.add(coluna);
                            }

                        }
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Verificando se o objeto é parecido", t);
                    }
                }
        );
        return !campos.isEmpty();

    }

}
