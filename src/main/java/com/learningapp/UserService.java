package com.learningapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learningapp.UserController;
import com.learningapp.model.User;
import com.learningapp.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validatePassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User register(UserController.UserRegistration userRegistration) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userRegistration.password()));
        user.setEmail(userRegistration.email());
        user.setFullName("greg");
        user.setPhoneNumber("1234567890");
        return userRepository.save(user);
    }
}