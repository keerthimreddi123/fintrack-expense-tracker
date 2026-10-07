package com.aicodelabs.fintrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Expense entity mapped to the `expenses` table.
 *
 * This class is PROVIDED. The fields, constructors, and accessors are complete so the
 * evaluation harness can construct and read Expense instances. You may add JPA refinements,
 * but do not rename fields or change the column mapping.
 */
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "incurred_on", nullable = false)
    private LocalDate incurredOn;

    public Expense() {
    }

    public Expense(Long id, String description, String category, BigDecimal amount, LocalDate incurredOn) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.incurredOn = incurredOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getIncurredOn() {
        return incurredOn;
    }

    public void setIncurredOn(LocalDate incurredOn) {
        this.incurredOn = incurredOn;
    }
}
