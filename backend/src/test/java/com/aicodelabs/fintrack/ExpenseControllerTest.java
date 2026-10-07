package com.aicodelabs.fintrack;

import com.aicodelabs.fintrack.controller.ExpenseController;
import com.aicodelabs.fintrack.exception.ExpenseNotFoundException;
import com.aicodelabs.fintrack.model.Expense;
import com.aicodelabs.fintrack.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PUBLIC tests — visible to you, safe to run locally with `mvn -Dtest=ExpenseControllerTest test`.
 * These slice-test the controller with a mocked service (no database needed). The hidden
 * EvalTest exercises the full stack against the app's in-memory SQLite database.
 */
@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService service;

    private Expense sample(Long id) {
        return new Expense(id, "Coffee", "Food", new BigDecimal("4.50"), LocalDate.of(2026, 6, 1));
    }

    @Test
    void postShouldReturn201AndBody() throws Exception {
        when(service.create(any(Expense.class))).thenReturn(sample(1L));

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"Coffee","category":"Food","amount":4.50,"incurredOn":"2026-06-01"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Coffee"));
    }

    @Test
    void getAllShouldReturn200AndList() throws Exception {
        when(service.findAll()).thenReturn(List.of(sample(1L)));

        mockMvc.perform(get("/api/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getMissingShouldReturn404() throws Exception {
        when(service.findById(eq(9999L))).thenThrow(new ExpenseNotFoundException(9999L));

        mockMvc.perform(get("/api/expenses/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void putMissingShouldReturn404() throws Exception {
        when(service.update(eq(9999L), any(Expense.class))).thenThrow(new ExpenseNotFoundException(9999L));

        mockMvc.perform(put("/api/expenses/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"X","category":"Y","amount":1.00,"incurredOn":"2026-06-01"}
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void summaryShouldReturnTotal() throws Exception {
        when(service.totalAmount()).thenReturn(new BigDecimal("12.75"));

        mockMvc.perform(get("/api/expenses/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(12.75));
    }
}
