package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.TarvinGillMovieList.controller.ActorController;
import com.fdmgroup.TarvinGillMovieList.controller.DirectorController;
import com.fdmgroup.TarvinGillMovieList.controller.MovieController;
import com.fdmgroup.TarvinGillMovieList.controller.MovieListController;
import com.fdmgroup.TarvinGillMovieList.controller.PersonnelController;
import com.fdmgroup.TarvinGillMovieList.controller.UserController;
import com.fdmgroup.TarvinGillMovieList.service.ActorService;
import com.fdmgroup.TarvinGillMovieList.service.DirectorService;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;
import com.fdmgroup.TarvinGillMovieList.service.MovieService;
import com.fdmgroup.TarvinGillMovieList.service.PersonnelService;
import com.fdmgroup.TarvinGillMovieList.service.UserService;
import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.model.Director;
import com.fdmgroup.TarvinGillMovieList.model.Movie;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.Personnel;
import com.fdmgroup.TarvinGillMovieList.model.User;

@SpringBootTest
class ControllerTests {
	
	/**
	 * Mock services
	 */
	@Mock 
	UserService userService;
	
	@Mock
	MovieListService mlService;
	
	@Mock
	MovieService movieService;
	
	@Mock
	PersonnelService personnelService;
	
	@Mock
	ActorService actorService;
	
	@Mock
	DirectorService directorService;
	
	/**
	 * Controllers
	 */
	

	UserController userController;
	
	MovieListController mlController;
	
	MovieController movieController;
	
	PersonnelController personnelController;
	
	ActorController actorController;
	
	DirectorController directorController;
	
	
	
	//Optionals
	Optional<User> opUser1;
	
	Optional<MovieList> opMvList1;
	Optional<MovieList> opMvList2;
	
	Optional<Movie> opMovie1;
	Optional<Movie> opMovie2;
	
	Optional<Personnel> opPerson1;
	Optional<Personnel> opPerson2;
	Optional<Personnel> opPerson3;
	
	Optional<Actor> opActor1;
	Optional<Actor> opActor2;
	
	Optional<Director> opDir1;
	Optional<Director> opDir2;
	
	
	User user1;
	
	MovieList mvList1;
	MovieList mvList2;
	
	Movie movie1;
	Movie movie2;
	
	Personnel person1;
	Personnel person2;
	Personnel person3;
	
	Actor actor1;
	Actor actor2;
	
	Director dir1;
	Director dir2;
	


	@BeforeEach
	public void setup() { 
		
		/**
		 * Controller instantiation
		 */
		userController = new UserController(userService);
		mlController = new MovieListController(mlService);
		movieController = new MovieController(movieService);
		personnelController = new PersonnelController(personnelService);
		actorController = new ActorController(actorService);
		directorController = new DirectorController(directorService);
		
		/**
		 * Model initialisation
		 */
		
		opUser1 = Optional.of(new User("johndoe@gmail.com", "johndoe23", "password123"));
		user1 = opUser1.get();
		user1.setUserId(1);
		
		opMvList1 = Optional.of(new MovieList("Best movies of all time"));
		opMvList2 = Optional.of(new MovieList("Best films of the 2010s"));
		
		mvList1 = opMvList1.get();
		mvList1.setListId(1);
		
		mvList2 = opMvList2.get();
		mvList2.setListId(2);
		
		
		opMovie1 = Optional.of(new Movie("Iron Man", Date.valueOf("2008-05-02"), 126,
				"After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
				4, "Action",
				"https://upload.wikimedia.org/wikipedia/en/0/02/Iron_Man_%282008_film%29_poster.jpg"));
		
		opMovie2 = Optional.of(new Movie("No Country For Old Men", Date.valueOf("2007-11-09"), 122,
				"While out hunting, Llewelyn finds the grisly aftermath of a drug deal. Though he knows better, he cannot resist the cash left behind and takes it. The hunter becomes the hunted when a merciless killer named Chigurh picks up his trail.",
				5, "Thriller",
				"https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p170313_p_v13_ad.jpg"));
		
		movie1 = opMovie1.get();
		movie1.setMovieId(1);
		
		movie2 = opMovie2.get();
		movie2.setMovieId(2);
		
		opPerson1 = Optional.of(new Personnel("Jon", "Favraeu"));	
		person1 = opPerson1.get();
		person1.setPersonnelId(1);
		
		opPerson2 = Optional.of(new Personnel("Robert", "Downey", "Jr"));
		person2 = opPerson2.get();
		person2.setPersonnelId(2);
		
		opPerson3 = Optional.of(new Personnel("Ben", "Affleck")); // both director and actor
		person3 = opPerson3.get();
		person3.setPersonnelId(3);
		
		opActor1 = Optional.of(new Actor(person2));
		actor1 = opActor1.get();
		actor1.setActorId(1);
		
		opActor2 = Optional.of(new Actor(person3));
		actor2 = opActor2.get();
		actor2.setActorId(2);
		
		opDir1 = Optional.of(new Director(person1));
		dir1 = opDir1.get();
		dir1.setDirId(1);
		
		opDir2 = Optional.of(new Director(person3));
		dir2 = opDir2.get();
		dir2.setDirId(2);
		
		
	}
	 @Test
	    void contextLoads() {}
	
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
		int userId = user1.getUserId();
				
