package com.aurionpro.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class StudentCreateRequest {
	@NotBlank
	private String name;

	@NotNull
	@Past
	private LocalDate dob;

	@Valid
	private ProfileCreateRequest profile;

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

	public ProfileCreateRequest getProfile() {
		return profile;
	}

	public void setProfile(ProfileCreateRequest profile) {
		this.profile = profile;
	}
}
