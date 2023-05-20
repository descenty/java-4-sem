package com.mirea.practice18.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mirea.practice18.model.Departure;
import com.mirea.practice18.model.PostOffice;

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

    @Async
    public <T> void sendEmail(T instance) {
        try {
            if (instance instanceof Departure)
                sendEmail((Departure) instance);
            else if (instance instanceof PostOffice)
                sendEmail((PostOffice) instance);
            else
                throw new IllegalArgumentException("Unknown instance type");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Async
    public void sendEmail(Departure departure) {
        try {
            String subject = "Создано отправление № " + departure.getId();

            String msgBody = String.format(
                    "Отправление № %s\nТип: %s,\nДата отправления: %s\nПочтовое отделение: %s, г. %s",
                    departure.getId(),
                    departure.getType(),
                    departure.getDepartureDate(),
                    departure.getPostOffice().getName(),
                    departure.getPostOffice().getCityName());

            sendEmail(subject, msgBody);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Async
    public void sendEmail(PostOffice postOffice) {
        try {
            String subject = "Новое почтовое отделение в г. " + postOffice.getCityName();

            String msgBody = String.format(
                    "Почтовое отделение № %s\n%s, г. %s",
                    postOffice.getId(),
                    postOffice.getName(),
                    postOffice.getCityName());

            sendEmail(subject, msgBody);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
