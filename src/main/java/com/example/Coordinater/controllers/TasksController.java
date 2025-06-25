package com.example.Coordinater.controllers;

import com.example.Coordinater.DAO.TasksDAO;
import com.example.Coordinater.Exceptions.TaskNotFoundException;
import com.example.Coordinater.run.TasksForEngineers;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    private final TasksDAO tasksDAO;

    public TasksController(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @GetMapping
    List<TasksForEngineers> findAll(){
        return tasksDAO.findAll();
    }
//
//    @GetMapping("/{id}")
//    TasksForEngineers findById(@PathVariable Integer id){
//        Optional<TasksForEngineers> tasks = tasksDAO.findById(id);
//        if (tasks.isEmpty()){
//            throw new TaskNotFoundException();
//        }
//        return tasks.get();
//    }
//
//    //post
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    void create(@Valid @RequestBody TasksForEngineers task){
//        tasksDAO.create(task);
//    }
//
//    //put
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{id}")
//    void update(@Valid @RequestBody TasksForEngineers task, @PathVariable Integer id){
//        tasksDAO.update(task, id);
//    }
//
//
//    //delete
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable Integer id){
//        tasksDAO.delete(id);
//    }

}
