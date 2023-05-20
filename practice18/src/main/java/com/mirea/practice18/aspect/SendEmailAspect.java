package com.mirea.practice18.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mirea.practice18.service.EmailService;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class SendEmailAspect {
    private final EmailService emailService;
    @Value("${send_email}")
    private boolean sendEmail;

    @Around("@annotation(com.mirea.practice18.annotation.SendEmail)")
    public void sendEmail(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        if (sendEmail)
            emailService.sendEmail(joinPoint.getArgs()[0]);
    }
}
