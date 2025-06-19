package com.cdac.first_spring_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.first_spring_project.dtos.UserDTO;
import com.cdac.first_spring_project.repositories.UserRepository;
import com.cdac.first_spring_project.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

		@Autowired
		UserService userService;
		
		@GetMapping("/getAllUsers")
		public List<UserDTO> getAllUsers() {
			return userService.getAllUsers();
		}
	
		
		@GetMapping("/getUserById")
		public UserDTO getUserById(@RequestParam("user_id") Integer userId) {
			return userService.getUserById(userId);
		}
		
		@PostMapping("/addUser")
		public boolean createUser(@RequestBody UserDTO dto) {
			return userService.createUser(dto);
		}
		
		@GetMapping("/getUsersPerPage/{pageNo}/{pageSize}")
		public List<UserDTO> getUsersOnPage(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
			return userService.getUsersOnPage(pageNo, pageSize);
		}
		
		@PatchMapping("/updateUser")
		public boolean partialUpdate() {
			return true;
		}
}
