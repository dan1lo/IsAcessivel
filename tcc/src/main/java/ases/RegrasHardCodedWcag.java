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

import java.util.ArrayList;
import java.util.HashMap;

import br.org.acessobrasil.ases.regras.Regra;
import br.org.acessobrasil.silvinha2.mli.RegrasWcag;

/**
 * Contem algoritmos para as Regras WCAG
 * 
 * @author Renato Tomaz Nati
 * @since 27/07/2007
 * @version 1.0
 */
public class RegrasHardCodedWcag extends SuperRegrasHardCoded implements InterfRegrasHardCoded {

	final int NUMERO_DE_GENERICOS = 17;

	private static HashMap<String, Regra> mapRegra = new HashMap<String, Regra>();

	public RegrasHardCodedWcag() {
		getGenericos();
	}

	/**
	 * M�todo que retorna os avisos Gen�ricos WCAG
	 * 
	 * @author Renato Tomaz Nati
	 * @return String[] formato: gl.cp@regrax@prioridade@***@...
	 * @since 27/07/2007
	 * @version 1.0
	 */
	@Override
	public String[] getGenericos() {

		int cont = 0;

		// array que conter� respectivamente: gl, cp
		String glCpPrio[] = new String[3];

		// mapa que conter� respectivamente: o glcp e a String txtRegra
		String[] regras = new String[NUMERO_DE_GENERICOS];

		// adiciona a primeira regra
		glCpPrio[0] = "4";
		glCpPrio[1] = "1";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_15 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "4";
		glCpPrio[1] = "2";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_16 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "11";
		glCpPrio[1] = "1";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_45 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "9";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_47 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "11";
		glCpPrio[1] = "4";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_48 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "12";
		glCpPrio[1] = "3";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_51 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "2";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_54 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "3";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_55 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "4";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_56 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "5";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_57 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "8";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_60 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "13";
		glCpPrio[1] = "10";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_62 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "14";
		glCpPrio[1] = "1";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_63 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "14";
		glCpPrio[1] = "2";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_64 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "14";
		glCpPrio[1] = "3";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_65 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "2";
		glCpPrio[1] = "1";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_6 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "2";
		glCpPrio[1] = "2";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_7 + "@" + glCpPrio[2];

		return regras;

	}

	/**
	 * Retorna o texto da regra Ex: 2.19
	 * 
	 * @param codigo
	 *            c�digo da regra (2.19)
	 * @return Texto da regra
	 */
	@Override
	public String getTextoRegra(String codigo) {
		carregaRegras();
		if (mapRegra == null) {
			System.out.println("Sem regras");
			return "";
		}
		if (codigo == null) {
			System.out.println("Sem codigo");
			return "";
		}
		if (mapRegra.get(codigo) == null) {
			System.out.println("Sem regra " + codigo);
			return "";
		}
		return mapRegra.get(codigo).getTexto();
	}

	/**
	 * Retorna a prioridade da regra Ex: 2.19
	 * 
	 * @param codigo
	 *            c�digo da regra (2.19)
	 * @return Prioridade da regra 1 2 ou 3
	 */
	@Override
	public int getPrioridadeRegra(String codigo) {
		carregaRegras();
		Regra regra = mapRegra.get(codigo);
		if (regra == null) {
			// System.out.print("Regra " +codigo +" n�o encontrada");
			return 0;
		} else {
			return regra.getPrioridade();
		}
	}

	@Override
	public int getCodWcagEmag() {
		return 1;
	}

	public void carregaRegras() {
		if (mapRegra.size() > 0) {
			return;
		}
		mapRegra = RegrasWcag.getHashMap(TokenLang.LANG);
	}

