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

import com.fdmgroup.TarvinGillMovieList.model.Personnel;
import com.fdmgroup.TarvinGillMovieList.service.PersonnelService;


@RestController
public class PersonnelController {


private PersonnelService personnelService;
	
  	@Autowired
	public PersonnelController(PersonnelService personnelService) {
		super();
		this.personnelService = personnelService;
	}

	@GetMapping("personnel")
	public List<Personnel> getAllPersonnel() {
		return personnelService.getAllPersonnel();
	}
	
	@GetMapping("personnel/{personnelId}")
	public Optional<Personnel> getPersonnelById(@PathVariable int personnelId) {
		return personnelService.getPersonnelById(personnelId);
	}
	
	@PostMapping("personnel")
	public void addPersonnel(@RequestBody Personnel person) {
		personnelService.addPersonnel(person);
	}
	
	@PutMapping("personnel")
	public void updatePersonnel(@RequestBody Personnel person) {
		personnelService.updatePersonnel(person);
	}

	@DeleteMapping ("personnel/{personnelId}") 
	public void deletePersonnel(@PathVariable int personnelId) {
		personnelService.deletePersonnelById(personnelId);
	}
}
