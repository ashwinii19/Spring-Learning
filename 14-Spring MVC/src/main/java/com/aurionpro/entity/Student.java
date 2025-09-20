package com.aurionpro.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Student {

	@NotNull(message="first name is required!")
	@Size(min=1,message="is required")
	private String firstName;
	private String lastName;
	private String country;
	private String language;
	// private List<String> os;
	private String operatingSystem;

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Student() {

	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
