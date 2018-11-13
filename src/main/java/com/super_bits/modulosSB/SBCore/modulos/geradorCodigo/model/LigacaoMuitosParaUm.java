/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.basico.FabTipoBeanSBGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class LigacaoMuitosParaUm implements ItfEstruturaDeEntidade {

    private String label, descricao, nomeDeclarado;
    private final ItfEstruturaDeEntidade estruturaObjeto;
    private final Class classeObjetoVinculado;
    private EstruturaDeEntidade estruturaPai;

    public LigacaoMuitosParaUm(EstruturaCampo campo) {

        classeObjetoVinculado = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(campo.getClasseCampoDeclaradoOuTipoLista());
        nomeDeclarado = campo.getNomeDeclarado();
        label = campo.getLabel();
        descricao = campo.getLabel();
        estruturaPai = campo.getEstruturaPai();
        estruturaObjeto = (ItfEstruturaDeEntidade) MapaObjetosProjetoAtual.getEstruturaObjeto(classeObjetoVinculado);
        estruturaPai = campo.getEstruturaPai();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return O Nome do declarado atributo do objeto: <br>
     * Ex: private String nomeTeste; <- retorna nomeTeste
     */
    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    @Override
    public void adicionarCampo(Field pCampo) {
        estruturaObjeto.adicionarCampo(pCampo);
    }

    @Override
    public void adicionarEnum(String enums) {
        estruturaObjeto.adicionarEnum(enums);
    }

    @Override
    public void adicionarTags(String pTag) {
        estruturaObjeto.adicionarTags(pTag);
    }

    @Override
    public List<CalculoDeEntidade> getCalculos() {
        return estruturaObjeto.getCalculos();
    }

    @Override
    public EstruturaCampo getCampoByNomeDeclarado(String pNome) {
        return estruturaObjeto.getCampoByNomeDeclarado(pNome);
    }

    @Override
    public List<EstruturaCampo> getCampos() {
        return estruturaObjeto.getCampos();
    }

    @Override
    public String getIcone() {
        return estruturaObjeto.getIcone();
    }

    @Override
    public String getIconeDaClasse() {
        return estruturaObjeto.getIconeDaClasse();
    }

    @Override
    public List<String> getListaEnum() {
        return estruturaObjeto.getListaEnum();
    }

    @Override
    public List<ListaDeEntidade> getListas() {
        return estruturaObjeto.getListas();
    }

    @Override
    public List<LigacaoMuitosParaMuitos> getMuitosParaMuitos() {
        return estruturaObjeto.getMuitosParaMuitos();
    }

    @Override
    public List<LigacaoMuitosParaUm> getMuitosParaUm() {
        return estruturaObjeto.getMuitosParaUm();
    }

    @Override
    public String getNomeEntidade() {
        return estruturaObjeto.getNomeEntidade();
    }

    @Override
    public String getPlural() {
        return estruturaObjeto.getPlural();
    }

    @Override
    public List<String> getTags() {
        return estruturaObjeto.getTags();
    }

    @Override
    public FabTipoBeanSBGenerico getTipoEntidade() {
        return estruturaObjeto.getTipoEntidade();
    }

    @Override
    public List<LigacaoUmParaMuitos> getUmParaMuitos() {
        return estruturaObjeto.getUmParaMuitos();
    }

    @Override
    public void setCalculos(List<CalculoDeEntidade> calculos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCampos(List<EstruturaCampo> campos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIcone(String icone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListaEnum(List<String> listaEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListas(List<ListaDeEntidade> listas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMuitosParaMuitos(List<LigacaoMuitosParaMuitos> muitosParaMuitos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMuitosParaUm(List<LigacaoMuitosParaUm> muitosParaUm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNomeEntidade(String nomeEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlural(String plural) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTags(List<String> tags) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipoEntidade(FabTipoBeanSBGenerico tipoEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUmParaMuitos(List<LigacaoUmParaMuitos> umParaMuitos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Class getClasseObjetoVinculado() {
        return classeObjetoVinculado;
    }

    public ItfEstruturaDeEntidade getEstruturaObjeto() {
        return estruturaObjeto;
    }

}
