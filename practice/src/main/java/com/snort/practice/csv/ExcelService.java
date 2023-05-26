package com.snort.practice.csv;


import java.io.IOException;
import java.util.List;

import com.snort.practice.Repository.QuestionRepository;
import com.snort.practice.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ExcelService {

    @Autowired
    private QuestionRepository repository;

    public void save(MultipartFile file) {

        try {
            List<Question> products = Helper.convertExcelToListOfQuestions(file.getInputStream());
            this.repository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public List<Question> getAllProducts() {
        return (List<Question>) this.repository.findAll();
    }
}