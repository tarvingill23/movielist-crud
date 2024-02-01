package com.fdmgroup.TarvinGillMovieList.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;

@Service
public class MovieListService {
	private MovieListRepository mvRepo;

	@Autowired
	public MovieListService(MovieListRepository mvRepo) {
		super();
		this.mvRepo = mvRepo;
	}

	public List<MovieList> findAll() {
		return this.mvRepo.findAll();
	}

	public Optional<MovieList> findById(int listId) {
		if (mvRepo.existsById(listId)) {
			return this.mvRepo.findById(listId);
		} else {
			throw new NotFoundException("Movie list with ID: " + listId + " not found");
		}
	}

	public void createList(MovieList mvList) {
		if (mvRepo.existsById(mvList.getListId())) {
			throw new NotFoundException("Movie list with ID: " + mvList.getListId()
					+ " exists, please update correctly using a PUT method");
		}

		// ensures that the dates are correct when list is created regardless of input
		mvList.setDateCreated(new Date(System.currentTimeMillis()));
		mvList.setDateModified(new Date(System.currentTimeMillis()));
		this.mvRepo.save(mvList);
	}

	public void update(MovieList mvList) {
		if (mvRepo.existsById(mvList.getListId())) {
			this.mvRepo.save(mvList);
		} else {
			throw new NotFoundException("Movie list with ID: " + mvList.getListId() + " not found");
		}
	}

	public void deleteById(int listId) {
		if (mvRepo.existsById(listId)) {
			this.mvRepo.deleteById(listId);
		} else {
			throw new NotFoundException("Movie list with ID: " + listId + " not found");
		}
	}

}
