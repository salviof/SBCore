/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.basico.FabTipoBeanSBGenerico;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
@InfoObjetoSB(plural = "Dados Estruturados", tags = {"Estrutura de Tabela"})
public class EstruturaDeEntidade extends ItemSimples implements ItfEstruturaDeEntidade {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    private List<String> listaEnum;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nomeEntidade;

    private List<EstruturaCampo> campos;

    private List<LigacaoMuitosParaUm> muitosParaUm;

    private List<LigacaoUmParaMuitos> umParaMuitos;

    private List<LigacaoMuitosParaMuitos> muitosParaMuitos;

    private List<String> tags;

    private String plural, icone;

    private FabTipoBeanSBGenerico tipoEntidade;

    private List<CalculoDeEntidade> calculos;

    private List<ListaDeEntidade> listas;

    private final Map<String, EstruturaCampo> mapaCampo;

    private String descricao;

    private boolean configurouLigacoes = false;

    @Override
    public List<CalculoDeEntidade> getCalculos() {
        return calculos;
    }

    @Override
    public void setCalculos(List<CalculoDeEntidade> calculos) {
        this.calculos = calculos;
    }

    @Override
    public List<ListaDeEntidade> getListas() {
        return listas;
    }

    @Override
    public void setListas(List<ListaDeEntidade> listas) {
        this.listas = listas;
    }

    @Override
    public List<LigacaoMuitosParaMuitos> getMuitosParaMuitos() {
        return muitosParaMuitos;
    }

    @Override
    public void setMuitosParaMuitos(List<LigacaoMuitosParaMuitos> muitosParaMuitos) {
        this.muitosParaMuitos = muitosParaMuitos;
    }

    public EstruturaDeEntidade() {
        campos = new ArrayList<>();
        muitosParaUm = new ArrayList<>();
        muitosParaMuitos = new ArrayList<>();
        umParaMuitos = new ArrayList<>();
        calculos = new ArrayList<>();
        listas = new ArrayList<>();
        mapaCampo = new HashMap<>();

    }

    @Override
    public void adicionarCampo(Field pCampo) {
        if (mapaCampo.get(pCampo.getName()) == null) {

            EstruturaCampo cp = new EstruturaCampo(pCampo, this);
            mapaCampo.put(cp.getNomeDeclarado(), cp);
            campos.add(cp);

        }

    }

    private void buildLigacoes() {
        if (!configurouLigacoes) {
            try {
                if (campos.isEmpty()) {
                    throw new UnsupportedOperationException("nenhum campo foi configurado na estrutura de entidade " + nomeEntidade);
                }
                for (EstruturaCampo cp : campos) {
                    switch (cp.getFabricaTipoAtributo()) {
                        case OBJETO_DE_UMA_LISTA:
                        case REG_USUARIO_ALTERACAO:
                        case REG_USUARIO_INSERCAO:

                            Class classeVinculada = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(cp.getClasseCampoDeclaradoOuTipoLista());
                            if (classeVinculada != null) {
                                muitosParaUm.add(new LigacaoMuitosParaUm(cp));
                            }

                            break;
                        case ENUM_FABRICA:
                            break;
                        case LISTA_OBJETOS_PUBLICOS:
                            break;
                        case LISTA_OBJETOS_PARTICULARES:
                            break;

                    }
                }
                configurouLigacoes = true;
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro constuindo ligações da estrutura de entidade" + nomeEntidade, t);
            }
        }
    }

    @Override
    public List<LigacaoMuitosParaUm> getMuitosParaUm() {

        buildLigacoes();
        return muitosParaUm;
    }

    @Override
    public EstruturaCampo getCampoByNomeDeclarado(String pNome
    ) {
        return mapaCampo.get(pNome);
    }

    @Override
    public String getNomeEntidade() {
        return nomeEntidade;

    }

    @Override
    public void setNomeEntidade(String nomeEntidade
    ) {
        this.nomeEntidade = nomeEntidade;
        id = nomeEntidade.hashCode();
    }

