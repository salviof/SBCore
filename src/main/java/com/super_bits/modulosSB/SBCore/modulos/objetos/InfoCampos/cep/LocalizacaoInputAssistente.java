/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.TipoOrganizacaoDadosEndereco;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCObjetoSB;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringComparador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;

import java.util.ArrayList;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfAssistenteDeLocalizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoUnidadeFederativa;

/**
 *
 * @author SalvioF
 */
public class LocalizacaoInputAssistente implements ItfAssistenteDeLocalizacao {

    private final TipoOrganizacaoDadosEndereco tipoOrganizacao;

    private final ComoEntidadeSimples entidadePai;

    private List<ComoUnidadeFederativa> unidadesFederativasDisponiveis;
    private List<ComoCidade> cidadesDisponiveis;
    private List<ComoBairro> bairrosDisponiveis;

    private ComoUnidadeFederativa unidadeFederativaTemporaria;
    private ComoCidade cidadeTemporaria;
    private ComoBairro bairroTemporario;

    private boolean permitirNovaCidade;
    private boolean permitriNovoBairro;
    private boolean permitirNovoCep;
    private final String identificacaoMapa;

    private final boolean temCep;

    private boolean pesquisaSucessoCidade;
    private boolean pesquisaSucessoBairro;
    private boolean pesquisaSucessoUnidadeFederativa;

