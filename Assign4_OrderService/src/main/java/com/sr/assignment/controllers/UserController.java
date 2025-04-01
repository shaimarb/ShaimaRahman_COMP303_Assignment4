package com.sr.assignment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sr.assignment.models.User;
import com.sr.assignment.services.UserService;

@Controller
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @GetMapping("/")
    public String showRegisterForm() {
        return "register"; // Return the Thymeleaf template for registration
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (user.getUsername() == null || user.getPassword() == null) {
            model.addAttribute("error", "Username and password are required");
            return "register"; // Show the form again if input is invalid
        }

        User registeredUser = userService.registerUser(user);
        model.addAttribute("success", "User registered successfully!");
        return "login"; // Redirect to login page after successful registration
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the Thymeleaf login page
    }

    @PostMapping("/login")
    public String loginUser(User user, Model model) {
        if (user.getUsername() == null || user.getPassword() == null) {
            model.addAttribute("error", "Username and password are required");
            return "login"; // Show the login form again if input is invalid
        }

        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            model.addAttribute("success", "Login successful");
            return "redirect:/orders/place-order"; // Redirect to order placement page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Show error if login fails
        }
    }
}
