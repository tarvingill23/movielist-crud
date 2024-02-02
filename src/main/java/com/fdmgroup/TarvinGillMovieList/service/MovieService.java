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
		if (this.movieRepo.existsById(movieId)) {
			return this.movieRepo.findById(movieId);
		} else {
			throw new NotFoundException("Movie with ID: " + movieId + " not found");
		}
	}

	public void addMovie(Movie movie) {
		if (this.movieRepo.existsById(movie.getMovieId())) {
			throw new NotFoundException("Movie with ID: " + movie.getMovieId()
					+ " already exists, please update correctly using the PUT method");
		} else {
			this.movieRepo.save(movie);
		}
	}

	public void updateMovie(Movie movie) {
		if (this.movieRepo.existsById(movie.getMovieId())) {
			this.movieRepo.save(movie);

		} else {
			throw new NotFoundException("Movie with ID: " + movie.getMovieId() + " not found");
		}
	}

	public void deleteMovie(int movieId) {
		if (this.movieRepo.existsById(movieId)) {
			this.movieRepo.deleteById(movieId);
		} else {
			throw new NotFoundException("Movie with ID: " + movieId + " not found");
		}
	}
}
