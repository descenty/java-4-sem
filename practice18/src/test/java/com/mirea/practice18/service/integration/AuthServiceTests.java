package com.mirea.practice18.service.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.mirea.practice18.dto.AuthRequestDto;
import com.mirea.practice18.dto.AuthResponseDto;
import com.mirea.practice18.repository.UserRepository;
import com.mirea.practice18.service.AuthService;

@SpringBootTest
@AutoConfigureTestDatabase
public class AuthServiceTests {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void register() {
        AuthRequestDto authRequestDto = AuthRequestDto.builder()
                .email("user1@mail.ru").password("password1").build();
        Assertions.assertThat(authService.register(authRequestDto).get()).isInstanceOf(AuthResponseDto.class);
        Assertions.assertThat(userRepository.findByEmail("user1@mail.ru")).isPresent();
        Assertions.assertThat(authService.register(authRequestDto)).isEmpty();
    }

    @Test
    void login() {
        AuthRequestDto authRequestDto1 = AuthRequestDto.builder()
                .email("user2@mail.ru").password("password2").build();
        authService.register(authRequestDto1);
        Assertions.assertThat(authService.login(authRequestDto1).get()).isInstanceOf(AuthResponseDto.class);
        AuthRequestDto authRequestDto2 = AuthRequestDto.builder()
                .email("user3@mail.ru").password("password3").build();
        Assertions.assertThat(authService.login(authRequestDto2)).isEmpty();
    }
}
