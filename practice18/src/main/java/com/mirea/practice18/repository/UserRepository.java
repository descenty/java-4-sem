package com.mirea.practice18.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirea.practice18.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}