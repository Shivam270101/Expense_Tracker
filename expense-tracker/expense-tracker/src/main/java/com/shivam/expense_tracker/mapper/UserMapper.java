package com.shivam.expense_tracker.mapper;

import org.springframework.stereotype.Component;

import com.shivam.expense_tracker.dto.auth.RegisterRequest;
import com.shivam.expense_tracker.dto.auth.UserResponse;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.enums.Role;

@Component
public class UserMapper {

    /**
     * Convert RegisterRequest DTO to User Entity
     */
    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        return user;
    }
    
    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }

}