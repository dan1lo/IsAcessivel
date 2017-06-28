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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

/**
 * Motor do silvinha Antigo
 * 
 * @author Mariano Aloi Construido em 22/08/2005
 * @version 1.3
 */
public class Geral {
	/**
	 * Cria um log
	 */
	private static final Logger log = Logger.getLogger("br.org.acessobrasil.silvinha");

	/**
	 * conex&atilde;o com o banco.
	 */
	private Conexao con;

	/**
	 * Array com as tags do arquivo.
	 */
	Tag[][] tagArray;

	/**
	 * Lista de tags do documento.
	 */
	private HashSet<String> tag = new HashSet<String>();

	/**
	 * Lista de tags que devem ser pesquisadas se existe no documento.
	 */
	private HashMap<String, ArrayList<ArmazenaErroOuAvisoAntigo>> erradosMap = new HashMap<String, ArrayList<ArmazenaErroOuAvisoAntigo>>();

	/**
	 * Lista de erros do documento.
	 */
	private ArrayList<ArmazenaErroOuAvisoAntigo> errados = new ArrayList<ArmazenaErroOuAvisoAntigo>();

	/**
	 * coluna da tag.
	 */
	private int colunaTag = 0;

	/**
	 * Espa&ccedil;amento para endenta&ccedil;&atilde;o
	 */
	private Espaco2 Espaco2Tag;

	/**
	 * tipo de avalia&ccedil;&atilde;o.
	 */
	private int orgao;

	/**
	 * tag em formato de texto.
	 */
	private String tagCompleta;

	/**
	 * linha da tag.
	 */
	private int linhaTag;

	/**
	 * Conte&uacute;do HTML da p&aacute;gina a ser avaliada.
	 */
	private StringBuilder conteudo;

	private Validar validar;

	/**
	 * Nome corrente da tag
	 */
	private String currentTagName;

	public String strHtmlSource;

	InterfRegrasHardCoded regras;

	boolean achouH1aH6;

	/**
	 * Adquiri uma tag errada e a concatena dentro de um mapa de erros. Se
	 * j&aacute; existe o erro no mapa ele apenas concatena dentro de uma lista
	 * formada por todas as tags com esse erro, senao apenas cria uma nova
	 * lista.
	 * 
	 * @param erros
	 *            tag errada
	 */
	private void setErrados(final ArmazenaErroOuAvisoAntigo erros) {

		String nome = erros.getProcurado();

		ArrayList<ArmazenaErroOuAvisoAntigo> erradoAL = null;

		/*
		 * Verifica a existencia da lista de erro.
		 */
		if (this.erradosMap.containsKey(nome)) {

			erradoAL = this.erradosMap.get(nome);

			this.erradosMap.remove(nome);

		} else {

			erradoAL = new ArrayList<ArmazenaErroOuAvisoAntigo>();// senao cria
																	// uma
			// nova lista

		}

		erradoAL.add(erros);

		this.erradosMap.put(nome, erradoAL);// concatena

	}

	/**
	 * M&eacute;todo que ira preparar os erros da tag com o seu texto sua
	 * posi&ccedil;&atilde;o
	 * 
	 * @param erros
	 *            lista de erros da tag avaliada
	 */
	private void avaliar(ArrayList<ArmazenaErroOuAvisoAntigo> erros) {

		ArmazenaErroOuAvisoAntigo erro = null;

		/*
		 * Itera os erros.
		 */
		for (Iterator<ArmazenaErroOuAvisoAntigo> iteracao = erros.iterator(); iteracao.hasNext();) {

			erro = iteracao.next();

			/*
			 * Identacao da tag.
			 */
			erro.setEspaco(Espaco2Tag);

			/*
			 * Tag em texto.
			 */
			erro.setTagCompleta(tagCompleta);

			/*
			 * Posicao da tag.
			 */
			erro.setPosicao(new Posicao2(linhaTag, colunaTag));

			// System.out.print("erro.getProcurado()="+erro.getProcurado()+"\n");
			// System.out.print("erro.isTag()="+erro.isTag()+"\n");
			// if((currentTagName.equals("option") ||
			// (currentTagName.equals("input") &&
			// tagCompleta.toLowerCase().contains("hidden"))) &&
			// erro.getProcurado().equals("VALUE")){
			// Esses erros nao existem, tentar otimizar depois
			// }else{
			if (currentTagName.equals("input")
					&& (tagCompleta.toLowerCase().contains("submit") || tagCompleta.toLowerCase().contains("buttom"))
					&& erro.getProcurado().equals("ONCLICK")) {

				// System.out.print("erro.getProcurado()=" + erro.getProcurado()
				// + "\n");
				// System.out.print("Erro omitido\n");

				return;

			}

			/*
			 * Se erro deve ser passado pela avaliacao de tag, senao agrupe
			 * direto na lista de erros.
			 */

			if (erro.isTag()) {

				setErrados(erro);

			} else {

				// agrupar na lista de erros
				errados.add(erro);

			}

			// }

		}
	}

