/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.coletivojava.fw.utilCoreBase.UtilCRCDiretoriosSimples;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Funcções basicas relativas ao Shell, para funções mais avançadas importe o
 * SuperBits Shell Command
 *
 * @author desenvolvedor
 */
public abstract class UtilCRCShellBasico {

    public static String executeCommand(String... pComando) {
        return executeCommand(true, false, pComando);
    }

    public static String executeCommand(boolean detectarDiretorioExecucao, boolean pConsiderarErrorStreamErro, String... pComando) {
        System.out.println("Executando comando" + Arrays.toString(pComando));
        StringBuilder output = new StringBuilder();
        StringBuilder outputErro = new StringBuilder();

        Process processo;
        try {

            if (detectarDiretorioExecucao && pComando[0].split("/").length > 1) {
                if (pComando.length > 1) {
                    throw new UnsupportedOperationException("A execução detectando diretorio de execução só é permitida para chamada de uma arquivo de Script específica, portando não suporta multiplas linhas de comando, utilize  /caminho/do/arquivo.sh parameto, ou baixe o pocote  br.org.coletivoJava.fw.modulos shellcommands");
                }
                String caminhoParaScript = pComando[0];
                String diretorio = UtilCRCDiretoriosSimples.getDiretorioArquivo(caminhoParaScript) + "/";
                // 1) Parse genérico do comando recebido
                List<String> partes = new ArrayList<>();
                Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(caminhoParaScript);

                while (m.find()) {
                    partes.add(m.group(1).replace("\"", ""));
                }

                if (partes.isEmpty()) {
                    throw new IllegalArgumentException("Comando vazio");
                }

                // 2) Monta bash -c exec "$@"
                List<String> cmd = new ArrayList<>();
                cmd.add("/bin/bash");
                cmd.add("-c");
                cmd.add("exec \"$@\"");

                cmd.add("_");

                // 3) Tudo depois vira $0 $1 $2 ...
                cmd.addAll(partes);

                ProcessBuilder pb = new ProcessBuilder(cmd);

                // 4) Garante environment completo
                Map<String, String> env = pb.environment();
                env.clear();
                env.putAll(System.getenv());

                if (diretorio != null) {
                    pb.directory(new File(diretorio));
                }

                processo = pb.start();

            } else {
                processo = Runtime.getRuntime().exec(pComando);
            }
            processo.waitFor();
            int valorSaida = processo.exitValue();
            System.out.println("Valor saída=" + valorSaida);
            BufferedReader out
                    = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            BufferedReader err
                    = new BufferedReader(new InputStreamReader(processo.getErrorStream()));

            boolean temSaida = false;

            String linha;
            while ((linha = out.readLine()) != null) {
                output.append(linha).append("\n");
                temSaida = true;
            }

            boolean temErro = false;
            while ((linha = err.readLine()) != null) {
                output.append(linha).append("\n");
                outputErro.append(linha).append("\n");

                temErro = true;

            }

            if (valorSaida != 0) {

                if (temErro) {
                    throw new UnsupportedOperationException("ERRO executando script:[" + Arrays.toString(pComando) + "] ->" + outputErro.toString());
                } else {
                    if (temSaida) {
                        throw new UnsupportedOperationException("ERRO executando script:[" + Arrays.toString(pComando) + "] ->" + output.toString());
                    }
                    throw new UnsupportedOperationException("ERRO executando script:[" + Arrays.toString(pComando) + "]");
                }

            } else {
                if (temErro && pConsiderarErrorStreamErro) {
                    throw new UnsupportedOperationException("ERRO executando script:[" + Arrays.toString(pComando) + "] ->" + outputErro.toString());
                }
            }

        } catch (IOException | InterruptedException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, Arrays.toString(pComando), e);
        }

        return output.toString();

    }

    public static void abrirScriptEmConsole(String... pScripteParametros) {

        File arquivo = new File(pScripteParametros[0]);
        String comandoCompleto = "";
        int i = 0;
        for (String linha : pScripteParametros) {
            if (i > 0) {
                comandoCompleto += " " + linha;
            } else {
                comandoCompleto = linha;
            }
            i++;
        }
        if (!arquivo.exists()) {
            throw new UnsupportedOperationException("O script " + arquivo + " não foi encontrado ");
        }
        System.out.println("Executando " + comandoCompleto + " em console");
        String command[] = {"/bin/sh", "-c",
            "gnome-terminal -e \"" + comandoCompleto + "\""};
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(command);

        } catch (Exception ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando executar" + pScripteParametros, ex);
        }

    }

}
