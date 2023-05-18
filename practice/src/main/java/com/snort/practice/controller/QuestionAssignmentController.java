package com.snort.practice.controller;

import com.snort.practice.entity.User;
import com.snort.practice.service.QuestionAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api")
    public class QuestionAssignmentController {

        private final QuestionAssignmentService questionAssignmentService;

        public QuestionAssignmentController(QuestionAssignmentService questionAssignmentService) {
            this.questionAssignmentService = questionAssignmentService;
        }

        @PostMapping("/assign-questions/{userId}")
        public ResponseEntity<String> assignQuestionsToUserById(@PathVariable int userId,
                                                                @RequestParam("category") String category,
                                                                @RequestParam("level") String level,
                                                                @RequestParam("setNumber") Integer setNumber) {
            questionAssignmentService.assignQuestionsToUserById(userId, category, level, setNumber);
            return ResponseEntity.ok("Assigned questions to user with ID: " + userId);
        }

        // Other controller methods...

    }




       /* {
            "username": "Anu@gmail.com",
                "category": "java",
                "level": "beginner",
                "setNumber": 1
        }*/