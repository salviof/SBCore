/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfTipoAtributoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfEstruturaCampoEntidade extends ItfTipoAtributoSB {

    String getClasseCampoDeclaradoOuTipoLista();

    String getDescricao();

    EstruturaDeEntidade getEstruturaPai();

    FabTipoAtributoObjeto getFabricaTipoAtributo();

    String getFraseValidacao();

    String getIdComponente();

    String getLabel();

    String getLabelPadrao();

    String getMascara();

    String getMascaraJqueryMode();

    String getNomeDeclarado();

    int getNumeroDeCasasDecimais();

    TIPO_ORIGEM_VALOR_CAMPO getOrigemValor();

    char getSeparadorDecimal();

    char getSeparadorMilhar();

    String getTipoCampoSTR();

    TIPO_DECLARACAO getTipoDeclaracao();

    TIPO_PRIMITIVO getTipoPrimitivoDoValor();

    String getTipoVisualizacao();

    String getValidacaoRegex();

    int getValorMaximo();

    int getValorMinimo();

    String getValorPadrao();

    boolean isMoeda();

    boolean isNumeral();

    boolean isObrigatorio();

    boolean isSomenteLeitura();

    boolean isTemMascara();

    boolean isTemValidacaoMaximo();

    boolean isTemValidacaoMinimo();

    boolean isTemValidacaoRegex();

    boolean isUmCampoDinamico();

    boolean isUmValorComLista();

    boolean isUmValorLivre();

    boolean isUmValorMultiploComLista();

    boolean isUmValorMultiploLivre();

    void setDescricao(String pDescricao);

    void setEstruturaPai(EstruturaDeEntidade estruturaPai);

    void setFabricaTipoAtributo(FabTipoAtributoObjeto pTipoCampo);

    void setFraseValidacao(String pFraseValidacao);

    void setLabelPadrao(String pLabel);

    void setListaDeOpcoes(List<ItfBeanSimples> pLista);

    void setMascara(String pMaskara);

    void setNomeDeclarado(String nomeDeclarado);

    void setNumeroDeCasasDecimais(int pNumeroCasasDecimais);

    void setObrigatorio(boolean pObrigatorio);

    void setSeparadorDecimal(char pSeparadorDecimal);

    void setSeparadorMilhar(char pSeparadorMilhar);

    void setSomenteLeitura(boolean pSomenteLeitura);

    void setTipoDeclaracao(TIPO_DECLARACAO tipoDeclaracao);

    void setValidacaoRegex(String pValidacaoRegex);

    void setValorMaximo(int pValorMaximo);

    void setValorMinimo(int pValorMinimo);

}
