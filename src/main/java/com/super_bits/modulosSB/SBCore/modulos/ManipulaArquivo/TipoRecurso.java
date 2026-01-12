/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemFabricaEstatica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author salvioF
 */
@InfoObjetoSB(tags = {"Tipo Recurso"}, plural = "Tipos de Recurso", fabricaVinculada = FabTipoArquivoConhecido.class)
public class TipoRecurso extends EntidadeSimples implements ComoTemFabricaEstatica {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String descricao;
    private final FabTipoArquivoConhecido fabipoArquivo;

    public TipoRecurso(FabTipoArquivoConhecido pTipoArquivo) {
        fabipoArquivo = pTipoArquivo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FabTipoArquivoConhecido getFabipoArquivo() {
        return fabipoArquivo;
    }

    @Override
    public FabTipoArquivoConhecido getFabricaObjeto() {
        return fabipoArquivo;
    }

}
