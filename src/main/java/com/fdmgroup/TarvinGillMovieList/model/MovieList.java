package com.fdmgroup.TarvinGillMovieList.model;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class MovieList {
	
	@Id
	@GeneratedValue
	@Column(name = "list_id", nullable=false, unique=true)
	private int listId;
	@Column(nullable = false)
	private String title;
	@Immutable
	@Column(name = "date_created", nullable = false)
	private Timestamp dateCreated;
	@Column(name = "date_modified", nullable=false)
	private Timestamp dateModified;
	
	@ManyToMany
	@JoinTable(name = "MovieList_Movie", joinColumns = @JoinColumn(name = "list_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private List<Movie> movies;
	
	
	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	private User user;

	/**
	 * CONSTRUCTORS
	 */
	public MovieList() {
		super();
	}

	public MovieList(String title) {
		super();
		this.title = title;
		this.dateCreated = new Timestamp(System.currentTimeMillis());
		this.dateModified = new Timestamp(System.currentTimeMillis());	
	}
	
	/**
	 * GETTERS AND SETTERS
	 */

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * TO STRING METHOD
	 */
	
	@Override
	public String toString() {
		return "List [listId=" + listId + ", title=" + title + ", dateCreated=" + dateCreated + ", dateModified="
				+ dateModified + "]";
	}

}
