package com.snort.practice.service;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.Repository.UserQuestionRepository;
import com.snort.practice.entity.Question;
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

    public void assignQuestionsToUser(Long userId, String category, String level, Integer setNumber) {
        List<Question> questions = questionRepository.findByCategoryAndLevelAndSetNumber(category, level, setNumber);
        for (Question question : questions) {
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setUserId(userId);
            userQuestion.setQuestionId(question.getId());
            userQuestionRepository.save(userQuestion);
        }
    }

    public List<Question> getAssignedQuestions(Long userId) {
        List<UserQuestion> userQuestions = userQuestionRepository.findByUserId(userId);
        List<Long> questionIds = userQuestions.stream().map(UserQuestion::getQuestionId).collect(Collectors.toList());
        return questionRepository.findAllById(questionIds);
    }
}
