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

/**
 * Representa��o de um ponto de verifica��o. Um ponto de verifica��o � a
 * abstra��o de uma recomenda��o da WCAG e/ou eGov, que recebe v�rias linhas e
 * tem prioridades.<br>
 * Revis�o de 21/12/2005: O atributo 'exigencia' define se a recomenda��o �
 * obrigat�ria (erro) ou n�o (aviso).
 * 
 * @author Mariano Aloi, 17/10/2005.
 * @version 1.1, 21/12/2005
 */
public final class PontoVerificacao {

	/**
	 * N�mero de identifica��o da recomenda��o na base de dados.
	 */
	private int idRegra;

	/**
	 * Guarda o c�digo do documento 1 = WCAG 2 = EMAG
	 */
	private int wcag_emag;

	/**
	 * Exig�ncia da Recomenda��o.
	 */
	private char exigencia;

	/**
	 * Guia (Guideline) da recomenda��o.
	 */
	private int gl;

	/**
	 * Pontos de verifica��o (checkpoint) da recomenda��o.
	 */
	private int cp;

	/**
	 * Prioridade da recomenda��o.
	 */
	private int prioridade;

	/**
	 * Linhas onde foram encontrados erros.
	 */
	private ArrayList<Integer> linhas;

	/**
	 * Colunas onde foram encontrados os erros
	 */
	private ArrayList<Integer> colunas;

	/**
	 * Tamanho da tag onde foi encontrado o erro
	 */
	private ArrayList<Integer> tagLength;

	/**
	 * Tag onde foi encontrado o erro<br>
	 * Ex.: &lt;img src=""> <br>
	 * verificar peso em memoria
	 */
	// private ArrayList<String> tagInteira;

	/**
	 * Se a ocorrencia foi um aviso ou erro
	 */
	private ArrayList<String> avisoOuErro;

	/**
	 * Construtor de PontoVerificacao.
	 */
	public PontoVerificacao() {

		this.idRegra = 0;
		this.prioridade = 0;
		this.gl = 0;
		this.cp = 0;
		this.exigencia = 'e';
		this.linhas = new ArrayList<Integer>();
	}

	/**
	 * Construtor de PontoVerificacao.
	 * 
	 * @param texto
	 *            Texto explicativo da recomenda��o.
	 * @param guideline
	 *            Guia (Guideline) da recomenda��o.
	 * @param checkpoint
	 *            Pontos de verifica��o (checkpoint) da recomenda��o.
	 * @param nlinhas
	 *            Linhas onde foram encontrados erros.
	 * @param exige
	 *            Exig�ncia da Recomenda��o.
	 * @deprecated
	 */

	@Deprecated
	public PontoVerificacao(final String texto, final int guideline,

			final int checkpoint, final ArrayList<Integer> nlinhas, final String exige) {

		this.gl = guideline;
		this.cp = checkpoint;
		this.linhas = nlinhas;
		this.exigencia = exige.charAt(0);
	}

	public PontoVerificacao(final String texto, int idRegra, int prioridade, String pontoVerificacaoReal,
			final ArrayList<Integer> nlinhas, final String exige) {

		this.idRegra = idRegra;

		this.prioridade = prioridade;

		this.gl = Integer.parseInt(pontoVerificacaoReal.split("\\.")[0]);

		this.cp = Integer.parseInt(pontoVerificacaoReal.split("\\.")[1]);

		this.linhas = nlinhas;

		this.exigencia = exige.charAt(0);

	}

	public PontoVerificacao(PontoVerificacao pv) {

		this.idRegra = pv.getIdRegra();
		this.prioridade = pv.getPrioridade();
		this.gl = pv.getGl();
		this.cp = pv.getCp();
		this.exigencia = pv.getExigencia();
		this.linhas = pv.getLinhas();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.gl + "." + this.cp + " = " + this.prioridade;
	}

	public int getWcag_emag() {
		return wcag_emag;
	}

	public void setWcag_emag(int wcag_emag) {
		this.wcag_emag = wcag_emag;
	}

