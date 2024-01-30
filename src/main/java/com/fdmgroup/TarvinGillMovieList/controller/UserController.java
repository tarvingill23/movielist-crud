package com.fdmgroup.TarvinGillMovieList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.TarvinGillMovieList.model.User;
import com.fdmgroup.TarvinGillMovieList.service.UserService;

@RestController
public class UserController {
private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId) {
		return userService.findById(userId);
	}
	
	@PostMapping("users")
	public void createNew(@RequestBody User newUser) {
		System.out.println(newUser);
		userService.save(newUser);
	}
	
	@PutMapping("users")
	public void updateUser(@RequestBody User newUser) {
		System.out.println(newUser);
		userService.update(newUser);
	}

	@DeleteMapping ("users/{userId}") 
	public void deleteUser(@PathVariable int userId) {
		userService.deleteById(userId);
	}
}
