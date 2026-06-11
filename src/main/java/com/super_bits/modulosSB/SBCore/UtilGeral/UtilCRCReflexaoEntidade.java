/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeReflexivel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ComoEntidadeGenerica;

/**
 *
 * @author salvio
 */
public abstract class UtilCRCReflexaoEntidade {

    public static ItfCampoInstanciado getCampoInstanciadoByCaminho(ComoEntidadeReflexivel pEntidade, String pCaminho) throws ErroEntidade {

        pEntidade.getCampoInstanciadoByNomeOuAnotacao(pCaminho);
        try {

        } catch (Throwable erro) {
            if (erro.getClass().getSimpleName().toLowerCase().contains("Lazy")) {
                throw new ErroEntidade(pEntidade, "Verifique a gestão do entityManager, erro percorrendo " + pCaminho + " em Objeto com atributos LAsy, e entitymanager fechado");
            }
            throw new ErroEntidade(pEntidade, "Erro dentando encontrar " + pCaminho + " em " + pEntidade);
        }
        return null;
    }

    public static Object getValorByCaminho(ComoEntidadeGenerica pEntidade, final String pCaminho) throws ErroEntidade {
        return null;
    }

    public static Object getListaByCaminho(ComoEntidadeGenerica pEntidade, final String pCaminho) throws ErroEntidade {
        if (!pCaminho.endsWith("[]")) {
            throw new ErroEntidade(pEntidade, "Para lidar com listas, utilize [] ou [x] exemplo: Cliente.pedidos[]");
        }
        return null;
    }

}
