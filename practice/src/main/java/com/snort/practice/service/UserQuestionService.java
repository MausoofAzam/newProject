package com.snort.practice.service;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.Repository.UserQuestionRepository;
import com.snort.practice.Repository.UserRepository;
import com.snort.practice.entity.ExamResult;
import com.snort.practice.entity.Question;
import com.snort.practice.entity.User;
import com.snort.practice.entity.UserQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserQuestionService {

    @Autowired
    private UserQuestionRepository userQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;

    public void assignQuestionsToUser(int userId, String category, String level, Integer setNumber) {
        List<Question> questions = questionRepository.findByCategoryAndLevelAndSetNumber(category, level, setNumber);
        for (Question question : questions) {
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setUserId(userId);
            userQuestion.setQuestionId(question.getId());
            userQuestionRepository.save(userQuestion);
        }
    }

    public List<Question> getAssignedQuestions(int userId) {
        List<UserQuestion> userQuestions = userQuestionRepository.findByUserId(userId);
        List<Long> questionIds = userQuestions.stream().map(UserQuestion::getQuestionId).collect(Collectors.toList());
        return questionRepository.findAllById(questionIds);
    }




    public ExamResult getExamResult(int userId) {
        // Retrieve all UserQuestion records for the given user
        List<UserQuestion> userQuestions = userQuestionRepository.findByUserId(userId);

        int score = 0;
        // Loop through each UserQuestion record
        for (UserQuestion userQuestion : userQuestions) {
            // Retrieve the corresponding Question record
            Question question = questionRepository.findById(userQuestion.getQuestionId()).orElse(null);
            if (question != null) {
                // Calculate the score based on the user's answer and the correct answer
                if (userQuestion.getAnswer().equals(question.getCorrectAnswer())) {
                    score += question.getTotalMarks();
                }
            }
        }

        // Save the score to the User record
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setScore(score);
            userRepository.save(user);
        }

        // Create and return an ExamResult object with the calculated score
        return new ExamResult(userId, score);
    }
}
