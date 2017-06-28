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
 * @author Acessibilidade Brasil, em 22/08/2005
 */
public final class Hierarquia {
	/**
	 * N&uacute;mero de identifica&ccedil;&atilde;o do Ponto de
	 * verifica&ccedil;&atilde;o utilizado.
	 */
	private int pv3;

	/**
	 * Tag que foi avaliada (validada).
	 */
	private ArmazenaErroOuAvisoAntigo validado;

	/**
	 * Construtor de Hierarquia.
	 * 
	 * @param tempVal
	 *            Inst&acirc;ncia de Validado.
	 * @param chkPoint
	 *            N&uacute;mero de identifica&ccedil;&atilde;o do Ponto de
	 *            verifica&ccedil;&atilde;o utilizado.
	 */
	public Hierarquia(final ArmazenaErroOuAvisoAntigo tempVal, final int chkPoint) {

		this.pv3 = chkPoint;
		this.validado = tempVal;
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
	 * @return Retorna o valor de validado.
	 */
	public ArmazenaErroOuAvisoAntigo getValidado() {
		return validado;
	}

	/**
	 * @param corrigido
	 *            Seta o valor de validado.
	 */
	public void setValidado(final ArmazenaErroOuAvisoAntigo corrigido) {
		this.validado = corrigido;
	}

}
