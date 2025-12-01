/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.validador.validadoresPadrao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoTemEndereco;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;

/**
 *
 * @author sfurbino
 */
public class ValidadorUnitarioLocalizacao extends ValidadorUnitarioCampoInstGenerico {

    private final ComoLocal localAnalizado;

    public ValidadorUnitarioLocalizacao(ItfCampoInstanciado pCampoInstanciado) {
        super(pCampoInstanciado, FabTipoValidacaoUnitaria.LOCALIZACAO);
        localAnalizado = (ComoLocal) campoInstanciado.getValor();
    }

    @Override
    public String gerarMensagemErroValidacao(Object pValor) {
        if (localAnalizado != null) {
            if (campoInstanciado.getComoCampoLocalizacao() == null) {
                if (campoInstanciado.isObrigatorio()) {
                    return "Informe o endereço";
                } else {
                    return null;
                }
            }
            if (localAnalizado.getBairro() != null
                    && UtilCRCStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida, informe o BAIRRO";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro() != null
                    && UtilCRCStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida, informe o BAIRRO";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade() != null
                    && UtilCRCStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida informe a CIDADE";
            }
            if (campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getUnidadeFederativa() != null
                    && UtilCRCStringValidador.isNuloOuEmbranco(campoInstanciado.getComoCampoLocalizacao().getBairro().getCidade().getUnidadeFederativa().getNome())) {
                campoInstanciado.setValor(null);
                return "Localização inválida informe a Unidade Federativa";
            }

        } else {
            if (campoInstanciado.getValor() == null) {
                if (campoInstanciado.isObrigatorio()) {
                    if (campoInstanciado.getObjetoDoAtributo() instanceof ComoTemEndereco) {
                        ((ComoTemEndereco) campoInstanciado.getObjetoDoAtributo()).instanciarNovoEndereco();
                        return "Localização inválida, informe o endereço";
                    }
                }
            }
        }
        return null;
    }

}
