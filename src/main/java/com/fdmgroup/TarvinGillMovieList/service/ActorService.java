package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.ActorRepository;
import com.fdmgroup.TarvinGillMovieList.model.Actor;

@Service
public class ActorService {
	private ActorRepository actorRepo;
	
	@Autowired
	public ActorService(ActorRepository actorRepo) {
		super();
		this.actorRepo = actorRepo;
	}

	public List<Actor> getAllActors() {
		return this.actorRepo.findAll();
	}

	public Optional<Actor> getActorById(int actorId) {
		if (actorRepo.existsById(actorId)) {
			return this.actorRepo.findById(actorId);
		} else {
			throw new RuntimeException("Actor with ID: " + actorId + " not found");
		}
	}

	public void addActor(Actor actor) {
		if (actorRepo.existsById(actor.getActorId())) {
			throw new RuntimeException(
					"Actor with ID: " + actor.getActorId() + " exists, please update correctly using a PUT method");
		}
		this.actorRepo.save(actor);
	}
	
	// cannot update actors as they only reference a personnelId at the moment
	public void updateActor(Actor actor) {
			throw new RuntimeException("Cannot update actors");
	}
	
//	public void deleteActorById(int actorId) {
//		if (actorRepo.existsById(actorId)) {
//			this.actorRepo.deleteById(actorId);
//			
//		} else {
//			throw new RuntimeException("Actor with ID: " + actorId + " not found");
//		}
//	}
	
	public void deleteByActorId(int actorId) {
		if (actorRepo.existsById(actorId)) {
			actorRepo.deleteByActorId(actorId);
		} else {
			throw new RuntimeException("Director with ID: " + actorId + " not found");
		}
	}
	
}
