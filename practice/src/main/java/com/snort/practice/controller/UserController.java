package com.snort.practice.controller;

import com.snort.practice.entity.User;
import com.snort.practice.exception.UnauthorizedAccessException;
import com.snort.practice.request.QuestionRequest;
import com.snort.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/assign-question")
    public ResponseEntity<String> assignQuestionToUser(@PathVariable int userId, @RequestBody QuestionRequest questionRequest, Principal principal) {
        // Check if the authenticated user is an admin
        if (!isAdmin(principal)) {
            throw new UnauthorizedAccessException("Only admin users can assign questions.");
        }

        // Assign the question to the normal user
        userService.assignQuestionToUser(userId, questionRequest);

        return ResponseEntity.ok("Question assigned successfully.");
    }

    // Helper method to check if the user has admin role
    private boolean isAdmin(Principal principal) {
        // Logic to determine if the user has admin role
        // You can use your own authentication mechanism or user role management

        // Example: Assuming the User entity has a 'role' field representing the user's role
        // You can customize this logic based on your actual implementation
        User user = userService.getUserByUsername(principal.getName());
        return "admin".equals(user.getRole());
    }
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}

