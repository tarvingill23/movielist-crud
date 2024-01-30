package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.model.User;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public User findById(int userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("user not found for" + userId));
	}

	public void save(User newUser) {
		this.userRepository.save(newUser);
	}

	public void update(User newUser) {
		if (userRepository.existsById(newUser.getUserId())) {
			userRepository.save(newUser);
			return; // stops error from throwing 
		}
		throw new RuntimeException("Invalid ID: " + newUser.getUserId());
	}
	
	public void deleteById(int userId) {
		userRepository.deleteById(userId);
	}
}
