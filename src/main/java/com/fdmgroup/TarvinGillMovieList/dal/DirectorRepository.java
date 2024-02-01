package com.fdmgroup.TarvinGillMovieList.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.TarvinGillMovieList.model.Director;

import jakarta.transaction.Transactional;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
	@Transactional
    @Modifying
	@Query("DELETE FROM Director d WHERE d.dirId = :dirId")
	void deleteByDirId(@Param("dirId") int dirId);
}
