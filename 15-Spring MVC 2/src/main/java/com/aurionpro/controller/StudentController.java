package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aurionpro.entity.Student;

import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Value("${countries}")
	private List<String>countries;
	
	@Value("${languages}")
	private List<String>languages;
	
	@Value("${subjects}")
	private List<String>subjects;
	
	@ModelAttribute
	public void populateModel(Model theModel) {
		theModel.addAttribute("countries",countries);
		theModel.addAttribute("languages",languages);
		theModel.addAttribute("subjects",subjects);
	}
	
	@GetMapping("/showStudentForm")
	public String showForm(Model theModel) {
		
		Student theStudent=new Student();
		
		theModel.addAttribute("student",theStudent);
//		theModel.addAttribute("countries",countries);
//		theModel.addAttribute("languages",languages);
//		theModel.addAttribute("subjects",subjects);
		
		return "StudentForm";
	}
	
	@PostMapping("/processStudentForm")
	public String processForm(@Valid @ModelAttribute("student") Student theStudent,BindingResult theBindingResult) {
		
		
		System.out.println("Binding results: " + theBindingResult.toString());
		
		if(theBindingResult.hasErrors()) {
			return "StudentForm";
		}
		
		
		return "StudentFormDisplay";
	}
	
	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}
	
}
