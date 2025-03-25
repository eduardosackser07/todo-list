package com.github.eduardosackser07.todoApp.repository;

import com.github.eduardosackser07.todoApp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
