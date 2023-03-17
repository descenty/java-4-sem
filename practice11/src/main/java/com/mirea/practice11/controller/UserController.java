package com.mirea.practice11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mirea.practice11.model.User;
import com.mirea.practice11.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<User[]> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        User user = userService.getUser(email);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return ResponseEntity.ok(user);
    }

}