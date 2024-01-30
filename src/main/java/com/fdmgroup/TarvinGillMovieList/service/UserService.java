package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<User> findById(int userId) {
		if (userRepository.existsById(userId)) {
			return this.userRepository.findById(userId);
		} else {
			throw new RuntimeException("User with ID: " + userId + " not found");
		}
	}

	public void save(User newUser) {
		if (userRepository.existsById(newUser.getUserId())) {
			throw new RuntimeException(
					"User with ID: " + newUser.getUserId() + " exists, please update correctly using a PUT method");
		}
		this.userRepository.save(newUser);
	}

	public void update(User newUser) {
		if (userRepository.existsById(newUser.getUserId())) {
			this.userRepository.save(newUser);
		} else {
			throw new RuntimeException("No user exists with ID: " + newUser.getUserId());
		}
	}

	public void deleteById(int userId) {
		if (userRepository.existsById(userId)) {
			this.userRepository.deleteById(userId);
		} else {
			throw new RuntimeException("No user exists with ID: " + userId);
		}
	}
}
