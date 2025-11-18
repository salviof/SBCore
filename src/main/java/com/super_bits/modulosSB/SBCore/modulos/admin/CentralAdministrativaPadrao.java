/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.admin;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
public class CentralAdministrativaPadrao implements ItfCentralAdministrativa, Serializable {

    private final ComoFabricaAcoes paginaInicialAnonimo;
    private final ComoFabricaAcoes paginaInicialAdmin;

    public CentralAdministrativaPadrao(ComoFabricaAcoes pPaginaInicialAnon, ComoFabricaAcoes pPaginaAdministrativa) {
        paginaInicialAnonimo = pPaginaInicialAnon;
        paginaInicialAdmin = pPaginaAdministrativa;
        // ATENCAO-> Nao instanciar ações aqui, o mapa de ações pode estar vazio, nesta etapa..
        if (!paginaInicialAdmin.toString().contains("_MB_")) {
            throw new UnsupportedOperationException("A pagina adminstrativa precisa ser do tipo gestão.");
        }
    }

    @Override
    public ComoFabricaAcoes getFormularioPainelAdministrativoDev() {
        return paginaInicialAdmin;
    }

    @Override
    public ComoFabricaAcoes getFormularioHomePadrao() {
        return paginaInicialAnonimo;
    }

}
