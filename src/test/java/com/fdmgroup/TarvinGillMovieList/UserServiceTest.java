package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.model.User;
import com.fdmgroup.TarvinGillMovieList.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@BeforeEach
	public void setup() {
		userService = new UserService(userRepository);
	}

	@Test
	public void findAll_users_return_2_users() {
		List<User> users = new ArrayList<User>();
		users.add(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		users.add(new User("juliexiong17@gmail.com", "juliexiong17", "password12"));

		when(userRepository.findAll()).thenReturn(users); 

		List<User> foundUsers = new ArrayList<User>();
		foundUsers = userService.findAll();

		assertEquals(foundUsers, users);
		verify(userRepository, times(1)).findAll();

	}

	@Test
	public void findById() {
		Optional<User> user1 = Optional.of(new User("johndoe@gmail.com", "johndoe23", "password123"));
		int userId = 1;

		when(userRepository.existsById(userId)).thenReturn(true);

		when(userRepository.findById(userId)).thenReturn(user1);

		Optional<User> foundUser = userService.findById(userId);

		assertEquals(foundUser, user1);

		verify(userRepository, times(1)).findById(userId);
	}

	@Test
	public void findById_throws_exception_if_user_does_not_exist() {
		int userId = 1;
		// user does not exist in the database
		when(userRepository.existsById(userId)).thenReturn(false);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.findById(userId);
		});
		
		assertEquals("User with ID: " + userId + " not found", exception.getMessage());

	}

	@Test
	public void add_new_user() {
		User user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		userService.save(user1);
		verify(userRepository, times(1)).save(user1);
	}
	
	@Test
	public void add_new_user_throws_exception_if_user_exists() {
		User user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		user1.setUserId(1);
		
		when(userRepository.existsById(user1.getUserId())).thenReturn(true);
		
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("User with ID: " + user1.getUserId() + " exists, please update correctly using a PUT method", exception.getMessage());
	}
	@Test
	public void add_new_user_throws_exception_if_username_is_taken() {
		User user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		user1.setUserId(1);
		
		// Pretend username already exists in database
		when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);
		
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("This username has been taken, please type in another username", exception.getMessage());
	}
	@Test
	public void add_new_user_throws_exception_if_email_is_taken() {
		User user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		user1.setUserId(1);
		
		// Pretend username already exists in database
		when(userRepository.existsByEmail(user1.getEmail())).thenReturn(true);
		
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("User with email " + user1.getEmail() + " already exists, please login", exception.getMessage());
	}

	@Test
	public void update_user() {
		User originalUser = new User("johndoe@gmail.com", "johndoe9812", "password123");
		originalUser.setUserId(1);

		User updatedUser = new User("johndoe@gmail.com", "updatedTestUserName", "password123");
		updatedUser.setUserId(1);
		
		// Pretend that the original user exists in the database
		when(userRepository.existsById(originalUser.getUserId())).thenReturn(true);
		
		
		userService.update(updatedUser);
		verify(userRepository, times(1)).save(updatedUser);
		// Get the original user
		when(userRepository.findById(originalUser.getUserId())).thenReturn(Optional.of(updatedUser));
		
		Optional<User> foundUser = userService.findById(originalUser.getUserId());
		
		// Check the user has been updated
		assertEquals(foundUser, Optional.of(updatedUser));
		assertEquals("updatedTestUserName", updatedUser.getUsername());
	}

	@Test
	public void update_user_throws_exception_if_id_does_not_exist() {
		// user which is not persisted in the database
		User user1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		user1.setUserId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.update(user1);
		});

		assertEquals("User with ID: " + user1.getUserId() + " not found", exception.getMessage());
	}
	@Test
	public void update_user_throws_exception_if_username_already_exists() {
		// user which is not persisted in the database
		User user1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		user1.setUserId(1);
		
		// Pretend username already exists in database
		when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);
		
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.update(user1);
		});
		
		assertEquals("This username has been taken, please type in another username", exception.getMessage());
	}

	@Test
	public void delete_user_by_id() {

		Optional<User> user1 = Optional.of(new User("johndoe@gmail.com", "johndoe23", "password123"));
		int userId = 1;
	
		// Assume that the user exists in the database
		when(userRepository.existsById(userId)).thenReturn(true);
		
		userService.deleteById(userId);
		
		// Find the user by id
		user1 = userService.findById(userId);
		assertEquals(false, user1.isPresent());
		
		verify(userRepository, times(1)).deleteById(userId);
	}

	@Test
	public void delete_user_throws_exception_if_id_does_not_exist() {
		User user1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		user1.setUserId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.deleteById(user1.getUserId());
		});

		assertEquals("User with ID: " + user1.getUserId() + " not found", exception.getMessage());
	}
}
