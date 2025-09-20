package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aurionpro.entity.Student;

@Controller
public class StudentController {

	@Value("${countries}")
	private List<String>countries;
	
	@Value("${languages}")
	private List<String>languages;
	
	@Value("${subjects}")
	private List<String>subjects;
	
	@GetMapping("/showStudentForm")
	public String showForm(Model theModel) {
		
		Student theStudent=new Student();
		
		theModel.addAttribute("student",theStudent);
		theModel.addAttribute("countries",countries);
		theModel.addAttribute("languages",languages);
		theModel.addAttribute("subjects",subjects);
		
		return "StudentForm";
	}
	
	@PostMapping("/processStudentForm")
	public String processForm(@ModelAttribute("student") Student theStudent) {
		
		
		
		return "StudentFormDisplay";
	}
	
}
