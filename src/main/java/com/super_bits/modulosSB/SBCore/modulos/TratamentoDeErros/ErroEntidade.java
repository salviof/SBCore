/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeReflexivel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ComoEntidadeGenerica;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class ErroEntidade extends ErroCRCCoreFW {

    private String nomeEntidade;
    private boolean enviadaComoProxy;

    private void defineDadosEntidade(ComoEntidadeReflexivel pEntidade) {
        if (pEntidade == null) {
            nomeEntidade = "Entidade nula enviada";
            enviadaComoProxy = false;
        } else {
            Class classe = UtilCRCReflexaoObjeto.getClassExtraindoProxy(pEntidade.getClass().getSimpleName());
            if (classe == null) {
                nomeEntidade = "Entidade não reconhecida no sistema: " + pEntidade.getClass().getSimpleName();
            } else {
                nomeEntidade = classe.getSimpleName();
            }
            enviadaComoProxy = UtilCRCReflexaoObjeto.getClasseTemProxy(pEntidade.getClass().getSimpleName());
        }
    }

    public ErroEntidade(ComoEntidadeReflexivel pEntidade, String texto) {
        super(texto);
        defineDadosEntidade(pEntidade);

    }

    public ErroEntidade(FabErro pTipoTratamento, ComoEntidadeGenerica pEntidade, String texto) {
        super(pTipoTratamento, texto);
        defineDadosEntidade(pEntidade);
    }

    public ErroEntidade(FabErro pTipoTratamento, ComoEntidadeGenerica pEntidade, Throwable texto) {
        super(pTipoTratamento, texto);
        defineDadosEntidade(pEntidade);
    }

    public ErroEntidade(ComoEntidadeGenerica pEntidade, Throwable pErro) {
        super(pErro);
        defineDadosEntidade(pEntidade);
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public boolean isEnviadaComoProxy() {
        return enviadaComoProxy;
    }

}
