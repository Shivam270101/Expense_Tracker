package com.shivam.expense_tracker.repository;


import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shivam.expense_tracker.entity.Budget;
import com.shivam.expense_tracker.entity.User;



@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

	// Current month's budget
    Optional<Budget> findByUserAndMonthAndYear(
            User user,
            Integer month,
            Integer year);

    // All budgets of a user
    List<Budget> findByUser(User user);

    // Check duplicate budget
    boolean existsByUserAndMonthAndYear(
            User user,
            Integer month,
            Integer year);
    
    List<Budget> findByUserId(Long userId);
    

    List<Budget> findByYear(Integer year);

    List<Budget> findByMonth(Integer month);

    List<Budget> findByMonthAndYear(
            Integer month,
            Integer year);

    List<Budget> findByBudgetAmountGreaterThan(BigDecimal amount);

    List<Budget> findByRemainingAmountLessThan(BigDecimal amount);
    
    @Query("""
    		SELECT COALESCE(SUM(b.budgetAmount),0)
    		FROM Budget b
    		WHERE b.user.id = :userId
    		""")
    		BigDecimal getTotalBudget(Long userId);


    @Query("""
    		SELECT b.budgetAmount
    		FROM Budget b
    		WHERE b.user.id = :userId
    		AND b.month = :month
    		AND b.year = :year
    		""")
    		BigDecimal getBudgetByMonth(
    		        Long userId,
    		        Integer month,
    		        Integer year);
    @Query("""
    		SELECT COALESCE(SUM(b.budgetAmount),0)
    		FROM Budget b
    		WHERE b.user=:user
    		AND b.month BETWEEN :startMonth AND :endMonth
    		AND b.year=:year
    		""")
    		BigDecimal getBudgetForReport(
    		        @Param("user") User user,
    		        @Param("startMonth") Integer startMonth,
    		        @Param("endMonth") Integer endMonth,
    		        @Param("year") Integer year);
    
    
    List<Budget> findByUserAndYear(
            User user,
            Integer year
    );

    List<Budget> findByUserAndYearAndMonthIn(
            User user,
            Integer year,
            List<Integer> months
    );
}