    @Override
    public FabTipoBeanSBGenerico getTipoEntidade() {
        return tipoEntidade;
    }

    @Override
    public void setTipoEntidade(FabTipoBeanSBGenerico tipoEntidade
    ) {
        this.tipoEntidade = tipoEntidade;
    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public void setTags(List<String> tags
    ) {
        this.tags = tags;
    }

    @Override
    public void adicionarTags(String pTag
    ) {

        String[] array;
        array = pTag.split(",");

        for (int i = 0; i < array.length; i++) {

            if (tags == null) {

                tags = new ArrayList();
                tags.add(array[i]);

            } else {

                tags.add(array[i]);

            }
        }
    }

    @Override
    public void adicionarEnum(String enums
    ) {
        String[] array;
        array = enums.split(",");

        for (int i = 0; i < array.length; i++) {
            if (listaEnum == null) {
                listaEnum = new ArrayList();
                for (ListaDeEntidade entidade : listas) {
                    listaEnum.add(entidade.getNomeEnum());
                }
            } else {
                listaEnum.add(array[i]);
            }
        }
    }

    @Override
    public String getIcone() {
        return icone;
    }

    @Override
    public void setIcone(String icone
    ) {
        this.icone = icone;
    }

    @Override
    public String getPlural() {
        return plural;
    }

    @Override
    public void setPlural(String plural
    ) {
        this.plural = plural;
    }

    /**
     * -> Utilize @see #adicionarCampo(Field pCampo) para adicionar um campo,
     * NAO utilize .add
     *
     * @return
     */
    @Override
    public List<EstruturaCampo> getCampos() {
        return campos;
    }

    @Override
    public void setCampos(List<EstruturaCampo> campos
    ) {
        this.campos = campos;
    }

    @Override
    public void setMuitosParaUm(List<LigacaoMuitosParaUm> muitosParaUm
    ) {
        this.muitosParaUm = muitosParaUm;
    }

    @Override
    public List<LigacaoUmParaMuitos> getUmParaMuitos() {
        return umParaMuitos;
    }

    @Override
    public void setUmParaMuitos(List<LigacaoUmParaMuitos> umParaMuitos
    ) {
        this.umParaMuitos = umParaMuitos;
    }

    @Override
    public List<String> getListaEnum() {
        return listaEnum;
    }

    @Override
    public void setListaEnum(List<String> listaEnum
    ) {
        this.listaEnum = listaEnum;
    }

    @Override
    public String getIconeDaClasse() {
        return icone;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao
    ) {
        this.descricao = descricao;
    }

    public List<EstruturaCampo> getCamposComValidadorLogico() {
        List<EstruturaCampo> camposComValidadorLogico = new ArrayList<>();
        getCampos().stream().filter(cp -> cp.isTemValidadorLogico()).forEach(camposComValidadorLogico::add);
        return camposComValidadorLogico;

    }

    public List<EstruturaCampo> getCamposComListaDinamica() {
        List<EstruturaCampo> camposComValidadorLogico = new ArrayList<>();
        getCampos().stream().filter(cp -> cp.isTemListaDinamica()).forEach(camposComValidadorLogico::add);
        return camposComValidadorLogico;

    }

    public List<EstruturaCampo> getCamposComValorLogico() {
        List<EstruturaCampo> campoValorLogico = new ArrayList<>();
        getCampos().stream().filter(cp -> cp.isTemValorLogico()).forEach(campoValorLogico::add);
        return campoValorLogico;

    }

    public boolean isTemCampoValidadoresLogicos() {
        try {
            return getCampos().stream().filter(cp -> cp.isTemValidadorLogico()).findFirst().isPresent();

        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isTemCampoValorLogico() {
        try {
            return getCampos().stream().filter(cp -> cp.isTemValorLogico()).findFirst().isPresent();

        } catch (Throwable t) {
            return false;
        }
    }

    public boolean isTemCampoListaDinamica() {
        try {
            return getCampos().stream().filter(cp -> cp.isTemListaDinamica()).findFirst().isPresent();

        } catch (Throwable t) {
            return false;
        }
    }

}
