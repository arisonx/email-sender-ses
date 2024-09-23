package com.arison.email_service.infra.ses;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.arison.email_service.adapters.EmailSenderGateway;
import com.arison.email_service.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender (AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void SendEmail(String to, String subject, String body) {
        String email = "arisontechp@gmail.com";

        SendEmailRequest request =  new SendEmailRequest()
                .withSource(email)
                .withDestination(new Destination().withBccAddresses(to))
                .withMessage(
                        new Message()
                                .withSubject(new Content(subject))
                                .withBody(new Body().withText(new Content(body)))
                );

        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException e) {
            throw new EmailServiceException("Failure while sending email", e);
        }
    }

}
