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

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Conex&atilde;o com o banco de dados
 * 
 * @author Acessibilidade Brasil, em 22/08/2005.
 */
public class Conexao {
	/**
	 * conex&atilde;o
	 */
	private static Connection con;

	/**
	 * Implementa&ccedil;&atilde;o de encapsulamento da field con que retorna o
	 * seu valor em Connection
	 * 
	 * @return con que &eacute; Connection.
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * Respons&aacute;vel pela abertura da conex&atilde;o com o banco
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Conexao() throws ClassNotFoundException, SQLException {

		// Class.forName(TokenNucleo.DRIVER);// instanciacao do driver
		// con = DriverManager.getConnection(TokenNucleo.CONECTOR +
		// TokenNucleo.URL, TokenNucleo.LOGIN, TokenNucleo.SENHA); // abertura
		// da conexao
		// con =
		// DriverManager.getConnection("jdbc:hsqldb:file:basedados/regras",
		// "sa", "");
		con = ConexaoMysql.mysqlConnectionCreateDB("basedados_regras");

		criarTabelas();
	}

	public static void main(String[] args) {
		try {
			new Conexao();
			con.getMetaData().getDatabaseProductName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void criarTabelas() {
		try {
			if (con.getMetaData().getDatabaseProductName().equalsIgnoreCase("MySQL")) {
				criarTabelasMYSQL();
			} else if (con.getMetaData().getDatabaseProductName().equalsIgnoreCase("HSQL Database Engine")) {
				criarTabelasHSQLDB();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void criarTabelasMYSQL() throws SQLException {

		String sql = "DROP TABLE IF EXISTS TIPOAVALIACAOERRO";

		con.createStatement().execute(sql);

		sql = "DROP TABLE IF EXISTS VALIDANTE";

		con.createStatement().execute(sql);

		sql = "DROP TABLE IF EXISTS REGRA";

		con.createStatement().execute(sql);

		sql = "CREATE TABLE REGRA (".concat("IDREGRA INTEGER NOT NULL auto_increment primary key,")
				.concat("DESCRICAO_REGRA VARCHAR(255),").concat("REGRA INTEGER").concat(")");

		con.createStatement().execute(sql);

		sql = "CREATE TABLE VALIDANTE (".concat("id INTEGER NOT NULL auto_increment primary key,")
				.concat("PV3 INTEGER,").concat("REGRA INTEGER,").concat("UNIQUE(PV3),")
				.concat("FOREIGN KEY(REGRA) REFERENCES REGRA(IDREGRA)").concat(")");

		con.createStatement().execute(sql);

		sql = "CREATE TABLE TIPOAVALIACAOERRO (".concat("id INTEGER NOT NULL auto_increment primary key,")
				.concat("GL INTEGER,").concat("CP INTEGER,").concat("PRIORIDADE INTEGER,").concat("EXIGENCIA INTEGER,")
				.concat("PV3 INTEGER,").concat("IDORGAO INTEGER,").concat("FOREIGN KEY(PV3) REFERENCES VALIDANTE(PV3)")
				.concat(")");

		con.createStatement().execute(sql);
	}

	private void criarTabelasHSQLDB() throws SQLException {
		String sql = "DROP TABLE IF EXISTS TIPOAVALIACAOERRO";

		con.createStatement().execute(sql);

		sql = "DROP TABLE IF EXISTS VALIDANTE";

		con.createStatement().execute(sql);

		sql = "DROP TABLE IF EXISTS REGRA";

		con.createStatement().execute(sql);

		sql = "CREATE TABLE REGRA (".concat("IDREGRA INTEGER NOT NULL IDENTITY,")
				.concat("DESCRICAO_REGRA VARCHAR(255),").concat("REGRA INTEGER").concat(")");

		con.createStatement().execute(sql);

		sql = "CREATE TABLE VALIDANTE (".concat("id INTEGER NOT NULL IDENTITY,").concat("PV3 INTEGER,")
				.concat("REGRA INTEGER,").concat("UNIQUE(PV3),").concat("FOREIGN KEY(REGRA) REFERENCES REGRA(IDREGRA)")
				.concat(")");

		con.createStatement().execute(sql);

		sql = "CREATE TABLE TIPOAVALIACAOERRO (".concat("id INTEGER NOT NULL IDENTITY,").concat("GL INTEGER,")
				.concat("CP INTEGER,").concat("PRIORIDADE INTEGER,").concat("EXIGENCIA INTEGER,").concat("PV3 INTEGER,")
				.concat("IDORGAO INTEGER,").concat("FOREIGN KEY(PV3) REFERENCES VALIDANTE(PV3)").concat(")");

		con.createStatement().execute(sql);
	}

	public void close() throws SQLException {
		con.close();
	}

}
