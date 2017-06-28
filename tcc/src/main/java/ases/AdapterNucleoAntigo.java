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

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.jguedes.tcc.gerenciadorrelatorioarquivo.FachadaArquivador;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

/**
 * Adapter para o nucleo antigo
 * 
 * @author Renato Tomaz Nati
 *
 */

public class AdapterNucleoAntigo implements InterfNucleos {

	/**
	 * retorna o ArrayList de Validados, necess�rio para o processar Erros
	 * 
	 * @author Renato Tomaz Nati
	 * @param contexto
	 * 
	 *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ArmazenaErroOuAviso> getValidados(RelatorioDaUrl relatorio, ContextoDeAvaliacao contexto) {

		ArrayList<ArmazenaErroOuAviso> validados = new ArrayList<ArmazenaErroOuAviso>();

		// precisa atender o preenchimento da interface validado e este estar
		// num array

		try {

			Geral gr = new Geral();

			StringBuilder htmlSource = FachadaArquivador.recuperarArquivoConteudoHTML(contexto,
					relatorio.getLinkEvalCode());

			gr.setStrHtmlSource(htmlSource.toString().trim());

			validados = (ArrayList<ArmazenaErroOuAviso>) gr.getErrados(relatorio.getUrl(),
					contexto.getCriterio().getPadraoAcessibilidade().getValue(), htmlSource);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return validados;

	}

	@Override
	public void addCliente(InterfClienteDoNucleo obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAvaliaCSS(boolean s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEscolheErro(int cod_erro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWCAGEMAG(int ConstRegra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCodHTML(String codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void avalia() {
		// TODO Auto-generated method stub

	}

	@Override
	public InterfRegrasHardCoded getRegras() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRegras(InterfRegrasHardCoded regras) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ArmazenaErroOuAviso> getErroOuAviso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCodWcagEmag() {
		return 0;
	}

	@Override
	public String getCodHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodHTMLOriginal() {

		return null;
	}
}
