package com.github.eduardosackser07.todoApp.controller;

import com.github.eduardosackser07.todoApp.model.ToDo;
import com.github.eduardosackser07.todoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private ToDoService todoService;

    @PostMapping
    public ToDo create(@RequestBody ToDo todo){
        System.out.println("todo recebido: "+todo);

        todoService.save(todo);

        return todo;
    }

    public ToDo findById("{id}"){

    }
}
