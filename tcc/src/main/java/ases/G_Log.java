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

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe para criar logs
 */
public class G_Log {
	private String myFileName;

	public G_Log(String nomeArq) {
		this.myFileName = nomeArq;
	}

	public synchronized static void loggerInNewLine(String nomeArq, String conteudo) {

		try {
			Date startDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			FileWriter arq = new FileWriter(nomeArq, true);

			arq.write(dateFormat.format(startDate) + "\t" + conteudo + "\n");

			// System.out.println("Arquivo de G_Log: " + arq);
			arq.close();
		} catch (IOException e) {
			/*
			 * Erro de IO
			 */
		}
	}

	/**
	 * Adiciona um texto ao log
	 */
	public void addLog(String conteudo) {

		try {
			Date startDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			FileWriter arq = new FileWriter(this.myFileName, true);
			arq.write(dateFormat.format(startDate) + "\t" + conteudo);
			// System.out.println("Arquivo de G_Log: " + arq);
			arq.close();
		} catch (IOException e) {
			/*
			 * Erro de IO
			 */
		}

	}

	/**
	 * Wrapper de fun��o
	 */
	public void debug(String conteudo) {
		this.addLog(conteudo + "\r\n");
	}
}
