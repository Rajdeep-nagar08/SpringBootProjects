package com.example.UserDefinedAnnotations.modelEntity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private  String name;
    private String email;
    private String password;

}
