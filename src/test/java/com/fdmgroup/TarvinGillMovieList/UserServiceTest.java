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
	public void find_all_users() {
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
	public void findById_returns_correct_user() {
		
		Optional<User> user1 = Optional.of(new User("johndoe@gmail.com", "johndoe9812", "password123"));
		int userId = 1;
		when(userService.findById(userId)).thenReturn(user1);
		
		Optional<User> foundUser = userService.findById(userId);
		assertEquals(foundUser, user1);
		verify(userRepository, times(1)).findById(userId);
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
}
