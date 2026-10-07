package com.aicodelabs.fintrack.service;

import com.aicodelabs.fintrack.model.Expense;
import com.aicodelabs.fintrack.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Business logic for expenses. Keep all logic here — the controller should only delegate.
 *
 * TODO: implement each method. Throw {@code ExpenseNotFoundException} when an id is missing.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense create(Expense expense) {
        // TODO: persist a new expense (ensure id is null/ignored so it is generated).
        return null;
    }

    public List<Expense> findAll() {
        // TODO: return all expenses.
        return List.of();
    }

    public Expense findById(Long id) {
        // TODO: return the expense or throw ExpenseNotFoundException.
        return null;
    }

    public Expense update(Long id, Expense changes) {
        // TODO: update the existing expense's fields or throw ExpenseNotFoundException.
        return null;
    }

    public void delete(Long id) {
        // TODO: delete by id or throw ExpenseNotFoundException if it does not exist.
    }

    public List<Expense> findByCategory(String category) {
        // TODO: case-insensitive filter by category.
        return List.of();
    }

    public BigDecimal totalAmount() {
        // TODO: sum the amount of all expenses (return BigDecimal.ZERO when empty).
        return BigDecimal.ZERO;
    }
}
