/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.modeloDocumento.ComoModeloDocumento;
import com.super_bits.modulosSB.SBCore.modulos.objetos.estrutura.ItfEstruturaCampoEntidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public class CampoInstanciadoTemplate implements ItfCampoInstTemplate {

    private final ItfCampoInstanciado campoInstanciado;

    private List<String> opcoesPalavraChave;
    private String opcoesPalavaChaveJson;

    public CampoInstanciadoTemplate(ItfCampoInstanciado pCampoInstanciado) {
        campoInstanciado = pCampoInstanciado;
    }

    boolean palavrasChavesDefinidas = false;

    private void builPalavraChave() {
        if (!palavrasChavesDefinidas) {
            opcoesPalavraChave = campoInstanciado.getPropriedadesRefexao().getTemplateCampos();

            if (campoInstanciado.getObjetoRaizDoAtributo() instanceof ComoModeloDocumento) {
                if (opcoesPalavraChave == null && ((ComoModeloDocumento) campoInstanciado.getObjetoRaizDoAtributo()).getEntidadePrincipalPalavraChave() != null) {
                    opcoesPalavraChave = new ArrayList<>();
                }

                if (((ComoModeloDocumento) campoInstanciado.getObjetoRaizDoAtributo()).getEntidadePrincipalPalavraChave() != null) {
                    String entidade = ((ComoModeloDocumento) campoInstanciado.getObjetoRaizDoAtributo()).getEntidadePrincipalPalavraChave();
                    EstruturaDeEntidade estrutura = MapaObjetosProjetoAtual.getEstruturaObjeto(entidade);
                    if (estrutura != null) {
                        for (ItfEstruturaCampoEntidade campo : estrutura.getCampos()) {
                            opcoesPalavraChave.add(campo.getNomeDeclarado());
                        }
                    }

                }

            }
        }

    }

    @Override
    public List<String> getOpcoesPalavraChave() {

        if (opcoesPalavraChave == null) {
            builPalavraChave();
        }
        return opcoesPalavraChave;
    }

    @Override
    public String getOpcoesPalavaChaveJson() {
        if (opcoesPalavaChaveJson == null) {
            opcoesPalavaChaveJson = "[";
            int i = 0;
            for (String palavraChave : getOpcoesPalavraChave()) {
                if (i > 0) {
                    opcoesPalavaChaveJson += (",\"" + palavraChave + "\"");
                } else {
                    opcoesPalavaChaveJson += ("\"" + palavraChave + "\"");
                }
                i++;
            }
            opcoesPalavaChaveJson += "]";
        }

        return opcoesPalavaChaveJson;
    }

}
