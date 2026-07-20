package com.shivam.expense_tracker.service;

import java.util.List;

import com.shivam.expense_tracker.dto.auth.LoginRequest;
import com.shivam.expense_tracker.dto.auth.LoginResponse;
import com.shivam.expense_tracker.dto.auth.RegisterRequest;
import com.shivam.expense_tracker.dto.auth.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, RegisterRequest request);

    void deleteUser(Long id);
}