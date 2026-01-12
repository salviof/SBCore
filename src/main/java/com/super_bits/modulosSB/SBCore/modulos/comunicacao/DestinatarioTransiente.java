/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author desenvolvedor
 */
public class DestinatarioTransiente implements ItfDestinatario {

    private final FabTipoDestinatario tipoDestinatario;
    private final ComoUsuario usuario;
    private List<ComoUsuario> usuarios;
    private ComoGrupoUsuario grupoUsuario;

    private List<ComoGrupoUsuario> gruposUsuario;

    public DestinatarioTransiente(ComoUsuario pUsuario) {
        tipoDestinatario = FabTipoDestinatario.USUARIO;
        usuario = pUsuario;
    }

    @Override
    public FabTipoDestinatario getTipoDestinatario() {
        return tipoDestinatario;
    }

    @Override
    public List<ComoUsuario> getUsuarios() {
        List<ComoUsuario> usuariosEncontrados = new ArrayList<>();
        switch (tipoDestinatario) {
            case USUARIO:
                usuariosEncontrados.add(usuario);
                break;
            case USUARIOS:
                return usuarios;

            case GRUPO:
                return (List) grupoUsuario.getUsuarios();

            case GRUPOS:
                for (ComoGrupoUsuario grupo : gruposUsuario) {
                    usuariosEncontrados.addAll((List) grupo.getUsuarios());
                }

                break;
            default:
                throw new AssertionError(tipoDestinatario.name());

        }
        return usuariosEncontrados;
    }

    @Override
    public ComoUsuario getUsuario() {
        switch (tipoDestinatario) {
            case USUARIO:
                return usuario;

            case USUARIOS:
            case GRUPO:
            case GRUPOS:

            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getUsuario");

        }
    }

    @Override
    public ComoGrupoUsuario getGrupoUsuario() {
        switch (tipoDestinatario) {
            case GRUPO:
                return grupoUsuario;
            case USUARIO:
            case USUARIOS:
            case GRUPOS:
            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getGrupoUsuario");

        }

    }

    @Override
    public List<ComoGrupoUsuario> getGruposUsuario() {
        switch (tipoDestinatario) {
            case GRUPOS:
                return gruposUsuario;
            case GRUPO:

            case USUARIO:
            case USUARIOS:

            default:
                throw new UnsupportedOperationException("O tipo " + tipoDestinatario + " não suporta o metodo getGruposUsuario");

        }
    }

    @Override
    public String getEmailsConcatenados() {
        switch (tipoDestinatario) {
            case USUARIO:
                return getUsuario().getEmail();

            case USUARIOS:

                return UtilCRCListas.getValoresSeparadosPorPontoVirgula(UtilCRCListas.getListaStringAtributoObjeto(getUsuarios(), "email"));

            case GRUPO:

                return getGrupoUsuario().getEmail();

            case GRUPOS:
                return UtilCRCListas.getValoresSeparadosPorPontoVirgula(UtilCRCListas.getListaStringAtributoObjeto(getGruposUsuario(), "email"));

            default:
                throw new AssertionError(tipoDestinatario.name());

        }
    }

}
