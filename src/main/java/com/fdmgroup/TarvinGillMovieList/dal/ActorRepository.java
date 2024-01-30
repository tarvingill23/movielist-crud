package com.fdmgroup.TarvinGillMovieList.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.TarvinGillMovieList.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

}