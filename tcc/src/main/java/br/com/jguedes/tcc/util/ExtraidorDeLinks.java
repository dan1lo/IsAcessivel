package br.com.jguedes.tcc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ases.RegrasHardCodedEmag;
import ases.Token;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;
import edu.uci.ics.crawler4j.url.WebURL;

public class ExtraidorDeLinks {

	public static void main(String[] args) {
		String Url = "https://www.fatecpe.com.br";
		System.out.println(Url.substring(0, Url.lastIndexOf("/") + 1));

	}

	/**
	 * Extrai links de uma p&aacute;gina
	 * 
	 * @param HTMLsource
	 * @param Url
	 * @param profundidade
	 * @param path
	 * @param listaLinks
	 * @return
	 */
	public static boolean extraiLinksFromPage(String HTMLsource, String Url, Profundidade profundidade, String path,
			List<WebURL> listaLinks) {

		if (HTMLsource.isEmpty()) {
			return false;
		}

		String link = "";

		String baseUrl = "";

		String dominio = "", servidor = "";

		String HTMLsourceLower = HTMLsource + " ";

		String arrUrl[];

		int i, fim, ini = fim = 0;

		HTMLsourceLower = HTMLsourceLower.toLowerCase();

		baseUrl = Url.substring(0, Url.lastIndexOf("/") + 1);

		/*
		 * procurar por <base href=
		 * "http://www.bcb.gov.br/pom/spb/estatistica/port/estatistica.asp">
		 */
		ini = HTMLsourceLower.indexOf("<base ", ini);

		int ini_href = -1;

		if (ini > -1) {

			fim = HTMLsourceLower.indexOf(">", ini);

			link = HTMLsource.substring(ini, fim);

			ini_href = link.toLowerCase().indexOf("href=\"");

			if (ini_href > -1) {

				ini_href += 6;

				int fim_href = link.indexOf("\"", ini_href);

				if (fim_href > ini_href) {

					link = link.substring(ini_href, fim_href);

					baseUrl = link.substring(0, link.lastIndexOf("/") + 1);

				}

			}

		}

		// buscar o nome do "servidor" www.lslslsl.gov.br
		if (Url.indexOf("?") != -1) {

			servidor = Url.substring(0, Url.indexOf("?"));

			arrUrl = servidor.split("/");

		} else {

			arrUrl = Url.split("/");

		}

		for (i = 0; i < arrUrl.length; i++) {

			if (i == 2) {

				servidor = arrUrl[i];

				break;

			}

		}

		// dominio = servidor.replaceAll("www", "");
		dominio = servidor;

		RegrasHardCodedEmag regra = new RegrasHardCodedEmag();

		// Pegar os links de <a href
		ini = HTMLsourceLower.indexOf("<a ", 0);

		while (ini > -1) {

			ini_href = -1;

			if (ini > -1) {

				fim = HTMLsourceLower.indexOf(">", ini);

				String alink = HTMLsource.substring(ini, fim);

				// *

				link = regra.getAtributo(alink + " >", "href");

				if (link.startsWith("\"") && link.endsWith("\"")) {

					link = link.replaceAll("\"", "");

					regra.getAtributo(alink, "href");

				}

				addNewLink(listaLinks, link, servidor, dominio, baseUrl, Url, path, profundidade);

			}

			ini = HTMLsourceLower.indexOf("<a ", fim);

		}
		// Fim pegar os links de <a href

		// ini pegar os links de <area
		ini = HTMLsourceLower.indexOf("<area ", 0);

		while (ini > -1) {
			ini_href = -1;
			if (ini > -1) {
				fim = HTMLsource.indexOf(">", ini);
				link = HTMLsource.substring(ini, fim);
				ini_href = link.toLowerCase().indexOf("href=\"");
				if (ini_href > -1) {
					ini_href += 6;
					int fim_href = link.indexOf("\"", ini_href);
					if (fim_href > ini_href) {
						link = link.substring(ini_href, fim_href);
						addNewLink(listaLinks, link, servidor, dominio, baseUrl, Url, path, profundidade);
					}
				}
			}
			ini = HTMLsourceLower.indexOf("<area ", fim);
		}
		// Fim pegar os links de <area
		// ini pegar os links de <iframe
		ini = HTMLsourceLower.indexOf("<iframe ", 0);
		while (ini > -1) {
			ini_href = -1;
			if (ini > -1) {
				fim = HTMLsource.indexOf(">", ini);
				link = HTMLsource.substring(ini, fim);
				ini_href = link.toLowerCase().indexOf("src=\"");
				if (ini_href > -1) {
					ini_href += 5;
					int fim_href = link.indexOf("\"", ini_href);
					if (fim_href > ini_href) {
						link = link.substring(ini_href, fim_href);
						addNewLink(listaLinks, link, servidor, dominio, baseUrl, Url, path, profundidade);
					}
				}
			}
			ini = HTMLsourceLower.indexOf("<iframe ", fim);
		}
		// Fim pegar os links de <iframe
		// ini pegar os links de <frame
		ini = HTMLsourceLower.indexOf("<frame ", 0);
		while (ini > -1) {
			ini_href = -1;
			if (ini > -1) {
				fim = HTMLsource.indexOf(">", ini);
				link = HTMLsource.substring(ini, fim);
				ini_href = link.toLowerCase().indexOf("src=\"");
				if (ini_href > -1) {
					ini_href += 5;
					int fim_href = link.indexOf("\"", ini_href);
					if (fim_href > ini_href) {
						link = link.substring(ini_href, fim_href);
						addNewLink(listaLinks, link, servidor, dominio, baseUrl, Url, path, profundidade);
					}
				}
			}
			ini = HTMLsourceLower.indexOf("<frame ", fim);
		}

		return true;

	}

