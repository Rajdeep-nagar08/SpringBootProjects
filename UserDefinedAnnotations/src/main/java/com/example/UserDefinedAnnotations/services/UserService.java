package com.example.UserDefinedAnnotations.services;


import com.example.UserDefinedAnnotations.daoRepositories.UserDao;
import com.example.UserDefinedAnnotations.modelEntity.UserModel;
import com.example.UserDefinedAnnotations.payload.UserPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserDao userDao;


    @Autowired
    ModelMapper modelMapper;


    public UserPayload saveUser(UserPayload userPayload){

        UserModel userModel= this.modelMapper.map(userPayload,UserModel.class);

        UserModel SavedUser = this.userDao.save(userModel);

        return this.modelMapper.map(SavedUser,UserPayload.class);

    }

    public List<UserPayload> getAllUsers() {

        List<UserModel> users = this.userDao.findAll();

        List<UserPayload> userPayloads=new ArrayList<>();

        for(UserModel u: users){
            userPayloads.add(this.modelMapper.map(u,UserPayload.class));
        }

        return userPayloads;
    }
}
