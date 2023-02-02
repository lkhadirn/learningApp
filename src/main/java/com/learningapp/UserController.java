package com.learningapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User foundUser = userService.findByEmail(user.getEmail());
        if (foundUser == null) {
            return false;
        }
        return userService.validatePassword(foundUser, user.getPassword());
    }

    @PostMapping("/registration")
    public String register(@RequestParam String email, @RequestParam String password) {
        UserRegistration userRegistration = new UserRegistration(email, password);
        User createdUser = userService.register(userRegistration);
        System.out.println(createdUser);
        return "quiz";
    }

    record UserRegistration(@JsonProperty String email, @JsonProperty String password) {
    }
}
