/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_json_to_entidade;

import com.fasterxml.jackson.databind.JsonNode;
import com.super_bits.modulosSB.SBCore.modulos.erpCore.dto_entidade_to_json.UtilEntidadeToJsonParser;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.SENHA;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto.SENHA_SEGURANCA_MAXIMA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoDominioEntidadeGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvio
 */
public class UtilJsonToEntidadeParser {

    public static ComoEntidadeSimples instanciarClasseEntidadeCaramelo(JsonNode pJsonNode) {
        try {

            if (pJsonNode.has("entidade")) {
                Class classe = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pJsonNode.get("entidade").asText());

                ComoEntidadeSimples pEntidade = (ComoEntidadeSimples) classe.newInstance();

                for (ItfCampoInstanciado cpInstanciado : ((ComoDominioEntidadeGenerico) pEntidade).getCamposInstanciados()) {
                    switch (cpInstanciado.getFabricaTipoAtributo()) {
                        case SENHA:
                        case SENHA_SEGURANCA_MAXIMA:
                            continue;
                    }
                    if (pJsonNode.has(cpInstanciado.getNomeCamponaClasse())) {
                        JsonNode valor = pJsonNode.get(cpInstanciado.getNomeCamponaClasse());
                        if (valor.isArray()) {
                            List lista = new ArrayList();
                            ArrayNode arrayNode = (ArrayNode) valor;
                            for (JsonNode itemNode : arrayNode) {
                                if (itemNode.isObject() && itemNode.has("entidade")) {
                                    // item é uma entidade conhecida — deserializa recursivamente
                                    ComoEntidadeSimples itemEntidade = instanciarClasseEntidadeCaramelo(itemNode);
                                    if (itemEntidade != null) {
                                        lista.add(itemEntidade);
                                    }
                                } else if (itemNode.isTextual()) {
                                    // item escalar (enum em texto, String, etc.)
                                    lista.add(itemNode.asText());
                                } else if (itemNode.isNumber()) {
                                    lista.add(itemNode.numberValue());
                                } else if (itemNode.isBoolean()) {
                                    lista.add(itemNode.booleanValue());
                                }
                                // itens com _ciclo ou _profundidadeMaxima são ignorados
                            }
                            cpInstanciado.setValor(lista);
                            continue;
                        }
                        if (valor.isObject()) {
                            if (valor.has("entidade")) {
                                String nometipoEntidadeFilho = valor.get("entidade").asText();
                                Class tipoEntidadeFilho = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nometipoEntidadeFilho);
                                ComoEntidadeSimples entidadeFilho = instanciarClasseEntidadeCaramelo(valor);
                                cpInstanciado.setValor(entidadeFilho);
                            }
                            continue;
                        }
                        switch (cpInstanciado.getFabricaTipoAtributo().getTipoPrimitivo()) {

                            case INTEIRO:
                                cpInstanciado.setValor(valor.asText());
                                break;
                            case NUMERO_LONGO:
                                break;
                            case LETRAS:
                                cpInstanciado.setValor(valor.asText());
                                break;
                            case DATAS:
                                cpInstanciado.setValor(new Date(valor.asLong()));
                                break;
                            case BOOLEAN:
                                cpInstanciado.setValor(valor.asBoolean());
                                break;
                            case DECIMAL:
                                cpInstanciado.setValor(valor.asDouble());
                                break;
                            case ENTIDADE:
                                //Tratado em valor.isObjetct
                                break;
                            case OUTROS_OBJETOS:
                                break;
                            default:
                                throw new AssertionError();
                        }

                    }
                }
                return (ComoEntidadeSimples) pEntidade;
            }
        } catch (Throwable ex) {
            Logger.getLogger(UtilEntidadeToJsonParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
