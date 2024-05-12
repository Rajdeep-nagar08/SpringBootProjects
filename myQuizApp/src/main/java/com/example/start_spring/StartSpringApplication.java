package com.example.start_spring;

import com.example.start_spring.controller.QuestionController;
import com.example.start_spring.dao.QuestionDao;
import com.example.start_spring.model.Question;
import com.example.start_spring.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@SpringBootApplication
public class StartSpringApplication {

    public static void main(String[] args) {


//        SpringApplication.run(StartSpringApplication.class, args);


        ApplicationContext context= SpringApplication.run(StartSpringApplication.class, args);

        QuestionDao qs = context.getBean(QuestionDao.class);
        // creating object QuestionDa

//        Question q1=new Question(); // creating object of model
//
//        q1.setCategory("Python");
//        q1.setOption1("1");
//        q1.setOption2("4");
//        q1.setOption3("6");
//        q1.setOption4("9");
//        q1.setDifficulty_level("Easy");
//        q1.setQuestionTitle("Size of INT in C++ ?");
//        q1.setRightAnswer("4");
//
//        // save and return
//        Question newQuestion= qs.save(q1);  // saveAll to save multiple objects at once
//        System.out.println("New Question "+newQuestion);
//        System.out.println("Done!!!");



        // Update part (updating question of id=20
//           Optional <Question> optional= qs.findById(20);
//           Question q20= optional.get();
//           q20.setQuestionTitle("which operator is used to concatenate two strings in kotlin");
//           Question new20 =qs.save(q20);
//           System.out.println(new20);



        // findAll(), to get all data(via Iterable),

//        Iterable<Question> itr= qs.findAll();

        // moving through iterator using lambda function
//        itr.forEach(Question-> System.out.println(Question));


        // delete the elements using delete()








    }

}
