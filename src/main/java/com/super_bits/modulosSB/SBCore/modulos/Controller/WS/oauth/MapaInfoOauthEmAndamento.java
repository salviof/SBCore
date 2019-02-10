/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.WS.oauth;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreListas;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.WS.ItfFabricaIntegracaoRestOAuth;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SalvioF
 */
public class MapaInfoOauthEmAndamento {

    private final static Map<String, Oath2Conexao> AUTENTICADORES_REGISTRADOS = new HashMap<>();
    private final static Map<String, Class> API_POR_CHAVE_PUBLICA = new HashMap<>();

    private static String getIdentificacaoAPIUsuario(String codigoApi) {
        return codigoApi + SBCore.getUsuarioLogado().getEmail();
    }

    private static String getIdentificacaoAPISistema(String codigoApi) {
        return codigoApi + SBCore.getGrupoProjeto();
    }

    public static void registrarAutenticador(Oath2Conexao pAutenticador, String codigoApi) {
        switch (pAutenticador.getTipoCliente()) {
            case USUARIO:
                AUTENTICADORES_REGISTRADOS.put(getIdentificacaoAPIUsuario(codigoApi), pAutenticador);
                break;
            case SISTEMA:
                AUTENTICADORES_REGISTRADOS.put(getIdentificacaoAPISistema(codigoApi), pAutenticador);
                break;
            default:
                throw new AssertionError(pAutenticador.getTipoCliente().name());
        }

    }

    public static void registrarAutenticador(Oath2Conexao pAutenticador, ItfFabricaIntegracaoRestOAuth api) {
        registrarAutenticador(pAutenticador, api.getClass().getSimpleName());

    }

    public static Oath2Conexao getAutenticadorUsuarioLogado(ItfFabricaIntegracaoRestOAuth api) {
        return AUTENTICADORES_REGISTRADOS.get(getIdentificacaoAPIUsuario(api.getClass().getSimpleName()));
    }

    public static Oath2Conexao getAutenticadorSistemaAtual(ItfFabricaIntegracaoRestOAuth api) {
        return AUTENTICADORES_REGISTRADOS.get(getIdentificacaoAPISistema(api.getClass().getSimpleName()));
    }

    public static Oath2Conexao getAutenticadorSistemaAtual(String api) {
        return AUTENTICADORES_REGISTRADOS.get(getIdentificacaoAPISistema(api));
    }

    public static Oath2Conexao getAutenticadorUsuarioLogado(String api) {
        return AUTENTICADORES_REGISTRADOS.get(getIdentificacaoAPIUsuario(api));
    }

    public static void printConexoesAtivas() {
        System.out.println("Atenticadores Registrados:");
        System.out.println(UtilSBCoreListas.getValoresSeparadosPorPontoVirgula(Lists.newArrayList(AUTENTICADORES_REGISTRADOS.keySet())));
        System.out.println("Api por Chace pública:");
        System.out.println(UtilSBCoreListas.getValoresSeparadosPorPontoVirgula(Lists.newArrayList(API_POR_CHAVE_PUBLICA.keySet())));

    }
}
