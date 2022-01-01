package com.backend.server.email;

public interface EmailSender {
    void send(String to, String email);
    
}
