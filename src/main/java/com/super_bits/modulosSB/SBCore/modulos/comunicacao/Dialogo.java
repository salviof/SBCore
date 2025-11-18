/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

/**
 *
 * @author salvioF
 */
public class Dialogo {

    private List<ItfMensagem> dialogo;
    private ComoUsuario usuarioInicioDialogo;
    private FabTipoRespostaComunicacao respostaObtida;

    public List<ItfMensagem> getDialogo() {
        return dialogo;
    }

    public Dialogo setDialogo(List<ItfMensagem> dialogo) {
        this.dialogo = dialogo;
        return this;
    }

    public ComoUsuario getUsuarioInicioDialogo() {
        return usuarioInicioDialogo;
    }

    public Dialogo setUsuarioInicioDialogo(ComoUsuario usuarioInicioDialogo) {
        this.usuarioInicioDialogo = usuarioInicioDialogo;
        return this;
    }

    public FabTipoRespostaComunicacao getRespostaObtida() {
        return respostaObtida;

    }

    public Dialogo setRespostaObtida(FabTipoRespostaComunicacao respostaObtida) {
        this.respostaObtida = respostaObtida;
        return this;
    }

}
