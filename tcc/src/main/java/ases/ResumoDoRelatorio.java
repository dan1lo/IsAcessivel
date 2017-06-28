/*******************************************************************************
 * Copyright 2005, 2006, 2007, 2008 Acessibilidade Brasil
 * Este arquivo � parte do programa ASES - Avaliador e Simulador para AcessibilidadE de S�tios
 * O ASES � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
 * publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o posterior.
 * Este programa � distribuido na esperan�a que possa ser  util, mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer  MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

/**
 * Representa��o do resumo do relat�rio
 *
 */
public class ResumoDoRelatorio {

	/**
	 * Guarda o n�mero de links por p�gina
	 */
	public static final int results_pp = 50;

	/**
	 * Atualiza uma linha
	 * 
	 * @param hashCodeString
	 * @param url
	 * @param erros_p1
	 * @param erros_p2
	 * @param erros_p3
	 * @param avisos_p1
	 * @param avisos_p2
	 * @param avisos_p3
	 */
	public static void updLine(ContextoDeAvaliacao contexto, String hashCodeString, String url, int erros_p1,
			int erros_p2, int erros_p3, int avisos_p1, int avisos_p2, int avisos_p3) {
		int i = 1;
		File arq = new File(contexto.getFolderTemp() + "resumo" + i + ".csv");
		while (arq.exists()) {
			G_File fileResumo = new G_File(contexto.getFolderTemp() + "resumo" + i + ".csv");
			String resumo = fileResumo.read();
			int ini = resumo.indexOf("\n" + hashCodeString + "\t");
			if (ini != -1) {
				int fim = resumo.indexOf("\n", ini + 1);
				// Atualizar a linha
				String conteudo = "\n" + hashCodeString + "\t" + erros_p1 + "\t" + erros_p2 + "\t" + erros_p3 + "\t"
						+ avisos_p1 + "\t" + avisos_p2 + "\t" + avisos_p3 + "\t" + url;
				if (fim != -1) {
					resumo = resumo.substring(0, ini) + conteudo + resumo.substring(fim);
				} else {
					resumo = resumo.substring(0, ini) + conteudo;
				}
				fileResumo.write(resumo);
				break;
			} else {
				if (resumo.indexOf(hashCodeString + "\t") == 0) {
					int fim = resumo.indexOf("\n", ini + 1);
					String conteudo = hashCodeString + "\t" + erros_p1 + "\t" + erros_p2 + "\t" + erros_p3 + "\t"
							+ avisos_p1 + "\t" + avisos_p2 + "\t" + avisos_p3 + "\t" + url;
					resumo = conteudo + resumo.substring(fim);
					fileResumo.write(resumo);
					break;
				}
			}
			i++;
			arq = new File(contexto.getFolderTemp() + "resumo" + i + ".csv");
		}
	}

	/**
	 * Adiciona uma linha com hash, link, erros e avisos.
	 * 
	 * @param contextoDeExecucaoDeAvaliacao
	 */
	public synchronized static void addLine(ContextoDeAvaliacao contexto, String hashCodeString, String url,
			int erros_p1, int erros_p2, int erros_p3, int avisos_p1, int avisos_p2, int avisos_p3) {

		int indice = calculaIndiceResumoCSV(contexto);

		String conteudo = hashCodeString + "\t" + erros_p1 + "\t" + erros_p2 + "\t" + erros_p3 + "\t" + avisos_p1 + "\t"
				+ avisos_p2 + "\t" + avisos_p3 + "\t" + url + "\n";

		try {

			FileWriter arq = new FileWriter(contexto.getFolderTemp() + "resumo" + indice + ".csv", true);
			arq.write(conteudo);
			arq.close();
		} catch (IOException e) {
		}
	}

	/**
	 * @param contexto
	 * @return
	 */
	private synchronized static int calculaIndiceResumoCSV(ContextoDeAvaliacao contexto) {

		Double r_pp = Double.valueOf(ResumoDoRelatorio.results_pp);

		Double tot_l = Double.valueOf(contexto.getTotLinks());

		Double indx = 0.0;

		indx = (tot_l / r_pp) + 1;

		int indice = indx.intValue();

		if (Double.valueOf(indice) == indx) {
			indice--;
		}

		return indice;

	}

	/**
	 * Retorna a p�gina que ele estava vendo
	 */
	public static String getActualPage(ContextoDeAvaliacao contexto) {
		return getPage(contexto);
	}

