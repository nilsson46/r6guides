package com.example.r6guides.controller;

import com.example.r6guides.DTO.LoginRequest;
import com.example.r6guides.DTO.LoginResponse;
import com.example.r6guides.models.RoleType;
import com.example.r6guides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/create")
    public void createUser(@RequestParam String email, @RequestParam String password, @RequestParam String username, @RequestParam RoleType roleType) {
        userService.createUser(email, password, username,roleType );
    }
}
