/*******************************************************************************
 * Copyright 2005, 2006, 2007, 2008 Acessibilidade Brasil
 * Este arquivo &eacute; parte do programa ASES - Avaliador e Simulador para AcessibilidadE de S&iacut;tios
 * O ASES &eacute; um software livre; voc&ecirc; pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen&ccedil;a P&uacute;blica Geral GNU como
 * publicada pela Funda&ccedil;&atilde;o do Software Livre (FSF); na vers&ccedil;&atilde;o 2 da Licen&ccedil;a, ou (na sua opni&atilde;o) qualquer vers&ccedil;&atilde;o posterior.
 * Este programa &eacute; distribuido na esperan&ccedil;a que possa ser  util, mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA&Ccedil;&Atilde;O a qualquer  MERCADO ou APLICA&Ccedil;&Atilde;O EM PARTICULAR. Veja a Licen&ccedil;a P&uacute;blica Geral GNU para maiores detalhes.
 * Voc&ecirc; deve ter recebido uma c&oacute;pia da Licen&ccedil;a P&uacute;blica Geral GNU, sob o t&iacute;tulo "LICENCA.txt", junto com este programa, se n&atilde;o, escreva para a Funda&ccedil;&atilde;o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *******************************************************************************/

/*******************************************************************************
 * Copyright (c) 2005, 2006, 2007 Acessibilidade Brasil.
 * 
 * This file is part of ASES.
 *
 * ASES is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * A copy of the license can be found at 
 * http://www.gnu.org/copyleft/lesser.txt.
 *******************************************************************************/

package ases;

import java.util.HashSet;

import br.com.jguedes.tcc.gerenciadorrelatorioarquivo.FachadaArquivador;
import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

/**
 * Representa&ccedil;&atilde;o do relat&oacute;rio da p&aacute;gina
 *
 *
 * @author joaoguedes
 *
 */
public class RelatorioDaUrl {

	private String linkEvalCode;

	private String url;

	private Profundidade profundidade;

	private PadraoAcessibilidade padraoAcessibilidade;

	private String nomeProjeto;

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

	private StringBuilder conteudo;

	public RelatorioDaUrl() { // inicializa apenas
		url = "";
		mostraP1 = true;
		mostraP2 = true;
		mostraP3 = true;
		errosPrioridade1 = 0;
		errosPrioridade2 = 0;
		errosPrioridade3 = 0;
		avisosPrioridade1 = 0;
		avisosPrioridade2 = 0;
		avisosPrioridade3 = 0;
		listaErrosP1 = new HashSet<PontoVerificacao>();
		listaErrosP2 = new HashSet<PontoVerificacao>();
		listaErrosP3 = new HashSet<PontoVerificacao>();
		listaAvisosP1 = new HashSet<PontoVerificacao>();
		listaAvisosP2 = new HashSet<PontoVerificacao>();
		listaAvisosP3 = new HashSet<PontoVerificacao>();

	}

	public RelatorioDaUrl(String linkEvalCode) {
		setLinkEvalCode(linkEvalCode);
	}

	// GETTERS AND SETTERS

	public void setConteudo(StringBuilder conteudo) {

		this.conteudo = conteudo;

	}

	public StringBuilder getConteudo() {
		return conteudo;
	}

	/**
	 * @return Returns the avisosPrioridade1.
	 */
	public Integer getAvisosPrioridade1() {
		return avisosPrioridade1;
	}

	/**
	 * @param avisosPrioridade1
	 *            The avisosPrioridade1 to set.
	 */
	public void setAvisosPrioridade1(int avisosPrioridade1) {
		this.avisosPrioridade1 = avisosPrioridade1;
	}

	/**
	 * @return Returns the avisosPrioridade2.
	 */
	public Integer getAvisosPrioridade2() {
		return avisosPrioridade2;
	}

	/**
	 * @param avisosPrioridade2
	 *            The avisosPrioridade2 to set.
	 */
	public void setAvisosPrioridade2(int avisosPrioridade2) {
		this.avisosPrioridade2 = avisosPrioridade2;
	}

	/**
	 * @return Returns the avisosPrioridade3.
	 */
	public Integer getAvisosPrioridade3() {
		return avisosPrioridade3;
	}

