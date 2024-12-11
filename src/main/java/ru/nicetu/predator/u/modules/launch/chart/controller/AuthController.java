package ru.nicetu.predator.u.modules.launch.chart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nicetu.predator.u.modules.launch.chart.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public void login(
            @RequestParam("username") String username, @RequestParam("password") String password
    ) {
        authService.auth(username, password);
    }
}
