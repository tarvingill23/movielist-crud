package com.fdmgroup.TarvinGillMovieList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.TarvinGillMovieList.dal.MovieListRepository;
import com.fdmgroup.TarvinGillMovieList.model.MovieList;
import com.fdmgroup.TarvinGillMovieList.service.MovieListService;

@SpringBootTest
public class MovieListServiceTest {

	@Mock
	MovieListRepository mlRepo;
	
	@Autowired
	MovieListService mlService;

	@BeforeEach
	public void setup() {
		mlService = new MovieListService(mlRepo);
	}

	@Test
	public void find_all_users() {
		List<MovieList> mvLists = new ArrayList<>();
		mvLists.add(new MovieList("Best movies of all time"));
		mvLists.add(new MovieList("My favourite films of the 2010s")); 

		when(mlRepo.findAll()).thenReturn(mvLists);

		List<MovieList> foundLists = new ArrayList<>();
		foundLists = mlService.findAll();

		assertEquals(foundLists, mvLists);
		verify(mlRepo, times(1)).findAll();

	}

	@Test
	public void get_list_by_list_id_throws_exception_if_list_does_not_exist() {
		int listId = 1;

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.findById(listId);
		});

		assertEquals("Movie list with ID: " + listId + " not found", exception.getMessage());

	}
	
	@Test
	public void add_new_movie_list() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mlService.createList(mvList1);
		verify(mlRepo, times(1)).save(mvList1);
	}
	
	
	@Test
	public void update_list_throws_exception_if_id_does_not_exist() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.update(mvList1);
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
	@Test
	public void delete_list_throws_exception_if_id_does_not_exist() {
		MovieList mvList1 = new MovieList("My favourite films of the 2010s");
		mvList1.setListId(1);

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			mlService.deleteById(mvList1.getListId());
		});

		assertEquals("Movie list with ID: " + mvList1.getListId() + " not found",exception.getMessage());
	}
	
	
	
}