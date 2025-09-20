package com.aurionpro.Spring.Training;

import org.springframework.stereotype.Component;

@Component
public class footballCoach implements Coach{

	@Override
	public String getWorkOut() {
		
		return "Practice football";
	}
	
	public footballCoach() {
		System.out.println("In Constructor: "+getClass().getSimpleName());
	}

}
