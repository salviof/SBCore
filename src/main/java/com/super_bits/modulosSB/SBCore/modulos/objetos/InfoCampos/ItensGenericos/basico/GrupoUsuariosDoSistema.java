/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.FabModuloSistemaCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 29/07/2015
 * @version 1.0
 */
public class GrupoUsuariosDoSistema extends EntidadeSimples implements ComoGrupoUsuario {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String usuario;

    private boolean usuarioRoot = false;

    public GrupoUsuariosDoSistema() {
    }

    public GrupoUsuariosDoSistema(UsuarioSistemaRoot usuarioroot) {
        usuarioRoot = true;
    }

    @Override
    public Long getId() {
        return -99l;
    }

    @Override
    public String getNome() {
        return "Grupo interno da Aplicação";
    }

    @Override
    public String getDescricao() {
        return "Grupo utilizado para usuários do sistema";
    }

    @Override
    public List<? extends ComoUsuario> getUsuarios() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAtivo() {
        return true;
    }

    @Override
    public ItfModuloAcaoSistema getModuloPrincipal() {
        if (usuarioRoot) {
            return FabModuloSistemaCore.MODULO_SISTEMA.getRegistro();
        } else {
            return FabModuloSistemaCore.MODULO_SISTEMA.getRegistro();
        }
    }

    @Override
    public String getNomeUnico() {
        return getId() + getNome();
    }

    @Override
    public String getEmail() {
        try {
            Field campo = getCampoReflexaoByAnotacao(FabTipoAtributoObjeto.EMAIL);
            if (campo != null) {

                String valor = (String) campo.get(this);
                if (valor != null) {
                    return valor;
                }
            }
            return UtilCRCStringSlugs.gerarSlugSimples(getNome()) + "@" + UtilCRCStringSlugs.gerarSlugSimples(getModuloPrincipal().getNome());
        } catch (Throwable t) {
            return UtilCRCStringSlugs.gerarSlugSimples(getNome()) + "@" + UtilCRCStringSlugs.gerarSlugSimples(getModuloPrincipal().getNome());

        }
    }

    @Override
    public ComoFabricaAcoes getPaginaInicial() {

        if (SBCore.getCentralAdministrativa() == null) {
            return null;
        }
        if (usuarioRoot) {
            return SBCore.getCentralAdministrativa().getFormularioPainelAdministrativoDev();
        } else {
            return SBCore.getCentralAdministrativa().getFormularioHomePadrao();
        }

    }

    @Override
    public boolean isGrupoNativoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
