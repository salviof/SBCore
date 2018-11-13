/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.seletores;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.List;

/**
 *
 * @author desenvolvedor
 * @param <T>
 */
public abstract class B_SeletorDeGetorGenerico<T extends ItfBeanSimplesSomenteLeitura>
        implements ItfSeletorGenerico<T> {

    protected final ItfCampoInstanciado campoInstanciado;
    protected final List<T> origem;
    private String nomeListaOrigem;

    public B_SeletorDeGetorGenerico(ItfCampoInstanciado pCampoInstanciado) {
        this.campoInstanciado = pCampoInstanciado;
        origem = (List) pCampoInstanciado.getListaDeOpcoes();

    }

    public B_SeletorDeGetorGenerico(List<T> pOrigem) {
        campoInstanciado = null;
        origem = (List) pOrigem;
    }

    @Override
    public List<T> getOrigem() {
        return origem;
    }

    @Override
    public String getNomeOrigem() {
        if (nomeListaOrigem == null) {
            if (campoInstanciado != null) {
                nomeListaOrigem = campoInstanciado.getNomeDoObjeto() + " disponíveis ";
            } else {
                nomeListaOrigem = "Opções disponíveis";
            }
        }
        return nomeListaOrigem;

    }

    @Override
    public void setNomeOrigem(String pNomeString) {
        nomeListaOrigem = pNomeString;
    }

}
