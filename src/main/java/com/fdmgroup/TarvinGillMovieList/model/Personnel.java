package com.fdmgroup.TarvinGillMovieList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Personnel {

	@Id
	@GeneratedValue
	@Column(name = "personnel_id", nullable=false, unique=true)
	private int personnelId;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name", nullable = false)
	private String lastName;

	/**
	 * CONSTRUCTORS
	 */
	public Personnel(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		
	}

	public Personnel(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Personnel() {
		super();
	}

	/**
	 * GETTERS AND SETTERS
	 */

	public int getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(int personnelId) {
		this.personnelId = personnelId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * TO STRING METHOD
	 */
	@Override
	public String toString() {
		return "Personnel [personnelId=" + personnelId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
