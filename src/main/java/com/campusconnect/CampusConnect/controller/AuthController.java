package com.campusconnect.CampusConnect.controller;

import com.campusconnect.CampusConnect.dto.LoginDTO;
import com.campusconnect.CampusConnect.dto.UniversityDTO;
import com.campusconnect.CampusConnect.dto.UserDTO;
import com.campusconnect.CampusConnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // User signup
    @PostMapping("/user/signup")
    public ResponseEntity<String> userSignUp(@Valid @RequestBody UserDTO userData) {
        authService.userSignUp(userData);
        return ResponseEntity.status(201).body("User successfully registered");
    }

    // User login
    @PostMapping("/user/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody LoginDTO userData) {
        boolean isLoggedIn = authService.userLogin(userData);
        if (isLoggedIn) {
            return ResponseEntity.status(202).body("User successfully logged in");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // University login
    @PostMapping("/university/login")
    public ResponseEntity<String> universityLogin(@Valid @RequestBody LoginDTO universityData) {
        boolean isLoggedIn = authService.universityLogin(universityData);
        if (isLoggedIn) {
            return ResponseEntity.status(202).body("University successfully logged in");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // University signup
    @PostMapping("/university/signup")
    public ResponseEntity<String> universitySignUp(@Valid @RequestBody UniversityDTO universityData) {
        authService.universitySignUp(universityData);
        return ResponseEntity.status(201).body("University successfully registered");
    }
}