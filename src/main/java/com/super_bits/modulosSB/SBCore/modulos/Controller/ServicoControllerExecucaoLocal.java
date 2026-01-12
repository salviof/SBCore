/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 *
 * @author sfurbino
 */
public class ServicoControllerExecucaoLocal implements ItfServicoController {

    @Override
    public ItfRespostaAcaoDoSistema getResposta(ComoFabricaAcoes pAcao, Object... pParametros) throws ErroChamadaController {

        if (true) {
            //      throw new ErroChamadaController("A chamada com multiplos parametros não foi implementado");
        }
        Method metodo = UtilSBController.getMetodoByAcaoController(pAcao.getRegistro().getComoController());
        if (metodo.getParameterCount() != pParametros.length) {
            throw new ErroChamadaController("A quantidade de parametros para " + pAcao.getNomeUnico() + " é divergente dos parametros enviados");
        }
        try {
            if (metodo.getParameterCount() == 0) {
                return (ItfRespostaAcaoDoSistema) metodo.invoke(null);
            }
            return (ItfRespostaAcaoDoSistema) metodo.invoke(null, pParametros[0]);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServicoControllerExecucaoLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServicoControllerExecucaoLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ServicoControllerExecucaoLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ItfRespostaAcaoDoSistema getResposta(ComoFabricaAcoes pAcao, ComoEntidadeSimples pEntidade) throws ErroChamadaController {

        if (pAcao == null) {
            throw new ErroChamadaController("Tentativa de chamada de ação enviando ação nula");
        }

        if (pAcao == null) {
            throw new ErroChamadaController("Tentativa de chamada de ação enviando entidade nula");
        }
        Method metodo = UtilSBController.getMetodoByAcaoController(pAcao.getRegistro().getComoController());

        String tipoMetodoParametro = metodo.getParameterTypes()[0].getSimpleName();
        String tipoEntidadeSelecionada = pEntidade.getClass().getSimpleName();

        if (!tipoMetodoParametro.equals(tipoEntidadeSelecionada)) {
            throw new ErroChamadaController("" + pAcao.getNomeUnico() + " não suporta  parametro  do tipo " + tipoMetodoParametro + " Apenas:" + tipoMetodoParametro);
        }
        try {
            return (ItfRespostaAcaoDoSistema) metodo.invoke(null, pEntidade);
        } catch (IllegalAccessException ex) {

        } catch (IllegalArgumentException ex) {
            throw new ErroChamadaController("" + pAcao.getNomeUnico() + " não suporta  parametro  do tipo " + tipoMetodoParametro + " Apenas:" + tipoMetodoParametro);
        } catch (InvocationTargetException ex) {
            throw new ErroChamadaController("" + pAcao.getNomeUnico() + " não suporta  parametro  do tipo " + tipoMetodoParametro + " Apenas:" + tipoMetodoParametro);
        }
        return null;
    }

    @Override
    public ItfRespostaAcaoDoSistema getResposta(String pNomeUncicoAcao, Object... pParametros) throws ErroChamadaController {
        if (true) {
            throw new ErroChamadaController("A chamada com multiplos parametros não foi implementado");
        }
        return null;
    }

    @Override
    public ItfRespostaAcaoDoSistema getResposta(String pNomeUncicoAcao, ComoEntidadeSimples pEntidade) throws ErroChamadaController {
        if (true) {
            throw new ErroChamadaController("A chamada com multiplos parametros não foi implementado");
        }
        ComoAcaoDoSistema pAcao = MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(pNomeUncicoAcao);
        return getResposta(pAcao.getEnumAcaoDoSistema(), pEntidade);
    }

}
