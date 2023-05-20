package com.mirea.practice18.service;

import java.util.Optional;

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

  public Optional<AuthResponseDto> login(AuthRequestDto request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    } catch (Exception e) {
      return Optional.empty();
    }
    return userRepository.findByEmail(request.getEmail())
        .map(jwtService::generateToken)
        .map(AuthResponseDto::new);
  }

  public Optional<AuthResponseDto> register(AuthRequestDto request) {
    if (userRepository.existsByEmail(request.getEmail()))
      return Optional.empty();
    return Optional.of(User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(ERole.USER)
        .build())
        .map(userRepository::save)
        .map(jwtService::generateToken)
        .map(AuthResponseDto::new);
  }
}