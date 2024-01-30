package com.fdmgroup.TarvinGillMovieList.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.TarvinGillMovieList.model.MovieList;


@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Integer>{
	
}