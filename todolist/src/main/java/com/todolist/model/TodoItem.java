package com.todolist.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@jakarta.persistence.Entity
public class TodoItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    private boolean done;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date duedate;


    public TodoItem() {
    }
    
    public TodoItem(Long id,String title, boolean done,Date duedate) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.duedate = duedate;
    }

    public boolean isDone() {
        return done;
    }

}
