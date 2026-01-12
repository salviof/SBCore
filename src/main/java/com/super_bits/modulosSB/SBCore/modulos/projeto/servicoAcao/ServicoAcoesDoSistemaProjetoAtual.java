package com.super_bits.modulosSB.SBCore.modulos.projeto.servicoAcao;

import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;

/**
 * Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90
 *
 */
/**
 *
 * @author salvio
 */
public class ServicoAcoesDoSistemaProjetoAtual implements ComoServicoAcoesDoSistema {

    @Override
    public ComoAcaoDoSistema getAcaoByNomeUnico(String pNome) {
        return MapaAcoesSistema.getAcaoDoSistemaByNomeUnico(pNome);
    }

}
