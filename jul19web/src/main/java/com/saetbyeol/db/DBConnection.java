package com.saetbyeol.db;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection dbConn() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/saetbyeol";
			String id = "root";
			String pw = "1234";
			conn = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
