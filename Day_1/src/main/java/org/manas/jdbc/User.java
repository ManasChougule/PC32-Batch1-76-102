package org.manas.jdbc;

public class User {
	String user_name;
	Integer user_id;
	String email;
	User(String user_name, int user_id, String email){
		this.user_name = user_name;
		this.user_id = user_id;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", user_id=" + user_id + ", email=" + email + "]";
	}
	
	
}