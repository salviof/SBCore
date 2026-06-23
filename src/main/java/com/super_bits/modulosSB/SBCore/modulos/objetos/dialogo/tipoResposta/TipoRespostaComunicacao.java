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

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"Resposta"}, plural = "respostas")
public class TipoRespostaComunicacao extends EntidadeSimples implements ComoTipoRespostaComunicacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    private final FabTipoRespostaComunicacao fabricaTipoResposta;
    private ComoAcaoDoSistema botaoResposta;
    private String icone;
    private String cor;

    public TipoRespostaComunicacao() {
        fabricaTipoResposta = null;
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

}
