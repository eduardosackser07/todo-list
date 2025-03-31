package com.github.eduardosackser07.todoApp.controller;

import com.github.eduardosackser07.todoApp.model.ToDo;
import com.github.eduardosackser07.todoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ToDo todo = todoService.findById(id);



        if(todo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ToDo com ID " + id + " não foi encontrado");
        }

        return ResponseEntity.ok(todo);
    }

    @GetMapping
    public List<ToDo> findAll(){

        return todoService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ToDo todo){

        if(todo == null || id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID or ToDo data");
        }

        todo.setId(id);
        todoService.save(todo);

        return ResponseEntity.status(HttpStatus.CREATED).body("ToDo updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);

    }

    @GetMapping(params = "completed")
    public List<ToDo> findByCompleted(@RequestParam boolean completed){
        return todoService.findByCompleted(completed);
    }


}
