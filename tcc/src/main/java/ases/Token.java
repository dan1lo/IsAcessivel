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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Configura&ccedil;&atilde;o do software
 * 
 * @author Mariano Aloi
 *
 *         Construido em 15/08/2005
 *
 */
public class Token {

	/**
	 * Remover no final do projeto
	 */
	public static final int EMAG_AV_GEN_P1 = 8;
	/**
	 * Remover no final do projeto
	 */
	public static final int EMAG_AV_GEN_P2 = 3;
	/**
	 * Remover no final do projeto
	 */
	public static final int EMAG_AV_GEN_P3 = 7;
	/**
	 * Remover no final do projeto
	 */
	public static final int WCAG_AV_GEN_P1 = 4;
	/**
	 * Remover no final do projeto
	 */
	public static final int WCAG_AV_GEN_P2 = 5;
	/**
	 * Remover no final do projeto
	 */
	public static final int WCAG_AV_GEN_P3 = 8;

	public static String autServer = "";
	public static String SMTP = "";
	/**
	 * Guarda o email do remetente
	 */
	public static final String REMETENTE = "silvinha@acessobrasil.org.br";

	/**
	 * Guarda o email do cliente
	 */
	public static String CLIENTE_EMAIL = "";

	public static String URL_STRING = "";
	public static URL URL;

	public static final String[] PROTS = { "mailto", "news", "javascript" };

	public static final String[] CMS = { "calandra.nsf" };
	public static final String[] EXTS = { "htm", "html", "asp", "php", "jsp" };
	public static final String[] NAOEXTS = { "txt", "css", "gif", "jpg", "png", "ico", "pdf", "doc", "xls", "rtf", "ps",
			"zip", "gz", "bz2", "rar", "ppt", "avi", "mpg", "mp3", "exe", "pps", "js", "xsl", "xsd", "odp", "odt",
			"jar" };

	public static String DATA;
	public static boolean ADMIN = true;

	public static File localArquivos;
	// public static String diretorioInicial;
	public static String ADMINISTRADOR;

	/**
	 * Construtor para a classe Token.java
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public Token() throws IOException {

	}

	public static void setUrl(String url) {

		if (url.startsWith("www")) { // note que so coloca http se tiver www no
										// inicio

			url = "http://" + url;

		}

		URL_STRING = url;

	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
