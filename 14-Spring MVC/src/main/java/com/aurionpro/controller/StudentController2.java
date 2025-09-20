package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aurionpro.entity.Student;

import jakarta.validation.Valid;

@Controller
public class StudentController2 {
	
	@Value("${countries}")
	private List<String>countries;
	
	@Value("${languages}")
	private List<String>languages;
	
	@Value("${operatingSystem}")
	private List<String>operatingSystem;
	

	
	@GetMapping("/showStudentForm")
	public String showForm(Model theModel) {
		
		Student theStudent=new Student();
		
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("countries",countries);
		theModel.addAttribute("languages",languages);
		theModel.addAttribute("operatingSystem",operatingSystem);
		
		return "StudentForm";
	}
	
	
	@PostMapping("/processStudentForm")
	public String processForm( @Valid @ModelAttribute("student") Student theStudent,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "StudentForm";
		}
		
		else {
			return "studentConfirmation";
		}
		
		
	}
}
