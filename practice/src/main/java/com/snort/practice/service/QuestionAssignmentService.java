package com.snort.practice.service;

import com.snort.practice.Repository.QuestionAssignmentRepository;
import com.snort.practice.entity.Question;
import com.snort.practice.entity.QuestionAssignment;
import com.snort.practice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAssignmentService {

    @Autowired
    private QuestionAssignmentRepository questionAssignmentRepository;

    public void assignQuestionToUser(User adminUser, User normalUser, Question question, String category, String level, int setNumber) {
        if (!adminUser.getRole().equals("admin")) {
//            throw new UnauthorizedAccessException("Only admin users can assign questions.");
            System.out.println("Only admin users can assign questions.");
        }

        QuestionAssignment assignment = new QuestionAssignment();
        assignment.setUser(normalUser);
        assignment.setQuestion(question);
        assignment.setCategory(category);
        assignment.setLevel(level);
        assignment.setSetNumber(setNumber);

        questionAssignmentRepository.save(assignment);
    }
}
