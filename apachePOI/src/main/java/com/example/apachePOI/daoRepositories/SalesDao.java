package com.example.apachePOI.daoRepositories;

import com.example.apachePOI.modelEntity.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesDao extends JpaRepository<SalesModel,Integer> {


}
