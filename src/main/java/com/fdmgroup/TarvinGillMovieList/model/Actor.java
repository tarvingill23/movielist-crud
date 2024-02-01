package com.fdmgroup.TarvinGillMovieList.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Actor {
	@Id
	@GeneratedValue
	@Column(name = "actor_id", nullable=false, unique=true)
	private int actorId;
	
	@OneToOne
	@JoinColumn(name="fk_personnel_id")
	private Personnel personnel;
	
	@ManyToMany(mappedBy="actors", cascade= CascadeType.ALL)
	List <Movie> movies;
	/**
	 * CONSTRUCTORS
	 */
	public Actor(Personnel personnel) {
		super();
		this.personnel = personnel;
	}

	public Actor() {
		super();
	}
	
	/**
	 * GETTERS AND SETTERS
	 */
	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
	/**
	 * TO STRING METHOD
	 */

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", personnel=" + personnel + "]";
	}
	
}
