package com.example.todoApp.Controllers;
import com.example.todoApp.Entity.TodoEntity;
import com.example.todoApp.Payload.TodoPayload;
import com.example.todoApp.Service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TodoController {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TodoService todoService;


    @PostMapping("/todo/add")
    private ResponseEntity<TodoPayload> addTodo(@RequestBody TodoPayload todoPayload){

        TodoEntity todoEntity = todoService.save(modelMapper.map(todoPayload,TodoEntity.class));

        return new ResponseEntity<>(modelMapper.map(todoEntity,TodoPayload.class),HttpStatus.CREATED);

    }

    @GetMapping("todo/{id}")
    private ResponseEntity<TodoPayload> getTodo(@PathVariable Integer id){

        TodoEntity todoEntity = todoService.getTodoById(id);

        return new ResponseEntity<>(modelMapper.map(todoEntity,TodoPayload.class),HttpStatus.OK);
    }

    @GetMapping("todo/getAll")
    private ResponseEntity<List<TodoPayload>> getAllTodos(){

       List<TodoEntity> todos = this.todoService.getAllTodos();

       return new ResponseEntity<>(todos.stream().map(t->modelMapper.map(t,TodoPayload.class)).collect(Collectors.toList()), HttpStatus.OK);
    }


    @PutMapping("todo/completed")
    private ResponseEntity<?> todoCompleted(@RequestBody TodoPayload todoPayload){

        TodoEntity todoEntity= this.todoService.completeTodo(modelMapper.map(todoPayload,TodoEntity.class));

        return new ResponseEntity<>(modelMapper.map(todoEntity,TodoPayload.class),HttpStatus.OK);

    }


}
