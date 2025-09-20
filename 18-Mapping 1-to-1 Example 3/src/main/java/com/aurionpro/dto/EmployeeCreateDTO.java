package com.aurionpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeCreateDTO {

	@NotBlank
	@Size(min = 2)
	private String firstName;

	@NotBlank
	@Size(min = 2)
	private String lastName;

	@NotBlank
	@Email
	private String email;

	private PassportCreateDTO passport;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PassportCreateDTO getPassport() {
		return passport;
	}

	public void setPassport(PassportCreateDTO passport) {
		this.passport = passport;
	}

}
