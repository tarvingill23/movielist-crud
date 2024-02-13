package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.DirectorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.ForbiddenException;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.Director;

@Service
public class DirectorService {
	private DirectorRepository directorRepo;
	private PersonnelRepository personnelRepo;

	@Autowired
	public DirectorService(DirectorRepository directorRepo, PersonnelRepository personnelRepo) {
		super();
		this.directorRepo = directorRepo;
		this.personnelRepo = personnelRepo;
	}

	public List<Director> getAllDirectors() {
		return this.directorRepo.findAll();
	}

	public Optional<Director> getDirectorById(int dirId) {
		checkDirectorExists(dirId);
		return this.directorRepo.findById(dirId);
	}

	public void addDirector(Director director) {
		if (directorRepo.existsById(director.getDirId())) {
			throw new NotFoundException("Director with ID: " + director.getDirId() + " exists");
		}
		
		if (!personnelRepo.existsById(director.getPersonnel().getPersonnelId())) {
			throw new NotFoundException(
					"Director with personnel ID: " + director.getPersonnel().getPersonnelId() + " does not exist");
		}
			
		this.directorRepo.save(director);
		
	}

	// cannot update actors as they only reference a personnelId at the moment
	public void updateDirector() {
		throw new ForbiddenException("Cannot update directors");
	}

	public void deleteByDirId(int dirId) {
		checkDirectorExists(dirId);
		directorRepo.deleteByDirId(dirId);
	}

	public void checkDirectorExists(int dirId) {
		if (!directorRepo.existsById(dirId)) {
			throw new NotFoundException("Director with ID: " + dirId + " not found");
		}
	}
}
