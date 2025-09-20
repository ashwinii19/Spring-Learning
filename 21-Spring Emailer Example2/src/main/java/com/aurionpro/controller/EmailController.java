package com.aurionpro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleEmail("amit421.tiwari@gmail.com",
                "Hello",
                "Good AfterNoon, this is a test email of spring!");
        return "Email Sent!";
    }
}

