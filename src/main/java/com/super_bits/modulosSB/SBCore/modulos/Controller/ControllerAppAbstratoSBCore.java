/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.comunicacao.RespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import java.lang.reflect.Method;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 *
 *
 * Classe para extender as ações do sistema da camada Controller do modelo MVC
 *
 *
 * @author Salvio
 */
public abstract class ControllerAppAbstratoSBCore implements ItfControlerAPP {

    /**
     *
     * Instancia uma nova resposta
     *
     * @param pTipoRetorno Classe esperada para o objeto de retorno
     * @return A nova resposta instanciada
     */
    protected static ItfRespostaAcaoDoSistema getNovaResposta(Class pTipoRetorno) {
        RespostaAcaoDoSistema resp = new RespostaAcaoDoSistema(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));

        return resp;
    }

    /**
     *
     * Instancia uma nova resposta com erro inesperado
     *
     * @param pTipoRetorno Classe esparada para objeto de retorno
     * @return Uma nova resposta já com um erro inesperado adicionado 'a lista
     * de mensagem
     */
    protected static ItfRespostaAcaoDoSistema getRespostaErroInesperado(Class pTipoRetorno) {
        RespostaAcaoDoSistema resp = new RespostaAcaoDoSistema(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));
        ComoAcaoDoSistema acao = getAcaoDoMetodo();
        resp.addErro("Erro inesperado executando  " + acao.getNomeAcao() + " o suporte foi notificado, você pode nos escrever sobre isso caso queira, obrigado");
        return resp;
    }

    /**
     *
     * Instancia uma nova resposta, e caso o usuário logado não possua permissão
     * adiciona uma mensagem de erro
     *
     * @param pTipoRetorno Tipo de retorno esperado
     * @return a nova resposta
     */
    protected static ItfRespostaAcaoDoSistema getNovaRespostaComAutorizacao(Class pTipoRetorno) {
        RespostaAcaoDoSistema resp = new RespostaAcaoDoSistema(pTipoRetorno, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));
        addMensagemDeAutorizacao(resp);
        return resp;
    }

    /**
     *
     * @return RespostaAcaoDoSistema com o erro não implementado
     */
    protected static ItfRespostaAcaoDoSistema getNovaRespostaNaoImplementado() {
        RespostaAcaoDoSistema naoImplementado = new RespostaAcaoDoSistema(UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));
        return (ItfRespostaAcaoDoSistema) naoImplementado.addMensagemErroDisparaERetorna("Os algorítimos para [" + getAcaoDoMetodo().getNome() + "] do modulo [" + getAcaoDoMetodo().getModulo().getNome() + "] não foi implementado. em" + getMetodoChamado().getDeclaringClass().getSimpleName() + "." + getMetodoChamado().getName());
    }

    protected List<ItfPermissao> carregaAcessos() {
        return SBCore.getCentralPermissao().configuraPermissoes();
    }

    private static String buildNomeClasse() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String nomeMetodo = stackTraceElements[3].getMethodName();
        String nomeClasse = stackTraceElements[3].getClassName();
        String[] classeFull = nomeClasse.split("\\.");

        nomeClasse = classeFull[classeFull.length - 1];

        return nomeClasse + "." + nomeMetodo;
    }

    @Deprecated
    protected static Method getMetodoChamado() {

        int inicioPesquisaMetodo = 3;

        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTraceTodosMetodos = t.getStackTrace();

        int idfinalStacktrace = stackTraceTodosMetodos.length - 1;
        if (inicioPesquisaMetodo >= stackTraceTodosMetodos.length) {
            inicioPesquisaMetodo = stackTraceTodosMetodos.length - 1;
        }
        for (int i = inicioPesquisaMetodo; (i <= idfinalStacktrace); i++) {
            final StackTraceElement stackTrhaceMetodoAtual = stackTraceTodosMetodos[i];
            final String methodName = stackTrhaceMetodoAtual.getMethodName();
            final String className = stackTrhaceMetodoAtual.getClassName();

            Class<?> classedoMetodo;
            try {
                classedoMetodo = Class.forName(className);

                do {
                    for (final Method candidate : classedoMetodo.getDeclaredMethods()) {
                        if (candidate.getName().equals(methodName)) {

                            if (UtilSBController.possuiMetodoDeAcao(candidate)) {
                                return candidate;
                            }

                        }
                    }
                    classedoMetodo = classedoMetodo.getSuperclass();
                } while (classedoMetodo != null);
            } catch (Throwable tt) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obter ação vinculada ao Método, verifique se o método tem anotação ", tt);
            }

        }

        return null;
    }

    private ItfResposta gerarRespostaAcessoNegado(ItfResposta resp) {
        return resp.addMensagemErroDisparaERetorna("Acesso negado, solicite acesso a este recurso.");
    }

    /**
     *
     * Verifica se o parametro é nulo e adiciona uma mensagem de erro na
     * resposta caso seja.
     *
     * @param pParametro o parametro que será verificado
     * @param nomeParametro o nome que será apresentado na mensagem de erro
     * @param pResp O objeto de resposta onde a mensagem de erro será adicionada
     */
    protected static void addMensagemNulo(Object pParametro, String nomeParametro, ItfResposta pResp) {

        if (pParametro == null) {
            pResp.addErro("o parametro " + nomeParametro + " não pode ser núlo");
        }

    }

    /**
     *
     * Retorna ação que está vinculada ao metodo;
     *
     *
     *
     *
     * @return A ação que estiver anotada no metodo
     */
    protected static ComoAcaoDoSistema getAcaoDoMetodo() {
        try {
            Method metodo = getMetodoChamado();
            ComoAcaoDoSistema acao = UtilSBController.getAcaoByMetodo(metodo, true);
            if (acao == null) {
                String nomeMetodo = "NULO!!!";
                if (metodo != null) {
                    nomeMetodo = metodo.getName();
                }
                throw new UnsupportedOperationException("Não foi possível identificar a ação do método" + nomeMetodo);
            }
            return acao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo ação do sistema do método ", t);
            return null;
        }
    }

    /**
     *
     * Verifica se o usuário logado possui autorização para executar este método
     *
     * @param pResp O objeto de resposta onde a mensagem de erro será adicionada
     */
    @SuppressWarnings("null")
    protected static void addMensagemDeAutorizacao(@NotNull ItfRespostaAcaoDoSistema pResp) {
        try {
            if (pResp == null) {
                throw new UnsupportedOperationException("Tentativa de adicionar uma mensgem de autorização em uma resposta nula");
            }
            if (!pResp.getAcaoVinculada().isPrecisaPermissao()) {
                return;
            }

            ComoUsuario usuario = SBCore.getControleDeSessao().getSessaoAtual().getUsuario();

            if (usuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
                return;
            }

            Method metodo = getMetodoChamado();

            ComoAcaoDoSistema acao = UtilSBController.getAcaoByMetodo(metodo, true);
            if (acao == null) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "A ANOTAÇÃO DE AÇÃO NÃO FOI ENCONTRADA NO METODO DE AÇÃO DO SISTEMA", null);
            }
            if (!acao.isPrecisaPermissao()) {
                return;
            }

            if (!SBCore.getServicoPermissao().isAcaoPermitidaUsuario(usuario, acao)) {
                pResp.addErro(usuario.getNome() + ", você não tem acesso para realizar a ação: " + acao.getNomeAcao() + " entre em contato com o administrador");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando verificar permissao a ação do sistema", t);
        }
    }

    private static boolean isPermitido(ItfPermissao pAcesso, ComoUsuario pUsuario) {
        if (SBCore.isIgnorarPermissoes()) {
            return true;
        }
        return SBCore.getCentralPermissao().isAcaoPermitidaUsuario(pUsuario, pAcesso.getAcao());

    }

    /**
     *
     * Verifica se o usuário possui acesso ao recurso
     *
     * @param pUsuario
     * @param pAcao
     * @return
     */
    @Override
    public boolean isAcessoPermitido(ComoUsuario pUsuario, ComoAcaoDoSistema pAcao) {
        if (SBCore.isIgnorarPermissoes()) {
            return true;
        }
        if (!pAcao.isPrecisaPermissao()) {
            return true;
        } else {
            return SBCore.getCentralPermissao().isAcaoPermitidaUsuario(pUsuario, pAcao);
        }
    }

    public static boolean isAcessoPermitido(ComoAcaoDoSistema pAcao) {
        if (SBCore.isIgnorarPermissoes()) {
            return true;
        }
        if (!pAcao.isPrecisaPermissao() && !pAcao.getAcaoDeGestaoEntidade().isPrecisaPermissao()) {

            return true;
        } else {
            if (SBCore.getCentralPermissao().isAcaoPermitidaUsuario(SBCore.getUsuarioLogado(), pAcao.getAcaoDeGestaoEntidade())) {
                return SBCore.getCentralPermissao().isAcaoPermitidaUsuario(SBCore.getUsuarioLogado(), pAcao);
            } else {
                return false;
            }

        }

    }

    /**
     *
     * Verifica se este modulo possui esta ação cadastrada em algum metodo
     *
     * @param permissao
     * @return
     */
    @Override
    @Deprecated
    public boolean possuiEstaAcao(ComoAcaoDoSistema permissao) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(permissao.getNomeUnico()) != null;
    }

}
