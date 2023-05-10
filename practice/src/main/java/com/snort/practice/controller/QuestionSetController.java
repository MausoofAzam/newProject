/*
package com.snort.practice.controller;

import com.snort.practice.entity.QuestionSet;
import com.snort.practice.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestionSetController {

    @Autowired
    private QuestionSetService questionSetService;

    @GetMapping("/questionsets")
    public String getAllQuestionSets(Model model) {
        List<QuestionSet> questionSets = questionSetService.getAllQuestionSets();
        model.addAttribute("questionSets", questionSets);
        return "questionSetList"; // return the name of the Thymeleaf template to be used
    }
}*/
