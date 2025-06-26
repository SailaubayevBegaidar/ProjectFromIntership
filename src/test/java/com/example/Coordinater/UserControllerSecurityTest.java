package com.example.Coordinater;

import com.example.Coordinater.controllers.UserController;
import com.example.Coordinater.entity.Role;
import com.example.Coordinater.entity.User;
import com.example.Coordinater.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    private User createTestUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(Role.ADMIN);
        return user;
    }

    @Test
    void viewUsersPage() {
        // Подготовка
        List<User> users = Collections.singletonList(createTestUser());
        when(userRepository.findAll()).thenReturn(users);

        // Выполнение
        String viewName = userController.viewUsersPage(model);

        // Проверка
        assertEquals("users", viewName);
        verify(model).addAttribute("users", users);
    }


    @Test
    void saveUser() {
        // Подготовка
        User user = createTestUser();

        // Выполнение
        String redirect = userController.saveUser(user);

        // Проверка
        assertEquals("redirect:/api/users", redirect);
        verify(userRepository).create(user);
    }
}