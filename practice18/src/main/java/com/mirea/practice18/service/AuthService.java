package com.mirea.practice18.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.dto.AuthResponseDto;
import com.mirea.practice18.model.ERole;
import com.mirea.practice18.model.User;
import com.mirea.practice18.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<?> login(AuthRequestDto request) {
    try {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body("Invalid email/password");
    }
    User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwt = jwtService.generateToken(user);
    return ResponseEntity.ok(new AuthResponseDto(jwt));
  }

  public ResponseEntity<?> register(AuthRequestDto request) {
    if (userRepository.existsByEmail(request.getEmail()))
      return ResponseEntity.badRequest().body("User with this email already exists");
    var user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(ERole.USER)
        .build();
    userRepository.save(user);
    var jwt = jwtService.generateToken(user);
    return ResponseEntity.ok(new AuthResponseDto(jwt));
  }
}