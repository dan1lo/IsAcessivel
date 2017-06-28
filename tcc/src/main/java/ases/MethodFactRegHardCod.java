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
 * Classe no padr�o de desenho Method Factory que retorna ao cliente um objeto
 * do Org�o escolhido, para incluir mais um org�o basta incluir mais um else
 * if(orgao.equals("***")){return new RegrasHardCodedorgao***();} e implementar
 * a interface InterfRegrasHardCoded. Seria interessante que sempre ao adicionar
 * mais um org�o se utilizasse esta forma para atender ao cliente
 * 
 * @author Renato Tomaz Nati
 * @since 27/07/2007
 * @version 1.0
 */

public class MethodFactRegHardCod {

	/**
	 * @author Renato Tomaz Nati
	 * @return InterfRegrasHardCoded
	 * @since 27/07/2007
	 * @version 1.0
	 */
	public static InterfRegrasHardCoded mFRegHardCod(String orgao) {

		if (orgao.equals("EMAG")) {

			return new RegrasHardCodedEmag();
		} else if (orgao.equals("WCAG")) {

			return new RegrasHardCodedWcag();
		}

		return null;
	}

}
