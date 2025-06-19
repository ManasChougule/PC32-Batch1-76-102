package com.cdac.first_spring_project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	@Override
	public boolean createUser(UserDTO dto) {
		try {
			User user = new User();
			BeanUtils.copyProperties(dto, user);
			userRepo.save(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	  
	@Override
	public List<UserDTO> getUsersOnPage(int pageNo, int pageSize){
//		Sort sort =  Sort
//				.by("firstname")
//				.and(Sort.by("lastname").ascending());
		Pageable pageable = PageRequest.of(pageNo, pageSize); // sort
		Page<User> pageResult = userRepo.findAll(pageable);

		List<UserDTO> result = new ArrayList<>();
		for(User user:pageResult) {
			result.add(new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getCity()));
		}
		return result;
	}
	
	
//	@Override
	@PatchMapping("/users/{id}")
	public boolean updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates){
		try {	    
			
			User user = userRepo.findById(id).orElseThrow(() -> new Exception("User Resource not found"));

		    updates.forEach((key, value) -> {
		        switch (key) {
		            case "email" -> user.setEmail((String) value);
		            case "firstname" -> user.setFirstname((String) value);
		            case "lastname" -> user.setLastname((String) value);
		            case "city" -> user.setCity((String) value);
		            // Add more allowed fields here
		            default -> throw new IllegalArgumentException("Field '" + key + "' cannot be updated.");
		        }
		    });

		    userRepo.save(user);
		    return true;
	    }catch(Exception e) {
	    	return false;
	    }
	}


}
