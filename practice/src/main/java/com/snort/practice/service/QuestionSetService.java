/*
package com.snort.practice.service;


import com.snort.practice.Repository.QuestionSetRepository;
import com.snort.practice.Repository.StudentRepository;
import com.snort.practice.entity.QuestionSet;
import com.snort.practice.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSetService {
    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void assignQuestionSetToStudents(Long questionSetId, List<Long> studentIds) {
        QuestionSet questionSet = questionSetRepository.findById(questionSetId).orElseThrow(() -> new RuntimeException());
        List<Student> students = studentRepository.findAllById(studentIds);

        for (Student student : students) {
            student.getQuestionSets().add(questionSet);
        }

        studentRepository.saveAll(students);
    }
    public List<QuestionSet> getAllQuestionSets() {
        return questionSetRepository.findAll();
    }
}
*/
