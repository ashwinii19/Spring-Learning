package com.aurionpro.Spring2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.aurionpro.Spring.Training.Coach;

@Component
@Primary
public class HockeyCoach implements Coach{

	@Override
	public String getWorkOut() {
		return "Practice hockey";
	}
	
	public HockeyCoach() {
		System.out.println("In Constructor: "+getClass().getSimpleName());
	}
}
