package com.fdmgroup.TarvinGillMovieList.model;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
@NamedQuery(name = "findByUser", query = "SELECT mv FROM MovieList mv JOIN mv.user u WHERE u.id = :userId")
@Entity
public class MovieList {
	
	@Id
	@GeneratedValue
	@Column(name = "list_id", nullable=false, unique=true)
	private int listId;
	@Column(nullable = false)
	private String title;
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;
	@Column(name = "date_modified", nullable=false)
	private Date dateModified;
	
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
		this.dateCreated = new Date(System.currentTimeMillis());
		this.dateModified = new Date(System.currentTimeMillis());	
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
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
