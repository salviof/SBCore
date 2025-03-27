package com.super_bits.modulosSB.SBCore.modulos.grafico;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Item totalizador gráfico", plural = "Itens totalizadores de gráfico")
public class ItemDadoGraficoTotal extends ItemSimples implements ItfDadoGraficoTotal {

    public ItemDadoGraficoTotal(int codigo, String label, double valor) {
        this.codigo = codigo;
        this.label = label;
        this.valor = valor;

    }
    public ItemDadoGraficoTotal(int codigo, String label, double valor,Object pItemSelecionado) {
        this(codigo, label, valor);
        this.itemRelacionado=pItemSelecionado;
    }

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private final int codigo;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private final String label;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE)
    private final double valor;
    
    private Object itemRelacionado;

    @Override
    public int getId() {
        return codigo;
    }

    @Override
    public String getNome() {
        return label;
    }

    @Override
    public double getValor() {
        return valor;
    }

    public boolean isTotalPorTipo() {
        return (this instanceof ItfDadoGraficoTotalTipo);
    }

    public boolean isTotalPorData() {
        return (this instanceof ItfDadoGraficoTotalData);
    }

    public ItfDadoGraficoTotalTipo getComoDadoGraficoTotalTipo() {
        return (ItfDadoGraficoTotalTipo) this;
    }

    public ItfDadoGraficoTotalData getComoDadoGraficoTotalData() {
        return (ItfDadoGraficoTotalData) this;
    }

    @Override
    public Object getItemRelacionado() {
        return itemRelacionado;
    }
    
    
}
