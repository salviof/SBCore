/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author salvio
 */
public class UtilSBCoreStringTelefone {

    /**
     * Converte um número de telefone para o formato internacional E.164 com
     * código do Brasil (+55).
     *
     * <p>
     * O método remove caracteres especiais do número informado e garante que
     * ele esteja no formato correto.</p>
     *
     * <h3>Regras de conversão:</h3>
     * <ul>
     * <li>Se o telefone tiver menos de 10 caracteres, retorna
     * {@code null}.</li>
     * <li>Remove caracteres especiais, como traços, parênteses e espaços.</li>
     * <li>Se já estiver no formato internacional (começar com "+") e não for
     * brasileiro, apenas retorna o número limpo.</li>
     * <li>Se o telefone começar com "55" e tiver mais de 11 caracteres,
     * adiciona o "+" no início.</li>
     * <li>Se não começar com "+55", adiciona o código do Brasil
     * automaticamente.</li>
     * <li>Se o número brasileiro tiver apenas 8 dígitos após o DDD, adiciona o
     * nono dígito "9".</li>
     * </ul>
     *
     * <h3>Exemplos de entrada e saída:</h3>
     * <table border="1">
     * <tr><th>Entrada</th><th>Saída</th></tr>
     * <tr><td>"(31) 98765-4321"</td><td>"+5531987654321"</td></tr>
     * <tr><td>"+5541998765432"</td><td>"+5541998765432"</td></tr>
     * <tr><td>"4198765432"</td><td>"+554198765432"</td></tr>
     * <tr><td>"+15551234567"</td><td>"+15551234567"</td></tr>
     * <tr><td>"12345"</td><td>{@code null}</td></tr>
     * </table>
     *
     * @param pTelefone O número de telefone a ser convertido.
     * @return O número no formato internacional, ou {@code null} se a entrada
     * for inválida.
     */
    public static String gerarCeluarInternacional(final String pTelefone) {
        if (pTelefone.length() < 10) {
            return null;
        }

        String telefoneFormatoInternacional = UtilSBCoreStringFiltros.removeCaracteresEspeciais(pTelefone);
        telefoneFormatoInternacional = telefoneFormatoInternacional.replace("_", "");
        if (pTelefone.startsWith("+")) {
            //se já estiver em formato internacional, mas não for formato Brasileiro, apenas retorna o número sem os caracteres especiais
            if (!pTelefone.startsWith("+55")) {
                return telefoneFormatoInternacional;
            }
        }

        if (telefoneFormatoInternacional.startsWith("55") && telefoneFormatoInternacional.length() > 11) {
            telefoneFormatoInternacional = "+55" + telefoneFormatoInternacional.substring(2, telefoneFormatoInternacional.length());
        }

        if (!telefoneFormatoInternacional.startsWith("+55")) {
            telefoneFormatoInternacional = "+55" + telefoneFormatoInternacional;
        }

        if (telefoneFormatoInternacional.length() == 13) {
            telefoneFormatoInternacional = telefoneFormatoInternacional.substring(0, 5) + "9"
                    + telefoneFormatoInternacional.substring(5, telefoneFormatoInternacional.length());
        }

        if (telefoneFormatoInternacional.length() >= 13) {
            return telefoneFormatoInternacional;

        }
        return null;
    }

    public static String gerarCeluarWhatasapp(String pTelefone) {
        String telefone = gerarCeluarInternacional(pTelefone);
        //+5531988888888"
        if (telefone == null) {
            return null;
        }
        if (telefone.length() == 13) {
            return telefone.replace("+", "");
        }
        if (telefone.length() == 14) {
            if (telefone.charAt(5) == '9') {
                telefone = UtilSBCoreStringFiltros.removerCaracteres(telefone, 5);
                return telefone.replace("+", "");
            }
        }
        return pTelefone.replace("+", "");

    }
    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^\\+(?:[1-9]\\d{0,2})(?:9\\d{8}|(?<!\\+55)\\d{6,14})$");

    /**
     * Verifica se um número de telefone no formato internacional é um celular
     * válido.
     *
     * <p>
     * O número deve estar no formato E.164, ou seja, começar com um "+" seguido
     * do código do país e do número de telefone.
     * </p>
     *
     * <h3>Regras de validação:</h3>
     * <ul>
     * <li>O código do país deve ter entre 1 e 3 dígitos.</li>
     * <li>Para números brasileiros (+55), o número deve ter exatamente 9
     * dígitos e começar com '9'.</li>
     * <li>Para outros países, o número deve ter entre 6 e 14 dígitos.</li>
     * </ul>
     *
     * <h3>Exemplos de números válidos:</h3>
     * <ul>
     * <li>+15551234567 (EUA)</li>
     * <li>+447911123456 (Reino Unido)</li>
     * <li>+5531998765432 (Brasil - celular válido)</li>
     * </ul>
     *
     * <h3>Exemplos de números inválidos:</h3>
     * <ul>
     * <li>+553174178550 (Brasil - telefone fixo, não tem 9 dígitos)</li>
     * <li>+331234567 (França - muito curto)</li>
     * <li>5511998765432 (Faltando o '+')</li>
     * <li>+abc123456789 (Caracteres inválidos)</li>
     * </ul>
     *
     * @param pNumero O número de telefone em formato internacional (ex:
     * "+5511998765432").
     * @return {@code true} se for um número de celular válido, {@code false}
     * caso contrário.
     */
    public static boolean isUmCelular(String pNumero) {

        if (pNumero == null || pNumero.isEmpty()) {
            return false;
        }
        String numero = gerarCeluarInternacional(pNumero);
        return MOBILE_PHONE_PATTERN.matcher(numero).matches();

    }

}
