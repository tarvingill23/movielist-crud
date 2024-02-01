package com.fdmgroup.TarvinGillMovieList.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Director {

	@Id
	@GeneratedValue
	@Column(name = "director_id", nullable=false, unique=true)
	private int dirId;
	
	@OneToOne
	@JoinColumn(name="fk_personnel_id")
	private Personnel personnel;
	
	public Director(Personnel personnel) {
		super();
		this.personnel = personnel;
	}
	
	public Director() {
		super();
	}
	
	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public int getDirId() {
		return dirId;
	}

	public void setDirId(int dirId) {
		this.dirId = dirId;
	}

	@Override
	public String toString() {
		return "Director [dirId=" + dirId + ", personnel=" + personnel + "]";
	}
}
