package com.example.todoApp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="todoApp")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title",length = 100, nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="completed")
    private boolean completed;


}
