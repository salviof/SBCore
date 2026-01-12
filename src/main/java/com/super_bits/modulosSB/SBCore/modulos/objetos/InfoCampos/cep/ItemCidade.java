/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.cep.ComoLocalidade;

/**
 *
 * @author desenvolvedor
 */
public class ItemCidade extends EntidadeSimples implements ComoCidade {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA)
    private ComoUnidadeFederativa unidadeFederativa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LC_LOCALIDADE)
    private ComoLocalidade localidade;

    private ComoLocal vinculoLocalizacao;

    private final List<ComoBairro> listaDeBairros;

    public ItemCidade() {
        super();
        this.listaDeBairros = new ArrayList<>();
        unidadeFederativa = new ItemUnidadeFederativa();
    }

    @Override
    public void setId(Long pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        configIDPeloNome();
        return this.nome;
    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

    @Override
    public ComoUnidadeFederativa getUnidadeFederativa() {

        return this.unidadeFederativa;

    }

    @Override
    public List<ComoBairro> getBairros() {

        return this.listaDeBairros;

    }

    @Override
    public String getEstadoPontoNomeCidade() {
        return getUnidadeFederativa() + getNome();
    }

    @Override
    public ComoLocalidade getLocalidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocalidade(ComoLocalidade pLocalidade) {
        localidade = pLocalidade;
    }

    @Override
    public void setUnidadeFederativa(ComoUnidadeFederativa pUnidadeFederativa) {
        unidadeFederativa = pUnidadeFederativa;
    }

}
