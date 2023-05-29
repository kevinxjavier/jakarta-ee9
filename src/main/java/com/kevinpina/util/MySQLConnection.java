package com.kevinpina.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQLConnection {

	private static Connection connection;
	private static String url = "jdbc:mysql://localhost:3306/enterprise?serverTimezone=America/Santiago";// ?serverTimezone=America/Santiago
	private static String username = "root";
	private static String password = "249861";

	// Singleton: metodo static y validar que sea null el objeto coneccion
	public static Connection getConnection() throws SQLException {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		if (connection == null) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

}
