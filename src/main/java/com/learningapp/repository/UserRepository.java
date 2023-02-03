package com.learningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
