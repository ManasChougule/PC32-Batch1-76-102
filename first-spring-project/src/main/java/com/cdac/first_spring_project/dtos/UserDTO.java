package com.cdac.first_spring_project.dtos;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class UserDTO {
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String city;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Integer id, String username, String firstname, String lastname, String email, String city) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.city = city; 
	}
	public UserDTO(Integer id, String username, String password, String firstname, String lastname, String email, String city) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.city = city;
		this.password = password; // need to accept during add user
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
