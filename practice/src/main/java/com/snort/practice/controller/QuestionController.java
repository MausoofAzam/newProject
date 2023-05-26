package com.snort.practice.controller;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.Repository.UserQuestionRepository;
import com.snort.practice.entity.ExamResult;
import com.snort.practice.entity.Question;
import com.snort.practice.entity.UserQuestion;
import com.snort.practice.request.QuestionRequest;
import com.snort.practice.service.QuestionService;
import com.snort.practice.service.UserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Controller
@RestController
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    UserQuestionService userQuestionService;
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserQuestionRepository userQuestionRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/mcq/result")
    public String showResultPage() {
        return "result";
    }

    @PostMapping("/mcq/saveQuestions")
    public Question createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionService.createQuestion(questionRequest);
        return question;
    }

    @GetMapping("/mcq/createQuestion")
    public String showCreateQuestionForm(Model model) {
        model.addAttribute("questionRequest", new QuestionRequest());
        return "createQuestion";
    }

    @PostMapping("/mcq/saveQuestion")
    public String saveQuestion(@ModelAttribute("questionRequest") QuestionRequest questionRequest) {
        Question question = questionService.createQuestion(questionRequest);
        System.out.println("created Question: "+question);
        return "redirect:/mcq/createQuestion";
    }

    @GetMapping("/mcq/findByCategoryAndLevel/{category}/{level}")
    public List<Question> findByCategoryAndLevel(@PathVariable String category, @PathVariable String level) {
        List<Question> byCateoryAndLevel = questionService.findByCateoryAndLevel(category, level);
        return byCateoryAndLevel;
    }

    @GetMapping("/mcq/examsList")
    public String startExam(Model model, @RequestParam(required = false, name = "category") String category,
                            @RequestParam(required = false, name = "level") String level, @RequestParam(required = false, name = "setNumber") Integer setNumber) {
        System.out.println("category :"+category+"level :"+level+"setNumber : "+setNumber);
        if (category != null && level != null && setNumber != null) {
            List<Question> questions = questionService.findQusByCategoryAndLevelAndSetNumber(category, level, setNumber);
            System.out.println("List of Question : "+questions);
            model.addAttribute("category", category);
            model.addAttribute("level", level);
            model.addAttribute("setNumber", setNumber);
            model.addAttribute("questions", questions);
            model.addAttribute("totalCount", questionService.countByCategoryAndLevelAndSetNumber(category, level, setNumber));
            model.addAttribute("totalMarks", questionService.addMarksByCategoryAndLevelAndSetNumber(category, level, setNumber));

            return "exam";
        } else {
            return "startExamsDemo";
        }
    }


    @GetMapping("/mcq/exam")
    public String startExam(Model model,
                            @RequestParam(required = false, name = "category") String category,
                            @RequestParam(required = false, name = "level") String level,
                            @RequestParam(required = false, name = "setNumber") Integer setNumber,
                            @PageableDefault(size = 1) Pageable pageable) {

        if (category != null && level != null && setNumber != null) {
            Page<Question> questions = questionService.findQuestionsByCategoryAndLevelAndSetNumberPaginated(category, level, setNumber, pageable);
            model.addAttribute("questions", questions);
            model.addAttribute("category", category);
            model.addAttribute("level", level);
            model.addAttribute("setNumber", setNumber);
            model.addAttribute("totalCount", questionService.countByCategoryAndLevelAndSetNumber(category, level, setNumber));
            model.addAttribute("totalMarks", questionService.addMarksByCategoryAndLevelAndSetNumber(category, level, setNumber));
            System.out.println("category :"+category+"level :"+level+"setNumber : "+setNumber);
            return "exam122";
        } else {
            return "startExamsDemo";
        }



    }







  /*  @GetMapping("/findQusByCategoryAndLevelAndSetNumber/{category}/{level}/{setNumber}")
    public List<Question> getQuestionsByCategoryAndLevelAndSetNumber(@PathVariable String category, @PathVariable String level, @PathVariable Integer setNumber){
        return questionService.findQusByCategoryAndLevelAndSetNumber(category, level, setNumber);
    }*/


    @GetMapping("/mcq/exam12")
    public String startExam1(Model model) {
        return "startExamsDemo";
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        if (id != null) {
            questionService.deleteQuestion(id);
            return "Questions with id : " + id + "Deleted Successfully";
        }
        return "Questions with id  :" + id + "Unable to delete";
    }

    @GetMapping("mcq/{setNumber}")
    public List<Question> findBySetNumber(@PathVariable Integer setNumber) {
        System.out.println("set number: " + setNumber);
        return questionService.findBySetNumber(setNumber);
    }

    @GetMapping("totalCount/{category}/{level}/{setNumber}")
    public Integer countByCategoryAndLevelAndSetNumber(@PathVariable String category, @PathVariable String level, @PathVariable Integer setNumber) {
        Integer integer = questionService.countByCategoryAndLevelAndSetNumber(category, level, setNumber);
        System.out.println("countByCategoryAndLevelAndSetNumber : " + integer);
        return integer;

    }

    @GetMapping("totalMarks/{category}/{level}/{setNumber}")
    public Integer addTotalMarksByCategoryAndLevelAndSetNumber(@PathVariable String category, @PathVariable String level, @PathVariable Integer setNumber) {
        Integer sum = questionService.addMarksByCategoryAndLevelAndSetNumber(category, level, setNumber);
        System.out.println("addTotalMarksByCategoryAndLevelAndSetNumber : " + sum);
        return sum;
    }


        /*handler for exam result*/
    @GetMapping("/result/{userId}")
    public ResponseEntity<ExamResult> getExamResult(@PathVariable int userId) {
        ExamResult examResult = userQuestionService.getExamResult(userId);
        return ResponseEntity.ok(examResult);
    }





    /*handler for receiving the question*/

    @RestController
    @RequestMapping("/user/assigned-question")
    public class UserQuestionController {

        // Autowire the necessary dependencies

        @PostMapping("/update-answer")
        public ResponseEntity<String> updateAnswer(@RequestBody UserQuestion userQuestion) {
            // Retrieve the user ID and question ID from the UserQuestion object
            int userId = userQuestion.getUserId();
            Long questionId = userQuestion.getQuestionId();

            // Retrieve the question from the database using the question ID
            Optional<Question> optionalQuestion = questionRepository.findById(questionId);
            if (optionalQuestion.isEmpty()) {
                return ResponseEntity.badRequest().body("Question not found");
            }
            Question question = optionalQuestion.get();

            // Check if the selected answer is correct
            boolean isCorrectAnswer = question.getCorrectAnswer().equals(userQuestion.getAnswer());

            // Calculate the score based on whether the answer is correct or not
            int score = isCorrectAnswer ? question.getTotalMarks() : 0;

            // Save the user's attempted question, their answer, and the score in the database
            UserQuestion attemptedQuestion = new UserQuestion();
            attemptedQuestion.setUserId(userId);
            attemptedQuestion.setQuestionId(questionId);
            attemptedQuestion.setAnswer(userQuestion.getAnswer());
            attemptedQuestion.setScore(score);

            userQuestionRepository.save(attemptedQuestion);

            // Return a response indicating the status of the update
            return ResponseEntity.ok("Answer updated successfully");
        }

        // Other methods in the controller
    }


}
