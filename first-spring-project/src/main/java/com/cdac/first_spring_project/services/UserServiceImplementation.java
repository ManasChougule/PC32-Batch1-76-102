package com.cdac.first_spring_project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.first_spring_project.dtos.UserDTO;
import com.cdac.first_spring_project.entities.User;
import com.cdac.first_spring_project.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<UserDTO> getAllUsers(){
		List<User> users =   userRepo.findAll();
		List<UserDTO> result = new ArrayList<>();
		for(User user:users) {
			UserDTO dto = new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getCity());
			result.add(dto);
		}
		return result;
	}
	

	@Override
	public UserDTO getUserById(Integer id) {
		Optional<User> optional = userRepo.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			return new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getCity());
		}
		return null;
	}
}
