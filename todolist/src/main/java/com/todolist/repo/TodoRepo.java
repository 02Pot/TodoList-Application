package com.todolist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.TodoItem;

public interface TodoRepo extends JpaRepository<TodoItem,Long> {
    
}