	/**
	 * @param avisosPrioridade3
	 *            The avisosPrioridade3 to set.
	 */
	public void setAvisosPrioridade3(int avisosPrioridade3) {
		this.avisosPrioridade3 = avisosPrioridade3;
	}

	/**
	 * @return Returns the errosPrioridade1.
	 */
	public Integer getErrosPrioridade1() {
		return errosPrioridade1;
	}

	/**
	 * @param errosPrioridade1
	 *            The errosPrioridade1 to set.
	 */
	public void setErrosPrioridade1(int errosPrioridade1) {
		;
		this.errosPrioridade1 = errosPrioridade1;
	}

	/**
	 * @return Returns the errosPrioridade2.
	 */
	public Integer getErrosPrioridade2() {
		return errosPrioridade2;
	}

	/**
	 * @param errosPrioridade2
	 *            The errosPrioridade2 to set.
	 */
	public void setErrosPrioridade2(int errosPrioridade2) {
		this.errosPrioridade2 = errosPrioridade2;
	}

	/**
	 * @return Returns the errosPrioridade3.
	 */
	public Integer getErrosPrioridade3() {
		return errosPrioridade3;
	}

	/**
	 * @param errosPrioridade3
	 *            The errosPrioridade3 to set.
	 */
	public void setErrosPrioridade3(int errosPrioridade3) {
		this.errosPrioridade3 = errosPrioridade3;
	}

	/**
	 * @return Returns the listaAvisosP1.
	 */
	public HashSet<PontoVerificacao> getListaAvisosP1() {
		return listaAvisosP1;
	}

	/**
	 * @param listaAvisosP1
	 *            The listaAvisosP1 to set.
	 */
	public void setListaAvisosP1(HashSet<PontoVerificacao> listaAvisosP1) {
		this.listaAvisosP1 = listaAvisosP1;
	}

	/**
	 * @return Returns the listaAvisosP2.
	 */
	public HashSet<PontoVerificacao> getListaAvisosP2() {
		return listaAvisosP2;
	}

	/**
	 * @param listaAvisosP2
	 *            The listaAvisosP2 to set.
	 */
	public void setListaAvisosP2(HashSet<PontoVerificacao> listaAvisosP2) {
		this.listaAvisosP2 = listaAvisosP2;
	}

	/**
	 * @return Returns the listaAvisosP3.
	 */
	public HashSet<PontoVerificacao> getListaAvisosP3() {
		return listaAvisosP3;
	}

	/**
	 * @param listaAvisosP3
	 *            The listaAvisosP3 to set.
	 */
	public void setListaAvisosP3(HashSet<PontoVerificacao> listaAvisosP3) {
		this.listaAvisosP3 = listaAvisosP3;
	}

	/**
	 * @return Returns the listaErrosP1.
	 */
	public HashSet<PontoVerificacao> getListaErrosP1() {
		return listaErrosP1;
	}

	/**
	 * @param listaErrosP1
	 *            The listaErrosP1 to set.
	 */
	public void setListaErrosP1(HashSet<PontoVerificacao> listaErrosP1) {
		this.listaErrosP1 = listaErrosP1;
	}

	/**
	 * @return Returns the listaErrosP2.
	 */
	public HashSet<PontoVerificacao> getListaErrosP2() {
		return listaErrosP2;
	}

	/**
	 * @param listaErrosP2
	 *            The listaErrosP2 to set.
	 */
	public void setListaErrosP2(HashSet<PontoVerificacao> listaErrosP2) {
		this.listaErrosP2 = listaErrosP2;
	}

	/**
	 * @return Returns the listaErrosP3.
	 */
	public HashSet<PontoVerificacao> getListaErrosP3() {
		return listaErrosP3;
	}

