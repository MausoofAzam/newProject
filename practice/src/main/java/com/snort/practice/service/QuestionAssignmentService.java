package com.snort.practice.service;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.Repository.UserRepository;
import com.snort.practice.entity.Question;
import com.snort.practice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionAssignmentService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionAssignmentService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public void assignQuestionsToUserById(int userId, String category, String level, Integer setNumber) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Question> questions = questionRepository.findByCategoryAndLevelAndSetNumber(category, level, setNumber);
            user.setQuestions(questions);
            userRepository.save(user);
        } else {
            // Handle case when user is not found by ID
        }
    }

    // You can add other methods or functionalities as needed
}


