package com.example.start_spring.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity

@Data

// for each quiz we can have multiple questions and same question can be a part of multiple quizes
// its like many to many relationship between quiz and questions

// it will create 2 tables
// one is simple quiz (with quiz id and title)
// and second table will be quiz_questions (with quiz id and question id's)

public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String title;

    @ManyToMany
    private List<Question> questions;

}
