package com.aurionpro.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class StudentUpdateRequest {

	private String name;

	@Past
	private LocalDate dob;
	
	@Valid
	private ProfileUpdateRequest profile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public ProfileUpdateRequest getProfile() {
		return profile;
	}

	public void setProfile(ProfileUpdateRequest profile) {
		this.profile = profile;
	}
}
