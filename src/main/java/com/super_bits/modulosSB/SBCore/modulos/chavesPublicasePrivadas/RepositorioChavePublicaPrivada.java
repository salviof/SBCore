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
 *
 *
 * @author sfurbino
 */
public class RepositorioChavePublicaPrivada {

    public static String getIdentificacaoChavePublica(String pChavePublica) {
        return String.valueOf(pChavePublica.hashCode());
    }

    public static String gerarPathPersistenciaIdentificadorChave(String pCodigoChavePrivada) {
        String diretorio = getCaminhoBaseRepositorio() + "/" + pCodigoChavePrivada;
        return diretorio;
    }

    public static String getIdentificacaoChavePublica(Map<String, String> pParPublicoPrivado) {
        if (pParPublicoPrivado.size() != 1) {
            throw new UnsupportedOperationException("Este método suporta a identificação de um único par de chaves, vários foram enviados");
        }
        return getIdentificacaoChavePublica(pParPublicoPrivado.keySet().iterator().next());
    }

    public static String getChavePublicaByHash(String pHash) {

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

    public static String getConteudoParDeChavesArquivoPem(String pHash) {
        try {
            String diretorio = getCaminhoBaseRepositorio();
            File arquivo = new File(diretorio + "/" + pHash);
            System.out.println("EM" + arquivo.getPath());
            if (!arquivo.exists()) {
                throw new UnsupportedOperationException("Arquivo do hash não encontrado");
            }
            String stringwrap = UTilSBCoreInputs.getStringByArquivoLocal(arquivo.getAbsolutePath());
            return stringwrap;
        } catch (Throwable t) {
            return null;
        }
    }

    public static Map<String, String> getParDeChavesPubPrivadaByHash(String pHash) {
        try {
            String diretorio = getCaminhoBaseRepositorio();
            File arquivo = new File(diretorio + "/" + pHash);
            System.out.println("EM" + arquivo.getPath());
            if (!arquivo.exists()) {
                throw new UnsupportedOperationException("Arquivo do hash não encontrado");
            }
            String stringwrap = UTilSBCoreInputs.getStringByArquivoLocal(arquivo.getAbsolutePath());
            int indexInicio = stringwrap.indexOf(MARCADOR_INICIO_CHAVE_PUBLIC) + MARCADOR_INICIO_CHAVE_PUBLIC.length();
            int indexFinal = stringwrap.indexOf(MARCADOR_FINAL_CHAVE_PUBLIC);
            Map<String, String> mapa = new HashMap<>();

            String chavePublica = stringwrap.substring(indexInicio, indexFinal);
            System.out.println("hash chave pública =");
            System.out.println(chavePublica.hashCode());
            if (!pHash.equals(String.valueOf(chavePublica.hashCode()))) {
                throw new UnsupportedOperationException("falha definindo hash de chave pública");
            }
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

    public static boolean persistirChavePublicaPrivada(Map<String, String> pParPublicoPrivado) {
        String diretorio = getCaminhoBaseRepositorio();
        File diretorioRepostiorio = new File(diretorio);
        if (!diretorioRepostiorio.isDirectory()) {
            diretorioRepostiorio.mkdirs();
        }
        String identificacao = RepositorioChavePublicaPrivada.getIdentificacaoChavePublica(pParPublicoPrivado);
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
