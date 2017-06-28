package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;

import ases.ExceptionDialog;
import ases.PontoVerificacao;
import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

public class GerenciadorRelatorioXML extends GerenciadorRelatorioArquivo {

	private static final long serialVersionUID = -4454747941238630095L;

	private ContextoDeAvaliacao contexto;

	private RelatorioDaUrl relatorio;

	private ConcurrentMap<String, String> elements;

	private AmbienteArquivacaoXML ambienteArquivacao;

	public GerenciadorRelatorioXML(AmbienteArquivacaoXML ambienteArquivacao) {

		this.ambienteArquivacao = ambienteArquivacao;

		setElements();

	}

	@Override
	public void gravarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio) {

		this.relatorio = relatorio;

		this.contexto = contexto;

		geraArquivoRelatorioEmXml2();

	}

	@Override
	public RelatorioDaUrl recuperarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio) {

		this.contexto = contexto;

		this.relatorio = relatorio;

		recarregaArquivoRelatorioEmXml();

		return this.relatorio;

	}

	private void setElements() {

		if (ambienteArquivacao.isNomeTagCOMPLETO()) {

			setElementosOriginais();

		} else {

			setElementosReduzidos();

		}

	}

	/**
	 * Grava o relat&oacute;rio no HD em Xml na pasta cache
	 */
	private void geraArquivoRelatorioEmXml2() {

		try {

			gravaXmlNoHD(contexto);

		} catch (Exception e) {

			ExceptionDialog.showExceptionDialog("[RelatorioDaURL]: " + e.getMessage());

		}

	}

	/**
	 * Grava o Xml deste relat&oacute;rio no HD
	 */
	public File gravaXmlNoHD(ContextoDeAvaliacao contexto) {

		try {

			String fileName = String.valueOf(relatorio.getLinkEvalCode());

			File outFile = new File(contexto.getFolderTemp() + fileName + ".xml");
			//
			//
			//

			Document doc = this.serializarXml(contexto);

			//
			//
			//
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setNamespaceAware(true);

			factory.setValidating(true);

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(outFile);

			transformer.transform(source, result);

			return outFile;

		} catch (Exception e) {
			ExceptionDialog.showExceptionDialog("[RelatorioDaURL]: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Retorna um documento serializado em XML
	 * 
	 * @return documento
	 */
	public Document serializarXml(ContextoDeAvaliacao contexto) {

		try {

			Document doc = DocumentBuilderFactoryImpl.newInstance().newDocumentBuilder().newDocument();

			Element root = doc.createElement(elements.get("relatorio"));

			Element url = doc.createElement(elements.get("url"));
			url.setTextContent(this.relatorio.getUrl());
			root.appendChild(url);

			Element ep1 = doc.createElement(elements.get("errosp1"));
			ep1.setTextContent(String.valueOf(this.relatorio.getErrosPrioridade1()));
			root.appendChild(ep1);

			Element ep2 = doc.createElement(elements.get("errosp2"));
			ep2.setTextContent(String.valueOf(this.relatorio.getErrosPrioridade2()));
			root.appendChild(ep2);

			Element ep3 = doc.createElement(elements.get("errosp3"));
			ep3.setTextContent(String.valueOf(this.relatorio.getErrosPrioridade3()));
			root.appendChild(ep3);

			Element ap1 = doc.createElement(elements.get("avisosp1"));
			ap1.setTextContent(String.valueOf(this.relatorio.getAvisosPrioridade1()));
			root.appendChild(ap1);

			Element ap2 = doc.createElement(elements.get("avisosp2"));
			ap2.setTextContent(String.valueOf(this.relatorio.getAvisosPrioridade2()));
			root.appendChild(ap2);

			Element ap3 = doc.createElement(elements.get("avisosp3"));
			ap3.setTextContent(String.valueOf(this.relatorio.getAvisosPrioridade3()));
			root.appendChild(ap3);

			if (ambienteArquivacao.isComCONTEUDOLINHAS()) {

				Element conteudo = doc.createElement(elements.get("conteudo"));

				String[] cLinhas = super.recuperarArquivoConteudoHTML(contexto, this.relatorio.getLinkEvalCode())
						.toString().split("\n");

				Element clinha;

				for (int i = 0; i < cLinhas.length; i++) {

					String linha = String.format("%04d", (i + 1));

					clinha = doc.createElement(elements.get("clinha"));

					clinha.setAttribute(elements.get("nro"), linha);

					clinha.setTextContent(cLinhas[i] + "\n");

					conteudo.appendChild(clinha);

				}

				root.appendChild(conteudo);

			}

			if (this.relatorio.mostraP1()) {

				root.appendChild(doc.createElement(elements.get("mostrap1")));

				if (this.relatorio.getErrosPrioridade1() > 0) {

					root.appendChild(geraLista(elements.get("listaep1"), this.relatorio.getListaErrosP1(), doc));

				}

				if (this.relatorio.getAvisosPrioridade1() > 0) {

					root.appendChild(geraLista(elements.get("listaap1"), this.relatorio.getListaAvisosP1(), doc));

				}

			}

			if (this.relatorio.mostraP2()) {
				root.appendChild(doc.createElement(elements.get("mostrap2")));
				if (this.relatorio.getErrosPrioridade2() > 0) {
					root.appendChild(geraLista(elements.get("listaep2"), this.relatorio.getListaErrosP2(), doc));
				}
				if (this.relatorio.getAvisosPrioridade2() > 0) {
					root.appendChild(geraLista(elements.get("listaap2"), this.relatorio.getListaAvisosP2(), doc));
				}
			}

			if (this.relatorio.mostraP3()) {
				root.appendChild(doc.createElement(elements.get("mostrap3")));
				if (this.relatorio.getErrosPrioridade3() > 0) {
					root.appendChild(geraLista(elements.get("listaep3"), this.relatorio.getListaErrosP3(), doc));
				}
				if (this.relatorio.getAvisosPrioridade3() > 0) {
					root.appendChild(geraLista(elements.get("listaap3"), this.relatorio.getListaAvisosP3(), doc));
				}
			}

			doc.appendChild(root);

			return doc;

		} catch (ParserConfigurationException pce) {
			return null;
		}
	}

	/**
	 * Gera um xml com os pvs
	 * 
	 * @param nomeElemento
	 * @param pvs
	 * @param doc
	 * @return
	 */
	private Element geraLista(String nomeElemento, HashSet<PontoVerificacao> pvs, Document doc) {

		Element lista = doc.createElement(nomeElemento);

		HashSet<PontoVerificacao> pontos = pvs;

		for (PontoVerificacao pv : pontos) {

			Element pontoVerificacao = doc.createElement(elements.get("pontoverificacao"));

			pontoVerificacao.setAttribute(elements.get("priori"), String.valueOf(pv.getPrioridade()));

			pontoVerificacao.setAttribute(elements.get("wcagemag"), String.valueOf(pv.getWcagEmag()));

			Element gl = doc.createElement(elements.get("gl"));
			gl.setTextContent(String.valueOf(pv.getGl()));
			pontoVerificacao.appendChild(gl);

			Element cp = doc.createElement(elements.get("cp"));
			cp.setTextContent(String.valueOf(pv.getCp()));
			pontoVerificacao.appendChild(cp);

			Element regra = doc.createElement(elements.get("id_regra"));
			regra.setTextContent(String.valueOf(pv.getIdRegra()));
			pontoVerificacao.appendChild(regra);

			Element total = doc.createElement(elements.get("total"));
			int size = pv.getLinhas().size();
			String strSize = String.valueOf(size);
			total.setTextContent(!strSize.equals("0") ? strSize : "---");
			pontoVerificacao.appendChild(total);

			Element linhas = doc.createElement(elements.get("linhas"));
			ArrayList<Integer> arrayLinhas = pv.getLinhas();
			ArrayList<Integer> arrayColunas = pv.getColunas();
			ArrayList<Integer> arrayTagLength = pv.getTagLength();
			ArrayList<String> arrayAvOuErr = pv.getAvisoOuErro();
			Element linha;
			for (int i = 0; i < arrayLinhas.size(); i++) {
				linha = doc.createElement(elements.get("linha"));
				linha.setAttribute(elements.get("col"), arrayColunas.get(i).toString());
				linha.setAttribute(elements.get("taglen"), arrayTagLength.get(i).toString());
				linha.setAttribute(elements.get("avisoerro"), arrayAvOuErr.get(i).toString());
				String strLinha = String.format("%04d", (arrayLinhas.get(i)));
				linha.setTextContent(strLinha);
				linhas.appendChild(linha);
			}
			pontoVerificacao.appendChild(linhas);
			lista.appendChild(pontoVerificacao);
		}
		return lista;
	}

	/**
	 * Recarrega um relat&oacute;rio do HD.
	 * 
	 * @param myHash
	 *            � o nome do arquivo
	 */
	public void recarregaArquivoRelatorioEmXml() {

		this.relatorio.setLinkEvalCode(this.contexto.getLinkEvalCodeRelAtual());

		File tmpFile = new File(contexto.getFolderTemp() + this.relatorio.getLinkEvalCode() + ".xml");

		Document doc;

		try {

			DocumentBuilder db;

			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			doc = db.parse(tmpFile);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		} catch (SAXException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// popula os valores
		Node root = doc.getFirstChild();

		NodeList nl = root.getChildNodes();

		this.relatorio.setMostraP1(false);
		this.relatorio.setMostraP2(false);
		this.relatorio.setMostraP3(false);

		for (int i = 0; i < nl.getLength(); i++) {

			Node node = nl.item(i);

			String nodeName = node.getNodeName();

			if (nodeName.equalsIgnoreCase(elements.get("url"))) {

				this.relatorio.setUrl(node.getTextContent());

			} else if (nodeName.equalsIgnoreCase(elements.get("errosp1"))) {

				this.relatorio.setErrosPrioridade1(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("errosp2"))) {

				this.relatorio.setErrosPrioridade2(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("errosp3"))) {

				this.relatorio.setErrosPrioridade3(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("avisosp1"))) {

				this.relatorio.setAvisosPrioridade1(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("avisosp2"))) {

				this.relatorio.setAvisosPrioridade2(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("avisosp3"))) {

				this.relatorio.setAvisosPrioridade3(Integer.parseInt(node.getTextContent()));

			} else if (nodeName.equalsIgnoreCase(elements.get("mostrap1"))) {

				this.relatorio.setMostraP1(true);

			} else if (nodeName.equalsIgnoreCase(elements.get("mostrap2"))) {

				this.relatorio.setMostraP2(true);

			} else if (nodeName.equalsIgnoreCase(elements.get("mostrap3"))) {

				this.relatorio.setMostraP3(true);

			}

			else if (nodeName.equalsIgnoreCase(elements.get("conteudo")) && ambienteArquivacao.isComCONTEUDOLINHAS()) {

				StringBuilder conteudo = new StringBuilder();

				NodeList nl2 = node.getChildNodes();

				for (int j = 0; j < nl2.getLength(); j++) {

					String linhaCodigo = nl2.item(j).getTextContent();

					linhaCodigo = linhaCodigo.endsWith("\n") ? linhaCodigo : linhaCodigo + "\n";

					conteudo.append(linhaCodigo);

				}

				this.relatorio.setConteudo(conteudo);

			}

			else if (nodeName.equalsIgnoreCase(elements.get("listaep1"))) {
				this.relatorio.setListaErrosP1(geraListaDeErros(node));
			} else if (nodeName.equalsIgnoreCase(elements.get("listaep2"))) {
				this.relatorio.setListaErrosP2(geraListaDeErros(node));
			} else if (nodeName.equalsIgnoreCase(elements.get("listaep3"))) {
				this.relatorio.setListaErrosP3(geraListaDeErros(node));
			} else if (nodeName.equalsIgnoreCase(elements.get("listaap1"))) {
				this.relatorio.setListaAvisosP1(geraListaDeErros(node));
			} else if (nodeName.equalsIgnoreCase(elements.get("listaap2"))) {
				this.relatorio.setListaAvisosP2(geraListaDeErros(node));
			} else if (nodeName.equalsIgnoreCase(elements.get("listaap3"))) {
				this.relatorio.setListaAvisosP3(geraListaDeErros(node));
			}
		}
	}

	/**
	 * Popula um hashSet com os pontos de verifica��o passado em node
	 *
	 * @param node
	 *            n� onde est�o gravados os pontos de verifica��o
	 * @return
	 */
	private HashSet<PontoVerificacao> geraListaDeErros(Node node) {
		HashSet<PontoVerificacao> lista = new HashSet<PontoVerificacao>();
		NodeList nl = node.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			PontoVerificacao pv = new PontoVerificacao();
			Node pontoVerificacao = nl.item(i);
			NamedNodeMap nodePVatt = pontoVerificacao.getAttributes();
			int wcagemag = Integer.parseInt(nodePVatt.getNamedItem(elements.get("wcagemag")).getTextContent());
			pv.setWcagEmag(wcagemag);
			NodeList children = pontoVerificacao.getChildNodes();
			for (int j = 0; j < children.getLength(); j++) {
				Node child = children.item(j);
				String childName = child.getNodeName();
				if (childName.equalsIgnoreCase(elements.get("gl"))) {
					pv.setGl(Integer.parseInt(child.getTextContent()));
				} else if (childName.equalsIgnoreCase(elements.get("cp"))) {
					pv.setCp(Integer.parseInt(child.getTextContent()));
				} else if (childName.equalsIgnoreCase(elements.get("id_regra"))) {
					pv.setIdRegra(Integer.parseInt(child.getTextContent()));
				} else if (childName.equalsIgnoreCase(elements.get("linhas"))) {
					pv.setLinhas(new ArrayList<Integer>());
					pv.setAvisoOuErro(new ArrayList<String>());
					pv.setColunas(new ArrayList<Integer>());
					pv.setTagLength(new ArrayList<Integer>());
					NodeList nodeLinhas = child.getChildNodes();
					for (int k = 0; k < nodeLinhas.getLength(); k++) {
						NamedNodeMap att = nodeLinhas.item(k).getAttributes();
						String strErrOuAv = att.getNamedItem(elements.get("avisoerro")).getTextContent();
						int col = Integer.parseInt(att.getNamedItem(elements.get("col")).getTextContent());
						int tagLen = Integer.parseInt(att.getNamedItem(elements.get("taglen")).getTextContent());
						int linha = Integer.parseInt(nodeLinhas.item(k).getTextContent());
						pv.getLinhas().add(linha);
						pv.getAvisoOuErro().add(strErrOuAv);
						pv.getColunas().add(col);
						pv.getTagLength().add(tagLen);
					}
				}
			}
			lista.add(pv);
		}
		return lista;
	}

	/**
	 * 
	 */
	public void setElementosOriginais() {
		elements = new ConcurrentHashMap<String, String>();
		elements.putIfAbsent("relatorio", "rel");
		elements.putIfAbsent("url", "url");
		elements.putIfAbsent("errosp1", "errosp1");
		elements.putIfAbsent("errosp2", "errosp2");
		elements.putIfAbsent("errosp3", "errosp3");
		elements.putIfAbsent("avisosp1", "avisosp1");
		elements.putIfAbsent("avisosp2", "avisosp2");
		elements.putIfAbsent("avisosp3", "avisosp3");
		elements.putIfAbsent("conteudo", "conteudo");
		elements.putIfAbsent("clinha", "clinha");
		elements.putIfAbsent("nro", "nro");
		elements.putIfAbsent("mostrap1", "mostrap1");
		elements.putIfAbsent("mostrap2", "mostrap2");
		elements.putIfAbsent("mostrap3", "mostrap3");
		elements.putIfAbsent("listaep1", "listaep1");
		elements.putIfAbsent("listaep2", "listaep2");
		elements.putIfAbsent("listaep3", "listaep3");
		elements.putIfAbsent("listaap1", "listaap1");
		elements.putIfAbsent("listaap2", "listaap2");
		elements.putIfAbsent("listaap3", "listaap3");
		elements.putIfAbsent("pontoverificacao", "pontoverificacao");
		elements.putIfAbsent("priori", "priori");
		elements.putIfAbsent("wcagemag", "wcagemag");
		elements.putIfAbsent("gl", "gl");
		elements.putIfAbsent("cp", "cp");
		elements.putIfAbsent("id_regra", "id_regra");
		elements.putIfAbsent("total", "total");
		elements.putIfAbsent("linhas", "linhas");
		elements.putIfAbsent("linha", "linha");
		elements.putIfAbsent("col", "col");
		elements.putIfAbsent("taglen", "taglen");
		elements.putIfAbsent("avisoerro", "avisoerro");
	}

	/**
	 * 
	 */
	private void setElementosReduzidos() {
		elements = new ConcurrentHashMap<String, String>();
		elements.putIfAbsent("relatorio", "re");
		elements.putIfAbsent("url", "ur");
		elements.putIfAbsent("errosp1", "e1");
		elements.putIfAbsent("errosp2", "e2");
		elements.putIfAbsent("errosp3", "e3");
		elements.putIfAbsent("avisosp1", "a1");
		elements.putIfAbsent("avisosp2", "a2");
		elements.putIfAbsent("avisosp3", "a3");
		elements.putIfAbsent("conteudo", "co");
		elements.putIfAbsent("clinha", "cl");
		elements.putIfAbsent("nro", "nr");
		elements.putIfAbsent("mostrap1", "m1");
		elements.putIfAbsent("mostrap2", "m2");
		elements.putIfAbsent("mostrap3", "m3");
		elements.putIfAbsent("listaep1", "l1");
		elements.putIfAbsent("listaep2", "l2");
		elements.putIfAbsent("listaep3", "l3");
		elements.putIfAbsent("listaap1", "l4");
		elements.putIfAbsent("listaap2", "l5");
		elements.putIfAbsent("listaap3", "l6");
		elements.putIfAbsent("pontoverificacao", "po");
		elements.putIfAbsent("priori", "pr");
		elements.putIfAbsent("wcagemag", "wc");
		elements.putIfAbsent("gl", "gl");
		elements.putIfAbsent("cp", "cp");
		elements.putIfAbsent("id_regra", "id");
		elements.putIfAbsent("total", "to");
		elements.putIfAbsent("linhas", "l7");
		elements.putIfAbsent("linha", "l8");
		elements.putIfAbsent("col", "cl");
		elements.putIfAbsent("taglen", "ta");
		elements.putIfAbsent("avisoerro", "av");
	}

}
