package com.mirea.practice13;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Student {
    @Value("${student.name}")
    private String name;
    
    @Value("${student.last_name}")
    private String lastName;

    @Value("${student.group}")
    private String group;

    @PostConstruct
    public void init() {
        System.out.println("Student name: " + name);
        System.out.println("Student last name: " + lastName);
        System.out.println("Student group: " + group);
    }
}
