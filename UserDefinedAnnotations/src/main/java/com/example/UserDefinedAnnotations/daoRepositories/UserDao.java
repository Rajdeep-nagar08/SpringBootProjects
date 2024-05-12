package com.example.UserDefinedAnnotations.daoRepositories;

import com.example.UserDefinedAnnotations.modelEntity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<UserModel,Integer> {


    List<UserModel> findByEmail(String email);

}
