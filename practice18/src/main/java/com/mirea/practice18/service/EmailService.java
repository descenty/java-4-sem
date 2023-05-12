package com.mirea.practice18.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${email_recipient}")
    private String recipient;

    @Async
    public void sendEmail(String subject, String msgBody) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipient);
            mailMessage.setSubject(subject);
            mailMessage.setText(msgBody);

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
