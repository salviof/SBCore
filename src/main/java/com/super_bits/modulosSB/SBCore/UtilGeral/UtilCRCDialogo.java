/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ComoDialogo;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.dialogo.resposta.RespostaComunicacao;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.coletivojava.fw.utilCoreBase.UtilCRCComunicacao.getTipoRespostas;

/**
 *
 * @author salvio
 */
public class UtilCRCDialogo {

    public static List<ItfRespostaComunicacao> getRespostaCOmunicacao(FabTipoComunicacao pTipoComunicacao, ComoDialogo cm) {
        List<ItfRespostaComunicacao> respostas = new ArrayList<>();

        getTipoRespostas(pTipoComunicacao).stream().map((resposta)
                -> new RespostaComunicacao(cm, resposta)).forEach((resp) -> {
            respostas.add(resp);
        });

        Comparator cp = new OrdemPositivoPorUltimo();
        respostas.sort(cp);
        return respostas;

    }

    public static List<ItfRespostaComunicacao> getRespostaCOmunicacao(ComoDialogo pComunicacao) {

        return getRespostaCOmunicacao(pComunicacao.getTipoComunicacao().getFabTipoComunicacao(), pComunicacao);

    }

    private static class OrdemPositivoPorUltimo implements Comparator<ItfRespostaComunicacao> {

        @Override
        public int compare(ItfRespostaComunicacao o1, ItfRespostaComunicacao o2) {
            if (o1.getTipoResposta().isRespostasPosiva()) {
                if (o2.getTipoResposta().isRespostasPosiva()) {
                    return -1;
                }
                return 1;
            } else {
                return -1;
            }
        }

    }

}
