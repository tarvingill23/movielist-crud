package com.fdmgroup.TarvinGillMovieList.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.TarvinGillMovieList.model.Movie;
import com.fdmgroup.TarvinGillMovieList.service.MovieService;

@RestController
public class MovieController {
	private MovieService movieService;
	
	@Autowired 
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@GetMapping ("movies")
	public List<Movie> getAllMovies() {
		return this.movieService.getAllMovies();
	}
	
	@GetMapping ("movies/{movieId}")
	public Optional<Movie> getMovieById(@PathVariable int movieId) {
		return this.movieService.getMovieById(movieId);
	}
	
	@PostMapping ("movies")
	public void addMovie( @RequestBody Movie movie ) {
		this.movieService.addMovie(movie);
	}
	
	@PutMapping ("movies")
	public void updateMovie( @RequestBody Movie movie ) {
		this.movieService.updateMovie(movie);
	}
	
	@DeleteMapping ("movies/{movieId}")
	public void updateMovie( @PathVariable int movieId) {
		this.movieService.deleteMovie(movieId);
	}
	
}
