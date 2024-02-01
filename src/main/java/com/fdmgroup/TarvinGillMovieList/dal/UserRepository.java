package com.fdmgroup.TarvinGillMovieList.dal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.TarvinGillMovieList.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}