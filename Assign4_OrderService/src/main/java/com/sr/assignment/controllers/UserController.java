package com.sr.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sr.assignment.models.User;
import com.sr.assignment.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Show registration form
    @GetMapping("/")
    public String showRegisterForm() {
        return "register"; // Return the Thymeleaf template for registration
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        String result = userService.registerUser(user);
        model.addAttribute("message", result); // Add success or error message
        if (result.equals("User registered successfully!")) {
            return "redirect:/login?userId=" + user.getId(); 
        }
        return "register"; // Stay on the registration page if there's an error
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Return the Thymeleaf login page
    }

 // Handle user login
    @PostMapping("/login")
    public String loginUser(User user, Model model) {
        String result = userService.loginUser(user);

        if (result.equals("Username and password are required") || result.equals("Invalid username or password")) {
            model.addAttribute("message", result); // Add the error message to the model
            return "login"; // Stay on the login page if there's an error
        }

        // If login is successful, redirect with the userId
        model.addAttribute("message", "Login successful");
        return "redirect:/orders/place-order?userId=" + result; // Redirect to place order page with userId
    }
}