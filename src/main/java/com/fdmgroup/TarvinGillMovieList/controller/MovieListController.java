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

import com.fdmgroup.TarvinGillMovieList.model.MovieList;

import com.fdmgroup.TarvinGillMovieList.service.MovieListService;

@RestController
public class MovieListController {
	private MovieListService mlService;

	@Autowired
	public MovieListController(MovieListService mlService) {
		super();
		this.mlService = mlService; 
	}

	@GetMapping("movielists")
	public List<MovieList> getAllLists() {
		return mlService.findAll();
	}

	@GetMapping("movielists/{listId}")
	public Optional<MovieList> getMovieListById(@PathVariable int listId) {
		return mlService.findById(listId);
	}

	@PostMapping("movielists-post")
	public void addMovieList(@RequestBody MovieList mvList) {
		mlService.createList(mvList);
	}

	@PutMapping("movielists")
	public void updateMovieList(@RequestBody MovieList mvList) {
		mlService.update(mvList);
	}

	@DeleteMapping("movielists/{listId}")
	public void deleteMovieList(@PathVariable int listId) {
		mlService.deleteById(listId);
	}

}