package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
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
		if (personnelRepo.existsById(personnelId)) {
			return this.personnelRepo.findById(personnelId);
		} else {
			throw new NotFoundException("Personnel with ID: " + personnelId + " not found");
		}
	}

	public void addPersonnel(Personnel person) {
		if (personnelRepo.existsById(person.getPersonnelId())) {
			throw new NotFoundException("Person with ID: " + person.getPersonnelId()
					+ " exists, please update correctly using a PUT method");
		}
		this.personnelRepo.save(person);
	}

	public void updatePersonnel(Personnel person) {
		if (personnelRepo.existsById(person.getPersonnelId())) {

			this.personnelRepo.save(person);
		} else {
			throw new NotFoundException("Personnel with ID: " + person.getPersonnelId() + " not found");
		}
	}

	public void deletePersonnelById(int personnelId) {
		if (personnelRepo.existsById(personnelId)) {
			this.personnelRepo.deleteById(personnelId);
		} else {
			throw new NotFoundException("Personnel with ID: " + personnelId + " not found");
		}
	}
}
