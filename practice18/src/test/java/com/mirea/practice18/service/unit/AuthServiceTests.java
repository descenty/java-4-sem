package com.mirea.practice18.service.unit;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.dto.AuthResponseDto;
import com.mirea.practice18.model.ERole;
import com.mirea.practice18.model.User;
import com.mirea.practice18.repository.UserRepository;
import com.mirea.practice18.service.AuthService;
import com.mirea.practice18.service.JwtService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void login() {
        AuthRequestDto authRequestDto = AuthRequestDto.builder().email("email").password("password").build();
        User user = User.builder().email("email").password("password").build();

        AuthService authService = new AuthService(userRepository, passwordEncoder, jwtService, authenticationManager);

        Mockito.when(userRepository.findByEmail(authRequestDto.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateToken(user)).thenReturn("token");

        Optional<AuthResponseDto> authResponseDto = authService.login(authRequestDto);
        Assertions.assertThat(authResponseDto.get().getToken()).isEqualTo("token");

        Mockito.when(userRepository.findByEmail(authRequestDto.getEmail())).thenReturn(Optional.empty());
        Assertions.assertThat(authService.login(authRequestDto)).isEmpty();
    }

    @Test
    void register() {
        AuthRequestDto authRequestDto = AuthRequestDto.builder().email("email").password("password").build();
        User user = User.builder().email("email").password("password").role(ERole.USER).build();

        AuthService authService = new AuthService(userRepository, passwordEncoder, jwtService, authenticationManager);

        Mockito.when(passwordEncoder.encode(authRequestDto.getPassword())).thenReturn("password");
        Mockito.when(userRepository.existsByEmail(authRequestDto.getEmail())).thenReturn(false);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(jwtService.generateToken(user)).thenReturn("token");

        Optional<AuthResponseDto> authResponseDto = authService.register(authRequestDto);
        Assertions.assertThat(authResponseDto.get().getToken()).isEqualTo("token");

        Mockito.when(userRepository.existsByEmail(authRequestDto.getEmail())).thenReturn(true);
        Assertions.assertThat(authService.register(authRequestDto)).isEmpty();
    }

}
