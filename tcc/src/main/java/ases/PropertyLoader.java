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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * Carrega os arquivos de propriedades
 */
public class PropertyLoader {

	private static String path = "/mnt/774ce448-05a2-4fc5-a56c-3261efee833f/Acadêmica/TCC2 - Or. Danilo Monteiro/ases-acessibilidade-5ff365243539/config";

	private static File configPath;

	static {

		File f = new File(System.getProperty("user.home"), ".ases2");

		configPath = new File(f, "config");

		if (!configPath.exists()) {

			configPath.mkdirs();

		}

		try {

			File t = new File(path);

			if (!t.exists()) {
				t.mkdirs();
			}

			System.out.println(t.getAbsolutePath());

			FileUtils.copyDirectory(t, configPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String SILVINHA_LANGUAGE = "lang";

	public static final String SILVINHA_VERSION = "serv_versao";

	public static final String SILVINHA_FRAME_WIDTH = "width";

	public static final String SILVINHA_FRAME_HEIGHT = "height";

	public static final String SILVINHA_FRAME_LOCATION_X = "loc_x";

	public static final String SILVINHA_FRAME_LOCATION_Y = "loc_y";

	public static final String SILVINHA_FRAME_STATE = "state";

	public PropertyLoader() {

	}

	public static void saveProperty(Object o, Properties p) throws FileNotFoundException, IOException {

		String comments = "# Arquivo de Configura&ccedil;&atilde;o do Avaliador Silvinha"
				+ "# Cria&ccedil;&atilde;o: 29/11/05 - 17:20" + "# Autor: Danniel Nascimento";

		String fileName = o.getClass().getSimpleName().toLowerCase();

		File arquivoPropriedades = new File(configPath, fileName + ".properties");

		OutputStream out = new FileOutputStream(arquivoPropriedades);

		p.store(out, comments);

	}

	/**
	 * Salva as propriedades de uma classe
	 * 
	 * @param nome
	 *            nome da classe
	 * @param p
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void saveProperty(String nome, Properties p) throws FileNotFoundException, IOException {
		String comments = "# Arquivo de Configura&ccedil;&atilde;o do Avaliador Silvinha"
				+ "# Cria&ccedil;&atilde;o: 29/11/05 - 17:20" + "# Autor: Danniel Nascimento";
		String fileName = nome.toLowerCase();
		File arquivoPropriedades = new File(configPath, fileName + ".properties");
		OutputStream out = new FileOutputStream(arquivoPropriedades);
		p.store(out, comments);
	}

	/**
	 * Retorna um arquivo de .properties da pasta config de acordo com o nome da
	 * classe
	 * 
	 * @param o
	 * @return Propriedade
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties loadProperty(Object o) throws FileNotFoundException, IOException {
		return getPropertyFile(o.getClass().getSimpleName().toLowerCase());
	}

	/**
	 * Retorna um arquivo de .properties da pasta config de acordo com o nome da
	 * classe
	 * 
	 * @param nome
	 *            nome da classe
	 * @return Propriedades
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties loadProperty(String nome) throws FileNotFoundException, IOException {
		return getPropertyFile(nome.toLowerCase());
	}

	private Properties getPropertyFile(String fileName) throws FileNotFoundException, IOException {

		Properties prop = new Properties();

		File arquivoPropriedades = new File(configPath, fileName + ".properties");

		FileInputStream in = new FileInputStream(arquivoPropriedades);

		prop.load(in);

		in.close();

		return prop;

	}

	public Properties loadLanguage(String language) throws FileNotFoundException, IOException {
		return getLanguageFile(language);
	}

	private Properties getLanguageFile(String fileName) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		// File arquivoPropriedades = new File(loadResource(path + "/" +
		// fileName + ".properties"));
		File arquivoPropriedades = new File(path + "/lang/" + fileName);
		FileInputStream in = new FileInputStream(arquivoPropriedades);
		prop.load(in);
		in.close();
		return prop;
	}

	// private String loadResource(String url) {
	// System.out.println(url);
	// return this.getClass().getClassLoader().getResource(url).getPath() + "/";
	// }
}
