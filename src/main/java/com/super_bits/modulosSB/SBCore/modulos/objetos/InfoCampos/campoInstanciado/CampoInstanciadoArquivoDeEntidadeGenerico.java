/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

import java.io.File;

/**
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoArquivoDeEntidadeGenerico implements ItfCampoInstArquivoEntidade {

    private final ItfCampoInstanciado campoInstanciado;
    private byte[] intputTemporario;
    private boolean contemMapaCaracteres;

    private String getCaminhoArquivo(ItfCampoInstanciado pCampo) {
        String caminhoArquivo = SBCore.getCentralDeArquivos().getEndrLocalArquivoItem((ItfBeanSimples) pCampo.getObjetoDoAtributo(), (String) pCampo.getValor(), pCampo.getNomeCamponaClasse());
        return caminhoArquivo;
    }

    public CampoInstanciadoArquivoDeEntidadeGenerico(ItfCampoInstanciado pCampoInstanciado) {
        this.campoInstanciado = pCampoInstanciado;
    }

    @Override
    public boolean uploadArquivo(String pNomeArquivo, byte[] pInputStream) {
        try {
            Object entidade = campoInstanciado.getObjetoDoAtributo();
            if (pNomeArquivo == null) {
                throw new UnsupportedOperationException("O nome do Arquivo não foi enviado, impossível realizar o upload ");
            }

            if (!(entidade instanceof ItfBeanSimples)) {
                throw new UnsupportedOperationException("Este campo não extende " + ItfBeanSimples.class.getSimpleName());
            }

            campoInstanciado.setValor(pNomeArquivo);
            ItfBeanSimples entidadeVinculada = (ItfBeanSimples) campoInstanciado.getObjetoDoAtributo();
            if (entidadeVinculada.getId() == 0) {
                intputTemporario = pInputStream;
                entidadeVinculada.uploadArquivoDeEntidade(campoInstanciado, pInputStream, pNomeArquivo);
                return true;
            } else {

                entidadeVinculada.uploadArquivoDeEntidade(campoInstanciado, pInputStream, pNomeArquivo);
                return true;
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

                if (!UtilSBCoreArquivos.copiarArquivos(caminhoArquivo, arquivoTemporario)) {
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
        if (getCampoInstanciado().getObjetoDoAtributo().getId() == 0) {
            return intputTemporario != null;
        }
        return new File(getCaminhoArquivo(campoInstanciado)).exists();
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
            File novoFile = new File(arquivo);
            novoFile.delete();
            getCampoInstanciado().setValor(null);
        } catch (Throwable t) {
            SBCore.enviarMensagemUsuario("Erro removendo arquivo", FabMensagens.ERRO);
        }

    }

}
