package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.TarvinGillMovieList.controller.MovieListController;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;


@SpringBootTest
class MovieListControllerTest {

	@Mock
	MovieListService mlService;
	
	@Autowired
	MovieListController mlController;
	

	@BeforeEach
	public void setup() {
		mlController = new MovieListController(mlService);
	}

	@Test
	public void getAllMovieLists_returns_2_lists() {
		List<MovieList> mvList1 = new ArrayList <>();
		mvList1.add(new MovieList("Best movies of all time"));
		mvList1.add(new MovieList("My favourite films of the 2010s"));
		when(mlService.findAll()).thenReturn(mvList1);
		
		List<MovieList> foundMvLists = new ArrayList <>();
		foundMvLists = mlController.getAllLists();
		assertEquals(foundMvLists, mvList1); 
		verify(mlService, times(1)).findAll();
	}
	
	@Test
	public void getAllUserById_returns_correct_user() {
		int listId = 1;
		Optional<MovieList> mvList1 = Optional.of(new MovieList("Best movies of all time"));
		when(mlService.findById(listId)).thenReturn(mvList1);
		
		Optional<MovieList> foundMvList = mlController.getMovieListById(listId);
		
		assertEquals(foundMvList, mvList1);
		verify(mlService, times(1)).findById(listId);
	}

}
