package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringFiltros;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import java.io.File;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class EntidadeSimples extends ComoEntidadeGenerica implements
        ComoEntidadeSimples {

    public EntidadeSimples() {
        super();

        //	adcionaCampoEsperado(new CampoEsperado(TC.IMG_PEQUENA, CInfo.SITE_URL
        //			+CInfo.pastaImagens + "/SBPequeno.jpg"));
        //adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.NOME_CURTO), true);
        //adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
        // adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.NOME), true);
        // adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
    }

    public EntidadeSimples(Object pInstancia) {
        super(pInstancia);
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.NOME), true);
        adcionaCampoEsperado(new CampoEsperado(FabTipoAtributoObjeto.ID), true);
    }

    @Override
    public String getImgPequena() {
        return SBCore.getCentralDeArquivos().getEndrRemotoImagem(this, FabTipoAtributoObjeto.IMG_PEQUENA);

    }

    @Override
    public String getNomeCurto() {
        try {
            String nomeCurto = UtilCRCStringFiltros.getNomeReduzido((String) getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.NOME).getValor());

            return nomeCurto;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo o campo nome da classe" + this.getClass().getSimpleName() + " certifique que o nome tenha sido anotado, e que o tipo retornado seja String", t);
        }
        return null;

    }

    public String getNomeCurtoURLAmigavel() {
        return "url amigavel para Item simples não foi implementado";
        //String nomeCurto = (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.NOME_CURTO);
        // return UtilCRCStrings.makeStrUrlAmigavel(nomeCurto);
    }

    @Override
    public Long getId() {

        Long idEncontrado = (Long) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.ID);
        if (idEncontrado == null) {
            return null;
        }
        return idEncontrado;
    }

    public String getCampoSQLNomeCurto() {

        Field campo = getCampoByAnotacao(FabTipoAtributoObjeto.NOME);
        if (campo == null) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Nome curto não foi encontrado para obter o nome SQL do campo", null);

            return "Nome não encontrado na classe" + this.getClass().getSimpleName();
        }
        return campo.getName();

    }

    @Override
    public String getNome() {
        return (String) getValorByTipoCampoEsperado(FabTipoAtributoObjeto.NOME);
    }

    @Override
    public void setNome(String pNome) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.NOME, pNome);
    }

    @Override
    public void setId(Long pID) {
        setValorByTipoCampoEsperado(FabTipoAtributoObjeto.ID, pID);
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public String getIconeDaClasse() {
        return UtilCRCReflexaoObjeto.getIconeDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao() {
        return SBCore.getCentralVisualizacao().getCaminhoXhtmlItemCard(this.getClass());
    }

    @Override
    public boolean validar() {
        return true;
    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return new ArrayList<>();
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return SBCore.getCentralDeArquivos().salvarArquivo(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoMedio(this, pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTamanhoPequeno(this, pStream);
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return SBCore.getCentralDeArquivos().salvarImagemTodosOsFormatos(this, pStream);
    }

    @Override
    public String getSlugIdentificador() {
        return gerarSlug();
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return new File(SBCore.getCentralDeArquivos().getEndrLocalImagem(this, FabTipoAtributoObjeto.IMG_PEQUENA)).exists();
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return MapaObjetosProjetoAtual.getVisualizacaoDoObjeto(this.getClass());
    }

}
