package com.aurionpro.Spring.Training;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach{

	@Override
	public String getWorkOut() {
		return "Practice Boling";
	}
	
	public CricketCoach() {
		System.out.println("In Constructor: "+getClass().getSimpleName());
	}
	
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("In doMyStartUpStuff():"+getClass().getSimpleName());
	}
	
	

}
