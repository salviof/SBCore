/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.formulario;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author salvio
 */
public interface ItfFormularioEntidade<T> extends ItfDominioEntidade<T>, ItfFormularioAcao {

    /**
     *
     * Caso o managed bean da pagina possua uma ação de listagem configurada em
     * seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoListarRegistro} />
     *
     * @return Ação listar registro da pagina de gestão
     */
    public ItfAcaoFormularioEntidade getAcaoListarRegistros();

    /**
     *
     * Caso o managed bean da pagina possua uma ação para formulário de novo
     * registro configurada em seu constructor esta ação pode ser obtida por
     * aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoNovoRegistro} />
     *
     * @return Ação novo registro
     */
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro();

    /**
     * Caso o managed bean da pagina possua uma ação para salvar alterações
     * configurada em seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao:
     * <SBComp:botaoDeAcaoPagina acao=#{PgClientes.acaoSalvarAlteracoes} />
     *
     *
     *
     * @return Ação salvar alterações da entidade
     */
    public ComoAcaoDoSistema getAcaoSalvarAlteracoes();

    /**
     *
     * Caso o managed bean da pagina possua uma lista de ações de registro pré
     * configuradas em seu constructor esta ação pode ser obtida por aqui
     *
     * ex utilizacao: <SBComp:seletorDeAcoes
     * opcoes=#{PgClientes.acaoAcoesRegistros} ../>*
     *
     * @return Ações do sistema associadas ao registro selecionado
     */
    public List<ComoAcaoDoSistema> getAcoesRegistros();

    public void setAcaoSelecionada(ComoAcaoDoSistema acaoSelecionada);

    public void setEntidadeSelecionada(T entidadeSelecionada);

    public void setEntidadesListadas(List<T> entidadesListadas);

    /**
     *
     * Executa a ação selecionada, enviando o Item selecionado como parametro
     *
     * @param pCompradorSelecionado
     */
    public void executarAcao(T pCompradorSelecionado);

}
