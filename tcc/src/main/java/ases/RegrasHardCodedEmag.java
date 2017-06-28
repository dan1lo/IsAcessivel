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

import java.util.ArrayList;
import java.util.HashMap;

import br.org.acessobrasil.ases.regras.Regra;

/**
 * Contem algoritmos para as Regras EMAG
 * 
 * @author Renato Tomaz Nati
 * @since 27/07/2007
 * @version 1.0
 */
public class RegrasHardCodedEmag extends SuperRegrasHardCoded implements InterfRegrasHardCoded {
	final int NUMERO_DE_GENERICOS = 18;
	// private String[] regrasGenericas = new String[NUMERO_DE_GENERICOS];

	/**
	 * Guarda as regras e seu c�digo
	 */
	private static HashMap<String, Regra> mapRegra = new HashMap<String, Regra>();

	public RegrasHardCodedEmag() {
		// getGenericos();
	}

	/**
	 * M�todo que retorna os avisos Gen�ricos EMAG
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
		glCpPrio[0] = "1";
		glCpPrio[1] = "2";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_15 + "@" + glCpPrio[2];

		cont++;

		// adiciona a segunda regra

		glCpPrio[0] = "3";
		glCpPrio[1] = "2";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_16 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "3";
		glCpPrio[1] = "5";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_47 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "24";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_48 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "2";
		glCpPrio[1] = "11";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_51 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "3";
		glCpPrio[1] = "14";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_54 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "2";
		glCpPrio[1] = "17";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_55 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "10";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_56 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "3";
		glCpPrio[1] = "6";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_57 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "3";
		glCpPrio[1] = "9";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_60 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "3";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_62 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "9";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_63 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "3";
		glCpPrio[1] = "11";
		glCpPrio[2] = "3";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_64 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "2";
		glCpPrio[1] = "9";
		glCpPrio[2] = "2";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_65 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "21";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_67 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "4";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_6 + "@" + glCpPrio[2];

		cont++;

		glCpPrio[0] = "1";
		glCpPrio[1] = "5";
		glCpPrio[2] = "1";
		regras[cont] = glCpPrio[0] + "." + glCpPrio[1] + "@" + TokenLang.REGRA_7 + "@" + glCpPrio[2];

		// regrasGenericas = (String[]) regras.clone();
		// +2 prioridade1
		// +1 prioridade 3
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
		return mapRegra.get(codigo).getTexto();
	}

	/**
	 * Retorna a prioridade da regra Ex: 2.19
	 * 
	 * @param codigo
	 *            c&oacute;digo da regra (2.19)
	 * @return Prioridade da regra 1 2 ou 3
	 */
	@Override
	public int getPrioridadeRegra(String codigo) {
		carregaRegras();
		Regra regra = mapRegra.get(codigo);
		if (regra == null) {
			// System.out.print("Regra " +codigo +" n\u00e1o encontrada");
			return 0;
		} else {
			return regra.getPrioridade();
		}
	}

	public ArrayList<String> getArrCod() {
		carregaRegras();
		return new ArrayList<String>(mapRegra.keySet());
	}

