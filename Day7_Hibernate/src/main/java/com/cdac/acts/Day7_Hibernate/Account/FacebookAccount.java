package com.cdac.acts.Day7_Hibernate.Account;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdac.acts.Day7_Hibernate.entities.User;

public class FacebookAccount implements Account {

	private User objUser;
	

//	public FacebookAccount(User objUser) {
//		super();
//		this.objUser = objUser;
//	}
	
	@Autowired
	public void setUser(User user) {
		objUser=user;
	}

	@Override
	public User getUserAssociatedWithAccount() {
		return objUser; 
	}

}
