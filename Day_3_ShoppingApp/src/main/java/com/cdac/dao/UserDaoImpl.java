package com.cdac.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cdac.connection.DriverConnection;
import com.cdac.constants.ShoppingAppConstants;
import com.cdac.pojo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean authenticate(User user) throws SQLException, ClassNotFoundException, IOException {
		
		Connection conn = DriverConnection.getConnection();
		PreparedStatement userStatement = conn.prepareStatement(ShoppingAppConstants.AUTHENTICATEQUERY);
		
		userStatement.setString(1, user.getName());
		userStatement.setString(2, user.getPassword());
		
		ResultSet userResult = userStatement.executeQuery();
		
		if(userResult.next())
			return true;

		if(userStatement!=null)
			userStatement.close();
		
		if(conn!=null)
			conn.close();
			
		return false;
	}

	@Override
	public String createUser() {
		return null;
	}

	@Override
	public boolean changePass() {
		return false;
	}

	@Override
	public User getUserDetails() {
		return null;
	}
}
