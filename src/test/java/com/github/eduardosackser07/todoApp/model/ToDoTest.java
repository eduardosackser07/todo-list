package com.github.eduardosackser07.todoApp.model;

import com.github.eduardosackser07.todoApp.repository.ToDoRepository;
import com.github.eduardosackser07.todoApp.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToDoTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoService toDoService;

    @Test
    void createTodo(){
        ToDo todo = new ToDo();

        todo.setTitle("teste 2");
        todo.setDescription("testano parte 2");
        todo.setCompleted(false);

        toDoService.save(todo);
    }
}