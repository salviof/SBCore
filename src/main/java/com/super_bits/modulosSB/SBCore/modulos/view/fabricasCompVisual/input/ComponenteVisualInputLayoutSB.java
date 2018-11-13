/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.input;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import org.coletivojava.fw.api.objetoNativo.view.componente.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;

/**
 *
 * Modos de exibição input: Esquerda, Topo, Mascara, sem Label etc..
 *
 * @author desenvolvedor
 */
@InfoObjetoSB(fabricaVinculada = FabCompVIsualInputsLayout.class, tags = {"Layout Input"}, plural = "Layouts Input", descricao = "Devide o modo de exibição de um input")
public class ComponenteVisualInputLayoutSB extends ComponenteVisualSB {

    private String estiloTabela;
    private String estiloLinhas;
    private String estiloColunas;
    private String estiloLinhasLeitura;
    private String estiloColunasLeitura;

    @Override
    public void setFabricaDoComponente(ItfFabTipoComponenteVisual fabricaDoComponente) {
        try {
            super.setFabricaDoComponente(fabricaDoComponente);
            FabCompVIsualInputsLayout enumInput = (FabCompVIsualInputsLayout) getFabricaDoComponente();
            estiloTabela = enumInput.estiloTabela();
            estiloLinhas = enumInput.estiloLinhas();

            estiloColunas = enumInput.estiloColunas();
            estiloColunasLeitura = enumInput.estiloColunasLeitura();
            estiloLinhasLeitura = enumInput.estiloLinhasLeitura();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando propriedades do componente input" + fabricaDoComponente, t);
        }

    }

    public String getEstiloTabela() {
        return estiloTabela;
    }

    public String getEstiloLinhas() {
        return estiloLinhas;
    }

    public String getEstiloColunas() {
        return estiloColunas;
    }

    public String getEstiloLinhasLeitura() {
        return estiloLinhasLeitura;
    }

    public String getEstiloColunasLeitura() {
        return estiloColunasLeitura;
    }

    public String getEstiloColuna(boolean somenteLeitura) {
        if (somenteLeitura) {
            return estiloColunasLeitura;
        } else {
            return estiloColunas;
        }
    }

    public String getEstiloLinha(boolean somenteLeitura) {
        if (somenteLeitura) {
            return estiloLinhasLeitura;
        } else {
            return estiloLinhas;
        }
    }

}
