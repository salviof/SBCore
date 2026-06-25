/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.tipoResposta;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import org.coletivojava.fw.api.objetoNativo.controller.acao.AcaoBotaoNaoRequisitado;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Resposta"}, plural = "respostas")
public class TipoRespostaComunicacao extends EntidadeSimples implements ComoTipoRespostaComunicacao, ComoEntidadeVinculadoAEnum {

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoRespostaComunicacao fabricaTipoResposta;

    private ComoAcaoDoSistema botaoResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean respostaPositiva;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean respostaNegativa;

    public TipoRespostaComunicacao() {
        fabricaTipoResposta = null;
    }

    public void setFabricaTipoResposta(FabTipoRespostaComunicacao fabricaTipoResposta) {
        this.fabricaTipoResposta = fabricaTipoResposta;

        respostaPositiva = fabricaTipoResposta.isRespostaPositiva();
        respostaNegativa = !fabricaTipoResposta.isRespostaPositiva();
    }

    public TipoRespostaComunicacao(FabTipoRespostaComunicacao pTipo) {
        fabricaTipoResposta = pTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    public FabTipoRespostaComunicacao getFabricaTipoResposta() {
        return fabricaTipoResposta;
    }

    @Override
    public ComoAcaoDoSistema getBotaoResposta() {
        if (botaoResposta == null) {
            botaoResposta = new AcaoBotaoNaoRequisitado();
            botaoResposta.setNomeAcao(getDescricao());
            botaoResposta.setIconeAcao(getIcone());
        }
        return botaoResposta;
    }

    @Override
    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public boolean isRespostasNegativa() {
        return ComoTipoRespostaComunicacao.super.isRespostasNegativa(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRespostasPosiva() {
        return ComoTipoRespostaComunicacao.super.isRespostasPosiva(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String pNome) {
        descricao = pNome;
    }

    @Override
    public String getNome() {
        return descricao;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        setFabricaTipoResposta((FabTipoRespostaComunicacao) pFabrica);
    }

    @Override
    public ComoFabrica getEnumVinculado() {
        return fabricaTipoResposta;
    }

    public boolean isRespostaPositiva() {
        return respostaPositiva;
    }

    public boolean isRespostaNegativa() {
        return respostaNegativa;
    }

}
