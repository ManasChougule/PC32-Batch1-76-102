package com.cdac.dao;
import java.io.IOException;
import java.sql.SQLException;

import com.cdac.pojo.User;

public interface UserDao {
	public boolean authenticate(User user) throws SQLException, ClassNotFoundException, IOException;

	public String createUser();

	public boolean changePass();

	public User getUserDetails();
}