package com.example.start_spring.controller;


import com.example.start_spring.model.ResponsesByUser;
import com.example.start_spring.model.Question;
import com.example.start_spring.model.QuestionWrapper;
import com.example.start_spring.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")

public class QuizController {

    @Autowired
  QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title
            ){

        return quizService.createQuiz(category, numQ, title);

    }


    @GetMapping("get/{id}")

    // In Quiz we cann't send complete Question object to the UI as that also have answers of that questons
    // so we can create Wrapper (e.g payload we are returning) that have all the columns except answer
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){

        return quizService.getQuizQuestions(id);

    }


    // http://localhost:8080/quiz/submit/3  (BODY => for each question of quiz( question id, response ))
    // whatever JSON data coming in the body, we handle that data in a List of object so we needs an object of a class, and that class == JSON BODY
    @PostMapping("submit/{QuizId}")
    // returning score on the basis of correct answers
    public ResponseEntity<Integer> checkQuizResponse(@PathVariable Integer QuizId, @RequestBody List<ResponsesByUser> responses){

        return quizService.calculateResult(QuizId, responses);

    }
}
