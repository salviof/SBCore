/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.erros.ErroDeteccaoSeparadorDecimal;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 18/03/2015
 * @version 1.0
 */
public class UtilSBCoreNumeros {

    /**
     *
     *
     *
     * @param pValor
     * @param pCasas
     * @author Alline Basile
     * @return
     */
    public static Integer getLpadZero(Integer pValor, int pCasas) {
        try {

            return Integer.parseInt(String.format("%" + pCasas + "s", pValor.toString()).replace(" ", "0"));

        } catch (Exception e) {

            return pValor;
        }

    }

    public static Integer getConcatenados(int... pNumeros) {
        StringBuilder sb = new StringBuilder(pNumeros.length);
        for (int digito : pNumeros) {
            sb.append(digito);
        }
        return Integer.getInteger(sb.toString());
    }

    public static Integer getNumeroRandomico(int pMinimo, int pMaximo) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((pMaximo) + 1) + pMinimo;

        return randomNum;

    }

    /**
     *
     * @param pValor ex: 120,50
     * @return 12050
     */
    public static String converterNumeroToStrMoedaPadraoBanco(Object pValor) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        String valor = formatter.format(pValor);
        valor = valor.replace(",", "");
        valor = valor.replace(".", "");
        return valor;
    }

    public static String converterMoeda(Object pValor) {
        if (pValor == null) {
            return " - ";
        }
        try {
            //    Currency currency = Currency.getInstance("BRL");
            //currency.getSymbol();
            String simbolo = "R$";
            DecimalFormat formato = new DecimalFormat("#,##0.00");

            return simbolo + formato.format(pValor);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro convertendo Moeda" + pValor, t);
            return pValor.toString();
        }
    }

    public static String formatarNumeroInteiro(long pNumero) {
        DecimalFormat nf = new DecimalFormat("###,###,###,###");
        return nf.format(pNumero);
    }

    public static final double doubleArredondamento(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static final char getSeparadorDecimal(String pValorDouble) throws ErroDeteccaoSeparadorDecimal {
        if (pValorDouble.length() < 2) {
            throw new ErroDeteccaoSeparadorDecimal(ErroDeteccaoSeparadorDecimal.TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL.NENHUM_SEPARADOR_DETECTADO);
        }
        char caracterSeparadorDecimal;
        if (pValorDouble.length() > 3) {
            caracterSeparadorDecimal = pValorDouble.charAt(pValorDouble.length() - 3);
            if (caracterSeparadorDecimal == '.' || caracterSeparadorDecimal == ',') {
                return caracterSeparadorDecimal;
            }
        }

        caracterSeparadorDecimal = pValorDouble.charAt(pValorDouble.length() - 2);
        if (caracterSeparadorDecimal != '.' && caracterSeparadorDecimal != ',') {
            throw new ErroDeteccaoSeparadorDecimal(ErroDeteccaoSeparadorDecimal.TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL.NENHUM_SEPARADOR_DETECTADO);
        }

        return caracterSeparadorDecimal;
    }

    public static final Double getDoublePorString(String pValorDouble) {
        try {
            if (!pValorDouble.contains(",") && !pValorDouble.contains(".")) {
                //Adicionar virgula automaticamente após 2 casas caso não envie
                //este padrão foi adotado devido a ser uma especificação comum nas integrações bancárias
            }
            if (pValorDouble.length() < 3) {

            }
            char caracterSeparadorDecimal;
            boolean separadorExplicito = true;
            try {
                caracterSeparadorDecimal = UtilSBCoreNumeros.getSeparadorDecimal(pValorDouble);
            } catch (ErroDeteccaoSeparadorDecimal ex) {
                switch (ex.getTipoErro()) {
                    case NENHUM_SEPARADOR_DETECTADO:
                        separadorExplicito = false;
                        caracterSeparadorDecimal = '.';
                        break;
                    case VALOR_NULO_OU_INVALIDO:
                        throw new ErroDeteccaoSeparadorDecimal(ErroDeteccaoSeparadorDecimal.TIPO_ERRO_DETECTOR_SEPARADOR_DECIMAL.VALOR_NULO_OU_INVALIDO, "Falha buscando double em: " + pValorDouble.toString());

                    default:
                        throw new AssertionError();
                }
            }

            String parteInteiro = "0";
            String parteDecimal = "0";
            String valorApenasNumero = UtilSBCoreStringFiltros.filtrarApenasNumeros(pValorDouble);
            boolean temIteiro = valorApenasNumero.length() > 2;
            if (!separadorExplicito) {

                if (!temIteiro) {
                    parteDecimal = pValorDouble;
                } else {
                    parteDecimal = pValorDouble.substring(pValorDouble.length() - 2, pValorDouble.length());
                    parteInteiro = pValorDouble.substring(0, pValorDouble.length() - 2);
                }

            } else {
                if (caracterSeparadorDecimal == '.' || caracterSeparadorDecimal == ',') {

                    if (!temIteiro) {
                        parteDecimal = pValorDouble;
                    } else {
                        parteDecimal = pValorDouble.substring(pValorDouble.indexOf(caracterSeparadorDecimal) + 1, pValorDouble.length());
                        parteInteiro = pValorDouble.substring(0, pValorDouble.indexOf(caracterSeparadorDecimal));

                    }

                }
            }
            parteInteiro = UtilSBCoreStringFiltros.filtrarApenasNumeros(parteInteiro);
            String nuneroFormatadoAdequadamente = parteInteiro + "." + parteDecimal;
            Double valorDouble = Double.valueOf(nuneroFormatadoAdequadamente);
            return valorDouble;
        } catch (Throwable t) {
            return null;
        }
    }
}
