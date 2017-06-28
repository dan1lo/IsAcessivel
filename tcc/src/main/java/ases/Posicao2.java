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
 * Classe que corresponde a localiza&ccedil;&atilde;o, em linhas e colunas, da
 * tag HTML avaliada na p&aacute;gina avaliada.
 * 
 * @author Acessibilidade Brasil, em 22/08/2005. Refatorado em 04/01/2006.
 * @version 1.1
 */
public final class Posicao2 {
	/**
	 * Linha onde a tag est&aacute; localizada.
	 */
	private int linha;
	/**
	 * Coluna onde a tag est&aacute; localizada.
	 */
	private int coluna;

	/**
	 * Construtor de Posic&ccedil;&atilde;.
	 * 
	 * @param posLinha
	 *            Linha onde est&aacute; a tag.
	 * @param posCol
	 *            Coluna onde est&aacute; a tag.
	 */
	public Posicao2(final int posLinha, final int posCol) {

		this.linha = posLinha;

		this.coluna = posCol;

	}

	/**
	 * Construtor Privado de Posic&ccedil;&atilde;.
	 */
	@SuppressWarnings("unused")
	private Posicao2() {
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return Retorna as coordenadas da tag HTML (linha,coluna).
	 */
	@Override
	public String toString() {
		return this.linha + " | " + this.coluna;
	}

	/**
	 * @return Retorna o valor de coluna.
	 */
	public int getColuna() {
		return coluna;
	}

	/**
	 * @param numCol
	 *            Seta o valor de coluna.
	 */
	public void setColuna(final int numCol) {
		this.coluna = numCol;
	}

	/**
	 * @return Retorna o valor de linha.
	 */
	public int getLinha() {
		return linha;
	}

	/**
	 * @param numLinha
	 *            Seta o valor de linha.
	 */
	public void setLinha(final int numLinha) {
		this.linha = numLinha;
	}

}
