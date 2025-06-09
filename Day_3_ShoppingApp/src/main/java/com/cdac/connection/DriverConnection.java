package com.cdac.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cdac.constants.ShoppingAppConstants;

public class DriverConnection {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		Class.forName(ShoppingAppConstants.JDBCDRIVER);
		return DriverManager.getConnection(ShoppingAppConstants.URL, ShoppingAppConstants.USERNAME, ShoppingAppConstants.PASSWORD);
	}	
}