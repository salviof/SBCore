/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.formulario;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfDominioEntidade<T> {

    /**
     *
     *
     *
     * @return entidade Selecionada
     */
    public T getEntidadeSelecionada();

    /**
     *
     * @return True se Modo novo Registro
     */
    public boolean isNovoRegistro();

    /**
     *
     * @return True se pode editar
     */
    public boolean isPodeEditar();

    /**
     *
     * Lista de entidades para executar alguma ação
     *
     * @return lista de entidades disponíveis no managed bean
     */
    public List<T> getEntidadesListadas();

    /**
     *
     * @return True se o managed bean possuir formulario de visualização de
     * entidade
     */
    public boolean isTemVisualizar();

    /**
     *
     * @return Verdadeiro se o managed bean possuir alçai vinculada a formulario
     * de novo registro
     */
    public boolean isTemNovo();

    /**
     *
     * @return Verdadeiro se possui ação de formulario do tipo generico edição
     * do registro
     */
    public boolean isTemEditar();

    /**
     *
     * @return Verdadeiro se o managed ben possui formulario de controller para
     * alteração de status
     */
    public boolean isTemAlterarStatus();

    /**
     * Atualiza a lista de dados
     */
    public void listarDados();

    /**
     *
     * @return Verdadeiro se possui possibilidade de filtro por pesquisa
     */
    public boolean isTemPesquisa();

    public ItfAcaoFormularioEntidade getAcaoVisualizar();

    public ComoAcaoControllerEntidade getAcaoAlterarStatus();

    public ItfAcaoFormularioEntidade getAcaoEditar();

    /**
     *
     * @return Verdadeiro se bloequeado para edição
     */
    public boolean isSomenteLeitura();

    public void atualizarEntidadeSelecionada();
}
