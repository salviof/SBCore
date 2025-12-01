/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Conjunto de Utilitáros para manipulação de arquivos Texto
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public abstract class UtilCRCArquivoTexto {

    public static boolean criarSeArquivoSeNaoExistir(String pCaminhoArquivo) throws IOException {
        try {
            File arquivo = new File(pCaminhoArquivo);

            if (!arquivo.exists()) {

                File pasta = new File(arquivo.getParent());
                pasta.mkdirs();

                arquivo.createNewFile();
                return true;

            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando criar:" + pCaminhoArquivo, t);
            return false;
        }

    }

    /**
     *
     * Cria , ou substitui o arquivo, e adiciona o conteudo
     *
     * @param pCaminhoArquivo Caminho do arquivo
     * @param pLinha Conteúdo da linha que será escrita
     * @return Verdadeiro -> suecesso da operação, Falso falha na operação
     *
     */
    public synchronized static boolean escreverEmArquivoSubstituindoArqAnterior(String pCaminhoArquivo, String pLinha) {

        try {
            criarSeArquivoSeNaoExistir(pCaminhoArquivo);
            FileWriter f = new FileWriter(pCaminhoArquivo);

            f.write(pLinha);
            f.close();

            return true;
        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro esrevendo no arquivo", e);
            System.out.println("errp" + e.getMessage());
            return false;
        }
    }

    /**
     *
     * Cria uma nova linha no arquivo, e escreve o conteúdo possui tratamento
     * para criação do arquivo caso não exista
     *
     * @param pCaminhoArquivo Caminho do arquivo
     * @param pLinha Conteúdo da linha que será escrita
     * @return Verdadeiro -> suecesso da operação, Falso falha na operação
     *
     */
    public synchronized static boolean escreverEmArquivo(String pCaminhoArquivo, String pLinha) {
        try {
            criarSeArquivoSeNaoExistir(pCaminhoArquivo);
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, pLinha, ex);
            return false;
        }
        System.out.println("Escrevendo em arquivo" + pCaminhoArquivo);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pCaminhoArquivo, true)))) {
            out.println(pLinha);
            out.close();
            return true;
        } catch (IOException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro esrevendo no arquivo", e);
            System.out.println("errp" + e.getMessage());
            return false;
        }
    }

    /**
     *
     * Cria uma nova linha e escreve a String no arquivo
     *
     * @param linha linha adicionada
     * @param pArquivo Caminho do arquivo
     * @return
     */
    public static synchronized boolean printLnNoArquivo(String linha, String pArquivo) {

        try {
            criarSeArquivoSeNaoExistir(pArquivo);
        } catch (IOException ex) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(pArquivo, true));
            output.println(linha);
            output.flush();
            output.close();
            return true;

        } catch (IOException ex) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

    }

    /**
     *
     * @param pArquivo
     * @param pLinhas
     * @return
     */
    public static synchronized boolean escreveLinhasNoArquivo(String pArquivo, List<String> pLinhas) {

        try {
            criarSeArquivoSeNaoExistir(pArquivo);
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(pArquivo, true));
            for (String linha : pLinhas) {
                output.println(linha);
            }

            output.flush();
            output.close();
            return true;

        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

    }

    /**
     *
     * @param pArquivo
     * @param pLinhas
     * @return
     */
    public static synchronized boolean escreveLinhasEmNovoArquivo(String pArquivo, List<String> pLinhas) {

        try {
            criarSeArquivoSeNaoExistir(pArquivo);
            limparArquivoTexto(pArquivo);
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(pArquivo, false));
            for (String linha : pLinhas) {
                output.println(linha);
            }

            output.flush();
            output.close();
            return true;

        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando arquivo" + pArquivo, ex);
            return false;
        }

    }

    /**
     *
     * Limpa o contaúdo do arquivo
     *
     * @param pCaminhoArquivo caminho absoluto do arquivo
     * @return verdadeiro se executar com sucesso, falso se falhar na operação
     */
    public synchronized static boolean limparArquivoTexto(String pCaminhoArquivo) {
        try {
            criarSeArquivoSeNaoExistir(pCaminhoArquivo);
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando Arquivo" + pCaminhoArquivo, ex);
            return false;
        }

        File arquivo = new File(pCaminhoArquivo);
        PrintWriter writer;
        try {
            writer = new PrintWriter(arquivo);
            writer.print("");
            writer.close();

            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }

    }

    /**
     * Substitui um texto específico dentro de um arquivo.
     *
     * @param caminhoArquivo Caminho completo do arquivo
     * @param textoOriginal Texto que será substituído
     * @param novoTexto Novo texto para colocar no lugar
     * @return true se a substituição foi feita com sucesso
     */
    public static boolean substituirTextoNoArquivo(String caminhoArquivo, String textoOriginal, String novoTexto) {
        Path caminho = Paths.get(caminhoArquivo);

        try {
            // Lê todo o conteúdo do arquivo como uma String
            String conteudo = new String(Files.readAllBytes(caminho), StandardCharsets.UTF_8);

            // Substitui o texto
            String conteudoModificado = conteudo.replace(textoOriginal, novoTexto);

            // Escreve de volta no mesmo arquivo
            Files.write(caminho, conteudoModificado.getBytes(StandardCharsets.UTF_8));

            return true;

        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
            return false;
        }
    }

    public static boolean isTemPalavraNoArquivo(String pArquivo, String pPalavra) {
        String linhaAtual;
        File arquivo = new File(pArquivo);
        try {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader operador = new BufferedReader(leitor);
            while (operador.ready()) {
                linhaAtual = operador.readLine().toLowerCase();
                if (linhaAtual.contains(pPalavra.toLowerCase())) {
                    return true;

                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilCRCArquivoTexto.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(UtilCRCArquivoTexto.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static Properties getPropriedadesNoArquivo(String pCaminhoArquivo) {
        Properties proppriedades = new Properties();
        InputStream arqPropriedadesSource = UTilSBCoreInputs.getStreamByLocalFile(pCaminhoArquivo);
        if (arqPropriedadesSource == null) {
            throw new UnsupportedOperationException("Arquivo " + pCaminhoArquivo + " não encontrado na pasta resources");
        }
        try {
            proppriedades.load(arqPropriedadesSource);
            return proppriedades;

        } catch (IOException ex) {
            Logger.getLogger(UtilCRCArquivoTexto.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Properties getPropriedadesNoArquivoRessource(String pArquivo, Class pClasseReferencia) {

        Properties proppriedadesBasicas = new Properties();

        InputStream stream = pClasseReferencia.getClassLoader().getResourceAsStream(pArquivo);
        try {
            proppriedadesBasicas.load(stream);
            return proppriedadesBasicas;

        } catch (IOException ex) {
            Logger.getLogger(UtilCRCArquivoTexto.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param pArquivo Caminho do arquivo
     * @param novaLinha Conteúdo que será escrito
     * @param linha Linha onde a String será escrita
     * @return
     */
    public static boolean substituirEstaLinha(String pArquivo, String novaLinha, int linha) {
        try {
            List<String> linhas = UTilSBCoreInputs.getStringsByArquivoLocal(pArquivo);
            linhas.set(linha - 1, novaLinha);
            UtilCRCArquivoTexto.escreveLinhasEmNovoArquivo(pArquivo, linhas);
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro substituindo trecho de arqiovp", t);
            return false;
        }
    }

}
