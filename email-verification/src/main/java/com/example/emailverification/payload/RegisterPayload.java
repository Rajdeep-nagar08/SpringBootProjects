package com.example.emailverification.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPayload {

    private String name;

    private String email;

    private String password;


}
