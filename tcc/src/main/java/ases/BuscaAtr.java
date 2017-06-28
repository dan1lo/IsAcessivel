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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementa&ccedil;&atilde;o da regra de avalia&ccedil&atilde;o de uma tag no
 * qual ele busca:
 * <ol>
 * <li>Exist&ecirc;ncia ou n&atilde;o do atributo</li>
 * <li>Array de valores que podem existir no atributo.</li>
 * <li>Array de valores que n&atilde;o podem existir no atributo.</li>
 * <li>Tamanho m&aacute;nimo do atributo.</li>
 * <li>Tamanho m&iacute;ximo do atributo.</li>
 * </ol>
 * 
 * @author Acessibilidade Brasil.
 */
public class BuscaAtr {

	/**
	 * Avalia&ccedil;&atilde;o do atributo sobre a vis&atilde;o apresentada em
	 * banco.
	 * 
	 * @param regra
	 *            conjunto de regras para a tag.
	 * @param valor
	 *            conte&uacute;do de um atributo.
	 * @return verdadeiro se ele n&atilde;o tiver nenhum problema com os
	 *         par&acirc;metros oferecidos.
	 */
	private boolean confere(final Regra regra, final String valor) {
		/**
		 * Valor sim.
		 */
		final String sim = regra.getVs();
		/*
		 * Valor n&atilde;o.
		 */
		final String nao = regra.getVn();
		/*
		 * Valor m&iacute;nimo.
		 */
		final int minimo = regra.getTi();
		/*
		 * Valor m&aacute;ximo.
		 */
		final int maximo = regra.getTx();

		if (maximo != 0 && valor.length() > maximo) {
			return false;
		}

		if (minimo != 0 && valor.length() < minimo) {
			return false;
		}

		if (sim != null && !"".equals(sim)) {

			String[] array = sim.split(";");

			boolean bool = true;

			for (int i = 0; i < array.length; i++) {

				String string = array[i];

				if (valor.toUpperCase().indexOf(string.toUpperCase()) >= 0) {

					bool = false;

				}

			}

			if (bool) {

				return false;

			}

		}

		if (nao != null && !"".equals(nao)) {

			String[] array = nao.split(";");

			for (int i = 0; i < array.length; i++) {

				String string = array[i];

				if (valor.toUpperCase().indexOf(string.toUpperCase()) >= 0) {

					return false;

				}

			}

		}

		return true;

	}

	/**
	 * Iterador de atributos para que possam ser avaliados. ele tem o objetivo
	 * de afirmar se o atributo &eacute; incorreto, mas ela tenta sempre afirmar
	 * que a tag &eacute; incorreta.
	 * 
	 * @param regra
	 *            para avalia&ccedil;&atilde;o do atributo.
	 * @param atributos
	 *            atributos da tag.
	 * @return falso se o conjunto de tags n&atilde;o contiver erros.
	 */
	public boolean validacao(final Regra regra, final ArrayList<Atributo> atributos) {

		boolean bol = true;

		for (Atributo atr : atributos) {

			if (regra.getProcurado().equalsIgnoreCase(atr.getNome())) {

				bol = !confere(regra, atr.getValor());

			}

		}

		return bol;

	}

	/**
	 * Iterador de atributos para que possam ser avaliados. ele tem o objetivo
	 * de afirmar se o atributo &eacute; incorreto, mas ela tenta sempre afirmar
	 * que a tag &eacute; correta.
	 * 
	 * @param regra
	 *            para avalia&ccedil;&atilde;o do atributo.
	 * @param atributos
	 *            atributos da tag.
	 * @return falso se o conjunto de tags n&atilde;o contiver erros.
	 */
	public boolean validacaoIncertiva(final Regra regra, final ArrayList<Atributo> atributos) {

		boolean bol = false;

		Iterator<Atributo> iatributo = atributos.iterator();

		Atributo atrib = null;

		while (iatributo.hasNext()) {

			atrib = iatributo.next();

			if (regra.getProcurado().equalsIgnoreCase(atrib.getNome())) {

				bol = true;

				bol = !confere(regra, atrib.getValor());

			}
		}

		return bol;
	}

}
