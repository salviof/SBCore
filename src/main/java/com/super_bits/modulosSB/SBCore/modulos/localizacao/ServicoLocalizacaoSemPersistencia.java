/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep.ItemCidade;
import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoUnidadeFederativa;

/**
 *
 * @author desenvolvedor
 */
public class ServicoLocalizacaoSemPersistencia implements CmoServicoLocalizacao {

    @Override
    public List<ComoUnidadeFederativa> getUnidadesFederativas() {
        return (List) FabUnidadesFederativasSemPersistencia.getTodos();
    }

    @Override
    public List<ComoCidade> gerarListaDeCidades(String pNomePesquisa, ComoUnidadeFederativa pUnidadeFederativa) {
        List<ItemCidade> cidadesEncontradas = new ArrayList<>();
        pUnidadeFederativa.getCidades().stream().filter((cidade) -> (cidade.getNome().contains(pNomePesquisa))).forEach((cidade) -> {
            cidadesEncontradas.add((ItemCidade) cidade);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ComoBairro> gerarListaDeBairros(String pNomePesquisa, ComoCidade pCidade) {

        List<ComoBairro> cidadesEncontradas = new ArrayList<>();
        pCidade.getBairros().stream().filter((bairro) -> (bairro.getNome().contains(pNomePesquisa))).forEach((bairro) -> {
            cidadesEncontradas.add(bairro);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ComoCidade> gerarListaDeCidades(String pNomePesquisa, ComoUnidadeFederativa pUnidadeFederativa, String parametroEspecial) {
        List<ComoCidade> cidadesEncontradas = new ArrayList<>();
        pUnidadeFederativa.getCidades().stream().filter((cidade) -> (cidade.getNome().contains(pNomePesquisa))).forEach((cidade) -> {
            cidadesEncontradas.add(cidade);
        });
        return (List) cidadesEncontradas;

    }

    @Override
    public List<ComoBairro> gerarListaDeBairros(String pNomePesquisa, ComoCidade pCidade, String parametroEspecial) {
        List<ComoBairro> bairrosEncontrados = SBCore.getCentralDeLocalizacao().gerarListaDeBairros(pNomePesquisa, pCidade);

        pCidade.getBairros().stream().filter((bairro) -> (bairro.getNome().contains(pNomePesquisa))).forEach((bairro) -> {
            bairrosEncontrados.add(bairro);
        });
        return (List) bairrosEncontrados;
    }

    @Override
    public void configurarPosicionamento(ComoLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarCep(ComoLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configurarEndereco(String cep, ComoLocal pLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean salvarFlexivel(ComoEntidadeLocalizavel pBeanLocalizava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComoBairro instanciarNovoBairo(String pBairro, ComoCidade pCidade) {
        ItemBairro bairro = new ItemBairro();
        bairro.setCidade(pCidade);
        bairro.setNome(pBairro);
        bairro.configIDPeloNome();
        return bairro;
    }

}