	/**
	 * Agora utiliza o MLI
	 * 
	 * @deprecated
	 */
	@Deprecated
	public void carregaRegrasBkp() {
		// evita o carregamento mais de uma vez
		if (mapRegra.size() > 0) {
			return;
		}

		// endere�o para consulta:
		// http://www.w3.org/TR/1999/WAI-WEBCONTENT-19990505/

		// Recomenda��o 1 - Fornecer alternativas ao conte�do sonoro e visual
		mapRegra.put("1.1", new Regra(1,
				"Fornecer um equivalente textual a cada elemento n�o textual (por ex., por meio de �alt� ou �longdesc�, ou como parte do conte�do do elemento). Isso abrange: imagens, representa��es gr�ficas do texto (incluindo s�mbolos), regi�es de mapa de imagem, anima��es (por ex., GIF animados), applets e objetos programados, arte ASCII, frames, programas interpret�veis, imagens utilizadas como sinalizadores de pontos de enumera��o, espa�adores, bot�es gr�ficos, sons (reproduzidos ou n�o com intera��o do usu�rio), arquivos de �udio independentes, trilhas �udio de v�deo e trechos de v�deo."));
		mapRegra.put("1.2", new Regra(1,
				"Fornecer links de texto redundantes relativos a cada regi�o ativa de um mapa de imagem armazenado no servidor."));
		mapRegra.put("1.3", new Regra(1,
				"Fornecer uma descri��o sonora das informa��es importantes veiculadas em trechos visuais das apresenta��es multim�dia."));
		mapRegra.put("1.4", new Regra(1,
				"Em apresenta��es multim�dia baseadas em tempo (filme ou anima��o), sincronizar, sempre que poss�vel, os textos alternativos equivalentes (legendas ou descri��es sonoras dos trechos visuais) e da apresenta��o."));
		mapRegra.put("1.5", new Regra(3,
				"N�o � suficiente fornecer descri��es textuais atrav�s do atributo ALT dos links encontrados no elemento AREA, criado no mapa de imagens atrav�s de uma figura geom�trica. Para assegurar que qualquer pessoa possa navegar no seu s�tio, ainda � necess�rio colocar links redundantes em formato texto."));

		// Recomenda��o 2 - N�o recorrer apenas � cor
		mapRegra.put("2.1", new Regra(1,
				"Assegure a acessibilidade de objetos programados, tais como programas interpret�veis e applets, garantindo que a resposta a eventos seja independente do dispositivo de entrada e que qualquer elemento dotado de interface pr�pria possa funcionar com qualquer leitor de tela ou navegador que o usu�rio utilize. Evite colocar scripts que estejam vinculados a links. Se isso n�o for poss�vel, forne�a informa��es equivalentes em uma p�gina alternativa acess�vel."));
		// 2.2:prioridade 2 para imagens e prioridade 3 para textos
		mapRegra.put("2.2", new Regra(2,
				"Assegurar que a combina��o de cores entre o fundo e o primeiro plano seja suficientemente contrastante para ser vista por pessoas com cromo defici�ncias, bem como pelas que utilizam monitores de v�deo monocrom�ticos."));

		// Recomenda��o 3 - Utilizar corretamente marca��es e folhas de estilo

		mapRegra.put("3.1", new Regra(2,
				"Sempre que existir uma linguagem de marca��o apropriada, utilizar marca��es em vez de imagens para transmitir informa��es. Evite tamb�m usar imagens para representar texto, prefira folhas de estilo."));
		mapRegra.put("3.2", new Regra(2,
				"Criar documentos pass�veis de valida��o pelos padr�es formais, publicados pelo W3C. Caso seja necess�rio desenvolver uma p�gina com uma gram�tica em particular, use a instru��o DOCTYPE na primeira linha do seu arquivo HTML. Esse tipo de instru��o informa aos servidores, navegadores e validadores que o c�digo est� dentro das regras da linguagem informada."));
		mapRegra.put("3.4", new Regra(2,
				"Utilizar unidades relativas, e n�o absolutas, nos valores dos atributos da linguagem de marca��o e nos valores das propriedades das folhas de estilo. Em CSS n�o use valores absolutos como �pt� ou �cm� e sim valores relativos como o �em�, \"ex\" ou em porcentagem."));
		mapRegra.put("3.5", new Regra(2,
				"Use elementos de cabe�alho para veicular a l�gica e hier�rquica da estrutura e use-os de acordo com as especifica��es. Utilize os cabe�alhos de forma ordenada."));
		mapRegra.put("3.6", new Regra(2,
				"Use a nota��o para listas e use os seus itens corretamente. Use corretamente a estrutura e os itens das listas. Evitar o uso destes elementos para formatar par�grafos. Quando necess�rio use listas numeradas."));
		mapRegra.put("3.7", new Regra(2,
				"Marcar as cita��es. N�o utilizar marca��es de cita��o para efeitos de formata��o, como, por exemplo, o avan�o de texto."));

		// Recomenda��o 4 - Indicar claramente qual o idioma utilizado
		mapRegra.put("4.1", new Regra(1,
				"Identificar claramente quaisquer mudan�as de idioma no texto de um documento, bem como nos equivalentes textuais (por ex., legendas). Use o atributo �lang� para identificar claramente as altera��es do idioma no texto."));
		mapRegra.put("4.2", new Regra(3,
				"Especificar por extenso cada abreviatura ou sigla quando da sua primeira ocorr�ncia em um documento. Quando usar uma abreviatura ou uma sigla numa frase, n�o se esque�a de o indicar no HTML usando ABBR e ACRONYM"));
		mapRegra.put("4.3", new Regra(3,
				"Identificar o principal idioma utilizado nos documentos. O idioma do documento deve ser especificado na express�o HTML."));

		// Recomenda��o 5 - Criar tabelas pass�veis de transforma��o harmoniosa

		mapRegra.put("5.1", new Regra(1, "Em tabelas de dados, identificar os cabe�alhos de linha e de coluna."));
		mapRegra.put("5.2", new Regra(1,
				"Em tabelas de dados com dois ou mais n�veis l�gicos de cabe�alhos de linha ou de coluna, utilizar marca��es para associar as c�lulas de dados �s c�lulas de cabe�alho (atributo id/headers). Organize tabelas complexas de forma que se possa identificar facilmente suas divis�es."));
		mapRegra.put("5.3", new Regra(2,
				"N�o utilizar tabelas para efeitos de disposi��o em p�gina, a n�o ser que a tabela continue a fazer sentido depois de ser linearizada. Se n�o for o caso, fornecer um equivalente alternativo (que pode ser uma vers�o linearizada)."));
		mapRegra.put("5.4", new Regra(2,
				"Fornecer resumos das tabelas. Utilize o atributo SUMMARY dentro das tabelas para descrever o conte�do de tabelas."));
		mapRegra.put("5.5", new Regra(3,
				"Fornecer abreviaturas para os r�tulos de cabe�alho. Use o atributo ABBR dentro do elemento TH quando voc� tiver cabe�alhos muito longos. Os leitores de tela quando acharem o ABBR ler�o apenas o seu conte�do e n�o o texto do cabe�alho na integra."));

		// Recomenda��o 6 - Assegurar que as p�ginas dotadas de novas
		// tecnologias sejam transformadas harmoniosamente
		mapRegra.put("6.1", new Regra(1,
				"Organizar os documentos de tal forma que possam ser lidos sem recurso em folhas de estilo. Por exemplo, se um documento em HTML for reproduzido sem as folhas de estilo que lhe est�o associadas, deve continuar a ser poss�vel l�-lo."));
		mapRegra.put("6.2", new Regra(1,
				"Assegurar que os equivalentes de conte�do din�mico sejam atualizados sempre que esse conte�do mudar. Construa o Frame sempre baseado num documento HTML."));
		mapRegra.put("6.3", new Regra(1,
				"Assegurar que todas as p�ginas possam ser utilizadas mesmo que os programas interpret�veis, os applets ou outros objetos programados tenham sido desativados ou n�o sejam suportados. Se isso n�o for poss�vel, fornecer informa��es equivalentes em uma p�gina alternativa, acess�vel. Evite colocar scripts que executem no navegador do usu�rio. Pode ser que seja um navegador que n�o suporte scripts. Evite a cria��o de links que usem �javascript� tais como URI. Se um usu�rio n�o usar scripts, ent�o n�o ser� capaz de encontrar os links, uma vez que o navegador n�o consegue criar o conte�do link."));
		mapRegra.put("6.4", new Regra(2,
				"Em programas interpret�veis e applets, assegurar que a resposta a eventos seja independente do dispositivo de entrada. Sempre que tiver script associe o elemento NOSCRIPT."));
		mapRegra.put("6.5", new Regra(2,
				"Assegurar a acessibilidade do conte�do din�mico ou fornecer apresenta��o ou p�gina alternativa."));

		// Recomenda��o 7 - Assegurar o controle do usu�rio sobre as altera��es
		// temporais do conte�do

		mapRegra.put("7.1", new Regra(1,
				"Evitar concep��es que possam provocar intermit�ncia da tela, at� que o usu�rio possam acessar o controle para interromper a seq��ncia. Pessoas com epilepsia fotosensitiva podem desencadear um ataque epil�ptico com o cintilar ou piscar numa banda de 4 a 59 intermit�ncias por segundo (Hertz), com um pico nas 20 intermit�ncias por segundo, bem como com altera��es r�pidas do escuro para a luz (como sucede com as l�mpadas das discotecas)."));
		mapRegra.put("7.2", new Regra(2,
				"Evitar situa��es que possam provocar o piscar do conte�do das p�ginas (isto �, alterar a apresenta��o a intervalos regulares, como ligar e desligar), possibilitar que o software do usu�rio possa ter o controle desse efeito."));
		mapRegra.put("7.3", new Regra(2,
				"Evitar p�ginas contendo movimento, at� que os softwares do usu�rio possibilitem a imobiliza��o do conte�do."));
		mapRegra.put("7.4", new Regra(2,
				"N�o criar p�ginas de atualiza��o autom�tica peri�dica, at� que os agentes do usu�rio possibilitem parar essa atualiza��o."));
		mapRegra.put("7.5", new Regra(2,
				"N�o utilizar marca��es para redirecionar as p�ginas automaticamente, at� que os softwares do usu�rio possibilitem parar o redirecionamento autom�tico. Ao inv�s de utilizar marca��es, configurar o servidor para que execute os redirecionamentos."));

		// Recomenda��o 8 - Assegurar a acessibilidade direta de interfaces do
		// usu�rio integradas

		// 8.1:[prioridade 1 se a funcionalidade for importante e n�o estiver
		// presente em outro local; prioridade 2, se n�o for o caso].
		mapRegra.put("8.1", new Regra(1,
				"Criar elementos de programa��o, tais como programas interpret�veis e applets, diretamente acess�veis pelas tecnologias de apoio ou com elas compat�veis."));

		// Recomenda��o 9 - Projetar p�ginas considerando a independ�ncia de
		// dispositivos
		mapRegra.put("9.3", new Regra(1,
				"Fornecer mapas de imagem armazenados no cliente ao inv�s de no servidor, exceto quando as regi�es n�o puderem ser definidas por forma geom�trica dispon�vel."));
		mapRegra.put("9.2", new Regra(2,
				"Assegurar que qualquer elemento dotado de interface pr�pria possa funcionar de modo independente de equipamentos, dispositivos ou qualquer hardware."));
		mapRegra.put("9.3", new Regra(2,
				"Em programas interpret�veis, especificar respostas a eventos, preferindo-as a rotinas dependentes de dispositivos (mouse).�	Use �onmousedown� com �onkeydown�. Use �onmouseup� com �onkeyup�, Use �onclick� com �onkeypress� , Note que n�o existe equivalente de teclado para duplo-click (�ondblclick�) em HTML 4.0."));
		mapRegra.put("9.4", new Regra(3,
				"Criar uma seq��ncia l�gica de tabula��o para percorrer links, controles de formul�rios e objetos. A tecla TAB � usada tamb�m, dentro do navegador para se movimentar dentro das p�ginas. Com isso elas devem ser dispostas para que sejam navegadas de forma linear. O comando TABINDEX permite que voc� estabele�a uma ordem l�gica. O TABINDEX trabalha com os elementos <A>, <AREA>, <BUTTON>, <INPUT>, <OBJECT>, <SELECT> e <TEXTAREA>."));
		mapRegra.put("9.5", new Regra(3,
				"Fornecer atalhos por teclado que apontem para links importantes (incluindo os contidos em mapas de imagem armazenados no cliente), controles de formul�rios, grupo de controles de formul�rios, menus e conte�do. Voc� pode permitir que o usu�rio possa saltar ou ir diretamente a campos do formul�rio atrav�s do comando ACCESSKEY."));

		// Recomenda��o 10 - Utilizar solu��es de transi��o
		mapRegra.put("10.1", new Regra(2,
				"N�o provocar o aparecimento de janelas de sobreposi��o, janelas popup ou outras quaisquer, assim como nenhuma modifica��o do conte�do sem que o usu�rio seja informado disso. N�o � recomend�vel para o usu�rio, que links abram em uma nova janela. Se o usu�rio utiliza um navegador com tela cheia n�o poder� voltar para a p�gina anterior, tamb�m o hist�rico e a possibilidade de ir e voltar a p�ginas visitadas ficam comprometidos. Caso voc� tenha uma real necessidade que sua p�gina abra uma outra janela, informe ao usu�rio."));
		mapRegra.put("10.2", new Regra(2,
				"Assegurar o correto posicionamento de todos os controles de formul�rios que tenham r�tulos implicitamente associados, at� que os agentes do usu�rio venham a suportar associa��es expl�citas entre r�tulos e controles de formul�rios. Associe legendas aos controles dos formul�rios de forma que a informa��o seja clara."));
		mapRegra.put("10.3", new Regra(3,
				"Proporcionar uma alternativa de texto linear (na mesma ou em outra p�gina), em rela��o a todas as tabelas que apresentem o texto em colunas paralelas e com translinea��o, at� que os agentes do usu�rio (incluindo as tecnologias de apoio) reproduzam corretamente texto colocado lado a lado. Ofere�a uma alternativa para as pessoas que disponham de leitores de tela e que n�o interpretem tabelas corretamente."));
		mapRegra.put("10.4", new Regra(3,
				"Incluir caracteres predefinidos de preenchimento nas caixas de edi��o e nas �reas de texto, at� que os agentes do usu�rio tratem corretamente os controles vazios. Alguns navegadores antigos n�o permitem que a tecla TAB seja usada para movimenta��o dentro de formul�rios. Para isso coloque um texto no campo do formul�rio para que o campo seja localizado de forma mais f�cil."));
		mapRegra.put("10.5", new Regra(3,
				"Inserir, entre links adjacentes, caracteres que n�o funcionem como link e sejam pass�veis de impress�o (com um espa�o de in�cio e outro de fim), at� que os softwares do usu�rio (incluindo as tecnologias de apoio) reproduzam clara e distintamente os links adjacentes. Quando existem muitos links numa mesma linha, separe-os com caracteres de forma a criar mais espa�o entre eles. Isso criar� uma pausa maior entre os links quando houver um leitor de tela passando entre os links."));

		// Recomenda��o 11 - Utilizar tecnologias e recomenda��es do W3C
		mapRegra.put("11.1", new Regra(2,
				"Utilizar tecnologias do W3C sempre dispon�veis e adequadas a uma determinada tarefa; utilizar as vers�es mais recentes, desde que suportadas."));
		mapRegra.put("11.2", new Regra(2, "N�o usar funcionalidades desatualizadas de tecnologias do W3C."));
		mapRegra.put("11.3", new Regra(3,
				"Fornecer informa��es que possibilitem aos usu�rios receber os documentos de acordo com as suas prefer�ncias (por ex., por idioma ou por tipo de conte�do)."));
		mapRegra.put("11.4", new Regra(1,
				"Se, apesar de todos os esfor�os, n�o for poss�vel criar uma p�gina acess�vel, momentaneamente fornecer um link a uma p�gina alternativa que utilize tecnologias do W3C, seja acess�vel, contenha informa��es (ou funcionalidade) equivalentes e seja atualizada t�o freq�entemente quanto a p�gina original, considerada inacess�vel."));

		// Recomenda��o 12 - Fornecer informa��es de contexto e orienta��es.
		mapRegra.put("12.1",
				new Regra(1, "Dar, a cada frame, um t�tulo que facilite a identifica��o dos frames e sua navega��o."));
		mapRegra.put("12.2", new Regra(2,
				"Descrever a finalidade dos frames e o modo como se relacionam entre si, se isso n�o for �bvio a partir unicamente dos t�tulos. Caso seja necess�rio, explicar com mais detalhes sobre a finalidade de cada frame, use o atributo LONGDESC para complementar a informa��o."));
		mapRegra.put("12.3", new Regra(2,
				"Dividir grandes blocos de informa��o em grupos mais f�ceis de gerenciar, sempre que for o caso. Por exemplo, em HTML, use OPTGROUP para agrupar os elementos OPTION dentro de um elemento de lista SELECT; agrupe os controles de formul�rio com FIELDSET e LEGEND; use listas sempre que seja apropriado; use cabe�alhos para estruturar documentos, etc."));
		mapRegra.put("12.4", new Regra(2,
				"Associar explicitamente os r�tulos aos respectivos controles. Use o comando LABEL para associar campos nos formul�rios. Fazendo isso os leitores de tela associar�o os elementos dos formul�rios de forma correta."));

		// Recomenda��o 13 - Fornecer mecanismos de navega��o claros

		mapRegra.put("13.1", new Regra(2,
				"Identificar claramente o destino de cada link. O texto do link deve ser facilmente compreens�vel e conciso para que tenha sentido quando for lido, mesmo fora do dispositivos padr�o. Como por exemplo, um leitor de tela."));
		mapRegra.put("13.2",
				new Regra(2, "Fornecer metadados para acrescentar informa��es sem�nticas a p�ginas ou sites."));
		mapRegra.put("13.3", new Regra(2,
				"Dar informa��es sobre a organiza��o geral de um s�tio (por ex., por meio de um mapa do s�tio ou de um sum�rio). Crie um mapa do sitio de forma textual. Mas crie associando aos t�tulos das p�ginas. Isso n�o ir� causar confus�o aos usu�rios que utilizarem leitores de tela."));
		mapRegra.put("13.4", new Regra(2, "Utilizar os mecanismos de navega��o de maneira coerente e sistem�tica."));
		mapRegra.put("13.5", new Regra(3,
				"Fornecer barras de navega��o para destacar e dar acesso ao mecanismo de navega��o. Deve-se indicar ao usu�rio as etapas do caminho percorrido durante a navega��o, oferecendo a op��o de voltar a qualquer das partes."));
		mapRegra.put("13.6", new Regra(3,
				"Agrupar links relacionados entre si, identificar o grupo (em benef�cio dos agentes do usu�rio) e, at� que os softwares do usu�rio se encarreguem de tal fun��o, fornecer um modo de contornar determinado grupo."));
		mapRegra.put("13.7", new Regra(3,
				"Se forem oferecidas fun��es de pesquisa, ativar diferentes tipos de pesquisa de modo a corresponderem a diferentes n�veis de compet�ncia e �s prefer�ncias dos usu�rios."));
		mapRegra.put("13.8",
				new Regra(3, "Colocar informa��es identificativas no in�cio de cabe�alhos, par�grafos, listas."));
		mapRegra.put("13.9", new Regra(3,
				"Fornecer informa��es sobre cole��es de documentos (isto �, documentos compostos por v�rias p�ginas)."));
		mapRegra.put("13.10", new Regra(3, "Fornecer meios para ignorar inser��es de arte ASCII com v�rias linhas."));

		// Recomenda��o 14 - Assegurar a clareza e a simplicidade dos
		// documentos.

		mapRegra.put("14.1",
				new Regra(1, "Utilizar linguagem a mais clara e simples poss�vel, adequada ao conte�do do s�te."));
		mapRegra.put("14.2", new Regra(3,
				"Complementar o texto com apresenta��es gr�ficas ou sonoras, sempre que facilitarem a compreens�o da p�gina. Incluir, por exemplo imagens, anima��es ou v�deos em apresenta��es facilita e torna especialmente �til para analfabetos, surdos que podem visualizar as apresenta��es visuais."));
		mapRegra.put("14.3", new Regra(3,
				"Criar um estilo de apresenta��o coerente e sistem�tico, ao longo das diferentes p�ginas."));

	}

	public ArrayList<String> getArrCod() {
		carregaRegras();
		return new ArrayList<String>(mapRegra.keySet());
	}

}