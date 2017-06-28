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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * L� o relat�rio salvo
 *
 */
public class LeitorDeRelatorio {

	public static HashMap<String, Document> lerRelatorios(String fileName) {
		String errFileAtual = null;
		HashMap<String, Document> docsRelatorios = new HashMap<String, Document>();
		byte[] buf = new byte[1024];
		int len;
		try {
			ZipInputStream in = new ZipInputStream(new FileInputStream(fileName));
			ZipEntry entry;
			OutputStream out;
			while ((entry = in.getNextEntry()) != null) {
				String file = entry.getName();
				errFileAtual = entry.getName();
				File tmpFile = new File(file);
				out = new FileOutputStream(tmpFile);
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.close();
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = db.parse(tmpFile);
				docsRelatorios.put(file, doc);
				tmpFile.delete();
			}
			in.close();
			return docsRelatorios;
		} catch (IOException ioe) {
			ExceptionDialog.showExceptionDialog("[LeitorDeRelatorio]: " + ioe.getMessage() + "\nF:" + errFileAtual);
			ioe.printStackTrace();
			return null;
		} catch (ParserConfigurationException pce) {
			ExceptionDialog.showExceptionDialog("[LeitorDeRelatorio]: " + pce.getMessage() + "\nF:" + errFileAtual);
			pce.printStackTrace();
			return null;
		} catch (SAXException se) {
			ExceptionDialog.showExceptionDialog("[LeitorDeRelatorio]: " + se.getMessage() + "\nF:" + errFileAtual);
			se.printStackTrace();
			return null;
		}
	}

}
