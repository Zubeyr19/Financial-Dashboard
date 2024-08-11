package com.example.spring.services;

import com.example.spring.model.Expense;
import com.example.spring.observer.Subject;

import java.util.List;

public interface ExpenseService extends Subject {
    void addExpense(Expense expense);
    List<Expense> getAllExpenses();
    void deleteExpense(String id);
    void processExpense(Expense expense);
}