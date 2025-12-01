/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilCRCArquivoTexto;
import java.io.File;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class MapaSubstituicaoArquivo extends MapaSubstituicao {

    protected final FabTipoArquivoConhecido tipoArquivo;
    protected final File arquivo;

    public MapaSubstituicaoArquivo(File pArquivo) {
        adicionarPalavraChave("nomeArquivo", UtilCRCStringNomeArquivosEDiretorios.getNomeArquivo(pArquivo.getName()));
        arquivo = pArquivo;
        tipoArquivo = FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(pArquivo.getName());
    }

    public MapaSubstituicaoArquivo(String pCaminho) {
        this(new File(pCaminho));
    }

    public void substituirEmArquivo() {

        switch (tipoArquivo) {
            case IMAGEM_WEB:
                break;
            case VIDEO:

                break;
            case DOCUMENTO_WORD_XDOC2007:
                throw new UnsupportedOperationException("Para substituir em documentos office importe a dependencia utilitária editorArquivos e utilize o MapaSubstituicaoOffice ");

            case DOCUMENTO_PDF:
                break;
            case ARQUIVO_TEXTO_SIMPLES:
                List<String> conteudo = UTilSBCoreInputs.getStringsByArquivoLocal(arquivo.getAbsolutePath());
                int i = 0;
                for (String linha : conteudo) {
                    conteudo.set(i, substituirEmString(linha));
                }
                UtilCRCArquivoTexto.escreveLinhasEmNovoArquivo(arquivo.getAbsolutePath(), conteudo);

                break;
            default:
                throw new AssertionError(tipoArquivo.name());

        }

    }

}
