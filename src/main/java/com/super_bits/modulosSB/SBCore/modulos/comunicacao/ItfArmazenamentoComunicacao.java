/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;

/**
 *
 * @author SalvioF
 */
public interface ItfArmazenamentoComunicacao {

    public boolean registrarInicioComunicacao(ItfComunicacao pComunicacao);

    public boolean regsitrarRespostaComunicacao(String pCodigoComunicacao, ItfRespostaComunicacao pResposta);

    public boolean limparComunicacaoExpirada();

    public ItfComunicacao getComunicacaoByCodigoSelo(String pCodigoSelo);

    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoDestinatario(ItfUsuario pDestinatario);

    public List<ItfComunicacao> getComunicacoesAguardandoRespostaDoRemetente(ItfUsuario pRemetente);

}
