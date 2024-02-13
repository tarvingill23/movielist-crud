package com.fdmgroup.TarvinGillMovieList.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.TarvinGillMovieList.model.Director;
import com.fdmgroup.TarvinGillMovieList.service.DirectorService;

@RestController
public class DirectorController {
	private DirectorService directorService;

	public DirectorController(DirectorService directorService) {
		super();
		this.directorService = directorService;
	}
	
	@GetMapping("directors")
	public List<Director> getAllDirectors() {
		return directorService.getAllDirectors();
	}
	
	@GetMapping("directors/{dirId}")
	public Optional<Director> getDirectorById(@PathVariable int dirId) {
		return directorService.getDirectorById(dirId);
	}
	
	@PostMapping("directors")
	public void addDirector(@RequestBody Director director) {
		directorService.addDirector(director);
	}
	
	@PutMapping("directors")
	public void updateDirector() {
		directorService.updateDirector();
	}
	
	@DeleteMapping ("directors/{dirId}") 
	public void deleteByDirID(@PathVariable int dirId) {
		directorService.deleteByDirId(dirId);
	}
}
