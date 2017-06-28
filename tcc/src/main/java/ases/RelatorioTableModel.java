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
import java.util.HashSet;

import javax.swing.table.AbstractTableModel;

import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.org.acessobrasil.silvinha2.mli.GERAL;

/**
 * Modelo de tabela do relat�rio
 *
 */
public class RelatorioTableModel extends AbstractTableModel {
	int cont;

	HashSet<PontoVerificacao> HashPVRelatorio = new HashSet<PontoVerificacao>();

	/**
	 * 
	 */
	private static final long serialVersionUID = -519198052574412142L;

	private String[] columnNames = { GERAL.TBL_RELATORIO_COL_1, GERAL.TBL_RELATORIO_COL_2, GERAL.TBL_RELATORIO_COL_3,
			GERAL.TBL_RELATORIO_COL_4 };

	private Object[][] data;

	public RelatorioTableModel(HashSet<PontoVerificacao> relatorio, String TipoAndPrioridade) {

		cont = 0;
		HashPVRelatorio = relatorio;

		Object[] pvs = relatorio.toArray();
		int wcag_emag;
		// se nucleo antigo
		// inclusaoDeGenericos(pvs,TipoAndPrioridade,Gerente.getProperties().getProperty("tipo_avaliacao"));
		// data = new Object[relatorio.size()+numDeGenericos][4];
		// //

		// se nucleo novo
		data = new Object[relatorio.size()][4];
		// ///

		int contPV = 0;

		for (; cont < data.length; cont++) {

			PontoVerificacao pv = (PontoVerificacao) pvs[contPV];

			wcag_emag = pv.getWcagEmag();

			/*
			 * C�digo da Regra
			 */
			data[cont][0] = "" + (wcag_emag == 2 ? "EMAG: " : "WCAG: ") + pv.getGl() + "." + pv.getCp();
			/*
			 * Texto da regra
			 */
			String texto = TokenLang.getRegra(pv.getIdRegra()).trim();

			if (texto.equals("")) {

				InterfRegrasHardCoded regra;

				if (wcag_emag == 2) {

					regra = MethodFactRegHardCod.mFRegHardCod("EMAG");

				} else {

					regra = MethodFactRegHardCod.mFRegHardCod("WCAG");

				}

				data[cont][1] = regra.getTextoRegra(pv.getGl() + "." + pv.getCp());// +
																					// GERAL.SAIBA_MAIS_PARENTESES;

			} else {

				data[cont][1] = TokenLang.getRegra(pv.getIdRegra()).trim();

			}

			/*
			 * Retira as ocorrencias com o valor do numero da linha igual a 0
			 */
			ArrayList<Integer> linhas = pv.getLinhas();

			int ocorrencias = linhas.size();

			for (int j = 0; j < linhas.size(); j++) {
				if (linhas.get(j).intValue() == 0) {
					linhas.remove(j);
				}
			}
			ocorrencias = linhas.size();

			/*
			 * Ocorrencias
			 */
			data[cont][2] = "Ocorrências: " + (ocorrencias != 0 ? String.valueOf(ocorrencias) : "---");

			/*
			 * Linhas
			 */
			data[cont][3] = "Linhas: " + linhas;
			contPV++;

		}

	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	@Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 3) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private int inclusaoDeGenericos(Object[] pvs, String tipoAndPrioridade, String orgao) {

		InterfRegrasHardCoded regrasHardCoded = null;
		int tipoAndPrio = 0;
		String regras[];
		int cont2 = 0;
		int lengthParaPrio = 0;
		// String EMAG ou String WCAG
		if (tipoAndPrioridade.equals("AvisoP1") || tipoAndPrioridade.equals("AvisoP2")
				|| tipoAndPrioridade.equals("AvisoP3")) {
			if (tipoAndPrioridade.equals("AvisoP1")) {
				tipoAndPrio = 1;
			} else if (tipoAndPrioridade.equals("AvisoP2")) {
				tipoAndPrio = 2;
			} else if (tipoAndPrioridade.equals("AvisoP3")) {
				tipoAndPrio = 3;
			}
			if (orgao.equals(String.valueOf(PadraoAcessibilidade.EMAG))) {
				regrasHardCoded = MethodFactRegHardCod.mFRegHardCod("EMAG");
			} else if (orgao.equals(String.valueOf(PadraoAcessibilidade.WCAG))) {
				regrasHardCoded = MethodFactRegHardCod.mFRegHardCod("WCAG");
			}
			regras = regrasHardCoded.getGenericos();

			for (; cont < regras.length; cont++) {

				if (regras[cont].split("@")[2].equals(String.valueOf(tipoAndPrio))) {
					lengthParaPrio++;
				}

			}

			data = new Object[HashPVRelatorio.size() + lengthParaPrio][4];
			cont = 0;

			for (; cont < regras.length; cont++) {

				if (regras[cont].split("@")[2].equals(String.valueOf(tipoAndPrio))) {
					data[cont2][0] = (regras[cont].split("@"))[0];
					// data[i][1] = pv.getRegra().trim();
					data[cont2][1] = (regras[cont].split("@"))[1];
					data[cont2][2] = "---";
					data[cont2][3] = new ArrayList<Integer>();
					cont2++;
				}
			}
			cont = cont2;
			return cont;
		} else {
			data = new Object[HashPVRelatorio.size()][4];
			return 0;
		}

	}

}