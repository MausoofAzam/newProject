package com.snort.practice.service;

import com.snort.practice.Repository.OptionRepository;
import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.entity.Option;
import com.snort.practice.entity.Question;
import com.snort.practice.request.QuestionRequest;
import com.snort.practice.request.QuestionResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;

    public Question createQuestion(QuestionRequest questionRequest){
        Question question = new Question(questionRequest);
        Option option = new Option();
        option.setOption1(questionRequest.getOption1());
        option.setOption2(questionRequest.getOption2());
        option.setOption3(questionRequest.getOption3());
        option.setOption4(questionRequest.getOption4());
        option.setTitle(questionRequest.getTitle());
         option = optionRepository.save(option);
        question.setOptions(option);
        question.setCategory(questionRequest.getCategory());
        question.setQuestionDescription(questionRequest.getQuestionDescription());
        question.setLevel(questionRequest.getLevel());
        question.setSetNumber(questionRequest.getSetNumber());
        question.setQuestionType(questionRequest.getQuestionType());
        question.setTotalMarks(questionRequest.getTotalMarks());
        question.setQuestionType(questionRequest.getQuestionType());

        questionRepository.save(question);
        return question;

    }

    public List<Question> findByCateoryAndLevel(String category, String level){
        List<Question> byCategoryAndLevel = questionRepository.findByCategoryAndLevel(category, level);
        return byCategoryAndLevel;
    }
    public List<Question> findAllQuestions(){
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    public void deleteQuestion(Long id){
        if (id!=null){
//            optionRepository.deleteById(id);
            questionRepository.deleteById(id);
        }
    }

   /*  public int calculateResult(String category, String level, List<Long> userAnswers) {
        List<Question> questions = questionRepository.findByCategoryAndLevel(category, level);
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            Option correctAnswer = question.getAnswer();
            if (correctAnswer.getId() == userAnswers.get(i)) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }*/

    public List<Question> findBySetNumber(Integer setNumber){
        return questionRepository.findBySetNumber(setNumber);
    }
    public Integer countByCategoryAndLevelAndSetNumber(String category,String level,Integer setNumber){
        return questionRepository.countByCategoryAndLevelAndSetNumber(category,level,setNumber);
    }
    public Integer addMarksByCategoryAndLevelAndSetNumber(String category, String level, Integer setNumber){
        return questionRepository.sumByCategoryAndLevelAndSetNumber(category,level,setNumber);
    }

}
