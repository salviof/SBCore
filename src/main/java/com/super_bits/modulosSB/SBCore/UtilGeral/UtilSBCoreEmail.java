/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import com.super_bits.modulosSB.SBCore.modulos.email.FabConfigModuloEmailService;
import com.super_bits.modulosSB.SBCore.modulos.email.ItfServidordisparoEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Função: Lidar com envio de Email FabConfigModuloEmailService
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 */
public abstract class UtilSBCoreEmail {

    private static ConfigEmailServersProjeto configuracao;

    private static final String TEMPLATE_GERAL_EMAIL = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "<meta name=\"format-detection\" content=\"telephone=yes\">\n"
            + "<meta name=\"format-detection\" content=\"date=yes\">\n"
            + "<meta name=\"format-detection\" content=\"address=yes\">\n"
            + "<meta name=\"format-detection\" content=\"email=yes\">\n"
            + "</head>\n"
            + "<body>\n"
            + "\n"
            + "[CONTEUDO]"
            + "\n"
            + "</body>\n"
            + "</html>";

    public static String gerarConteudoEmailNormatizado(String pConteudo) {
        if (!pConteudo.contains("<html>")) {

            String conteudo = TEMPLATE_GERAL_EMAIL.replace("[CONTEUDO]", pConteudo);
            //conteudo = StringEscapeUtils.escapeHtml4(pConteudo);
            return conteudo;
        } else {
            return pConteudo;
        }
    }

    public static void configurarEmailPorVariavelDeAmbiente() {
        ConfigModulo configuracao = SBCore.getConfigModulo(FabConfigModuloEmailService.class);
        String hostname = configuracao.getPropriedade(FabConfigModuloEmailService.EMAIL_SERVICE_HOSTNAME);
        String usuario = configuracao.getPropriedade(FabConfigModuloEmailService.EMAIL_SERVICE_USUARIO);
        String senha = configuracao.getPropriedade(FabConfigModuloEmailService.EMAIL_SERVICE_SENHA);
        String nomeRemetente = configuracao.getPropriedade(FabConfigModuloEmailService.EMAIL_NOME_REMETENTE);
        String emailREmetente = configuracao.getPropriedade(FabConfigModuloEmailService.EMAIL_EMAIL_REMETENTE);
        configurar(new ConfigEmailServersProjeto(hostname, emailREmetente, nomeRemetente, usuario, senha));

    }

    public static void verificarConfiguracao() {
        if (configuracao == null) {

            throw new UnsupportedOperationException("O servidor padrão para email transacional não foi enviado, execute " + UtilSBCoreEmail.class.getSimpleName() + ".configurar(configuracao); ao iniciar o projeto ");
        }
    }

    public static void configurar(ConfigEmailServersProjeto pConfig) {
        configuracao = pConfig;
    }

    public static boolean enviarPorServidorPadraoV2(
            ItfUsuario pDestinatario,
            String pMensagem,
            String pAssunto) {
        verificarConfiguracao();
        return enviarEmail(configuracao.getServidorPrincipalTransacional().getEnderecoServidor(),
                configuracao.getFromEmail(),
                configuracao.getServidorPrincipalTransacional().getFromNome(),
                configuracao.getServidorPrincipalTransacional().getUsuarioSMTP(),
                configuracao.getServidorPrincipalTransacional().getSenhaSMTP(), pMensagem,
                pDestinatario.getEmail(), pAssunto);
    }

    public static boolean enviarPorServidorPadraoV2(
            String pDestinatario,
            String pMensagem,
            String pAssunto) {
        verificarConfiguracao();
        return enviarEmail(configuracao.getServidorPrincipalTransacional().getEnderecoServidor(),
                configuracao.getFromEmail(),
                configuracao.getServidorPrincipalTransacional().getFromNome(),
                configuracao.getServidorPrincipalTransacional().getUsuarioSMTP(),
                configuracao.getServidorPrincipalTransacional().getSenhaSMTP(), pMensagem,
                pDestinatario, pAssunto);
    }

    public static boolean enviaporSSL(final String servidor, final String pFromEmail, final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        return enviarEmail(servidor, pFromEmail, null, pUsuario, pSenha, mensagem, para, pAssunto);
    }

    private static boolean enviarEmail(String pEnderecoServidor, final String pFromEmail, final String pNomeFrom, final String pUsuario,
            final String pSenha, String pMensagem, String para, String pAssunto) {
        boolean envioucomsucesso = false;

        try {

            HtmlEmail email = new HtmlEmail();
            //email.setCharset("text/html; charset=utf-8");
            email.setCharset("UTF-8");
            email.setAuthentication(pUsuario, pSenha);
            email.setHostName(pEnderecoServidor); // o servidor SMTP para envio do e-mail

            if (pFromEmail.isEmpty()) {

                throw new UnsupportedOperationException("Você precisa especificar os contatos que irão receber o documento");

            }

            email.addTo(para);

            if (email.getToAddresses().isEmpty()) {
                throw new Throwable("Nenhum destinatário foi adicionando no campo para");
            }
            if (pNomeFrom == null) {
                email.setFrom(pFromEmail);
            } else {
                email.setFrom(pFromEmail, pNomeFrom);
            }

            email.setSubject(pAssunto); //Assunto
            //pConteudo = "<html> <head> <link rel=\"stylesheet\" "
            //      + "href=\"https://fonts.googleapis.com/css?family=Montserrat:200,600,amp;lang=br\" /> </head> \n"
            //    + pConteudo + "\n </html>";

            email.setMsg(UtilSBCoreEmail.gerarConteudoEmailNormatizado(pMensagem));
            //email.setMsg(pConteudo);
            //email.setSmtpPort(465);

            email.setSmtpPort(587);
            email.setSSL(true);
            email.setTLS(true);

            //email.attach(); // adiciona o anexo à mensagem
            String resp = email.send();// envia o e-mail
            System.out.println(resp);

            return true;

        } catch (EmailException t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando email" + t.getMessage(), t);
            return false;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro enviando email" + t.getMessage(), t);
            return false;
        }

    }

    /**
     *
     * @param pUsuario email do usuário
     * @param pSenha senha do usuário
     * @param mensagem mensagem enviada
     * @param para email do destinatario
     * @param pAssunto Assunto do e-mail
     * @return
     */
    public static boolean enviaGmailporSSL(final String pUsuario, final String pSenha, String mensagem, String para, String pAssunto) {

        return enviarEmail("smtp.gmail.com", pUsuario, null, pUsuario, pSenha, mensagem, para, pAssunto);

    }

    public static ItfServidordisparoEmail getServidorDisparoEmail() {
        verificarConfiguracao();
        return (ItfServidordisparoEmail) configuracao.getServidorPrincipalTransacional();
    }

}