	/**
	 * Avaliador se h&aacute; uma tag especifica dentro do arquivo, havendo ela
	 * distingue que a tag n&atilde;o est&aacute; errada, exemplo script
	 * necessita de um noscript dentro do documento. Para formar toda lista de
	 * tags especificas ele busca em banco as que s&atilde;o
	 * obrigat&oacute;rias.
	 * 
	 * @throws SQLException
	 */
	private void avaliaTagObrigatoria() throws SQLException {
		/*
		 * busca as tags obrigatorias do documento.
		 */
		// TODO ESTE SELECT SOH TRAZ A TAG !DOCTYPE, OU SEJA, SE ELE TRAZ AS
		// TAGS OBRIGATORIAS,
		// ENTAO ELE SOH TAH CONSIDERANDO O !DOCTYPE COMO OBRIGATORIO. OU ENTAO
		// TEM ALGUMA
		// COISA ERRADA NO BANCO DE DADOS. OBS: NA BASE DE DADOS SOH TEM
		// CADASTRADA UMA LINHA
		// COM ID_ATITUDE = 4 (TABELA ESPERADO) : (9,4,9,0,0,'','',0)
		// TRECHO COMENTADO E HARDCODED PARA DIMINUIR AS CONSULTAS A BANCO DE
		// DADOS
		// final String sql = "SELECT ta.nome_tag, v.pv3 "
		// + " FROM esperado e, validante v, tipoavaliacaoerro t, tag ta "
		// + " WHERE e.idesperado=v.idesperado "
		// + " AND v.pv3=t.pv3 AND idatitude = 4 "
		// + " AND v.idtag=ta.idtag AND t.idorgao = " + orgao;
		//
		// ResultSet rs = con.getCon().createStatement().executeQuery(sql);
		//
		// String nome = null;
		// ArrayList<Validado> validados = null;
		//
		// /*
		// * Monta as tags especificas.
		// */
		// while (rs.next()) {
		// nome = rs.getString(1);
		// validados = new ArrayList<Validado>();
		// validados.add(new Validado(nome, rs.getInt(2)));
		// this.erradosMap.put(nome, validados);
		// }
		// gambiarra?????
		// TRECHO HARDCODED QUE SUBSTITUI A CONSULTA AO BD ACIMA

		ArrayList<ArmazenaErroOuAvisoAntigo> validados = new ArrayList<ArmazenaErroOuAvisoAntigo>();

		validados.add(new ArmazenaErroOuAvisoAntigo("!DOCTYPE", 33));

		this.erradosMap.put("!DOCTYPE", validados);

		// FIM DO TRECHO
		for (String element : this.erradosMap.keySet()) {

			/*
			 * Verifica se a existencia da tag dentro do documento. N�o
			 * existindo a tag, adiciona a mesma a lista de erros.
			 */
			if (!tag.contains(element)) {

				this.errados.addAll(this.erradosMap.get(element));

			}

		}

	}

	/**
	 * M&eacute;todo respons&aacute;vel fazer uma pesquisa em cada tag para
	 * pesquis&aacute;-la e parametriz&aacute;-la com linha e coluna, a pesquisa
	 * &eacute; continua indo at&eacute; os &uacute;ltimos filhos de uma tag
	 * m&atilde;e, alem de validar o doctype.
	 * 
	 * @param documento
	 *            documento completo com os par&acirc;metros de
	 *            localiza&ccedil;&atilde;o
	 * @throws SQLException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws ExceptionMariano
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private void loopingChildren(Node documento) throws IOException, SQLException, Exception {

		ArrayList<ArmazenaErroOuAvisoAntigo> temp = new ArrayList<ArmazenaErroOuAvisoAntigo>();

		Integer aux = null;

		if (documento != null) {

			/**
			 * teste
			 */
			String nomeTag = documento.getNodeName();

