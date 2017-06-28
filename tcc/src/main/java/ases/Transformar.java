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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Classe respons�vel por ler o documento text/html e contabilizar as tags
 * existentes em cada linha. Esta classe cria um documento XML contendo a
 * identa��o da linha, quais tags ela possui e em qual coluna.
 * 
 * @author Acessibilidade Brasil.
 */
public class Transformar {
	/**
	 * tabula��o para identa��o.
	 */
	private int tabulacao;

	/**
	 * espa�amento para identa��o.
	 */
	private int espaco;

	/**
	 * booleano que avisa se a tag foi fechada na linha anterior.
	 */
	private boolean fechaLinhaAnt = true;

	/**
	 * N�mero de linha.
	 */
	int numeracaoLinha;

	/**
	 * Conte�do da p�gina a ser manipulada.
	 */
	private StringBuilder conteudo;

	/**
	 * Construtor de Transformar.
	 */
	public Transformar() {
		super();
	}

	/**
	 * Construtor de Transformar.
	 * 
	 * @param texto
	 *            Conte�do da p�gina a ser manipulada.
	 */
	public Transformar(final StringBuilder texto) {
		super();

		this.conteudo = texto;
	}

	/**
	 * Contemplador das tags m�es. Para que no momento da interpreta��o, o JTydy
	 * pense que o documento seja XML.
	 * 
	 * @param urlString
	 *            URL a ser pesquisada.
	 * @return uma string com o documento original parametrizado com linhas,
	 *         colunas e identa��es
	 * @throws IOException
	 */
	public String tagDefineLinha(String urlString) throws IOException {
		URL url;
		if (!urlString.contains("://")) {
			urlString = "http://" + urlString;
		}
		StringBuilder concat = new StringBuilder();
		url = new URL(urlString); // abertura do documento da WEB
		concat.append("<" + TokenNucleo.MAE + ">"); // tag m�e, deve ser
													// colocada para se
													// interpretar como um XML
		concat.append(tagar(url));
		concat.append("</" + TokenNucleo.MAE + ">");// fechamento da tag m�e
		return concat.toString();// documento j� parametrizado
	}

