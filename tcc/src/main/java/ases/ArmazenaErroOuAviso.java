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

/**
 * Classe que representa o resumo da avalia��o de uma tag HTML.
 * 
 * @author Fabio & Renato
 * @version 1.0
 */
public final class ArmazenaErroOuAviso {

	/**
	 * Guarda a regra utilizada
	 */
	private int regraWCAGEMAG;

	/**
	 * Guarda o c�digo da regra
	 */
	private String idTextoRegra;

	/**
	 * Guarda o n�mero da linha com o erro ou aviso
	 */
	private int linha = 0;

	/**
	 * Guarda o n�mero da coluna com o erro ou aviso
	 */
	private int coluna = 0;

	/**
	 * true se estiver guardando um erro
	 */
	private boolean erro = false;

	/**
	 * true se estiver guardando um aviso
	 */
	private boolean aviso = false;
	/**
	 * N�mero de identifica��o do Ponto de verifica��o utilizado.
	 */
	private int pv3;

	/**
	 * Espa�os da indenta��o da tag HTML avaliada.
	 */
	private Espaco1 espaco;

	/**
	 * Posi��o da tag dentro do arquivo pesquisado.
	 */
	private Posicao1 posicao;

	/**
	 * Nome da tag ou atributo procurado.
	 */
	private String procurado;

	/**
	 * String que representa a tag HTML avaliada.
	 */
	private String tagCompleta;

	/**
	 * Length da tag para n�o ter que guardar a string no xml
	 */

	/**
	 * Se para a sua avalia��o � exigida uma outra tag HTML.
	 */
	private boolean tag;

	/**
	 * Construtor especifico para tags se designar tags que devem existir dentro
	 * do documento.
	 * 
	 * @param elemento
	 *            Nome da tag ou atributo que se foi utilizado para avaliar a
	 *            tag HTML.
	 * @param ptVf
	 *            N�mero de identifica��o do Ponto de verifica��o utilizado.
	 */
	public ArmazenaErroOuAviso(final String elemento, final int ptVf) {
		// System.out.print("Validado(elemento='"+elemento+"',ptVf="+ptVf+")\n");
		this.pv3 = ptVf;
		this.procurado = elemento;
		this.posicao = new Posicao1(0, 0);
	}

	/**
	 * Construtor de Validado.
	 * 
	 * @param tagHtml
	 *            Tag HTML avaliada.
	 * @param chkPoint
	 *            N�mero de identifica��o do Ponto de verifica��o utilizado.
	 * @param elemento
	 *            Nome da tag ou atributo que se foi utilizado para avaliar a
	 *            tag HTML.
	 */
	public ArmazenaErroOuAviso(final boolean tagHtml, final int chkPoint, final String elemento) {
		// System.out.print("Validado(tagHtml="+tagHtml+",chkPoint="+chkPoint+",elemento='"+elemento+"')\n");
		this.pv3 = chkPoint;
		this.tag = tagHtml;
		this.procurado = elemento;
	}

	/**
	 * Construtor de Validado.
	 */
	public ArmazenaErroOuAviso() {
	}

	/**
	 * Construtor de Validado.
	 */
	public ArmazenaErroOuAviso(String codigoRegra, boolean isErro, int wcag_emag, String tagInteira) {
		if (isErro) {
			erro = true;
			aviso = false;
		} else {
			aviso = true;
			erro = false;
		}
		setIdTextoRegra(codigoRegra);
		setWCAGEMAG(wcag_emag);
		setTagCompleta(tagInteira);
	}

	/**
	 * @return Retorna o valor de espaco.
	 */
	public Espaco1 getEspaco() {
		return espaco;
	}

	/**
	 * @param espacos
	 *            Seta o valor de espaco.
	 */
	public void setEspaco(final Espaco1 espacos) {
		this.espaco = espacos;
	}

	/**
	 * @return Retorna o valor de posicao.
	 */
	public Posicao1 getPosicao() {
		return posicao;
	}

	/**
	 * @param coords
	 *            Seta o valor de posicao.
	 */
	public void setPosicao(final Posicao1 coords) {
		this.posicao = coords;
	}

	/**
	 * @return Retorna o valor de procurado.
	 */
	public String getProcurado() {
		return procurado;
	}

	/**
	 * @param element
	 *            Seta o valor de procurado.
	 */
	public void setProcurado(final String element) {
		// System.out.print("setProcurado(final String element="+element+")\n");
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
	 * @return Retorna o valor de tag.
	 */
	public boolean isTag() {
		return tag;
	}

	/**
	 * @param isRequired
	 *            Seta o valor de tag.
	 */
	public void setTag(final boolean isRequired) {
		this.tag = isRequired;
	}

	/**
	 * @return Retorna o valor de tagCompleta.
	 */
	public String getTagCompleta() {
		return tagCompleta;
	}

	/**
	 * @param tagFull
	 *            Seta o valor de tagCompleta.
	 */
	public void setTagCompleta(final String tagFull) {
		// System.out.print("setTagCompleta(final String
		// tagFull="+tagFull+")\n");
		this.tagCompleta = tagFull;
	}

	/**
	 * Informa que est� guardando um erro
	 */
	public void setErro() {
		erro = true;
		aviso = false;
	}

	/**
	 * Informa que est� guardando um aviso
	 */
	public void setAviso() {
		erro = false;
		aviso = true;
	}

	/**
	 * Retorna true se estiver guardando um aviso
	 */
	public boolean isAviso() {
		return aviso;
	}

	/**
	 * Retorna true se estiver guardando um erro
	 */
	public boolean isErro() {
		return erro;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	/**
	 * C�digo do identificador da regra Ex: 2.19
	 */
	public String getIdTextoRegra() {
		return idTextoRegra;
	}

	/**
	 * Atribui o c�digo da regra Ex: 2.19 setIdTextoRegra(2,19);
	 * 
	 * @param codigo
	 */
	public void setIdTextoRegra(String codigo) {
		this.idTextoRegra = codigo;
	}

	/**
	 * Retorna a regra utilizada
	 * 
	 * @return InterfNucleos.EMAG ou InterfNucleos.WCAG
	 */
	public int getWCAGEMAG() {
		return regraWCAGEMAG;
	}

	/**
	 * Guarda a regra utilizada InterfNucleos.EMAG ou InterfNucleos.WCAG
	 * 
	 * @param emag_wcag
	 */
	public void setWCAGEMAG(int emag_wcag) {
		this.regraWCAGEMAG = emag_wcag;
	}

}
