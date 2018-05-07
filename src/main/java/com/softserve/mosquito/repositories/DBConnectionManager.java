package com.softserve.mosquito.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	private Connection connection;
	
	public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			this.connection = DriverManager.getConnection(dbURL, user, pwd);
		}catch (Exception e) {
			System.out.println("Connection refused..");	
		}
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}