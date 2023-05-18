package com.mirea.practice18.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.dto.AuthResponseDto;
import com.mirea.practice18.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto registerRequestDto) {
    return ResponseEntity.ok(authService.login(registerRequestDto));
  }
  
  @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRequestDto registerRequestDto) {
    return ResponseEntity.ok(authService.register(registerRequestDto));
  }
}