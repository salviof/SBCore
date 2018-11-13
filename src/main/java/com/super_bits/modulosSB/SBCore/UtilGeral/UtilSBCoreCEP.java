/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.apiClients.UtilWebService.cep.WebServiceCepRepublicaVirtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreCEP {

    /**
     *
     * Configura rua, cidade, estado, nome do local, a partir de um CEP
     *
     * @param cep cep utilizado em uma pesquisa
     * @param pLocal O local onde o endereço será configurado
     * @return True se encontrar o CEP, false se não encontrar
     */
    public static boolean configuraEndereco(String cep, ItfLocal pLocal) {

        WebServiceCepRepublicaVirtual republicaVirtual = WebServiceCepRepublicaVirtual.searchCep(cep);

        if (!republicaVirtual.isCepNotFound()) {
            //ItfBairro bairroEncontrao = new ItemBairro(republicaVirtual);
            try {
                pLocal.prepararNovoObjeto();
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando Objeto para configuração de cep", t);
            }
            pLocal.setNome(republicaVirtual.getLogradouroFull());
            try {
                pLocal.getComoLocalPostavel().setLogradouro(republicaVirtual.getLogradouroFull());
            } catch (Throwable t) {

            }
            pLocal.getBairro().setNome(republicaVirtual.getBairro());

            pLocal.getBairro().getCidade().setNome(republicaVirtual.getCidade());
            pLocal.getBairro().getCidade().getUnidadeFederativa().setNome(republicaVirtual.getUf());
            pLocal.getBairro().getCidade().getUnidadeFederativa().setSigla(republicaVirtual.getUf());

            return true;
        } else {
            System.out.println("local:" + pLocal);
            System.out.println("Cep não encontrado em republic virtual" + cep);
        }

        return false;

    }

    public static boolean cepExiste(String cep) {
        WebServiceCepRepublicaVirtual republicaVirtual = WebServiceCepRepublicaVirtual.searchCep(cep);

        return !republicaVirtual.isCepNotFound();
    }

    public static List<String> cepsEncontrados(String parametros) {
        return new ArrayList<>();
    }

}
