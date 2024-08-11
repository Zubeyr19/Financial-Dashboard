package com.example.spring.controller;

import com.example.spring.model.Expense;
import com.example.spring.services.LoggingService;
import com.example.spring.services.ExpenseService;
import com.example.spring.factory.ExpenseServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseServiceFactory expenseServiceFactory, LoggingService loggingService) {
        this.expenseService = expenseServiceFactory.createExpenseService("basic");
        this.expenseService.registerObserver(loggingService);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        // Validate the expense object
        if (expense.getTitle() == null || expense.getCategory() == null || expense.getDescription() == null || expense.getDate() == null) {
            return ResponseEntity.badRequest().body("All fields are required!");
        }
        if (expense.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Amount must be a positive number!");
        }

        // Use the expense service to add the expense, triggering any observers
        expenseService.addExpense(expense);

        return ResponseEntity.ok("Expense Added");
    }

    @GetMapping("/all")
    public List<Expense> getExpenses() {
        // Use the expense service to retrieve all expenses
        return expenseService.getAllExpenses();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable String id) {
        // Use the expense service to delete the expense, triggering any observers
        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense Deleted");
    }
}
