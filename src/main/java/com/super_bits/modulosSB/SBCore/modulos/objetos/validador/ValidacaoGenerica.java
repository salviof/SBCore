/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.coletivojava.fw.api.objetoNativo.log.LogPadraoSB;

/**
 *
 * @author desenvolvedor
 */
public class ValidacaoGenerica<T> implements ItfValidacao<T>, Serializable {

    private final ItfCampoInstanciado campoInst;

    @Override
    public Object validar(Object pValor) throws ErroValidacao {
        try {
            throw new UnsupportedOperationException("Ainda não implementado");
        } catch (Throwable t) {
            LogManager.getLogger(LogPadraoSB.class).error("O validador do  campo" + getCampoInstanciado().getNomeClasseAtributoDeclarado() + " não foi impplementado", t);
            if (LogManager.getLogger(LogPadraoSB.class).isDebugEnabled()) {
                LogManager.getLogger(LogPadraoSB.class).error("Falta implmentar o validador para o campo", t);
            }
            return null;
        }
    }

    public ValidacaoGenerica(ItfCampoInstanciado pCampo) {
        campoInst = pCampo;
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInst;
    }

}
