/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Salvio
 */
public class UTILSBCoreDesktopApp {

    static class EnviaMensagemThread implements Runnable {

        ItfMensagem mensagem;

        public EnviaMensagemThread(ItfMensagem pMensagem) {
            mensagem = pMensagem;
        }

        @Override
        public void run() {
            showMessageStopProcess(mensagem);
        }

    }

    /**
     *
     * Envia uma mensagem utilizando Jpanel em uma Thread Separada, para
     * permitir o fluxo continuo da execução do sistema.
     *
     * @param pMensagem
     */
    public static void showMessageNovaThread(ItfMensagem pMensagem) {
        new Thread(new EnviaMensagemThread(pMensagem)).start();
    }

    /**
     *
     * Envia a mensagem, e continua a execução do código depois do OK
     *
     * @param pMensagem
     */
    public static void showMessageStopProcess(ItfMensagem pMensagem) {

        String titulo = pMensagem.getTipoDeMensagem().toString() + " para " + pMensagem.getTipoDestinatario();

        int tipoDeMensagem = JOptionPane.INFORMATION_MESSAGE;
        switch (pMensagem.getTipoDeMensagem()) {
            case AVISO:
                tipoDeMensagem = JOptionPane.INFORMATION_MESSAGE;
                break;
            case ALERTA:
                tipoDeMensagem = JOptionPane.WARNING_MESSAGE;
                break;
            case ERRO:
                tipoDeMensagem = JOptionPane.ERROR_MESSAGE;
                break;
            case ERRO_FATAL:
                tipoDeMensagem = JOptionPane.ERROR_MESSAGE;
                break;
            default:
                throw new AssertionError(pMensagem.getTipoDeMensagem().name());

        }
        JTextArea textArea = new JTextArea(pMensagem.getMenssagem());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setBackground(null); // deixa o fundo transparente como o JOptionPane
        textArea.setBorder(null);
        textArea.setColumns(80);
        textArea.setRows(10);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        //JScrollPane scrollDetalhes = getScrollPaneDetalhes(pMensagem.getTipoDeMensagem() + pMensagem.getTipoDestinatario(), , titulo, titulo)
        JOptionPane.showMessageDialog(null, textArea, titulo, tipoDeMensagem);

    }

    public static JScrollPane getScrollPaneDetalhes(String pTitulo, String pDescricao, String causaInicial, String stacktrace) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 12));

        StyledDocument doc = textPane.getStyledDocument();

        // Estilo normal
        SimpleAttributeSet normal = new SimpleAttributeSet();
        StyleConstants.setBold(normal, false);

        // Estilo em negrito
        SimpleAttributeSet bold = new SimpleAttributeSet();
        StyleConstants.setBold(bold, true);

        try {
            doc.insertString(doc.getLength(), "Mensagem de erro na linha ", normal);
            doc.insertString(doc.getLength(), "42", bold);
            doc.insertString(doc.getLength(), " do arquivo ", normal);
            doc.insertString(doc.getLength(), "MinhaClasse.java", bold);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        return scrollPane;
    }

}
