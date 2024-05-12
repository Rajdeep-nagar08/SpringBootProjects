package com.example.emailverification.daoRepositories;

import com.example.emailverification.modelEntity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<UserModel,Integer> {

    Optional<UserModel> findByEmail(String email);
}
