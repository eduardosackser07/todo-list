package com.github.eduardosackser07.todoApp.service;

import com.github.eduardosackser07.todoApp.model.ToDo;
import com.github.eduardosackser07.todoApp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo save(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public void delete(ToDo todo){
        toDoRepository.delete(todo);
    }

    public void update(Long id, ToDo todo){
        todo.setId(id);
    }

    public ToDo findById(Long id){
        Optional<ToDo> todo = toDoRepository.findById(id);

        return todo.orElse(null);
    }

    public List<ToDo> findAll(){
        List<ToDo> list = toDoRepository.findAll();

        return list;
    }

    public void delete(Long id){
        toDoRepository.deleteById(id);
    }


}