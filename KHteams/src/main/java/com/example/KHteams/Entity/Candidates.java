package com.example.KHteams.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Candidates {



    @Id
    private Integer id;

    private String name;

    private Date dateOfBirth;

    private String collegeName;

    private String highestEducation;

    private Integer Yoe;

    private Date collegePassingYear;



}
