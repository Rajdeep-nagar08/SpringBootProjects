package com.example.start_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
// to map data variables with table columns
@Data

// as data is coming from lombock, if we not use lombock, then for
// each each variable we needs to define getter and setter method

public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY, or SEQUENCE (check it and conform)
    private Integer id;
    private String questionTitle;
    // questionTitle===question_title (JPA will take care of it)
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficulty_level;
    private String category;

}
