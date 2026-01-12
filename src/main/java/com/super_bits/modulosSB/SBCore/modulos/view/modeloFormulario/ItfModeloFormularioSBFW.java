/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.modeloFormulario;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfModeloFormularioSBFW extends ComoEntidadeSimples {

    public String getAreaExtra1();

    public String getAreaExtra2();

    public String getAreaExtra3();

    public String getAreaPrincipal();

    public String getAreaSecundaria();

    public List<String> getAreas();

    public String getDescricao();

    public Long getId();

    public String getNomeModelo();

    public String getXhtmlVinculado();

    public void setAreas(List<String> areas);

    public void setDescricao(String descricao);

    public void setId(Long id);

    public void setNomeModelo(String nomeModelo);

    public void setXhtmlVinculado(String xhtmlVinculado);

}
