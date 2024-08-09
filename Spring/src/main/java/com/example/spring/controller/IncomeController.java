package com.example.spring.controller;

import com.example.spring.model.Income;
import com.example.spring.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<?> addIncome(@RequestBody Income income) {
        // Validate the income object (can be moved to a validation service)
        if (income.getTitle() == null || income.getCategory() == null || income.getDescription() == null || income.getDate() == null) {
            return ResponseEntity.badRequest().body("All fields are required!");
        }
        if (income.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Amount must be a positive number!");
        }

        incomeService.addIncome(income);
        return ResponseEntity.ok("Income Added");
    }

    @GetMapping("/all")
    public List<Income> getIncomes() {
        return incomeService.getAllIncomes();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable String id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok("Income Deleted");
    }
}