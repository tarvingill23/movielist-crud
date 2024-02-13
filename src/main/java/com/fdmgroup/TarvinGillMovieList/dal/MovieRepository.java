package com.fdmgroup.TarvinGillMovieList.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.TarvinGillMovieList.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT('%',:searchValue,'%')")
	List<Movie> findPartialMatch(@Param("searchValue") String q);
	
}
