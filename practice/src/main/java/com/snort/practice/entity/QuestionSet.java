/*
package com.snort.practice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class QuestionSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "questionSets")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "questionSet")
    private List<Question> questions = new ArrayList<>();

}
*/
