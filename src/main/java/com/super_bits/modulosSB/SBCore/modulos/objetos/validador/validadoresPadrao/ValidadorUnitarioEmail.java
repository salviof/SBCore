/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ItfValidacaoUnitaria;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

/**
 *
 * @author salvio
 */
public class ValidadorUnitarioEmail extends ValidadorUnitarioCampoInstGenerico implements ItfValidacaoUnitaria {

    public ValidadorUnitarioEmail(ItfCampoInstanciado pCampoInst) {
        super(pCampoInst, FabTipoValidacaoUnitaria.EMAIL);

    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {

        if (pValor == null) {
            return null;
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            return null;
        }
        try {

            String email = pValor.toString();
            if (!email.contains("@")) {
                throw new ErroValidacao("O e-mail não parece válido");
            }

            String dominio = email.substring(email.indexOf("@") + 1);

            try {
                Record[] records = new Lookup(dominio, Type.MX).run();
                if (records == null || records.length == 0) {

                    try {
                        Record[] recordsSimples = new Lookup(dominio, Type.NS).run();
                        Record[] recordsSimplesTPA = new Lookup(dominio, Type.A).run();
                        if ((recordsSimples == null || recordsSimples.length == 0)
                                && (recordsSimplesTPA == null || recordsSimplesTPA.length == 0)) {
                            throw new ErroValidacao("O domínio [" + dominio + "] não foi registrado");
                        }
                    } catch (TextParseException ex) {
                        throw new ErroValidacao("O domínio [" + dominio + "] não é valido");
                    }

                    throw new ErroValidacao("O domínio [" + dominio + "] não contem caixas postais");
                }
            } catch (TextParseException ex) {
                throw new ErroValidacao("O domínio [" + dominio + "] não contem caixas postais");
            }
        } catch (ErroValidacao erv) {
            return erv.getMensagemAoUsuario();
        }
        return null;
    }
}
