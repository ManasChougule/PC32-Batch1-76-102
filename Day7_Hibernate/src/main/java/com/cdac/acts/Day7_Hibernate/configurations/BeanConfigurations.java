package com.cdac.acts.Day7_Hibernate.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cdac.acts.Day7_Hibernate.Account.Account;
import com.cdac.acts.Day7_Hibernate.Account.AmazonAccount;
import com.cdac.acts.Day7_Hibernate.Account.FacebookAccount;
import com.cdac.acts.Day7_Hibernate.entities.User;

@Configuration
@ComponentScan(basePackages = "com.cdac.acts.Day7_Hibernate.entities")
public class BeanConfigurations {
	
	@Bean
	@Scope("prototype")
	public User objUser() {
		return new User();
	}
	
	@Bean
	public Account AmazonAccount() {
		return new AmazonAccount();
	}
	
	@Bean
	public Account FacebookAccount() {
		return new FacebookAccount();
	}
}
