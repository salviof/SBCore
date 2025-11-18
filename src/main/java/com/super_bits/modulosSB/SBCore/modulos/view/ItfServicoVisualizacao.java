/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.view.telas.ItfEstruturaDeFormuario;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 *
 *
 *
 * @author salvioF
 */
public interface ItfServicoVisualizacao {

    /**
     *
     * Retorna o xhtml padrão para exibição do item.
     *
     *
     * @param pEntidade
     * @return
     */
    public String getCaminhoXhtmlItemCard(Class pEntidade);

    /**
     *
     * @param pEntidade
     * @return
     */
    public String getCaminhoXhtmlItemCardLab(Class pEntidade);

    /**
     *
     * @param pEntidade
     * @param nomeAlternativo
     * @return
     */
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String nomeAlternativo);

    /**
     *
     * @param pEntidade
     * @param nomeAlternativo
     * @return
     */
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String nomeAlternativo);

    /**
     *
     * @param pEntidade
     * @return
     */
    public List<String> getTodasVisualizacoes(Class pEntidade);

    /**
     *
     * Retorna o XHTML padrão de acordo com a ação do sistema
     *
     * @param pAcao
     * @return
     */
    public String getCaminhoXhtmlAcaoDoSistema(ItfAcaoFormulario pAcao);

    /**
     *
     * Abre um formulário de gestão
     *
     * @param acaoForm
     */
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm);

    public ServicoVisualizacaoAbstrato.TIPOS_INTERFACES_COMUM_VISUALIZACAO getTipoVisualizacao();

    public String getCaminhoLocalPastaImagem();

    public String getCaminhoWebAppDeveloper();

    public String getRemotoPastaResource();

    public String getCaminhoPastaContainerLaboratorio(Class pEntidade);

    public String getCaminhoPastaContainerPublicado(Class pEntidade);

    /**
     *
     * @param pTipoVisualizacao
     * @param pClasse
     * @param pTipoEspecial
     * @return caminho relativo onde os Conteiners de exibição do componente são
     * armazenados
     */
    public String buildPastaContainers(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pClasse);

    /**
     *
     *
     *
     * @param pEntidade
     * @param ptipoVisualizacao
     *
     * @param versaoMobile
     * @return O nome do arquivo Xhtml que o arquivo deve deve ter
     */
    public String buildArqXHTML(Class pEntidade, String ptipoVisualizacao, boolean versaoMobile);

    public String buildArqJavaScript(Class pEntidade, String ptipoVisualizacao);

    public String buildArqCSSFolhaDeEstilo(Class pEntidade, String ptipoVisualizacao);

    /**
     *
     *
     * @param pTipoVisualizacao
     * @param pEntidade
     * @param pTipoEspecial
     * @param pQuantidadeColunas
     * @param visualizacaoMobile
     * @return Caminho Relativo do xhtml do Contener
     */
    public String buildCaminhoXHTMLComponente(ServicoVisualizacaoAbstrato.TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade, String pTipoEspecial, Integer pQuantidadeColunas, boolean visualizacaoMobile);

    public String buildCaminhoRelativoEntidadeSimples();

    public String buildCaminhoRelativoEntidadeSimplesNulo();

    public String buildCaminhoRelativoEntidadeNormal();

    public String buildCaminhoRelativoItemNulo();

    public String getEndrLocalArquivoReferenciaNovoComponente();

    public String getEndrRemotoFormulario(ComoFabricaAcoes pAcao, Object... paramentros);

    public String getEndrRemotoFormularioTokenAcesso(ItfTokenAcessoDinamico token);

    public String getEndrRemotoFormulario(String pAcaoNomeUnico, Object... paramentros);

    public default String getFORMULARIO_TOKEN_GENERICO() {
        return FORMULARIO_TOKEN_DE_ACESSO_GENERICO;
    }
    public final static String FORMULARIO_TOKEN_DE_ACESSO_GENERICO = "FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB";

    public ItfEstruturaDeFormuario getEstruturaFormulario(ComoAcaoDoSistema pAcaop);
}
