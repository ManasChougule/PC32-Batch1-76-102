package com.cdac.acts.Day7_Hibernate.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdac.acts.Day7_Hibernate.entities.User;

@Component
public class AmazonAccount implements Account {

	@Autowired(required = true)
	private User objUser;

	public AmazonAccount(User objUser) {
		super();
		this.objUser = objUser;
	}

	@Override
	public User getUserAssociatedWithAccount() {
		return objUser;
	}

}
