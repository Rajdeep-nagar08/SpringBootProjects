package com.example.start_spring.dao;

import com.example.start_spring.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
