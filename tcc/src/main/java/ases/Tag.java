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
 * @author Acessibilidade Brasil, em 22/08/2005.
 */
public final class Tag {

	/**
	 * Posi&ccedil;&atilde;o da tag HTML na p&aacute;gina avaliada.
	 */
	private Posicao2 p;
	/**
	 * Texto da tag HTML.
	 */
	private String texto;
	/**
	 * Espa&ccedil;os ou tabula&ccedil;&otilde;es, utilizadas para formatar a
	 * tag dentro da linha na p&aacute;gina avaliada.
	 */
	private Espaco2 espaco;
	/**
	 * N&uacute;mero de identifica&ccedil;&atilde;o do Ponto de
	 * verifica&ccedil;&atilde;o utilizado.
	 */
	private int pv3;

	/**
	 * Construtor de Tag.
	 * 
	 * @param position
	 *            Posi&ccedil;&atilde;o da tag HTML na p&aacute;gina avaliada.
	 * @param text
	 *            Texto da tag HTML.
	 * @param espacos
	 *            Dados referentes a espa&ccedil;amento.
	 */
	public Tag(final Posicao2 position, final String text, final Espaco2 espacos) {

		this.p = position;

		this.texto = text;

		this.espaco = espacos;

	}

	/**
	 * Construtor de Tag.
	 * 
	 * @param position
	 *            Posi&ccedil;&atilde;o da tag HTML na p&aacute;gina avaliada.
	 * @param text
	 *            Texto da tag HTML.
	 * @param espacos
	 *            Dados referentes a espa&ccedil;amento.
	 * @param chkPoint
	 *            N&uacute;mero de identifica&ccedil;&atilde;o do Ponto de
	 *            verifica&ccedil;&atilde;o utilizado.
	 */
	public Tag(final Posicao2 position, final String text, final Espaco2 espacos, final int chkPoint) {

		this.p = position;

		this.texto = text;

		this.espaco = espacos;

		this.pv3 = chkPoint;

	}

	/**
	 * @return Retorna o valor de espaco.
	 */
	public Espaco2 getEspaco() {
		return espaco;
	}

	/**
	 * @param espacos
	 *            Seta o valor de espaco.
	 */
	public void setEspaco(final Espaco2 espacos) {
		this.espaco = espacos;
	}

	/**
	 * @return Retorna o valor de p.
	 */
	public Posicao2 getP() {
		return p;
	}

	/**
	 * @param coords
	 *            Seta o valor de p.
	 */
	public void setP(final Posicao2 coords) {
		this.p = coords;
	}

	/**
	 * @return Retorna o valor de pv3.
	 */
	public int getPv3() {
		return pv3;
	}

	/**
	 * @param chkPoint
	 *            Seta o valor de pv3.
	 */
	public void setPv3(final int chkPoint) {
		this.pv3 = chkPoint;
	}

	/**
	 * @return Retorna o valor de texto.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param text
	 *            Seta o valor de texto.
	 */
	public void setTexto(final String text) {
		this.texto = text;
	}
}
