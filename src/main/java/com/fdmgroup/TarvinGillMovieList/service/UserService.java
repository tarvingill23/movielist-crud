package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.ConflictException;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
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
			throw new NotFoundException("User with ID: " + userId + " not found");
		}
	}

	public void save(User newUser) {
		if (userRepository.existsById(newUser.getUserId())) {
			throw new ConflictException(
					"User with ID: " + newUser.getUserId() + " exists, please update correctly using a PUT method");
		} else if (userRepository.existsByUsername(newUser.getUsername())) {
			throw new ConflictException(
					"This username has been taken, please type in another username");
		} else if (userRepository.existsByEmail(newUser.getEmail())) {
			throw new ConflictException("User with email " + newUser.getEmail() + " already exists, please login");
		} else {
			this.userRepository.save(newUser);
		}
	}

	public void update(User newUser) {
		// checks if the user exists in the database
		if (userRepository.existsById(newUser.getUserId())) {
			this.userRepository.save(newUser);
		// checking for email to be implemented
		} else if (userRepository.existsByUsername(newUser.getUsername())) {
			throw new ConflictException("This username has been taken, please type in another username");
		} else {
			throw new NotFoundException("User with ID: " + newUser.getUserId() + " not found");
		}
	}

	public void deleteById(int userId) {
		if (userRepository.existsById(userId)) {
			this.userRepository.deleteById(userId);
		} else {
			throw new NotFoundException("User with ID: " + userId + " not found");
		}
	}
}
