package com.example.start_spring.service;

import com.example.start_spring.dao.QuestionDao;
import com.example.start_spring.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service


public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        // using by-default in-build function
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        // as category s not a primary key, so there is no any direct in-build method
        // to findAll for "category", so handle such case we needs to tell DAO explicitly
        // that we need data by "category"
        // as finding data by category, and category is not primary key
        // so using user defined function (its function description in dao layer)

  try {
      return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
  }
  catch(Exception e){
      e.printStackTrace();
  }

  return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByDifficulty(String difficulty_level) {

        try {
            return new ResponseEntity<>(questionDao.findByDifficultyLevel(difficulty_level), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {

         try {
             questionDao.save(question);

             return new ResponseEntity<>("Question Saved !!!", HttpStatus.OK);
         }
         catch(Exception e){
             e.printStackTrace();
         }

        return new ResponseEntity<>("SOMETHING GOT WRONG !!!", HttpStatus.BAD_REQUEST);

    }

}
