/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.tipoComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao;
import org.coletivojava.fw.utilCoreBase.UtilCRCStringsCammelCaseSimples;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(tags = {"tipo comunicação"}, plural = "Tipos de Comunicação", fabricaVinculada = FabTipoComunicacao.class)
public class TipoComunicacao extends EntidadeSimples implements ComoTipoComunicacao, ComoEntidadeVinculadoAEnum {

    public static final String MENSAGEM_PADRAO_COMUNICACAO = "Mensagem padrão (Falta Implementar)";

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ENUM_FABRICA)
    private FabTipoComunicacao fabTipoComunicacao;
    private List<ComoTipoRespostaComunicacao> respostasPossiveis;
    private String mensagemModeloPredefinida;
    private String assunto;
    @InfoCampo(tipo = FabTipoAtributoObjeto.COR)
    private String cor;

    public String gerarMensagemPadrao() {
        return MENSAGEM_PADRAO_COMUNICACAO;
    }

    public TipoComunicacao() {
        this.nome = null;
        this.id = null;
        this.fabTipoComunicacao = null;
        this.respostasPossiveis = null;
    }

    public TipoComunicacao(FabTipoComunicacao pTipo) {
        fabTipoComunicacao = pTipo;
        respostasPossiveis = (List) UtilCRCComunicacao.getTipoRespostas(fabTipoComunicacao);
        nome = UtilCRCStringsCammelCaseSimples.getTextoByCammel(fabTipoComunicacao.toString());
        id = (long) fabTipoComunicacao.ordinal() + 1;
    }

    @Override
    public FabTipoComunicacao getFabTipoComunicacao() {
        return fabTipoComunicacao;
    }

    @Override
    public String getMensagemModeloPredefinida() {
        if (mensagemModeloPredefinida == null || mensagemModeloPredefinida.isEmpty()) {
            return gerarMensagemPadrao();
        } else {
            return mensagemModeloPredefinida;
        }

    }

    /**
     *
     * @param mensagemPersonalizada
     */
    @Override
    public void setMensagemPersonalizada(String mensagemPersonalizada) {
        this.mensagemModeloPredefinida = mensagemPersonalizada;
    }

    @Override
    public List<ComoTipoRespostaComunicacao> getTipoRespostasPossiveis() {
        if (respostasPossiveis == null) {
            if (fabTipoComunicacao != null) {
                respostasPossiveis = (List) UtilCRCComunicacao.getTipoRespostas(fabTipoComunicacao);
            }
        }
        return (List) respostasPossiveis;
    }

    @Override
    public String getIcone() {
        return icone;

    }

    @Override
    public String getAssuntoModeloPredefinido() {
        if (assunto == null) {
            assunto = getMensagemModeloPredefinida();
        }
        return assunto;
    }

    @Override
    public void setNome(String assunto) {
        this.assunto = assunto;
        this.nome = assunto;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long pID) {
        id = pID;
    }

    @Override
    public boolean isTemCampoAnotado(FabTipoAtributoObjeto pCampo) {
        if (pCampo.equals(FabTipoAtributoObjeto.ICONE)
                || pCampo.equals(FabTipoAtributoObjeto.NOME)
                || pCampo.equals(FabTipoAtributoObjeto.ID)) {
            return true;
        }
        return super.isTemCampoAnotado(pCampo); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public void setEnumVinculado(ComoFabrica pFabrica) {
        fabTipoComunicacao = (FabTipoComunicacao) pFabrica;
        if (pFabrica != null) {
            //  respostasPossiveis = (List) UtilCRCComunicacao.getTipoRespostas(fabTipoComunicacao);
        }
    }

    @Override
    public ComoFabrica getEnumVinculado() {
        return fabTipoComunicacao;
    }

}
