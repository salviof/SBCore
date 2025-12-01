/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringSlugs;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.TipoRecurso;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoArquivoDeEntidadeGenerico implements ItfCampoInstArquivoEntidade {

    private final ItfCampoInstanciado campoInstanciado;
    private byte[] intputTemporario;
    private boolean contemMapaCaracteres;

    private String getCaminhoArquivo(ItfCampoInstanciado pCampo) {
        String caminhoArquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoItem((ComoEntidadeSimples) pCampo.getObjetoDoAtributo(), (String) pCampo.getValor(), pCampo.getNomeCamponaClasse());
        return caminhoArquivo;
    }

    public CampoInstanciadoArquivoDeEntidadeGenerico(ItfCampoInstanciado pCampoInstanciado) {
        this.campoInstanciado = pCampoInstanciado;
        configuracoesIniciais();

    }

    private void configuracoesIniciais() {
        ComoEntidadeSimples entidadeVinculada = (ComoEntidadeSimples) campoInstanciado.getObjetoDoAtributo();
        if (entidadeVinculada.getId() == null) {
            if (isExisteArquivo()) {
                excluirArquivo();
            }
        }
    }

    @Override
    public boolean uploadArquivo(String pNomeArquivo, byte[] pInputStream) {
        try {
            String nomeArquivoCompativel = UtilCRCStringSlugs.gerarSlugSimples(pNomeArquivo);
            Object entidade = campoInstanciado.getObjetoDoAtributo();
            if (pNomeArquivo == null) {
                throw new UnsupportedOperationException("O nome do Arquivo não foi enviado, impossível realizar o upload ");
            }

            if (!(entidade instanceof ComoEntidadeSimples)) {
                throw new UnsupportedOperationException("Este campo não extende " + ComoEntidadeSimples.class.getSimpleName());
            }

            //campoInstanciado.setValor(pNomeArquivo);
            ComoEntidadeSimples entidadeVinculada = (ComoEntidadeSimples) campoInstanciado.getObjetoDoAtributo();
            if (entidadeVinculada.getId() == null) {
                intputTemporario = pInputStream;
                campoInstanciado.setValor(nomeArquivoCompativel);

                return entidadeVinculada.uploadArquivoDeEntidade(campoInstanciado, pInputStream, pNomeArquivo);
            } else {

                if (entidadeVinculada.uploadArquivoDeEntidade(campoInstanciado, pInputStream, pNomeArquivo)) {

                    campoInstanciado.setValor(nomeArquivoCompativel);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando upload de arquivo em " + campoInstanciado.getNome(), t);
            return false;
        }
    }

    @Override
    public void prepararArquivo() {
        try {
            if (isContemMapaCaracteres()) {
                String caminhoArquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campoInstanciado);
                String arquivoTemporario = SBCore.getCentralDeArquivos().getEndrLocalTemporarioArquivoCampoInstanciado(campoInstanciado);

                new File(SBCore.getControleDeSessao().getSessaoAtual().getPastaTempDeSessao()).mkdirs();

                if (!UtilCRCArquivos.copiarArquivos(caminhoArquivo, arquivoTemporario)) {
                    SBCore.enviarMensagemUsuario("Ouve um erro ao preparar o arquivo", FabMensagens.ERRO);
                }
            } else {
                // o arquivo não precisa ser preparado (lançar erro??)
            }
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Ouve um erro ao preparar o arquivo", FabMensagens.ERRO);
        }

    }

    @Override
    public boolean isUsuarioLogadoTemPermissaoBaixarArquivo() {
        return SBCore.getCentralDeArquivos().getCentralPermissao().isUsuarioLogadoPodeBaixar(this);

    }

    @Override
    public boolean isUsuarioLogadoTemPermissaoCriarArquivo() {
        return SBCore.getCentralDeArquivos().getCentralPermissao().isUsuarioLogadoPodeCriar(this);
    }

    @Override
    public boolean isUsuarioLogadoTemAlterarArquivo() {
        return SBCore.getCentralDeArquivos().getCentralPermissao().isUsuarioLogadoPodeAlterar(this);
    }

    @Override
    public boolean isExisteArquivo() {
        switch (getCampoInstanciado().getFabricaTipoAtributo()) {
            case ARQUIVO_DE_ENTIDADE:
                if (getCampoInstanciado().getObjetoDoAtributo().getId() == null) {
                    return intputTemporario != null;
                } else {
                    return SBCore.getServicoArquivosDeEntidade().isExisteArquivo(campoInstanciado);
                }

            default:
                return SBCore.getServicoArquivosDeEntidade().isExisteArquivo(campoInstanciado);
        }

    }

    @Override
    public String getCaminhoArquivoLocal() {
        return getCaminhoArquivo(campoInstanciado);
    }

    @Override
    public ItfCampoInstanciado getCampoInstanciado() {
        return campoInstanciado;
    }

    @Override
    public byte[] getIntputTemporario() {
        if (intputTemporario == null) {
            String caminhoTemp = getCaminhoArquivoLocal();
            try {
                boolean arquivoRemoto = caminhoTemp.startsWith("http");
                //  intputTemporario = UtilCRCBytes.gerarBytesPorArquivo(new File(getCaminhoArquivoLocal()));
            } catch (Exception ex) {
                Logger.getLogger(CampoInstanciadoArquivoDeEntidadeGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return intputTemporario;
    }

    @Override
    public boolean isUsuarioLogadoTemPermissaoExcluirArquivo() {
        return isUsuarioLogadoTemAlterarArquivo();
    }

    @Override
    public boolean isPrecisaPermissao() {
        return false;
    }

    public boolean isContemMapaCaracteres() {
        return contemMapaCaracteres;
    }

    @Override
    public String getLinkAbrirArquivo() {
        return SBCore.getCentralDeArquivos().getEndrRemotoArquivoCampoInstanciadoAbrir(campoInstanciado);
    }

    @Override
    public String getLinkBaixarArquivo() {
        return SBCore.getCentralDeArquivos().getEndrRemotoArquivoCampoInstanciadoBaixar(campoInstanciado);

    }

    @Override
    public void excluirArquivo() {
        try {
            String arquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoCampoInstanciado(campoInstanciado);
            if (arquivo.startsWith("http")) {
                SBCore.getServicoArquivosDeEntidade().excluirArquivo(campoInstanciado);
            } else {
                File novoFile = new File(arquivo);
                novoFile.delete();
                getCampoInstanciado().setValor(null);
            }
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Erro removendo arquivo", FabMensagens.ERRO);
        }

    }

    private String hashArquivoEntidade;

    public String getHashDeArquivoDeEntidadeRegistrado() {
        if (hashArquivoEntidade == null) {
            hashArquivoEntidade = SBCore.getServicoArquivosDeEntidade().getHashArquivoDeEntidadeRegistrado(campoInstanciado);
        }
        return hashArquivoEntidade;
    }

    @Override
    public String getIconeTipoArquivo() {
        if (getLinkAbrirArquivo() == null) {
            return "fa fa-file-text-o";
        }
        FabTipoArquivoConhecido tipoArquivo = FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(UtilCRCStringNomeArquivosEDiretorios.getExtencaoNomeArquivo(getLinkAbrirArquivo()));
        if (tipoArquivo == null) {
            return "fa fa-file-text-o";
        }

        switch (tipoArquivo) {
            case IMAGEM_WEB:
            case IMAGE_REPRESENTATIVA_ENTIDADE_PEQUENO:
            case IMAGE_REPRESENTATIVA_ENTIDADE_MEDIO:
            case IMAGE_REPRESENTATIVA_ENTIDADE_GRANDE:
                return "fa fa-file-image-o";

            case VIDEO:
                return "fa fa-file-video-o ";

            case DOCUMENTO_WORD_XDOC2007:
                return "fa fa-file-word-o";

            case FOLHA_DE_ESTILO_CSS:
            case JAVASCRIPT:
                return "fa fa-file-code-o";
            case DOCUMENTO_PDF:
                return "fa fa-file-pdf-o";

            case ARQUIVO_TEXTO_SIMPLES:
                return "fa fa-file-text-o";

            default:
                return "fa fa-file-text-o";

        }
    }

    @Override
    public boolean uploadArquivoRemoto(String pNomeArquivo, String pLinkRemoto) {
        return uploadArquivo(pNomeArquivo, UtilCRCBytes.gerarBytePorUrltream(pLinkRemoto));
    }

    @Override
    public boolean uploadArquivoLocal(String pNomeArquivo, String pLinkCAminhoCompleto) {
        try {
            return uploadArquivo(pNomeArquivo, UtilCRCBytes.gerarBytesPorArquivo(new File(pLinkCAminhoCompleto)));
        } catch (Exception ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha lendo arquivo local" + pLinkCAminhoCompleto, ex);
            return false;
        }
    }

}
