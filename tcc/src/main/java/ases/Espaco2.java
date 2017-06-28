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

/**
 * Classe repons&aacute;vel por informar a quantidade de espa&ccedil;os e
 * tabula&ccedil;&otilde;es no in&iacute;cio de cada linha da p&aacute;gina
 * avaliada. Ela &eacute; importante para reconstru&ccedil;&atilde;o da
 * p&aacute;gina nos relat&oacute;rios, j&aacute; que os espa&ccedil;os e
 * tabula&ccedil;&otilde;es s&atilde;o perdidos no processo de
 * avalia&ccedil;&atilde;o.
 * 
 * @author Acessibilidade Brasil, em 22/08/2005. Refatorado em 04/01/2006.
 * @version 1.1
 */
public final class Espaco2 {
	/**
	 * Quantidade de tabula&ccedil;&otilde;es na linha avaliada.
	 */
	private int tabulacaoLinha;
	/**
	 * Quantidade de espa&ccedil;os na linha avaliada.
	 */
	private int espacoLinha;

	/**
	 * Construtor de Espa&ccedil;o.
	 * 
	 * @param qtdTab
	 *            Quantidade de tabula&ccedil;&otilde;es na linha avaliada.
	 * @param qtdEspaco
	 *            Quantidade de espa&ccedil;os na linha avaliada.
	 */
	public Espaco2(final int qtdTab, final int qtdEspaco) {

		this.tabulacaoLinha = qtdTab;
		this.espacoLinha = qtdEspaco;
	}

	/**
	 * @return Retorna o valor de espacoLinha.
	 */
	public int getEspacoLinha() {
		return espacoLinha;
	}

	/**
	 * @param qtdEspaco
	 *            Seta o valor de espacoLinha.
	 */
	public void setEspacoLinha(final int qtdEspaco) {
		this.espacoLinha = qtdEspaco;
	}

	/**
	 * @return Retorna o valor de tabulacaoLinha.
	 */
	public int getTabulacaoLinha() {
		return tabulacaoLinha;
	}

	/**
	 * @param qtdTab
	 *            Seta o valor de tabulacaoLinha.
	 */
	public void setTabulacaoLinha(final int qtdTab) {
		this.tabulacaoLinha = qtdTab;
	}

}
