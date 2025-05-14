package com.example.r6guides.service;

import com.example.r6guides.DTO.LoginRequest;
import com.example.r6guides.DTO.LoginResponse;
import com.example.r6guides.exceptions.UserNotFoundException;
import com.example.r6guides.models.Role;
import com.example.r6guides.models.RoleType;
import com.example.r6guides.models.User;
import com.example.r6guides.repository.RoleRepository;
import com.example.r6guides.repository.UserRepository;
import com.example.r6guides.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        // is it okey to send "user not found" message?
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
            String token = jwtUtil.generateToken(user.getUsername());
            return new LoginResponse(user.getUsername(), user.getEmail(), token, user.getRoleType());
        } else {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            userRepository.save(user);
            throw new RuntimeException("Invalid credentials");
        }
    }
    public void createUser(String email, String password, String username, RoleType roleType) {
        Role role = roleRepository.findByRoleType(roleType).orElseThrow(() -> new RuntimeException("Role not found"));
        if(userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setRole(role); // Ensure role is set
        userRepository.save(user);
    }
}
