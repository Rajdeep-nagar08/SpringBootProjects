package com.example.todoApp.Service.ServiceImpl;

import com.example.todoApp.Entity.TodoEntity;
import com.example.todoApp.Payload.TodoPayload;
import com.example.todoApp.Repo.TodoRepo;
import com.example.todoApp.Service.TodoService;
import com.example.todoApp.exceptionsHandlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepo todoRepo;
    @Override
    public TodoEntity save(TodoEntity todoEntity) {
        return todoRepo.save(todoEntity);
    }

    @Override
    public TodoEntity getTodoById(Integer id) {
        return this.todoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo","id",id));
    }

    @Override
    public List<TodoEntity> getAllTodos() {
        return this.todoRepo.findAll();
    }

    @Override
    public TodoEntity completeTodo(TodoEntity todoEntity) {


        TodoEntity todo = this.todoRepo.findById(todoEntity.getId()).orElseThrow(()->new ResourceNotFoundException("Todo","todo id",todoEntity.getId()));

        todo.setCompleted(false);

        return this.todoRepo.save(todoEntity);

    }

}
