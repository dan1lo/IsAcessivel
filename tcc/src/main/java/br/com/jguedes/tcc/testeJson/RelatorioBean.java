package br.com.jguedes.tcc.testeJson;

import java.util.HashSet;
import java.util.Map;

import ases.PontoVerificacao;
import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;

public class RelatorioBean {

	private String linkEvalCode;

	private String url;

	private Profundidade profundidade;

	private PadraoAcessibilidade padraoAcessibilidade;

	private boolean mostraP1;

	private boolean mostraP2;

	private boolean mostraP3;

	private Integer errosPrioridade1;

	private Integer errosPrioridade2;

	private Integer errosPrioridade3;

	private Integer avisosPrioridade1;

	private Integer avisosPrioridade2;

	private Integer avisosPrioridade3;

	private HashSet<PontoVerificacao> listaErrosP1;

	private HashSet<PontoVerificacao> listaErrosP2;

	private HashSet<PontoVerificacao> listaErrosP3;

	private HashSet<PontoVerificacao> listaAvisosP1;

	private HashSet<PontoVerificacao> listaAvisosP2;

	private HashSet<PontoVerificacao> listaAvisosP3;

	private Map<String, String> conteudo;

	public RelatorioBean() {

	}

	public String getLinkEvalCode() {
		return linkEvalCode;
	}

	public void setLinkEvalCode(String linkEvalCode) {
		this.linkEvalCode = linkEvalCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Profundidade getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Profundidade profundidade) {
		this.profundidade = profundidade;
	}

	public PadraoAcessibilidade getPadraoAcessibilidade() {
		return padraoAcessibilidade;
	}

	public void setPadraoAcessibilidade(PadraoAcessibilidade padraoAcessibilidade) {
		this.padraoAcessibilidade = padraoAcessibilidade;
	}

	public boolean isMostraP1() {
		return mostraP1;
	}

	public void setMostraP1(boolean mostraP1) {
		this.mostraP1 = mostraP1;
	}

	public boolean isMostraP2() {
		return mostraP2;
	}

	public void setMostraP2(boolean mostraP2) {
		this.mostraP2 = mostraP2;
	}

	public boolean isMostraP3() {
		return mostraP3;
	}

	public void setMostraP3(boolean mostraP3) {
		this.mostraP3 = mostraP3;
	}

	public Integer getErrosPrioridade1() {
		return errosPrioridade1;
	}

	public void setErrosPrioridade1(Integer errosPrioridade1) {
		this.errosPrioridade1 = errosPrioridade1;
	}

	public Integer getErrosPrioridade2() {
		return errosPrioridade2;
	}

	public void setErrosPrioridade2(Integer errosPrioridade2) {
		this.errosPrioridade2 = errosPrioridade2;
	}

	public Integer getErrosPrioridade3() {
		return errosPrioridade3;
	}

	public void setErrosPrioridade3(Integer errosPrioridade3) {
		this.errosPrioridade3 = errosPrioridade3;
	}

	public Integer getAvisosPrioridade1() {
		return avisosPrioridade1;
	}

	public void setAvisosPrioridade1(Integer avisosPrioridade1) {
		this.avisosPrioridade1 = avisosPrioridade1;
	}

	public Integer getAvisosPrioridade2() {
		return avisosPrioridade2;
	}

	public void setAvisosPrioridade2(Integer avisosPrioridade2) {
		this.avisosPrioridade2 = avisosPrioridade2;
	}

	public Integer getAvisosPrioridade3() {
		return avisosPrioridade3;
	}

	public void setAvisosPrioridade3(Integer avisosPrioridade3) {
		this.avisosPrioridade3 = avisosPrioridade3;
	}

	public HashSet<PontoVerificacao> getListaErrosP1() {
		return listaErrosP1;
	}

	public void setListaErrosP1(HashSet<PontoVerificacao> listaErrosP1) {
		this.listaErrosP1 = listaErrosP1;
	}

	public HashSet<PontoVerificacao> getListaErrosP2() {
		return listaErrosP2;
	}

	public void setListaErrosP2(HashSet<PontoVerificacao> listaErrosP2) {
		this.listaErrosP2 = listaErrosP2;
	}

	public HashSet<PontoVerificacao> getListaErrosP3() {
		return listaErrosP3;
	}

	public void setListaErrosP3(HashSet<PontoVerificacao> listaErrosP3) {
		this.listaErrosP3 = listaErrosP3;
	}

	public HashSet<PontoVerificacao> getListaAvisosP1() {
		return listaAvisosP1;
	}

	public void setListaAvisosP1(HashSet<PontoVerificacao> listaAvisosP1) {
		this.listaAvisosP1 = listaAvisosP1;
	}

	public HashSet<PontoVerificacao> getListaAvisosP2() {
		return listaAvisosP2;
	}

	public void setListaAvisosP2(HashSet<PontoVerificacao> listaAvisosP2) {
		this.listaAvisosP2 = listaAvisosP2;
	}

	public HashSet<PontoVerificacao> getListaAvisosP3() {
		return listaAvisosP3;
	}

	public void setListaAvisosP3(HashSet<PontoVerificacao> listaAvisosP3) {
		this.listaAvisosP3 = listaAvisosP3;
	}

	public Map<String, String> getConteudo() {
		return conteudo;
	}

	public void setConteudo(Map<String, String> conteudo) {
		this.conteudo = conteudo;
	}
}
