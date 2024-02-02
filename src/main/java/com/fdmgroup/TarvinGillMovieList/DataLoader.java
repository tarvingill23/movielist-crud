package com.fdmgroup.TarvinGillMovieList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.ActorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.DirectorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.dal.MovieRepository;
import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.model.Director;
import com.fdmgroup.TarvinGillMovieList.model.Movie;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.Personnel;
import com.fdmgroup.TarvinGillMovieList.model.User;

@Service
public class DataLoader implements ApplicationRunner{
	
	private UserRepository userRepo;
	private MovieListRepository mlRepo;
	private MovieRepository movieRepo;
	private PersonnelRepository personnelRepo;
	private ActorRepository actorRepo;
	private DirectorRepository directorRepo;
	
	
	@Autowired
	public DataLoader (UserRepository userRepo, MovieListRepository mlRepo, MovieRepository movieRepo, PersonnelRepository personnelRepo, ActorRepository actorRepo, DirectorRepository directorRepo) {
		super();
		this.userRepo = userRepo;
		this.mlRepo = mlRepo;
		this.movieRepo = movieRepo;
		this.personnelRepo = personnelRepo;
		this.actorRepo = actorRepo;
		this.directorRepo = directorRepo;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user1 = new User("tarvingill23@gmail.com", "tarvingill23", "password123");
		User user2 = new User("juliexiong@gmail.com", "juliexiong17", "password1234");

		MovieList list1 = new MovieList("Best movies of all time");
		MovieList list2 = new MovieList("My favourite films of the 2010s");
		MovieList list3 = new MovieList("Top 5 films of the last decade");

		Movie movie1 = new Movie("Iron Man", Date.valueOf("2008-05-02"), 126,
				"After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
				4, "Action",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-Are6tUt-IHheN0fmqzWZkb2xu-t2YLSETSir59FmOi47DKBslRN-0vAiVLFFt9EtjJI&usqp=CAU");

		Movie movie2 = new Movie("No Country For Old Men", Date.valueOf("2007-11-09"), 122,
				"While out hunting, Llewelyn finds the grisly aftermath of a drug deal. Though he knows better, he cannot resist the cash left behind and takes it. The hunter becomes the hunted when a merciless killer named Chigurh picks up his trail.",
				5, "Thriller",
				"https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p170313_p_v13_ad.jpg");

		Movie movie3 = new Movie("Oppenheimer", Date.valueOf("2023-07-21"), 181,
				"The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
				5, "Biography", "https://upload.wikimedia.org/wikipedia/en/4/4a/Oppenheimer_%28film%29.jpg");

		Movie movie4 = new Movie("Interstellar", Date.valueOf("2014-11-05"), 169,
				"When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
				5, "Sci-Fi", "https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg");

		Movie movie5 = new Movie("Argo", Date.valueOf("2012-10-12"), 120,
				"Acting under the cover of a Hollywood producer scouting a location for a science fiction film, a CIA agent launches a dangerous operation to rescue six Americans in Tehran during the U.S. hostage crisis in Iran in 1979.",
				4, "Drama", "https://upload.wikimedia.org/wikipedia/en/e/e1/Argo2012Poster.jpg");

		Movie movie6 = new Movie("Moonlight", Date.valueOf("2016-10-21"), 111,
				"A young African-American man grapples with his identity and sexuality while experiencing the everyday struggles of childhood, adolescence, and burgeoning adulthood.",
				5, "Drama", "https://upload.wikimedia.org/wikipedia/en/8/84/Moonlight_%282016_film%29.png");

		Movie movie7 = new Movie("Hell or Highwater", Date.valueOf("2016-08-12"), 102,
				"Toby is a divorced father who's trying to make a better life. His brother is an ex-con with a short temper and a loose trigger finger. Together, they plan a series of heists against the bank that's about to foreclose on their family ranch.",
				4, "Crime", "https://upload.wikimedia.org/wikipedia/en/8/8f/Hell_or_High_Water_film_poster.png");

		Movie movie8 = new Movie("Million Dollar Baby", Date.valueOf("2004-12-15"), 132,
				"Frankie, an ill-tempered old coach, reluctantly agrees to train aspiring boxer Maggie. Impressed with her determination and talent, he helps her become the best and the two soon form a close bond.",
				4, "Drama", "https://upload.wikimedia.org/wikipedia/en/d/d3/Million_Dollar_Baby_poster.jpg");

		Movie movie9 = new Movie("Good Will Hunting", Date.valueOf("1997-12-05"), 126,
				"Will Hunting, a janitor at M.I.T., has a gift for mathematics, but needs help from a psychologist to find direction in his life.",
				5, "Drama", "https://upload.wikimedia.org/wikipedia/en/5/52/Good_Will_Hunting.png");

		Movie movie10 = new Movie("The Departed", Date.valueOf("2006-10-06"), 151,
				"An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.",
				4, "Crime", "https://upload.wikimedia.org/wikipedia/en/5/50/Departed234.jpg");

		// All personnel
		Personnel person1 = new Personnel("Jon", "Favraeu");
		Personnel person2 = new Personnel("Robert", "Downey", "Jr");
		Personnel person3 = new Personnel("Terrence", "Howard");

		Personnel person4 = new Personnel("Ethan", "Coen");
		Personnel person5 = new Personnel("Joel", "Coen");
		Personnel person6 = new Personnel("Tommy", "Lee", "Jones");
		Personnel person7 = new Personnel("Javier", "Bardem");

		Personnel person8 = new Personnel("Christopher", "Nolan");
		Personnel person9 = new Personnel("Cillian", "Murphy");
		Personnel person10 = new Personnel("Matt", "Damon");

		Personnel person11 = new Personnel("Matthew", "McConaughey");
		Personnel person12 = new Personnel("Anne", "Hathaway");

		Personnel person13 = new Personnel("Ben", "Affleck");
		Personnel person14 = new Personnel("George", "Clooney");

		Personnel person15 = new Personnel("Barry", "Jenkins");
		Personnel person16 = new Personnel("Trevante", "Rhodes");
		Personnel person17 = new Personnel("Mahershala", "Ali");

		Personnel person18 = new Personnel("David", "Mackenzie");
		Personnel person19 = new Personnel("Chris", "Pine");
		Personnel person20 = new Personnel("Jeff", "Bridges");

		Personnel person21 = new Personnel("Clint", "Eastwood");
		Personnel person22 = new Personnel("Hillary", "Swank");
		Personnel person23 = new Personnel("Morgan", "Freeman");

		Personnel person24 = new Personnel("Gus", "Van ", "Sant");

		Personnel person25 = new Personnel("Martin", "Scorsese");
		Personnel person26 = new Personnel("Leonardo", "Di", "Caprio");

		// Directors
		Director dir1 = new Director(person1);

		Director dir2 = new Director(person4);
		Director dir3 = new Director(person5);

		Director dir4 = new Director(person8);

		Director dir5 = new Director(person13);

		Director dir6 = new Director(person15);

		Director dir7 = new Director(person18);

		Director dir8 = new Director(person21);

		Director dir9 = new Director(person24);

		Director dir10 = new Director(person25);

		// Actors
		Actor actor1 = new Actor(person1);
		Actor actor2 = new Actor(person2);
		Actor actor3 = new Actor(person3);

		Actor actor4 = new Actor(person6);
		Actor actor5 = new Actor(person7);

		Actor actor6 = new Actor(person9);
		Actor actor7 = new Actor(person10);

		Actor actor8 = new Actor(person11);
		Actor actor9 = new Actor(person12);

		Actor actor10 = new Actor(person13);
		Actor actor11 = new Actor(person14);

		Actor actor12 = new Actor(person16);
		Actor actor13 = new Actor(person17);

		Actor actor14 = new Actor(person19);
		Actor actor15 = new Actor(person20);

		Actor actor16 = new Actor(person21);
		Actor actor17 = new Actor(person22);
		Actor actor18 = new Actor(person23);

		Actor actor19 = new Actor(person26);

		// Add data to collections

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		List<Movie> movies = new ArrayList<Movie>();
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		movies.add(movie4);
		movies.add(movie5);
		movies.add(movie6);
		movies.add(movie7);
		movies.add(movie8);
		movies.add(movie9);
		movies.add(movie10);

		List<MovieList> movieLists = new ArrayList<MovieList>();
		movieLists.add(list1);
		movieLists.add(list2);
		movieLists.add(list3);

		List<Personnel> personnel = new ArrayList<Personnel>();
		personnel.add(person1);
		personnel.add(person2);
		personnel.add(person3);
		personnel.add(person4);
		personnel.add(person5);
		personnel.add(person6);
		personnel.add(person7);
		personnel.add(person8);
		personnel.add(person9);
		personnel.add(person10);
		personnel.add(person11);
		personnel.add(person12);
		personnel.add(person13);
		personnel.add(person14);
		personnel.add(person15);
		personnel.add(person16);
		personnel.add(person17);
		personnel.add(person18);
		personnel.add(person19);
		personnel.add(person20);
		personnel.add(person21);
		personnel.add(person22);
		personnel.add(person23);
		personnel.add(person24);
		personnel.add(person25);
		personnel.add(person26);

		List<Director> directors = new ArrayList<Director>();
		directors.add(dir1);
		directors.add(dir2);
		directors.add(dir3);
		directors.add(dir4);
		directors.add(dir5);
		directors.add(dir6);
		directors.add(dir7);
		directors.add(dir8);
		directors.add(dir9);
		directors.add(dir10);

		List<Actor> actors = new ArrayList<Actor>();
		actors.add(actor1);
		actors.add(actor2);
		actors.add(actor3);
		actors.add(actor4);
		actors.add(actor5);
		actors.add(actor6);
		actors.add(actor7);
		actors.add(actor8);
		actors.add(actor9);
		actors.add(actor10);
		actors.add(actor11);
		actors.add(actor12);
		actors.add(actor13);
		actors.add(actor14);
		actors.add(actor15);
		actors.add(actor16);
		actors.add(actor17);
		actors.add(actor18);
		actors.add(actor19);
	
		// Set relationships

		list1.setUser(user1);
		list2.setUser(user1);
		list3.setUser(user2);

		list1.setMovies(Arrays.asList(movie1, movie5, movie6, movie9, movie10));
		list2.setMovies(Arrays.asList(movie3, movie4, movie5, movie6, movie7));
		list3.setMovies(Arrays.asList(movie2, movie3, movie4, movie6, movie9));

		movie1.setDirectors(Arrays.asList(dir1));
		movie1.setActors(Arrays.asList(actor2, actor3));

		movie2.setDirectors(Arrays.asList(dir2, dir3)); // multiple directors
		movie2.setActors(Arrays.asList(actor4, actor5));

		movie3.setDirectors(Arrays.asList(dir4));
		movie3.setActors(Arrays.asList(actor6, actor7));

		movie4.setDirectors(Arrays.asList(dir4));
		movie4.setActors(Arrays.asList(actor8, actor9));

		movie5.setDirectors(Arrays.asList(dir5));
		movie5.setActors(Arrays.asList(actor10, actor11));

		movie6.setDirectors(Arrays.asList(dir6));
		movie6.setActors(Arrays.asList(actor12, actor13));

		movie7.setDirectors(Arrays.asList(dir7));
		movie7.setActors(Arrays.asList(actor14, actor15));

		movie8.setDirectors(Arrays.asList(dir8));
		movie8.setActors(Arrays.asList(actor16, actor18, actor19));

		movie9.setDirectors(Arrays.asList(dir9));
		movie9.setActors(Arrays.asList(actor7, actor10));

		movie10.setDirectors(Arrays.asList(dir10));
		movie10.setActors(Arrays.asList(actor7, actor19));
		
		personnelRepo.saveAll(personnel);
		actorRepo.saveAll(actors);
		directorRepo.saveAll(directors);
		userRepo.saveAll(users);
		movieRepo.saveAll(movies);
		mlRepo.saveAll(movieLists);
	}
	
}
