/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.chavesPublicasePrivadas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBcoreModulos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sfurbino
 */
public class RepositorioChavePublicaPrivada {

    public static String getIdentificacaoChave(String pChave) {
        return String.valueOf(pChave.hashCode());
    }

    public static String getIndentificadorParDeChaves(Map<String, String> pParPublicoPrivado) {
        String texto = pParPublicoPrivado.keySet().iterator().next() + pParPublicoPrivado.values().iterator().next();
        int codigo = texto.hashCode();
        return String.valueOf(codigo);
    }

    public static String getChavePublicaByHash(String pHash) {
        UTilSBCoreInputs.getStringByArquivoLocal(pHash);
        String diretorio = getCaminhoBaseRepositorio();
        File arquivo = new File(diretorio + "/" + pHash);
        if (!arquivo.exists()) {
            throw new UnsupportedOperationException("Arquivo do hash não encontrado");
        }
        String stringwrap = UTilSBCoreInputs.getStringByArquivoLocal(arquivo.getAbsolutePath());
        int indexInicio = stringwrap.indexOf(MARCADOR_INICIO_CHAVE_PUBLIC) + MARCADOR_INICIO_CHAVE_PUBLIC.length();
        int indexFinal = stringwrap.indexOf(MARCADOR_FINAL_CHAVE_PUBLIC);
        String chavePublica = stringwrap.substring(indexInicio, indexFinal);
        return chavePublica;
    }

    public static Map<String, String> geParDeChavesPubPrivada(String pHash) {
        try {
            String diretorio = getCaminhoBaseRepositorio();
            File arquivo = new File(diretorio + "/" + pHash);
            if (!arquivo.exists()) {
                throw new UnsupportedOperationException("Arquivo do hash não encontrado");
            }
            String stringwrap = UTilSBCoreInputs.getStringByArquivoLocal(arquivo.getAbsolutePath());
            int indexInicio = stringwrap.indexOf(MARCADOR_INICIO_CHAVE_PUBLIC) + MARCADOR_INICIO_CHAVE_PUBLIC.length();
            int indexFinal = stringwrap.indexOf(MARCADOR_FINAL_CHAVE_PUBLIC);
            Map<String, String> mapa = new HashMap<>();

            String chavePublica = stringwrap.substring(indexInicio, indexFinal);

            int indexInicioPrivada = stringwrap.indexOf(MARCADOR_INICIO_CHAVE_PRIVADA) + MARCADOR_INICIO_CHAVE_PRIVADA.length();
            int indexFinalPricada = stringwrap.indexOf(MARCADOR_FINAL_CHAVE_PRIVADA);
            String chavePrivada = stringwrap.substring(indexInicioPrivada, indexFinalPricada);
            mapa.put(chavePublica, chavePrivada);
            return mapa;
        } catch (Throwable t) {
            return null;
        }

    }

    private static String getCaminhoBaseRepositorio() {
        String diretorioRelativo = "/repChavePubPrivada";
        if (SBCore.isEmModoProducao()) {
            return SBCore.getCentralDeArquivos().getEndrLocalResourcesObjeto() + diretorioRelativo;
        } else {
            return UtilSBcoreModulos.DIRETORIO_ARQUIVOS_CONFIG_TESTE + "/" + SBCore.getGrupoProjeto() + diretorioRelativo;
        }

    }
    public final static String MARCADOR_INICIO_CHAVE_PRIVADA = "\n-----BEGIN RSA PRIVATE KEY-----\n";
    public final static String MARCADOR_FINAL_CHAVE_PRIVADA = "\n-----END RSA PRIVATE KEY-----\n";
    public final static String MARCADOR_INICIO_CHAVE_PUBLIC = "\n-----BEGIN RSA PUBLIC KEY-----\n";
    public final static String MARCADOR_FINAL_CHAVE_PUBLIC = "\n-----END RSA PUBLIC KEY-----\n";

    public static boolean persistirChavePublica(Map<String, String> pParPublicoPrivado) {
        String diretorio = getCaminhoBaseRepositorio();
        File diretorioRepostiorio = new File(diretorio);
        if (!diretorioRepostiorio.isDirectory()) {
            diretorioRepostiorio.mkdirs();
        }
        String identificacao = getIndentificadorParDeChaves(pParPublicoPrivado);
        String arquivoArmazenamento = diretorio + "/" + identificacao;
        String conteudoArquivo = MARCADOR_INICIO_CHAVE_PUBLIC + pParPublicoPrivado.keySet().iterator().next()
                + MARCADOR_FINAL_CHAVE_PUBLIC
                + MARCADOR_INICIO_CHAVE_PRIVADA
                + pParPublicoPrivado.values().iterator().next()
                + MARCADOR_FINAL_CHAVE_PRIVADA;

        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(arquivoArmazenamento), "UTF-8"));
            try {
                out.write(conteudoArquivo);
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
