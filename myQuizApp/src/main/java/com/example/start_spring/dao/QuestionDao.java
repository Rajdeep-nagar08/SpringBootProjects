package com.example.start_spring.dao;

import com.example.start_spring.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

// jpa repository ask two things, <TableName, type of primary key>
// Basicaly, table name== class name of model
// In my case, table name (model class name)=> Qusetion
// type of primary key = Integer

public interface QuestionDao extends JpaRepository<Question, Integer> {

    // while fetching data by category from model, as "category" is not a primary key so we
    // need to mention here


    List<Question> findByCategory(String category);

    /*
    as "category" is a a columname of table/model "Question"
    so we don't needs to write HQL(Hyberanate Query Language) query for it, JPA is smart enough to process
    it

    But if we needs to fetch data in a complex way and needs to do lot
    of customization then we needs to use HQL or JPQL (JPA Query language)

    (HQL==JPQL==SQL)

    while working with JPA dependency, we prefer HQL or JPQL

     */


    //  to get question by difficulty_level

    @Query("SELECT q FROM Question q WHERE q.difficulty_level = :difficulty_level")
    List<Question> findByDifficultyLevel(String difficulty_level);

    @Query(value="select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
