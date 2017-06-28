package br.com.jguedes.tcc.model.relatorioresumido;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ases.RelatorioDaUrl;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResumoDeAvaliacao {

	@XmlElement
	private String linkEvalCode;

	@XmlElement
	private String link;

	@XmlElement
	private int errop1;

	@XmlElement
	private int avisop1;

	@XmlElement
	private int errop2;

	@XmlElement
	private int avisop2;

	@XmlElement
	private int errop3;

	@XmlElement
	private int avisop3;

	public ResumoDeAvaliacao() {

	}

	public ResumoDeAvaliacao(RelatorioDaUrl relatorioDaUrl) {
		setLinkEvalCode(relatorioDaUrl.getLinkEvalCode());
		setLink(relatorioDaUrl.getUrl());
		setErrop1(relatorioDaUrl.getErrosPrioridade1());
		setAvisop1(relatorioDaUrl.getAvisosPrioridade1());
		setErrop2(relatorioDaUrl.getErrosPrioridade2());
		setAvisop2(relatorioDaUrl.getAvisosPrioridade2());
		setErrop3(relatorioDaUrl.getErrosPrioridade3());
		setAvisop3(relatorioDaUrl.getAvisosPrioridade3());
	}

	/**
	 * @param resumoDeAvaliacao
	 *            Array de String contendo na posicao:<br/>
	 *            0 o link,<br/>
	 *            1 a quantidade de avisos P1,<br/>
	 *            2 a quantidade de erros P1,<br/>
	 *            3 a quantidade de avisos P2,<br/>
	 *            4 a quantidade de erros P2,<br/>
	 *            5 a quantidade de avisos P3,<br/>
	 *            6 a quantidade de erros P3,<br/>
	 *            7 o codigo do relatorio.
	 */
	public ResumoDeAvaliacao(Object[] resumoDeAvaliacao) {
		setLink(resumoDeAvaliacao[0].toString());
		setAvisop1(Integer.parseInt(resumoDeAvaliacao[1].toString()));
		setErrop1(Integer.parseInt(resumoDeAvaliacao[2].toString()));
		setAvisop2(Integer.parseInt(resumoDeAvaliacao[3].toString()));
		setErrop2(Integer.parseInt(resumoDeAvaliacao[4].toString()));
		setAvisop3(Integer.parseInt(resumoDeAvaliacao[5].toString()));
		setErrop3(Integer.parseInt(resumoDeAvaliacao[6].toString()));
		setLinkEvalCode(resumoDeAvaliacao[7].toString());
	}

	public String getLinkEvalCode() {
		return linkEvalCode;
	}

	public void setLinkEvalCode(String linkEvalCode) {
		this.linkEvalCode = linkEvalCode;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getErrop1() {
		return errop1;
	}

	public void setErrop1(int errop1) {
		this.errop1 = errop1;
	}

	public int getAvisop1() {
		return avisop1;
	}

	public void setAvisop1(int avisop1) {
		this.avisop1 = avisop1;
	}

	public int getErrop2() {
		return errop2;
	}

	public void setErrop2(int errop2) {
		this.errop2 = errop2;
	}

	public int getAvisop2() {
		return avisop2;
	}

	public void setAvisop2(int avisop2) {
		this.avisop2 = avisop2;
	}

	public int getErrop3() {
		return errop3;
	}

	public void setErrop3(int errop3) {
		this.errop3 = errop3;
	}

	public int getAvisop3() {
		return avisop3;
	}

	public void setAvisop3(int avisop3) {
		this.avisop3 = avisop3;
	}

}
