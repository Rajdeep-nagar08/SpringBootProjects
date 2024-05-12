package com.example.UserDefinedAnnotations.controller;


import com.example.UserDefinedAnnotations.payload.UserPayload;
import com.example.UserDefinedAnnotations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/add")

    public ResponseEntity<UserPayload> saveUser(@RequestBody UserPayload userPayload){

        return new ResponseEntity<>(userService.saveUser(userPayload), HttpStatus.CREATED);

    }


    @GetMapping("/getAll")

    public ResponseEntity<List<UserPayload>> getAllUsers(){

        return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);

    }
}
