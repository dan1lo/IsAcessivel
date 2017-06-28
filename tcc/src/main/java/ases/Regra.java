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
 * Classe que representa uma regra ou recomenda&ccedil;&atilde;o de
 * acessibilidade.
 * 
 * @author Acessibilidade Brasil, em 22/08/2005. Refatorado em 04/01/2006.
 * @version 1.1
 */
public final class Regra {
	/**
	 * N&uacute;mero de identifica&ccedil;&atilde;o do Ponto de
	 * verifica&ccedil;&atilde;o utilizado.
	 */
	private int pv3;

	/**
	 * Atitude ou comportamento necess&aacute;rio para a tag ser avaliada.
	 */
	private int atitude;

	/**
	 * Tamanho m&iacute;nimo que o valor de um atributo deve ter.
	 */
	private int ti;

	/**
	 * Tamanho m&aacute;ximo que o valor de um atributo deve ter.
	 */
	private int tx;

	/**
	 * Valida&ccedil;&atilde;o hier&aacute;rquica.
	 */
	private int hierarquia;

	/**
	 * Se existe existe alguma depend&ecirc;ncia.
	 */
	private int dep;

	/**
	 * Nome da tag HTML ou atributo necess&aacute;rios para a
	 * avalia&ccedil;&atilde;o.
	 */
	private String procurado;

	/**
	 * Valor que o atributo deve ter.
	 */
	private String vs;

	/**
	 * valor que o atributo n&atilde;o pode ter.
	 */
	private String vn;

	/**
	 * @see java.lang.Object#toString()
	 * @return string contendo Procurado + VS + VN + PV3.
	 */
	@Override
	public String toString() {
		return this.procurado + " " + this.vs + " " + this.vn + " " + this.pv3;
	}

	/**
	 * @return Retorna o valor de atitude.
	 */
	public int getAtitude() {

		return atitude;
	}

	/**
	 * @param atitudeID
	 *            Seta o valor de atitude.
	 */
	public void setAtitude(final int atitudeID) {

		this.atitude = atitudeID;
	}

	/**
	 * @return Retorna o valor de dep.
	 */
	public int getDep() {
		return dep;
	}

	/**
	 * @param depend
	 *            Seta o valor de dep.
	 */
	public void setDep(final int depend) {
		this.dep = depend;
	}

	/**
	 * @return Retorna o valor de hierarquia.
	 */
	public int getHierarquia() {
		return hierarquia;
	}

	/**
	 * @param hierarq
	 *            Seta o valor de hierarquia.
	 */
	public void setHierarquia(final int hierarq) {
		this.hierarquia = hierarq;
	}

	/**
	 * @return Retorna o valor de procurado.
	 * @see #procurado
	 */
	public String getProcurado() {
		return procurado;
	}

	/**
	 * @param element
	 *            Seta o valor de procurado.
	 * @see #procurado
	 */
	public void setProcurado(final String element) {
		this.procurado = element;
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
	 * @return Retorna o valor de ti.
	 * @see #ti
	 */
	public int getTi() {
		return ti;
	}

	/**
	 * @param tamMin
	 *            Seta o valor de ti.
	 * @see #ti
	 */
	public void setTi(final int tamMin) {
		this.ti = tamMin;
	}

	/**
	 * @return Retorna o valor de tx.
	 * @see #tx
	 */
	public int getTx() {
		return tx;
	}

	/**
	 * @param tamMax
	 *            Seta o valor de tx.
	 * @see #tx
	 */
	public void setTx(final int tamMax) {
		this.tx = tamMax;
	}

	/**
	 * @return Retorna o valor de vn.
	 * @see #vn
	 */
	public String getVn() {
		return vn;
	}

	/**
	 * @param valueNo
	 *            Seta o valor de vn.
	 * @see #vn
	 */
	public void setVn(final String valueNo) {
		this.vn = valueNo;
	}

	/**
	 * @return Retorna o valor de vs.
	 * @see #vs
	 */
	public String getVs() {
		return vs;
	}

	/**
	 * @param valueYes
	 *            Seta o valor de vs.
	 * @see #vs
	 */
	public void setVs(final String valueYes) {
		this.vs = valueYes;
	}
}
