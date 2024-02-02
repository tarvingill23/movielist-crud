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

import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.service.ActorService;

@RestController
public class ActorController {
	private ActorService actorService;
	
	@Autowired
	public ActorController(ActorService actorService) {
		super();
		this.actorService = actorService;
	}
	
	@GetMapping("actors")
	public List<Actor> getAllActors() {
		return actorService.getAllActors();
	}
	
	@GetMapping("actors/{actorId}")
	public Optional<Actor> getActorById(@PathVariable int actorId) {
		return actorService.getActorById(actorId);
	}
	
	@PostMapping("actors")
	public void addActor(@RequestBody Actor actor) {
		actorService.addActor(actor);
	}
	
	@PutMapping("actors")
	public void updateActor() {
		actorService.updateActor();
	}
	
	@DeleteMapping ("actors/{actorId}") 
	public void deleteActorById(@PathVariable int actorId) {
		actorService.deleteByActorId(actorId);
	}
}