	/**
	 * Motor principal da classe que ira produzir a parametriza&ccedil;&atilde;o
	 * de localiza&ccedil;&atilde;o da tag.<br/>
	 * O motor deve respeitar algumas diretrizes, entre elas:
	 * <ol>
	 * <li>A continuidade de uma tag mesmo que ela esteja entre varias linhas.
	 * </li>
	 * <li>Texto deve ser considerado tag, assim recebe coluna.</li>
	 * <li>Uma tag que come&ccedil;a com texto pode ser
	 * continua&ccedil;&atilde;o da linha acima(explica&ccedil;&atilde;o para
	 * tag continua).</li>
	 * </ol>
	 * Uma tag que se abre varias vezes ou fecha varias vezes n&atilde;o
	 * &eacute; tratado pelo motor sendo alegado que isso &eacute;
	 * <strong>m&aacute; forma&ccedil;&atilde;o do designer </strong>
	 * 
	 * @param linha
	 *            String que representa a linha corrente do documento
	 * @return string que est� parametrizada corretamente com a posi��o linha e
	 *         coluna
	 */
	public StringBuilder tagar(final String linha) {
		// /*
		// * Linha Default para linhas sem tags.
		// */
		// if (linha == null && linha.length() > 0) {
		// return new StringBuilder("<"
		// + Token.LINHA + " n=\"" + (numeracaoLinha) + "\" t=\""
		// + tabulacao + "\" s=\"" + espaco + "\"/>");
		// }

		/*
		 * Texto que ser� retornado.
		 */
		StringBuilder concat = new StringBuilder();
		StringBuilder tagLinha = new StringBuilder("<" + TokenNucleo.LINHA + " n=\"" + (numeracaoLinha) + "\" t=\""
				+ tabulacao + "\" s=\"" + espaco + "\"/>");// texto que
															// representa uma
															// tag de linha

		/*
		 * booleano que indica que a tag abre no in�cio da linha.
		 */
		boolean linhaAbre = false;

		/*
		 * indica que uma ou mais tags se abrem dentro da linha.
		 */
		boolean tagAbre = false;

		/*
		 * indica se a tag aberta foi fechada na mesma linha.
		 */
		boolean tagFecha = false;

		/*
		 * guarda a informa��o da linha anterior se a tag foi fechada.
		 */
		boolean fechaTagAnt = false;

		/*
		 * N�mero da coluna.
		 */
		int col = 0;

		if (linha.indexOf("<") == 0) {
			linhaAbre = true;
		}

		/*
		 * conjunto separado pela abertura das tags.
		 */
		String transA[] = linha.split("<");

		String tagAbreS = null;

		for (int indexAbertura = 0; indexAbertura < transA.length; indexAbertura++) {
			tagAbreS = transA[indexAbertura];

			if (tagAbreS.length() > 0) {

				/*
				 * conjunto separado pelo fechamento das tags.
				 */
				String transF[] = tagAbreS.trim().split(">");

				for (int indexFechamento = 0; indexFechamento < transF.length; indexFechamento++) {

					/*
					 * A tag crua para ser modelada.
					 */
					String tag = transF[indexFechamento].trim();

					if (tag.length() > 0) {
						/*
						 * se tag fecha dentro do elemento do conjunto de
						 * abertura e ainda o elemento n�o pode ser inicial ele
						 * ser� verdadeiro.
						 */
						if (tagAbreS.indexOf(">") > -1 && indexFechamento < 1) {
							tagFecha = true;
						} else {
							tagFecha = false;
						}

						/*
						 * se a linha � aberta ou o conjunto tem mais de 1
						 * elemento e tag deve ser inicial
						 */
						if ((linhaAbre || indexAbertura > 0) && indexFechamento < 1) {
							tagAbre = true;
						} else {
							tagAbre = false;
						}

						/*
						 * se a tag tem fechamento ela recebe o ">".
						 */
						if (tagFecha) {
							tag = tag + ">";
						}

						/*
						 * quando a tag abre.
						 */
						if (tagAbre) {
							/*
							 * Se � iniciante deve receber linha.
							 */
							if (col == 0)
								concat.append(tagLinha);

							concat.append("<" + TokenNucleo.COLUNA + " n=\"" + col + "\"/>\n<"); // como
																									// ela
																									// abre
																									// ela
																									// �
																									// colocada
							// uma tag de coluna e um sinal
							// de abertura

							concat.append(tag);

						}
						/*
						 * Quando n�o abre.
						 */
						else {
							/*
							 * E fecha.
							 */
							if (tagFecha) {
								/*
								 * coloca-se a tag e depois inclui a contagem de
								 * linha.
								 */
								concat.append(tag + tagLinha);

								/*
								 * para que a coluna entenda a inicia��o em 0,
								 * ja que no final do metodo ela ser� atribuita
								 * a +1 nada melho que colocar -1 que com +1
								 * vira 0, regra matematica da tia Teteca
								 */
								col = -1;
							} else if (fechaTagAnt)
								concat.append("<" + TokenNucleo.COLUNA + " n=\"" + col + "\"/>" + tag);// e
																										// tag
																										// anterior
																										// fecha
																										// coloque
							// apenas a tag de coluna + a tag
							// como ela �
							else if (fechaLinhaAnt)
								concat.append("<" + TokenNucleo.LINHA + " n=\"" + (numeracaoLinha) + "\" t=\""
										+ tabulacao + "\" s=\"" + espaco + "\"/>\n<" + TokenNucleo.COLUNA + " n=\""
										+ col + "\"/>" + tag);// e a linha
							// anterior fecha no
							// linal, coloque
							// linha, coluna e a
							// tag como ela �
							else
								concat.append(" " + tag);
							// e nada funciona
							// apenas jogue a
							// tag l�,
							// provavelmente ela
							// faz parte de uma
							// tag que foi
							// aberta em varias
							// linhas
						}
						fechaTagAnt = tagFecha;
						col++;
					}
				}

			}

		}

		fechaLinhaAnt = tagFecha;
		return concat;

	}

	/**
	 * C&oacute;digo respons&aacute;vel por fazer um looping do documento
	 * original para que ele seja parametrizado com a numera&ccedil;&atilde;o de
	 * linha.
	 * 
	 * @param url
	 * 
	 * @param br
	 *            buffer com o documento original
	 * @return string que est� parametrizada com a localiza��o em linhas e
	 *         colunas
	 * @throws IOException
	 */
	private StringBuilder tagar(final URL url) throws IOException {
		StringBuilder concat = new StringBuilder();
		ArrayList<StringBuilder> buf = new ArrayList<StringBuilder>();
		// N�o precisa deste c�digo!
		if (this.conteudo == null || "".equals(this.conteudo)) {
			buf = desmontar(url); // DW: se ainda n�o tiver sido baixado o
									// conteudo, este m�todo faz a baixa
		} else {
			// sempre passa por aqui.
			// buf = desmontar(PrepararConteudo.ignorarConteudo(this.conteudo));
			buf = desmontar(this.conteudo); // DW: separa o conteudo em linhas
		}
		this.numeracaoLinha = 0;
		for (StringBuilder linha : buf) {
			contaEspaco(linha.toString());
			concat.append(tagar(linha.toString().trim()));
			++this.numeracaoLinha;
		}

		return concat;
	}

