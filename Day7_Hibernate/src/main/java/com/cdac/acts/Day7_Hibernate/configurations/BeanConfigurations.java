package com.cdac.acts.Day7_Hibernate.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.cdac.acts.Day7_Hibernate.Account.Account;
import com.cdac.acts.Day7_Hibernate.Account.AmazonAccount;
import com.cdac.acts.Day7_Hibernate.Account.FacebookAccount;
import com.cdac.acts.Day7_Hibernate.entities.User;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "com.cdac.acts.Day7_Hibernate.entities")
//@PropertySource("classpath:application.properties")
public class BeanConfigurations {

//	@Bean
//	@Scope("prototype")
//	public User objUser(@Value("${objUser.userName}") String username,
//			@Value("${objUser.password}") String password, 
//			@Value("${objUser.name}") String name,
//			@Value("${objUser.email}") String email, 
//			@Value("${objUser.city}") String city) {
//		return new User(username, password, email, name, city);
//	}
	
	@Bean
	@Scope("prototype")
	public User objUser(@Value("manas") String username,
			@Value("manas") String password, 
			@Value("manas") String name,
			@Value("manas@gmail.com") String email, 
			@Value("kolhapur") String city) {
		return new User(username, password, email, name, city);
	}
	
	
//	@Bean
//	@Scope("prototype")
//	public User objUser() {
//		return new User();
//	}
	
	@Bean
	public Account AmazonAccount(User user) {
		return new AmazonAccount(user);
	}
	
	@Bean
	public Account FacebookAccount(User user) {
		return new FacebookAccount(user);
	}
}
