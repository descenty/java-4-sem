package com.mirea.practice18.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public Object getAll() {
        return userService.getAll();
    }
}