	// /**
	// * Motor principal da classe que ira produzir a
	// parametriza&ccedil;&atilde;o
	// * de localiza&ccedil;&atilde;o da tag.<br/> O motor deve respeitar
	// algumas
	// * diretrizes, entre elas:
	// * <ol>
	// * <li>A continuidade de uma tag mesmo que ela esteja entre varias linhas.
	// * </li>
	// * <li>Texto deve ser considerado tag, assim recebe coluna. </li>
	// * <li>Uma tag que come&ccedil;a com texto pode ser
	// continua&ccedil;&atilde;o da
	// * linha acima(explica&ccedil;&atilde;o para tag continua). </li>
	// * </ol>
	// * Uma tag que se abre varias vezes ou fecha varias vezes n&atilde;o
	// * &eacute; tratado pelo motor sendo alegado que isso &eacute;
	// * <strong>m&aacute; forma&ccedil;&atilde;o do designer </strong>
	// *
	// * @param linha String que representa a linha corrente do documento
	// * @return string que est� parametrizada corretamente com a posi��o linha
	// e
	// * coluna
	// */
	// public StringBuilder tagar(final String linha) {
	//
	// //Texto que ser� retornado.
	// StringBuilder retorno = new StringBuilder();
	//
	// if (linha.trim().length() == 0)
	// {
	// return retorno;
	// }
	//
	// //Texto que representa uma tag de linha
	// StringBuilder tagLinha = new StringBuilder("<" + Token.LINHA + " n=\""
	// + (numeracaoLinha) + "\" t=\"" + tabulacao + "\" s=\"" + espaco
	// + "\"/>\n");
	//
	// if (fechaLinhaAnt)
	// {
	// retorno.append(tagLinha);
	// }
	//
	// //verifica a abertura ou fechamento de tags na linha
	// if (!linha.contains("<") && !linha.contains(">"))
	// {
	// if (linha.trim().length() > 0)
	// {
	// if (fechaLinhaAnt)
	// {
	// retorno.append("<" + Token.COLUNA + " n=\"0\"/>\n");
	// retorno.append(linha);
	// }
	// else
	// {
	// retorno.append(linha);
	// }
	// }
	// return retorno;
	// }
	//
	// //Indices do caractere "<"
	// ArrayList<Integer> listaLt = new ArrayList<Integer>();
	//
	// //Indices do caractere ">"
	// ArrayList<Integer> listaGt = new ArrayList<Integer>();
	//
	// //Lista das tags da linha
	// ArrayList<String> listaTags = new ArrayList<String>();
	//
	// //Ultimo indice onde os caractere de tag ("<" ou ">") foram localizados
	// //para pesquisa do indexOf a partir deste �ndice.
	// //Come�a sempre por 0.
	// int idx = 0;
	// int fromIndex = 0;
	//
	// //Busca e Localiza��o dos �ndices dos Caracteres "<" e ">"
	// while ( (idx = linha.indexOf("<", fromIndex)) != -1)
	// {
	// fromIndex = idx + 1;
	// listaLt.add(idx);
	// }
	// fromIndex = 0;
	// while ( (idx = linha.indexOf(">", fromIndex)) != -1)
	// {
	// fromIndex = idx + 1;
	// listaGt.add(idx);
	// }
	//
	// //ordernar as listas
	// Collections.sort(listaLt);
	// Collections.sort(listaGt);
	//
	// //quantidade de aberturas de tags
	// int listaLtSize = listaLt.size();
	//
	// //quantidade de fechamentos de tags
	// int listaGtSize = listaGt.size();
	//
	// if (listaLtSize == listaGtSize) //todas as tags que s�o abertas fecham na
	// mesma linha
	// {
	// if (listaLt.get(0) != 0) //se a linha for iniciada por texto ao inv�s de
	// tag
	// {
	// listaTags.add(linha.substring(0, listaLt.get(0)));
	// }
	// a: for (int i = 0; i < listaLtSize; i++) //itera em todas as tags
	// {
	// if ( i < (listaLtSize - 1)) //ainda tem tags para serem iteradas
	// {
	// if (listaGt.get(i) < listaLt.get(i + 1)) //a tag fecha antes de ser
	// aberta nova tag
	// {
	// listaTags.add(linha.substring(listaLt.get(i), listaGt.get(i) + 1));
	// }
	// else //se uma tag for aberta dentro de outra
	// {
	// String tag = null;
	// b: for (int j = i; j <= listaGtSize - 2; j++)
	// {
	// if (listaGt.get(j) < listaLt.get(j + 1))
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(j) + 1);
	// i = j;
	// break b;
	// }
	// }
	// if (tag == null)
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(listaGtSize -1) + 1);
	// listaTags.add(tag);
	// break a;
	// }
	// listaTags.add(tag);
	// }
	// //se existirem caracteres entre o fechamento desta tag e a abertura da
	// pr�xima
	// if (listaLt.get(i + 1) - listaGt.get(i) > 1)
	// {
	// listaTags.add(linha.substring(listaGt.get(i) + 1, listaLt.get(i + 1)));
	// }
	// }
	// else //n�o existem mais tags a serem iteradas
	// {
	// listaTags.add(linha.substring(listaLt.get(i), listaGt.get(i) + 1));
	// if (listaGt.get(i) + 1 < linha.length()) //se a tag fecha antes do fim da
	// linha, buscar o restante
	// {
	// listaTags.add(linha.substring(listaGt.get(i) + 2, linha.length()));
	// }
	// }
	// }
	// }
	//
	// else if (listaLtSize > listaGtSize)//nem todas as tags que abrem fecham
	// {
	// fechaLinhaAnt = false;
	// if (listaLt.get(0) != 0) //se a linha for iniciada por texto ao inv�s de
	// tag
	// {
	// listaTags.add(linha.substring(0, listaLt.get(0)));
	// }
	// a: for (int i = 0; i < listaLtSize; i++) //itera em todas as tags
	// {
	// if ( i < (listaLtSize - 1)) //ainda tem tags para serem iteradas
	// {
	// if (listaGt.get(i) < listaLt.get(i + 1)) //a tag fecha antes de ser
	// aberta nova tag
	// {
	// listaTags.add(linha.substring(listaLt.get(i), listaGt.get(i) + 1));
	// }
	// else //se uma tag for aberta dentro de outra
	// {
	// String tag = null;
	// b: for (int j = i; j <= listaGtSize - 2; j++)
	// {
	// if (listaGt.get(j) < listaLt.get(j + 1))
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(j) + 1);
	// i = j;
	// break b;
	// }
	// }
	// if (tag == null)
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(listaGtSize -1) + 1);
	// listaTags.add(tag);
	// break a;
	// }
	// listaTags.add(tag);
	// }
	// //se existirem caracteres entre o fechamento desta tag e a abertura da
	// pr�xima
	// if (listaLt.get(i + 1) - listaGt.get(i) > 1)
	// {
	// listaTags.add(linha.substring(listaGt.get(i) + 1, listaLt.get(i + 1)));
	// }
	// }
	// else //n�o existem mais tags a serem iteradas
	// {
	// //traz o restante da linha
	// listaTags.add(linha.substring(listaLt.get(i), linha.length()));
	// }
	// }
	// }
	//
	// else if (listaLtSize < listaGtSize)//nem todas as tags que s�o fechadas
	// nesta linha foram abertas na mesma
	// {
	//
	// retorno.append(linha.substring(0, listaGt.get(0) + 1)).append(tagLinha);
	// fechaLinhaAnt = true;
	// listaLt.add(0);
	// Collections.sort(listaLt);
	// listaLtSize = listaLt.size();
	//
	// a: for (int i = 1; i < listaLtSize; i++) //itera em todas as tags
	// {
	// if ( i < (listaLtSize - 1)) //ainda tem tags para serem iteradas
	// {
	// if (listaGt.get(i) < listaLt.get(i + 1)) //a tag fecha antes de ser
	// aberta nova tag
	// {
	// listaTags.add(linha.substring(listaLt.get(i), listaGt.get(i) + 1));
	// }
	// else //se uma tag for aberta dentro de outra
	// {
	// String tag = null;
	// b: for (int j = i; j <= listaGtSize - 2; j++)
	// {
	// if (listaGt.get(j) < listaLt.get(j + 1))
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(j) + 1);
	// i = j;
	// break b;
	// }
	// }
	// if (tag == null)
	// {
	// tag = linha.substring(listaLt.get(i), listaGt.get(listaGtSize -1) + 1);
	// listaTags.add(tag);
	// break a;
	// }
	// listaTags.add(tag);
	// }
	// //se existirem caracteres entre o fechamento desta tag e a abertura da
	// pr�xima
	// if (listaLt.get(i + 1) - listaGt.get(i) > 1)
	// {
	// listaTags.add(linha.substring(listaGt.get(i) + 1, listaLt.get(i + 1)));
	// }
	// }
	// else //n�o existem mais tags a serem iteradas
	// {
	// if (listaGt.get(i) + 1 < linha.length()) //se a tag fecha antes do fim da
	// linha, buscar o restante
	// {
	// listaTags.add(linha.substring(listaGt.get(i) + 2, linha.length()));
	// }
	// }
	// if (listaTags.size() > 0)
	// {
	// retorno.append(tagLinha);
	// }
	// }
	// }
	//
	// for (int i = 0; i < listaTags.size(); i++)
	// {
	// retorno.append("<" + Token.COLUNA + " n=\"" + i + "\"/>\n");
	// retorno.append(listaTags.get(i));
	// }
	//
	//// try
	//// {
	//// BufferedWriter writer = new BufferedWriter(new FileWriter(new
	// File("fonte.txt"), true));
	//// writer.append(retorno.toString());
	//// writer.flush();
	//// } catch (Exception e){}
	//
	// return retorno;
	// }

	/**
	 * M�todo que recupera o conte�do da p�gina via web e a desmonta linha por
	 * linha em um ArrayList.
	 * 
	 * @return Retorna um Arraylist contendo cada linha da p�gina.
	 * @throws IOException
	 *             Erro ao acessar a p�gina.
	 */
	private ArrayList<StringBuilder> desmontar(final URL url) throws IOException {
		InputStreamReader isr = new InputStreamReader(url.openStream());
		ArrayList<StringBuilder> buf = new ArrayList<StringBuilder>();
		BufferedReader in = new BufferedReader(isr);
		while (in.ready()) {
			buf.add(new StringBuilder(in.readLine()));
		}
		in.close();
		return buf;
	}

	/**
	 * M�todo que recebe o conte�do da p�gina via web e a desmonta linha por
	 * linha em um ArrayList.
	 * 
	 * @param texto
	 *            Conte�do da p�gina avaliada.
	 * @return Retorna um Arraylist contendo cada linha da p�gina.
	 */
	private ArrayList<StringBuilder> desmontar(final StringBuilder texto) {
		String[] info = texto.toString().split("\n");
		ArrayList<StringBuilder> buf = new ArrayList<StringBuilder>();
		for (int i = 0; i < info.length; i++) {
			buf.add(new StringBuilder(info[i]));
		}
		return buf;
	}

	/**
	 * Contador de espa�os para se fazer a identa��o do documento pesquisado
	 * ap�s a avalia��o.
	 * 
	 * @param linha
	 *            String que est� sendo avaliada
	 */
	private void contaEspaco(final String linha) {
		if (linha.length() < 1)
			return;
		int index = 0;
		this.tabulacao = 0;
		this.espaco = 0;
		while (index < linha.length()) {
			char letra = linha.charAt(index++);
			switch (letra) {
			case '\t':
				this.tabulacao++;
				break;
			case ' ':
				this.espaco++;
				break;
			default:
				return;
			}
		}
	}

	// public static void main (String args[]) {
	// try {
	// String link = "http://www.acessobrasil.org.br";
	// final int mb = 1024;
	// HttpMethod metodo = null;
	// HttpClient cliente;
	// String type = "";
	// InputStream ist = null;
	// StringBuilder sbd = null;
	// int status;
	// cliente = new HttpClient();
	// metodo = new GetMethod(link);
	// metodo.setRequestHeader("user-agent", "Mozilla/5.0");
	// status = cliente.executeMethod(metodo);
	// Header header = metodo.getResponseHeader("Content-Type");
	// if (header != null) {
	// type = header.getValue();
	// }
	// if ((status == HttpStatus.SC_OK) && (type.toUpperCase().indexOf("TEXT") >
	// -1)) {
	// sbd = new StringBuilder();
	// ist = metodo.getResponseBodyAsStream();
	// byte[] dados = new byte[mb];
	// int bytesLidos = 0;
	// while ((bytesLidos = ist.read(dados)) > 0) {
	// sbd.append(new String(dados, 0, bytesLidos));
	// }
	// ist.close();
	// }
	// metodo.releaseConnection();
	// String code = new Transformar(sbd).tagDefineLinha(link);
	// System.out.println(code);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }

}