	/**
	 * @param listaErrosP3
	 *            The listaErrosP3 to set.
	 */
	public void setListaErrosP3(HashSet<PontoVerificacao> listaErrosP3) {
		this.listaErrosP3 = listaErrosP3;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The url to set.
	 */

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the mostraP1.
	 */
	public boolean mostraP1() {
		return mostraP1;
	}

	/**
	 * @param mostraP1
	 *            The mostraP1 to set.
	 */
	public void setMostraP1(boolean mostraP1) {
		this.mostraP1 = mostraP1;
	}

	/**
	 * @return Returns the mostraP2.
	 */
	public boolean mostraP2() {
		return mostraP2;
	}

	/**
	 * @param mostraP2
	 *            The mostraP2 to set.
	 */
	public void setMostraP2(boolean mostraP2) {
		this.mostraP2 = mostraP2;
	}

	/**
	 * @return Returns the mostraP3.
	 */
	public boolean mostraP3() {
		return mostraP3;
	}

	/**
	 * @param mostraP3
	 *            The mostraP3 to set.
	 */
	public void setMostraP3(boolean mostraP3) {
		this.mostraP3 = mostraP3;
	}

	public Profundidade getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Profundidade profundidade) {
		this.profundidade = profundidade;
	}

	public String getLinkEvalCode() {
		return linkEvalCode;
	}

	public void setLinkEvalCode(String linkEvalCode) {
		this.linkEvalCode = linkEvalCode;
	}

	public PadraoAcessibilidade getPadraoAcessibilidade() {
		return padraoAcessibilidade;
	}

