package com.mirea.practice11.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.mirea.practice11.model.User;

@Service
public class UserService {
    private User[] users = {
            new User("Alexey", "Vladimirov", "test@example.com", "qweasdzxc"),
            new User("Ivan", "Ivanov", "ivan@yandex.ru", "sdawdab2a"),
    };

    public User[] getUsers() {
        return users;
    }

    public User getUser(String email) {
        return Stream.of(users).filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
