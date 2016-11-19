package hoge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*	*
 * Simple connection Manager Class
*/

public class ConnectionManager {
	/*	*
	 * JDBC Driver Class
	*/
	final static String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * URL of Database
	*/
	final static String URL = "jdbc:mysql://localhost/mydb";

	/**
	 * Database User
	*/
	final static String USER = "root";

	/*
	 * Database Password
	*/
	final static String PASS = "hoge";

	/**
	 * Connection Get !
	*/
	public static Connection getConnection()
		throws SQLException {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException("fail to class load : "
					+ e.getMessage());
		}

		Connection con = DriverManager.getConnection(URL,
				USER, PASS);
		return con;
	}

	/**
	 * Connection Confirmation
	*/
	public static void main(String[] args) throws SQLException {
		Connection con = getConnection();
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery("select * from account");
		while (rs.next()) {
			String s = "NAME=" + rs.getString("NAME")
					+ ", MONEY=" + rs.getString("MONEY")
					;
					System.out.println(s);
		}
		smt.close();
		con.close();
		System.out.println("END");
	}
}
