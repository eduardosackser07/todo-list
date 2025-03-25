package com.github.eduardosackser07.todoApp.service;

import com.github.eduardosackser07.todoApp.model.ToDo;
import com.github.eduardosackser07.todoApp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo save(ToDo todo) {
        return toDoRepository.save(todo);  // Retorna o objeto salvo
    }

    public void delete(ToDo todo){
        toDoRepository.delete(todo);
    }

    public void update(Long id, ToDo todo){

        todo.setId(id);
    }


}