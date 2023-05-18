package com.snort.practice.controller;

import com.snort.practice.entity.Question;
import com.snort.practice.entity.User;
import com.snort.practice.exception.UnauthorizedAccessException;
import com.snort.practice.request.QuestionRequest;
import com.snort.practice.service.UserQuestionService;
import com.snort.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserQuestionService userQuestionService;

    @PostMapping("/assign/{userId}/{category}/{level}/{setNumber}")
    public ResponseEntity<?> assignQuestionsToUser(@PathVariable Long userId,
                                                   @PathVariable String category,
                                                   @PathVariable String level,
                                                   @PathVariable Integer setNumber) {
        userQuestionService.assignQuestionsToUser(userId, category, level, setNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Question>> getAssignedQuestions(@PathVariable Long userId) {
        List<Question> questions = userQuestionService.getAssignedQuestions(userId);
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}

