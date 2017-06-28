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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;

/**
 * Gera os pontos de verifica&ccedil;&atilde;o aqui ocorre a conex&ccedil;o com
 * o banco de dados
 */
public class PontosDeVerificacaoFactory {

	private static final Logger log = Logger.getLogger("br.org.acessibrasil.silvinha");

	/**
	 * Conjunto de Pontos de Verifica&ccedil;&atilde;o que ser&atilde;o
	 * utilizados para avalia&ccedil;&atilde;o dos erros no conte&uacute;do das
	 * p&aacute;ginas de acordo com as regras de Acessibilidade do E-GOV.
	 */
	private static HashMap<Integer, PontoVerificacao> pontosVerificacaoEgov;

	/**
	 * Conjunto de Pontos de Verifica&ccedil;&atilde;o que ser&atilde;o
	 * utilizados para avalia&ccedil;&atilde;o dos erros no conte&uacute;do das
	 * p&aacute;ginas de acordo com as regras de Acessibilidade do E-GOV.
	 */
	private static HashMap<Integer, PontoVerificacao> avisosGenericosEgov;

	/**
	 * Conjunto de Pontos de Verifica&ccedil;&atilde;o que ser&atilde;o
	 * utilizados para avalia&ccedil;&atilde;o dos avisos gen&eacute;ricos no
	 * conte&uacute;do das p&aacute;ginas de acordo com as regras de
	 * Acessibilidade do WCAG.
	 */
	private static HashMap<Integer, PontoVerificacao> pontosVerificacaoWcag;

	/**
	 * Conjunto de Pontos de Verifica&ccedil;&atilde;o que ser&atilde;o
	 * utilizados para avalia&ccedil;&atilde;o dos avisos gen&eacute;ricos no
	 * conte&uacute;do das p&aacute;ginas de acordo com as regras de
	 * Acessibilidade do WCAG.
	 */
	private static HashMap<Integer, PontoVerificacao> avisosGenericosWcag;

	static {

		pontosVerificacaoEgov = new HashMap<Integer, PontoVerificacao>();

		avisosGenericosEgov = new HashMap<Integer, PontoVerificacao>();

		pontosVerificacaoWcag = new HashMap<Integer, PontoVerificacao>();

		avisosGenericosWcag = new HashMap<Integer, PontoVerificacao>();

		inicializaPontosVerificacaoErros();

	}

	private PontosDeVerificacaoFactory() {
	}

	public static HashMap<Integer, PontoVerificacao> getPontosDeVerificacao(int tipoAvaliacao) {

		if (PadraoAcessibilidade.getOrgao(tipoAvaliacao) == PadraoAcessibilidade.EMAG) {

			return pontosVerificacaoEgov;

		}

		return pontosVerificacaoWcag;

	}

	public static HashMap<Integer, PontoVerificacao> getAvisosGenericos(int tipoAvaliacao) {

		if (PadraoAcessibilidade.getOrgao(tipoAvaliacao) == PadraoAcessibilidade.EMAG) {
			return avisosGenericosEgov;
		}

		return avisosGenericosWcag;

	}

	/**
	 * M&eacute;todo respons&aacute;vel por inicializar o atributo
	 * <code>pontosVerificacao</code> a partir de uma consulta ao Banco de
	 * Dados.
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static void inicializaPontosVerificacaoErros() {

		try {

			final String sqlWCAG = "SELECT V.PV3, TA.GL, TA.CP, TA.PRIORIDADE, R.IDREGRA, "
					+ "TA.EXIGENCIA, R.DESCRICAO_REGRA FROM VALIDANTE V "
					+ "INNER JOIN TIPOAVALIACAOERRO TA ON TA.PV3 = V.PV3 "
					+ "INNER JOIN REGRA R ON V.REGRA = R.IDREGRA " + "WHERE TA.IDORGAO = 1";

			final String sqlEGOV = "SELECT V.PV3, TA.GL, TA.CP, TA.PRIORIDADE, R.IDREGRA, "
					+ "TA.EXIGENCIA, R.DESCRICAO_REGRA FROM VALIDANTE V "
					+ "INNER JOIN TIPOAVALIACAOERRO TA ON TA.PV3 = V.PV3 "
					+ "INNER JOIN REGRA R ON V.REGRA = R.IDREGRA " + "WHERE TA.IDORGAO = 2";

			Conexao con = new Conexao();

			Statement st = con.getCon().createStatement();

			// inicializacao WCAG
			ResultSet rs = st.executeQuery(sqlWCAG);

			PontoVerificacao pve = null;

			while (rs.next()) {

				pve = new PontoVerificacao();

				pve.setCp(rs.getInt("cp"));

				pve.setGl(rs.getInt("gl"));

				pve.setPrioridade(rs.getInt("PRIORIDADE"));

				pve.setIdRegra(rs.getInt("IDREGRA"));

				pve.setExigencia(rs.getString("EXIGENCIA").charAt(0));

				pve.setLinhas(new ArrayList<Integer>());

				if (pve.getExigencia() == 'g') {

					avisosGenericosWcag.put(rs.getInt("pv3"), pve);

				} else {

					pontosVerificacaoWcag.put(rs.getInt("pv3"), pve);

				}

			}

			rs.close();

			// inicializacao EGOV
			rs = st.executeQuery(sqlEGOV);

			pve = null;

			while (rs.next()) {

				pve = new PontoVerificacao();

				pve.setCp(rs.getInt("cp"));

				pve.setGl(rs.getInt("gl"));

				pve.setPrioridade(rs.getInt("PRIORIDADE"));

				pve.setIdRegra(rs.getInt("IDREGRA"));

				pve.setExigencia(rs.getString("EXIGENCIA").charAt(0));

				pve.setLinhas(new ArrayList<Integer>());

				if (pve.getExigencia() == 'g') {

					avisosGenericosEgov.put(rs.getInt("pv3"), pve);

				} else {

					pontosVerificacaoEgov.put(rs.getInt("pv3"), pve);

				}

			}

			rs.close();

			/// fim da inicializacao
			st.close();

			con.close();

		} catch (Exception e) {

			// ExceptionDialog.showExceptionDialog(TokenLang.ERRO_1);

			log.fatal(e);

		}

	}

}