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

import java.util.ArrayList;

import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

/**
 * Interface para os adaptadores de nucleos
 * 
 * @author Renato Tomaz Nati
 * 
 *
 */
public interface InterfNucleos {
	/**
	 * Regra WCAG
	 */
	public static final int WCAG = 1;
	/**
	 * Regra EMAG
	 */
	public static final int EMAG = 2;

	/**
	 * retorna o ArrayList de Validados, necess�rio para o processar Erros
	 */
	public ArrayList<ArmazenaErroOuAviso> getValidados(RelatorioDaUrl relatorio, ContextoDeAvaliacao contexto);

	/**
	 * Adiciona um cliente para retornar as a��es do n�cleo
	 * 
	 * @param objCliente
	 */
	public void addCliente(InterfClienteDoNucleo objCliente);

	/**
	 * Para Wcag Para Emag
	 * 
	 * @param ConstRegra
	 */
	public void setWCAGEMAG(int ConstRegra);

	/**
	 * Op��o para avaliar ou n�o as regras do W3C sobre documentos CSS.
	 */
	public void setAvaliaCSS(boolean s);

	/**
	 * Passa o c�digo do Erro a ser avaliado. Exemplo para avaliar mais de um
	 * erro: nucleo.setEscolheErro(nucleo.IMG_ALT);
	 * nucleo.setEscolheErro(nucleo.LABEL);
	 * nucleo.setEscolheErro(nucleo.FRAME_TITULO);
	 * nucleo.setEscolheErro(nucleo.TABELA_TAM_FIXO);
	 */
	public void setEscolheErro(int cod_erro);

	/**
	 * Passa o c�digo HTML a ser avaliado.
	 */
	public void setCodHTML(String codigo);

	/**
	 * Retorna as regras que o n�cleo est� utilizando
	 * 
	 * @return
	 */
	public InterfRegrasHardCoded getRegras();

	/**
	 * Coloca as regras que o n�cleo est� utilizando
	 * 
	 * @param regras
	 */
	public void setRegras(InterfRegrasHardCoded regras);

	/**
	 * Avalia a p�gina
	 */
	public void avalia();

	/**
	 * Retorna os erros de forma bruta sem separar por prioridades
	 * 
	 * @return ArrayList de ArmazenaErroOuAviso
	 */
	public ArrayList<ArmazenaErroOuAviso> getErroOuAviso();

	public int getCodWcagEmag();

	public String getCodHTML();

	public String getCodHTMLOriginal();
}
