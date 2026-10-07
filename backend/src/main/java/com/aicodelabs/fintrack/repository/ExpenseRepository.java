package com.aicodelabs.fintrack.repository;

import com.aicodelabs.fintrack.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategoryIgnoreCase(String category);

    List<Expense> findByIncurredOnBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}