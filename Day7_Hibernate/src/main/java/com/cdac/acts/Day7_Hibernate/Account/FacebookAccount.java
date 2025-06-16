package com.cdac.acts.Day7_Hibernate.Account;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdac.acts.Day7_Hibernate.entities.User;

public class FacebookAccount implements Account {

	@Autowired
	private User objUser;
	
	@Override
	public boolean createAccount(String username, String password, String email, String name, String city) {
		// Logic to create a Facebook account
		// For now, we will just return true to indicate success
		return true;
	}

	@Override
	public User getUserAssociatedWithAccount() {
		return new User(); // Return a new User object for now
	}

}
