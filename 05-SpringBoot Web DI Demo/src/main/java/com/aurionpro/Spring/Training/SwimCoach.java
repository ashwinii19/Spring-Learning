package com.aurionpro.Spring.Training;

public class SwimCoach implements Coach{

	@Override
	public String getWorkOut() {
		return "Practice swim";
	}
	
	public SwimCoach() {
		System.out.println("In Constructor: "+getClass().getSimpleName());
	}
}