    public LocalizacaoInputAssistente(ItfCampoInstanciado pCampoGerador, TipoOrganizacaoDadosEndereco pTipoOrganizacao) {
        tipoOrganizacao = pTipoOrganizacao;
        identificacaoMapa = tipoOrganizacao.getIdentificacaoMapaAssisteBean(pCampoGerador);
        if (identificacaoMapa == null) {
            throw new UnsupportedOperationException("Imnpossível determinar a idetetificação do assistente de localizacao");
        }
        //       entidadeArmazenamento = pTipoOrganizacao.getBeanDeArmazenamento(pCampoGerador);
        entidadePai = pTipoOrganizacao.getBeanPai(pCampoGerador);

        switch (pTipoOrganizacao) {
            case LOCALIZACAO_POSTAVEL:
            case LOCALIZACAO_SIMPLES:
            case LOCALIZACAO_SIMPLES_COM_CEP_EM_ENTIDADE:

                break;

        }

        switch (pTipoOrganizacao) {
            case LOCALIZACAO_POSTAVEL:
                temCep = true;
                break;
            case LOCALIZACAO_SIMPLES:
                temCep = false;
                break;
            case DINAMICO:

                ItfCampoInstanciado campoCEp = null;
                if (pCampoGerador.isTemCampoAnotado(FabTipoAtributoObjeto.LCCEP)) {
                    campoCEp = pCampoGerador.getObjetoDoAtributo().
                            getCampoInstanciadoByNomeOuAnotacao(
                                    FabTipoAtributoObjeto.LCCEP.name());
                }
                if (pCampoGerador.getObjetoDoAtributo().isTemCampoAnotado(FabTipoAtributoObjeto.LCCEP)) {
                    campoCEp = pCampoGerador.getObjetoDoAtributo().getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LCCEP);
                    temCep = true;
                } else {

                    if (campoCEp == null || campoCEp.equals(new CampoNaoImplementado())) {
                        temCep = false;
                    } else {
                        temCep = true;
                    }
                }
                break;
            case LOCALIZACAO_SIMPLES_COM_CEP_EM_ENTIDADE:
                temCep = true;
                break;
            case SEM_ENDERECO:
                temCep = false;
                break;
            default:
                throw new AssertionError(pTipoOrganizacao.name());
        }
        if (temCep) {
            if (UtilCRCStringValidador.isNAO_NuloNemBranco(getCep())) {
                if (UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getUnidadeFederativa())) {
                    pesquisaSucessoUnidadeFederativa = true;
                    if (UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getCidade())) {
                        pesquisaSucessoCidade = true;
                        if (UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getBairro())) {
                            pesquisaSucessoBairro = true;
                        }
                    }

                }
            }
        }
    }

    private boolean isTemCep() {
        return temCep;
    }

    @Override
    public List<ComoCidade> metodoAutoCompleteCidade(String pCidadeTXT) {
        List<ComoCidade> cidadesEncontradas = new ArrayList<>();
        if (getUnidadeFederativa() == null) {
            SBCore.enviarMensagemUsuario("Selecione o Estado para listar a cidade", FabMensagens.ALERTA);
            setCidade(null);
            return cidadesEncontradas;
        }

        SBCore.getCentralDeLocalizacao().gerarListaDeCidades(pCidadeTXT, getUnidadeFederativa()).stream().forEach(
                (cidadesEncontradas::add));

        return cidadesEncontradas;
    }

    @Override
    public List<ComoUnidadeFederativa> metodoAutoCompleteEstado(String pNomeEstado) {
        if (unidadesFederativasDisponiveis == null) {
            unidadesFederativasDisponiveis = SBCore.getCentralDeLocalizacao().getUnidadesFederativas();
        }
        List<ComoUnidadeFederativa> resultadoPesquisa = new ArrayList();

        List<ComoUnidadeFederativa> todos = SBCore.getCentralDeLocalizacao().getUnidadesFederativas();
        if (pNomeEstado.length() == 2) {
            todos.stream().filter(uf -> (uf.getSigla().toLowerCase().equals(pNomeEstado.toLowerCase()))).forEach(resultadoPesquisa::add);
        } else {
            resultadoPesquisa = UtilCRCListasObjeto.filtrarOrdenandoMaisParecidos(SBCore.getCentralDeLocalizacao().getUnidadesFederativas(), pNomeEstado, 1);
        }

        return resultadoPesquisa;
    }

    @Override
    public List<ComoBairro> metodoAutoCompleteBairro(String pCidadeTXT) {
        return SBCore.getCentralDeLocalizacao().gerarListaDeBairros(pCidadeTXT, cidadeTemporaria);
    }

    @Override
    public ComoBairro getBairro() {
        try {
            if (bairroTemporario == null) {
                if (isInstanciaBairroCriada()) {
                    if (getCampoInstBairro().getValor() != null) {
                        ComoBairro bairro = (ComoBairro) getCampoInstBairro().getValor();
                        if (UtilCRCStringValidador.isNAO_NuloNemBranco(bairro.getNome())) {
                            bairroTemporario = bairro;
                        }
                    }
                }
            }
            ItfCampoInstanciado cpBairro = getCampoInstBairro();
            if (cpBairro == null) {
                return null;
            } else {

                return (ComoBairro) cpBairro.getValor();
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo bairro do endereço", t);
            return null;
        }

    }

    @Override
    public String getCep() {
        if (isTemCep()) {
            return (String) getCampoInstCep().getValor();
        } else {
            return null;
        }

    }

    @Override
    public void setCep(String pcep) {
        if (isTemCep()) {

            if (isCepsnulo() || !getCep().equals(pcep)) {
                getCampoInstCep().setValor(pcep);
                if (atualizarEnderecoPorCep()) {
                    getBairro().setCidade(getCidade());
                    getCidade().setUnidadeFederativa(getUnidadeFederativa());
                    bairroTemporario = getBairro();
                    cidadeTemporaria = getCidade();
                    unidadeFederativaTemporaria = getUnidadeFederativa();

                }
            }

        }
    }

    @Override
    public boolean atualizarEnderecoPorCep() {

        switch (tipoOrganizacao) {
            case LOCALIZACAO_POSTAVEL:
                //SBCore.getServicoLocalizacao().configurarCep(null);
                boolean localizouCEP = SBCore.getServicoLocalizacao().getImplementacaoPadraoApiCep().
                        getImplementacaoDoContexto().configuraEndereco(getCep(), getLocal());

                if (localizouCEP) {
                    pesquisaSucessoBairro = UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getBairro());
                    pesquisaSucessoCidade = UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getCidade());
                    pesquisaSucessoUnidadeFederativa = UtilCRCObjetoSB.isEntidadeSimplesExistETemNome(getUnidadeFederativa());
                } else {
                    pesquisaSucessoBairro = false;
                    pesquisaSucessoCidade = false;
                    pesquisaSucessoUnidadeFederativa = false;
                }
                return localizouCEP;
            case LOCALIZACAO_SIMPLES:
                break;
            case DINAMICO:
                break;
            case LOCALIZACAO_SIMPLES_COM_CEP_EM_ENTIDADE:
                break;
            case SEM_ENDERECO:
                break;
            default:
                throw new AssertionError(tipoOrganizacao.name());

        }
        return false;

    }

    @Override
    public ComoCidade getCidade() {

        if (cidadeTemporaria == null) {
            if (isInstanciaCidadeCriada()) {
                if (getCampoInstCidade().getValor() != null) {
                    ComoCidade cidade = (ComoCidade) getCampoInstCidade().getValor();

                    if (UtilCRCStringValidador.isNAO_NuloNemBranco(cidade.getNome())) {
                        cidadeTemporaria = cidade;
                    }
                }
            }
        }
        ItfCampoInstanciado campoinst = getCampoInstCidade();
        if (campoinst == null) {
            return null;
        }
        return (ComoCidade) campoinst.getValor();
    }

    @Override
    public void setBairro(ComoBairro pBairro) {

        try {
            if (pBairro == null) {
                //Verificação nescessária Contornando bug do primefaces, que envia nulo ao submeter o formulário com autocomplete em certos casos
                // utilize o metodo limpar unidade federativa
            } else {

                if (pBairro.getCidade() == null) {
                    throw new UnsupportedOperationException("A cidade do bairro é nula");

                }
                if (pBairro.getCidade().getUnidadeFederativa() == null) {
                    throw new UnsupportedOperationException("A unidade federativa do bairro é nulo");
                }
                bairroTemporario = pBairro;
                unidadeFederativaTemporaria = pBairro.getCidade().getUnidadeFederativa();
                cidadeTemporaria = pBairro.getCidade();
                if (isInstanciaBairroCriada()) {
                    getCampoInstBairro().setValor(pBairro);

                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Configurando bairro selecionado", t);
        }
    }

    @Override
    public void setCidade(ComoCidade pCidade) {
        //Verificação nescessária Contornando bug do primefaces, que envia nulo ao submeter o formulário com autocomplete em certos casos
        // utilize o metodo limpar unidade federativa
        if (pCidade != null) {

            if (bairroTemporario != null) {

                if (pCidade != getBairro().getCidade()) {

                    limparBairro();

                }

            }

            if (pCidade == null) {
                limparCidade();
            } else {
                cidadeTemporaria = pCidade;

                if (isInstanciaCidadeCriada()) {
                    getCampoInstCidade().setValor(pCidade);
                }
            }
        }

    }

    @Override
    public void setUnidadeFederativa(ComoUnidadeFederativa pUnidadeFerativa) {
        //Verificação nescessária Contornando bug do primefaces, que envia nulo ao submeter o formulário em certos casos
        // utilize o metodo limpar unidade federativa
        if (pUnidadeFerativa != null) {
            if (getCidade() != null) {
                if (getCidade().getUnidadeFederativa() != null) {

                    if (!getCidade().getUnidadeFederativa().equals(pUnidadeFerativa)) {
                        limparCidade();
                    }
                }

            }

            if (pUnidadeFerativa == null) {
                limparEstado();
            } else {
                unidadeFederativaTemporaria = pUnidadeFerativa;
                if (isInstanciaUnidadeFederativaCriada()) {
                    getCampoInstUnidadeFederativa().setValor(pUnidadeFerativa);
                }
            }
        }
    }

    @Override
    public ComoUnidadeFederativa getUnidadeFederativa() {

        if (isInstanciaUnidadeFederativaCriada()) {
            if (getCampoInstUnidadeFederativa().getValor() != null) {

                if (UtilCRCStringValidador.isNAO_NuloNemBranco(((ComoUnidadeFederativa) getCampoInstUnidadeFederativa().getValor()).getSigla())) {
                    unidadeFederativaTemporaria = (ComoUnidadeFederativa) getCampoInstUnidadeFederativa().getValor();
                }
            }
        }

        return unidadeFederativaTemporaria;

    }

    @Override
    public String getNomeLocal() {
        return (String) getCampoInstNomeLocal().getValor();
    }

    @Override
    public void setLogradouro(String pLogradouro) {
        getCampoInstLogradouro().setValor(pLogradouro);
    }

    @Override
    public void setNomeLocal(String pLocal) {
        getCampoInstNomeLocal().setValor(pLocal);
    }

    @Override
    public boolean isBairroNulo() {
        return ((getCampoInstBairro() == null) || (getCampoInstBairro().isCampoNaoInstanciado()));

    }

    @Override
    public boolean isCidadeNula() {
        return getCidade() == null;
    }

    @Override
    public boolean isUnidadeFederativaEstadoNulo() {
        return getUnidadeFederativa() == null;
    }

    @Override
    public boolean isPaisnulo() {
        return false;
    }

    @Override
    public boolean isCepsnulo() {
        return UtilCRCStringValidador.isNuloOuEmbranco(getCep());
    }

    @Override
    public boolean isColetarBairroAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.BAIRRO;
    }

    @Override
    public boolean isColetarEstadoAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.ESTADO;
    }

    @Override
    public boolean isColetarCidadeAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.CIDADE;
    }

    @Override
    public boolean isColetarCepAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.CEP;
    }

    @Override
    public boolean isColetarLogradouroAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.ENDERECO;
    }

    @Override
    public boolean isColetarNomeELogradouroAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.ENDERECO;
    }

    @Override
    public boolean isColetarComplementoAgora() {
        return getColetaMomento() == COLETA_ENDERECO_DO_MOMENTO.ENDERECO;
    }

    private boolean isInstanciaBairroCriada() {
        if (getCampoInstBairro() != null && !getCampoInstBairro().isCampoNaoInstanciado()) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isInstanciaComplementoCriada() {
        if (getCampoInstComplemento() != null && !getCampoInstComplemento().isCampoNaoInstanciado()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInstanciaLogradouroCriada() {
        if (getCampoInstLogradouro() != null && !getCampoInstLogradouro().isCampoNaoInstanciado()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInstanciaUnidadeFederativaCriada() {

        return getCampoInstUnidadeFederativa() != null && !getCampoInstUnidadeFederativa().isCampoNaoInstanciado();
    }

    private boolean isInstanciaCidadeCriada() {
        return getCampoInstCidade() != null && !getCampoInstCidade().isCampoNaoInstanciado();
    }

    @Override
    public void limparEstado() {
        try {
            unidadeFederativaTemporaria = null;
            bairroTemporario = null;
            cidadeTemporaria = null;
            if (isInstanciaUnidadeFederativaCriada()) {
                getCampoInstUnidadeFederativa().setValor(getCampoInstUnidadeFederativa().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
            if (isInstanciaCidadeCriada()) {
                getCampoInstCidade().setValor(getCampoInstCidade().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
            if (isInstanciaBairroCriada()) {
                getCampoInstBairro().setValor(getCampoInstBairro().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro o limpar estado do endereço!", t);
        }

    }

    @Override
    public void limparCidade() {
        try {
            bairroTemporario = null;
            cidadeTemporaria = null;

            if (isInstanciaCidadeCriada()) {
                getCampoInstCidade().setValor(getCampoInstCidade().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
            if (isInstanciaBairroCriada()) {
                getCampoInstBairro().setValor(getCampoInstBairro().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro o limpar cidade do endereço!", t);
        }

    }

    @Override
    public void limparBairro() {
        try {
            bairroTemporario = null;
            if (isInstanciaCidadeCriada()) {
                getCampoInstBairro().setValor(getCampoInstBairro().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro o limpar bairro do endereço!", t);
        }

    }

    @Override

    public void limparCep() {
        try {
            unidadeFederativaTemporaria = null;
            bairroTemporario = null;
            cidadeTemporaria = null;
            if (isInstanciaUnidadeFederativaCriada()) {
                getCampoInstUnidadeFederativa().setValor(getCampoInstUnidadeFederativa().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
            if (isInstanciaCidadeCriada()) {
                getCampoInstCidade().setValor(getCampoInstCep().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
            if (isInstanciaBairroCriada()) {
                getCampoInstBairro().setValor(getCampoInstBairro().getPropriedadesRefexao().getClasseDeclaracaoAtributo().newInstance());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro o limpar CEP do endereço!", t);
        }

    }

    @Override
    public boolean isEstadoPreenchidoECidadeNula() {

        return unidadeFederativaTemporaria != null && cidadeTemporaria == null;

    }

    @Override
    public boolean isCidadePreenchidaEBairroNulo() {
        return cidadeTemporaria != null && bairroTemporario == null;
    }

    @Override
    public boolean isPodeColetarBairro() {
        return cidadeTemporaria != null;
    }

    @Override
    public boolean isPodeColetarCidade() {
        return unidadeFederativaTemporaria != null;

    }

    @Override
    public boolean isPodeColetarDescricaoLogradouro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLogradouro() {
        if (!isInstanciaLogradouroCriada()) {
            return null;
        }
        return (String) getCampoInstLogradouro().getValor();
    }

    @Override
    public void setComplemento(String pComplemento) {
        getCampoInstComplemento().setValor(pComplemento);
    }

    @Override
    public String getComplemento() {
        if (!isInstanciaComplementoCriada()) {
            return null;
        }
        return (String) getCampoInstComplemento().getValor();
    }

    public enum COLETA_ENDERECO_DO_MOMENTO {

        CEP,
        ESTADO,
        CIDADE,
        BAIRRO,
        ENDERECO
    }

    private COLETA_ENDERECO_DO_MOMENTO getColetaMomento() {

        if (isTemCep()) {
            if (getCep() == null || getCep().isEmpty()) {
                return COLETA_ENDERECO_DO_MOMENTO.CEP;
            }
        }

        if (isUnidadeFederativaEstadoNulo()) {
            return COLETA_ENDERECO_DO_MOMENTO.ESTADO;
        }
        if (isCidadeNula()) {
            return COLETA_ENDERECO_DO_MOMENTO.CIDADE;
        }
        if (isBairroNulo()) {
            return COLETA_ENDERECO_DO_MOMENTO.BAIRRO;
        }
        return COLETA_ENDERECO_DO_MOMENTO.ENDERECO;

    }

    @Override
    public ComoLocal getLocal() {

        try {
            switch (tipoOrganizacao) {
                case LOCALIZACAO_POSTAVEL:

                case LOCALIZACAO_SIMPLES:
                case LOCALIZACAO_SIMPLES_COM_CEP_EM_ENTIDADE:
                    return (ComoLocal) tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_LOCALIZACAO, identificacaoMapa).getValor();
                //    return (ComoLocal) entidadeArmazenamento;
                case DINAMICO:
                    throw new UnsupportedOperationException("Um campo de endereço dinamico não possui um objeto de localização vinculado");

                case SEM_ENDERECO:
                    throw new UnsupportedOperationException("Este bean não parece ter relação com registro de localizações");

                default:
                    throw new AssertionError(tipoOrganizacao.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo local vinculado ao " + LocalizacaoInputAssistente.class.getSimpleName(), t);

        }
        return null;
    }

    public ItfCampoInstanciado getCampoInstLocal() {
        try {
            switch (tipoOrganizacao) {
                case LOCALIZACAO_POSTAVEL:

                case LOCALIZACAO_SIMPLES:
                case LOCALIZACAO_SIMPLES_COM_CEP_EM_ENTIDADE:
                    return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_LOCALIZACAO, identificacaoMapa);

                default:
                    return null;

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo local vinculado ao " + LocalizacaoInputAssistente.class.getSimpleName(), t);
            return null;
        }

    }

    public ItfCampoInstanciado getCampoInstCep() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LCCEP, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstUnidadeFederativa() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_UNIDADE_FEDERATIVA, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstCidade() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_CIDADE, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstNomeLocal() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.NOME, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstBairro() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_BAIRRO, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstLogradouro() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_LOGRADOURO, identificacaoMapa);
    }

    public ItfCampoInstanciado getCampoInstComplemento() {
        return tipoOrganizacao.getCampoInstanciadoPorTipo(entidadePai, FabTipoAtributoObjeto.LC_COMPLEMENTO_E_NUMERO, identificacaoMapa);
    }

    public TipoOrganizacaoDadosEndereco getTipoOrganizacao() {
        return tipoOrganizacao;
    }

    public String getIdentificacaoMapa() {
        return identificacaoMapa;
    }

    @Override
    public boolean isPesquisaSucessoCidade() {
        return pesquisaSucessoCidade;
    }

    @Override
    public boolean isPesquisaSucessoBairro() {
        return pesquisaSucessoBairro;
    }

    @Override
    public boolean isPesquisaSucessoUnidadeFederativa() {
        return pesquisaSucessoUnidadeFederativa;
    }

    @Override
    public boolean isCepEncontradoObrigatorio() {
        if (getCampoInstLocal() == null) {
            return false;
        } else {
            return !getCampoInstLocal().isPermitirCadastroManualEndereco();
        }
    }

    public void adicionarListaOpcoesBairro(List<ComoBairro> bairros) {
        getCampoInstBairro().getComoCampoSeltorItem().getSeletor().getOrigem().addAll(bairros);
    }

}
