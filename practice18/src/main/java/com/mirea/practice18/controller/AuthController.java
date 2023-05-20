package com.mirea.practice18.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto) {
    return authService.login(authRequestDto).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody AuthRequestDto authRequestDto) {
    return authService.register(authRequestDto).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
  }
}