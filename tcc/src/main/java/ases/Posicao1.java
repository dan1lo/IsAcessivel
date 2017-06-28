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
 * Classe que corresponde a localiza��o, em linhas e colunas,
 * da tag HTML avaliada na p�gina avaliada.
 * @author Mariano Aloi, em 22/08/2005.Refatorado por Andr� em 04/01/2006.
 * @version 1.1
 */
public final class Posicao1 {
    /**
     * Linha onde a tag est� localizada.
     */
    private int linha;
    /**
     * Coluna onde a tag est� localizada.
     */
    private int coluna;

    /**
     * Construtor de Posicao.
     * @param posLinha Linha onde est� a tag.
     * @param posCol Coluna onde est� a tag.
     */
    public Posicao1(final int posLinha, final int posCol) {
   	 
    	this.linha = posLinha;
        this.coluna = posCol;
    }

    /**
     * @see java.lang.Object#toString()
     * @return Retorna as coordenadas da tag HTML (linha,coluna).
     */
    @Override public String toString() {
        return this.linha + " | " + this.coluna;
    }

    /**
     * @return Retorna o valor de coluna.
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * @param numCol Seta o valor de coluna.
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
     * @param numLinha Seta o valor de linha.
     */
    public void setLinha(final int numLinha) {
        this.linha = numLinha;
    }


}
