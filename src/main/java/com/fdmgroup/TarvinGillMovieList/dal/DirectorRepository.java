package com.fdmgroup.TarvinGillMovieList.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.TarvinGillMovieList.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
