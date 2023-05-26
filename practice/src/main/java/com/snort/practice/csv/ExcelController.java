package com.snort.practice.csv;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


//@RestController
@Controller
@CrossOrigin("*")
public class ExcelController {
    @Autowired
    private ExcelService service;

    @Autowired
    private QuestionRepository questionRepository;

    private int size=0;



    @PostMapping("/register")
    public ModelAndView submitForm(@ModelAttribute("question") Question question, @RequestParam("file") MultipartFile file) {
        ModelAndView view = new  ModelAndView();


//		if (Helper.checkExcelFormat(file)) {
//			// true
//			System.out.println("File upload....");
//			//this.service.save(file);
//		}

        //answersheet
        System.out.println(question);
//        question.setQuestionId(101);
        questionRepository.save(question);

        List<Question> answer = this.service.getAllProducts();
        System.out.println("Total answers ::  "+answer.size());
        view.addObject("answer",answer);
        view.setViewName("register_success");
        return view;
    }


    @PostMapping("/sheet/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            this.service.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

    @GetMapping("/sheet")
    public List<Question> getAllProduct() {
        return this.service.getAllProducts();
    }
}