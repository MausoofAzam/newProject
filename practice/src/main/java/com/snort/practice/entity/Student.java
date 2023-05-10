/*
package com.snort.practice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "student_questionset",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "questionset_id"))
    private List<QuestionSet> questionSets = new ArrayList<>();

    // Getters and setters
}*/
