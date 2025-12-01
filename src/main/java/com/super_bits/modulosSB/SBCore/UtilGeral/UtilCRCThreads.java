/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivoTexto;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class UtilCRCThreads {

    public synchronized static void salvaInfoThread(String pPastaArquivoLog, boolean milesegundos) {

        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        SimpleDateFormat dateformat = null;
        if (milesegundos) {
            dateformat = new SimpleDateFormat("dd-mm-ss-SSS");
        } else {
            dateformat = new SimpleDateFormat("dd-mm-ss");
        }

        String dia = dateformat.format(new Date());

        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
        Map<String, Integer> totais = new HashMap<>();
        Map<String, Integer> totaisMetodos = new HashMap<>();
        Map<String, Integer> totaisWait = new HashMap<>();
        System.out.println("TOTAL THREADS:" + Thread.activeCount());
        System.out.println("AllStack" + threads.size());
        System.out.println("@@@@TESTE:::");
        String caminhoArquivoLog = pPastaArquivoLog + "/" + dia + ".log";
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Quantidade total Threads:" + Thread.activeCount());

        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");

        for (Thread tr : threads.keySet()) {

            UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "DESCRIÇÃO DA THREAD ATIVA:");
            UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, tr.getClass().getSimpleName());
            Integer quantidade = totais.get(tr.getClass().getSimpleName());

            if (quantidade == null) {
                totais.put(tr.getClass().getSimpleName(), 1);
            } else {
                totais.put(tr.getClass().getSimpleName(), quantidade + 1);
            }

            for (StackTraceElement elemento : threads.get(tr)) {
                UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, elemento.getClassName() + "-" + elemento.getFileName() + "-" + elemento.getMethodName());
                String nomeMetodo = elemento.getClassName() + "." + elemento.getMethodName();
                UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, ".");

                Integer quantidadeMetodo = totaisMetodos.get(nomeMetodo);

                if (nomeMetodo.contains("wait")) {
                    String caminhoCompletoWait = "";
                    for (StackTraceElement elemento2 : tr.getStackTrace()) {
                        caminhoCompletoWait = caminhoCompletoWait + "." + elemento2.getMethodName();
                    }
                    Integer quantidadeWait = totaisWait.get(caminhoCompletoWait);
                    if (quantidadeWait == null) {
                        totaisWait.put(caminhoCompletoWait, 1);
                    } else {
                        totaisWait.put(caminhoCompletoWait, quantidadeWait + 1);
                    }
                }

                if (quantidadeMetodo == null) {
                    totaisMetodos.put(nomeMetodo, 1);
                } else {
                    totaisMetodos.put(nomeMetodo, quantidadeMetodo + 1);
                }

            }

        }

        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_____________________Classes ATIVAS_________________________________________");

        for (String nomeTr
                : totais.keySet()) {
            UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeTr + ": " + totais.get(nomeTr));
        }

        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_______________________METODOS wait:_______________________________________");

        for (String nomeWait : totaisWait.keySet()) {
            UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeWait + ": " + totaisWait.get(nomeWait));
        }

        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_______________________METODOS ATIVOS:_______________________________________");

        for (String nomeMet
                : totaisMetodos.keySet()) {

            UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeMet + ": " + totaisMetodos.get(nomeMet));
        }

        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Memoria livre: " + format.format(freeMemory / 1024));
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Memória Alocada:" + format.format(allocatedMemory / 1024));
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Máximo memória:" + format.format(maxMemory / 1024) + " ");
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "total Memmória livre " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "TOTAL THREADS:" + Thread.activeCount());
        UtilCRCArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "AllStack" + threads.size());
    }

}
