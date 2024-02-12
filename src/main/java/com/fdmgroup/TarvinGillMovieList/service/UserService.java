package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.ConflictException;
import com.fdmgroup.TarvinGillMovieList.exceptions.ForbiddenException;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// done
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	// done
	public Optional<User> findById(int userId) {
		checkUserExists(userId);
		return this.userRepository.findById(userId);
	}

	public Optional<User> verifyUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			Optional<User> foundOpUser = userRepository.findByEmail(user.getEmail());
			User foundUser = foundOpUser.get();
			if (foundUser.getPassword().equals(user.getPassword())) {
				return this.userRepository.findById(foundUser.getUserId());
			}
		}
		throw new NotFoundException("User with email " + user.getEmail() + " not found");
	}

	// done
	public void save(User newUser) {
		if (userRepository.existsById(newUser.getUserId())) {
			throw new ConflictException("User with ID: " + newUser.getUserId() + " exists");
		}
		if (userRepository.existsByEmail(newUser.getEmail())) {
			throw new ConflictException("User with email " + newUser.getEmail() + " already exists, please login with your associated username");
		}
		if (userRepository.existsByUsername(newUser.getUsername())) {
			throw new ConflictException( newUser.getUsername() + " has been taken, please type in another username");
		}
		register(newUser);
	}

	public void update(User newUser) {
		checkUserExists(newUser.getUserId());
		Optional<User> user = findById(newUser.getUserId());
		User foundUser = user.get();
		updateEmail(newUser, foundUser);
		updateUsername(newUser, foundUser);

		this.userRepository.save(newUser);
	}

	public void deleteById(int userId) {
		checkUserExists(userId);
		this.userRepository.deleteById(userId);
	}

	public void updateEmail(User newUser, User foundUser) {
		if (!newUser.getEmail().equals(foundUser.getEmail())) {
			throw new ForbiddenException("Updating user email is not permitted");
		}
	}

	public void updateUsername(User newUser, User foundUser) {
		if (userRepository.existsByUsername(newUser.getUsername())) {
			if (!newUser.getUsername().equals(foundUser.getUsername())) {
				throw new ConflictException("This username has been taken, please type in another username");
			}
		}
	}

	public void checkUserExists(int userId) {
		if (userRepository.existsById(userId) == false) {
			throw new NotFoundException("User with ID: " + userId + " not found");
		}
	}

	public void register(User user) {
        //Hash(encode) the password
	        String hashedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(hashedPassword);
	        this.userRepository.save(user);
    }
}
