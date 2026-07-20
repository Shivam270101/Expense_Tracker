package com.shivam.expense_tracker.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shivam.expense_tracker.dto.auth.LoginRequest;
import com.shivam.expense_tracker.dto.auth.LoginResponse;
import com.shivam.expense_tracker.dto.auth.RegisterRequest;
import com.shivam.expense_tracker.dto.auth.UserResponse;
import com.shivam.expense_tracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register New User
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = userService.register(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

}