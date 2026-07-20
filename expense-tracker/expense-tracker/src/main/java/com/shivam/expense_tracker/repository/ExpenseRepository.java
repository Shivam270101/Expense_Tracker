package com.shivam.expense_tracker.repository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shivam.expense_tracker.dto.dashboard.CategoryChartResponse;
import com.shivam.expense_tracker.dto.dashboard.MonthlyTrendResponse;
import com.shivam.expense_tracker.entity.Category;
import com.shivam.expense_tracker.entity.Expense;
import com.shivam.expense_tracker.entity.User;
import com.shivam.expense_tracker.enums.PaymentMode;



@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    // All expenses of a user
    List<Expense> findByUser(User user);

    // Expenses between dates
    List<Expense> findByUserAndExpenseDateBetween(
            User user,
            LocalDate startDate,
            LocalDate endDate);

    // Expenses by category
    List<Expense> findByUserAndCategory(
            User user,
            Category category);

    // Used while deleting category
    boolean existsByCategory(Category category);
    
    List<Expense> findByUserAndPaymentMode(
            User user,
            PaymentMode paymentMode);
    
    List<Expense> findByUserAndTitleContainingIgnoreCase(
            User user,
            String title);
    
    List<Expense> findByAmountGreaterThan(BigDecimal amount);
    
    List<Expense> findByAmountLessThan(BigDecimal amount);

    List<Expense> findByPaymentMode(PaymentMode paymentMode);
    
//    List<Expense> findByUserAndCategory(User user, Category category);
    
    List<Expense> findByAmountBetween(
            BigDecimal min,
            BigDecimal max);
    
    @Query("""
    		SELECT COALESCE(SUM(e.amount),0)
    		FROM Expense e
    		WHERE e.user.id=:userId
    		""")
    		BigDecimal getTotalExpense(Long userId);
    
    @Query("""
    		SELECT COALESCE(SUM(e.amount),0)
    		FROM Expense e
    		WHERE e.user.id=:userId
    		AND MONTH(e.expenseDate)=:month
    		AND YEAR(e.expenseDate)=:year
    		""")
    		BigDecimal getMonthlyExpense(
    		        Long userId,
    		        Integer month,
    		        Integer year);
    
    @Query("""
    		SELECT new com.shivam.expense_tracker.dto.dashboard.CategoryChartResponse(
    		c.name,
    		SUM(e.amount))
    		FROM Expense e
    		JOIN e.category c
    		WHERE e.user.id=:userId
    		AND MONTH(e.expenseDate) = :month
    		AND YEAR(e.expenseDate) = :year
    		GROUP BY c.name
    		""")
    		List<CategoryChartResponse> getCategoryWiseExpense(Long userId, Integer month, Integer year);
   
    @Query("""
    		SELECT new com.shivam.expense_tracker.dto.dashboard.MonthlyTrendResponse(
    		MONTH(e.expenseDate),
    		SUM(e.amount))
    		FROM Expense e
    		WHERE e.user.id=:userId
    		GROUP BY MONTH(e.expenseDate)
    		ORDER BY MONTH(e.expenseDate)
    		""")
    		List<MonthlyTrendResponse> getMonthlyTrend(Long userId, Integer year);
    
	Long countByUserId(Long userId);

	@Query("""
		    SELECT COALESCE(SUM(e.amount),0)
		    FROM Expense e
		    WHERE e.user.id = :userId
		      AND MONTH(e.expenseDate) = :month
		      AND YEAR(e.expenseDate) = :year
		""")
		BigDecimal getTotalExpenseByMonth(
		        @Param("userId") Long userId,
		        @Param("month") Integer integer,
		        @Param("year") Integer integer2); 
    

}
