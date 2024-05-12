package com.example.start_spring.service;


import com.example.start_spring.dao.QuestionDao;
import com.example.start_spring.dao.QuizDao;
import com.example.start_spring.model.Question;
import com.example.start_spring.model.QuestionWrapper;
import com.example.start_spring.model.Quiz;
import com.example.start_spring.model.ResponsesByUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuizService {
    @Autowired

    QuizDao quizDao;


    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // fetching list of questions from database using Question Dao object

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        // updating Quiz model (table) using object of quizdao
        // title user se liya h, random questions quiz k liye questions table (original starting table) se liye h
        // sabkuch lene k baad uss sab ko quiz table m daal rhe (database m store kare k liye) jisse bad m bhi particular quiz ka data easly database se fetch kar sake
        // starting m koi quiz table nhi h, starting m JPA autometically quiz table ceate kar dega as per the Quiz model

        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);  // quiz info saved to model (database) using quizDao

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        // Optional is for like data corresponds to this particular id may or may not present

        Optional<Quiz> quiz=quizDao.findById(id);  // fetching Quiz data
        List<Question> questionsFromDB= quiz.get().getQuestions();  // getting Question from quiz data

        // as return type is QuestionWrapper, we needs to manually convert each question to QuestionWrapper

        List<QuestionWrapper> questionForUsers= new ArrayList<>();

        for(Question q: questionsFromDB){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(qw);
        }
        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer QuizId, List<ResponsesByUser> responses) {

        // logic to calculate score

        // first getting all questions of QuizId using quizDao

        // then calculating score by traversing responses

         int score=0;

         Optional<Quiz> quizData= quizDao.findById(QuizId);

         List<Question> quizQuestions= quizData.get().getQuestions();

         int i=0;

         for(ResponsesByUser rp: responses){

           if(rp.getResponse().equals(quizQuestions.get(i).getRightAnswer()))
               score++;

           i++;
         }

       return new ResponseEntity<>(score, HttpStatus.OK);

    }
}
