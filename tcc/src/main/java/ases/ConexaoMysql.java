package ases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMysql {
	public static Connection mysqlConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String serverName = "localhost"; // caminho do servidor do BD
		String url = "jdbc:mysql://" + serverName;// + "/" + mydatabase;
		String username = "root"; // nome de um usuário de seu BD
		String password = ""; // sua senha de acesso
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static Connection mysqlConnectionUseDB(String nomeDB) throws SQLException {
		String mydatabase; // nome do seu banco de dados
		Connection conn = mysqlConnection();
		if (nomeDB == null || nomeDB.trim().isEmpty()) {
			return conn;
		} else {
			mydatabase = nomeDB.trim().replace(".", "_");
		}
		Statement st = conn.createStatement();
		st.executeUpdate("USE " + mydatabase);
		return conn;

	}

	public static Connection mysqlConnectionCreateDB(String nomeDB) throws SQLException {
		String mydatabase; // nome do seu banco de dados
		Connection conn = mysqlConnection();
		if (nomeDB == null || nomeDB.trim().isEmpty()) {
			return conn;
		} else {
			mydatabase = nomeDB.trim().replace(".", "_");
		}
		Statement st = conn.createStatement();
		st.executeUpdate("DROP DATABASE IF EXISTS " + mydatabase);
		st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + mydatabase);
		st.executeUpdate("USE " + mydatabase);
		return conn;
	}
}
