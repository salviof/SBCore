/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.localizacao;

import br.org.coletivoJava.fw.api.erp.codigoPostal.br.ERPCodigoPostalBR;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeLocalizavel;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoUnidadeFederativa;

/**
 * ############################################################# <br/>
 * Esta Interface assina um dos serviços do núcleo SBCore
 * <br/>
 * Veja todos os serviços disponíveis digitando SBCore.getServico...
 * ############################################################# <br/>
 *
 * Serviço de gestão de CEP
 *
 *
 * @author salviof@gmail.com
 */
public interface CmoServicoLocalizacao {

    public List<ComoUnidadeFederativa> getUnidadesFederativas();

    public List<ComoCidade> gerarListaDeCidades(String pNomePesquisa, ComoUnidadeFederativa pUnidadeFederativa);

    public List<ComoBairro> gerarListaDeBairros(String pNomePesquisa, ComoCidade pCidade);

    public List<ComoCidade> gerarListaDeCidades(String pNomePesquisa, ComoUnidadeFederativa pUnidadeFederativa, String parametroEspecial);

    public List<ComoBairro> gerarListaDeBairros(String pNomePesquisa, ComoCidade pCidade, String parametroEspecial);

    public void configurarPosicionamento(ComoLocal pLocal);

    public boolean salvarFlexivel(ComoEntidadeLocalizavel pBeanLocalizava);

    public void configurarCep(ComoLocal pLocal);

    public void configurarEndereco(String cep, ComoLocal pLocal);

    public ComoBairro instanciarNovoBairo(String pBairro, ComoCidade pCidade);

    public default ERPCodigoPostalBR getImplementacaoPadraoApiCep() {
        return ERPCodigoPostalBR.API_FREE_REDUNTANTE;
    }
}
