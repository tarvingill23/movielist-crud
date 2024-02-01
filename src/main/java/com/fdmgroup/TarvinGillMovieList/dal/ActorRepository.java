package com.fdmgroup.TarvinGillMovieList.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.TarvinGillMovieList.model.Actor;

import jakarta.transaction.Transactional;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	@Transactional
    @Modifying
	@Query("DELETE FROM Actor a WHERE a.actorId= :actorId")
	void deleteByActorId(@Param("actorId") int actorId);
}