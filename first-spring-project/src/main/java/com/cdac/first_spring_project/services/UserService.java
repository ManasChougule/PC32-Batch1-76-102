package com.cdac.first_spring_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.first_spring_project.dtos.UserDTO;

@Service
public interface UserService {
	public UserDTO getUserById(Integer id);
	public List<UserDTO> getAllUsers();
}
