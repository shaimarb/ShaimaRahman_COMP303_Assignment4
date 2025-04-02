package com.sr.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.assignment.models.User;
import com.sr.assignment.repos.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public String registerUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Username and password are required";
        }
        
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username is already taken";
        }
        
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Handle user login
    public String loginUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Username and password are required";
        }

        // Retrieve the user by username and check password
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser.getId(); // Return userId if login is successful
        } else {
            return "Invalid username or password";
        }
    }


    // Find user by username
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    // Find user by ID
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }
}