	/**
	 * Retorna o n�mero de p�ginas no total
	 */
	public static int getTotPage(ContextoDeAvaliacao contexto) {

		return calculaIndiceResumoCSV(contexto);

	}

	/**
	 * Retorna a p�gina escolhida
	 */
	public synchronized static String getPage(ContextoDeAvaliacao contexto) {

		StringBuilder sb = new StringBuilder();

		final int mb = 2048;

		int indice = calculaIndiceResumoCSV(contexto);

		for (int i = 0; i <= indice; i++) {

			File file = new File(contexto.getFolderTemp() + "resumo" + i + ".csv");

			if (!file.exists()) {
				continue;
			}

			FileInputStream fis = null;

			try {
				if (file.exists()) {
					fis = new FileInputStream(file);
					byte[] dados = new byte[mb];
					int bytesLidos = 0;
					while ((bytesLidos = fis.read(dados)) > 0) {
						sb.append(new String(dados, 0, bytesLidos));
					}
					fis.close();
				} else {
					return "";
				}
			} catch (Exception e) {
				return "";
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
					}
				}
			}

		}

		return sb.toString().trim();

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

	// /**
	// * Guarda o �ndice corrente para gera��o da p�gina
	// */
	// private int indicePg = 1;
	//
	// /**
	// * Guarda o total de links em todas as p�ginas
	// */
	// private int totalLinks = 0;

	// public static ArrayList<RelatorioDaUrl> relatorios = new
	// ArrayList<RelatorioDaUrl>();

	// private boolean gravaCompleto;
	//
	// /**
	// * O conjunto de Op&ccedil&otilde;es selecionadas no in&iacute;cio da
	// * avalia&ccedil;&atilde;o. Cont&eacute;m as seguintes
	// * informa&ccedil;&otilde;es:
	// * <ul>
	// * <li>Tipo de avalia&ccedil;&atilde;o (WCAG / EGOV);</li>
	// * <li>Prioridades a serem avaliadas (Prioridade 1, 2 e/ou 3); e</li>
	// * <li>N&iacute;vel de aprofundamento no site (Sub-dom&iacute;nios
	// * avaliados).</li>
	// * </ul>
	// */
	// private CriterioAvaliacao opcoes;

	// public ResumoDoRelatorio(ArrayList<RelatorioDaUrl> relatorios,
	// ContextoDeAvaliacao contexto) {
	// ResumoDoRelatorio.relatorios = relatorios;
	// this.opcoes = contexto.getCriterio();
	// getTotPage(contexto);
	// }
	//
	// public ResumoDoRelatorio(ContextoDeAvaliacao contexto) {
	// this.opcoes = contexto.getCriterio();
	// getTotPage(contexto);
	// }

	// /**
	// * @return Returns the relatorios.
	// */
	// public ArrayList<RelatorioDaUrl> getRelatorios() {
	// return relatorios;
	// }
	//
	// /**
	// * @param relatorios
	// * The relatorios to set.
	// */
	// public void setRelatorios(ArrayList<RelatorioDaUrl> relatorios) {
	// ResumoDoRelatorio.relatorios = relatorios;
	// }
	//
	// /**
	// * @return Returns the opcoes.
	// */
	// public CriterioAvaliacao getOpcoes() {
	// return opcoes;
	// }
	//
	// /**
	// * @param opcoes
	// * The opcoes to set.
	// */
	// public void setOpcoes(CriterioAvaliacao opcoes) {
	// this.opcoes = opcoes;
	// }

	// /**
	// * @return Returns the gravaCompleto.
	// */
	// public boolean isGravaCompleto() {
	// return gravaCompleto;
	// }
	//
	// /**
	// * @param gravaCompleto
	// * The gravaCompleto to set.
	// */
	// public void setGravaCompleto(boolean gravaCompleto) {
	// this.gravaCompleto = gravaCompleto;
	// }

	// public Document geraResumoEmXl() { // usado para fazer grava��o .sil
	// try {
	//
	// Document doc =
	// DocumentBuilderFactoryImpl.newInstance().newDocumentBuilder().newDocument();
	// Element root = doc.createElement("resumo");
	//
	// Element completo = doc.createElement("completo");
	// if (this.isGravaCompleto()) {
	// completo.setTextContent("sim");
	// } else {
	// completo.setTextContent("nao");
	// }
	// root.appendChild(completo);
	//
	// Element tipoAvaliacao = doc.createElement("tipo_avaliacao");
	// tipoAvaliacao.setTextContent(String.valueOf(opcoes.getPadraoAcessibilidade()));
	// root.appendChild(tipoAvaliacao);
	//
	// Element niveis = doc.createElement("niveis");
	// niveis.setTextContent(String.valueOf(opcoes.getProfundidade()));
	// root.appendChild(niveis);
	//
	// Element pri1 = doc.createElement("pri1");
	// pri1.setTextContent(String.valueOf(opcoes.isPrioridade1()));
	// root.appendChild(pri1);
	//
	// Element pri2 = doc.createElement("pri2");
	// pri2.setTextContent(String.valueOf(opcoes.isPrioridade2()));
	// root.appendChild(pri2);
	//
	// Element pri3 = doc.createElement("pri3");
	// pri3.setTextContent(String.valueOf(opcoes.isPrioridade3()));
	// root.appendChild(pri3);
	//
	// for (RelatorioDaUrl relatorio : relatorios) {
	// Element rel = doc.createElement("relatorio");
	//
	// Element url = doc.createElement("url");
	// url.setTextContent(relatorio.getUrl());
	// rel.appendChild(url);
	//
	// Element ep1 = doc.createElement("errosp1");
	// ep1.setTextContent(String.valueOf(relatorio.getErrosPrioridade1()));
	// rel.appendChild(ep1);
	//
	// Element ep2 = doc.createElement("errosp2");
	// ep2.setTextContent(String.valueOf(relatorio.getErrosPrioridade2()));
	// rel.appendChild(ep2);
	//
	// Element ep3 = doc.createElement("errosp3");
	// ep3.setTextContent(String.valueOf(relatorio.getErrosPrioridade3()));
	// rel.appendChild(ep3);
	//
	// Element ap1 = doc.createElement("avisosp1");
	// ap1.setTextContent(String.valueOf(relatorio.getAvisosPrioridade1()));
	// rel.appendChild(ap1);
	//
	// Element ap2 = doc.createElement("avisosp2");
	// ap2.setTextContent(String.valueOf(relatorio.getAvisosPrioridade2()));
	// rel.appendChild(ap2);
	//
	// Element ap3 = doc.createElement("avisosp3");
	// ap3.setTextContent(String.valueOf(relatorio.getAvisosPrioridade3()));
	// rel.appendChild(ap3);
	//
	// root.appendChild(rel);
	// }
	//
	// doc.appendChild(root);
	//
	// return doc;
	//
	// } catch (ParserConfigurationException pce) {
	// return null;
	// }
	// }

	// public static File gravaResumoEmXm(File outFile, Document doc) {
	// try {
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
	// return outFile;
	// } catch (Exception e) {
	// return null;
	// }
	// }

	// public File geraArquivoResumoEmXm() {
	// try {
	//
	// File outFile = new File("resumo.xml");
	//
	// Document doc = this.geraResumoEmXml();
	//
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
	// return outFile;
	// } catch (Exception e) {
	// ExceptionDialog.showExceptionDialog(e.getMessage());
	// return null;
	// }
	// }
	//
	// public ArrayList<File> gerarArquivosRelatorio(ContextoDeAvaliacao
	// contexto) {
	//
	// ArrayList<File> arquivos = new ArrayList<File>();
	//
	// arquivos.add(this.geraArquivoResumoEmXml());
	//
	// if (this.isGravaCompleto()) {
	//
	// for (RelatorioDaUrl relatorio : relatorios) {
	//
	// File file = relatorio.geraArquivoRelatorioEmXml(contexto);
	// // o
	// // arquivo
	// // serializado
	// if (file != null) {
	// arquivos.add(file);
	// }
	//
	// }
	// }
	//
	// return arquivos;
	// }
	//
	// public ArrayList<File> gerarRelatorioTemporario(RelatorioDaUrl relatorio,
	// ContextoDeAvaliacao contexto) {
	//
	// gravaCompleto = true;
	//
	// ArrayList<File> arquivos = new ArrayList<File>();
	//
	// arquivos.add(this.geraArquivoResumoEmXml());
	//
	// File file = relatorio.geraArquivoRelatorioEmXml(contexto);
	//
	// if (file != null) {
	// arquivos.add(file);
	// }
	//
	// return arquivos;
	// }

	// public static ResumoDoRelatorio lerArquivoResumoEmXm(Document doc,
	// ContextoDeAvaliacao contexto) {
	// ArrayList<RelatorioDaUrl> relatorios = new ArrayList<RelatorioDaUrl>();
	// contexto.setCriterio(lerOpcoesDoXml(doc));
	// ResumoDoRelatorio resumo = new ResumoDoRelatorio(relatorios, contexto);
	// Node root = doc.getFirstChild();
	// NodeList nl = root.getChildNodes();
	// for (int i = 0; i < nl.getLength(); i++) {
	// Node node = nl.item(i);
	// if (node.getNodeName().equalsIgnoreCase("completo")) {
	// String completo = node.getTextContent();
	// if (completo != null && completo.equalsIgnoreCase("sim")) {
	// resumo.setGravaCompleto(true);
	// } else {
	// resumo.setGravaCompleto(false);
	// }
	// } else if (node.getNodeName().equalsIgnoreCase("relatorio")) {
	// RelatorioDaUrl relatorio = new RelatorioDaUrl();
	// NodeList nl2 = node.getChildNodes();
	// for (int j = 0; j < nl2.getLength(); j++) {
	// Node child = nl2.item(j);
	// String childName = child.getNodeName();
	// if (childName.equalsIgnoreCase("url")) {
	// relatorio.setUrl(child.getTextContent());
	// } else if (childName.equalsIgnoreCase("errosp1")) {
	// relatorio.setErrosPrioridade1(Integer.parseInt(child.getTextContent()));
	// } else if (childName.equalsIgnoreCase("errosp2")) {
	// relatorio.setErrosPrioridade2(Integer.parseInt(child.getTextContent()));
	// } else if (childName.equalsIgnoreCase("errosp3")) {
	// relatorio.setErrosPrioridade3(Integer.parseInt(child.getTextContent()));
	// } else if (childName.equalsIgnoreCase("avisosp1")) {
	// relatorio.setAvisosPrioridade1(Integer.parseInt(child.getTextContent()));
	// } else if (childName.equalsIgnoreCase("avisosp2")) {
	// relatorio.setAvisosPrioridade2(Integer.parseInt(child.getTextContent()));
	// } else if (childName.equalsIgnoreCase("avisosp3")) {
	// relatorio.setAvisosPrioridade3(Integer.parseInt(child.getTextContent()));
	// }
	// }
	// relatorios.add(relatorio);
	// }
	// }
	// resumo.setRelatorios(relatorios);
	// return resumo;
	// }

	// public static CriterioAvaliacao lerOpcoesDoXm(Document doc) {
	//
	// CriterioAvaliacao criterios = new CriterioAvaliacao();
	//
	// Node root = doc.getFirstChild();
	// NodeList nl = root.getChildNodes();
	// for (int i = 0; i < nl.getLength(); i++) {
	// Node node = nl.item(i);
	// if (node.getNodeName().equalsIgnoreCase("tipo_avaliacao")) {
	// String tipoAvaliacao = node.getTextContent();
	// if (tipoAvaliacao != null) {
	// criterios.setPadraoAcessibilidade(PadraoAcessibilidade.getOrgao(Integer.parseInt(tipoAvaliacao)));
	// } else {
	// criterios.setPadraoAcessibilidade(PadraoAcessibilidade.getOrgao(1));
	// }
	// } else if (node.getNodeName().equalsIgnoreCase("niveis")) {
	// String niveis = node.getTextContent();
	// if (niveis != null) {
	// criterios.setProfundidade(Profundidade.getProfundidade(Integer.parseInt(niveis)));
	// } else {
	// criterios.setProfundidade(Profundidade.getProfundidade(1));
	// }
	// } else if (node.getNodeName().equalsIgnoreCase("pri1")) {
	// String pri1 = node.getTextContent();
	// criterios.setPrioridade1(Boolean.getBoolean(pri1));
	// } else if (node.getNodeName().equalsIgnoreCase("pri2")) {
	// String pri2 = node.getTextContent();
	// criterios.setPrioridade2(Boolean.getBoolean(pri2));
	// } else if (node.getNodeName().equalsIgnoreCase("niveis")) {
	// String pri3 = node.getTextContent();
	// criterios.setPrioridade3(Boolean.getBoolean(pri3));
	// }
	// }
	// return criterios;
	// }

	//
	// public static int getTotalLinks() {
	// return totalLinks;
	// }
	//
	// public static void setTotalLinks(int totalLinks) {
	// ResumoDoRelatorio.totalLinks = totalLinks;
	// }

	//
	// /**
	// * Retorna o n�mero da p�gina atual
	// */
	// public int getIndicePg() {
	// return indicePg;
	// }
}
