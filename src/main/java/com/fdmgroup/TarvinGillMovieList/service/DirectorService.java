package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.DirectorRepository;
import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.model.Director;

@Service
public class DirectorService {
	private DirectorRepository directorRepo;

	public DirectorService(DirectorRepository directorRepo) {
		super();
		this.directorRepo = directorRepo;
	}

	public List<Director> getAllDirectors() {
		return this.directorRepo.findAll();
	}

	public Optional<Director> getDirectorById(int dirId) {
		if (directorRepo.existsById(dirId)) {
			return this.directorRepo.findById(dirId);
		} else {
			throw new RuntimeException("Director with ID: " + dirId + " not found");
		}
	}

	public void addDirector(Director director) {
		if (directorRepo.existsById(director.getDirId())) {
			throw new RuntimeException(
					"Director with ID: " + director.getDirId() + " exists, please update correctly using a PUT method");
		} else {
			this.directorRepo.save(director);
		}
	}

	// cannot update actors as they only reference a personnelId at the moment
	public void updateDirector(Director director) {
		throw new RuntimeException("Cannot update directors");
	}

	public void deleteDirectorById(int dirId) {
		if (directorRepo.existsById(dirId)) {
			this.directorRepo.deleteById(dirId);

		} else {
			throw new RuntimeException("Director with ID: " + dirId + " not found");
		}
	}
}
