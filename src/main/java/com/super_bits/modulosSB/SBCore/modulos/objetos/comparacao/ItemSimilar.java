/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.comparacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCaminhoCampoInvalido;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAssistenteDeLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedorninja01
 * @since 05/09/2019
 * @version 1.0
 */
public class ItemSimilar implements ItfBeanSimples, Comparable<ItemSimilar> {

    private final ItfBeanSimples pObjetoAnalizado;
    private double nota;
    private final List<Double> notasValidas = new ArrayList<>();
    private final List<Double> notasIdentico = new ArrayList<>();
    private final List<Double> notasIdenticoInicio = new ArrayList<>();

    public ItemSimilar(ItfBeanSimples pObjetoAnalizado, String parametro) {
        this.pObjetoAnalizado = pObjetoAnalizado;
        nota = 0;
        try {
            parametro = parametro.toLowerCase();

            if (pObjetoAnalizado.getNome() == null || pObjetoAnalizado.getNome().isEmpty()) {
                return;
            }

            Arrays.stream(parametro.split("\\s"))
                    .forEach(parteParametro -> {
                        Arrays.stream(pObjetoAnalizado.getNome().toLowerCase().replace("-", " ").split("\\s"))
                                .forEach(parteTextoAnalizadao -> {
                                    if (UtilSBCoreStringValidador.isNuloOuEmbranco(parteTextoAnalizadao)) {
                                        return;
                                    }
                                    if (parteTextoAnalizadao.length() > parteParametro.length()) {
                                        parteTextoAnalizadao = parteTextoAnalizadao.substring(0, parteParametro.length());
                                    }
                                    double nt = UtilSBCoreStringComparador.JaroWinkler(parteTextoAnalizadao, parteParametro);
                                    if (nt > 0.8) {
                                        if (nt >= 0.9) {
                                            notasIdentico.add(nt);
                                            if (pObjetoAnalizado.getNome().toLowerCase().startsWith(parteParametro)) {
                                                notasIdenticoInicio.add(nt);
                                            }
                                        } else {
                                            notasValidas.add(nt);
                                        }
                                    }
                                    if (nt > nota) {
                                        nota = nt;
                                    }
                                });

                    });

            if (!notasValidas.isEmpty()) {
                nota = (notasValidas.stream().mapToDouble(Double::doubleValue)
                        .sum() / notasValidas.size());
            }
            if (!notasIdentico.isEmpty()) {
                nota += (notasIdentico.size() * 1);
            }
            if (!notasIdenticoInicio.isEmpty()) {
                nota += (notasIdenticoInicio.size() * 2);
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando indice de semelhanca", t);
        }
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String getNomeUnicoSlug() {
        return pObjetoAnalizado.getNomeUnicoSlug();
    }

    @Override
    public boolean validar() {
        return pObjetoAnalizado.validar();
    }

    @Override
    public void setNome(String pNome) {

    }

    @Override
    public List<ItfMensagem> validarComMensagens() {
        return pObjetoAnalizado.validarComMensagens();
    }

    @Override
    public boolean uploadArquivoDeEntidade(ItfCampoInstanciado prcampo, byte[] pStream, String pNomeArquivo) {
        return pObjetoAnalizado.uploadArquivoDeEntidade(prcampo, pStream, pNomeArquivo);
    }

    @Override
    public boolean isTemImagemPequenaAdicionada() {
        return pObjetoAnalizado.isTemImagemPequenaAdicionada();
    }

    @Override
    public String getSlugIdentificador() {
        return pObjetoAnalizado.getSlugIdentificador();
    }

    @Override
    public String getNomeCurto() {
        return pObjetoAnalizado.getNomeCurto();
    }

    @Override
    public String getNome() {
        return pObjetoAnalizado.getNome();
    }

    @Override
    public String getIconeDaClasse() {
        return pObjetoAnalizado.getIconeDaClasse();
    }

    @Override
    public String getXhtmlVisao() {
        return pObjetoAnalizado.getXhtmlVisao();
    }

    @Override
    public String getXhtmlVisaoMobile() {
        return pObjetoAnalizado.getXhtmlVisaoMobile();
    }

    @Override
    public String getXhtmlVisao(int numeroColunas) {
        return pObjetoAnalizado.getXhtmlVisao(numeroColunas);
    }

    @Override
    public int getId() {
        return pObjetoAnalizado.getId();
    }

    @Override
    public int configIDPeloNome() {
        return pObjetoAnalizado.configIDPeloNome();
    }

    @Override
    public String getNomeDoObjeto() {
        return pObjetoAnalizado.getNomeDoObjeto();
    }

    @Override
    public String getNomeDoObjetoPlural() {
        return pObjetoAnalizado.getNomeDoObjetoPlural();
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        pObjetoAnalizado.adicionarItemNaLista(nomeDaLista);
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        return pObjetoAnalizado.isTemCampoAnotado(pCampo);
    }

    @Override
    public String getImgPequena() {
        return pObjetoAnalizado.getImgPequena();
    }

    @Override
    public void setId(int pID) {

    }

    @Override
    public void adicionarJustificativaExecucaoAcao(ItfAcaoDoSistema pAcao, String pJustificativa) {

    }

    @Override
    public String getJustificativa(ItfAcaoDoSistema pAcao) {
        return pObjetoAnalizado.getJustificativa(pAcao);
    }

    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        pObjetoAnalizado.prepararNovoObjeto(parametros);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstanciados() {
        return pObjetoAnalizado.getCamposInstaciadosInvalidos();
    }

    @Override
    public void adicionarSubItem(String pNomeCampo) {

    }

    @Override
    public ItfAssistenteDeLocalizacao getAssistenteLocalizacao(ItfCampoInstanciado pCampoInst, TipoOrganizacaoDadosEndereco pTipo) {
        return pObjetoAnalizado.getAssistenteLocalizacao(pCampoInst, pTipo);
    }

    @Override
    public void adicionarAssitenteLocalizacao(ItfAssistenteDeLocalizacao pLocalizacao) {

    }

    @Override
    public void copiaDados(Object pObjetoReferencia) {
        pObjetoAnalizado.copiaDados(pObjetoReferencia);
    }

    @Override
    public List<ItfCampoInstanciado> getCamposInstaciadosInvalidos() {
        return pObjetoAnalizado.getCamposInstaciadosInvalidos();
    }

    @Override
    public ItfCampoInstanciado getCampoByNomeOuAnotacao(String pNome) {
        return pObjetoAnalizado.getCampoInstanciadoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByNomeOuAnotacao(String pNome) {
        return pObjetoAnalizado.getCampoInstanciadoByNomeOuAnotacao(pNome);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto pTipocampo) {
        return pObjetoAnalizado.getCampoInstanciadoByAnotacao(pTipocampo);
    }

    @Override
    public ItfCampoInstanciado getCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return pObjetoAnalizado.getCampoByCaminhoCampo(pNome);
    }

    @Override
    public Object getValorCampoByCaminhoCampo(ItfCaminhoCampo pNome) {
        return pObjetoAnalizado.getValorCampoByCaminhoCampo(pNome);
    }

    @Override
    public List<ItfCaminhoCampo> getEntidadesVinculadas() {
        return pObjetoAnalizado.getEntidadesVinculadas();
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        return pObjetoAnalizado.getBeanSimplesPorNomeCampo(pNomeCampo);
    }

    @Override
    public ItfBeanSimples getItemPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return pObjetoAnalizado.getItemPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfBeanSimples> getListaPorCaminhoCampo(ItfCaminhoCampo pCaminho) {
        return pObjetoAnalizado.getListaPorCaminhoCampo(pCaminho);
    }

    @Override
    public List<ItfCaminhoCampoInvalido> getCamposInvalidos() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public Field getCampoReflexaoByAnotacao(FabTipoAtributoObjeto pInfoCampo) {
        return pObjetoAnalizado.getCampoReflexaoByAnotacao(pInfoCampo);
    }

    @Override
    public String getNomeCampo(FabTipoAtributoObjeto pInfocampo) {
        return pObjetoAnalizado.getNomeCampo(pInfocampo);
    }

    @Override
    public boolean uploadFotoTodosFormatos(InputStream pStream) {
        return pObjetoAnalizado.uploadFotoTodosFormatos(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoGrande(InputStream pStream) {
        return pObjetoAnalizado.uploadFotoTamanhoGrande(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoPequeno(InputStream pStream) {
        return pObjetoAnalizado.uploadFotoTamanhoPequeno(pStream);
    }

    @Override
    public boolean uploadFotoTamanhoMedio(InputStream pStream) {
        return pObjetoAnalizado.uploadFotoTamanhoMedio(pStream);
    }

    @Override
    public int compareTo(ItemSimilar o) {

        return Double.compare(nota, o.nota);

    }

    public ItfBeanSimples getpObjetoAnalizado() {
        return pObjetoAnalizado;
    }

}
