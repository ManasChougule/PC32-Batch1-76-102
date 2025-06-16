package com.cdac.acts.Day7_Hibernate.Account;

import com.cdac.acts.Day7_Hibernate.entities.User;

public interface Account {
	public void setUser(User user);
	public User getUserAssociatedWithAccount();
}
