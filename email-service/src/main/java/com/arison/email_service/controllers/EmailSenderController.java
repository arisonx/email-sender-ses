package com.arison.email_service.controllers;


import com.arison.email_service.adapters.EmailSenderGateway;
import com.arison.email_service.core.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderController(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @PostMapping("/send")
      public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
         this.emailSenderGateway.SendEmail(request.getTo(), request.getSubject(), request.getBody());
         return ResponseEntity.ok("Email sent successfully");
        }catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
      }

    }
