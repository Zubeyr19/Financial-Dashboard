package com.example.spring.services;

import com.example.spring.model.Expense;


import java.util.List;

public interface ExpenseService {
    void addExpense(Expense expense);
    List<Expense> getAllExpenses();
    void deleteExpense(String id);

    void processExpense(Expense expense);
}

