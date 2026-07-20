package com.shivam.expense_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
//	  boolean existsByUserAndName(User user, String name);
	 
//    boolean existsByName(String name);

//    boolean existsByNameIgnoreCase(String name);
	
	// Get all categories of a user
    List<Category> findByUser(User user);

    // Check duplicate category
    boolean existsByUserAndName(User user, String name);

    // Find category by name
    Optional<Category> findByUserAndName(User user, String name);

    Optional<Category> findByName(String name);

    List<Category> findByNameContainingIgnoreCase(String keyword);
    
    long countByUserId(Long userId);
    
    List<Category> findByUserId(Long userId);

}