	public void setPadraoAcessibilidade(PadraoAcessibilidade padraoAcessibilidade) {
		this.padraoAcessibilidade = padraoAcessibilidade;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public void gravarRelatorio(ContextoDeAvaliacao contexto) {

		FachadaArquivador.gravarRelatorio(contexto, this);

	}

	public void carregarGravado(ContextoDeAvaliacao contexto) {

		this.setLinkEvalCode(contexto.getLinkEvalCodeRelAtual());

		RelatorioDaUrl r = FachadaArquivador.recuperarRelatorio(contexto, this);

		setLinkEvalCode(r.getLinkEvalCode());
		setUrl(r.getUrl());
		setProfundidade(r.getProfundidade());
		setPadraoAcessibilidade(r.getPadraoAcessibilidade());
		setNomeProjeto(r.getNomeProjeto());
		setMostraP1(r.mostraP1());
		setMostraP2(r.mostraP2());
		setMostraP3(r.mostraP3());
		setAvisosPrioridade1(r.getAvisosPrioridade1());
		setAvisosPrioridade2(r.getAvisosPrioridade2());
		setAvisosPrioridade3(r.getAvisosPrioridade3());
		setErrosPrioridade1(r.getErrosPrioridade1());
		setErrosPrioridade2(r.getErrosPrioridade2());
		setErrosPrioridade3(r.getErrosPrioridade3());
		setListaAvisosP1(r.getListaAvisosP1());
		setListaAvisosP2(r.getListaAvisosP2());
		setListaAvisosP3(r.getListaAvisosP3());
		setListaErrosP1(r.getListaErrosP1());
		setListaErrosP2(r.getListaErrosP2());
		setListaErrosP3(r.getListaErrosP3());
	}

	/*
	 * DAQUI PRA BAIXO É LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI
	 * PRA BAIXO É LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA
	 * BAIXO É LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA BAIXO É
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA BAIXO É LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA BAIXO É LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA BAIXO É LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO DAQUI PRA BAIXO É LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO LIXO
	 * LIXO LIXO LIXO LIXO LIXO LIXO
	 */

	// public static void gravaRelatorioEmHtm(File outFile, Document doc) {
	// try {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// factory.setNamespaceAware(true);
	// factory.setValidating(true);
	//
	// File style = new File("relatorio.xsl");
	// StreamSource styleSource = new StreamSource(style);
	//
	// TransformerFactory tFactory = TransformerFactory.newInstance();
	// Transformer transformer = tFactory.newTransformer(styleSource);
	//
	// DOMSource source = new DOMSource(doc);
	// StreamResult result = new StreamResult(outFile);
	// transformer.transform(source, result);
	//
	// } catch (Exception e) {
	// }
	// }

	// public File geraArquivoRelator(ContextoDeAvaliacao contexto) {
	// try {
	// String fileName = String.valueOf(this.hashCode());
	// File outFile = new File(contexto.getFolderTemp() + fileName + ".xml"); //
	// o
	// // gravador
	// // de
	// // arquivos no diretorio
	// // que n�o deve ser
	// // gravado est� aqui?
	// Document doc = this.serializarXml(contexto);
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// factory.setNamespaceAware(true);
	// factory.setValidating(true);
	//
	// TransformerFactory tFactory = TransformerFactory.newInstance();
	// Transformer transformer = tFactory.newTransformer();
	//
	// DOMSource source = new DOMSource(doc);
	// StreamResult result = new StreamResult(outFile);
	// transformer.transform(source, result);
	//
	// G_Log.loggerInNewLine(contexto.getFolderTemp().concat(hashCodeString).concat(".json"),
	// result.getWriter().toString());
	//
	// return outFile;
	// } catch (Exception e) {
	// ExceptionDialog.showExceptionDialog("[RelatorioDaURL]: " +
	// e.getMessage());
	// return null;
	// }
	// }

	// public static RelatorioDaUrl lerArquivoRelatorioEmXm(Document doc,
	// ContextoDeAvaliacao contexto) {
	// RelatorioDaUrl relatorio = new RelatorioDaUrl();
	// Node root = doc.getFirstChild();
	// NodeList nl = root.getChildNodes();
	// relatorio.setMostraP1(false);
	// relatorio.setMostraP2(false);
	// relatorio.setMostraP3(false);
	// for (int i = 0; i < nl.getLength(); i++) {
	// Node node = nl.item(i);
	// String nodeName = node.getNodeName();
	// if (nodeName.equalsIgnoreCase(elements.get("url"))) {
	// relatorio.setUrl(node.getTextContent());
	// } else if (nodeName.equalsIgnoreCase(elements.get("errosp1"))) {
	// relatorio.setErrosPrioridade1(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("errosp2"))) {
	// relatorio.setErrosPrioridade2(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("errosp3"))) {
	// relatorio.setErrosPrioridade3(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("avisosp1"))) {
	// relatorio.setAvisosPrioridade1(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("avisosp2"))) {
	// relatorio.setAvisosPrioridade2(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("avisosp3"))) {
	// relatorio.setAvisosPrioridade3(Integer.parseInt(node.getTextContent()));
	// } else if (nodeName.equalsIgnoreCase(elements.get("mostrap1"))) {
	// relatorio.setMostraP1(true);
	// } else if (nodeName.equalsIgnoreCase(elements.get("mostrap2"))) {
	// relatorio.setMostraP2(true);
	// } else if (nodeName.equalsIgnoreCase(elements.get("mostrap3"))) {
	// relatorio.setMostraP3(true);
	// }
	//
	// else if (nodeName.equalsIgnoreCase(elements.get("conteudo")) &&
	// !desconsideraConteudo) {
	// StringBuilder conteudo = new StringBuilder();
	// NodeList nl2 = node.getChildNodes();
	// for (int j = 0; j < nl2.getLength(); j++) {
	// String linhaCodigo = nl2.item(j).getTextContent();
	// linhaCodigo = linhaCodigo.endsWith("\n") ? linhaCodigo : linhaCodigo +
	// "\n";
	// conteudo.append(linhaCodigo);
	// }
	// relatorio.setConteudo(conteudo, contexto);
	// }
	//
	// else if (nodeName.equalsIgnoreCase(elements.get("listaep1"))) {
	// relatorio.setListaErrosP1(relatorio.geraListaDeErros(node));
	// } else if (nodeName.equalsIgnoreCase(elements.get("listaep2"))) {
	// relatorio.setListaErrosP2(relatorio.geraListaDeErros(node));
	// } else if (nodeName.equalsIgnoreCase(elements.get("listaep3"))) {
	// relatorio.setListaErrosP3(relatorio.geraListaDeErros(node));
	// } else if (nodeName.equalsIgnoreCase(elements.get("listaap1"))) {
	// relatorio.setListaAvisosP1(relatorio.geraListaDeErros(node));
	// } else if (nodeName.equalsIgnoreCase(elements.get("listaap2"))) {
	// relatorio.setListaAvisosP2(relatorio.geraListaDeErros(node));
	// } else if (nodeName.equalsIgnoreCase(elements.get("listaap3"))) {
	// relatorio.setListaAvisosP3(relatorio.geraListaDeErros(node));
	// }
	// }
	// return relatorio;
	// }

}
