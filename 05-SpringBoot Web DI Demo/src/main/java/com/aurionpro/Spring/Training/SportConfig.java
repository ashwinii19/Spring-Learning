package com.aurionpro.Spring.Training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

	@Bean
	public Coach swimCoach() {
		return new SwimCoach();
	}
}
