package com.example.Coordinater.controllers;

import com.example.Coordinater.entity.Task;
import com.example.Coordinater.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public String viewTasksPage(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String viewTask(@PathVariable("id") int id, Model model) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            model.addAttribute("task", taskOpt.get());
            return "task-details";
        } else {
            return "error/404";
        }
    }

}