	/**
	 * Carrega as regras
	 *
	 */
	public void carregaRegras() {
		// evita o carregamento mais de uma vez
		if (mapRegra.size() > 0) {
			return;
		}
		// 2.15 label

		mapRegra.put("1.1", new Regra(1,
				"Identificar o principal idioma utilizado nos documentos. O idioma do documento deve ser especificado na express\u00e3o HTML."));
		mapRegra.put("1.2", new Regra(1,
				"Identificar claramente quaisquer mudan\u00e7as de idioma no texto de um documento, bem como nos equivalentes textuais (por ex., legendas de imagens). Use o atributo \"lang\" para identificar claramente as altera\u00e7\u00e3es do idioma no texto."));
		mapRegra.put("1.3", new Regra(1,
				"Fornecer meios para ignorar e explicar inser\u00e7\u00f5es de arte ASCII com v\00e1rias linhas, tais como links de \u00e2ncora ou p\00e1ginas alternativas."));
		mapRegra.put("1.4", new Regra(1,
				"Assegurar que todas as informa\u00e7\u00f5es veiculadas com cor estejam tamb\u00eam dispon\u00edveis sem cor."));
		// Geral por enquanto
		// Contraste
		mapRegra.put("1.5", new Regra(1,
				"Assegurar que a combina\u00e7\u00e3o de cores entre o fundo e o primeiro plano seja suficientemente contrastante para poder ser vista por pessoas com cromodefici\u00eancias, bem como pelas que utilizam monitores de v\00eddeo monocrom\00e1ticos."));

		mapRegra.put("1.6", new Regra(1,
				"Organizar os documentos de tal forma que possam ser lidos sem recurso \u00e0 folhas de estilo. Por exemplo, se um documento em HTML for reproduzido sem as folhas de estilo que lhe est\u00e3o associadas, deve continuar a ser poss\u00edvel l\u00ea-lo."));
		mapRegra.put("1.7", new Regra(1,
				"N\u00e3o usar concep\u00e7\00f5es que possam provocar intermit\00eancia da tela, at\u00e9 que os leitores de tela ou navegadores do usu\u00e1rio possibilitem o seu controle."));
		mapRegra.put("1.8", new Regra(1,
				"Criar uma sequ\00eancia l\u00f3gica de tabula\u00e7\\u00e3o para percorrer links, controles de formul\u00e1rios e objetos."));
		mapRegra.put("1.9", new Regra(1,
				"Utilizar a linguagem mais clara e simples poss\u00edvel, logicamente, adequada ao conte\u00fado do s\u00edtio."));
		mapRegra.put("1.10", new Regra(1,
				"Utilizar os mecanismos de navega\u00e7\u00e3o de maneira coerente, consistente e sistem\u00e1tica. Por exemplo, organizar itens do menu por tema, se\u00e7\u00f5es ou classes, etc."));
		mapRegra.put("1.11", new Regra(1,
				"Fornecer um equivalente textual a cada imagem (isso abrange: representa\u00e7\u00f5es gr\u00e1ficas do texto, incluindo s\u00edmbolos, GIFs animados, imagens utilizadas como sinalizadores de pontos de enumera\u00e7\u00e3o, espa\u00e7adores e bot\u00f5es gr\u00e1ficos), para tanto, utiliza-se o atributo \"alt\" ou \"longdesc\" em cada imagem."));
		mapRegra.put("1.12", new Regra(1,
				"Fornecer links de texto redundantes relativos a cada regi\u00e3o ativa de um mapa de imagem armazenado tanto no cliente quanto no servidor. N\u00e3o esquecendo de adicionar texto equivalente \u00e0 imagem mostrada, no caso o \"alt\" ou \"longdesc\"."));
		mapRegra.put("1.13", new Regra(1,
				"Fornecer resumos das tabelas utilizando o atributo \"summary\", caso seja criada uma tabela para dados. Se a tabela foi criada para efeito de design, deixar o \"summary\" em branco."));
		mapRegra.put("1.14", new Regra(1,
				"Em tabelas de dados com dois ou mais n\u00edveis l\u00f3gicos de cabe\u00e7alhos, sejam de linha ou de coluna, utilizar marca\u00e7\u00f5es para associar as c\u00e9lulas de dados \u00e0s c\u00e9lulas de cabe\u00e7alho. Organize tabelas complexas de forma que possa identificar facilmente suas divis\u00f5es."));
		mapRegra.put("1.15", new Regra(1,
				"Assegurar que os equivalentes de conte\u00fado dos frames (din\u00e2mico ou N\u00e3o) sejam atualizados sempre que esse conte\u00fado mudar. A origem do frame sempre deve estar ligada a um arquivo HTML."));
		mapRegra.put("1.16", new Regra(1,
				"Assegurar a acessibilidade do conte\u00fado de frames, fornecendo uma p\u00e1gina alternativa atrav\u00e9s do elemento \"noframes\"."));
		mapRegra.put("1.17", new Regra(1,
				"Dar a cada frame um t\u00edtulo que facilite a identifica\u00e7\u00e3o dos frames e sua navega\u00e7\u00e3o."));
		mapRegra.put("1.18", new Regra(1,
				"Descrever a finalidade dos frames e o modo como se relacionam entre si, se isso N\u00e3o for \u00f3bvio a partir unicamente dos t\u00edtulos, forne\u00e7a uma descri\u00e7\u00e3o mais detalhada."));
		mapRegra.put("1.19", new Regra(1,
				"Assegure a acessibilidade de objetos programados, tais como programas interpret\u00e1veis e applets, garantindo que a resposta a eventos seja independente do dispositivo de entrada e que qualquer elemento dotado de interface pr\u00f3pria possa funcionar com qualquer leitor de tela ou navegador que o usu\u00e1rio utilize. Evite colocar scripts que estejam vinculados a links, se isso N\u00e3o for poss\u00edvel, fornecer informa\u00e7\u00f5es equivalentes em uma p\u00e1gina alternativa acess\u00edvel."));
		mapRegra.put("1.20", new Regra(1,
				"Assegurar que todas as p\u00E1ginas possam ser utilizadas mesmo que os programas interpret\u00E1veis, os applets ou outros objetos programados tenham sido desativados ou n\u00E3o sejam suportados. Sempre que tiver script associe logo a seguir o elemento \"noscript\"."));
		mapRegra.put("1.21", new Regra(1,
				"Fornecer equivalentes textuais para sons (reproduzidos ou N\u00e3o com intera\u00e7\u00e3o do usu\u00e1rio), arquivos de \u00e1udio independentes, trilhas \u00e1udio de v\00eddeo e trechos de v\00eddeo."));
		mapRegra.put("1.22", new Regra(1,
				"Em apresenta\u00e7\u00f5es multim\u00eddia baseadas em tempo (filme ou anima\u00e7\u00e3o), fornecer ou sincronizar alternativas textuais equivalentes (legendas ou descri\u00e7\u00f5es sonoras dos trechos visuais)."));
		mapRegra.put("1.23", new Regra(1,
				"Evitar p\00e1ginas contendo movimento, at\u00e9 que os agentes do usu\u00e1rio possibilitem o controle e a imobiliza\u00e7\u00e3o do conte\u00fado."));
		mapRegra.put("1.24", new Regra(1,
				"N\u00e3o sendo poss\u00edvel criar uma p\u00e1gina acess\u00edvel, crie uma p\u00e1gina alternativa, juntamente com uma justificativa apropriada, que utilize tecnologias em conformidade com este documento - acess\u00edvel, que contenha informa\u00e7\u00f5es (ou funcionalidade) equivalentes e seja atualizada t\u00e3o frequentemente quanto a p\u00e1gina original, considerada inacess\u00edvel."));
		mapRegra.put("2.1", new Regra(2,
				"Criar documentos pass\u00edveis de valida\u00e7\u00e3o por gram\u00e1ticas formais publicadas. Declarando o tipo de documento (atributo \"doctype\") no topo do c\u00f3digo fonte de cada p\u00e1gina do s\u00edtio. Assim seu s\u00edtio informar\u00e1 aos servidores, navegadores e validadores que o c\u00f3digo est\u00e1 dentro das regras da linguagem utilizada."));
		mapRegra.put("2.2", new Regra(2,
				"Utilizar unidades relativas, e N\u00e3o absolutas, nos valores dos atributos de tabelas, textos, etc. Em CSS N\u00e3o use valores absolutos como \"pt\" ou \"px\" e sim valores relativos como o \"em\", \"ex\" ou em porcentagem."));
		mapRegra.put("2.3", new Regra(2,
				"Marcar corretamente listas e pontos de enumera\u00e7\u00e3o em listas ordenadas. Use corretamente a estrutura e os itens das listas. Evite o uso destes elementos para formatar par\u00e1grafos."));
		mapRegra.put("2.4", new Regra(2,
				"N\u00e3o criar p\00e1ginas com atualiza\u00e7\u00e3o autom\u00e1tica peri\u00f3dica, at\u00e9 que os leitores de tela ou navegadores possibilitem o controle da atualiza\u00e7\u00e3o para o usu\u00e1rio. N\u00e3o utilize a tag meta \"refresh\" ou dispositivos semelhantes para atualiza\u00e7\u00e3o da p\u00e1gina. Caso a p\u00e1gina seja continuamente atualizada, informe ao usu\u00e1rio que ele deve \"recarregar\" a p\u00e1gina de tempos em tempos."));
		mapRegra.put("2.5", new Regra(2,
				"N\u00e3o utilizar marca\u00e7\u00f5es para redirecionar as p\00e1ginas automaticamente, at\u00e9 que os leitores de tela ou navegadores do usu\u00e1rio possibilitem interromper o processo."));
		mapRegra.put("2.6", new Regra(2,
				"N\u00e3o provocar o aparecimento de janelas de sobreposi\u00e7\u00e3o, janelas popup ou outras quaisquer, assim como nenhuma modifica\u00e7\u00e3o do conte\u00fado sem que o usu\u00e1rio seja informado disso. N\u00e3o \u00e9 recomend\u00e1vel para o usu\u00e1rio, que links abram em uma nova janela. Se o usu\u00e1rio utiliza um navegador com tela cheia N\u00e3o poder\u00e1 voltar para a p\u00e1gina anterior, tamb\u00eam o hist\u00f3rico e a possibilidade de ir e voltar a p\00e1ginas visitadas ficam comprometidos. Caso voc\u00ea tenha uma real necessidade que sua p\u00e1gina abra uma outra janela, informe ao usu\u00e1rio."));
		mapRegra.put("2.7", new Regra(2,
				"Sempre que existir uma linguagem de marca\u00e7\u00e3o apropriada, utilizar marca\u00e7\u00f5es em vez de imagens para transmitir informa\u00e7\u00f5es, um exemplo \u00e9 a linguagem MathML que permite a cria\u00e7\u00e3o de f\u00f3rmulas matem\u00e1ticas somente utilizando-se das tags apropriadas."));
		mapRegra.put("2.8", new Regra(2,
				"Utilizar o elemento \"blockquote\" para marcar cita\u00e7\u00f5es quando existentes. N\u00e3o use \"blockquote\", \"ul\", \"dl\" & \"dt\", \"table\" e outros elementos para criar efeitos visuais nos par\u00e1grafos. Caso a inten\u00e7\u00e3o seja organizar a estrutura ou a disposi\u00e7\u00e3o de textos no s\u00edtio, utilize folhas de estilo."));
		mapRegra.put("2.9", new Regra(2,
				"Criar um estilo de apresenta\u00e7\u00e3o coerente e sistem\u00e1tico, ao longo das diferentes p\00e1ginas, como exemplo, mantendo um padr\u00e3o de desenho, agrupando os itens do menu de forma coerente. Mantenha os bot\u00f5es principais de navega\u00e7\u00e3o no mesmo local em cada p\u00e1gina. Isso ajudar\u00e1 ao usu\u00e1rio a localizar-se rapidamente, e saber o destino de cada bot\u00e3o levar\u00e1. Mantenha para o s\u00edtio uma paleta de cores, estilos de texto e diagrama\u00e7\u00e3o consistente. Identifique as regi\u00f5es da p\u00e1gina, navega\u00e7\u00e3o e fim de p\u00e1gina de forma clara."));
		mapRegra.put("2.10", new Regra(2,
				"Utilize elementos de cabe\u00e7alho de forma l\u00f3gica, organizando o conte\u00fado de acordo com uma hierarquia."));
		mapRegra.put("2.11", new Regra(2,
				"Sempre que necess\u00e1rio, divida grandes blocos de informa\u00e7\u00e3o em grupos mais f\u00e1ceis de gerenciar. As op\u00e7\u00f5es de menu devem ser dispostas de forma consistente na mesma ordem relativa no grupo de op\u00e7\u00f5es. Se as op\u00e7\u00f5es num painel de menu est\u00e3o ordenadas \"arquivo, editar, inserir, imprimir\", essas op\u00e7\u00f5es devem aparecer naquela mesma ordem quando aquele grupo for apresentado novamente (ou quando um outro painel contendo aquele mesmo grupo de op\u00e7\u00f5es seja apresentado)."));
		mapRegra.put("2.12", new Regra(2,
				"N\u00e3o utilizar tabelas para efeitos de disposi\u00e7\u00e3o em p\u00e1gina, prefira o uso de folhas de estilo para a diagrama\u00e7\u00e3o das p\00e1ginas. Sendo utilizadas tabelas construa de forma que a disposi\u00e7\u00e3o continue a fazer sentido depois de ser linearizada. Em \u00faltimo caso, forne\u00e7a um equivalente alternativo (que pode ser uma vers\u00e3oo linearizada)."));
		mapRegra.put("2.13", new Regra(2,
				"Se for utilizada uma tabela para efeitos de disposi\u00e7\u00e3o em p\u00e1gina, N\u00e3o utilizar qualquer marca\u00e7\u00e3o estrutural para efeitos de formata\u00e7\u00e3o visual. N\u00e3o use comandos destinados a indicar cabe\u00e7alhos de tabela como o \"th\" para formatar par\u00e1grafos ou fazer t\u00edtulos em \"bold\"."));
		mapRegra.put("2.14", new Regra(2,
				"Incluir caracteres pr\u00e9-definidos de preenchimento nas caixas de edi\u00e7\u00e3o e nas \u00e1reas de texto, at\u00e9 que os navegadores tratem corretamente os controles vazios."));
		mapRegra.put("2.15", new Regra(2,
				"Usar o elemento \"label\" juntamente com o atributo \"id\" para associar os r\u00f3tulos aos respectivos controles dos formul\u00e1rios. Assim, os leitores de tela associar\u00e3o os elementos do formul\u00e1rio de forma correta. Usando o comando \"label\" as pessoas que usam leitores de tela N\u00e3o ter\u00e3o problemas ao ler o formul\u00e1rio. Caso haja grupos de informa\u00e7\u00e3o, controles, etc, a estes devem estar devidamente diferenciados, seja por meio de espa\u00e7amento, localiza\u00e7\u00e3o ou elementos gr\u00e1ficos."));
		mapRegra.put("2.16", new Regra(2,
				"Assegurar o correto posicionamento de todos os controles de formul\u00e1rios que tenham r\u00f3tulos implicitamente associados, at\u00e9 que os leitores de tela ou navegadores do suportem associa\u00e7\u00f5es expl\u00edcitas entre r\u00f3tulos e controles de formul\u00e1rios."));
		mapRegra.put("2.17", new Regra(2,
				"forne\u00e7a informa\u00e7\u00f5es sobre como o s\u00edtio est\u00e1 estruturado, atrav\u00e9s de um mapa ou de sum\u00e1rio. Crie o mapa de forma textual, associando aos t\u00edtulos das p\00e1ginas para N\u00e3o causar confus\u00e3o aos usu\u00e1rios que utilizarem leitores de tela."));
		mapRegra.put("2.18", new Regra(2,
				"Assegure a acessibilidade de objetos programados, tais como programas interpret\u00e1veis e applets, garantindo que a resposta a eventos seja independente do dispositivo de entrada e que qualquer elemento dotado de interface pr\u00f3pria possa funcionar com qualquer leitor de tela ou navegador que o usu\u00e1rio utilize. Evite colocar scripts que estejam vinculados a links. Se isso N\u00e3o for poss\u00edvel, forne\u00e7a informa\u00e7\u00f5es equivalentes em uma p\u00e1gina alternativa acess\u00edvel."));
		mapRegra.put("2.19", new Regra(2,
				"Em programas interpret\u00e1veis, especificar respostas a eventos, preferindo as rotinas dependentes de dispositivos (mouse, teclado, etc)."));
		mapRegra.put("3.1", new Regra(3, "N\u00e3o usar elementos considerados ultrapassados pelo W3C."));
		mapRegra.put("3.2", new Regra(3,
				"Especificar por extenso cada abreviatura ou sigla, quando da sua primeira ocorr\u00e1ncia em um documento, utilizando os atributos \"abbr\" e \"acronym\". Utilize o atributo \"abbr\" dentro de um elemento \"th\" quando vocvoc\u00ea tiver cabe\u00e7alhos muito longos, para que os leitores de tela lerem apenas o seu conte\u00fado e N\u00e3o o texto do cabe\u00e7alho na \u00edntegra."));
		mapRegra.put("3.3", new Regra(3,
				"Fornecer atalhos por teclado que apontem para links importantes (incluindo os contidos em mapas de imagem armazenados no cliente), para in\u00edcio da \u00e1rea principal de conte\u00fado da p\u00e1gina, controles de formul\u00e1rios, e grupo de controles de formul\u00e1rios."));
		mapRegra.put("3.4", new Regra(3,
				"Inserir, entre links adjacentes, caracteres que N\u00e3o funcionem como link e sejam pass\u00edveis de impress\u00e3o (como um espa\u00e7o), at\u00e9 que os leitores de tela ou navegadores (incluindo as tecnologias de apoio) reproduzam clara e distintamente os links adjacentes."));
		mapRegra.put("3.5", new Regra(3,
				"Sempre que poss\u00edvel, fornecer informa\u00e7\u00f5es que possibilitem aos usu\u00e1rios receber os documentos de acordo com as suas prefer\u00eancias (por ex., por idioma ou por tipo de conte\u00fado)."));
		mapRegra.put("3.6", new Regra(3,
				"Fornecer barras de navega\u00e7\u00e3o para auxiliar os menus de navega\u00e7\u00e3o. Utilizar elemento que contextualizem a localiza\u00e7\u00e3o do usu\u00e1rio, como barras de caminho e \"Sua Localiza\u00e7\u00e3o\" nas p\00e1ginas do documento."));
		mapRegra.put("3.7", new Regra(3,
				"Agrupar links relacionados entre si, identificando o grupo (em benef\u00edcio do navegador ou leitor de tela do usu\u00e1rio) e, at\u00e9 que o navegador ou leitor de tela do usu\u00e1rio se encarregue de tal fun\u00e7\u00e3o, fornecer um modo de contornar determinado grupo."));
		mapRegra.put("3.8", new Regra(3,
				"Se forem oferecidas fun\u00e7\u00f5es de pesquisa, ativar diferentes tipos de pesquisa de modo a corresponderem a diferentes n\u00edveis de compet\u00eancia e \u00e0s prefer\u00eancias dos usu\u00e1rios. Sendo poss\u00edvel, quando a pesquisa N\u00e3o encontrar a palavra, sugerir palavras semelhantes."));
		mapRegra.put("3.9", new Regra(3,
				"Use palavras relevantes no in\u00edcio de cabe\u00e7alhos, par\u00e1grafos, e listas para identificar o assunto tratado."));
		mapRegra.put("3.10", new Regra(3,
				"Fornecer informa\u00e7\u00f5es sobre documentos compostos por v\00e1rias p\00e1ginas (isto  \u00e9 , cole\u00e7\u00f5es de documentos). Caso seja necess\u00e1rio, utilize ferramentas de compacta\u00e7\u00e3o de arquivo, tais como ZIP, TAR, GZIP ou ARJ. Informe o tamanho do arquivo e o tempo estimado para baixar por meio de um modem comum. forne\u00e7a documentos em formatos alternativos, pass\u00edveis de leitura pelos leitores de tela."));
		mapRegra.put("3.11", new Regra(3,
				"Complementar o texto com apresenta\u00e7\u00f5es gr\u00e1ficas ou sonoras, sempre que puderem facilitar a compreens\u00e3o da p\u00e1gina."));
		mapRegra.put("3.12", new Regra(3,
				"Identificar claramente o destino de cada link, bot\u00e3o ou elemento que submeta uma a\u00e7\u00e3o. Prefira utilizar textos mais claros e objetivos, mostrando o verdadeiro sentido e o destino do link. Evite usar frases como \"Clique aqui\"."));
		mapRegra.put("3.13", new Regra(3,
				"Informar previamente ao usu\u00e1rio o destino e resultado da a\u00e7\u00e3o, quando houver campos e elementos do formul\u00e1rio, como, por exemplo, caixas de sele\u00e7\u00e3o, que submetem automaticamente o conte\u00fado ao se efetuar uma determinada sele\u00e7\u00e3o. Nestes casos, ao inv \u00e9 s da sele\u00e7\u00e3o submeter automaticamente o formul\u00e1rio,  \u00e9  recomend\u00e1vel que se vincule ao elemento um bot\u00e3o para efetuar a a\u00e7\u00e3o."));
		mapRegra.put("3.14", new Regra(3,
				"forne\u00e7a metadados para acrescentar informa\u00e7\u00f5es sem\u00e2nticas e descritivas do s\u00edtio, que sejam \u00fateis para os mecanismos de busca."));

		// Regras especificas da ACBR
		mapRegra.put("4.1", new Regra(3,
				"N\u00e3o fa\u00e7a atalhos \"accesskey\" que entrem em conflito com o Internet Explorer."));
	}

	@Override
	public int getCodWcagEmag() {
		return 2;
	}
}
