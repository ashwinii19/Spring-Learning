package com.aurionpro.Spring.Training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PreDestroy;

@RestController
public class DemoController {

//	@Autowired
//	@Qualifier("hockeyCoach")
	private Coach coach;
//	private Coach anotherCoach;
	
	
	@Autowired
	public DemoController(@Qualifier("swimCoach") Coach coach) {
		this.coach=coach;
//		this.coach=anotherCoach;
//		
//		System.out.println(coach==anotherCoach);
	}
	
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("In doMyCleanUpStuff():"+getClass().getSimpleName());
	}
	
	
//	@Autowired
//	public void setCoach(Coach coach) {
//		this.coach=coach;
//	}
	
	@GetMapping("/workout")
	public String workout() {
		return coach.getWorkOut();
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
}
