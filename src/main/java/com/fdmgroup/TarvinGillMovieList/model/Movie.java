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

@Entity
public class Movie {
	
	@Id
	@GeneratedValue
	@Column(name = "movie_id", nullable=false, unique=true)
	private int movieId;
	@Column(nullable = false)
	private String title;
	@Column(name = "release_date", nullable=false)
	private Date releaseDate;
	@Column(nullable = false)
	private int runtime;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private int rating;
	@Column(nullable = false)
	private String genre;
	private String posterImage; // will have placeholder image for those that dont have a provided image
	
	@ManyToMany(mappedBy= "movies")
	private List<MovieList> movieList;
	
	@ManyToMany
	@JoinTable(name = "Movie_Director", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
	private List<Director> directors;
	
	@ManyToMany
	@JoinTable(name = "Movie_Actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private List<Actor> actors;
	
	/**
	 * CONSTRUCTORS
	 */
	
	public Movie() {
		super();
	}

	public Movie(String title, Date releaseDate, int runtime, String description, int rating, String genre, String posterImage) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.runtime = runtime;
		this.description = description;
		this.rating = rating;
		this.genre = genre;
		this.posterImage = posterImage;
	}
	
	/**
	 * GETTERS AND SETTERS
	 */

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	

	public List<MovieList> getMovieList() {
		return movieList;
	}
	
	public void setMovieList(List<MovieList> movieList) {
		this.movieList = movieList;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
	
	public List<Director> getDirectors() {
		return directors;
	}
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", releaseDate=" + releaseDate + ", runtime="
				+ runtime + ", description=" + description + ", rating=" + rating + ", genre=" + genre + "]";
	}
}
