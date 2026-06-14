/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeReflexivel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ComoEntidadeGenerica;
import java.util.List;

/**
 *
 * @author salvio
 */
public abstract class UtilCRCReflexaoEntidade {

    public static ItfCampoInstanciado getCampoInstanciadoByCaminho(ComoEntidadeReflexivel pEntidade, String pCaminho) throws ErroEntidade {

        try {

            if (pCaminho == null || pCaminho.trim().isEmpty()) {
                throw new ErroEntidade(pEntidade, "Especifique um caminho para o campo instanciado");
            }

            String[] segmentos = pCaminho.trim().split("\\.");

            int inicio = 0;

            // O nome da entidade é opcional e é caracterizado pela primeira letra maiúscula.
            // Quando informado, deve corresponder à entidade recebida no parametro.
            String primeiroSegmento = segmentos[0];
            if (!primeiroSegmento.isEmpty() && Character.isUpperCase(primeiroSegmento.charAt(0))) {
                try {
                    if (!isNomeEntidadeCompativel(pEntidade, primeiroSegmento)) {
                        throw new ErroEntidade(pEntidade, "Entidade " + pEntidade.getClass().getSimpleName().split("\\$")[0] + " enviada, é incompativel com caminho " + pCaminho + " é incompativel nome de entidade " + primeiroSegmento + " não encontrado");
                    }
                    inicio = 1;
                    if (segmentos.length == 1) {
                        // Apenas o nome da entidade, sem nenhum campo (ex: "Cliente")
                        throw new ErroEntidade(pEntidade, "Especifique o nome do campo (Ex:  " + pCaminho + ".nomeCampo");

                    }
                } catch (ErroEntidade e) {
                    throw e;
                } catch (Throwable t) {

                    System.out.println("");
                    throw new ErroEntidade(pEntidade,
                            "Entidade " + pEntidade.getClass().getSimpleName().split("\\$")[0] + " enviada, é incompativel com caminho " + pCaminho + " é incompativel nome de entidade " + primeiroSegmento + " não encontrado");
                }
            }

            // Percorre os níveis intermediários descendo no valor de cada campo.
            // getCampoInstanciadoByNomeOuAnotacao resolve apenas um campo simples por vez,
            // por isso a navegação entre subitens é feita aqui.
            ComoEntidadeReflexivel entidadeAtual = pEntidade;
            for (int i = inicio; i < segmentos.length - 1; i++) {
                String segmento = segmentos[i];
                String nomeCampo = getNomeCampoSemColchete(segmento);
                Integer indice = getIndiceLista(segmento);

                if (temColchete(segmento) && indice == null) {
                    // Lista sem índice (ex: subitens[]) não aponta para um único item navegável
                    throw new ErroEntidade(pEntidade, "Lista sem índice (ex: subitens[]) não aponta para um único item navegável, impossível acessar o subitem revise o caminho:  " + pCaminho);
                }

                ItfCampoInstanciado campo = entidadeAtual.getCampoInstanciadoByNomeOuAnotacao(nomeCampo);
                if (campo.isCampoNaoInstanciado()) {
                    return campo;
                }

                Object valor = campo.getValor();
                if (indice != null) {
                    if (!(valor instanceof List)) {
                        throw new ErroEntidade(pEntidade, "esperado uma lista em " + campo.getObjetoRaizDoAtributo().getNome() + campo.getNomeCamponaClasse() + "revise o caminho:  " + pCaminho + "as listas devem ser representadas por []");
                    }
                    List<?> lista = (List<?>) valor;
                    if (indice < 0 || indice >= lista.size()) {
                        throw new ErroEntidade(pEntidade, "indice  " + segmento + campo.getObjetoRaizDoAtributo().getNome() + campo.getNomeCamponaClasse() + " não encontrado, revise o caminho revise o caminho:  " + pCaminho);
                    }
                    valor = lista.get(indice);
                }

                if (!(valor instanceof ComoEntidadeReflexivel)) {

                    throw new ErroEntidade(pEntidade, nomeCampo + "não é compativel com Entidade não encontrada revise " + pCaminho);
                }
                entidadeAtual = (ComoEntidadeReflexivel) valor;
            }

            // Último segmento -> é o campo que será retornado.
            String ultimoSegmento = segmentos[segmentos.length - 1];
            String nomeCampoFinal = getNomeCampoSemColchete(ultimoSegmento);
            ItfCampoInstanciado campoFinal = entidadeAtual.getCampoInstanciadoByNomeOuAnotacao(nomeCampoFinal);
            if (campoFinal.isCampoNaoInstanciado()) {
                throw new ErroEntidade(pEntidade, "Ultima paarte do caminho " + nomeCampoFinal + " não encontrada revise " + pCaminho);
            }

            if (temColchete(ultimoSegmento)) {
                Integer indiceFinal = getIndiceLista(ultimoSegmento);
                if (indiceFinal != null) {
                    campoFinal.setIndiceValorLista(indiceFinal);
                    if (!campoFinal.isUmValorEmLista()) {
                        throw new ErroEntidade(pEntidade, "Esperado um campo de lista em " + campoFinal + " revise " + pCaminho);
                    }
                    //retorna lista com indice selecionado (o indice selecionado é usado para alerar o getTextoFormatado
                    return campoFinal;
                } else {
                    return campoFinal;
                }

            }

            return campoFinal;

        } catch (ErroEntidade e) {
            throw e;
        } catch (Throwable erro) {
            if (erro.getClass().getSimpleName().toLowerCase().contains("Lazy")) {
                throw new ErroEntidade(pEntidade, "Verifique a gestão do entityManager, erro percorrendo " + pCaminho + " em Objeto com atributos LAsy, e entitymanager fechado");
            }
            throw new ErroEntidade(pEntidade, "Erro dentando encontrar " + pCaminho + " em " + pEntidade);
        }
    }

