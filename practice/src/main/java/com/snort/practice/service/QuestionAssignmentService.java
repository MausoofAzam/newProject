package com.snort.practice.service;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.Repository.UserRepository;
import com.snort.practice.entity.Question;
import com.snort.practice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAssignmentService {


        private final UserRepository userRepository;
        private final QuestionRepository questionRepository;

        public QuestionAssignmentService(UserRepository userRepository, QuestionRepository questionRepository) {
            this.userRepository = userRepository;
            this.questionRepository = questionRepository;
        }

        public Object assignQuestionsToUser(String username, String category, String level, Integer setNumber) {
            User user = userRepository.findByName(username).get();
            List<Question> questions = questionRepository.findByCategoryAndLevelAndSetNumber(category, level, setNumber);

            user.setQuestions(questions);
            userRepository.save(user);
            return null;
        }
    }


