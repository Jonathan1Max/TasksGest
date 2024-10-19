package com.example.gestion_tareas.controller;

import com.example.gestion_tareas.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@Valid @RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return ResponseEntity.ok("Tarea creada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        tasks.removeIf(task -> task.getId() == id);
        return ResponseEntity.ok("Tarea eliminada exitosamente");
    }
}