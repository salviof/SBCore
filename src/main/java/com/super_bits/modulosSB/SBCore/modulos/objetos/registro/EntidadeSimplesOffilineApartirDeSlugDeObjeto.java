/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

/**
 *
 * @author Salvio Furbino
 */
public class EntidadeSimplesOffilineApartirDeSlugDeObjeto extends EntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id = null;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String descricao = null;
    private final String slugEnviado;
    private TipoEntradaParametroUrl tipoEntrada;

    public EntidadeSimplesOffilineApartirDeSlugDeObjeto(String pSlug) {

        if (pSlug == null) {
            throw new UnsupportedOperationException("O valor não foi enviado");
        }
        tipoEntrada = TipoEntradaParametroUrl.getTipoEntrada(pSlug);

        java.util.regex.Pattern p
                = java.util.regex.Pattern.compile("^(.*?)(?:-(-?\\d+))?$");

        java.util.regex.Matcher m = p.matcher(pSlug);

        String texto;
        String numero = null;

        if (m.matches()) {
            texto = m.group(1);
            numero = m.group(2);
        } else {
            texto = pSlug;
        }

        System.out.println("Texto: [" + texto + "]");
        System.out.println("Numero: " + numero);

        descricao = texto;

        if (numero != null) {
            id = Long.valueOf(numero);
        }

        if (descricao == null || descricao.isEmpty()) {
            if (numero != null && !numero.isEmpty()) {
                descricao = numero;
            }

        }

// Caso "123" ou "-123"
        if (numero == null) {
            try {
                long valor = Long.parseLong(pSlug);
                id = Math.abs(valor);
                descricao = String.valueOf(Math.abs(valor));
            } catch (NumberFormatException ignored) {
            }
        }

        slugEnviado = pSlug;
    }

    @Override
    public Long getId() {

        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSlugEnviado() {
        return slugEnviado;
    }

    public TipoEntradaParametroUrl getTipoEntrada() {
        return tipoEntrada;
    }

}
