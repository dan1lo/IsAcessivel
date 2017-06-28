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

/*
 * Created on 06/03/2005
 */
package ases;

/**
 * Classe que representa um atributo de uma tag HTML.
 * 
 * @author Acessibilidade Brasil, em 22/08/2005. Refatorado em 04/01/2006.
 * @version 1.1
 */
public final class Atributo {
	/**
	 * Nome do atributo.
	 */
	private String nome;

	/**
	 * Valor do atributo.
	 */
	private String valor;

	/**
	 * Construtor de Atributo.
	 * 
	 * @param nome
	 *            Nome do Atributo
	 * @param valor
	 *            Valor do Atributo.
	 */
	public Atributo(final String nome, final String valor) {

		this.valor = valor;
		this.nome = nome;
	}

	/**
	 * Construtor Privado de Atributo.
	 */
	@SuppressWarnings("unused")
	private Atributo() {
	}

	/**
	 * @return Retorna o valor de conte&uacute;do.
	 * @see #valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            Seta o valor de conte&uacute;do.
	 * @see #valor
	 */
	public void setValor(final String valor) {
		this.valor = valor;
	}

	/**
	 * @return Retorna o valor do nome do atributo.
	 * @see #nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            Seta o valor do nome do atributo.
	 * @see #nome
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}
}
