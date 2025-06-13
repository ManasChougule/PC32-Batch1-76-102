package com.cdac.acts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;

@Entity
//@NamedQueries({
//    @NamedQuery(name = "getAllUsers", query = "FROM User"),
//    @NamedQuery(name = "getUserByName", query = "FROM User WHERE name = :uname"),
//    @NamedQuery(name = "getUserColumns", query = "SELECT name, email FROM User WHERE name = :uname")
//})

@NamedNativeQueries({
    @NamedNativeQuery(name = "getAllUsersNative", query = "SELECT * FROM users", resultClass = User.class),
    @NamedNativeQuery(name = "getUserByNameNative", query = "SELECT * FROM users WHERE name = :uname", resultClass = User.class),
    @NamedNativeQuery(name = "getUserColumnsNative", query = "SELECT name, email FROM users WHERE name = :uname", resultClass = Object[].class)
})
public class User {
	private String name;
	private String password;
	private String email;
	
	public User() {
		super();
	}
	
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.email = null;
	}
	
	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + "]";
	}	
	
	
}
