package com.example.spring.services.impl;

import com.example.spring.model.Expense;
import com.example.spring.repository.ExpenseRepository;
import com.example.spring.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public void processExpense(Expense expense) {
        // Implementation of processExpense
        addExpense(expense);
    }
}