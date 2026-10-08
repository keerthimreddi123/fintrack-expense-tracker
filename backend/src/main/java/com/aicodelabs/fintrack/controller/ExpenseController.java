package com.aicodelabs.fintrack.controller;

import com.aicodelabs.fintrack.model.Expense;
import com.aicodelabs.fintrack.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        return service.create(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return service.update(id, expense);
    }
@DeleteMapping
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteAllExpenses() {
    service.deleteAll();
}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(
            @PathVariable String category) {

        return service.findByCategory(category);
    }

    @GetMapping("/summary")
    public Map<String, BigDecimal> getSummary() {
        return Map.of("total", service.totalAmount());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(
            IllegalArgumentException exception) {

        return ResponseEntity.badRequest()
                .body(Map.of("error", exception.getMessage()));
    }
}