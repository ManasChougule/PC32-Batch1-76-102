package com.cdac.first_spring_project.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdac.first_spring_project.dtos.UserDTO;

@Service
public interface UserService {
	public UserDTO getUserById(Integer id);
	public List<UserDTO> getAllUsers();
	public List<UserDTO> getUsersOnPage(int pageNo, int pageSize); 
	public boolean createUser(UserDTO dto);
	public boolean partialUpdate(Integer id, Map<String, Object> updates);
}
