package com.cdac.acts.Day7_Hibernate.Account;

import com.cdac.acts.Day7_Hibernate.entities.User;

public interface Account {
	public boolean createAccount(String username, String password, String email, String name, String city);
	public User getUserAssociatedWithAccount();
}
