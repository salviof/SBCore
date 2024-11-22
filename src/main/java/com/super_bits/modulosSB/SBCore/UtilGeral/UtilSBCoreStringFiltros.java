/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import static com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador.isNuloOuEmbranco;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.coletivojava.fw.utilCoreBase.UtilSBCoreStringFiltrosSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringFiltros extends UtilSBCoreStringFiltrosSimples {

    public static String getStringSemNumeros(String pTexto) {
        if (pTexto == null) {
            return null;
        }
        return pTexto.replaceAll("[0-9]", "");
    }

    public static String removerCaracteres(final String pCaracteres, Integer... indices) {
        StringBuilder strb = new StringBuilder();
        List<Integer> letrasExcluidas = Lists.newArrayList(indices);
        for (int i = 0; i < pCaracteres.length(); i++) {
            if (!letrasExcluidas.contains(i)) {
                strb.append(pCaracteres.charAt(i));
            }
        }
        return strb.toString();
    }

    public static String getNomeReduzido(String pNome) {
        if (pNome == null) {
            return null;
        }
        String nome = pNome;
        String nomeCurto = "";
        nome = nome.replace("-", " ");
        nome = nome.replace(".", " ");
        String[] nomeReduzidoSplit = nome.split(" ");
        int indice = 0;
        int indicefinal = nomeReduzidoSplit.length - 1;
        for (String parte : nomeReduzidoSplit) {
            // Tamanho máximo da parte é 15
            if (!parte.isEmpty()) {
                if (parte.length() > 15) {
                    parte = parte.substring(0, parte.length());
                }
                // enquanto o total não deu 15 letras
                if (nomeCurto.length() < 15) {
                    //se for uma abreviação do meio pega só a primeira letra
                    if (indice != 0 && indice != indicefinal) {
                        nomeCurto = nomeCurto + " " + parte.substring(0, 1);
                    } else {
                        nomeCurto = nomeCurto + " " + parte;
                    }
                }
            }
            indice++;
        }

        return nomeCurto;
    }

    public static String getNuloEmEmpty(String pString) {
        if (pString == null) {
            return "";
        } else {
            return pString;
        }
    }

    public static String getLTRIMRemoveEspacosAesquerda(String pParametro) {
        if (pParametro == null) {
            return null;
        }
        return pParametro.replaceAll("^\\s+", "");
    }

    public static String getRTRIMRemoveEspacosAesquerda(String pParametro) {
        if (pParametro == null) {
            return null;
        }
        return pParametro.replaceAll("\\s+$", "");

    }

    public static String getTRIMRemoveEspacos(String pParametro) {
        if (pParametro == null) {
            return null;
        }
        return pParametro.replaceAll("\\s", "");

    }

    /**
     *
     * Utiliza .replaceAll com regex para substituir um espaço por algum
     * caracter específico ########## ATENÇÃO, ESTE MÉTODO RETIRA AS QUEBRAS DE
     * LINHA
     *
     * @param pString
     * @param pParametro
     * @return
     */
    public static String substituirEspacosPorCaracter(String pString, String pParametro) {
        return pString.replaceAll("\\s", pParametro);
    }

    /**
     *
     *
     * Formata string com LPad Exemplo texto [exemplo] tamanho [10] digito [-]
     * vira: [---exemplo]
     *
     * @param pValor String que será formatada
     * @param pCasas número de casas
     * @param pDigito digito Digito que ocupará os espacos
     *
     *
     * @return Retorna a string formatada
     * @Autor Alline Bazile
     */
    public static String getLpad(String pValor, int pCasas, String pDigito) {
        try {

            /*String resultado = String.format("%" + pCasas + "d", Integer.parseInt(pValor));
             resultado = resultado.replace(" ", digito);
             return resultado;
             */
            pValor = String.format("%" + pCasas + "s", pValor).replace(" ", pDigito);

            return pValor;

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao formatar Lpad", e);
            return pValor;
        }
    }

    /**
     *
     * Formata string com RPad Exmplo texto [exemplo] tamanho [10] digito [-]
     * vira: [exemplo---]
     *
     * @param pValor String que será formatada
     * @param pCasas x número de casas
     * @param pDigito - digito Digito que ocupará os espacos
     *
     *
     * @return Retorna a string formatada
     * @Autor Alline Bazile
     */
    public static String getRpad(String pValor, int pCasas, String pDigito) {
        try {

            /*String resultado = String.format("%" + pCasas + "d", Integer.parseInt(pValor));
             resultado = resultado.replace(" ", digito);
             return resultado;
             */
            String result
                    = String
                            // First right pad the string
                            // with space up to length L
                            .format("%" + (-pCasas) + "s", pValor)
                            // Then replace all the spaces
                            // with the given character ch
                            .replace(' ', pDigito.charAt(0));

            // Return the resultant string
            return result;

        } catch (Exception e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao formatar Lpad", e);
            return pValor;
        }
    }

    /**
     *
     * Quebra a String adicionando /n a cada X caracteres, sem quebrar palavras
     * ao meio
     *
     * @param pString
     * @param caracteresMaximos
     * @return
     */
    public static String quebrarStringEmLinhas(String pString, int caracteresMaximos) {
        try {
            if (pString == null) {
                return "";
            }
            if (pString.length() < caracteresMaximos) {
                return pString;
            }
            return WordUtils.wrap(pString, caracteresMaximos);
        } catch (Throwable t) {
            return pString;
        }
    }

    /**
     *
     * Coleta as primeiras letras de uma String utilizando substring
     *
     * @param pString
     * @param pNumeroDeCasas
     * @return
     */
    public static String getPrimeirasXLetrasDaString(String pString, int pNumeroDeCasas) {
        try {
            if (pString == null || pString.isEmpty()) {
                return pString;
            }
            if (pString.length() < pNumeroDeCasas) {
                pNumeroDeCasas = pString.length();
            }
            return pString.substring(0, pNumeroDeCasas);

        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo primeiras letras da string" + pString, e);
        }
        return null;
    }

    /**
     *
     *
     * Retira as letras e retorna apenas os numeros na string
     *
     * @param pString String completa (com numeros e letras)
     * @return string Contendo apenas as numeros
     */
    public static String getNumericosDaString(String pString) {
        if (pString == null) {
            return null;
        }
        return pString.replaceAll("\\D*", ""); //To numeric digits only
    }

    /**
     *
     *
     * Retira as letras e caracteres epeciais e retorna apenas os numeros na
     * string
     *
     * @param pString String completa (com numeros e letras)
     * @return string Contendo apenas as numeros
     */
    public static String filtrarApenasNumeros(String pString) {
        if (pString == null) {
            return null;
        }
        return getNumericosDaString(removeCaracteresEspeciaisEspacosETracos(pString));
    }

    public static String filtrarApenasLetra(String pString) {
        return pString.replaceAll("\\d", "");

    }

    /**
     *
     * @param pMascara
     * @return Transforma uma mascara de campo formato Java, para mascara de
     * campo formato JQuery
     */
    public static String getMascaraJavaMaskParaJQueryMask(String pMascara) {
        if (pMascara != null) {

            return pMascara.replace('#', '9').replace('U', 'a').replace('?', '*').replace('L', 'a');
        } else {
            return "";
        }
    }

    public static String removeEspacamentoDuplo(String param) {
        if (isNuloOuEmbranco(param)) {
            return param;
        }
        //  "\\s"= espaço, "+"= um espaço ou mais
        return param.replaceAll("\\s+", " ");

    }

    public static String removeEspacamentoDuploEUltimoEspacaomento(String param) {
        String pStringEspacamentoCorrigido = removeEspacamentoDuplo(param);
        if (isNuloOuEmbranco(param)) {
            return param;
        }
        if (pStringEspacamentoCorrigido.endsWith(" ")) {
            return pStringEspacamentoCorrigido.substring(0, pStringEspacamentoCorrigido.length() - 1);
        } else {
            return pStringEspacamentoCorrigido;
        }
    }

    public static String removeCaracteresEspeciaisEspacosETracos(String param) {
        param = Normalizer.normalize(param, Normalizer.Form.NFD);
        param = param.replaceAll("[^\\p{ASCII}]", "");
        param = param.replace(" ", "");
        param = param.replace("(", "");
        param = param.replace(")", "");
        param = param.replace("-", "");
        param = param.replace(":", "");
        return param;

    }

    public static String removeCaracteresEspeciaisEspacosTracosEPontos(String param) {
        param = removeCaracteresEspeciais(param);
        param = param.replace(" ", "").replace(".", "_").replace("-", "").replace(":", "");

        return param;

    }

    /**
     *
     * Percorre cada caracter, extrai o char criando uma nova string.
     *
     * é útil quando você precisa retirar tudo o que não for caracter da string
     *
     *
     * @param s
     * @return
     */
    public static String limparCacteresEstranhosDaStringComNumeros(String s) {

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int cr = Integer.getInteger(Character.toString(c));
            sb.append(cr);
        }
        return sb.toString();

    }

    public static String inverteString(String pString) {

        char auxiliar[] = pString.toCharArray();

        pString = "";

        int i = auxiliar.length - 1;

        for (int j = i; j >= 0; j--) {

            pString += auxiliar[j];

        }

        return pString;

    }

    public static String substituirUsandoInicioFimTrechoPesquisado(String pTextoOriginal, String inicioTrechoPesquisado, String pFinalTrechoPesquisado, String pconteudoSubstituto) {
        //String line = "${env1}sojods${env2}${env3}";
        final String regex = "(" + inicioTrechoPesquisado + ".*?" + pFinalTrechoPesquisado + ")";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(pTextoOriginal);

        // The substituted value will be contained in the result variable
        final String result = matcher.replaceAll(pconteudoSubstituto);

        return result;
    }

    public static String substituirUsandoRegexPersonalizado(final String pTextoOriginal, String pRegex, String pconteudoSubstituto) {
        //String line = "${env1}sojods${env2}${env3}";

        final Pattern pattern = Pattern.compile(pRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(pTextoOriginal);

        // The substituted value will be contained in the result variable
        final String result = matcher.replaceAll(pconteudoSubstituto);
        int letrasAnterior = pTextoOriginal.length();
        int letrasFim = result.length();
        System.out.println("Diferenca = " + (letrasAnterior - letrasFim));
        //  System.out.println("Substitution result: " + result);
        return result;
    }

    public static String substituirUsandoLiteralPersonalizado(final String pTextoOriginal, String pLiteral, String pconteudoSubstituto) {
        //String line = "${env1}sojods${env2}${env3}";

        final Pattern pattern = Pattern.compile(pLiteral, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.LITERAL);
        final Matcher matcher = pattern.matcher(pTextoOriginal);

        // The substituted value will be contained in the result variable
        final String result = matcher.replaceAll(pconteudoSubstituto);
        int letrasAnterior = pTextoOriginal.length();
        int letrasFim = result.length();
        System.out.println("Diferenca = " + (letrasAnterior - letrasFim));
        //  System.out.println("Substitution result: " + result);
        return result;
    }
}
