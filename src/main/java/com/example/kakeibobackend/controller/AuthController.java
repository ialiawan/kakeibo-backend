package com.example.kakeibobackend.controller;

import com.example.kakeibobackend.dto.AuthRequest;
import com.example.kakeibobackend.entity.User;
import com.example.kakeibobackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody AuthRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return "Login Successful";
    }
}