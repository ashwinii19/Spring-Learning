package com.aurionpro.Spring.Training;

import org.springframework.stereotype.Component;

@Component
public class KabbadiCoach implements Coach{

	@Override
	public String getWorkOut() {
		
		return "Practice kabbadi";
	}

	public KabbadiCoach() {
		System.out.println("In Constructor: "+getClass().getSimpleName());
	}
}
