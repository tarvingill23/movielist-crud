package com.fdmgroup.TarvinGillMovieList.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.TarvinGillMovieList.model.Movie;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;


@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Integer>{
	@Query("SELECT m FROM MovieList m WHERE m.title LIKE CONCAT('%',:searchValue,'%')")
	List<MovieList> findPartialMatch(@Param("searchValue") String q);
}