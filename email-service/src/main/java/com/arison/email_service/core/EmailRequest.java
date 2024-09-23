package com.arison.email_service.core;


public interface  EmailRequest  {
    String getTo();
    String getSubject();
    String getBody();
}
