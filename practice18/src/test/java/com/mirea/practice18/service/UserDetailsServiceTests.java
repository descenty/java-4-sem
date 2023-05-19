package com.mirea.practice18.service;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mirea.practice18.model.User;
import com.mirea.practice18.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTests {
    @Mock
    private UserRepository userRepository;

    @Test
    void getAll() {
        User user = new User();
        user.setEmail("test_user@mail.ru");
        User user2 = new User();
        user2.setEmail("test_admin@mail.ru");
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user,
                user2));
        UserDetailsServiceImpl userService = new UserDetailsServiceImpl(userRepository);
        Assertions.assertEquals(2,
                userService.getAll().size());
        Assertions.assertEquals("test_user@mail.ru",
                userService.getAll().get(0).getEmail());
        Assertions.assertEquals("test_admin@mail.ru",
                userService.getAll().get(1).getEmail());
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setEmail("test_user@mail.ru");
        Mockito.when(userRepository.findByEmail("test_user@mail.ru")).thenReturn(Optional.of(user));
        UserDetailsServiceImpl userService = new UserDetailsServiceImpl(userRepository);
        Assertions.assertEquals("test_user@mail.ru",
                userService.loadUserByUsername("test_user@mail.ru").getUsername());
        Assertions.assertThrows(
                org.springframework.security.core.userdetails.UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("test_abcd@yandex.ru"));
    }
}
