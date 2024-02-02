package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.Personnel;

@Service
public class PersonnelService {
	private PersonnelRepository personnelRepo;

	@Autowired
	public PersonnelService(PersonnelRepository personnelRepo) {
		super();
		this.personnelRepo = personnelRepo;
	}

	public List<Personnel> getAllPersonnel() {
		return this.personnelRepo.findAll();
	}

	public Optional<Personnel> getPersonnelById(int personnelId) {
		checkPersonnelExists(personnelId);
		return this.personnelRepo.findById(personnelId);
	}

	public void addPersonnel(Personnel person) {
		if (personnelRepo.existsById(person.getPersonnelId())) {
			throw new NotFoundException("Person with ID: " + person.getPersonnelId() + " exists");
		}
		this.personnelRepo.save(person);
	}

	public void updatePersonnel(Personnel person) {
		checkPersonnelExists(person.getPersonnelId());
		this.personnelRepo.save(person);
	}

	public void deletePersonnelById(int personnelId) {
		checkPersonnelExists(personnelId);
		this.personnelRepo.deleteById(personnelId);
	}

	public void checkPersonnelExists(int personnelId) {
		if (!personnelRepo.existsById(personnelId)) {
			throw new NotFoundException("Personnel with ID: " + personnelId + " not found");
		}
	}
}
