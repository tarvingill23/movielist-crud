package com.fdmgroup.TarvinGillMovieList.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.dal.UserRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.model.User;

@Service
public class MovieListService {
	private MovieListRepository mlRepo;
	private UserRepository userRepo;

	@Autowired
	public MovieListService(MovieListRepository mlRepo, UserRepository userRepo) {
		super();
		this.mlRepo = mlRepo;
		this.userRepo = userRepo;
	}

	public List<MovieList> findAll() {
		return this.mlRepo.findAll();
	}

	public Optional<MovieList> findById(int listId) {
		checkMovieListExists(listId);
		return this.mlRepo.findById(listId);
	}
	
	public void createList(MovieList mvList) {
		if (mlRepo.existsById(mvList.getListId())) {
			throw new NotFoundException("Movie list with ID: " + mvList.getListId()
					+ " exists");
		}
		
		// details the backend should handle instead of user are correct when list is created regardless of input
		Optional<User> opUser = this.userRepo.findByUsername(mvList.getUser().getUsername());
		User foundUser = opUser.get();
	
		mvList.setUser(foundUser);
		mvList.setDateCreated(new Timestamp(System.currentTimeMillis()));
		mvList.setDateModified(new Timestamp(System.currentTimeMillis()));
		
		this.mlRepo.save(mvList);
	}

	public void update(MovieList mvList) {
		checkMovieListExists(mvList.getListId());
		mvList.setDateModified(new Timestamp(System.currentTimeMillis()));
		this.mlRepo.save(mvList);
	}

	public void deleteById(int listId) {
		checkMovieListExists(listId);
		this.mlRepo.deleteById(listId);

	}

	public void checkMovieListExists(int listId) {
		if (!mlRepo.existsById(listId)) {
			throw new NotFoundException("Movie list with ID: " + listId + " not found");
		}
	}

}