	/**
	 * @return Retorna o valor de cp.
	 */
	public int getCp() {
		return cp;
	}

	/**
	 * @param vChkPoint
	 *            para se colocar cp no campo.
	 */
	public void setCp(final int vChkPoint) {
		this.cp = vChkPoint;
	}

	/**
	 * @return Retorna o valor de exigencia.
	 */
	public char getExigencia() {
		return exigencia;
	}

	/**
	 * @param vExige
	 *            para se colocar exigencia no campo.
	 */
	public void setExigencia(final char vExige) {
		this.exigencia = vExige;
	}

	/**
	 * @return Retorna o valor de gl.
	 */
	public int getGl() {
		return gl;
	}

	/**
	 * @param vGuideline
	 *            para se colocar gl no campo.
	 */
	public void setGl(final int vGuideline) {
		this.gl = vGuideline;
	}

	/**
	 * @return Retorna o valor de linhas.
	 */
	public ArrayList<Integer> getLinhas() {
		return linhas;
	}

	/**
	 * @param vLinhas
	 *            para se colocar linhas no campo.
	 */
	public void setLinhas(final ArrayList<Integer> vLinhas) {
		this.linhas = vLinhas;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() { /// HASH CODE!!
		final int num = 1000;
		return (num * this.cp) + this.gl;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {

		boolean resultado = false;

		if (obj instanceof PontoVerificacao) {

			PontoVerificacao chk = (PontoVerificacao) obj;

			if (//

			(chk.getCp() == this.cp) && //
					(chk.getGl() == this.gl) && //
					(chk.getPrioridade() == this.prioridade) && //
					(chk.getExigencia() == this.exigencia) //

			) {
				resultado = true;
			}
		}

		return resultado;

	}

	/**
	 * @return Retorna o valor de prioridade.
	 */
	public int getPrioridade() {
		return prioridade;
	}

	/**
	 * @param vPrior
	 *            valor a ser setado em prioridade.
	 */
	public void setPrioridade(final int vPrior) {
		this.prioridade = vPrior;
	}

	/**
	 * @return Retorna o valor de idRegra.
	 */
	public int getIdRegra() {
		return idRegra;
	}

	/**
	 * @param vRegraID
	 *            valor a ser setado em idRegra.
	 */
	public void setIdRegra(final int vRegraID) {
		this.idRegra = vRegraID;
	}

	/**
	 * Retorna se � wcag ou emag
	 * 
	 * @return
	 */
	public int getWcagEmag() {
		return wcag_emag;
	}

	/**
	 * Define se � wcag ou emag
	 * 
	 * @param wcag_emag
	 */
	public void setWcagEmag(int wcag_emag) {
		this.wcag_emag = wcag_emag;
	}

	/**
	 * Retorna as colunas dos erros encontrados
	 * 
	 * @return
	 */
	public ArrayList<Integer> getColunas() {
		return colunas;
	}

	/**
	 * Atribui a lista de colunas referentes ao n� de linhas encontradas
	 * 
	 * @param cols
	 */
	public void setColunas(ArrayList<Integer> cols) {
		colunas = cols;
	}

	/**
	 * Retorna o tamanho da tag
	 * 
	 * @return
	 */
	public ArrayList<Integer> getTagLength() {
		return tagLength;
	}

	/**
	 * Informa o tamanho da tag
	 * 
	 * @param tagLength
	 */
	public void setTagLength(ArrayList<Integer> tagLength) {
		this.tagLength = tagLength;
	}

	public ArrayList<String> getAvisoOuErro() {
		return avisoOuErro;
	}

	public void setAvisoOuErro(ArrayList<String> avisoOuErro) {
		this.avisoOuErro = avisoOuErro;
	}

	// public ArrayList<String> getTagInteira() {
	// return tagInteira;
	// }
	//
	// public void setTagInteira(ArrayList<String> tagInteira) {
	// this.tagInteira = tagInteira;
	// }

}
