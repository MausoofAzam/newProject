package com.snort.practice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_question")
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "question_id")
    private Long questionId;

    private String answer;
    private int score;

    // any other fields relevant to the assignment of questions to users

    // constructors, getters, and setters
}
