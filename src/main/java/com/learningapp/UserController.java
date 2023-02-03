package com.learningapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningapp.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        User foundUser = userService.findByEmail(email);
        if (foundUser != null && userService.validatePassword(foundUser, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", foundUser);
            return "quiz";
        }
        return "invalid credentials";
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
