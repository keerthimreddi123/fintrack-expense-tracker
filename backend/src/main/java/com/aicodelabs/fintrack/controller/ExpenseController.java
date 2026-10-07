package com.aicodelabs.fintrack.controller;

import com.aicodelabs.fintrack.model.Expense;
import com.aicodelabs.fintrack.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * REST API for expenses. Delegate everything to {@link ExpenseService}; no business logic here.
 *
 * TODO: implement each endpoint body. The CrossOrigin allows the React dev server / E2E
 * runner to call the API directly when not proxied.
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense createExpense(@RequestBody Expense expense) {
        // TODO
        return null;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        // TODO
        return List.of();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {
        // TODO
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {
        // TODO
        return List.of();
    }

    @GetMapping("/summary")
    public Map<String, BigDecimal> getSummary() {
        // TODO: return a map with key "total".
        return Map.of("total", BigDecimal.ZERO);
    }
}
