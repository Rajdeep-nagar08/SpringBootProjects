package com.example.emailverification.controller;


import com.example.emailverification.payload.LoginPayload;
import com.example.emailverification.payload.RegisterPayload;
import com.example.emailverification.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping("/register")

    public ResponseEntity<String> register(@RequestBody RegisterPayload registerPayload){

        return new ResponseEntity<>(userService.register(registerPayload), HttpStatus.CREATED);


    }

    @PutMapping("/verify-account")

    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String otp){

        return new ResponseEntity<>(userService.verifyAccount(email,otp),HttpStatus.OK);

    }


    @PutMapping("/regenerate-otp")

    public ResponseEntity<String> regenerateOtp(@RequestParam String email){
        return new ResponseEntity<>(userService.regenerateOtp(email),HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginPayload loginPayload){
        return new ResponseEntity<>(userService.userLogin(loginPayload),HttpStatus.OK);
    }

}
