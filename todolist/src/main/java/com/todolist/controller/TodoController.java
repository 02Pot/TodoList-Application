package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.model.TodoItem;
import com.todolist.repo.TodoRepo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<TodoItem> findAll(){
        return todoRepo.findAll();
    }

    @PostMapping
    public TodoItem save(@Valid @NotNull @RequestBody TodoItem todoItem){
        return todoRepo.save(todoItem);
    }

    @PutMapping(value = "/{id}")
    public TodoItem update(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        TodoItem existingItem = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        existingItem.setTitle(todoItem.getTitle());
        existingItem.setDone(todoItem.isDone());
        return todoRepo.save(existingItem);
    }
    

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        todoRepo.deleteById(id);
    }
}
