/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.objetoNativo.ObjetoNativoComoItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import mtfn.MetaphonePtBr;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.apache.commons.text.similarity.LongestCommonSubsequenceDistance;
import org.coletivojava.fw.api.objetoNativo.ObjetoNativoCoreDoSistema;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringComparador {

    private static final LongestCommonSubsequence CALCULO_SIMILAR = new LongestCommonSubsequence();
    private static final LongestCommonSubsequenceDistance CALCULO_DIFERENCA = new LongestCommonSubsequenceDistance();

    public static boolean isParecido(String pReferencia, String pParametro) {
        int score = 80;
        if ((pReferencia.length() - pParametro.length()) > 10) {
            score = 95;
        }
        return isParecido(pReferencia, pParametro, score);
    }

    public static double JaroWinkler(String pUm, String pDois) {
        double score = StringUtils.getJaroWinklerDistance(pUm, pDois);
        //  System.out.println(pUm+"-"+pDois+"="+score);
        return score;
    }

    public static boolean isBastanteParecido(String pReferencia, String pParametro) {
        return isParecido(pReferencia, pParametro, 90);
    }

    public static boolean isParecido(String pReferencia, String pParametro, int indiceIgualdade) {
        int maximo = pParametro.length();
        int score = CALCULO_SIMILAR.apply(pReferencia.toLowerCase(), pParametro.toLowerCase());
        double valor = (score * 100) / maximo + 1;

        return valor > 80;
    }

    public static boolean isBastanteParecidoFoneticamente(String pReferencia, String pParamentro) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pReferencia) || UtilSBCoreStringValidador.isNuloOuEmbranco(pParamentro)) {
            return false;
        } else {
            return new MetaphonePtBr(pReferencia).toString().contains(new MetaphonePtBr(pParamentro).toString());
        }
    }

    public static boolean isParecidoFoneticamente(String pReferencia, String pParamentro) {
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pReferencia) || UtilSBCoreStringValidador.isNuloOuEmbranco(pParamentro)) {
            return false;
        } else {
            return new MetaphonePtBr(pReferencia).toString().contains(new MetaphonePtBr(pParamentro).toString());
        }
    }

    private static final Map<String, String> substituicoes = new HashMap<>();

    static {
        // Substituições de caracteres individuais
        substituicoes.put("ç", "c");
        substituicoes.put("y", "i");
        substituicoes.put("ã", "a");
        substituicoes.put("õ", "o");
        substituicoes.put("á", "a");
        substituicoes.put("à", "a");
        substituicoes.put("â", "a");
        substituicoes.put("é", "e");
        substituicoes.put("ê", "e");
        substituicoes.put("í", "i");
        substituicoes.put("ó", "o");
        substituicoes.put("ô", "o");
        substituicoes.put("ú", "u");
        substituicoes.put("ü", "u");

        // Substituições de combinações de letras
        substituicoes.put("ão", "ao");
        substituicoes.put("õe", "oe");
        substituicoes.put("ss", "s");
        substituicoes.put("rr", "r");
        substituicoes.put("ch", "x");
        substituicoes.put("lh", "li");
        substituicoes.put("nh", "ni");
    }

    public static String normalizarTexto(String texto) {
        texto = texto.toLowerCase();

        // Substituir combinações de letras primeiro
        for (Map.Entry<String, String> entry : substituicoes.entrySet()) {
            texto = texto.replace(entry.getKey(), entry.getValue());
        }

        return texto;
    }

    public static boolean isParecido(ComoEntidadeSimples pReferencia, List<? extends ItfCaminhoCampo> pCampos, String pParametro, boolean pParametroNumerico) {
        if (pReferencia == null) {
            return false;
        }
        ConcurrentLinkedQueue<ItfCaminhoCampo> camposComMatch = new ConcurrentLinkedQueue<>();
        pCampos.parallelStream().forEach(
                coluna -> {
                    try {
                        ItfCampoInstanciado campoinstanciado = pReferencia.getCampoInstanciadoByNomeOuAnotacao(coluna.getCaminhoSemNomeClasse());

                        if (campoinstanciado == null) {
                            if (pReferencia instanceof ObjetoNativoCoreDoSistema) {
                                ObjetoNativoComoItemSimples nativoComoItemSimples = new ObjetoNativoComoItemSimples(pReferencia);
                                campoinstanciado = nativoComoItemSimples.getCampoInstanciadoByNomeOuAnotacao(coluna.getCaminhoSemNomeClasse());
                            } else {
                                throw new UnsupportedOperationException("não foi possível encontrar o campo: [" + coluna.getCaminhoSemNomeClasse() + "] para pesquisa emm campos do objeto: [" + pReferencia + "]");
                            }
                        }
                        if (!pParametroNumerico) {
                            switch (campoinstanciado.getTipoPrimitivoDoValor()) {
                                case LETRAS:
                                    if (campoinstanciado.isTemMascara()) {
                                        if (campoinstanciado.getMascara().contains("#")) {
                                            try {
                                                if (campoinstanciado.getValor().toString().contains(pParametro)) {
                                                    camposComMatch.add(coluna);
                                                    break;
                                                }
                                            } catch (NullPointerException n) {
                                                break;
                                            }
                                        }
                                    } else {
                                        if (campoinstanciado.contem(pParametro, 100)) {
                                            camposComMatch.add(coluna);
                                        }
                                    }
                                    break;
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
                                    if (campoinstanciado.contem(pParametro, 100)) {
                                        camposComMatch.add(coluna);
                                    }

                            }

                        } else {
                            if (campoinstanciado.getValor() != null) {

                                if (String.valueOf(campoinstanciado.getValor()).contains(pParametro)) {
                                    camposComMatch.add(coluna);
                                }
                            }

                        }
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Verificando se o objeto é parecido", t);
                    }
                }
        );
        return !camposComMatch.isEmpty();

    }

}
