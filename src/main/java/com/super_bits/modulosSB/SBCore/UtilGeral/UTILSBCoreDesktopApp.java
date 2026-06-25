/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

    public static ItfRespostaComunicacao exibirDialogo(ComoDialogo pDialogo) {
        List<ItfRespostaComunicacao> respostas = pDialogo.getRepostasPossiveis();

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mensagem
        JLabel lblMensagem = new JLabel("<html>" + pDialogo.getMensagem() + "</html>");
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        painel.add(lblMensagem, BorderLayout.CENTER);

        // Painel de botões dinâmicos
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));

        AtomicReference<ItfRespostaComunicacao> respostaEscolhida = new AtomicReference<>(null);
        JDialog dialog = new JDialog((Frame) null, pDialogo.getAssunto(), true);

        for (ItfRespostaComunicacao resposta : respostas) {
            ComoTipoRespostaComunicacao tipo = resposta.getTipoResposta();

            // Monta label com ícone FontAwesome (Unicode) + nome
            String icone = UtilCRCDesktopFontWasome.toUnicode(tipo.getIcone(), "");  // ex: "\uf00c" para fa-check
            String label = icone + "  " + resposta.getNome();

            JButton botao = new JButton(label);
            botao.setFont(new Font("FontAwesome", Font.PLAIN, 13)); // requer FontAwesome carregado
            botao.setForeground(Color.WHITE);
            botao.setBackground(hexParaColor(tipo.getCor())); // ex: "#28a745"
            botao.setOpaque(true);
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            botao.addActionListener(e -> {
                respostaEscolhida.set(resposta);
                dialog.dispose();
            });

            painelBotoes.add(botao);
        }

        painel.add(painelBotoes, BorderLayout.SOUTH);

        dialog.setContentPane(painel);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setMinimumSize(new Dimension(400, 150));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true); // bloqueia até dispose()

        return respostaEscolhida.get(); // null se fechou sem escolher
    }

    private static Color hexParaColor(String hex) {
        try {
            return Color.decode(hex);
        } catch (Exception e) {
            return Color.GRAY;
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
