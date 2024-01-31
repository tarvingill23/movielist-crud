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

	UserService userService;

	@Autowired
	public UserServiceTest(UserService userService) {
		super();
		this.userService = userService;
	}

	@BeforeEach
	public void setup() {
		userService = new UserService(userRepository);
	}

	@Test
	public void get_all_users() {
		List<User> users = new ArrayList<User>();
		users.add(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		users.add(new User("juliexiong17@gmail.com", "juliexiong17", "password12"));

		when(userService.findAll()).thenReturn(users);

		List<User> foundUsers = new ArrayList<User>();
		foundUsers = userService.findAll();

		assertEquals(foundUsers, users);
		verify(userRepository, times(1)).findAll();

	}
	
	@Test
	public void get_user_by_id_throws_exception_if_user_does_not_exist() {
		int userId = 1;
		
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
	public void update_user_throws_exception_if_id_does_not_exist() {
		User user1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		user1.setUserId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.update(user1);
		});

		assertEquals("No user exists with ID: " + user1.getUserId(), exception.getMessage());
	}
	
	@Test
	public void delete_user_throws_exception_if_id_does_not_exist() {
		User user1 = new User("johndoe@gmail.com", "johndoe9812", "password123");
		user1.setUserId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.deleteById(user1.getUserId());
		});

		assertEquals("No user exists with ID: " + user1.getUserId(), exception.getMessage());
	}
}
