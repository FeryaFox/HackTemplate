package ru.feryafox.hacktemplate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feryafox.hacktemplate.models.responses.Task.GetAllTasksResponce;
import ru.feryafox.hacktemplate.models.responses.UserWithIdResponce;
import ru.feryafox.hacktemplate.services.UserService;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.List;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserWithIdResponce> getAllTasks(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getAllUsers();
    }
}
