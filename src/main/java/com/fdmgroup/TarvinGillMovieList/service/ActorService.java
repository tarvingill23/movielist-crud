package com.fdmgroup.TarvinGillMovieList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.TarvinGillMovieList.dal.ActorRepository;
import com.fdmgroup.TarvinGillMovieList.dal.PersonnelRepository;
import com.fdmgroup.TarvinGillMovieList.exceptions.ConflictException;
import com.fdmgroup.TarvinGillMovieList.exceptions.ForbiddenException;
import com.fdmgroup.TarvinGillMovieList.exceptions.NotFoundException;
import com.fdmgroup.TarvinGillMovieList.model.Actor;
import com.fdmgroup.TarvinGillMovieList.model.Personnel;

@Service
public class ActorService {
	private ActorRepository actorRepo;
	private PersonnelRepository personnelRepo;

	@Autowired
	public ActorService(ActorRepository actorRepo, PersonnelRepository personnelRepo) {
		super();
		this.actorRepo = actorRepo;
		this.personnelRepo = personnelRepo;
	}

	public List<Actor> getAllActors() {
		return this.actorRepo.findAll();
	}

	public Optional<Actor> getActorById(int actorId) {
		checkActorExists(actorId);
		return this.actorRepo.findById(actorId);
	}

	public void addActor(Actor actor) {
		int actorId = actor.getActorId();
		if (actorRepo.existsById(actorId)) {
			throw new NotFoundException("Actor with ID: " + actorId + " exists");
		}
		if (!personnelRepo.existsById(actor.getPersonnel().getPersonnelId())) {
			throw new NotFoundException(
					"Actor with personnel ID: " + actor.getPersonnel().getPersonnelId() + " does not exist");
		}
		this.actorRepo.save(actor);
	}

	// cannot update actors as they only reference a personnelId at the moment
	public void updateActor() {
		throw new ForbiddenException("Cannot update actors");
	}

	public void deleteByActorId(int actorId) {
		checkActorExists(actorId);
		actorRepo.deleteByActorId(actorId);
	}

	public void checkActorExists(int actorId) {
		if (!actorRepo.existsById(actorId)) {
			throw new NotFoundException("Actor with ID: " + actorId + " not found");
		}
	}

}