	private static void addNewLink(List<WebURL> listaLinks, String link, String servidor, String dominio,
			String baseUrl, String url, String path, Profundidade profundidade) {

		link = link.replaceAll("&amp;", "&");

		while (link.endsWith("%20")) {

			link = link.substring(0, link.length() - 3);

		}

		link = link.trim();

		// verifica se eh de <a name
		if (link.indexOf("#") != -1) {
			String[] splits = link.split("#");
			try {
				link = splits[0].trim();
				if (link.equals("")) {
					return;
				}
			} catch (Exception e) {
				return;
			}
		}

		// nao considerar links para arquivos ou extensoes nao aceitas
		for (int i = 0; i < Token.NAOEXTS.length; i++) {
			if (link.toLowerCase().endsWith(Token.NAOEXTS[i])) {
				return;
			}
		}

		if (link.indexOf("http://") == 0 || link.indexOf("https://") == 0) {

			// verificar se vai para outro endereco, codigo mais abaixo

		} else if (link.toLowerCase().indexOf("ftp:") == 0 || link.toLowerCase().indexOf("javascript:") == 0
				|| link.toLowerCase().indexOf("mailto:") == 0 || link.toLowerCase().indexOf("file:") == 0
				|| link.toLowerCase().indexOf("telnet:") == 0) {

			// desconsiderar estes links
			return;

		} else if (link.indexOf("./") == 0) {

			// vai para o mesmo diretorio
			link = baseUrl + link.substring(2);

		} else if (link.indexOf("?") == 0) {

			// comeca com interrogacao simples
			link = baseUrl + link;

		} else if (link.indexOf("/") == 0) {

			// comeca com barra simples
			if (baseUrl.indexOf("https://") != -1) {

				link = "https://" + servidor + link;

			} else {

				link = "http://" + servidor + link;

			}

		} else if (link.indexOf("../") == 0) {

			// comeca com dois pontos barra
			String str2 = baseUrl;

			int pos = str2.lastIndexOf('/');

			str2 = str2.substring(0, pos + 1);

			while (link.startsWith("../")) {

				// sobe um diretorio.
				pos = str2.lastIndexOf('/', pos - 1);

				// Ja est na raiz.
				if (pos != -1) {

					str2 = str2.substring(0, pos + 1);

				}

				link = link.substring(3);

			}

			link = str2 + link;

		} else {

			link = baseUrl + link;

		}

		// verificar se vai para outro endereco
		String tStr = link.replace("http://", "");

		if (tStr.indexOf('/') != -1) {

			tStr = tStr.substring(0, tStr.indexOf('/'));

		}

		if (!link.contains(dominio))
			return;

		if (profundidade != null && profundidade_NAO_AtendeCriterio(profundidade, link)) {
			return;
		}

		WebURL wurl = new WebURL();

		wurl.setURL(link);

		if (!listaLinks.contains(wurl)) {
			listaLinks.add(wurl);
			gravaLink(link, path);
		}

	}

	/**
	 * @param profundidade
	 * @param nivel
	 * @return
	 */
	private static boolean profundidade_NAO_AtendeCriterio(Profundidade profundidade, String link) {

		int nivel = calculaProfundidade(link);

		return profundidade.getValue() <= Profundidade.NIVEL3.getValue() && nivel > profundidade.getValue();
	}

	private static int calculaProfundidade(String url) {

		String nomeSite = url.split("//")[1];

		if (nomeSite.endsWith("/")) {

			return nomeSite.split("/").length;

		} else {

			return nomeSite.split("/").length - 1;

		}

	}

	/**
	 * Grava os links no HD
	 */
	public static void gravaLink(String link, String path) {

		try {

			FileWriter arq = new FileWriter(path + "links.log", true);

			arq.write(link + "\n");

			arq.close();

		} catch (IOException e) {
		}

	}

	/**
	 * Extrai os links sem levar em considera&ccedil;&atilde;o o crit&eacute;rio
	 * de profundidade
	 * 
	 * @param html
	 * @param url
	 * @param folder
	 * @param listaLinks
	 * @return
	 */
	public static boolean extraiLinksFromPage(String html, String url, String folder, List<WebURL> listaLinks) {

		return extraiLinksFromPage(html, url, null, folder, listaLinks);

	}

	/**
	 * Restaura listas de links
	 */
	public static List<WebURL> getLinksExtraidos(String folder) {

		List<WebURL> listaLinks = null;

		StringBuilder sb = new StringBuilder();

		final int mb = 1024;

		File file = new File(folder + "links.log");

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

				return null;

			}

		} catch (Exception e) {

			return null;

		} finally {

			if (fis != null) {

				try {

					fis.close();

				} catch (Exception e) {

				}

			}

		}

		String arr[] = sb.toString().trim().split("\n");

		for (int i = 0; i < arr.length; i++) {

			if (listaLinks == null) {

				listaLinks = new LinkedList<WebURL>();

			}

			WebURL w = new WebURL();

			w.setURL(arr[i]);

			listaLinks.add(w);

		}

		return listaLinks;

	}

}
