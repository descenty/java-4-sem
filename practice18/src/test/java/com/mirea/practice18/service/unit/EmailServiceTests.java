package com.mirea.practice18.service.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mirea.practice18.service.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTests {
    @Mock
    private JavaMailSender javaMailSender;
    @Captor
    ArgumentCaptor<SimpleMailMessage> captor;

    @Test
    void sendEmail() {
        String subject = "test_subject";
        String msgBody = "test_msgbody";
        EmailService emailService = new EmailService(javaMailSender);
        emailService.sendEmail(subject, msgBody);
        Mockito.verify(javaMailSender).send(captor.capture());
        SimpleMailMessage value = captor.getValue();
        Assertions.assertThat(value.getSubject()).isEqualTo(subject);
        Assertions.assertThat(value.getText()).isEqualTo(msgBody);
    }
}
