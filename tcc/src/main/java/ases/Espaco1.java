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
 * Classe repons�vel por informar a quantidade de espa�os e tabula��es
 * no in�cio de cada linha da p�gina avaliada. Ela � importante para reconstru��o da
 * p�gina nos relat�rios, j� que os espa�os e tabula��es s�o perdidos
 * no processo de avalia��o.
 * @author Mariano Aloi, em 22/08/2005. Refatorado por Andr� em 04/01/2006.
 * @version 1.1
 */
public final class Espaco1 {
    /**
     * Quantidade de tabula��es na linha avaliada.
     */
    private int tabulacaoLinha;
    /**
     * Quantidade de espa�os na linha avaliada.
     */
    private int espacoLinha;

    /**
     * Construtor de Espa�o.
     * @param qtdTab Quantidade de tabula��es na linha avaliada.
     * @param qtdEspaco Quantidade de espa�os na linha avaliada.
     */
    public Espaco1(final int qtdTab, final int qtdEspaco) {
   	
    	this.tabulacaoLinha = qtdTab;
        this.espacoLinha = qtdEspaco;
    }

    /**
     * @return Retorna o valor de espacoLinha.
     */
    public int getEspacoLinha() {
        return espacoLinha;
    }

    /**
     * @param qtdEspaco Seta o valor de espacoLinha.
     */
    public void setEspacoLinha(final int qtdEspaco) {
        this.espacoLinha = qtdEspaco;
    }

    /**
     * @return Retorna o valor de tabulacaoLinha.
     */
    public int getTabulacaoLinha() {
        return tabulacaoLinha;
    }

    /**
     * @param qtdTab Seta o valor de tabulacaoLinha.
     */
    public void setTabulacaoLinha(final int qtdTab) {
        this.tabulacaoLinha = qtdTab;
    }

}