			/*
			 * Pesquisa se eh uma tag especial de parametro.
			 */
			if (nomeTag != null && (TokenNucleo.LINHA.equals(nomeTag) || TokenNucleo.COLUNA.equals(nomeTag)
					|| TokenNucleo.MAE.equals(nomeTag))) {

				/*
				 * Tag de linha que contem o numero da linha e a configuracao de
				 * identacao.
				 */
				if (TokenNucleo.LINHA.equals(nomeTag)) {

					linhaTag = new Integer(documento.getAttributes().item(2).getNodeValue());

					aux = new Integer(documento.getAttributes().item(1).getNodeValue());

					Espaco2Tag = new Espaco2(aux, new Integer(documento.getAttributes().item(0).getNodeValue()));

					documento = documento.getNextSibling();

				}

				/*
				 * Tag de coluna.
				 */
				if (TokenNucleo.COLUNA.equals(nomeTag)) {

					colunaTag = new Integer(documento.getAttributes().item(0).getNodeValue());

					documento = documento.getNextSibling();

				}

				/*
				 * tag mae para todas as outras.
				 */
				if (TokenNucleo.MAE.equals(nomeTag)) {

					NodeList children = documento.getChildNodes();

					for (int i = 0; i < children.getLength(); i++) {

						// Comeca a fazer um loop para dentro do documento.
						loopingChildren(children.item(i));

					}

				}

			}
			/*
			 * Se a tag for Tag HTML do Conteudo do Link.
			 */
			else {

				/*
				 * Tipo de No.
				 */
				short type = documento.getNodeType();

				/*
				 * Se houver um doctype ele eh atribuido a lista de tags, para
				 * que nao ocorra erros.
				 */
				if (type == Node.DOCUMENT_TYPE_NODE) {

					tag.add("!DOCTYPE");

				}

				/*
				 * Reescreve o no para que se possa guardar ela montada.
				 */
				tagCompleta = new ConversorTexto().reEscrever(documento);

				/*
				 * Verificacao HardCoded
				 */
				if (!nomeTag.equals("#text")) {

					if (nomeTag.toLowerCase().equals("input")) {

						if (!regras.verificaTextoPredefinidoInput(tagCompleta)) {

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(132);

							v.setProcurado("Value");

							v.setTag(true);

							temp.add(v);

						}

					}

					if (nomeTag.toLowerCase().equals("input") || nomeTag.toLowerCase().equals("textarea")
							|| nomeTag.toLowerCase().equals("select")) {

						boolean ishidden = false;

						boolean isbutton = false;

						boolean isimage = false;

						boolean comErro = true;

						NamedNodeMap nos = documento.getAttributes();

						for (int k = 0; k < nos.getLength(); k++) {

							if (nos.item(k).getNodeName().toLowerCase().equals("type")) {

								if (nos.item(k).getNodeValue().toLowerCase().equals("hidden")) {

									ishidden = true;

								} else if (nos.item(k).getNodeValue().toLowerCase().equals("button")) {

									isbutton = true;

								} else if (nos.item(k).getNodeValue().toLowerCase().equals("submit")) {

									isbutton = true;

								} else if (nos.item(k).getNodeValue().toLowerCase().equals("reset")) {

									isbutton = true;

								} else if (nos.item(k).getNodeValue().toLowerCase().equals("image")) {

									isimage = true;

								}

							}

							if (nos.item(k).getNodeName().equals("id")) {

								if (regras.verificaLabel(strHtmlSource, nos.item(k).getNodeValue())) {

									comErro = false;

								}

							}

						}

						if (ishidden == false && isbutton == false && isimage == false && comErro == true) {

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(233);

							v.setProcurado("Label");

							v.setTag(true);

							temp.add(v);

						}

					}

					if (nomeTag.toLowerCase().equals("h1") || nomeTag.toLowerCase().equals("h2")
							|| nomeTag.toLowerCase().equals("h3") || nomeTag.toLowerCase().equals("h4")
							|| nomeTag.toLowerCase().equals("h5") || nomeTag.toLowerCase().equals("h6")) {

						if (regras.verificaH1aH6(strHtmlSource) != 0) {

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(217);

							v.setProcurado("");

							v.setTag(true);

							temp.add(v);

						}

					}

					if (!regras.verificaTamFixoEmAtributoStyle(tagCompleta)) {

						ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

						v.setPv3(179);

						v.setProcurado("");

						v.setTag(true);

						temp.add(v);

					}

					if (nomeTag.toLowerCase().equals("textarea")) {

						if (!regras.verificaTextoPredefinidoTextarea(strHtmlSource, tagCompleta)) {

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(134);

							v.setProcurado("");

							v.setTag(true);

							temp.add(v);

						}

					}

					if (nomeTag.toLowerCase().equals("frame")) {

						if (!regras.verificaLongDesc(tagCompleta)) {

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(212);

							v.setProcurado("");

							v.setTag(true);

							temp.add(v);

						}

					}

					if (nomeTag.toLowerCase().equals("a")) {

						if (!regras.verificaSeparadorEmLink(strHtmlSource)) {

							// System.out.println("oi");

							ArmazenaErroOuAvisoAntigo v = new ArmazenaErroOuAvisoAntigo();

							v.setPv3(259);

							v.setProcurado("");

							v.setTag(true);

							temp.add(v);

						}

					}

				}

				/*
				 * No de abertura, ele apenas prossegue a pesquisa com os seus
				 * filhos.
				 */
				if (type == Node.DOCUMENT_NODE) {

					loopingChildren(((Document) documento).getDocumentElement());

				}

				/*
				 * No normal, que deve ser avaliado.
				 */
				else if (type == Node.ELEMENT_NODE) {

					/*
					 * Guarda o nome da tag para que depois ela possa ser feito
					 * o teste se ha uma tag especifica no documento.
					 */
					tag.add(documento.getNodeName().toUpperCase());

					/*
					 * Avalia a tag e recebe uma lista de erros.
					 */
					temp.addAll(this.validar.validar(documento));

					if (temp != null) {

						// informa qual � a tag corrente
						currentTagName = documento.getNodeName().toLowerCase();

						// Se houver erro guarde ele.
						avaliar(temp);

						// System.out.print("erro no " + documento.getNodeName()
						// +"\n");

						if (documento.getNodeName().toUpperCase().equals("IMG")) {

							// Node
							// src=documento.getAttributes().getNamedItem("src");
							// System.out.print("img " + src.getNodeValue()
							// +"\n");

						} else if (documento.getNodeName().toUpperCase().equals("OPTION")) {

							// Node
							// src=documento.getAttributes().getNamedItem("value");
							// System.out.print("option '" + src.getNodeValue()
							// +"'\n");

						}

					}

					/*
					 * Se tiver filhos busque-os.
					 */
					NodeList children = documento.getChildNodes();

					if (children != null) {

						int len = children.getLength();

						for (int pos = 0; pos < len; pos++) {

							try {

								/*
								 * Para cada filho eh feito uma nova consulta.
								 */
								loopingChildren(children.item(pos));

							} catch (Error e) {

								throw new Exception("Erro de overflow");

							}

						}

					}

				}

			}

		}

	}

	/**
	 * Transforma um documento HTML em XML e coloca dentro do Document DOM
	 * 
	 * @param link
	 *            Link a ser avaliado
	 * @param orgaoID
	 *            Identifica&ccedil;&atilde;o do &Oacute;rg&atilde;o
	 *            (WCAG/E-GOV)
	 * @param bidimensionaTag
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 *             Erro no banco de dados ou na instru&ccedil;&atilde;o SQL.
	 * @throws ClassNotFoundException
	 *             Driver do banco de dados n&atilde;o encontrado.
	 * @throws ExceptionMariano
	 */
	private ArrayList<ArmazenaErroOuAvisoAntigo> mains(final String link, final int orgaoID,
			final boolean bidimensionaTag) throws IOException, ClassNotFoundException, SQLException, Exception {

		this.orgao = orgaoID;

		this.con = new Conexao();

		achouH1aH6 = false;

		regras = MethodFactRegHardCod.mFRegHardCod("EMAG");

		this.validar = new Validar(this.con, this.orgao);

		// JOptionPane.showMessageDialog(null, link);
		/*
		 * Uma String que ira conter o XML Parametrizado das Tags existentes no
		 * HTML do link passado por Parametro.
		 */
		String code = null;

		if (this.conteudo == null || "".equals(this.conteudo)) {

			// JOptionPane.showMessageDialog(null, "code &eacute;:");
			// Tambem nao passa por aqui...
			code = new Transformar().tagDefineLinha(link);

		} else {

			// JOptionPane.showMessageDialog(null, "code2 &eacute;:");
			// code = new Transformar(this.conteudo).tagDefineLinha(link);
			code = new Transformar(this.conteudo).tagDefineLinha(link);

		}

		if (bidimensionaTag) {
			/*
			 * Le o XML parametrizado e retorna uma Matriz contendo as Tags do
			 * HTML, de acordo com a linha e coluna parametrizada no metodo
			 * Transformar.tagDefineLinha().
			 */

			MotorTag mt = new MotorTag(code);

			// pq eh um processo?
			/*
			 * mt.start();
			 * 
			 * //espera terminar while(tagArray == null) { tagArray =
			 * mt.getTags(); }
			 */

			// Roda sem ser como processo
			mt.noProcess();

			this.tagArray = mt.getTags();

		} else {

			this.tagArray = null;

		}

		// FileOutputStream fos = new FileOutputStream(new File("tagado.xml"));
		// fos.write(code.getBytes());

		/*
		 * Comeca o motor a avaliar.
		 */
		try {

			// parser que transforma um HTML em um XML
			final Tidy tidy = new Tidy();

			tidy.setXHTML(true);

			tidy.setXmlOut(true);

			tidy.setXmlTags(true);

			tidy.setShowWarnings(false);

			tidy.setQuiet(true);

			tidy.setErrout(new PrintWriter(new File("silvinha-tidy.log")));

			final Document dom = tidy.parseDOM(new ByteArrayInputStream(code.getBytes()), null);

			loopingChildren(dom);

		} catch (Exception em) {

			log.error("Erro na Avalia&ccedil;&atilde;o do Link: " + link, em);

		}

		// VERIFICA A EXISTENCIA DAS TAGS OBRIGATORIAS
		avaliaTagObrigatoria();

		con.getCon().close();

		return errados;

	}

	/**
	 * Respons&aacute;vel por iniciar o motor
	 * 
	 * @param site
	 *            URL da pagina
	 * @param orgaoID
	 *            tipo de avalia&ccedil;&atilde;o
	 * @return o array de tags
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws SQLException
	 *             Erro no banco de dados ou na instru&ccedil;&atilde;o SQL.
	 * @throws ClassNotFoundException
	 *             Driver do banco de dados n&atilde;o encontrado.
	 * @throws IOException
	 * @throws ExceptionMariano
	 * @throws @throws
	 *             Exception
	 */
	public Tag[][] mainTags(final String site, final int orgaoID)
			throws IOException, ClassNotFoundException, SQLException, Exception {

		mains(site, orgaoID, true);

		return this.tagArray;

	}

	public Tag[][] mainTags(String site, int orgaoID, StringBuilder conteudo)
			throws IOException, ClassNotFoundException, SQLException, Exception {

		this.conteudo = conteudo;

		mains(site, orgaoID, true);

		return this.tagArray;

	}

	/**
	 * Disponibilizador da lista de erros.
	 * 
	 * @return a lista de tags erradas.
	 */
	public ArrayList<ArmazenaErroOuAvisoAntigo> getErrados() {

		return this.errados;

	}

	/**
	 * @param site
	 * @param orgaoID
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 *             Erro no banco de dados ou na instru&ccedil;&atilde;o SQL.
	 * @throws ClassNotFoundException
	 *             Driver do banco de dados n&atilde;o encontrado.
	 * @throws ExceptionMariano
	 */
	public ArrayList<ArmazenaErroOuAvisoAntigo> getErrados(final String site, final int orgaoID)
			throws IOException, ClassNotFoundException, SQLException, Exception {

		mains(site, orgaoID, false);

		return this.errados;

	}

	/**
	 * 
	 * @param link
	 * @param orgaoID
	 * @param conteudo
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             Driver do banco de dados n&atilde;o encontrado.
	 * @throws SQLException
	 *             Erro no banco de dados ou na instru&ccedil;&atilde;o SQL.
	 * @throws ExceptionMariano
	 */
	// public ArrayList<Validado> getErrados(final String link, final int
	// orgaoID,
	public Object getErrados(final String link, final int orgaoID, final StringBuilder conteudo)
			throws IOException, ClassNotFoundException, SQLException, Exception {

		// chamado pelo ProcessarErro
		this.conteudo = conteudo;

		mains(link, orgaoID, false);

		return this.errados;

	}

	public String getStrHtmlSource() {

		return this.strHtmlSource;

	}

	public void setStrHtmlSource(String strHtmlSource) {

		this.strHtmlSource = strHtmlSource;

	}

}