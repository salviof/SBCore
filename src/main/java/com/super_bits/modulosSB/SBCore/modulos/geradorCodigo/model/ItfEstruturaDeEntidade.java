/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.basico.FabTipoBeanSBGenerico;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public interface ItfEstruturaDeEntidade {

    void adicionarCampo(Field pCampo);

    void adicionarEnum(String enums);

    void adicionarTags(String pTag);

    List<CalculoDeEntidade> getCalculos();

    EstruturaCampo getCampoByNomeDeclarado(String pNome);

    /**
     * -> Utilize @see #adicionarCampo(Field pCampo) para adicionar um campo,
     * NAO utilize .add
     *
     * @return
     */
    List<EstruturaCampo> getCampos();

    String getDescricao();

    String getIcone();

    String getIconeDaClasse();

    List<String> getListaEnum();

    List<ListaDeEntidade> getListas();

    List<LigacaoMuitosParaMuitos> getMuitosParaMuitos();

    List<LigacaoMuitosParaUm> getMuitosParaUm();

    /**
     *
     * @return Nome da Entidade class.getSimpleName()
     */
    String getNomeEntidade();

    String getPlural();

    List<String> getTags();

    FabTipoBeanSBGenerico getTipoEntidade();

    List<LigacaoUmParaMuitos> getUmParaMuitos();

    void setCalculos(List<CalculoDeEntidade> calculos);

    void setCampos(List<EstruturaCampo> campos);

    void setDescricao(String descricao);

    void setIcone(String icone);

    void setListaEnum(List<String> listaEnum);

    void setListas(List<ListaDeEntidade> listas);

    void setMuitosParaMuitos(List<LigacaoMuitosParaMuitos> muitosParaMuitos);

    void setMuitosParaUm(List<LigacaoMuitosParaUm> muitosParaUm);

    void setNomeEntidade(String nomeEntidade);

    void setPlural(String plural);

    void setTags(List<String> tags);

    void setTipoEntidade(FabTipoBeanSBGenerico tipoEntidade);

    void setUmParaMuitos(List<LigacaoUmParaMuitos> umParaMuitos);

}
