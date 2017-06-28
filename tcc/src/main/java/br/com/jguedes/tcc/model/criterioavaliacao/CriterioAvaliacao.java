package br.com.jguedes.tcc.model.criterioavaliacao;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CriterioAvaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2886243597535908488L;
	@XmlElement
	private Profundidade profundidade;

	@XmlElement
	private PadraoAcessibilidade padraoAcessibilidade;

	@XmlElement
	private boolean prioridade1;

	@XmlElement
	private boolean prioridade2;

	@XmlElement
	private boolean prioridade3;

	@XmlElement
	private String url;

	public CriterioAvaliacao() {
		setPadraoAcessibilidade(PadraoAcessibilidade.EMAG);
		setProfundidade(Profundidade.NIVEL1);
		setPrioridade1(true);
		setPrioridade2(true);
		setPrioridade3(true);
		// setUrl("http://dados.gov.br/");
		setUrl("http://www.fatecpe.com.br/");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PadraoAcessibilidade getPadraoAcessibilidade() {
		return padraoAcessibilidade;
	}

	public void setPadraoAcessibilidade(PadraoAcessibilidade padraoAcessibilidade) {
		this.padraoAcessibilidade = padraoAcessibilidade;
	}

	public Profundidade getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Profundidade profundidade) {
		this.profundidade = profundidade;
	}

	public boolean isPrioridade1() {
		return prioridade1;
	}

	public void setPrioridade1(boolean prioridade1) {
		this.prioridade1 = prioridade1;
	}

	public boolean isPrioridade2() {
		return prioridade2;
	}

	public void setPrioridade2(boolean prioridade2) {
		this.prioridade2 = prioridade2;
	}

	public boolean isPrioridade3() {
		return prioridade3;
	}

	public void setPrioridade3(boolean prioridade3) {
		this.prioridade3 = prioridade3;
	}

	public boolean profundidadeEstaAdequada(String url) {

		if (this.profundidade == Profundidade.SITE || this.profundidade == Profundidade.DIRETORIO_LOCAL) {

			return true;

		}

		return calculaProfundidade(url) <= this.profundidade.getValue();

	}

	public static void main(String[] args) {
		CriterioAvaliacao c = new CriterioAvaliacao();
		c.setProfundidade(Profundidade.NIVEL3);
		// boolean b = c.profundidadeEstaAdequada("http://www.fatecpe.com.br");
		boolean b = c.profundidadeEstaAdequada("http://www.fatecpe.com.br/trabalhe-conosco-professores/?replytocom=32");
		System.out.println(b);
	}

	private int calculaProfundidade(String url) {

		String nomeSite = url.split("//")[1];

		if (nomeSite.endsWith("/")) {

			return nomeSite.split("/").length;

		} else {

			return nomeSite.split("/").length - 1;

		}

	}

}