		when(userService.findById(userId)).thenReturn(opUser1);
		
		User foundUser = userController.getUserById(userId).get();
		foundUser.setUserId(userId);
		
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
		int userId = user1.getUserId();
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
		List<MovieList> lists = new ArrayList <>();
		lists.add(mvList1);
		lists.add(mvList2);
		when(mlService.findAll()).thenReturn(lists);
		
		List<MovieList> foundLists = new ArrayList <>();
		foundLists = mlController.getAllLists();
		assertEquals(foundLists, lists); 
		verify(mlService, times(1)).findAll();
	}
	
	@Test
	public void getAllMovielistsById_returns_correct_lists() {
		int listId = mvList1.getListId();
		when(mlService.findById(listId)).thenReturn(opMvList1);
		
		MovieList foundList = mlController.getMovieListById(listId).get();		
		assertEquals(foundList, mvList1);
		verify(mlService, times(1)).findById(listId);
	}
	
	@Test
	public void get_all_partial_matches_for_movielists() {
		List<MovieList> mvLists = new ArrayList<>();
		mvLists.add(mvList1);
		mvLists.add(mvList2);
		
		String query = "Best";
		
		when(mlService.getPartialMatches(query)).thenReturn(mvLists);
		
		List<MovieList> foundMvLists = mlController.searchByTitle(query);
		assertEquals(mvLists, foundMvLists);
		assertEquals(2,mvLists.size());
		verify(mlService, times(1)).getPartialMatches(query);
	}
	
	@Test
	public void add_new_movielist_to_database() {
		mlController.addMovieList(mvList1);
		verify(mlService, times(1)).createList(mvList1);
	}
	
	@Test
	public void update_movielist_in_database() {
		mlController.updateMovieList(mvList1);
		verify(mlService, times(1)).update(mvList1);
	}
	
	@Test
	public void delete_movielist_in_database() {
		int listId = mvList1.getListId();
		mlController.deleteMovieList(listId);
		verify(mlService, times(1)).deleteById(listId);
	}
	
	
	/**
	 * **************************************
	 * Movie Controller Tests
	 * **************************************
	 */
	 
	@Test
	public void get_all_movies_returns_2_movies_from_database() {
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);
		
		when(movieService.getAllMovies()).thenReturn(movies);
		
		List<Movie> foundMovies = movieController.getAllMovies();
		assertEquals(movies, foundMovies);
		verify(movieService, times(1)).getAllMovies();
		
	}
	@Test
	public void get_movie_by_id_from_database() {
		int id = movie1.getMovieId();
		when(movieService.getMovieById(id)).thenReturn(opMovie1);
		
		Movie foundMovie = movieController.getMovieById(id).get();
		
		assertEquals(movie1, foundMovie);
		verify(movieService, times(1)).getMovieById(id);
	}
	
	@Test
	public void get_all_partial_matches_for_movies() {
		Movie movie2 = new Movie("Iron Man 2", Date.valueOf("2010-05-07"), 125,
				"With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.",
				3, "Action",
				"https://upload.wikimedia.org/wikipedia/en/e/ed/Iron_Man_2_poster.jpg");
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);
		
		String query = "Iron";
		
		when(movieService.getPartialMatches(query)).thenReturn(movies);
		
		List<Movie> foundMovies = movieController.searchByName(query);
		assertEquals(movies, foundMovies);
		verify(movieService, times(1)).getPartialMatches(query);
	}
	
	@Test
	public void add_new_movie_to_database() {
		movieController.addMovie(movie1);
		verify(movieService, times(1)).addMovie(movie1);;
	}
	
	@Test
	public void update_movie_in_database() {
		movieController.updateMovie(movie1);
		verify(movieService, times(1)).updateMovie(movie1);;
	}
	
	@Test
	public void delete_movie_by_id_from_database() {
		int id = movie2.getMovieId();
		movieController.deleteMovie(id);
		verify(movieService, times(1)).deleteMovie(id);
	}
		
	
	/**
	 * **************************************
	 * Personnel Controller Tests
	 * **************************************
	 */
	
	@Test
	public void get_all_personnel_returns_2_people_from_database() {
		List<Personnel> personnel = new ArrayList<>();
		personnel.add(person1);
		personnel.add(person2);
		
		when(personnelService.getAllPersonnel()).thenReturn(personnel);
		
		List<Personnel> foundPersonnel = personnelController.getAllPersonnel();
		assertEquals(personnel, foundPersonnel);
		verify(personnelService, times(1)).getAllPersonnel();
		
	}
	@Test
	public void get_personnel_by_id_from_database() {
		int id = person1.getPersonnelId();
		when(personnelService.getPersonnelById(id)).thenReturn(opPerson1);
		
		Personnel foundPerson = personnelController.getPersonnelById(id).get();
		
		assertEquals(person1, foundPerson);
		verify(personnelService, times(1)).getPersonnelById(id);
	}
	
	@Test
	public void add_new_person_to_database() {
		personnelController.addPersonnel(person1);
		verify(personnelService, times(1)).addPersonnel(person1);;;
	}
	
	@Test
	public void update_personnel_in_database() {
		personnelController.updatePersonnel(person1);
		verify(personnelService, times(1)).updatePersonnel(person1);
	}
	
	@Test
	public void delete_personnel_by_id_from_database() {
		int id = person1.getPersonnelId();
		personnelController.deletePersonnel(id);
		verify(personnelService, times(1)).deletePersonnelById(id);
	}
	
	/**
	 * **************************************
	 * Actor Controller Tests
	 * **************************************
	 */
	@Test
	public void get_all_actors_returns_2_actors_from_database() {
		List<Actor> actors = new ArrayList<>();
		actors.add(actor1);
		actors.add(actor2);
		
		when(actorService.getAllActors()).thenReturn(actors);
		
		List<Actor> foundActors = actorController.getAllActors();
		assertEquals(actors, foundActors);
		verify(actorService, times(1)).getAllActors();
		
	}
	
	@Test
	public void get_actor_by_id_from_database() {
		int id = actor1.getActorId();
		when(actorService.getActorById(id)).thenReturn(opActor1);
		
		Actor foundActor = actorController.getActorById(id).get();
		
		assertEquals(actor1, foundActor);
		verify(actorService, times(1)).getActorById(id);
	}
	
	@Test
	public void add_new_actor_to_database() {
		actorController.addActor(actor1);
		verify(actorService, times(1)).addActor(actor1);	
	}
	
	@Test
	public void update_actor_in_database() {
		actorController.updateActor();
		verify(actorService, times(1)).updateActor();
	}
	
	@Test
	public void delete_actor_by_id_from_database() {
		int id = actor1.getActorId();
		actorController.deleteActorById(id);
		verify(actorService, times(1)).deleteByActorId(id);
	}
	
	/**
	 * **************************************
	 * Director Controller Tests
	 * **************************************
	 */
	@Test
	public void get_all_directors_returns_2_directors_from_database() {
		List<Director> directors = new ArrayList<>();
		directors.add(dir1);
		directors .add(dir2);
		
		when(directorService.getAllDirectors()).thenReturn(directors);
		
		List<Director> foundDirectors = directorController.getAllDirectors();
		assertEquals(directors, foundDirectors);
		verify(directorService, times(1)).getAllDirectors();
		
	}
	
	
	@Test
	public void get_director_by_id_from_database() {
		int id = dir1.getDirId();
		when(directorService.getDirectorById(id)).thenReturn(opDir1);
		
		Director foundDirector = directorController.getDirectorById(id).get();
		
		assertEquals(dir1, foundDirector);
		verify(directorService, times(1)).getDirectorById(id);
	}
	
	@Test
	public void add_new_director_to_database() {
		directorController.addDirector(dir1);
		verify(directorService, times(1)).addDirector(dir1);	
	}
	
	@Test
	public void update_director_in_database() {
		directorController.updateDirector();
		verify(directorService, times(1)).updateDirector();
	}
	
	@Test
	public void delete_director_by_id_from_database() {
		int id = dir1.getDirId();
		directorController.deleteByDirID(id);
		verify(directorService, times(1)).deleteByDirId(id);
	}
}
