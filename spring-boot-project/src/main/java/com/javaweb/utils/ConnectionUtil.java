package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASS = "0000";

	public static final Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			return con;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}