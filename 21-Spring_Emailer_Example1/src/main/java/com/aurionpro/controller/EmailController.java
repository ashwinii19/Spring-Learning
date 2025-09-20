package com.aurionpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.service.EmailService;

@RestController
public class EmailController {

	 @Autowired
	    private EmailService emailService;

	    @GetMapping("/sendEmail")
	    public String sendEmail(@RequestParam String to,
	                            @RequestParam String subject,
	                            @RequestParam String body) {
	        emailService.sendSimpleEmail(to, subject, body);
	        return "Email sent successfully";
	    }
}
