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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.User;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;
import com.fdmgroup.TarvinGillMovieList.service.UserService;

@SpringBootTest
public class ServiceTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	MovieListRepository mlRepo;
	
	@Autowired
	MovieListService mlService;

	@Autowired
	UserService userService;

	User user1;
	User user2;

	Optional<User> opUser1;
	
	
	/**
	 * **************************************
	 * User Service Tests
	 * **************************************
	 */
	
	@BeforeEach
	public void setup() {
		userService = new UserService(userRepository, passwordEncoder);
		mlService = new MovieListService(mlRepo);
		

		user1 = new User("johndoe@gmail.com", "johndoe9812", "password1234");
		user1.setUserId(1);

		user2 = new User("janedoe@gmail.com", "janedoe9823", "password4231");
		user2.setUserId(2);

		opUser1 = Optional.of(new User("johndoe@gmail.com", "johndoe9812", "password1234"));
		
	}

	@Test
	public void test_check_if_user_exists_returns_exception() {
		int userId = 1;
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.checkUserExists(1);
		});
		assertEquals("User with ID: " + userId + " not found", exception.getMessage());
	}

	/**
	 * GET USERS
	 */

	@Test
	public void findAll_users_returns_2_users() {
		List<User> users = new ArrayList<User>();
		users.add(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		users.add(new User("juliexiong17@gmail.com", "juliexiong17", "password12"));

		when(userRepository.findAll()).thenReturn(users);

		List<User> foundUsers = new ArrayList<User>();
		foundUsers = userService.findAll();

		assertEquals(foundUsers, users);
		assertEquals(2, foundUsers.size());

		verify(userRepository, times(1)).findAll();

	}

	@Test
	public void findById() {
		int userId = user1.getUserId();

		// pretend user exists in database
		when(userRepository.existsById(userId)).thenReturn(true);

		// // returns optional user
		when(userRepository.findById(userId)).thenReturn(opUser1);

		Optional<User> foundOpUser = userService.findById(userId);
		User foundUser = foundOpUser.get();
		foundUser.setUserId(userId);

		assertEquals(foundOpUser, opUser1); // check optional users match
		assertEquals(foundUser.getUserId(), user1.getUserId());

		verify(userRepository, times(1)).findById(userId);
	}

	@Test
	public void findById_throws_exception_if_user_does_not_exist() {
		int userId = user1.getUserId();
		// user does not exist in the database
		when(userRepository.existsById(userId)).thenReturn(false);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.findById(userId);
		});

		assertEquals("User with ID: " + userId + " not found", exception.getMessage());

		verify(userRepository, times(0)).findById(userId); // check that user repository method was not called
	}

	/*
	 * ADD USERS
	 */

	@Test
	public void add_new_user() {
		userService.save(user1);
		verify(userRepository, times(1)).save(user1);
	}

	@Test
	public void add_new_user_throws_exception_if_user_exists() {
		// Pretend userId already exists in database
		when(userRepository.existsById(user1.getUserId())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("User with ID: " + user1.getUserId() + " exists", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	@Test
	public void add_new_user_throws_exception_if_email_already_exists() {
		// Pretend email already exists in database
		when(userRepository.existsByEmail(user1.getEmail())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("User with email " + user1.getEmail() + " already exists, please login", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	@Test
	public void add_new_user_throws_exception_if_username_is_taken() {
		// Pretend username already exists in database
		when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals("This username has been taken, please type in another username", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	/**
	 * UPDATE USERS
	 */

//	@Test
//	public void update_user() {
//		
//		User updatedUser1 = new User("johndoe@gmail.com", "updatedUsername", "password1234");
//		updatedUser1.setUserId(1);
//		when(userRepository.existsById(updatedUser1.getUserId())).thenReturn(true);
//		userService.update(updatedUser1);
//
//		verify(userRepository, times(1)).save(updatedUser1);
//	}

	@Test
	public void update_user_throws_exception_if_id_does_not_exist() {
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.update(user1);
		});

		assertEquals("User with ID: " + user1.getUserId() + " not found", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	@Test
	public void update_user_throws_exceptions_if_username_or_email_already_exists() {
		// Pretend username already exists in database
		when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);
		when(userRepository.existsByEmail(user1.getEmail())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.updateUsername(user1, user2);
		});
		Throwable exception2 = assertThrows(RuntimeException.class, () -> {
			userService.updateEmail(user1, user2);
		});

		assertEquals("This username has been taken, please type in another username", exception.getMessage());
		assertEquals("Updating user email is not permitted", exception2.getMessage());

		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	/**
	 * DELETE USERS
	 */

	@Test
	public void delete_user_by_id() {
		int userId = 1;
		when(userRepository.existsById(userId)).thenReturn(true);

		userService.deleteById(userId);

		verify(userRepository, times(1)).deleteById(userId);
	}

	@Test
	public void delete_user_throws_exception_if_id_does_not_exist() {
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.deleteById(user1.getUserId());
		});

		assertEquals("User with ID: " + user1.getUserId() + " not found", exception.getMessage());
	}
	
	/**
	 * **************************************
	 * Movielist Service Tests
	 * **************************************
	 */
	
	@Test
	public void find_all_users() {
		List<MovieList> mvLists = new ArrayList<>();
		mvLists.add(new MovieList("Best movies of all time"));
		mvLists.add(new MovieList("My favourite films of the 2010s")); 

		when(mlRepo.findAll()).thenReturn(mvLists);

		List<MovieList> foundLists = new ArrayList<>();
		foundLists = mlService.findAll();

		assertEquals(foundLists, mvLists);
		verify(mlRepo, times(1)).findAll();

	}

	@Test
	public void get_list_by_list_id_throws_exception_if_list_does_not_exist() {
		int listId = 1;

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.findById(listId);
		});

		assertEquals("Movie list with ID: " + listId + " not found", exception.getMessage());

	}
	
	@Test
	public void add_new_movie_list() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mlService.createList(mvList1);
		verify(mlRepo, times(1)).save(mvList1);
	}
	@Test
	public void add_new_movie_list_throws_exception() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.createList(mvList1);
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " exists",exception.getMessage());
	}
	
	@Test
	public void update_list() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);
		
		mlService.update(mvList1);
		
		verify(mlRepo, times(1)).save(mvList1);
	}
	
	
	@Test
	public void update_list_throws_exception_if_id_does_not_exist() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.update(mvList1);
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
	@Test
	public void delete_list() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);
		
		mlService.deleteById(mvList1.getListId());
		
		verify(mlRepo, times(1)).deleteById(mvList1.getListId());
	}
	
	
	@Test
	public void delete_list_throws_exception_if_id_does_not_exist() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.deleteById(mvList1.getListId());
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
}
