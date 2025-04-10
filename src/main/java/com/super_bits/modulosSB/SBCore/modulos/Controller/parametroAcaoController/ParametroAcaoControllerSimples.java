/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.parametroAcaoController;

/**
 *
 * @author desenvolvedor
 */
public class ParametroAcaoControllerSimples implements ItfParametrosAcaoController {

    private String entidadePrincial;
    private Long idEntidadePrincipal;

    public ParametroAcaoControllerSimples(String entidadePrincial, Long idEntidadePrincipal) {
        this.entidadePrincial = entidadePrincial;
        this.idEntidadePrincipal = (long) idEntidadePrincipal;
    }

    @Override
    public ParametroAcaoControllerSimples setEntidadePrincial(String entidadePrincial) {
        this.entidadePrincial = entidadePrincial;
        return this;
    }

    @Override
    public ParametroAcaoControllerSimples setIdEntidadePrincipal(Long idEntidadePrincipal) {
        this.idEntidadePrincipal = idEntidadePrincipal;
        return this;
    }

    @Override
    public String getEntidadePrincial() {
        return entidadePrincial;
    }

    @Override
    public Long getIdEntidadePrincipal() {
        return idEntidadePrincipal;
    }

}
