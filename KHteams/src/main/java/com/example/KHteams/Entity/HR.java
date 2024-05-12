package com.example.KHteams.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.PrintWriter;

@Data
@Entity

public class HR {

    @Id
    private Integer id;

    private String name;

    private String designation;

    private String description;




}
