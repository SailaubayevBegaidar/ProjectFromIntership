package com.example.Coordinater;

import com.example.Coordinater.controllers.TaskController;
import com.example.Coordinater.entity.Task;
import com.example.Coordinater.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private TaskController taskController;

    private Task createTestTask() {
        return new Task(1, "Test Task",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                "Test Location", null);
    }

    @Test
    void viewTasksPage() {
        // Подготовка
        List<Task> tasks = Collections.singletonList(createTestTask());
        when(taskRepository.findAll()).thenReturn(tasks);

        // Выполнение
        String viewName = taskController.viewTasksPage(model);

        // Проверка
        assertEquals("tasks", viewName);
        verify(model).addAttribute("tasks", tasks);
    }

    @Test
    void viewTaskFound() {
        // Подготовка
        Task task = createTestTask();
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Выполнение
        String viewName = taskController.viewTask(1, model);

        // Проверка
        assertEquals("task-details", viewName);
        verify(model).addAttribute("task", task);
    }

    @Test
    void viewTaskNotFound() {
        // Подготовка
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // Выполнение
        String viewName = taskController.viewTask(1, model);

        // Проверка
        assertEquals("error/404", viewName);
    }


}