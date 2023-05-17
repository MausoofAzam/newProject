package com.snort.practice.controller;

import com.snort.practice.entity.User;
import com.snort.practice.service.QuestionAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
    public class AdminController {

    private final QuestionAssignmentService questionAssignmentService;

    public AdminController(QuestionAssignmentService questionAssignmentService) {
        this.questionAssignmentService = questionAssignmentService;
    }

    @PostMapping("/assign-questions")
    public String assignQuestionsToUser(@RequestParam("username") String username,
                                        @RequestParam("category") String category,
                                        @RequestParam("level") String level,
                                        @RequestParam("setNumber") Integer setNumber) {
        // Validate the request parameters
        // Example validation: Check if the required fields are present
           /* if (username == null || category == null || level == null || setNumber == null) {
                return ResponseEntity.badRequest().body("Invalid request parameters");
            }*/

        // Assign questions to the user based on the provided criteria
        questionAssignmentService.assignQuestionsToUser(username, category, level, setNumber);
        return "Assigned Success";
    }
}
            /*if (success) {
                return ResponseEntity.ok("Questions assigned successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign questions");
            }*/
//        }

        /*public void assignQuestionsToUserPostman(String username, String category, String level, Integer setNumber) {
            questionAssignmentService.assignQuestionsToUser(username, category, level, setNumber);
            // Handle the response or return a success message
        }


    }*/
//}



       /* {
            "username": "Anu@gmail.com",
                "category": "java",
                "level": "beginner",
                "setNumber": 1
        }*/