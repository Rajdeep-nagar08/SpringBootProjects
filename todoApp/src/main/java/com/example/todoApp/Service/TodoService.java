package com.example.todoApp.Service;

import com.example.todoApp.Entity.TodoEntity;
import com.example.todoApp.Payload.TodoPayload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TodoService {

    TodoEntity save(TodoEntity todoEntity);

    TodoEntity getTodoById(Integer id);

    List<TodoEntity> getAllTodos();

    TodoEntity completeTodo(TodoEntity todoEntity);
}
