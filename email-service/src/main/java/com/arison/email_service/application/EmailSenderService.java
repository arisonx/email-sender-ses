package com.arison.email_service.application;

import com.arison.email_service.adapters.EmailSenderGateway;
import com.arison.email_service.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {
    private EmailSenderGateway emailSenderGateway;

    @Autowired
    void setEmailSenderGateway(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.SendEmail(to, subject, body);
    }

}
