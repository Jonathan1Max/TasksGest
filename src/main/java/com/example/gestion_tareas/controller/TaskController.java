package com.example.gestion_tareas.controller;

import com.example.gestion_tareas.model.Task;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    // Lista que contiene las tareas
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    @GetMapping("/tasks")
    public String showTasks(Model model) {
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores en el formulario, muestra la página con los errores
            model.addAttribute("tasks", tasks);
            return "tasks";
        }

        // Si no hay errores, añade la nueva tarea a la lista
        task.setId(nextId++);
        tasks.add(task);

        // Redirige a la lista de tareas para evitar doble envío de formularios
        return "redirect:/tasks";
    }
}