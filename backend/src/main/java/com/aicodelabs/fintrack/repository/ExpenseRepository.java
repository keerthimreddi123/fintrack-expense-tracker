package com.aicodelabs.fintrack.repository;

import com.aicodelabs.fintrack.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for {@link Expense}.
 *
 * TODO: declare the derived query methods below. Spring Data implements them at runtime
 * from the method names — you do not write bodies.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // TODO: case-insensitive lookup by category.
    List<Expense> findByCategoryIgnoreCase(String category);

    // TODO: inclusive date-range lookup.
    List<Expense> findByIncurredOnBetween(LocalDate from, LocalDate to);
}
