package com.mirea.practice18.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.dto.AuthResponseDto;
import com.mirea.practice18.model.Role;
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

  public AuthResponseDto login(AuthRequestDto request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwt = jwtService.generateToken(user);
    return AuthResponseDto.builder()
        .token(jwt)
        .build();
  }

  public AuthResponseDto register(AuthRequestDto request) {
    var user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    userRepository.save(user);
    var jwt = jwtService.generateToken(user);
    return AuthResponseDto.builder()
        .token(jwt)
        .build();
  }
}