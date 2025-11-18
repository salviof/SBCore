/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabCidadesSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.localizacao.FabUnidadesFederativasSemPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Transient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoUnidadeFederativa;

/**
 *
 * @author desenvolvedor
 */
public class ItemUnidadeFederativa extends ItemSimples implements ComoUnidadeFederativa {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    private String sigla;

    @Transient
    private ComoLocal vinculoLocalizacao;

    private List<ComoCidade> cidades;

    @Override
    public Long getId() {
        configIDPeloNome();
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(Long pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String pNome) {
        this.nome = pNome;
    }

    @Override
    public List<ComoCidade> getCidades() {
        if (cidades == null || cidades.isEmpty()) {
            if (sigla.toLowerCase().equals("mg")) {
                cidades = (List) FabCidadesSemPersistencia.getCidadesPorEstado(FabUnidadesFederativasSemPersistencia.MG);
            } else {
                cidades = new ArrayList<>();
            }

        }
        return cidades;
    }

    @Override
    public String getSigla() {
        return sigla;
    }

    @Override
    public void setSigla(String pSigla) {
        sigla = pSigla;
    }

    @Override
    public void setCidades(List<ComoCidade> pCidades) {
        System.out.println("cidades do estado não podem ser alteradas");
    }

}
