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

import com.fdmgroup.TarvinGillMovieList.controller.MovieListController;
import com.fdmgroup.TarvinGillMovieList.controller.UserController;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;
import com.fdmgroup.TarvinGillMovieList.service.UserService;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.User;

@SpringBootTest
class ControllerTests {
	
	@Mock 
	UserService userService;
	
	@Autowired
	UserController userController;
	
	@Mock
	MovieListService mlService;
	
	@Autowired
	MovieListController mlController;
	
	User user1;
	Optional<User> opUser1;

	@BeforeEach
	public void setup() { 
		userController = new UserController(userService);
		user1 = new User("johndoe@gmail.com", "johndoe23", "password123");
		opUser1 = Optional.of(new User("johndoe@gmail.com", "johndoe23", "password123"));
		
		mlController = new MovieListController(mlService);
	}
	
	/**
	 * **************************************
	 * User Controller Tests
	 * **************************************
	 */
	
	@Test
	public void getAllUsers_returns_2_users() {
	
		List<User> users = new ArrayList<User>();
		users.add(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		users.add(new User("juliexiong17@gmail.com", "juliexiong17", "password12"));
		when(userService.findAll()).thenReturn(users);
		
		List<User> foundUsers = userController.getAllUsers();
		
		assertEquals(foundUsers, users);
		assertEquals(2, foundUsers.size());
		verify(userService, times(1)).findAll();
	}
	
	@Test
	public void getAllUserById_returns_correct_user() {
		int userId = 1;
		Optional<User> user1 = Optional.of(new User("tarvingill23@gmail,com", "tarvingill23", "password123"));
		when(userService.findById(userId)).thenReturn(user1);
		
		Optional<User> foundUser = userController.getUserById(userId);
		
		assertEquals(foundUser, user1);
		verify(userService, times(1)).findById(userId);
	}
	
	@Test
	public void add_new_user_to_database() {
		userController.addUser(user1);
		verify(userService, times(1)).save(user1);
	}
	
	@Test
	public void update_user_in_database() {
		userController.updateUser(user1);
		verify(userService, times(1)).update(user1);
	}
	
	@Test
	public void delete_user_in_database() {
		int userId = 1;
		userController.deleteUser(userId);
		verify(userService, times(1)).deleteById(userId);
	}
	
	
	/**
	 * **************************************
	 * Movielist Controller Tests
	 * **************************************
	 */
	

	@Test
	public void getAllMovieLists_returns_2_lists() {
		List<MovieList> mvList1 = new ArrayList <>();
		mvList1.add(new MovieList("Best movies of all time"));
		mvList1.add(new MovieList("My favourite films of the 2010s"));
		when(mlService.findAll()).thenReturn(mvList1);
		
		List<MovieList> foundMvLists = new ArrayList <>();
		foundMvLists = mlController.getAllLists();
		assertEquals(foundMvLists, mvList1); 
		verify(mlService, times(1)).findAll();
	}
	
	@Test
	public void getAllMovielistsById_returns_correct_lists() {
		int listId = 1;
		Optional<MovieList> mvList1 = Optional.of(new MovieList("Best movies of all time"));
		when(mlService.findById(listId)).thenReturn(mvList1);
		
		Optional<MovieList> foundMvList = mlController.getMovieListById(listId);
		
		assertEquals(foundMvList, mvList1);
		verify(mlService, times(1)).findById(listId);
	}

	
}
