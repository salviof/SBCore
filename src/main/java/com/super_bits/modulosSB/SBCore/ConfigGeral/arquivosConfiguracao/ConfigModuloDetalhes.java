
/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Configuração", plural = "Variaveis de ambiente")
public class ConfigModuloDetalhes extends EntidadeSimples implements ComoEntidadeSimples {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nomeFabrica;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String caminhoArquivoVariaveis;

    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS)
    private List<VariavelAmbienteModulo> variaveisAmbiente;

    public ConfigModuloDetalhes(Class<? extends ItfFabConfigModulo> pConfig) {
        ConfigModulo configuracao = SBCore.getConfigModulo(pConfig);

        id = Long.valueOf(pConfig.toString().hashCode());
        nomeFabrica = configuracao.fabricaConfig.getSimpleName();
        if (nomeFabrica.startsWith("Fab")) {
            nomeFabrica = nomeFabrica.substring(3, nomeFabrica.length());
        }
        caminhoArquivoVariaveis = configuracao.getPatchArquivoConfig();
        variaveisAmbiente = new ArrayList<>();
        for (ItfFabConfigModulo variavel : configuracao.getFabricaConfig().getEnumConstants()) {

            VariavelAmbienteModulo variavelAmbiente = new VariavelAmbienteModulo(variavel);
            variavelAmbiente.setVariavelEmArquivoEncontrada((configuracao.getProppriedadesBasicas().contains(variavel.toString()) || configuracao.getProppriedadesBasicas().contains(ConfigModulo.getNomeCompleto(variavel))));
            variavelAmbiente.setVariavelEmVariavemDeAmbienteEncontrada(configuracao.isEncontradoEmVariavelDeAmbienteDoSistema(variavel));
            variavelAmbiente.setTipoDeclaracao(configuracao.getTipoDeclaracaoVariavelAmbiente(variavel).toString());
            variaveisAmbiente.add(variavelAmbiente);

        }

    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNomeFabrica() {
        return nomeFabrica;
    }

    public void setNomeFabrica(String nomeFabrica) {
        this.nomeFabrica = nomeFabrica;
    }

    public String getCaminhoArquivoVariaveis() {
        return caminhoArquivoVariaveis;
    }

    public void setCaminhoArquivoVariaveis(String caminhoArquivoVariaveis) {
        this.caminhoArquivoVariaveis = caminhoArquivoVariaveis;
    }

    public List<VariavelAmbienteModulo> getVariaveisAmbiente() {
        return variaveisAmbiente;
    }

    public void setVariaveisAmbiente(List<VariavelAmbienteModulo> variaveisAmbiente) {
        this.variaveisAmbiente = variaveisAmbiente;
    }

}
