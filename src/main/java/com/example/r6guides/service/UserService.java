package com.example.r6guides.service;

import com.example.r6guides.DTO.LoginRequest;
import com.example.r6guides.DTO.LoginResponse;
import com.example.r6guides.models.User;
import com.example.r6guides.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = "generated-jwt-token";
            return new LoginResponse(user.getUsername(), user.getEmail(), token, user.getRoleType());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
