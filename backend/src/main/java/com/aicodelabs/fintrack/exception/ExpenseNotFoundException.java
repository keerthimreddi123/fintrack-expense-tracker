package com.aicodelabs.fintrack.exception;

/**
 * Thrown when an expense id does not exist.
 *
 * TODO: ensure this maps to HTTP 404. You can annotate it with
 * {@code @ResponseStatus(HttpStatus.NOT_FOUND)} or handle it in the controller.
 */
public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Long id) {
        super("Expense not found: " + id);
    }
}
