package com.aicodelabs.fintrack.service;

import com.aicodelabs.fintrack.exception.ExpenseNotFoundException;
import com.aicodelabs.fintrack.model.Expense;
import com.aicodelabs.fintrack.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense create(Expense expense) {
        validateExpense(expense);
        return repository.save(expense);
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public Expense update(Long id, Expense expense) {
        Expense existing = findById(id);

        validateExpense(expense);

        existing.setDescription(expense.getDescription());
        existing.setCategory(expense.getCategory());
        existing.setAmount(expense.getAmount());
        existing.setIncurredOn(expense.getIncurredOn());

        return repository.save(existing);
    }

    public void delete(Long id) {
        Expense existing = findById(id);
        repository.delete(existing);
    }
    public void deleteAll() {
    repository.deleteAll();
}
    public List<Expense> findByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    public BigDecimal totalAmount() {
        return repository.findAll()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense is required");
        }

        if (expense.getDescription() == null ||
                expense.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (expense.getCategory() == null ||
                expense.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }

        if (expense.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }

        if (expense.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (expense.getIncurredOn() == null) {
            throw new IllegalArgumentException("Incurred date is required");
        }
    }
}