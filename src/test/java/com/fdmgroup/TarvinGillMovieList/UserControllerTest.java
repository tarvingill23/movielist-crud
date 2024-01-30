package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.fdmgroup.TarvinGillMovieList.controller.UserController;
import com.fdmgroup.TarvinGillMovieList.service.UserService;
import com.fdmgroup.TarvinGillMovieList.model.User;

@SpringBootTest
class UserControllerTest {
	
	@Mock 
	UserService userService;
	
	UserController userController;
	
	@Autowired
	public UserControllerTest(UserController userController) {
		super();
		this.userController = userController;
	}

	@BeforeEach
	public void setup() {
		userController = new UserController(userService);
	}
	
	@Test
	public void getAllUsers_returns_2_users() {
	
		List<User> users = new ArrayList<User>();
		users.add(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		users.add(new User("juliexiong17@gmail.com", "juliexiong17", "password12"));
		when(userController.getAllUsers()).thenReturn(users);
		
		List<User> foundUsers = userController.getAllUsers();
		
		assertEquals(foundUsers, users);
		assertEquals(2, foundUsers.size());
		verify(userService, times(1)).findAll();
	}
	
	@Test
	public void getAllUserById_returns_correct_user() {
		int userId = 1;
		Optional<User> user1 = Optional.of(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		when(userController.getUserById(userId)).thenReturn(user1);
		
		Optional<User> foundUser = userController.getUserById(userId);
		
		assertEquals(foundUser, user1);
		verify(userService, times(1)).findById(userId);
	}
	
	@Test
	public void add_new_user_to_database() {
		User user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		userController.addUser(user1);
		verify(userService, times(1)).save(user1);
	}
	
	@Test
	public void update_user_in_database() {
		User updatedUser1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		userController.updateUser(updatedUser1);
		verify(userService, times(1)).update(updatedUser1);
	}
	
	@Test
	public void delete_user_in_database() {
		int userId = 1;
		userController.deleteUser(userId);
		verify(userService, times(1)).deleteById(userId);
	}
	
}