    /**
     * Verifica se o nome informado (primeiro segmento iniciado por maiúscula)
     * corresponde à entidade recebida, considerando possíveis proxies.
     */
    private static boolean isNomeEntidadeCompativel(ComoEntidadeReflexivel pEntidade, String pNomeEntidade) {

        String nomeClasse = pEntidade.getClass().getSimpleName();
        if (pNomeEntidade.equals(nomeClasse)) {
            return true;
        }
        Class classeSemProxy = UtilCRCReflexaoObjeto.getClassExtraindoProxy(nomeClasse);
        return classeSemProxy != null && pNomeEntidade.equals(classeSemProxy.getSimpleName());
    }

    private static boolean temColchete(String pSegmento) {
        return pSegmento.contains("[") && pSegmento.contains("]");
    }

    private static String getNomeCampoSemColchete(String pSegmento) {
        int abre = pSegmento.indexOf("[");
        if (abre < 0) {
            return pSegmento;
        }
        return pSegmento.substring(0, abre);
    }

    /**
     * @return o índice informado entre colchetes, ou null quando não há índice
     * (sem colchete ou colchete vazio []).
     */
    private static Integer getIndiceLista(String pSegmento) {
        int abre = pSegmento.indexOf("[");
        int fecha = pSegmento.indexOf("]");
        if (abre < 0 || fecha < 0 || fecha <= abre + 1) {
            return null;
        }
        String conteudo = pSegmento.substring(abre + 1, fecha).trim();
        if (conteudo.isEmpty()) {
            return null;
        }
        return Integer.valueOf(conteudo);
    }

    public static Object getValorByCaminho(ComoEntidadeReflexivel pEntidade, final String pCaminho) throws ErroEntidade {
        String[] segmentos = pCaminho.trim().split("\\.");
        String ultimoSegmento = segmentos[segmentos.length - 1];
        Integer indice = getIndiceLista(ultimoSegmento);

        ItfCampoInstanciado cpInstanciado = getCampoInstanciadoByCaminho(pEntidade, pCaminho);
        if (indice != null) {
            List lista = (List) cpInstanciado.getValor();
            return lista.get(indice);
        }
        return cpInstanciado.getValor();

    }

    public static List getListaByCaminho(ComoEntidadeReflexivel pEntidade, final String pCaminho) throws ErroEntidade {
        if (!pCaminho.endsWith("[]")) {
            throw new ErroEntidade(pEntidade, "Para lidar com listas, utilize [] ou [x] exemplo: Cliente.pedidos[]");
        }
        return (List) getValorByCaminho(pEntidade, pCaminho);
    }

}
