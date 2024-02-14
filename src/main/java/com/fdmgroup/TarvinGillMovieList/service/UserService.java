package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.BadRequestException;
import com.fdmgroup.TarvinGillMovieList.exceptions.ConflictException;
import com.fdmgroup.TarvinGillMovieList.exceptions.ForbiddenException;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.User;

@Service
public class UserService {
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> findAll() {
		return this.userRepo.findAll();
	}


	public Optional<User> findById(int userId) {
		checkUserExists(userId);
		return this.userRepo.findById(userId);
	}

	
	public void save(User newUser) throws BadRequestException {
		if (userRepo.existsById(newUser.getUserId())) {
			throw new ConflictException("User with ID: " + newUser.getUserId() + " exists");
		}
		if (userRepo.existsByEmail(newUser.getEmail())) {
			throw new ConflictException("User with email " + newUser.getEmail() + " already exists, please login with your associated username");
		}
		if (userRepo.existsByUsername(newUser.getUsername())) {
			throw new ConflictException( newUser.getUsername() + " has been taken, please type in another username");
		}
		if(newUser.getPassword() == null) {
			throw new BadRequestException("Password cannot be null");
		}
		register(newUser);
	}

	public void update(User newUser) {
		checkUserExists(newUser.getUserId());
		User foundUser= findById(newUser.getUserId()).get();
		updateEmail(newUser, foundUser);
		updateUsername(newUser, foundUser);
		
		this.userRepo.save(newUser);
	}

	public void deleteById(int userId) {
		checkUserExists(userId);
		this.userRepo.deleteById(userId);
	}

	public void updateEmail(User newUser, User foundUser) {
		if (!newUser.getEmail().equals(foundUser.getEmail())) {
			throw new ForbiddenException("Updating user email is not permitted");
		}
	}

	public void updateUsername(User newUser, User foundUser) {
		if (userRepo.existsByUsername(newUser.getUsername())) {
			if (!newUser.getUsername().equals(foundUser.getUsername())) {
				throw new ConflictException("This username has been taken, please type in another username");
			}
		}
	}

	public void checkUserExists(int userId) {
		if (userRepo.existsById(userId) == false) {
			throw new NotFoundException("User with ID: " + userId + " not found");
		}
	}

	public void register(User user) {
        //Hash(encode) the password
	        String hashedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(hashedPassword);
	        this.userRepo.save(user);
    }
}
