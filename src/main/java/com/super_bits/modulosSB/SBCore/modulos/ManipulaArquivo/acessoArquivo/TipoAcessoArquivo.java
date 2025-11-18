/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.acessoArquivo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoTemFabricaEstatica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Tipo Acesso Arquivo"}, plural = "tipos de acesso a arquivos", fabricaVinculada = FabTipoAcessoArquivo.class)
public class TipoAcessoArquivo extends EntidadeSimples implements ComoEntidadeVinculadoAEnum {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoAcessoArquivo tipoAcessoArquivo;

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

    public String getSlugURL() {
        return UtilSBCoreStringFiltros.gerarUrlAmigavel(nome);
    }

    public ComoFabrica getFabricaObjeto() {
        return tipoAcessoArquivo;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        tipoAcessoArquivo = (FabTipoAcessoArquivo) pFabrica;
    }

    @Override
    public ComoFabrica getEnumVinculado() {
        return tipoAcessoArquivo;
    }

}
