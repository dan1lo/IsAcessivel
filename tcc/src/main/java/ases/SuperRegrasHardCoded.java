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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Possui as regras em comum tanto para WCAG E EGOV
 * 
 * @author Fabio Issamu Oshiro
 * 
 */
public abstract class SuperRegrasHardCoded {

	G_Log log = new G_Log("SuperRegrasHardCoded.log");

	/**
	 * Retorna 1 para Wcag 2 para Emag
	 * 
	 */
	public abstract int getCodWcagEmag();

	/**
	 * Verifica se existe height ou width com tamanho fixo
	 * 
	 * @return true caso haja tamanho fixo
	 */
	public boolean verificaTamFixoAtributoWidhtHeight(String tagInteira) {
		String w = this.getAtributo(tagInteira, "width");
		String h = this.getAtributo(tagInteira, "height");
		// System.out.print("h='"+h+"' w='"+w+"'\n");
		if (w.equals("") && h.equals("")) {
			// n�o possui width nem height ent�o ok
			return false;
		}
		if (w.indexOf("%") == -1 && !w.equals("")) {
			// valor sem %
			return true;
		}
		if (h.indexOf("%") == -1 && !h.equals("")) {
			// valor sem %
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a extens�o � .gif O ideal no futuro seria baixar a imagem e
	 * pegar o content type
	 * 
	 * @param tagInteira
	 * @return true caso no final seja gif
	 */
	public boolean verificaIsGif(String tagInteira) {
		if (this.getAtributo(tagInteira.toLowerCase(), "src").endsWith(".gif")) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se h� um longdesc
	 * 
	 * @param tagInteira
	 * @return true caso tenha um long desc
	 */
	public boolean verificaLongDesc(String tagInteira) {
		String longdesc = this.getAtributo(tagInteira.toLowerCase(), "longdesc");
		if (longdesc.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se possui um atributo style
	 * 
	 * @param tagInteira
	 * @return true caso tenha algun style
	 */
	public boolean verificaPossuiStyle(String tagInteira) {
		String style = this.getAtributo(tagInteira, "style");
		if (style.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se h� algum parametro de cor
	 * 
	 * @param tagInteira
	 * @return true caso haja parametro de cor
	 */
	public boolean verificaCorStyleInline(String tagInteira) {
		String style = this.getAtributo(tagInteira, "style");
		// System.out.print("style='"+style+"'\n");
		if (style.indexOf("color") != -1) {
			return true;
		} else if (style.indexOf("background") != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a tag link � de css
	 * 
	 * @param tagInteira
	 * @return true caso seja link de CSS
	 */
	public boolean verificaIsCSS(String tagInteira) {
		String rel = this.getAtributo(tagInteira, "rel");
		if (rel.equals("stylesheet")) {
			return true;
		}
		String tipo = this.getAtributo(tagInteira, "type");
		if (tipo.equals("text/css")) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se est� na lista de Deprecated ou Non Standard
	 * 
	 * @param tagName
	 * @return
	 */
	public boolean verificaDeprecatedNonStandard(String tagName) {
		/*
		 * Colocado desta forma para poder descrever a tag
		 */
		if (tagName.equals("blink")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("basefont")) {
			/*
			 * ?
			 */
			return false;
		} else if (tagName.equals("font")) {
			// Deprecated
			return false;
		} else if (tagName.equals("u")) {
			// Deprecated
			return false;
		} else if (tagName.equals("applet")) {
			// Deprecated
			return false;
		} else if (tagName.equals("menu")) {
			// Deprecated
			return false;
		} else if (tagName.equals("center")) {
			// Deprecated
			return false;
		} else if (tagName.equals("s")) {
			// Deprecated
			return false;
		} else if (tagName.equals("strike")) {
			// Deprecated
			return false;
		} else if (tagName.equals("embed")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("noembed")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("spacer")) {
			/*
			 * The <spacer> tag places an empty space, of the specified size,
			 * into document. Non standard
			 */
			return false;
		} else if (tagName.equals("ilayer")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("nolayer")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("keygen")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("spacer")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("comment")) {
			/*
			 * Non standard Microsoft
			 */
			return false;
		} else if (tagName.equals("bgsound")) {
			/*
			 * Non standard Microsoft
			 */
			return false;
		} else if (tagName.equals("marquee")) {
			/*
			 * Non standard Microsoft
			 */
			return false;
		} else if (tagName.equals("wbr")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("nobr")) {
			/*
			 * Non standard Netscape
			 */
			return false;
		} else if (tagName.equals("blink")) {
			/*
			 * Non standard
			 */
			return false;
		} else if (tagName.equals("servlet")) {
			/*
			 * Non standard SUN
			 */
			return false;
		} else if (tagName.equals("nolayer")) {
			/*
			 * N�o standard
			 */
			return false;
		} else if (tagName.equals("align")) {
			/*
			 * N�o standard
			 */
			return false;
		} else if (tagName.equals("ilayer")) {
			/*
			 * N�o standard
			 */
			return false;
		} else if (tagName.equals("nosave")) {
			/*
			 * Non standard
			 */
			return false;
		} else if (tagName.equals("multicol")) {
			/*
			 * Non standard
			 */
			return false;
		} else if (tagName.equals("listing")) {
			/*
			 * Is an ancient tag which was intended to indicate a long
			 * unformatted list. Deprecated
			 */
			return false;
		} else if (tagName.equals("dir")) {
			/*
			 * Deprecated Tipo de lista
			 */
			return false;
		} else if (tagName.equals("isindex")) {
			/*
			 * Deprecated em favor do elemento input
			 */
			return false;
		}
		return true;
	}

	/**
	 * Verifica se existe uma tag noscript no c�digo html
	 * 
	 * @param codHTML
	 * @return
	 */
	public boolean verificaNoscript(String codHTML) {
		Pattern pattern = Pattern.compile("<noscript(\\s.*?>|>)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(codHTML.toLowerCase());
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se a tag possui o stributo lang
	 * 
	 * @param tag
	 * @return
	 */
	public boolean verificaLang(String tag) {
		String lang = this.getAtributo(tag, "lang");
		if (lang.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Verifica se existe texto dentro das tags object
	 * 
	 * @param codHTML
	 * @param tag
	 * @param tagIndex
	 *            in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto predefinido
	 */
	public boolean verificaTextoEmObject(String codHTML, String tag, int tagIndex) {
		// System.out.print("\nverificaTextoEmObject()\n");
		// System.out.print("tag object='" + tag + "'\n");
		String regex = "</?object(\\s+.*?>|>)";
		// System.out.print("regex='" + regex + "'\n");
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		int ini = tagIndex + tag.length();
		Matcher matcher = pattern.matcher(codHTML.substring(ini).toLowerCase());
		int objects = 1;
		// System.out.print("verificaTextoEmObject()\n");
		while (matcher.find()) {
			String abreOuFecha = matcher.group();
			if (abreOuFecha.startsWith("<object")) {
				objects++;
			} else {
				objects--;
			}
			// verifica se fechou todas as tags que abriram <object
			if (objects == 0) {
				int fim = ini + matcher.start();
				if (fim >= ini) {
					String conteudo = codHTML.substring(ini, fim);
					Pattern p = Pattern.compile("(<.*?>|\\s)", Pattern.DOTALL);
					Matcher m = p.matcher(conteudo); // get a matcher object
					while (m.find()) {
						String subConteudo = m.group();
						if (subConteudo.toLowerCase().startsWith("<img")) {
							String alt = this.getAtributo(subConteudo, "alt");
							if (!alt.equals("")) {
								return true;
							}
						}
					}
					conteudo = m.replaceAll("");
					// System.out.print("conteudo de texto do objeto='" +
					// conteudo + "'\n");
					if (conteudo.equals("")) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se existe texto na tag iframe
	 * 
	 * @param codHTML
	 * @param tag
	 * @param tagIndex
	 *            in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto dentro de iframe
	 */
	public boolean verificaTextoIframe(String codHTML, String tag, int tagIndex) {
		// System.out.print("\nverificaTextoIframe()\n");
		// System.out.print("tag iframe='" + tag + "'\n");
		String regex = "</iframe(\\s+.*?>|>)";
		// System.out.print("regex='" + regex + "'\n");
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		int ini = tagIndex + tag.length();
		Matcher matcher = pattern.matcher(codHTML.substring(ini).toLowerCase());
		if (matcher.find()) {
			int fim = ini + matcher.start();
			if (fim >= ini) {
				String conteudo = codHTML.substring(ini, fim);
				// System.out.print("conteudo do iframe='" + conteudo + "'\n");
				if (conteudo.equals("")) {
					return false;
				} else {
					return true;
				}
			} else {
				// n�o h� fecha </iframe
				return false;
			}
		}
		return false;
	}

	/**
	 * Verifica se existe texto predefinido para a tag area
	 * 
	 * @param codHTML
	 * @param tag
	 * @param tagIndex
	 *            in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto predefinido
	 */
	public boolean verificaTextoPredefinidoTextarea(String codHTML, String tag, int tagIndex) {
		// System.out.print("\nverificaTextoPredefinidoTextarea()\n");
		// System.out.print("tag textarea='" + tag + "'\n");
		String regex = "</textarea(\\s+.*?>|>)";
		// System.out.print("regex='" + regex + "'\n");
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		int ini = tagIndex + tag.length();
		Matcher matcher = pattern.matcher(codHTML.substring(ini).toLowerCase());
		if (matcher.find()) {
			int fim = ini + matcher.start();
			if (fim >= ini) {
				String conteudo = codHTML.substring(ini, fim);
				// System.out.print("conteudo do textarea='" + conteudo +
				// "'\n");
				if (conteudo.equals("")) {
					return false;
				} else {
					return true;
				}
			} else {
				// n�o h� fecha </textarea
				return false;
			}
		}
		return false;
	}

	/**
	 * duplicado provisoriamente para geral
	 * 
	 * @param codHTML
	 * @param tag
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public boolean verificaTextoPredefinidoTextarea(String codHTML, String tag) {
		// Pattern pattern =
		// Pattern.compile(tag+"(.*?)</textarea(\\s+.*?>|>)",Pattern.DOTALL);
		Pattern pattern = Pattern.compile(tag + "(.*?)</textarea>(\\s+.*?>|>)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(codHTML.toLowerCase());
		if (matcher.find()) {
			// System.out.print("conteudo do
			// textarea='"+matcher.group(1)+"'\n");
			if (matcher.group(1).equals("")) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se a tag html tem um label associado Input text Input (text
	 * default) Input password Input checkbox Input radio Input file textarea
	 * (sem type) select (sem type)
	 * 
	 * @param codHTML
	 * @param idField
	 * @return true caso esteja com label associado ou n�o precise
	 */
	public boolean verificaLabelTag(String codHTML, String tag) {
		String tipo = getAtributo(tag, "type");
		// System.out.print("tag=" + tag + "\n");
		boolean retorno = true;
		// Se for textarea ou select o type � igual a ""
		if (tipo.equals("text") || tipo.equals("password") || tipo.equals("") || tipo.equals("checkbox")
				|| tipo.equals("radio") || tipo.equals("file")) {
			String id_campo = this.getAtributo(tag, "id");
			if (!id_campo.equals("")) {
				// System.out.print("id_campo=" + id_campo + "\n");
				retorno = verificaLabel(codHTML, id_campo);
			} else {
				// n�o possui id
				retorno = false;
			}
		}
		// System.out.print("retorno=" + retorno + "\n");
		return retorno;
	}

	/**
	 * Verifica se o idField tem um label associado em todo html
	 * 
	 * @param codHTML
	 * @param idField
	 * @return
	 */
	public boolean verificaLabel(String codHTML, String idField) {
		// return regexFound("<label(\\s.*?|\\s+)for=\"" + idField.toLowerCase()
		// + "\".*?>", codHTML.toLowerCase());
		return regexFound("<label(\\s.*?|\\s+)for=([\"'])" + idField.toLowerCase() + "\\2.*?>", codHTML.toLowerCase());
	}

	/**
	 * Verifica se campos de texto possuem caracteres predefinidos aplicado em
	 * text e password
	 * 
	 * @param codHTML
	 * @return true caso a tag esteja Ok
	 */
	public boolean verificaTextoPredefinidoInput(String tagInput) {
		// Regex
		String tipo = this.getAtributo(tagInput, "type").toLowerCase();
		if (!tipo.equals("text") && !tipo.equals("password") && !tipo.equals("")) {
			// N�o � para verificar, a tag est� ok
			return true;
		}
		String value = this.getAtributo(tagInput, "value").toLowerCase();
		if (value.equals("")) {
			// value em branco, n�o passou no teste
			return false;
		}
		return true;
	}

	/**
	 * Verifica se campos de texto possuem caracteres predefinidos
	 * 
	 * @param codHTML
	 * @return a linha do erro ou 0 (zero) caso n�o tenha erros
	 */
	public int verificaTextoPredefinidoBKP(String codHTML) {
		// Regex
		// Pattern pattern =
		// Pattern.compile("(<input\\s|(<textarea\\s|<textarea>))");

		// Pattern pattern =
		// Pattern.compile("(<input\\s+|(<textarea(\\s+|>)))");

		// Patter para procurar o tipo
		Pattern ptr_type = Pattern.compile("type\\s*=\\s*[\"'](.*?)[\"']");

		// Patter para procurar o valor
		Pattern ptr_value = Pattern.compile("value\\s*=\\s*[\"'](.*?)[\"']");

		Pattern pattern = Pattern.compile("<input\\s+(.*?>)");
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(codHTML.toLowerCase());
		while (matcher.find()) {
			// Procura o tipo do input
			boolean verificar = true;
			Matcher mth_type = ptr_type.matcher(matcher.group());
			if (mth_type.find()) {
				// System.out.print("type="+mth_type.group(1)+"\n");
				// Achou um tipo
				if (!mth_type.group(1).equals("text") && !mth_type.group(1).equals("password")) {
					verificar = false;
				}
			}

			if (verificar) {
				// � para verificar se possui conteudo ou nao
				Matcher mth_value = ptr_value.matcher(matcher.group());
				if (mth_value.find()) {
					// existe algum valor
					if (mth_value.group(1).equals("")) {
						// erro
						// System.out.print("Erro: "+matcher.group()+"\n");
						return 0;
					}
				} else {
					// erro
					// System.out.print("Erro: "+matcher.group()+"\n");
					return 0;
				}
			}
			// System.out.print("Ok: "+matcher.group()+"\n");
			/*
			 * System.out.print("I found the text \""+matcher.group()+"\"
			 * starting at " + "index "+matcher.start()+" and ending at index
			 * "+matcher.end()+"\n");
			 * //System.out.print("tipo='"+matcher.group(1)+"'\n");
			 * //System.out.print("valor='"+matcher.group(3)+"'\n"); //
			 */
		}
		return 0;
	}

	/**
	 * Verifica a hierarquia de h1 at� h6
	 * 
	 * @param codHTML
	 * @return a linha do erro ou 0 (zero) caso n�o tenha erros
	 */
	public int verificaH1aH6(String codHTML) {
		int canBe = 1, indice = -1, linha = 0;
		// Regex
		Pattern pattern = Pattern.compile("<h(\\d+)(\\s+.*?>|>)");
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(codHTML.toLowerCase());
		while (matcher.find()) {
			/*
			 * System.out.print("I found the text \""+matcher.group()+"\"
			 * starting at " + "index "+matcher.start()+" and ending at index
			 * "+matcher.end()+"\n"); System.out.print(matcher.group(1)+"\n");
			 * //
			 */
			int n = Integer.parseInt(matcher.group(1));
			if (n > canBe || n < 1 || n > 6) {
				// Erro
				// System.out.print("Erro n="+n+"\n");
				indice = matcher.start();
				break;
			} else {
				canBe = n + 1;
			}
		}
		if (indice != -1) {
			// contar as linhas at� o indice
			linha = 1;
			int aux = codHTML.indexOf("\n", 0);
			while (aux != -1 && aux < indice) {
				aux = codHTML.indexOf("\n", aux + 1);
				linha++;
			}
		}
		return linha;
	}

	/**
	 * Retorna true se encontra o regex The metacharacters supported by this API
	 * are: ([{\^-$|]})?*+.
	 * 
	 * @param reg
	 * @param source
	 */
	private boolean regexFound(String reg, String source) {
		// Regex
		Pattern pattern = Pattern.compile(reg);
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(source);
		return matcher.find();
	}

	/**
	 * The metacharacters supported by this API are: ([{\^-$|]})?*+.
	 * 
	 * @param reg
	 * @param source
	 * @deprecated
	 */
	@Deprecated
	public void regexTest(String reg, String source) {
		// Regex
		Pattern pattern = Pattern.compile(reg);
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(source);
		boolean found = false;
		while (matcher.find()) {
			// System.out.print("I found the text \""+matcher.group()+"\"
			// starting at " +
			// "index "+matcher.start()+" and ending at index
			// "+matcher.end()+"\n");
			found = true;
		}
		if (!found) {
			// System.out.print("No match found.\n");
		}
	}

	/**
	 * Verifica se a tag tem algum parametro indicando tamanho fixo Hoje n�o vai
	 * at� um arquivo CSS separado
	 * 
	 * @param tag
	 * @return true caso esteja ok
	 */
	public boolean verificaTamFixoEmAtributoStyle(String tag) {
		// Regex
		Pattern pattern = Pattern.compile("\\sstyle\\s*?=([\"'])(.*?)\\1");
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(tag);
		if (!matcher.find()) {
			// N�o possui estilo, ent�o est� ok
			return true;
		}
		String estilo = matcher.group(2);
		// System.out.print("Estilo='"+estilo+"'\n");

		/*
		 * Verificando tamanho de fonte
		 */
		Pattern fontPx = Pattern.compile("font(-size)?:\\s*?.*?(px|pt|em|%|ex|pc|mm|cm|in).*?;*", Pattern.DOTALL);
		matcher = fontPx.matcher(estilo);
		if (matcher.find()) {
			// possui px ou pt
			String unidade = matcher.group(2);
			if (!(unidade.equals("em") || unidade.equals("%") || unidade.equals("ex"))) {
				// System.out.print("Estilo com fonte fixa:
				// '"+matcher.group()+"'\n");
				return false;
			}
		}

		/*
		 * Verificando tamanho width ou height
		 */
		String pares[] = estilo.toLowerCase().split(";");
		for (int i = 0; i < pares.length; i++) {
			String chaveValor[] = pares[i].split(":");
			if (chaveValor[0].trim().equals("width") || chaveValor[0].trim().equals("height")) {
				if (chaveValor[1].indexOf("%") == -1 && chaveValor[1].indexOf("em") == -1) {
					return false;
				}
			}
		}
		/*
		 * Pattern widthPx = Pattern.compile(
		 * "(width|height):\\s*?.*?(px|pt|em|%|ex|pc|mm|cm|in).*?;*",
		 * Pattern.DOTALL); matcher = widthPx.matcher(estilo); if
		 * (matcher.find()) {
		 * 
		 * // possui px ou pt String unidade = matcher.group(2); if
		 * (!unidade.equals("%")) { // System.out.print("Estilo com largura
		 * fixa: // '"+matcher.group()+"'\n"); return false; } }
		 */
		return true;

	}

	/**
	 * Retorna o atributo especificado caso n�o tenha retorna ""
	 * 
	 * @param tagInteira
	 *            &lt;img src="foto.jpg" alt="teste"&gt;
	 * @param att
	 *            Ex alt, longdesc, etc
	 * @return
	 */
	public String getAtributo(String tagInteira, String att) {
		Pattern pattern = Pattern.compile("<.*?\\s" + att.toLowerCase() + "\\s*?=\\s*?([\"'])(.*?)\\1.*?>",
				Pattern.DOTALL);
		// Texto onde procurar o regex
		Matcher matcher = pattern.matcher(tagInteira.toLowerCase());
		if (matcher.find()) {
			return tagInteira.substring(matcher.start(2), matcher.end(2));
		}
		/*
		 * Verifica se existe
		 */
		if (tagInteira.toLowerCase().indexOf(att) == -1)
			return "";
		/*
		 * Testar quando n�o possui aspas ou aspas simples
		 */
		// System.out.print("Ini - getAtributo(\""+tagInteira+"\")\n");
		int estado = 0;
		if (tagInteira.endsWith("/>")) {
			tagInteira = tagInteira.substring(0, tagInteira.length() - 2);
		} else if (tagInteira.endsWith(">")) {
			tagInteira = tagInteira.substring(0, tagInteira.length() - 1);
		}
		String par[] = tagInteira.toLowerCase().split("\\s");
		for (int i = 0; i < par.length; i++) {
			if (estado == 0) {
				if (att.equals(par[i])) {
					// verificar se o pr�ximo � um sinal de igual
					estado = 1;
				} else {
					if (par[i].indexOf("=") != -1) {
						String attVal[] = par[i].split("=");
						try {
							if (attVal[0].equals(att)) {
								return attVal[1];
							}
						} catch (Exception e) {
							// System.out.println("att = " + att + "
							// tagInteira='"+tagInteira+"'");
							return "";
						}
					}
				}
			} else if (estado == 1) {
				if (!par[i].equals("=") && !par[i].equals("")) {
					return "";
				}
				estado = 2;
			} else if (estado == 2) {
				if (!par[i].equals("")) {
					return par[i];
				}
			}
		}
		// System.out.print("Fim - getAtributo\n");
		// n�o possui
		return "";
	}

	/**
	 * Verifica se existe separador entre links, caso ache alguma ocorrencia
	 * indica como erro
	 * 
	 * @param codHTML
	 * @return true caso tenha o separador
	 */
	public boolean verificaSeparadorEmLink(String codHTML) {
		Pattern pattern = Pattern.compile("</a\\s*><a\\s");
		Matcher matcher = pattern.matcher(codHTML.toLowerCase());
		if (matcher.find()) {
			// System.out.print("conteudo =-='" + matcher.group(1) + "'\n");
			return false;
		}
		return true;
	}

}
