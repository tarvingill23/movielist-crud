package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.TarvinGillMovieList.dal.ActorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.DirectorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.dal.MovieRepository;
import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.ForbiddenException;
import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.model.Director;
import com.fdmgroup.TarvinGillMovieList.model.Movie;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.Personnel;
import com.fdmgroup.TarvinGillMovieList.model.User;
import com.fdmgroup.TarvinGillMovieList.service.ActorService;
import com.fdmgroup.TarvinGillMovieList.service.DirectorService;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;
import com.fdmgroup.TarvinGillMovieList.service.MovieService;
import com.fdmgroup.TarvinGillMovieList.service.PersonnelService;
import com.fdmgroup.TarvinGillMovieList.service.UserService;

@SpringBootTest
public class ServiceTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private MovieListRepository mlRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private MovieRepository movieRepo;
	
	@Mock
	private PersonnelRepository personnelRepo;
	
	@Mock
	private ActorRepository actorRepo;
	
	@Mock
	private DirectorRepository directorRepo;
	

	private MovieListService mlService;

	private UserService userService;
	
	private MovieService movieService;

	private PersonnelService personnelService;
	
	private ActorService actorService;
	
	private DirectorService directorService;


	Optional<User> opUser1;
	Optional<User> opUser2;
	
	Optional<Movie> opMovie1;
	Optional<Movie> opMovie2;
	
	Optional<MovieList> opMvList1;
	Optional<MovieList> opMvList2;

	
	Optional<Personnel> opPerson1;
	Optional<Personnel> opPerson2;
	Optional<Personnel> opPerson3;
	
	Optional<Actor> opActor1;
	Optional<Actor> opActor2;
	
	Optional<Director> opDir1;
	Optional<Director> opDir2;
	
	Movie movie1;
	Movie movie2;
	
	MovieList mvList1;
	MovieList mvList2;
	
	Director dir1;
	Director dir2;
	
	Actor actor1;
	Actor actor2;
	
	Personnel person1;
	Personnel person2;
	Personnel person3;
	
	User user1;
	User user2;
	
	
	@BeforeEach
	public void setup() {
		userService = new UserService(userRepository, passwordEncoder);
		mlService = new MovieListService(mlRepo, userRepo);
		movieService = new MovieService(movieRepo);
		personnelService = new PersonnelService(personnelRepo);
		actorService = new ActorService(actorRepo, personnelRepo);
		directorService = new DirectorService(directorRepo, personnelRepo);
		
		opUser1 = Optional.of(new User("johndoe@gmail.com", "johndoe9812", "password1234"));
		user1 = opUser1.get();
		user1.setUserId(1);
		
		opUser2 = Optional.of(new User("janedoe@gmail.com", "janedoe9823", "password4231"));
		user2 = opUser2.get();
		user2.setUserId(2);
		
		opMvList1 = Optional.of(new MovieList("Best movies of all time"));
		opMvList2 = Optional.of(new MovieList("The best films of the 2010s")); 
		
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
		movie2 = opMovie2.get();
		
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
	
	/**
	 * **************************************
	 * User Service Tests
	 * **************************************
	 */

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
		users.add(user1);
		users.add(user2);

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

		User foundUser = userService.findById(userId).get();
		foundUser.setUserId(userId);

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
		assertEquals("User with email " + user1.getEmail() + " already exists, please login with your associated username", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	@Test
	public void add_new_user_throws_exception_if_username_is_taken() {
		// Pretend username already exists in database
		when(userRepository.existsByUsername(user1.getUsername())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user1);
		});
		assertEquals( user1.getUsername() + " has been taken, please type in another username", exception.getMessage());
		verify(userRepository, times(0)).save(user1); // check that user repository method was not called
	}

	/**
	 * UPDATE USERS
	 */

	@Test
	public void update_user() {
		int id = user1.getUserId();

		when(userRepository.existsById(id)).thenReturn(true);
		when(userRepository.findById(id)).thenReturn(opUser1);
		
		
		userService.update(user1);
		verify(userRepository, times(1)).save(user1);
	}

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
	public void find_all_movielists() {
		List<MovieList> mvLists = new ArrayList<>();
		mvLists.add(mvList1);
		mvLists.add(mvList2); 

		when(mlRepo.findAll()).thenReturn(mvLists);

		List<MovieList> foundLists = new ArrayList<>();
		foundLists = mlService.findAll();

		assertEquals(foundLists, mvLists);
		verify(mlRepo, times(1)).findAll();

	}
	
	@Test
	public void get_list_by_id() {
		int listId = mvList1.getListId();
		when(mlRepo.existsById(listId)).thenReturn(true);
		when(mlRepo.findById(listId)).thenReturn(opMvList1);
		
		MovieList foundMvList = mlService.findById(listId).get();
		assertEquals(foundMvList, mvList1);
		verify(mlRepo, times(1)).findById(listId);
	}

	@Test
	public void get_list_by_list_id_throws_exception_if_list_does_not_exist() {
		int listId = mvList1.getListId();

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.findById(listId);
		});

		assertEquals("Movie list with ID: " + listId + " not found", exception.getMessage());
		
	}
	
	@Test
	public void find_partial_matches_for_movielists() {
		
		List<MovieList> mvLists = new ArrayList<>();
		mvLists.add(mvList1);
		mvLists.add(mvList2);
		
		String query = "Best";
		
		when(mlRepo.findPartialMatch(query)).thenReturn(mvLists);
		
		List<MovieList> foundLists = mlService.getPartialMatches(query);
		assertEquals(mvLists, foundLists);
		verify(mlRepo, times(1)).findPartialMatch(query);
	}
	
	@Test
	public void add_new_movielist() {
		when(userRepo.findByUsername(user1.getUsername())).thenReturn(opUser1);
		mvList1.setUser(user1);
		mlService.createList(mvList1);
		verify(mlRepo, times(1)).save(mvList1);
	}
	
	@Test
	public void add_new_movielist_throws_exception() {
		
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.createList(mvList1);
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " exists",exception.getMessage());
	}
	
	@Test
	public void update_list() {
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);
		
		mlService.update(mvList1);
		
		verify(mlRepo, times(1)).save(mvList1);
	}
	
	
	@Test
	public void update_list_throws_exception_if_id_does_not_exist() {
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.update(mvList1);
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
	@Test
	public void delete_list() {
		mvList1.setListId(1);
		
		when(mlRepo.existsById(mvList1.getListId())).thenReturn(true);
		
		mlService.deleteById(mvList1.getListId());
		
		verify(mlRepo, times(1)).deleteById(mvList1.getListId());
	}
	
	
	@Test
	public void delete_list_throws_exception_if_id_does_not_exist() {
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.deleteById(mvList1.getListId());
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
	/**
	 * **************************************
	 * Movie Service Tests
	 * **************************************
	 */
	
	@Test
	public void get_all_movies_test() {
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);

		when(movieRepo.findAll()).thenReturn(movies);

		List<Movie> foundMovies = new ArrayList<>();
		foundMovies = movieService.getAllMovies();

		assertEquals(foundMovies, movies);
		verify(movieRepo, times(1)).findAll();
	}
	
	@Test
	public void get_movie_by_id() {
		int id = movie1.getMovieId();
		when(movieRepo.findById(id)).thenReturn(opMovie1);
		when(movieRepo.existsById(id)).thenReturn(true);
		Movie foundMovie = movieService.getMovieById(id).get();
		assertEquals(movie1, foundMovie);
	}
	
	@Test
	public void find_partial_matches_for_movies() {
		Movie movie2 = new Movie("Iron Man 2", Date.valueOf("2010-05-07"), 125,
				"With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.",
				3, "Action",
				"https://upload.wikimedia.org/wikipedia/en/e/ed/Iron_Man_2_poster.jpg");
		List<Movie> movies = new ArrayList<>();
		movies.add(movie1);
		movies.add(movie2);
		
		String query = "Iron";
		
		when(movieRepo.findPartialMatch(query)).thenReturn(movies);
		
		List<Movie> foundMovies = movieService.getPartialMatches(query);
		assertEquals(movies, foundMovies);
		verify(movieRepo, times(1)).findPartialMatch(query);
	}
	
	@Test
	public void add_new_movie() {
		int id = movie1.getMovieId();
		when(movieRepo.existsById(id)).thenReturn(false);
		movieService.addMovie(movie1);
		verify(movieRepo, times(1)).save(movie1);
	}
	@Test
	public void update_movie() {
		int id = movie1.getMovieId();

		when(movieRepo.existsById(id)).thenReturn(true);
		
		movieService.updateMovie(movie1);
		verify(movieRepo, times(1)).save(movie1);
	}
	@Test
	public void delete_movie_by_id() {
		int id = movie1.getMovieId();
		when(movieRepo.existsById(id)).thenReturn(true);
		movieService.deleteMovie(id);
		verify(movieRepo, times(1)).deleteById(id);
	}
	
	/**	 * **************************************
	 * Personnel Service Tests
	 * **************************************
	 */
	
	@Test
	public void get_all_personnel() {
		List<Personnel> allPersonnel = new ArrayList<>();
		allPersonnel.add(person1);
		allPersonnel.add(person2);
		
		when(personnelRepo.findAll()).thenReturn(allPersonnel);
		List<Personnel> foundPersonnel = personnelService.getAllPersonnel();
		
		assertEquals(foundPersonnel, allPersonnel);
	}
	
	@Test
	public void get_personnel_by_id() {
		int id = person1.getPersonnelId();
		
		when(personnelRepo.existsById(id)).thenReturn(true);
		when(personnelRepo.findById(id)).thenReturn(opPerson1);
		
		Personnel foundPerson1 = personnelService.getPersonnelById(id).get();
		
		assertEquals(person1, foundPerson1);
	}
	
	@Test
	public void add_new_personnel() {
		personnelService.addPersonnel(person1);
		personnelService.addPersonnel(person2);
		
		verify(personnelRepo, times(1)).save(person1);
		verify(personnelRepo, times(1)).save(person2);
	}
	
	@Test
	public void delete_personnel_by_id() {
		int id = person1.getPersonnelId();
		
		when(personnelRepo.existsById(id)).thenReturn(true);
		personnelService.deletePersonnelById(id);
		
		verify(personnelRepo,times(1)).deleteById(id);

	}
	
	@Test
	public void update_personnel() {
		int id = person1.getPersonnelId();
		
		when(personnelRepo.existsById(id)).thenReturn(true);
		
		personnelService.updatePersonnel(person1);
		
		verify(personnelRepo,times(1)).save(person1);
		
	}
	
	/**	 * **************************************
	 * Actor Service Tests
	 * **************************************
	 */
	
	@Test
	public void get_all_actors() {
		List<Actor> allActors = new ArrayList<>();
		allActors.add(actor1);
		allActors.add(actor2);
		
		when(actorRepo.findAll()).thenReturn(allActors);
		List<Actor> foundActors = actorService.getAllActors();
		
		assertEquals(foundActors, allActors);
	}
	
	@Test
	public void get_actor_by_id() {
		int id = actor1.getActorId();
		
		
		when(actorRepo.existsById(id)).thenReturn(true);
		when(actorRepo.findById(id)).thenReturn(opActor1);
		
		Actor foundActor1 = actorService.getActorById(id).get();
		
		assertEquals(actor1, foundActor1);
	}
	
	@Test
	public void add_new_actor() {
		int actorId = actor1.getActorId();
		int personnelId = actor1.getPersonnel().getPersonnelId();
		
		when(actorRepo.existsById(actorId)).thenReturn(false);
		when(personnelRepo.existsById(personnelId)).thenReturn(true);
		actorService.addActor(actor1);
		verify(actorRepo, times(1)).save(actor1);

	}
	
	@Test
	public void update_actor_throws_forbidden_exception() {
		Throwable exception1 = assertThrows(ForbiddenException.class, () -> {
			actorService.updateActor();
		});
		
		assertEquals("Cannot update actors", exception1.getMessage());
	}
	
	@Test
	public void delete_actor_by_id() {
		int id = actor1.getActorId();
		
		when(actorRepo.existsById(id)).thenReturn(true);
		
		actorService.deleteByActorId(id);
		verify(actorRepo,times(1)).deleteByActorId(id);
	}
	
	/**	 * **************************************
	 * Director Service Tests
	 * **************************************
	 */
	
	@Test
	public void get_all_directors() {
		List<Director> directors = new ArrayList<>();
		directors.add(dir1);
		directors.add(dir2);
		
		when(directorRepo.findAll()).thenReturn(directors);
		
		List<Director> foundDirectors = new ArrayList<>();
		foundDirectors = directorService.getAllDirectors();
		assertEquals(foundDirectors, directors);
		
	}
	
	@Test
	public void get_director_by_id () {
		int id = dir1.getDirId();
		when(directorRepo.findById(id)).thenReturn(opDir1);
		when(directorRepo.existsById(id)).thenReturn(true);
		Director foundDir1 = directorService.getDirectorById(id).get();
		assertEquals(dir1, foundDir1);
	}
	
	@Test
	public void add_new_director() {
		int dirId = dir1.getDirId();
		int personnelId = dir1.getPersonnel().getPersonnelId();
		
		when(actorRepo.existsById(dirId)).thenReturn(false);
		when(personnelRepo.existsById(personnelId)).thenReturn(true);
		directorService.addDirector(dir1);
		verify(directorRepo, times(1)).save(dir1);

	}
	
	
	@Test
	public void update_directors() {
		Throwable exception1 = assertThrows(ForbiddenException.class, () -> {
			directorService.updateDirector();
		});
		
		assertEquals("Cannot update directors", exception1.getMessage());
	}
	
	@Test
	public void delete_director_by_id () {
		int dirId = dir1.getDirId();
		when(directorRepo.existsById(dirId)).thenReturn(true);
		
		directorService.deleteByDirId(dirId);
		verify(directorRepo, times(1)).deleteByDirId(dirId);
	}

}
