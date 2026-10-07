package com.aicodelabs.fintrack.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an expense id does not exist.
 *
 * TODO: ensure this maps to HTTP 404. You can annotate it with
 * {@code @ResponseStatus(HttpStatus.NOT_FOUND)} or handle it in the controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Long id) {
        super("Expense not found with id: " + id);
    }
}
