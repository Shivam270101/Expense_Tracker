package com.shivam.expense_tracker.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 // Used during Login
    Optional<User> findByEmail(String email);

    // Used during Registration
    boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);

    List<User> findByFullNameContainingIgnoreCase(String fullName);

}