package com.snort.practice.service;

import com.snort.practice.Repository.UserRepository;
import com.snort.practice.entity.User;
import com.snort.practice.exception.UserNotFoundException;
import com.snort.practice.request.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  UserService {

    @Autowired
    private UserRepository userRepository;

    public void assignQuestionToUser(int userId, QuestionRequest questionRequest) {
        // Get the user by ID from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Perform the assignment logic here
        // You can customize this based on your requirements
        // For example, you can update the user's assigned question field or create a new assignment entity

        // Example: Assuming the User entity has a field to store the assigned question ID
        user.setAssignedQuestionId(questionRequest.getId());

        // Save the updated user in the database
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
    public User saveUser(User user) {
        // Additional logic or validations can be implemented here
        user.setEnabled(true);
        user.setImageUrl("none");
        user.setPassword("no password");
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }
}
