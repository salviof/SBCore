/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.formulario;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroRequisicaoInstanciado;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfFormularioSimples extends Serializable {

    /**
     * Por padrão o titulo da pagina é a descrição da ação vinculada a pagina
     *
     * @return Titulo da Pagina
     */
    public String getTitulo();

    /**
     *
     * Descreve a utilidade da pagina.
     *
     * Ajuda o usuário a encontrar esta pagina, e a entender o que ela faz
     *
     * @return descrição da pagina
     */
    public String getDescricao();

}
