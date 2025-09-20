package com.aurionpro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo2")
public class Demo2Controller {

	@GetMapping("/hello")
	public String sayHello() {
		return "Demo 2 Hello World";
	}
	
	@GetMapping("/bye")
	public String sayBye() {
		return "Demo 2 Bye World";
	}
}
