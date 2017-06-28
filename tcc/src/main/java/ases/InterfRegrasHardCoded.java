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
 * Interface para as regras HardCoded de variados org�os
 * 
 * @author Renato Tomaz Nati & Fabio Issamu Oshiro
 * @since 27/07/2007
 * @version 1.0
 */
public interface InterfRegrasHardCoded {
	/**
	 * Verifica se a extens�o � .gif
	 * @param tagInteira
	 * @return true caso no final seja gif
	 */
	public boolean verificaIsGif(String tagInteira);
	
	/**
	 * M�todo que retorna os avisos Gen�ricos de determinado org�o(ex.:
	 * EMAG,WCAG)
	 * 
	 * @return String[] deve seguir este formato: 
	 *         gl.cp@regrax@prioridade@***@...
	 * @since 27/07/2007
	 */
	public String[] getGenericos();
	public boolean verificaLabel(String codHTML,String idField);
	public int verificaH1aH6(String codHTML);
	
	/**
	 * Verifica caracteres predefinidos
	 * @param tagInput
	 * @return true
	 */
	public boolean verificaTextoPredefinidoInput(String tagInput);
	
	/**
	 * Verifica se a tag tem algum parametro indicando tamanho fixo
	 * Hoje n�o vai at� um arquivo CSS separado
	 * @param tag
	 * @return true caso esteja ok
	 */
	public boolean verificaTamFixoEmAtributoStyle(String tag);
	
	/**
	 * Verifica se a tag html tem um label associado Input text Input (text
	 * default) Input password Input checkbox Input radio Input file textarea
	 * (sem type) select (sem type)
	 * 
	 * @param codHTML
	 * @param tag
	 * @return true caso esteja com label associado ou n�o precise
	 */
	public boolean verificaLabelTag(String codHTML,String tag);
	
	/**
	 * Verifica se existe texto predefinido para a tag area
	 * @param codHTML
	 * @param tag
	 * @param tagIndex in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto predefinido
	 */
	public boolean verificaTextoPredefinidoTextarea(String codHTML,String tag,int tagIndex);
	
	/**
	 * Verifica se existe texto predefinido para a tag area
	 * @param codHTML
	 * @param tag
	 * @param tagIndex in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto predefinido
	 *  @deprecated
	 */
	public boolean verificaTextoPredefinidoTextarea(String codHTML,String tag);
	
	public boolean verificaSeparadorEmLink(String codHTML);
	/**
	 * Verifica se existe uma tag noscript no c�digo html
	 * @param codHTML
	 * @return true caso exista alguma
	 */
	public boolean verificaNoscript(String codHTML);
	
	/**
	 * Verifica se est� na lista de Deprecated ou Non Standard
	 * @param tagName
	 * @return true
	 */
	public boolean verificaDeprecatedNonStandard(String tagName);
	
	/**
	 * Verifica se a tag possui o stributo lang
	 * @param tag
	 * @return true
	 */
	public boolean verificaLang(String tag);
	
	/**
	 * Verifica se a tag link � de css
	 * @param tagInteira
	 * @return true caso seja link de CSS
	 */
	public boolean verificaIsCSS(String tagInteira);
	
	/**
	 * Verifica se h� algum parametro de cor
	 * @param tagInteira
	 * @return true caso haja parametro de cor
	 */
	public boolean verificaCorStyleInline(String tagInteira);
	
	/**
	 * Verifica se na tag h� um longdesc
	 * @param tagInteira
	 * @return true caso tenha um long desc
	 */
	public boolean verificaLongDesc(String tagInteira);
	
	/**
	 * Retorna o texto da regra
	 * Ex: 2.19 
	 * @param codigo c�digo da regra (2.19)
	 * @return Texto da regra
	 */
	public String getTextoRegra(String codigo);
	
	/**
	 * Verifica se possui um atributo style
	 * @param tagInteira
	 * @return true caso tenha algun style
	 */
	public boolean verificaPossuiStyle(String tagInteira);
	
	/**
	 * Retorna o atributo especificado caso n�o tenha retorna ""
	 * @param tag
	 * @param att
	 * @return valor
	 */
	public String getAtributo(String tagInteira, String att);
	
	/**
	 * Retorna a prioridade da regra
	 * Ex: 2.19 
	 * @param codigo c�digo da regra (2.19)
	 * @return Prioridade da regra
	 */
	public int getPrioridadeRegra(String codigo);
	
	/**
	 * Verifica se existe height ou width com tamanho fixo]
	 * @return true caso haja tamanho fixo
	 */
	public boolean verificaTamFixoAtributoWidhtHeight(String tagInteira);
	
	/**
	 * As classes de regras devem informar qual � o c�digo da regra 
	 * @return 1 para Wcag
	 * 2 para Emag 
	 */
	public abstract int getCodWcagEmag();
	
	/**
	 * Verifica se existe texto dentro das tags object
	 * @param codHTML
	 * @param tag
	 * @param tagIndex in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto predefinido
	 */
	public boolean verificaTextoEmObject(String codHTML,String tag,int tagIndex);
	
	/**
	 * Verifica se existe texto na tag iframe
	 * 
	 * @param codHTML
	 * @param tag
	 * @param tagIndex
	 *            in�cio de onde come�a a tag no c�digo html
	 * @return true caso exista texto dentro de iframe
	 */
	public boolean verificaTextoIframe(String codHTML, String tag, int tagIndex);
}