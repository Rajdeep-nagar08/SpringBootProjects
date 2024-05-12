package com.example.todoApp.Repo;

import com.example.todoApp.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoEntity, Integer> {

}
