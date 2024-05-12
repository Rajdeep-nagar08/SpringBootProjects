package com.example.start_spring.controller;


// to accept request

import com.example.start_spring.model.Question;
import com.example.start_spring.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // to accept REST API request
@RequestMapping("question")

public class QuestionController {
    // creating object of question service class, then using
    // that object to get all requests (getAllquestions)
    @Autowired
    QuestionService questionService;

    @GetMapping("AllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

            return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){

        return questionService.getQuestionsByCategory(category);
    }

    /*

   If variable we are passing to path didn't have same name as of path
   then we needs to specify its name with the annotation @PathVariable
    @GetMapping("category/{cat}")
    public List<Question> getQuestionByCategory(@PathVariable("cat") String category){
    }
*/

    @GetMapping("difficulty_level/{diff}")
    public ResponseEntity<List<Question>> getQuestionByDifficulty(@PathVariable("diff") String difficulty_level){
        return questionService.getQuestionByDifficulty(difficulty_level);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){

        return questionService.addQuestion(question);

    }





}
