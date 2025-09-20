package com.aurionpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	@RequestMapping("/showForm")
	public String showForm() {
		return "form";
	}
	
	
//	@RequestMapping("/processForm")
//	public String processForm(HttpServletRequest request,Model model) {
//		
//		String name=request.getParameter("studentName");
//		
//		name=name.toUpperCase();
//		
//		String result="Yo! "+name;
//		
//		model.addAttribute("message", result);
//		
//		
//		
//		return "formResponse";
//	}
	
	
	@RequestMapping("/processForm")
	public String processForm(@RequestParam ("studentName") String name ,Model model) {
		
		
		
		name=name.toUpperCase();
		
		String result="Yo! "+name;
		
		model.addAttribute("message", result);
		
		
		
		return "formResponse";
	}
	
}
