/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.formulario;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfFormularioAcao extends ItfFormularioSimples {

    /**
     *
     * A area de exibição selecionada é a área que será atualizada após executar
     * uma ação atraves do
     *
     * @see ItfB_Pagina#executarAcaoSelecionada() , ou do
     * executarAcaoSelecionada(Entidade)
     *
     * @return O id da area que será atualizada automaticamente
     */
    public String getIdAreaExbicaoAcaoSelecionada();

    public void executarAcaoSelecionada();

    /**
     *
     * @return A ação selecionada no momento
     */
    public ItfAcaoDoSistema getAcaoSelecionada();

    /**
     *
     * Identificação numérica da pagina, não precisa ser único (Criado para
     * utilizar em menus)
     *
     * @return
     */
    public Long getId();

    /**
     *
     * Todo PgAlgumacoisa é vinculado a auma ação de gestão esta ação de gestão
     * determina quais ações existirão na pagina, nome da pagina e configurações
     * de segurança, este metodo retorna a ação de gestão vinculada a pagina
     *
     *
     * @return Ação de gestão vinculada a pagina
     */
    public ItfAcaoGerenciarEntidade getAcaoVinculada();

    //public Conversation getConversa();
    /**
     *
     * O xhtml da ação atual é o xhtml referente a ação do momento este xhtml é
     * diferente do
     *
     * @see ItfB_Pagina#getRecursoXHTML() , normalmente este é um xhtml filho de
     * uma ação de gestao (vinculada diretamente ao pg) e está vinculado a uma
     * ação de formulario
     *
     *
     * @return
     */
    public String getXhtmlAcaoAtual();

    /**
     *
     * Parametros de Url são parametros que podem ser configurados via chamada
     * de URL
     *
     * O fato de ser possível executar uma ação da pagina pela url informando um
     * parametro em seguida, tornou este recurso quase obsoleto, porém não
     * chegamos a conclusão sobre sua extinção, comente sobre issso em
     * wiki.superbits.org.br
     *
     * @return a lista de todos os parametros nescessários para esta pagina
     */
    public List<ItfParametroRequisicaoInstanciado> getParametrosURL();

}
