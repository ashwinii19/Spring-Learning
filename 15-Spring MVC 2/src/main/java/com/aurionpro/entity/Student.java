package com.aurionpro.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Student {

	
	@NotNull(message="First Name is required!")
	@Size(min=1,message="First Name is required!")
	private String firstName;
	
	@NotNull(message="Last Name is required!")
	@Size(min=1,message="Last Name is required!")
	private String lastName;
	
	@Min(value=0,message="Must be greater than or equal to zero!")
	@Max(value=10,message="must be less than or equal to 10!")
	private int freePasses;
	
	@Pattern(regexp="^[0-9]{6}",message="Only 6 Digits")
	private String postalCode;
	
	@NotNull(message="Country is required!")
	@Size(min=1,message="Country is required!")
	private String country;
	
	@NotNull(message="Language is required!")
	@Size(min=1,message="Language is required!")
	private String language;
	
	@NotNull(message="subjects is required!")
	@Size(min=1,message="subjects is required!")
	private String subjects;
	
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public Student() {
	}
	public int getFreePasses() {
		return freePasses;
	}
	public void setFreePasses(int freePasses) {
		this.freePasses = freePasses;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	
	
}
