package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.MovieRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.Movie;


@Service
public class MovieService {
	private MovieRepository movieRepo;

	@Autowired
	public MovieService(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	public List<Movie> getAllMovies() {
		return this.movieRepo.findAll();
	}

	public Optional<Movie> getMovieById(int movieId) {
		checkMovieExists(movieId);
		return this.movieRepo.findById(movieId);
	}
	
	public List<Movie> getPartialMatches(String q) {
		return movieRepo.findPartialMatch(q);	
	}

	public void addMovie(Movie movie) {
		if (this.movieRepo.existsById(movie.getMovieId())) {
			throw new NotFoundException("Movie with ID: " + movie.getMovieId() + " already exists");
		}
		this.movieRepo.save(movie);
	}

	public void updateMovie(Movie movie) {
		checkMovieExists(movie.getMovieId());
		this.movieRepo.save(movie);
	}

	public void deleteMovie(int movieId) {
		checkMovieExists(movieId);
		this.movieRepo.deleteById(movieId);
	}

	public void checkMovieExists(int movieId) {
		if (!movieRepo.existsById(movieId)) {
			throw new NotFoundException("Movie with ID: " + movieId + " not found");
		}
	}
}
