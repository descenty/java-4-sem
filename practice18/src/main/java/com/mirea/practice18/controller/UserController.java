package com.mirea.practice18.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.model.User;
import com.mirea.practice18.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.loadUserByUsername(username);
    }
}
