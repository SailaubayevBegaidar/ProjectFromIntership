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
    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "task-edit";
        } else {
            return "error/404";
        }
    }
    @PostMapping("/tasks/update")
    public String updateTask(@ModelAttribute("task") Task task) {
        taskRepository.update(task, task.getId());
        return "redirect:/api/tasks";
    }
    @GetMapping("/tasks/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-create";
    }

    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskRepository.create(task);
        return "redirect:/api/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskRepository.delete(id);
        return "redirect:/api/tasks";
    }


}

