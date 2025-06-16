package com.cdac.acts.Day7_Hibernate.Account;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdac.acts.Day7_Hibernate.entities.User;

public class FacebookAccount implements Account {

	@Autowired
	private User objUser;
	

	public FacebookAccount(User objUser) {
		super();
		this.objUser = objUser;
	}

	@Override
	public User getUserAssociatedWithAccount() {
		return objUser; 
	}

}
