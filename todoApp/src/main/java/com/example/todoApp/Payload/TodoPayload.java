package com.example.todoApp.Payload;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoPayload {

    private Integer id;
    private String title;
    private String description;
    private boolean completed;

}
