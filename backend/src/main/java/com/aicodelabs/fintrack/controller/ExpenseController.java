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
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


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
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        Expense created = service.create(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return ResponseEntity.ok(service.update(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> findByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(service.findByCategory(category));
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, BigDecimal>> summary() {
        return ResponseEntity.ok(
                Map.of("total", service.totalAmount())
        );
    }
}
