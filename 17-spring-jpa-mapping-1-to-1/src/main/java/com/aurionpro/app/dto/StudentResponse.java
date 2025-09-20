package com.aurionpro.app.dto;

import java.time.LocalDate;

public class StudentResponse {
	private Long id;
	private String name;
	private LocalDate dob;
	private ProfileCreateRequest profile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
