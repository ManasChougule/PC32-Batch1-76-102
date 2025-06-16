package com.cdac.acts.Day7_Hibernate.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdac.acts.Day7_Hibernate.entities.User;

@Component
public class AmazonAccount implements Account {

	@Autowired
	private User objUser;

	@Override
	public boolean createAccount(String username, String password, String email, String name, String city) {
		// Logic to create an account
		// For now, we will just return true to indicate success
		return true;
	}

	@Override
	public User getUserAssociatedWithAccount() {
		return new User();
	}

}
